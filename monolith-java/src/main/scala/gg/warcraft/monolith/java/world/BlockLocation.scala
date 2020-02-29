package gg.warcraft.monolith.java.world

import gg.warcraft.monolith.api.world.World
import gg.warcraft.monolith.java.math.Vector3i

object BlockLocation {
  private[java] implicit def fromScala(loc: ScalaBlockLocation): BlockLocation =
    new BlockLocation(loc)
}

class BlockLocation private[java] (private[java] val asScala: ScalaBlockLocation) {
  val world: World = asScala.world
  val translation: Vector3i = asScala.translation
  val x: Int = translation.x
  val y: Int = translation.y
  val z: Int = translation.z

  def this(world: World, transl: Vector3i) =
    this(new ScalaBlockLocation(world, transl))
  def this(world: World, x: Int, y: Int, z: Int) =
    this(world, new Vector3i(x, y, z))

  def withWorld(world: World): BlockLocation =
    asScala.copy(world = world)
  def withTranslation(transl: Vector3i): BlockLocation =
    asScala.copy(translation = transl)
  def withX(x: Int): BlockLocation =
    asScala.copy(translation = translation.asScala.copy(x = x))
  def withY(y: Int): BlockLocation =
    asScala.copy(translation = translation.asScala.copy(y = y))
  def withZ(z: Int): BlockLocation =
    asScala.copy(translation = translation.asScala.copy(z = z))

  def add(x: Int, y: Int, z: Int): BlockLocation = asScala.add(x, y, z)
  def add(vec: Vector3i): BlockLocation = asScala.add(vec)
  def add(loc: BlockLocation): BlockLocation = asScala.add(loc)

  def subtract(x: Int, y: Int, z: Int): BlockLocation = asScala.subtract(x, y, z)
  def subtract(vec: Vector3i): BlockLocation = asScala.subtract(vec)
  def subtract(loc: BlockLocation): BlockLocation = asScala.subtract(loc)

  def toLocation: Location =
    gg.warcraft.monolith.api.world.BlockLocation.toLocation(asScala)
}
