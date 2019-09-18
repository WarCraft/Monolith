package gg.warcraft.monolith.api.world.block

import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.api.world.block.material._
import gg.warcraft.monolith.api.world.block.state._

case class Ladder(
  location: BlockLocation,
  facing: BlockFace,
  flooded: Boolean
) extends DirectionalBlock with FloodableBlock {
  override val kind = BlockType.LADDER
  override def withLocation(loc: BlockLocation): Ladder = copy(location = loc)
  override def withFacing(facing: BlockFace): Ladder = copy(facing = facing)
  override def withFlooded(flooded: Boolean): Ladder = copy(flooded = flooded)
}

case class Lantern(
  location: BlockLocation,
  hanging: Boolean
) extends Block {
  override val kind = BlockType.LANTERN
  override def withLocation(loc: BlockLocation): Lantern = copy(location = loc)
  def withHanging(hanging: Boolean): Lantern = copy(hanging = hanging)
}

// TODO add falling: Boolean
case class Lava(
  location: BlockLocation,
  state: LavaState
) extends StatefulBlock[LavaState] {
  override val kind = BlockType.LAVA
  override val liquid: Boolean = true
  override def withLocation(loc: BlockLocation): Lava = copy(location = loc)
  override def withState(state: LavaState): Lava = copy(state = state)
}

case class Leaves(
  location: BlockLocation,
  material: WoodMaterial
) extends MaterialBlock[WoodMaterial] {
  override val kind = BlockType.LEAVES
  override def withLocation(loc: BlockLocation): Leaves = copy(location = loc)
  override def withMaterial(mat: WoodMaterial): Leaves = copy(material = mat)
}

case class Lectern(
  location: BlockLocation,
  facing: BlockFace,
  powered: Boolean,
  book: Boolean
) extends DirectionalBlock with PowerableBlock {
  override val kind = BlockType.LECTERN
  override def withLocation(loc: BlockLocation): Lectern = copy(location = loc)
  override def withFacing(facing: BlockFace): Lectern = copy(facing = facing)
  override def withPowered(powered: Boolean): Lectern = copy(powered = powered)
  def withBook(book: Boolean): Lectern = copy(book = book)
}

case class Lever(
  location: BlockLocation,
  facing: BlockFace,
  attached: BlockAttachment,
  powered: Boolean
) extends DirectionalBlock with AttachedBlock with PowerableBlock {
  override val kind = BlockType.LEVER
  override def withLocation(loc: BlockLocation): Lever = copy(location = loc)
  override def withFacing(facing: BlockFace): Lever = copy(facing = facing)
  override def withAttached(attached: BlockAttachment): Lever = copy(attached = attached)
  override def withPowered(powered: Boolean): Lever = copy(powered = powered)
}

case class LilyPad(location: BlockLocation) extends Block {
  override val kind = BlockType.LILY_PAD
  override def withLocation(loc: BlockLocation): LilyPad = copy(location = loc)
}

case class Log(
  location: BlockLocation,
  orientation: BlockOrientation,
  stripped: Boolean
) extends OrientedBlock {
  override val kind = BlockType.LOG
  override def withLocation(loc: BlockLocation): Log = copy(location = loc)
  override def withOrientation(orientation: BlockOrientation): Log = copy(orientation = orientation)
  def withStripped(stripped: Boolean): Log = copy(stripped = stripped)
}

case class Loom(
  location: BlockLocation,
  facing: BlockFace
) extends DirectionalBlock {
  override val kind = BlockType.LOOM
  override def withLocation(loc: BlockLocation): Loom = copy(location = loc)
  override def withFacing(facing: BlockFace): Loom = copy(facing = facing)
}

case class Magma(location: BlockLocation) extends Block {
  override val kind = BlockType.MAGMA
  override def withLocation(loc: BlockLocation): Magma = copy(location = loc)
}

case class Melon(location: BlockLocation) extends Block {
  override val kind = BlockType.MELON
  override def withLocation(loc: BlockLocation): Melon = copy(location = loc)
}

case class MelonStem(
  location: BlockLocation,
  state: MelonStemState,
  facing: Option[BlockFace]
) extends StatefulBlock[MelonStemState] with DirectableBlock {
  override val kind = BlockType.MELON_STEM
  override def withLocation(loc: BlockLocation): MelonStem = copy(location = loc)
  override def withState(state: MelonStemState): MelonStem = copy(state = state)
  override def withFacing(facing: Option[BlockFace]): MelonStem = copy(facing = facing)
}

