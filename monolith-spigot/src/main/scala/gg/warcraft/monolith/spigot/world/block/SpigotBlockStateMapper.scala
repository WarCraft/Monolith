package gg.warcraft.monolith.spigot.world.block

import gg.warcraft.monolith.api.world.block._
import gg.warcraft.monolith.api.world.block.state._
import org.bukkit.{ Material, Note => SpigotNote }
import org.bukkit.block.data.{ Ageable, AnaloguePowerable, Levelled }

class SpigotBlockStateMapper {

  def map(block: SpigotBlock): BlockState = {
    implicit val data: SpigotBlockData = block.getState.getBlockData

    lazy val age   = dataAs[Ageable].getAge
    lazy val level = dataAs[Levelled].getLevel
    lazy val power = dataAs[AnaloguePowerable].getPower

    block.getType match {
      case Material.BEETROOTS        => BeetrootState.valueOf(age)
      case Material.CACTUS           => CactusState.valueOf(age)
      case Material.CARROTS          => CarrotState.valueOf(age)
      case Material.CAULDRON         => CauldronState.valueOf(level)
      case Material.CHORUS_FLOWER    => ChorusFlowerState.valueOf(age)
      case Material.COCOA            => CocoaState.valueOf(age)
      case Material.COMPOSTER        => ComposterState.valueOf(level)
      case Material.FIRE             => FireState.valueOf(age)
      case Material.FROSTED_ICE      => FrostState.valueOf(age)
      case Material.KELP_PLANT       => KelpState.valueOf(age)
      case Material.LAVA             => LavaState.valueOf(level)
      case Material.NETHER_WART      => NetherWartState.valueOf(age)
      case Material.POTATOES         => PotatoState.valueOf(age)
      case Material.REDSTONE_WIRE    => RedstoneWireState.valueOf(power)
      case Material.SUGAR_CANE       => SugarCaneState.valueOf(age)
      case Material.SWEET_BERRY_BUSH => SweetBerryState.valueOf(age)
      case Material.WATER            => WaterState.valueOf(level)
      case Material.WHEAT            => WheatState.valueOf(age)

      // BAMBOO
      case Material.BAMBOO =>
        val stage = dataAs[SpigotBamboo].getStage
        BambooState.valueOf(stage)

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
        val eggs  = dataAs[SpigotTurtleEgg].getEggs
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
      spigotData: SpigotBlockData
  ): Unit = {
    implicit val data: SpigotBlockData = spigotData

    val state = block.state.toInt
    data match {case it: Ageable => it.setAge(state)}
    data match {case it: Levelled => it.setLevel(state)}
    data match {case it: AnaloguePowerable => it.setPower(state)}

    block match {
      case it: Bamboo =>
        val bambooData = dataAs[SpigotBamboo]
        bambooData.setStage(it.state.toInt)

      case it: Cake =>
        val cakeData = dataAs[SpigotCake]
        cakeData.setBites(it.state.toInt)

      case it: NoteBlock =>
        val note          = new SpigotNote(it.state.toInt)
        val noteBlockData = dataAs[SpigotNoteBlock]
        noteBlockData.setNote(note)

      case it: Repeater =>
        val repeaterData = dataAs[SpigotRepeater]
        repeaterData.setDelay(it.state.toInt)

      case it: Sapling =>
        val saplingData = dataAs[SpigotSapling]
        saplingData.setStage(it.state.toInt)

      case it: SeaPickle =>
        val seaPickleData = dataAs[SpigotSeaPickle]
        seaPickleData.setPickles(it.state.toInt)

      case it: TurtleEgg =>
        val turtleEggData = dataAs[SpigotTurtleEgg]
        turtleEggData.setHatch(it.state.age.toInt)
        turtleEggData.setEggs(it.state.count.toInt)
    }
  }
}
