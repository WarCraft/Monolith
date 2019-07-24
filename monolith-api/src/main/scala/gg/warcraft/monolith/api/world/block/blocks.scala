package gg.warcraft.monolith.api.world.block

import gg.warcraft.monolith.api.world.BlockLocation

case class Air(location: BlockLocation) extends Block {
  override val isSolid: Boolean = false
  override def withLocation(loc: BlockLocation): Air = copy(location = loc)
}

case class Anvil(
  location: BlockLocation,
  state: AnvilState,
  facing: BlockFace
) extends StatefulBlock[AnvilState] with DirectionalBlock {
  override def withLocation(loc: BlockLocation): Anvil = copy(location = loc)
  override def withState(state: AnvilState): Anvil = copy(state = state)
  override def withFacing(facing: BlockFace): Anvil = copy(facing = facing)
}

case class Barrier(location: BlockLocation) extends Block {
  override def withLocation(loc: BlockLocation): Barrier = copy(location = loc)
}

case class Beacon(location: BlockLocation) extends Block {
  override def withLocation(loc: BlockLocation): Beacon = copy(location = loc)
}

case class Bed(
  location: BlockLocation,
  color: BlockColor,
  facing: BlockFace,
  section: BlockBisection
) extends ColoredBlock with DirectionalBlock with BisectedBlock {
  override def withLocation(loc: BlockLocation): Bed = copy(location = loc)
  override def withColor(color: BlockColor): Bed = copy(color = color)
  override def withFacing(facing: BlockFace): Bed = copy(facing = facing)
  override def withSection(section: BlockBisection): Bed = copy(section = section)
}

case class Brick(
  location: BlockLocation,
  material: BrickMaterial
) extends MaterialBlock[BrickMaterial] {
  override def withLocation(loc: BlockLocation): Brick = copy(location = loc)
  override def withMaterial(mat: BrickMaterial): Brick = copy(material = mat)
}

case class Button(
  location: BlockLocation,
  material: ButtonMaterial,
  attachedTo: Block
) extends MaterialBlock[ButtonMaterial] with AttachedBlock {
  override def withLocation(loc: BlockLocation): Button = copy(location = loc)
  override def withMaterial(mat: ButtonMaterial): Button = copy(material = mat)
  override def withAttachedTo(attachedTo: Block): Button = copy(attachedTo = attachedTo)
}

case class Carpet(
  location: BlockLocation,
  color: BlockColor
) extends ColoredBlock {
  override def withLocation(loc: BlockLocation): Carpet = copy(location = loc)
  override def withColor(color: BlockColor): Carpet = copy(color = color)
}

case class Chest(
  location: BlockLocation,
  facing: BlockFace
) extends DirectionalBlock {
  override def withLocation(loc: BlockLocation): Chest = copy(location = loc)
  override def withFacing(facing: BlockFace): Chest = copy(facing = facing)
}

case class Cobweb(location: BlockLocation) extends Block {
  override def withLocation(loc: BlockLocation): Cobweb = copy(location = loc)
}

case class CommandBlock(location: BlockLocation) extends Block {
  override def withLocation(loc: BlockLocation): CommandBlock = copy(location = loc)
}

case class Concrete(
  location: BlockLocation,
  color: BlockColor
) extends ColoredBlock {
  override def withLocation(loc: BlockLocation): Concrete = copy(location = loc)
  override def withColor(color: BlockColor): Concrete = copy(color = color)
}

case class ConcretePowder(
  location: BlockLocation,
  color: BlockColor
) extends ColoredBlock {
  override def withLocation(loc: BlockLocation): ConcretePowder = copy(location = loc)
  override def withColor(color: BlockColor): ConcretePowder = copy(color = color)
}

case class Coral(
  location: BlockLocation,
  material: CoralMaterial
) extends MaterialBlock[CoralMaterial] {
  override val isSolid: Boolean = false
  override def withLocation(loc: BlockLocation): Coral = copy(location = loc)
  override def withMaterial(mat: CoralMaterial): Coral = copy(material = mat)
}

case class CoralBlock(
  location: BlockLocation,
  material: CoralMaterial
) extends MaterialBlock[CoralMaterial] {
  override def withLocation(loc: BlockLocation): CoralBlock = copy(location = loc)
  override def withMaterial(mat: CoralMaterial): CoralBlock = copy(material = mat)
}