case class Mineral(
  location: BlockLocation,
  material: MineralMaterial
) extends MaterialBlock[MineralMaterial] {
  override val kind = BlockType.MINERAL
  override def withLocation(loc: BlockLocation): Mineral = copy(location = loc)
  override def withMaterial(mat: MineralMaterial): Mineral = copy(material = mat)
}

case class MobHead(
  location: BlockLocation,
  material: MobHeadMaterial,
  facing: Option[BlockFace],
  rotation: Option[BlockRotation]
) extends MaterialBlock[MobHeadMaterial] with DirectableBlock with RotatableBlock {
  override val kind = BlockType.MOB_HEAD
  override def withLocation(loc: BlockLocation): MobHead = copy(location = loc)
  override def withMaterial(mat: MobHeadMaterial): MobHead = copy(material = mat)
  override def withFacing(facing: Option[BlockFace]): MobHead = copy(facing = facing)
  override def withRotation(rotation: Option[BlockRotation]): MobHead = copy(rotation = rotation)
}

case class Mushroom(
  location: BlockLocation,
  material: MushroomMaterial
) extends MaterialBlock[MushroomMaterial] {
  override val kind = BlockType.MUSHROOM
  override val solid: Boolean = false
  override def withLocation(loc: BlockLocation): Mushroom = copy(location = loc)
  override def withMaterial(material: MushroomMaterial): Mushroom = copy(material = material)
}

// TODO multi-orientations
case class MushroomBlock(
  location: BlockLocation,
  material: MushroomBlockMaterial
) extends MaterialBlock[MushroomBlockMaterial] {
  override val kind = BlockType.MUSHROOM_BLOCK
  override def withLocation(loc: BlockLocation): MushroomBlock = copy(location = loc)
  override def withMaterial(material: MushroomBlockMaterial): MushroomBlock = copy(material = material)
}

case class Mycelium(
  location: BlockLocation,
  snowy: Boolean
) extends SnowableBlock {
  override val kind = BlockType.MYCELIUM
  override def withLocation(loc: BlockLocation): Mycelium = copy(location = loc)
  override def withSnowy(snowy: Boolean): Mycelium = copy(snowy = snowy)
}

case class Netherrack(location: BlockLocation) extends Block {
  override val kind = BlockType.NETHERRACK
  override def withLocation(loc: BlockLocation): Netherrack = copy(location = loc)
}

case class NetherPortal(
  location: BlockLocation,
  orientation: BlockOrientation
) extends OrientedBlock {
  override val kind = BlockType.NETHER_PORTAL
  override def withLocation(loc: BlockLocation): NetherPortal = copy(location = loc)
  override def withOrientation(orientation: BlockOrientation): NetherPortal = copy(orientation = orientation)
}

case class NetherWarts(
  location: BlockLocation,
  state: NetherWartState
) extends StatefulBlock[NetherWartState] {
  override val kind = BlockType.NETHER_WARTS
  override val solid: Boolean = false
  override def withLocation(loc: BlockLocation): NetherWarts = copy(location = loc)
  override def withState(state: NetherWartState): NetherWarts = copy(state = state)
}

case class NetherWartBlock(location: BlockLocation) extends Block {
  override val kind = BlockType.NETHER_WART_BLOCK
  override def withLocation(loc: BlockLocation): NetherWartBlock = copy(location = loc)
}

case class NoteBlock(
  location: BlockLocation,
  material: NoteBlockMaterial,
  state: NoteBlockState,
  powered: Boolean
) extends MaterialBlock[NoteBlockMaterial] with StatefulBlock[NoteBlockState] with PowerableBlock {
  override val kind = BlockType.NOTE_BLOCK
  override def withLocation(loc: BlockLocation): NoteBlock = copy(location = loc)
  override def withMaterial(mat: NoteBlockMaterial): NoteBlock = copy(material = mat)
  override def withState(state: NoteBlockState): NoteBlock = copy(state = state)
  override def withPowered(powered: Boolean): NoteBlock = copy(powered = powered)
}

