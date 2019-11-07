package gg.warcraft.monolith.spigot.world.block

import java.util

import gg.warcraft.monolith.api.world.block._
import gg.warcraft.monolith.api.world.block.state._
import gg.warcraft.monolith.api.world.block.variant.BambooVariant
import org.bukkit.{Material, Note => SpigotNote}
import org.bukkit.block.data.{Ageable, AnaloguePowerable, Levelled}

// TODO finish cached state mapper impl
private object SpigotBlockStateMapper {
  private final val materialCache: util.EnumMap[Material, () => BlockState] =
    new util.EnumMap(classOf[Material])

  private final val stateCache: util.HashMap[BlockState, Material] =
    new util.HashMap()
}

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

  def map(state: BlockState): Material = state match {
    case _: BambooState       => Material.BAMBOO
    case _: BeetrootState     => Material.BEETROOTS
    case _: CactusState       => Material.CACTUS
    case _: CakeState         => Material.CAKE
    case _: CarrotState       => Material.CARROTS
    case _: CauldronState     => Material.CAULDRON
    case _: ChorusFlowerState => Material.CHORUS_FLOWER
    case _: CocoaState        => Material.COCOA
    case _: ComposterState    => Material.COMPOSTER
    case _: FireState         => Material.FIRE
    case _: FrostState        => Material.FROSTED_ICE
    case _: LavaState         => Material.LAVA
    case _: MelonStemState    => Material.MELON_STEM
    case _: NetherWartState   => Material.NETHER_WART
    case _: NoteBlockState    => Material.NOTE_BLOCK
    case _: PotatoState       => Material.POTATOES
    case _: PumpkinStemState  => Material.PUMPKIN_STEM
    case _: RedstoneWireState => Material.REDSTONE_WIRE
    case _: RepeaterState     => Material.REPEATER
    case _: SeaPickleState    => Material.SEA_PICKLE
    case _: SugarCaneState    => Material.SUGAR_CANE
    case _: SweetBerryState   => Material.SWEET_BERRY_BUSH
    case _: WaterState        => Material.WATER
    case _: WheatState        => Material.WHEAT

    // KELP
    case KelpState.FULLY_GROWN => Material.KELP_PLANT
    case _: KelpState          => Material.KELP

    // NOTE BambooState can not be mapped due to its compound nature, and Saplings
    // WeightedPressurePlates can not be mapped as they also need a variant
  }

  def map(state: BlockState, data: SpigotBlockData): Unit = state match {
    case it: CakeState =>
      val cakeData = data.asInstanceOf[SpigotCake]
      cakeData.setBites(it.toInt)

    case it: NoteBlockState =>
      val note = new SpigotNote(it.toInt)
      val noteBlockData = data.asInstanceOf[SpigotNoteBlock]
      noteBlockData.setNote(note)

    case it: RepeaterState =>
      val repeaterData = data.asInstanceOf[SpigotRepeater]
      repeaterData.setDelay(it.toInt)

    case it: SaplingState =>
      val saplingData = data.asInstanceOf[SpigotSapling]
      saplingData.setStage(it.toInt)

    case it: SeaPickleState =>
      val seaPickleData = data.asInstanceOf[SpigotSeaPickle]
      seaPickleData.setPickles(it.toInt)

    case it: TurtleEggState =>
      val turtleEggData = data.asInstanceOf[SpigotTurtleEgg]
      turtleEggData.setHatch(it.age.toInt)
      turtleEggData.setEggs(it.count.toInt)

    case _ =>
      if (data.isInstanceOf[Ageable])
        data.asInstanceOf[Ageable].setAge(state.toInt)
      if (data.isInstanceOf[Levelled])
        data.asInstanceOf[Levelled].setLevel(state.toInt)
      if (data.isInstanceOf[AnaloguePowerable])
        data.asInstanceOf[AnaloguePowerable].setPower(state.toInt)
  }

  def map(block: StatefulBlock[_ <: BlockState], data: SpigotBlockData): Unit = {
    block match {
      case it: Bamboo =>
        if (it.variant != BambooVariant.SAPLING) {
          val bambooData = data.asInstanceOf[SpigotBamboo]
          bambooData.setStage(it.state.toInt)
        }

      case it => map(it.state, data)
    }
  }
}
