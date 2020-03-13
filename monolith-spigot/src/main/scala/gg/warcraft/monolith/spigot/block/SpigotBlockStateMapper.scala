package gg.warcraft.monolith.spigot.block

import java.util

import gg.warcraft.monolith.api.block._
import gg.warcraft.monolith.api.block.state._
import gg.warcraft.monolith.api.block.variant.BambooVariant
import org.bukkit.{Material, Note => SpigotNote}
import org.bukkit.block.data.{Ageable, AnaloguePowerable, Levelled}

private object SpigotBlockStateMapper {
  private final val matCache: util.EnumMap[Material, SpigotBlockData => BlockState] =
    new util.EnumMap(classOf[Material])

  private final val stateCache: util.HashMap[BlockState, Material] =
    new util.HashMap()
}

class SpigotBlockStateMapper {
  def map(block: SpigotBlock): BlockState =
    SpigotBlockStateMapper.matCache
      .computeIfAbsent(block.getType, compute)
      .apply(block.getBlockData)

  def map(state: BlockState): Material =
    SpigotBlockStateMapper.stateCache.computeIfAbsent(state, compute)

  def map(state: BlockState, data: SpigotBlockData): Unit = state match {
    case it: CakeState =>
      data.asInstanceOf[SpigotCake].setBites(it.toInt)

    case it: NoteBlockState =>
      val note = new SpigotNote(it.toInt)
      data.asInstanceOf[SpigotNoteBlock].setNote(note)

    case it: RepeaterState =>
      data.asInstanceOf[SpigotRepeater].setDelay(it.toInt)

    case it: SaplingState =>
      data.asInstanceOf[SpigotSapling].setStage(it.toInt)

    case it: SeaPickleState =>
      data.asInstanceOf[SpigotSeaPickle].setPickles(it.toInt)

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

  def map(b: StatefulBlock[_ <: BlockState], data: SpigotBlockData): Unit = b match {
    case it: Bamboo =>
      if (it.variant != BambooVariant.SAPLING)
        data.asInstanceOf[SpigotBamboo].setStage(it.state.toInt)

    case it => map(it.state, data)
  }

  private def compute(material: Material): SpigotBlockData => BlockState = {
    val age = (data: SpigotBlockData) => data.asInstanceOf[Ageable].getAge
    val level = (data: SpigotBlockData) => data.asInstanceOf[Levelled].getLevel
    val power = (d: SpigotBlockData) => d.asInstanceOf[AnaloguePowerable].getPower
    material match {
      case Material.BEE_NEST         => (data => BeeNestState.valueOf(level(data)))
      case Material.BEEHIVE          => (data => BeehiveState.valueOf(level(data)))
      case Material.BEETROOTS        => (data => BeetrootState.valueOf(age(data)))
      case Material.CACTUS           => (data => CactusState.valueOf(age(data)))
      case Material.CARROTS          => (data => CarrotState.valueOf(age(data)))
      case Material.CAULDRON         => (data => CauldronState.valueOf(level(data)))
      case Material.CHORUS_FLOWER    => (data => ChorusFlowerState.valueOf(age(data)))
      case Material.COCOA            => (data => CocoaState.valueOf(age(data)))
      case Material.COMPOSTER        => (data => ComposterState.valueOf(level(data)))
      case Material.FIRE             => (data => FireState.valueOf(age(data)))
      case Material.FROSTED_ICE      => (data => FrostState.valueOf(age(data)))
      case Material.KELP             => (data => KelpState.valueOf(age(data)))
      case Material.LAVA             => (data => LavaState.valueOf(level(data)))
      case Material.MELON_STEM       => (data => MelonStemState.valueOf(age(data)))
      case Material.NETHER_WART      => (data => NetherWartState.valueOf(age(data)))
      case Material.POTATOES         => (data => PotatoState.valueOf(age(data)))
      case Material.PUMPKIN_STEM     => (data => PumpkinStemState.valueOf(age(data)))
      case Material.REDSTONE_WIRE    => (data => RedstoneWireState.valueOf(power(data)))
      case Material.SUGAR_CANE       => (data => SugarCaneState.valueOf(age(data)))
      case Material.SWEET_BERRY_BUSH => (data => SweetBerryState.valueOf(age(data)))
      case Material.WATER            => (data => WaterState.valueOf(level(data)))
      case Material.WHEAT            => (data => WheatState.valueOf(age(data)))

      case Material.KELP_PLANT            => (_ => KelpState.FULLY_GROWN)
      case Material.ATTACHED_MELON_STEM   => (_ => MelonStemState.ATTACHED)
      case Material.ATTACHED_PUMPKIN_STEM => (_ => PumpkinStemState.ATTACHED)

      // BAMBOO
      case Material.BAMBOO =>
        data =>
          val stage = data.asInstanceOf[SpigotBamboo].getStage
          BambooState.valueOf(stage)

      // CAKE
      case Material.CAKE =>
        data =>
          val bites = data.asInstanceOf[SpigotCake].getBites
          CakeState.valueOf(bites)

      // NOTE_BLOCK
      case Material.NOTE_BLOCK =>
        data =>
          val note = data.asInstanceOf[SpigotNoteBlock].getNote.getId
          NoteBlockState.valueOf(note)

      // REPEATER
      case Material.REPEATER =>
        data =>
          val delay = data.asInstanceOf[SpigotRepeater].getDelay
          RepeaterState.valueOf(delay)

      // SAPLING
      case Material.ACACIA_SAPLING | Material.BIRCH_SAPLING |
          Material.DARK_OAK_SAPLING | Material.JUNGLE_SAPLING |
          Material.OAK_SAPLING | Material.SPRUCE_SAPLING =>
        data =>
          val stage = data.asInstanceOf[SpigotSapling].getStage
          SaplingState.valueOf(stage)

      // SEA_PICKLE
      case Material.SEA_PICKLE =>
        data =>
          val pickles = data.asInstanceOf[SpigotSeaPickle].getPickles
          SeaPickleState.valueOf(pickles)

      // TURTLE_EGG
      case Material.TURTLE_EGG =>
        data =>
          val hatch = data.asInstanceOf[SpigotTurtleEgg].getHatch
          val eggs = data.asInstanceOf[SpigotTurtleEgg].getEggs
          TurtleEggState(
            TurtleEggAge.valueOf(hatch),
            TurtleEggCount.valueOf(eggs)
          )

      // WEIGHTED_PRESSURE_PLATE
      case Material.LIGHT_WEIGHTED_PRESSURE_PLATE |
          Material.HEAVY_WEIGHTED_PRESSURE_PLATE =>
        data => WeightedPressurePlateState.valueOf(power(data))

      case it => throw new IllegalArgumentException(s"$it")
    }
  }

  def compute(state: BlockState): Material = state match {
    case _: BambooState       => Material.BAMBOO
    case _: BeeNestState      => Material.BEE_NEST
    case _: BeehiveState      => Material.BEEHIVE
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

    // NOTE BambooState can not be mapped due to its compound nature, and Saplings &
    // WeightedPressurePlates can not be mapped as they also need a variant
  }
}