case class Observer(
  location: BlockLocation,
  facing: BlockFace,
  powered: Boolean
) extends DirectionalBlock with PowerableBlock {
  override val kind = BlockType.OBSERVER
  override def withLocation(loc: BlockLocation): Observer = copy(location = loc)
  override def withFacing(facing: BlockFace): Observer = copy(facing = facing)
  override def withPowered(powered: Boolean): Observer = copy(powered = powered)
}

case class Obsidian(location: BlockLocation) extends Block {
  override val kind = BlockType.OBSIDIAN
  override def withLocation(loc: BlockLocation): Obsidian = copy(location = loc)
}

case class Ore(
  location: BlockLocation,
  material: OreMaterial
) extends MaterialBlock[OreMaterial] {
  override val kind = BlockType.ORE
  override def withLocation(loc: BlockLocation): Ore = copy(location = loc)
  override def withMaterial(mat: OreMaterial): Ore = copy(material = mat)
}

case class Planks(
  location: BlockLocation,
  material: WoodMaterial
) extends MaterialBlock[WoodMaterial] {
  override val kind = BlockType.PLANKS
  override def withLocation(loc: BlockLocation): Planks = copy(location = loc)
  override def withMaterial(mat: WoodMaterial): Planks = copy(material = mat)
}

case class Pillar(
  location: BlockLocation,
  material: PillarMaterial
) extends MaterialBlock[PillarMaterial] {
  override val kind = BlockType.PILLAR
  override def withLocation(loc: BlockLocation): Pillar = copy(location = loc)
  override def withMaterial(mat: PillarMaterial): Pillar = copy(material = mat)
}

case class Piston(
  location: BlockLocation,
  facing: BlockFace,
  sticky: Boolean,
  extended: Boolean
) extends DirectionalBlock with StickyBlock {
  override val kind = BlockType.PISTON
  override def withLocation(loc: BlockLocation): Piston = copy(location = loc)
  override def withFacing(facing: BlockFace): Piston = copy(facing = facing)
  override def withSticky(sticky: Boolean): Piston = copy(sticky = sticky)
  def withExtended(extended: Boolean): Piston = copy(extended = extended)
}

case class Podzol(
  location: BlockLocation,
  snowy: Boolean
) extends SnowableBlock {
  override val kind = BlockType.PODZOL
  override def withLocation(loc: BlockLocation): Podzol = copy(location = loc)
  override def withSnowy(snowy: Boolean): Podzol = copy(snowy = snowy)
}

case class Potatoes(
  location: BlockLocation,
  state: PotatoState
) extends StatefulBlock[PotatoState] {
  override val kind = BlockType.POTATOES
  override val solid: Boolean = false
  override def withLocation(loc: BlockLocation): Potatoes = copy(location = loc)
  override def withState(state: PotatoState): Potatoes = copy(state = state)
}

case class PressurePlate(
  location: BlockLocation,
  material: PressurePlateMaterial,
  powered: Boolean
) extends MaterialBlock[PressurePlateMaterial] with PowerableBlock {
  override val kind = BlockType.PRESSURE_PLATE
  override def withLocation(loc: BlockLocation): PressurePlate = copy(location = loc)
  override def withMaterial(material: PressurePlateMaterial): PressurePlate = copy(material = material)
  override def withPowered(powered: Boolean): PressurePlate = copy(powered = powered)
}

case class Prismarine(
  location: BlockLocation,
  material: PrismarineMaterial
) extends MaterialBlock[PrismarineMaterial] {
  override val kind = BlockType.PRISMARINE
  override def withLocation(loc: BlockLocation): Prismarine = copy(location = loc)
  override def withMaterial(mat: PrismarineMaterial): Prismarine = copy(material = mat)
}

case class Pumpkin(
  location: BlockLocation,
  facing: Option[BlockFace],
  lit: Boolean,
  carved: Boolean
) extends DirectableBlock with LightableBlock {
  override val kind = BlockType.PUMPKIN
  override def withLocation(loc: BlockLocation): Pumpkin = copy(location = loc)
  override def withFacing(facing: Option[BlockFace]): Pumpkin = copy(facing = facing)
  override def withLit(lit: Boolean): Pumpkin = copy(lit = lit)
  def withCarved(carved: Boolean): Pumpkin = copy(carved = carved)
}