case class CoralFan(
  location: BlockLocation,
  material: CoralMaterial
) extends MaterialBlock[CoralMaterial] {
  override val isSolid: Boolean = false
  override def withLocation(loc: BlockLocation): CoralFan = copy(location = loc)
  override def withMaterial(mat: CoralMaterial): CoralFan = copy(material = mat)
}

case class CoralWallFan(
  location: BlockLocation,
  material: CoralMaterial
) extends MaterialBlock[CoralMaterial] {
  override val isSolid: Boolean = false
  override def withLocation(loc: BlockLocation): CoralWallFan = copy(location = loc)
  override def withMaterial(mat: CoralMaterial): CoralWallFan = copy(material = mat)
}

// TODO still needs 'hinge' val
case class Door(
  location: BlockLocation,
  material: DoorMaterial,
  facing: BlockFace,
  section: BlockBisection,
  open: Boolean
) extends MaterialBlock[DoorMaterial] with DirectionalBlock with BisectedBlock with OpenableBlock {
  override def withLocation(loc: BlockLocation): Door = copy(location = loc)
  override def withMaterial(mat: DoorMaterial): Door = copy(material = mat)
  override def withFacing(facing: BlockFace): Door = copy(facing = facing)
  override def withSection(section: BlockBisection): Door = copy(section = section)
  override def withOpen(open: Boolean): Door = copy(open = open)
}

case class Fence(
  location: BlockLocation,
  material: FenceMaterial
) extends MaterialBlock[FenceMaterial] {
  override def withLocation(loc: BlockLocation): Fence = copy(location = loc)
  override def withMaterial(mat: FenceMaterial): Fence = copy(material = mat)
}

case class Fire(location: BlockLocation) extends Block {
  override def withLocation(loc: BlockLocation): Fire = copy(location = loc)
}

case class FletchingTable(location: BlockLocation) extends Block {
  override def withLocation(loc: BlockLocation): FletchingTable = copy(location = loc)
}

case class Flower(
  location: BlockLocation,
  material: FlowerMaterial,
  potted: Boolean
) extends MaterialBlock[FlowerMaterial] with PottableBlock {
  override def withLocation(loc: BlockLocation): Flower = copy(location = loc)
  override def withMaterial(mat: FlowerMaterial): Flower = copy(material = mat)
  override def withPotted(potted: Boolean): Flower = copy(potted = potted)
}

case class Gate(
  location: BlockLocation,
  material: WoodMaterial,
  facing: BlockFace,
  open: Boolean
) extends MaterialBlock[WoodMaterial] with DirectionalBlock with OpenableBlock {
  override def withLocation(loc: BlockLocation): Gate = copy(location = loc)
  override def withMaterial(mat: WoodMaterial): Gate = copy(material = mat)
  override def withFacing(facing: BlockFace): Gate = copy(facing = facing)
  override def withOpen(open: Boolean): Gate = copy(open = open)
}

case class GlazedTerracotta(
  location: BlockLocation,
  color: BlockColor
) extends ColoredBlock {
  override def withLocation(loc: BlockLocation): GlazedTerracotta = copy(location = loc)
  override def withColor(color: BlockColor): GlazedTerracotta = copy(color = color)
}

case class HardenedClay(
  location: BlockLocation,
  color: BlockColor
) extends ColoredBlock {
  override def withLocation(loc: BlockLocation): HardenedClay = copy(location = loc)
  override def withColor(color: BlockColor): HardenedClay = copy(color = color)
}

case class Lava(
  location: BlockLocation
) extends Block {
  override val isLiquid: Boolean = true
  override def withLocation(loc: BlockLocation): Lava = copy(location = loc)
}

case class Leaves(
  location: BlockLocation,
  material: WoodMaterial
) extends MaterialBlock[WoodMaterial] {
  override def withLocation(loc: BlockLocation): Leaves = copy(location = loc)
  override def withMaterial(mat: WoodMaterial): Leaves = copy(material = mat)
}

case class Lever(
  location: BlockLocation,
  facing: BlockFace,
  attachedTo: Block,
  inverted: Boolean,
  powered: Boolean
) extends DirectionalBlock with AttachedBlock with InvertableBlock with PowerableBlock {
  override def withLocation(loc: BlockLocation): Lever = copy(location = loc)
  override def withFacing(facing: BlockFace): Lever = copy(facing = facing)
  override def withAttachedTo(attachedTo: Block): Lever = copy(attachedTo = attachedTo)
  override def withInverted(inverted: Boolean): Lever = copy(inverted = inverted)
  override def withPowered(powered: Boolean): Lever = copy(powered = powered)
}

