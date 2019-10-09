package gg.warcraft.monolith.spigot.world.block

import gg.warcraft.monolith.api.world.block._
import gg.warcraft.monolith.api.world.block.`type`._
import gg.warcraft.monolith.api.world.block.state._
import org.bukkit.Material
import org.bukkit.block.{Block => SpigotBlock}
import org.bukkit.block.data.{
  Ageable,
  AnaloguePowerable,
  Levelled,
  BlockData => SpigotBlockData
}
import org.bukkit.block.data.`type`.{
  Cake => SpigotCake,
  NoteBlock => SpigotNoteBlock,
  Repeater => SpigotRepeater,
  Sapling => SpigotSapling,
  SeaPickle => SpigotSeaPickle,
  TurtleEgg => SpigotTurtleEgg
}

class SpigotBlockStateMapper {

  def map(block: SpigotBlock): BlockState = {
    implicit val data: SpigotBlockData = block.getState.getBlockData

    lazy val age = dataAs[Ageable].getAge
    lazy val level = dataAs[Levelled].getLevel
    lazy val power = dataAs[AnaloguePowerable].getPower

    block.getType match {
      case Material.BAMBOO           => BambooState.valueOf(age)
      case Material.BEETROOTS        => BeetrootState.valueOf(age)
      case Material.CACTUS           => CactusState.valueOf(age)
      case Material.CHORUS_FLOWER    => ChorusFlowerState.valueOf(age)
      case Material.COCOA            => CocoaState.valueOf(age)
      case Material.COMPOSTER        => ComposterState.valueOf(level)
      case Material.KELP_PLANT       => KelpState.valueOf(age)
      case Material.LAVA             => LavaState.valueOf(level)
      case Material.NETHER_WART      => NetherWartState.valueOf(age)
      case Material.POTATOES         => PotatoState.valueOf(age)
      case Material.REDSTONE_WIRE    => RedstoneWireState.valueOf(power)
      case Material.SUGAR_CANE       => SugarCaneState.valueOf(age)
      case Material.SWEET_BERRY_BUSH => SweetBerryState.valueOf(age)
      case Material.WATER            => WaterState.valueOf(level)
      case Material.WHEAT            => WheatState.valueOf(age)

      // CAKE
      case Material.CAKE =>
        val bites = dataAs[SpigotCake].getBites
        CakeState.valueOf(bites)

      // MELON_STEM
      case Material.MELON_STEM | Material.ATTACHED_MELON_STEM =>
        MelonStemState.valueOf(age)

      // NOTE_BLOCK
      case Material.NOTE_BLOCK =>
        val note = dataAs[SpigotNoteBlock].getNote.getId
        NoteBlockState.valueOf(note)

      // PUMPKIN_STEM
      case Material.PUMPKIN_STEM | Material.ATTACHED_PUMPKIN_STEM =>
        PumpkinStemState.valueOf(age)

      // REPEATER
      case Material.REPEATER =>
        val delay = dataAs[SpigotRepeater].getDelay
        RepeaterState.valueOf(delay)

      // SAPLING
      case Material.BAMBOO_SAPLING | Material.ACACIA_SAPLING |
          Material.BIRCH_SAPLING | Material.DARK_OAK_SAPLING |
          Material.JUNGLE_SAPLING | Material.OAK_SAPLING |
          Material.SPRUCE_SAPLING =>
        val stage = dataAs[SpigotSapling].getStage
        SaplingState.valueOf(stage)

      // SEA_PICKLE
      case Material.SEA_PICKLE =>
        val pickles = dataAs[SpigotSeaPickle].getPickles
        SeaPickleState.valueOf(pickles)

      // TURTLE_EGG
      case Material.TURTLE_EGG =>
        val hatch = dataAs[SpigotTurtleEgg].getHatch
        val eggs = dataAs[SpigotTurtleEgg].getEggs
        TurtleEggState(
          TurtleEggAge.valueOf(hatch),
          TurtleEggCount.valueOf(eggs)
        )

      // WEIGHTED_PRESSURE_PLATE
      case Material.LIGHT_WEIGHTED_PRESSURE_PLATE |
          Material.HEAVY_WEIGHTED_PRESSURE_PLATE =>
        WeightedPressurePlateState.valueOf(power)

      case _ => throw new IllegalArgumentException(s"${block.getType}")
    }
  }

  def map(
      block: StatefulBlock[_ <: BlockState],
      data: SpigotBlockData
  ): Unit = {
    implicit val data: SpigotBlockData = data

    val state = block.state.toInt
    data match { case it: Ageable           => it.setAge(state) }
    data match { case it: Levelled          => it.setLevel(state) }
    data match { case it: AnaloguePowerable => it.setPower(state) }

    block match {
      case it: TurtleEgg =>
        val turtleEggData = dataAs[SpigotTurtleEgg]
        turtleEggData.setHatch(it.state.age.toInt)
        turtleEggData.setEggs(it.state.count.toInt)

      // TODO continue for other StatefulBlocks
    }
  }
}