case class PumpkinStem(
  location: BlockLocation,
  state: PumpkinStemState,
  facing: Option[BlockFace]
) extends StatefulBlock[PumpkinStemState] with DirectableBlock {
  override val kind = BlockType.PUMPKIN_STEM
  override def withLocation(loc: BlockLocation): PumpkinStem = copy(location = loc)
  override def withState(state: PumpkinStemState): PumpkinStem = copy(state = state)
  override def withFacing(facing: Option[BlockFace]): PumpkinStem = copy(facing = facing)
}

case class Purpur(location: BlockLocation) extends Block {
  override val kind = BlockType.PURPUR
  override def withLocation(loc: BlockLocation): Purpur = copy(location = loc)
}

case class Quartz(
  location: BlockLocation,
  material: QuartzMaterial
) extends MaterialBlock[QuartzMaterial] {
  override val kind = BlockType.QUARTZ
  override def withLocation(loc: BlockLocation): Quartz = copy(location = loc)
  override def withMaterial(mat: QuartzMaterial): Quartz = copy(material = mat)
}

case class Rails(
  location: BlockLocation,
  material: RailsMaterial,
  state: RailsState,
  powered: Boolean
) extends MaterialBlock[RailsMaterial] with StatefulBlock[RailsState] with PowerableBlock {
  override val kind = BlockType.RAILS
  override def withLocation(loc: BlockLocation): Rails = copy(location = loc)
  override def withMaterial(mat: RailsMaterial): Rails = copy(material = mat)
  override def withState(state: RailsState): Rails = copy(state = state)
  override def withPowered(powered: Boolean): Rails = copy(powered = powered)
}

case class RedstoneLamp(
  location: BlockLocation,
  lit: Boolean
) extends LightableBlock {
  override val kind = BlockType.REDSTONE_LAMP
  override def withLocation(loc: BlockLocation): RedstoneLamp = copy(location = loc)
  override def withLit(lit: Boolean): RedstoneLamp = copy(lit = lit)
}

case class RedstoneTorch(
  location: BlockLocation,
  facing: Option[BlockFace],
  lit: Boolean
) extends DirectableBlock with LightableBlock {
  override val kind = BlockType.REDSTONE_TORCH
  override def withLocation(loc: BlockLocation): RedstoneTorch = copy(location = loc)
  override def withFacing(facing: Option[BlockFace]): RedstoneTorch = copy(facing = facing)
  override def withLit(lit: Boolean): RedstoneTorch = copy(lit = lit)
}

// TODO add NESW connections
case class RedstoneWire(
  location: BlockLocation,
  state: RedstoneWireState
) extends StatefulBlock[RedstoneWireState] {
  override val kind = BlockType.REDSTONE_WIRE
  override def withLocation(loc: BlockLocation): RedstoneWire = copy(location = loc)
  override def withState(state: RedstoneWireState): RedstoneWire = copy(state = state)
}

case class Repeater(
  location: BlockLocation,
  state: RepeaterState,
  facing: BlockFace,
  powered: Boolean,
  locked: Boolean
) extends StatefulBlock[RepeaterState] with DirectionalBlock with PowerableBlock {
  override val kind = BlockType.REPEATER
  override def withLocation(loc: BlockLocation): Repeater = copy(location = loc)
  override def withState(state: RepeaterState): Repeater = copy(state = state)
  override def withFacing(facing: BlockFace): Repeater = copy(facing = facing)
  override def withPowered(powered: Boolean): Repeater = copy(powered = powered)
  def withLocked(locked: Boolean): Repeater = copy(locked = locked)
}

case class Sand(
  location: BlockLocation,
  material: SandMaterial
) extends MaterialBlock[SandMaterial] {
  override val kind = BlockType.SAND
  override def withLocation(loc: BlockLocation): Sand = copy(location = loc)
  override def withMaterial(mat: SandMaterial): Sand = copy(material = mat)
}

case class Sandstone(
  location: BlockLocation,
  material: SandstoneMaterial,
  state: SandstoneState
) extends MaterialBlock[SandstoneMaterial] with StatefulBlock[SandstoneState] {
  override val kind = BlockType.SANDSTONE
  override def withLocation(loc: BlockLocation): Sandstone = copy(location = loc)
  override def withMaterial(mat: SandstoneMaterial): Sandstone = copy(material = mat)
  override def withState(state: SandstoneState): Sandstone = copy(state = state)
}