case class Mineral(
  location: BlockLocation,
  material: MineralMaterial
) extends MaterialBlock[MineralMaterial] {
  override def withLocation(loc: BlockLocation): Mineral = copy(location = loc)
  override def withMaterial(mat: MineralMaterial): Mineral = copy(material = mat)
}

// TODO map WALL MobHeads / attachedTo
case class MobHead(
  location: BlockLocation,
  material: MobHeadMaterial
) extends MaterialBlock[MobHeadMaterial] {
  override def withLocation(loc: BlockLocation): MobHead = copy(location = loc)
  override def withMaterial(mat: MobHeadMaterial): MobHead = copy(material = mat)
}

case class Ore(
  location: BlockLocation,
  material: OreMaterial
) extends MaterialBlock[OreMaterial] {
  override def withLocation(loc: BlockLocation): Ore = copy(location = loc)
  override def withMaterial(mat: OreMaterial): Ore = copy(material = mat)
}

case class Planks(
  location: BlockLocation,
  material: WoodMaterial
) extends MaterialBlock[WoodMaterial] {
  override def withLocation(loc: BlockLocation): Planks = copy(location = loc)
  override def withMaterial(mat: WoodMaterial): Planks = copy(material = mat)
}

case class Pillar(
  location: BlockLocation,
  material: PillarMaterial
) extends MaterialBlock[PillarMaterial] {
  override def withLocation(loc: BlockLocation): Pillar = copy(location = loc)
  override def withMaterial(mat: PillarMaterial): Pillar = copy(material = mat)
}

case class Piston(
  location: BlockLocation,
  facing: BlockFace,
  powered: Boolean,
  sticky: Boolean
) extends DirectionalBlock with PowerableBlock with StickyBlock {
  override def withLocation(loc: BlockLocation): Piston = copy(location = loc)
  override def withFacing(facing: BlockFace): Piston = copy(facing = facing)
  override def withPowered(powered: Boolean): Piston = copy(powered = powered)
  override def withSticky(sticky: Boolean): Piston = copy(sticky = sticky)
}

case class Prismarine(
  location: BlockLocation,
  material: PrismarineMaterial
) extends MaterialBlock[PrismarineMaterial] {
  override def withLocation(loc: BlockLocation): Prismarine = copy(location = loc)
  override def withMaterial(mat: PrismarineMaterial): Prismarine = copy(material = mat)
}

case class Purpur(
  location: BlockLocation
) extends Block {
  override def withLocation(loc: BlockLocation): Purpur = copy(location = loc)
}

case class Quartz(
  location: BlockLocation,
  material: QuartzMaterial
) extends MaterialBlock[QuartzMaterial] {
  override def withLocation(loc: BlockLocation): Quartz = copy(location = loc)
  override def withMaterial(mat: QuartzMaterial): Quartz = copy(material = mat)
}

case class Sand(
  location: BlockLocation,
  material: SandMaterial
) extends MaterialBlock[SandMaterial] {
  override def withLocation(loc: BlockLocation): Sand = copy(location = loc)
  override def withMaterial(mat: SandMaterial): Sand = copy(material = mat)
}

case class Sandstone(
  location: BlockLocation,
  material: SandstoneMaterial,
  state: SandstoneState
) extends MaterialBlock[SandstoneMaterial] with StatefulBlock[SandstoneState] {
  override def withLocation(loc: BlockLocation): Sandstone = copy(location = loc)
  override def withMaterial(mat: SandstoneMaterial): Sandstone = copy(material = mat)
  override def withState(state: SandstoneState): Sandstone = copy(state = state)
}

case class Scaffold(location: BlockLocation) extends Block {
  override def withLocation(loc: BlockLocation): Scaffold = copy(location = loc)
}

case class SeaLantern(location: BlockLocation) extends Block {
  override def withLocation(loc: BlockLocation): SeaLantern = copy(location = loc)
}

case class ShulkerBox(
  location: BlockLocation,
  color: BlockColor
) extends ColoredBlock {
  override def withLocation(loc: BlockLocation): ShulkerBox = copy(location = loc)
  override def withColor(color: BlockColor): ShulkerBox = copy(color = color)
}

