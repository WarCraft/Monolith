/*
 * MIT License
 *
 * Copyright (c) 2020 WarCraft <https://github.com/WarCraft>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package gg.warcraft.monolith.api.block.build

import java.util.logging.Logger

import gg.warcraft.monolith.api.block.{Block, BlockFace, BlockType, Sign}
import gg.warcraft.monolith.api.block.box.{BlockBox, BlockBoxReader}
import gg.warcraft.monolith.api.core.MonolithConfig
import gg.warcraft.monolith.api.util.chaining._
import gg.warcraft.monolith.api.world.WorldService

class BlockBuildService(implicit
    logger: Logger,
    worldService: WorldService
) {
  private var buildsByType: Map[String, List[BlockBuild]] = Map.empty
  private var buildsByModel: Map[String, BlockBuild] = Map.empty

  def getBuilds(typed: String): List[BlockBuild] =
    buildsByType.getOrElse(typed, Nil)

  def getBuild(typed: String, model: String): Option[BlockBuild] =
    buildsByModel.get(s"$typed:$model")

  def readConfig(config: MonolithConfig): Unit = {
    logger.info("Initializing build repository, this may take a while...")
    val startTime = System.currentTimeMillis

    import config._
    new BlockBoxReader(buildRepositoryBox, buildRepositoryOrientation)
      .sliceY(
        buildRepositoryBox.lower
      ) // TODO have slice return a new reader on which getBlocks(ignore) can be called
      .filter { _.`type` == BlockType.SIGN }
      .map { _.asInstanceOf[Sign] }
      .filter { _.direction.isDefined }
      .map { initializeBuild(config, _) }
      .filter { _.nonEmpty }
      .map { _.get }
      .foreach { build =>
        buildsByType = buildsByType get build.typed match {
          case Some(builds) => buildsByType + (build.typed -> (build :: builds))
          case None         => Map.empty + (build.typed -> (build :: Nil))
        }
        buildsByModel += (s"${build.typed}:${build.model}" -> build)
      }

    val endTime = System.currentTimeMillis
    val duration = (endTime - startTime) / 1000
    logger info s"Initialized ${buildsByModel.size} builds in $duration seconds:"
    buildsByType foreach {
      case (typed, builds) =>
        logger info s"${builds.length} ${typed}s"
    }
  }

  private def initializeBuild(
      config: MonolithConfig,
      sign: Sign
  ): Option[BlockBuild] = {
    val header = sign.lines.head
    if (header.nonEmpty) {
      require(
        header contains ":",
        """Encountered build sign with illegal type and model. All bottom level wall 
          |mounted signs in the build repository need to specify a build type and
          |model on their first sign line as type:model like House:Human_1
          |""".stripMargin
      )

      val Array(typed, model) = header split ":"
      val boundingBox = computeBoundingBox(config, sign)
      val nextDirection = config.buildRepositoryOrientation.rotate(90)
      var extraSigns: List[Sign] = Nil
      // FIXME
      var nextSign = sign getRelative BlockFace.valueOf(nextDirection.name())
      while (nextSign.head.`type` == BlockType.SIGN) {
        extraSigns ::= nextSign.head.asInstanceOf[Sign]
        // FIXME
        nextSign = nextSign.head getRelative BlockFace.valueOf(nextDirection.name())
      }

      val allLines = sign.lines ++ (extraSigns flatMap { _.lines })

      BlockBuild(s"$typed:$model", typed, model, allLines, boundingBox) |> Some.apply
    } else None // sign contains extra data for another build
  }

  private def computeBoundingBox(config: MonolithConfig, sign: Sign): BlockBox = {
    val glassBlocks = searchFoundation(sign)
    if (glassBlocks.isEmpty) throw new IllegalStateException

    val foundationBB = BlockBox(glassBlocks)
    val maxY =
      new BlockBoxReader(foundationBB, config.buildRepositoryOrientation).getBlocks
        .map { _.location |> worldService.getHighestBlock }
        .map { _.location.y }
        .reduce { Math.max }

    foundationBB.copy(
      min = foundationBB.min + (0, 1, 0),
      max = foundationBB.max.copy(y = maxY)
    )
  }

  private def searchFoundation(sign: Sign): Set[Block] = {
    val attachedTo = sign.direction.get match {
      case BlockFace.NORTH => worldService.getBlock(sign.location + (0, 0, 1))
      case BlockFace.EAST  => worldService.getBlock(sign.location + (-1, 0, 0))
      case BlockFace.SOUTH => worldService.getBlock(sign.location + (0, 0, -1))
      case BlockFace.WEST  => worldService.getBlock(sign.location + (1, 0, 0))
      case _               => throw new IllegalArgumentException
    }

    if (attachedTo.`type` != BlockType.GLASS) throw new IllegalStateException

    var searching = true
    var searchedBlocks: Set[Block] = Set.empty
    var glassBlocks: Set[Block] = Set.empty
    var newGlassBlocks: Set[Block] = Set.empty

    newGlassBlocks += attachedTo
    while (searching) {
      val currentBlocks = newGlassBlocks
        .flatMap {
          _.getRelative(
            BlockFace.NORTH,
            BlockFace.EAST,
            BlockFace.SOUTH,
            BlockFace.WEST
          )
        }
        .filter { !searchedBlocks.contains(_) }
      searchedBlocks ++= currentBlocks

      newGlassBlocks = currentBlocks
        .filter { _.`type` == BlockType.GLASS }
        .filter { !glassBlocks.contains(_) }
      if (newGlassBlocks.isEmpty) searching = false
      glassBlocks ++= newGlassBlocks
    }

    glassBlocks
  }
}