// TODO add growth stage state
case class Sapling(
  location: BlockLocation,
  material: SaplingMaterial,
  state: SaplingState
) extends MaterialBlock[SaplingMaterial] with StatefulBlock[SaplingState] {
  override val kind = BlockType.SAPLING
  override def withLocation(loc: BlockLocation): Sapling = copy(location = loc)
  override def withMaterial(material: SaplingMaterial): Sapling = copy(material = material)
  override def withState(state: SaplingState): Sapling = copy(state = state)
}

case class Scaffold(location: BlockLocation) extends Block {
  override val kind = BlockType.SCAFFOLD
  override def withLocation(loc: BlockLocation): Scaffold = copy(location = loc)
}

case class Seagrass(
  location: BlockLocation,
  section: BlockBisection,
  tall: Boolean
) extends BisectedBlock {
  override val kind = BlockType.SEAGRASS
  override def withLocation(loc: BlockLocation): Seagrass = copy(location = loc)
  override def withSection(section: BlockBisection): Seagrass = copy(section = section)
}

case class SeaLantern(location: BlockLocation) extends Block {
  override val kind = BlockType.SEA_LANTERN
  override def withLocation(loc: BlockLocation): SeaLantern = copy(location = loc)
}

case class SeaPickle(
  location: BlockLocation,
  state: SeaPickleState,
  flooded: Boolean
) extends StatefulBlock[SeaPickleState] with FloodableBlock {
  override val kind = BlockType.SEA_PICKLE
  override def withLocation(loc: BlockLocation): SeaPickle = copy(location = loc)
  override def withState(state: SeaPickleState): SeaPickle = copy(state = state)
  override def withFlooded(flooded: Boolean): SeaPickle = copy(flooded = flooded)
}

case class ShulkerBox(
  location: BlockLocation,
  color: Option[BlockColor]
) extends ColorableBlock {
  override val kind = BlockType.SHULKER_BOX
  override def withLocation(loc: BlockLocation): ShulkerBox = copy(location = loc)
  override def withColor(color: Option[BlockColor]): ShulkerBox = copy(color = color)
}

case class Sign(
  location: BlockLocation,
  facing: Option[BlockFace],
  rotation: Option[BlockRotation],
  flooded: Boolean
) extends DirectableBlock with RotatableBlock with FloodableBlock {
  override val kind = BlockType.SIGN
  override def withLocation(loc: BlockLocation): Sign = copy(location = loc)
  override def withFacing(facing: Option[BlockFace]): Sign = copy(facing = facing)
  override def withRotation(rotation: Option[BlockRotation]): Sign = copy(rotation = rotation)
  override def withFlooded(flooded: Boolean): Sign = copy(flooded = flooded)
}

case class Slab(
  location: BlockLocation,
  material: SlabMaterial,
  section: BlockBisection
) extends MaterialBlock[SlabMaterial] with BisectedBlock {
  override val kind = BlockType.SLAB
  override def withLocation(loc: BlockLocation): Slab = copy(location = loc)
  override def withMaterial(mat: SlabMaterial): Slab = copy(material = mat)
  override def withSection(section: BlockBisection): Slab = copy(section = section)
}

case class SlimeBlock(location: BlockLocation) extends Block {
  override val kind = BlockType.SLIME_BLOCK
  override def withLocation(loc: BlockLocation): SlimeBlock = copy(location = loc)
}

case class SmithingTable(location: BlockLocation) extends Block {
  override val kind = BlockType.SMITHING_TABLE
  override def withLocation(loc: BlockLocation): SmithingTable = copy(location = loc)
}

case class Smoker(
  location: BlockLocation,
  facing: BlockFace,
  lit: Boolean
) extends DirectionalBlock with LightableBlock {
  override val kind = BlockType.SMOKER
  override def withLocation(loc: BlockLocation): Smoker = copy(location = loc)
  override def withFacing(facing: BlockFace): Smoker = copy(facing = facing)
  override def withLit(lit: Boolean): Smoker = copy(lit = lit)
}

// TODO add size of layer
case class Snow(location: BlockLocation) extends Block {
  override val kind = BlockType.SNOW
  override def withLocation(loc: BlockLocation): Snow = copy(location = loc)
}

case class SnowBlock(location: BlockLocation) extends Block {
  override val kind = BlockType.SNOW_BLOCK
  override def withLocation(loc: BlockLocation): SnowBlock = copy(location = loc)
}