case class Sign(
  location: BlockLocation
) extends Block {
  override def withLocation(loc: BlockLocation): Sign = copy(location = loc)
}

case class Slab(
  location: BlockLocation,
  material: SlabMaterial,
  section: BlockBisection
) extends MaterialBlock[SlabMaterial] with BisectedBlock {
  override def withLocation(loc: BlockLocation): Slab = copy(location = loc)
  override def withMaterial(mat: SlabMaterial): Slab = copy(material = mat)
  override def withSection(section: BlockBisection): Slab = copy(section = section)
}

case class SmithingTable(location: BlockLocation) extends Block {
  override def withLocation(loc: BlockLocation): SmithingTable = copy(location = loc)
}

case class StainedGlass(
  location: BlockLocation,
  color: BlockColor
) extends ColoredBlock {
  override def withLocation(loc: BlockLocation): StainedGlass = copy(location = loc)
  override def withColor(color: BlockColor): StainedGlass = copy(color = color)
}

case class StainedGlassPane(
  location: BlockLocation,
  color: BlockColor
) extends ColoredBlock {
  override def withLocation(loc: BlockLocation): StainedGlassPane = copy(location = loc)
  override def withColor(color: BlockColor): StainedGlassPane = copy(color = color)
}

case class Stairs(
  location: BlockLocation,
  material: StairsMaterial,
  attachedTo: Block,
  inverted: Boolean
) extends MaterialBlock[StairsMaterial] with AttachedBlock with InvertableBlock {
  override def withLocation(loc: BlockLocation): Stairs = copy(location = loc)
  override def withMaterial(mat: StairsMaterial): Stairs = copy(material = mat)
  override def withAttachedTo(attachedTo: Block): Stairs = copy(attachedTo = attachedTo)
  override def withInverted(inverted: Boolean): Stairs = copy(inverted = inverted)
}

case class Stone(
  location: BlockLocation,
  material: StoneMaterial
) extends MaterialBlock[StoneMaterial] {
  override def withLocation(loc: BlockLocation): Stone = copy(location = loc)
  override def withMaterial(mat: StoneMaterial): Stone = copy(material = mat)
}

case class Terracotta(
  location: BlockLocation,
  color: BlockColor
) extends ColoredBlock {
  override def withLocation(loc: BlockLocation): Terracotta = copy(location = loc)
  override def withColor(color: BlockColor): Terracotta = copy(color = color)
}

case class Trapdoor(
  location: BlockLocation,
  material: TrapdoorMaterial,
  facing: BlockFace,
  inverted: Boolean,
  open: Boolean
) extends MaterialBlock[TrapdoorMaterial] with DirectionalBlock with InvertableBlock with OpenableBlock {
  override def withLocation(loc: BlockLocation): Trapdoor = copy(location = loc)
  override def withMaterial(mat: TrapdoorMaterial): Trapdoor = copy(material = mat)
  override def withFacing(facing: BlockFace): Trapdoor = copy(facing = facing)
  override def withInverted(inverted: Boolean): Trapdoor = copy(inverted = inverted)
  override def withOpen(open: Boolean): Trapdoor = copy(open = open)
}

case class Wall(
  location: BlockLocation,
  material: WallMaterial,
  attachedTo: Block,
  inverted: Boolean
) extends MaterialBlock[WallMaterial] with AttachedBlock with InvertableBlock {
  override def withLocation(loc: BlockLocation): Wall = copy(location = loc)
  override def withMaterial(mat: WallMaterial): Wall = copy(material = mat)
  override def withAttachedTo(attachedTo: Block): Wall = copy(attachedTo = attachedTo)
  override def withInverted(inverted: Boolean): Wall = copy(inverted = inverted)
}

case class Water(
  location: BlockLocation
) extends Block {
  override val isLiquid: Boolean = true
  override def withLocation(loc: BlockLocation): Water = copy(location = loc)
}

case class Wood(
  location: BlockLocation,
  material: WoodMaterial,
  stripped: Boolean
) extends MaterialBlock[WoodMaterial] {
  override def withLocation(loc: BlockLocation): Wood = copy(location = loc)
  override def withMaterial(mat: WoodMaterial): Wood = copy(material = mat)
}

case class Wool(
  location: BlockLocation,
  color: BlockColor
) extends ColoredBlock {
  override def withLocation(loc: BlockLocation): Wool = copy(location = loc)
  override def withColor(color: BlockColor): Wool = copy(color = color)
}
