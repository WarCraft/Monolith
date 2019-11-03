package gg.warcraft.monolith.spigot.world.block

import gg.warcraft.monolith.api.world.block._
import gg.warcraft.monolith.api.world.block.state._
import gg.warcraft.monolith.api.world.block.variant.BambooVariant
import org.bukkit.{Material, Note => SpigotNote}
import org.bukkit.block.data.{Ageable, AnaloguePowerable, Levelled}

class SpigotBlockStateMapper {
  def map(block: SpigotBlock): BlockState = {
    val data: SpigotBlockData = block.getBlockData

    lazy val age = data.asInstanceOf[Ageable].getAge
    lazy val level = data.asInstanceOf[Levelled].getLevel
    lazy val power = data.asInstanceOf[AnaloguePowerable].getPower

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
        val stage = data.asInstanceOf[SpigotBamboo].getStage
        BambooState.valueOf(stage)

      // CAKE
      case Material.CAKE =>
        val bites = data.asInstanceOf[SpigotCake].getBites
        CakeState.valueOf(bites)

      // KELP
      case Material.KELP       => KelpState.valueOf(age)
      case Material.KELP_PLANT => KelpState.FULLY_GROWN

      // MELON
      case Material.MELON_STEM          => MelonStemState.valueOf(age)
      case Material.ATTACHED_MELON_STEM => MelonStemState.ATTACHED

      // NOTE_BLOCK
      case Material.NOTE_BLOCK =>
        val note = data.asInstanceOf[SpigotNoteBlock].getNote.getId
        NoteBlockState.valueOf(note)

      // PUMPKIN
      case Material.PUMPKIN_STEM          => PumpkinStemState.valueOf(age)
      case Material.ATTACHED_PUMPKIN_STEM => PumpkinStemState.ATTACHED

      // REPEATER
      case Material.REPEATER =>
        val delay = data.asInstanceOf[SpigotRepeater].getDelay
        RepeaterState.valueOf(delay)

      // SAPLING
      case Material.BAMBOO_SAPLING | Material.ACACIA_SAPLING |
          Material.BIRCH_SAPLING | Material.DARK_OAK_SAPLING |
          Material.JUNGLE_SAPLING | Material.OAK_SAPLING | Material.SPRUCE_SAPLING =>
        val stage = data.asInstanceOf[SpigotSapling].getStage
        SaplingState.valueOf(stage)

      // SEA_PICKLE
      case Material.SEA_PICKLE =>
        val pickles = data.asInstanceOf[SpigotSeaPickle].getPickles
        SeaPickleState.valueOf(pickles)

      // TURTLE_EGG
      case Material.TURTLE_EGG =>
        val hatch = data.asInstanceOf[SpigotTurtleEgg].getHatch
        val eggs = data.asInstanceOf[SpigotTurtleEgg].getEggs
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

  def map(block: StatefulBlock[_ <: BlockState], data: SpigotBlockData): Unit = {
    val state = block.state.toInt

    if (data.isInstanceOf[Ageable])
      data.asInstanceOf[Ageable].setAge(state)
    if (data.isInstanceOf[Levelled])
      data.asInstanceOf[Levelled].setLevel(state)
    if (data.isInstanceOf[AnaloguePowerable])
      data.asInstanceOf[AnaloguePowerable].setPower(state)

    block match {
      case it: Bamboo =>
        if (it.variant != BambooVariant.SAPLING) {
          val bambooData = data.asInstanceOf[SpigotBamboo]
          bambooData.setStage(it.state.toInt)
        }

      case it: Cake =>
        val cakeData = data.asInstanceOf[SpigotCake]
        cakeData.setBites(it.state.toInt)

      case it: NoteBlock =>
        val note = new SpigotNote(it.state.toInt)
        val noteBlockData = data.asInstanceOf[SpigotNoteBlock]
        noteBlockData.setNote(note)

      case it: Repeater =>
        val repeaterData = data.asInstanceOf[SpigotRepeater]
        repeaterData.setDelay(it.state.toInt)

      case it: Sapling =>
        val saplingData = data.asInstanceOf[SpigotSapling]
        saplingData.setStage(it.state.toInt)

      case it: SeaPickle =>
        val seaPickleData = data.asInstanceOf[SpigotSeaPickle]
        seaPickleData.setPickles(it.state.toInt)

      case it: TurtleEgg =>
        val turtleEggData = data.asInstanceOf[SpigotTurtleEgg]
        turtleEggData.setHatch(it.state.age.toInt)
        turtleEggData.setEggs(it.state.count.toInt)

      case _ => ()
    }
  }
}