case class Spawner(location: BlockLocation) extends Block {
  override val kind = BlockType.SPAWNER
  override def withLocation(loc: BlockLocation): Spawner = copy(location = loc)
}

case class Sponge(
  location: BlockLocation,
  wet: Boolean
) extends Block {
  override val kind = BlockType.SPONGE
  override def withLocation(loc: BlockLocation): Sponge = copy(location = loc)
  def withWet(wet: Boolean): Sponge = copy(wet = wet)
}

case class Stairs(
  location: BlockLocation,
  material: StairsMaterial,
  state: StairsState,
  facing: BlockFace,
  section: BlockBisection,
  flooded: Boolean
) extends MaterialBlock[StairsMaterial] with StatefulBlock[StairsState]
  with DirectionalBlock with BisectedBlock with FloodableBlock {
  override val kind = BlockType.STAIRS
  override def withLocation(loc: BlockLocation): Stairs = copy(location = loc)
  override def withMaterial(mat: StairsMaterial): Stairs = copy(material = mat)
  override def withState(state: StairsState): Stairs = copy(state = state)
  override def withFacing(facing: BlockFace): Stairs = copy(facing = facing)
  override def withSection(section: BlockBisection): Stairs = copy(section = section)
  override def withFlooded(flooded: Boolean): Stairs = copy(flooded = flooded)
}

case class Stone(
  location: BlockLocation,
  material: StoneMaterial
) extends MaterialBlock[StoneMaterial] {
  override val kind = BlockType.STONE
  override def withLocation(loc: BlockLocation): Stone = copy(location = loc)
  override def withMaterial(mat: StoneMaterial): Stone = copy(material = mat)
}

case class StoneCutter(
  location: BlockLocation,
  facing: BlockFace
) extends DirectionalBlock {
  override val kind = BlockType.STONE_CUTTER
  override def withLocation(loc: BlockLocation): StoneCutter = copy(location = loc)
  override def withFacing(facing: BlockFace): StoneCutter = copy(facing = facing)
}

case class StructureBlock(
  location: BlockLocation,
  state: StructureBlockState
) extends StatefulBlock[StructureBlockState] {
  override val kind = BlockType.STRUCTURE_BLOCK
  override def withLocation(loc: BlockLocation): StructureBlock = copy(location = loc)
  override def withState(state: StructureBlockState): StructureBlock = copy(state = state)
}

case class SugarCane(
  location: BlockLocation,
  state: SugarCaneState
) extends StatefulBlock[SugarCaneState] {
  override val kind = BlockType.SUGAR_CANE
  override def withLocation(loc: BlockLocation): SugarCane = copy(state = state)
  override def withState(state: SugarCaneState): SugarCane = copy(state = state)
}

case class SweetBerryBush(
  location: BlockLocation,
  state: SweetBerryState
) extends StatefulBlock[SweetBerryState] {
  override val kind = BlockType.SWEET_BERRY_BUSH
  override val solid: Boolean = false
  override def withLocation(loc: BlockLocation): SweetBerryBush = copy(location = loc)
  override def withState(state: SweetBerryState): SweetBerryBush = copy(state = state)
}

case class Terracotta(
  location: BlockLocation,
  color: Option[BlockColor]
) extends ColorableBlock {
  override val kind = BlockType.TERRACOTTA
  override def withLocation(loc: BlockLocation): Terracotta = copy(location = loc)
  override def withColor(color: Option[BlockColor]): Terracotta = copy(color = color)
}

case class TNT(
  location: BlockLocation,
  unstable: Boolean
) extends Block {
  override val kind = BlockType.TNT
  override def withLocation(loc: BlockLocation): TNT = copy(location = loc)
  def withUnstable(unstable: Boolean): TNT = copy(unstable = unstable)
}

case class Torch(
  location: BlockLocation,
  facing: BlockFace,
  wall: Boolean
) extends DirectionalBlock {
  override val kind = BlockType.TORCH
  override def withLocation(loc: BlockLocation): Torch = copy(location = loc)
  override def withFacing(facing: BlockFace): Torch = copy(facing = facing)
  def withWall(wall: Boolean): Torch = copy(wall = wall)
}

case class Trapdoor(
  location: BlockLocation,
  material: TrapdoorMaterial,
  facing: BlockFace,
  section: BlockBisection,
  powered: Boolean,
  flooded: Boolean,
  open: Boolean
) extends MaterialBlock[TrapdoorMaterial] with DirectionalBlock with BisectedBlock
  with PowerableBlock with FloodableBlock with OpenableBlock {
  override val kind = BlockType.TRAPDOOR
  override def withLocation(loc: BlockLocation): Trapdoor = copy(location = loc)
  override def withMaterial(mat: TrapdoorMaterial): Trapdoor = copy(material = mat)
  override def withFacing(facing: BlockFace): Trapdoor = copy(facing = facing)
  override def withSection(section: BlockBisection): Trapdoor = copy(section = section)
  override def withPowered(powered: Boolean): Trapdoor = copy(powered = powered)
  override def withFlooded(flooded: Boolean): Trapdoor = copy(flooded = flooded)
  override def withOpen(open: Boolean): Trapdoor = copy(open = open)
}

case class TurtleEgg(
  location: BlockLocation,
  state: TurtleEggState,
  count: Int
) extends StatefulBlock[TurtleEggState] {
  override val kind = BlockType.TURTLE_EGG
  override def withLocation(loc: BlockLocation): TurtleEgg = copy(location = loc)
  override def withState(state: TurtleEggState): TurtleEgg = copy(state = state)
  def withCount(count: Int): TurtleEgg = copy(count = count)
}

case class Vine(
  location: BlockLocation,
  extensions: Set[BlockFace]
) extends ExtendableBlock {
  override val kind = BlockType.VINE
  override def withLocation(loc: BlockLocation): Vine = copy(location = loc)
  override def withExtensions(extensions: Set[BlockFace]): Vine = copy(extensions = extensions)
}

case class Wall(
  location: BlockLocation,
  material: WallMaterial,
  extensions: Set[BlockFace]
) extends MaterialBlock[WallMaterial] with ExtendableBlock {
  override val kind = BlockType.WALL
  override def withLocation(loc: BlockLocation): Wall = copy(location = loc)
  override def withMaterial(mat: WallMaterial): Wall = copy(material = mat)
  override def withExtensions(extensions: Set[BlockFace]): Wall = copy(extensions = extensions)
}

// TODO add falling: Boolean
case class Water(
  location: BlockLocation,
  state: WaterState
) extends StatefulBlock[WaterState] {
  override val kind = BlockType.WATER
  override val liquid: Boolean = true
  override def withLocation(loc: BlockLocation): Water = copy(location = loc)
  override def withState(state: WaterState): Water = copy(state = state)
}

case class WeightedPressurePlate(
  location: BlockLocation,
  material: WeightedPressurePlateMaterial,
  state: WeightedPressurePlateState
) extends MaterialBlock[WeightedPressurePlateMaterial] with StatefulBlock[WeightedPressurePlateState] {
  override val kind = BlockType.WEIGHTED_PRESSURE_PLATE
  override def withLocation(loc: BlockLocation): WeightedPressurePlate = copy(location = loc)
  override def withMaterial(material: WeightedPressurePlateMaterial): WeightedPressurePlate = copy(material = material)
  override def withState(state: WeightedPressurePlateState): WeightedPressurePlate = copy(state = state)
}

case class Wheat(
  location: BlockLocation,
  state: WheatState
) extends StatefulBlock[WheatState] {
  override val kind = BlockType.WHEAT
  override def withLocation(loc: BlockLocation): Wheat = copy(location = loc)
  override def withState(state: WheatState): Wheat = copy(state = state)
}

case class Wood(
  location: BlockLocation,
  material: WoodMaterial,
  stripped: Boolean
) extends MaterialBlock[WoodMaterial] {
  override val kind = BlockType.WOOD
  override def withLocation(loc: BlockLocation): Wood = copy(location = loc)
  override def withMaterial(mat: WoodMaterial): Wood = copy(material = mat)
  def withStripped(stripped: Boolean): Wood = copy(stripped = stripped)
}

case class Wool(
  location: BlockLocation,
  color: BlockColor
) extends ColoredBlock {
  override val kind = BlockType.WOOL
  override def withLocation(loc: BlockLocation): Wool = copy(location = loc)
  override def withColor(color: BlockColor): Wool = copy(color = color)
}
