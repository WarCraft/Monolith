package gg.warcraft.monolith.api.world.block

import gg.warcraft.monolith.api.world.BlockLocation

case class Air(
  location: BlockLocation,
  material: AirMaterial
) extends MaterialBlock[AirMaterial] {
  override val isSolid: Boolean = false
  override def withLocation(loc: BlockLocation): Air = copy(location = loc)
  override def withMaterial(material: AirMaterial): Air = copy(material = material)
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

case class Bamboo(
  location: BlockLocation,
  material: BambooLeavesMaterial,
  state: BambooState,
  thick: Boolean
) extends MaterialBlock[BambooLeavesMaterial] with StatefulBlock[BambooState] {
  override def withLocation(loc: BlockLocation): Bamboo = copy(location = loc)
  override def withMaterial(material: BambooLeavesMaterial): Bamboo = copy(material = material)
  override def withState(state: BambooState): Bamboo = copy(state = state)
  def withThick(thick: Boolean): Bamboo = copy(thick = thick)
}

case class Barrel(
  location: BlockLocation,
  facing: BlockFace,
  open: Boolean
) extends DirectionalBlock with OpenableBlock {
  override def withLocation(loc: BlockLocation): Barrel = copy(location = loc)
  override def withFacing(facing: BlockFace): Barrel = copy(facing = facing)
  override def withOpen(open: Boolean): Barrel = copy(open = open)
}

case class Barrier(location: BlockLocation) extends Block {
  override def withLocation(loc: BlockLocation): Barrier = copy(location = loc)
}

case class Beacon(location: BlockLocation) extends Block {
  override def withLocation(loc: BlockLocation): Beacon = copy(location = loc)
}

// TODO add occupied flag
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

case class Bedrock(location: BlockLocation) extends Block {
  override def withLocation(loc: BlockLocation): Bedrock = copy(location = loc)
}

case class Beetroots(
  location: BlockLocation,
  state: BeetrootState
) extends StatefulBlock[BeetrootState] {
  override val isSolid: Boolean = false
  override def withLocation(loc: BlockLocation): Beetroots = copy(location = loc)
  override def withState(state: BeetrootState): Beetroots = copy(state = state)
}

// TODO add attachment val (NOTE this one is different for Bells from attachedTo)
case class Bell(
  location: BlockLocation,
  facing: BlockFace,
) extends DirectionalBlock {
  override def withLocation(loc: BlockLocation): Bell = copy(location = loc)
  override def withFacing(facing: BlockFace): Bell = copy(facing = facing)
}

case class BlastFurnace(
  location: BlockLocation,
  facing: BlockFace,
  lit: Boolean
) extends DirectionalBlock with LightableBlock {
  override def withLocation(loc: BlockLocation): BlastFurnace = copy(location = loc)
  override def withFacing(facing: BlockFace): BlastFurnace = copy(facing = facing)
  override def withLit(lit: Boolean): BlastFurnace = copy(lit = lit)
}

case class Bone(
  location: BlockLocation,
  orientation: BlockOrientation
) extends OrientableBlock {
  override def withLocation(loc: BlockLocation): Bone = copy(location = loc)
  override def withOrientation(orientation: BlockOrientation): Bone = copy(orientation = orientation)
}

case class Bookshelf(location: BlockLocation) extends Block {
  override def withLocation(loc: BlockLocation): Bookshelf = copy(location = loc)
}

// TODO add val which denotes which vials are filled
case class BrewingStand(location: BlockLocation) extends Block {
  override def withLocation(loc: BlockLocation): BrewingStand = copy(location = loc)
}

case class Brick(
  location: BlockLocation,
  material: BrickMaterial
) extends MaterialBlock[BrickMaterial] {
  override def withLocation(loc: BlockLocation): Brick = copy(location = loc)
  override def withMaterial(mat: BrickMaterial): Brick = copy(material = mat)
}

case class BubbleColumn(
  location: BlockLocation,
  drag: Boolean
) extends Block {
  override def withLocation(loc: BlockLocation): BubbleColumn = copy(location = loc)
  def withDrag(drag: Boolean): BubbleColumn = copy(drag = drag)
}

case class Button(
  location: BlockLocation,
  material: ButtonMaterial,
  attached: Block
) extends MaterialBlock[ButtonMaterial] with AttachedBlock {
  override def withLocation(loc: BlockLocation): Button = copy(location = loc)
  override def withMaterial(mat: ButtonMaterial): Button = copy(material = mat)
  override def withAttachedTo(attachedTo: Block): Button = copy(attached = attachedTo)
}

case class Cactus(
  location: BlockLocation,
  state: CactusState
) extends StatefulBlock[CactusState] {
  override def withLocation(loc: BlockLocation): Cactus = copy(location = loc)
  override def withState(state: CactusState): Cactus = copy(state = state)
}

case class Cake(
  location: BlockLocation,
  state: CakeState
) extends StatefulBlock[CakeState] {
  override def withLocation(loc: BlockLocation): Cake = copy(location = loc)
  override def withState(state: CakeState): Cake = copy(state = state)
}

case class Campfire(
  location: BlockLocation,
  facing: BlockFace,
  flooded: Boolean,
  lit: Boolean,
  signal: Boolean
) extends DirectionalBlock with FloodableBlock with LightableBlock {
  override def withLocation(loc: BlockLocation): Campfire = copy(location = loc)
  override def withFacing(facing: BlockFace): Campfire = copy(facing = facing)
  override def withFlooded(flooded: Boolean): Campfire = copy(flooded = flooded)
  override def withLit(lit: Boolean): Campfire = copy(lit = lit)
  def withSignal(signal: Boolean): Campfire = copy(signal = signal)
}

case class Carpet(
  location: BlockLocation,
  color: BlockColor
) extends ColoredBlock {
  override def withLocation(loc: BlockLocation): Carpet = copy(location = loc)
  override def withColor(color: BlockColor): Carpet = copy(color = color)
}

case class Carrots(
  location: BlockLocation,
  state: CarrotState
) extends StatefulBlock[CarrotState] {
  override def withLocation(loc: BlockLocation): Carrots = copy(location = loc)
  override def withState(state: CarrotState): Carrots = copy(state = state)
}

case class CartographyTable(location: BlockLocation) extends Block {
  override def withLocation(loc: BlockLocation): CartographyTable = copy(location = loc)
}

case class Cauldron(
  location: BlockLocation,
  state: CauldronState
) extends StatefulBlock[CauldronState] {
  override def withLocation(loc: BlockLocation): Cauldron = copy(location = loc)
  override def withState(state: CauldronState): Cauldron = copy(state = state)
}

case class Chest(
  location: BlockLocation,
  facing: BlockFace
) extends DirectionalBlock {
  override def withLocation(loc: BlockLocation): Chest = copy(location = loc)
  override def withFacing(facing: BlockFace): Chest = copy(facing = facing)
}

case class ChorusFlower(
  location: BlockLocation,
  state: ChorusFlowerState
) extends StatefulBlock[ChorusFlowerState] {
  override def withLocation(loc: BlockLocation): ChorusFlower = copy(location = loc)
  override def withState(state: ChorusFlowerState): ChorusFlower = copy(state = state)
}

case class Clay(location: BlockLocation) extends Block {
  override def withLocation(loc: BlockLocation): Clay = copy(location = loc)
}

case class Cobweb(location: BlockLocation) extends Block {
  override def withLocation(loc: BlockLocation): Cobweb = copy(location = loc)
}

case class Cocoa(
  location: BlockLocation,
  state: CocoaState,
  facing: BlockFace
) extends StatefulBlock[CocoaState] with DirectionalBlock {
  override def withLocation(loc: BlockLocation): Cocoa = copy(location = loc)
  override def withState(state: CocoaState): Cocoa = copy(state = state)
  override def withFacing(facing: BlockFace): Cocoa = copy(facing = facing)
}

case class CommandBlock(
  location: BlockLocation,
  material: CommandBlockMaterial,
  facing: BlockFace,
  conditional: Boolean
) extends MaterialBlock[CommandBlockMaterial] with DirectionalBlock {
  override def withLocation(loc: BlockLocation): CommandBlock = copy(location = loc)
  override def withMaterial(material: CommandBlockMaterial): CommandBlock = copy(material = material)
  override def withFacing(facing: BlockFace): CommandBlock = copy(facing = facing)
  def withConditional(conditional: Boolean): CommandBlock = copy(conditional = conditional)
}

case class Comparator(
  location: BlockLocation,
  state: ComparatorState,
  facing: BlockFace,
  powered: Boolean
) extends StatefulBlock[ComparatorState] with DirectionalBlock with PowerableBlock {
  override def withLocation(loc: BlockLocation): Comparator = copy(location = loc)
  override def withState(state: ComparatorState): Comparator = copy(state = state)
  override def withFacing(facing: BlockFace): Comparator = copy(facing = facing)
  override def withPowered(powered: Boolean): Comparator = copy(powered = powered)
}

case class Composter(
  location: BlockLocation,
  state: ComposterState
) extends StatefulBlock[ComposterState] {
  override def withLocation(loc: BlockLocation): Composter = copy(location = loc)
  override def withState(state: ComposterState): Composter = copy(state = state)
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

case class Conduit(
  location: BlockLocation,
  flooded: Boolean
) extends FloodableBlock {
  override def withLocation(loc: BlockLocation): Conduit = copy(location = loc)
  override def withFlooded(flooded: Boolean): Conduit = copy(flooded = flooded)
}

case class Coral(
  location: BlockLocation,
  material: CoralMaterial,
  flooded: Boolean
) extends MaterialBlock[CoralMaterial] with FloodableBlock {
  override val isSolid: Boolean = false
  override def withLocation(loc: BlockLocation): Coral = copy(location = loc)
  override def withMaterial(mat: CoralMaterial): Coral = copy(material = mat)
  override def withFlooded(flooded: Boolean): Coral = copy(flooded = flooded)
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
  material: CoralMaterial,
  facing: Option[BlockFace],
  flooded: Boolean
) extends MaterialBlock[CoralMaterial] with DirectableBlock with FloodableBlock {
  override val isSolid: Boolean = false
  override def withLocation(loc: BlockLocation): CoralFan = copy(location = loc)
  override def withMaterial(mat: CoralMaterial): CoralFan = copy(material = mat)
  override def withFacing(facing: Option[BlockFace]): CoralFan = copy(facing = facing)
  override def withFlooded(flooded: Boolean): CoralFan = copy(flooded = flooded)
}

case class CraftingTable(location: BlockLocation) extends Block {
  override def withLocation(loc: BlockLocation): CraftingTable = copy(location = loc)
}

case class DaylightDetector(location: BlockLocation) extends Block {
  override def withLocation(loc: BlockLocation): DaylightDetector = copy(location = loc)
}

case class DeadBush(location: BlockLocation) extends Block {
  override val isSolid: Boolean = false
  override def withLocation(loc: BlockLocation): DeadBush = copy(location = loc)
}

case class Dirt(
  location: BlockLocation,
  coarse: Boolean
) extends Block {
  override def withLocation(loc: BlockLocation): Dirt = copy(location = loc)
  def withCoarse(coarse: Boolean): Dirt = copy(coarse = coarse)
}

case class Dispenser(
  location: BlockLocation,
  facing: BlockFace,
  powered: Boolean
) extends DirectionalBlock with PowerableBlock {
  override def withLocation(loc: BlockLocation): Dispenser = copy(location = loc)
  override def withFacing(facing: BlockFace): Dispenser = copy(facing = facing)
  override def withPowered(powered: Boolean): Dispenser = copy(powered = powered)
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

case class DragonEgg(location: BlockLocation) extends Block {
  override def withLocation(loc: BlockLocation): DragonEgg = copy(location = loc)
}

case class DriedKelp(location: BlockLocation) extends Block {
  override def withLocation(loc: BlockLocation): DriedKelp = copy(location = loc)
}

case class Dropper(
  location: BlockLocation,
  facing: BlockFace,
  powered: Boolean
) extends DirectionalBlock with PowerableBlock {
  override def withLocation(loc: BlockLocation): Dropper = copy(location = loc)
  override def withFacing(facing: BlockFace): Dropper = copy(facing = facing)
  override def withPowered(powered: Boolean): Dropper = copy(powered = powered)
}

case class EnchantingTable(location: BlockLocation) extends Block {
  override def withLocation(loc: BlockLocation): EnchantingTable = copy(location = loc)
}

case class EndGateway(location: BlockLocation) extends Block {
  override def withLocation(loc: BlockLocation): EndGateway = copy(location = loc)
}

case class EndPortal(location: BlockLocation) extends Block {
  override def withLocation(loc: BlockLocation): EndPortal = copy(location = loc)
}

case class EndPortalFrame(
  location: BlockLocation,
  facing: BlockFace,
  eye: Boolean
) extends DirectionalBlock {
  override def withLocation(loc: BlockLocation): EndPortalFrame = copy(location = loc)
  override def withFacing(facing: BlockFace): EndPortalFrame = copy(facing = facing)
  def withEye(eye: Boolean): EndPortalFrame = copy(eye = eye)
}

case class EndRod(
  location: BlockLocation,
  facing: BlockFace
) extends DirectionalBlock {
  override def withLocation(loc: BlockLocation): EndRod = copy(location = loc)
  override def withFacing(facing: BlockFace): EndRod = copy(facing = facing)
}

// TODO add wetness state
case class Farmland(location: BlockLocation) extends Block {
  override def withLocation(loc: BlockLocation): Farmland = copy(location = loc)
}

case class Fence(
  location: BlockLocation,
  material: FenceMaterial,
  flooded: Boolean
) extends MaterialBlock[FenceMaterial] with FloodableBlock {
  override def withLocation(loc: BlockLocation): Fence = copy(location = loc)
  override def withMaterial(mat: FenceMaterial): Fence = copy(material = mat)
  override def withFlooded(flooded: Boolean): Fence = copy(flooded = flooded)
}

case class Fern(
  location: BlockLocation,
  section: BlockBisection,
  tall: Boolean
) extends BisectedBlock {
  override val isSolid: Boolean = false
  override def withLocation(loc: BlockLocation): Fern = copy(location = loc)
  override def withSection(section: BlockBisection): Fern = copy(section = section)
  def withTall(tall: Boolean): Fern = copy(tall = tall)
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
  section: BlockBisection,
  tall: Boolean
) extends MaterialBlock[FlowerMaterial] with BisectedBlock {
  override val isSolid: Boolean = false
  override def withLocation(loc: BlockLocation): Flower = copy(location = loc)
  override def withMaterial(mat: FlowerMaterial): Flower = copy(material = mat)
  override def withSection(section: BlockBisection): Flower = copy(section = section)
  def withTall(tall: Boolean): Flower = copy(tall = tall)
}

case class FlowerPot(
  location: BlockLocation,
  material: FlowerPotMaterial
) extends MaterialBlock[FlowerPotMaterial] {
  override def withLocation(loc: BlockLocation): FlowerPot = copy(location = loc)
  override def withMaterial(mat: FlowerPotMaterial): FlowerPot = copy(material = mat)
}

// TODO frost level
case class Frost(
  location: BlockLocation
) extends Block {
  override def withLocation(loc: BlockLocation): Frost = copy(location = loc)
}

case class Furnace(
  location: BlockLocation,
  facing: BlockFace,
  lit: Boolean
) extends DirectionalBlock with LightableBlock {
  override def withLocation(loc: BlockLocation): Furnace = copy(location = loc)
  override def withFacing(facing: BlockFace): Furnace = copy(facing = facing)
  override def withLit(lit: Boolean): Furnace = copy(lit = lit)
}

case class Gate(
  location: BlockLocation,
  material: WoodMaterial,
  facing: BlockFace,
  open: Boolean,
  powered: Boolean
) extends MaterialBlock[WoodMaterial] with DirectionalBlock with OpenableBlock with PowerableBlock {
  override def withLocation(loc: BlockLocation): Gate = copy(location = loc)
  override def withMaterial(mat: WoodMaterial): Gate = copy(material = mat)
  override def withFacing(facing: BlockFace): Gate = copy(facing = facing)
  override def withOpen(open: Boolean): Gate = copy(open = open)
  override def withPowered(pow: Boolean): Gate = copy(powered = pow)
}

case class Glass(
  location: BlockLocation,
  color: Option[BlockColor]
) extends ColorableBlock {
  override def withLocation(loc: BlockLocation): Glass = copy(location = loc)
  override def withColor(color: Option[BlockColor]): Glass = copy(color = color)
}

case class GlassPane(
  location: BlockLocation,
  color: Option[BlockColor]
) extends ColorableBlock {
  override def withLocation(loc: BlockLocation): GlassPane = copy(location = loc)
  override def withColor(color: Option[BlockColor]): GlassPane = copy(color = color)
}

case class GlazedTerracotta(
  location: BlockLocation,
  color: BlockColor
) extends ColoredBlock {
  override def withLocation(loc: BlockLocation): GlazedTerracotta = copy(location = loc)
  override def withColor(color: BlockColor): GlazedTerracotta = copy(color = color)
}

case class Glowstone(location: BlockLocation) extends Block {
  override def withLocation(loc: BlockLocation): Glowstone = copy(location = loc)
}

case class Grass(
  location: BlockLocation,
  section: BlockBisection,
  tall: Boolean
) extends BisectedBlock {
  override val isSolid: Boolean = false
  override def withLocation(loc: BlockLocation): Grass = copy(location = loc)
  override def withSection(section: BlockBisection): Grass = copy(section = section)
  def withTall(tall: Boolean): Grass = copy(tall = tall)
}

case class Gravel(location: BlockLocation) extends Block {
  override def withLocation(loc: BlockLocation): Gravel = copy(location = loc)
}

case class Grindstone(
  location: BlockLocation,
  facing: BlockFace,
  attached: BlockAttachment
) extends DirectionalBlock with AttachedBlock {
  override def withLocation(loc: BlockLocation): Grindstone = copy(location = loc)
  override def withFacing(facing: BlockFace): Grindstone = copy(facing = facing)
  override def withAttached(attached: BlockAttachment): Grindstone = copy(attached = attached)
}

case class HardenedClay(
  location: BlockLocation,
  color: BlockColor
) extends ColoredBlock {
  override def withLocation(loc: BlockLocation): HardenedClay = copy(location = loc)
  override def withColor(color: BlockColor): HardenedClay = copy(color = color)
}

case class HayBale(
  location: BlockLocation,
  orientation: BlockOrientation
) extends OrientableBlock {
  override def withLocation(loc: BlockLocation): HayBale = copy(location = loc)
  override def withOrientation(orientation: BlockOrientation): HayBale = copy(orientation = orientation)
}

case class Hopper(
  location: BlockLocation,
  facing: BlockFace
) extends DirectionalBlock {
  override def withLocation(loc: BlockLocation): Hopper = copy(location = loc)
  override def withFacing(facing: BlockFace): Hopper = copy(facing = facing)
}

case class Ice(
  location: BlockLocation,
  material: IceMaterial
) extends MaterialBlock[IceMaterial] {
  override def withLocation(loc: BlockLocation): Ice = copy(location = loc)
  override def withMaterial(mat: IceMaterial): Ice = copy(material = mat)
}

case class Jigsaw(
  location: BlockLocation,
  facing: BlockFace
) extends DirectionalBlock {
  override def withLocation(loc: BlockLocation): Jigsaw = copy(location = loc)
  override def withFacing(facing: BlockFace): Jigsaw = copy(facing = facing)
}

case class Jukebox(
  location: BlockLocation,
  record: Boolean
) extends Block {
  override def withLocation(loc: BlockLocation): Jukebox = copy(location = loc)
  def withRecord(record: Boolean): Jukebox = copy(record = record)
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

case class Lectern(
  location: BlockLocation,
  facing: BlockFace,
  powered: Boolean,
  book: Boolean
) extends DirectionalBlock with PowerableBlock {
  override def withLocation(loc: BlockLocation): Lectern = copy(location = loc)
  override def withFacing(facing: BlockFace): Lectern = copy(facing = facing)
  override def withPowered(powered: Boolean): Lectern = copy(powered = powered)
  def withBook(book: Boolean): Lectern = copy(book = book)
}

case class Lever(
  location: BlockLocation,
  facing: BlockFace,
  attached: Block,
  inverted: Boolean,
  powered: Boolean
) extends DirectionalBlock with AttachedBlock with InvertableBlock with PowerableBlock {
  override def withLocation(loc: BlockLocation): Lever = copy(location = loc)
  override def withFacing(facing: BlockFace): Lever = copy(facing = facing)
  override def withAttachedTo(attachedTo: Block): Lever = copy(attached = attachedTo)
  override def withInverted(inverted: Boolean): Lever = copy(inverted = inverted)
  override def withPowered(powered: Boolean): Lever = copy(powered = powered)
}

case class LilyPad(location: BlockLocation) extends Block {
  override def withLocation(loc: BlockLocation): LilyPad = copy(location = loc)
}

case class Log(
  location: BlockLocation,
  orientation: BlockOrientation,
  stripped: Boolean
) extends OrientableBlock {
  override def withLocation(loc: BlockLocation): Log = copy(location = loc)
  override def withOrientation(orientation: BlockOrientation): Log = copy(orientation = orientation)
  def withStripped(stripped: Boolean): Log = copy(stripped = stripped)
}

case class Loom(
  location: BlockLocation,
  facing: BlockFace
) extends DirectionalBlock {
  override def withLocation(loc: BlockLocation): Loom = copy(location = loc)
  override def withFacing(facing: BlockFace): Loom = copy(facing = facing)
}

case class Magma(location: BlockLocation) extends Block {
  override def withLocation(loc: BlockLocation): Magma = copy(location = loc)
}

case class Melon(location: BlockLocation) extends Block {
  override def withLocation(loc: BlockLocation): Melon = copy(location = loc)
}

case class MelonStem(
  location: BlockLocation,
  attached: Option[Block]
) extends AttachableBlock {
  override def withLocation(loc: BlockLocation): MelonStem = copy(location = loc)
  override def withAttachedTo(attachedTo: Option[Block]): MelonStem = copy(attached = attachedTo)
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

case class Mushroom(
  location: BlockLocation,
  material: MushroomMaterial
) extends MaterialBlock[MushroomMaterial] {
  override val isSolid: Boolean = false
  override def withLocation(loc: BlockLocation): Mushroom = copy(location = loc)
  override def withMaterial(material: MushroomMaterial): Mushroom = copy(material = material)
}

// TODO multi-orientations
case class MushroomBlock(
  location: BlockLocation,
  material: MushroomBlockMaterial
) extends MaterialBlock[MushroomBlockMaterial] {
  override def withLocation(loc: BlockLocation): MushroomBlock = copy(location = loc)
  override def withMaterial(material: MushroomBlockMaterial): MushroomBlock = copy(material = material)
}

case class Mycelium(
  location: BlockLocation,
  snowy: Boolean
) extends SnowableBlock {
  override def withLocation(loc: BlockLocation): Mycelium = copy(location = loc)
  override def withSnowy(snowy: Boolean): Mycelium = copy(snowy = snowy)
}

case class Netherrack(location: BlockLocation) extends Block {
  override def withLocation(loc: BlockLocation): Netherrack = copy(location = loc)
}

case class NetherWarts(
  location: BlockLocation,
  state: NetherWartState
) extends StatefulBlock[NetherWartState] {
  override val isSolid: Boolean = false
  override def withLocation(loc: BlockLocation): NetherWarts = copy(location = loc)
  override def withState(state: NetherWartState): NetherWarts = copy(state = state)
}

case class NetherWartBlock(location: BlockLocation) extends Block {
  override def withLocation(loc: BlockLocation): NetherWartBlock = copy(location = loc)
}

case class Observer(
  location: BlockLocation,
  facing: BlockFace,
  powered: Boolean
) extends DirectionalBlock with PowerableBlock {
  override def withLocation(loc: BlockLocation): Observer = copy(location = loc)
  override def withFacing(facing: BlockFace): Observer = copy(facing = facing)
  override def withPowered(powered: Boolean): Observer = copy(powered = powered)
}

case class Obsidian(location: BlockLocation) extends Block {
  override def withLocation(loc: BlockLocation): Obsidian = copy(location = loc)
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

case class Podzol(
  location: BlockLocation,
  snowy: Boolean
) extends SnowableBlock {
  override def withLocation(loc: BlockLocation): Podzol = copy(location = loc)
  override def withSnowy(snowy: Boolean): Podzol = copy(snowy = snowy)
}

case class Potatoes(
  location: BlockLocation,
  state: PotatoState
) extends StatefulBlock[PotatoState] {
  override val isSolid: Boolean = false
  override def withLocation(loc: BlockLocation): Potatoes = copy(location = loc)
  override def withState(state: PotatoState): Potatoes = copy(state = state)
}

case class PressurePlate(
  location: BlockLocation,
  material: PressurePlateMaterial,
  active: Boolean
) extends MaterialBlock[PressurePlateMaterial] with ActivatableBlock {
  override def withLocation(loc: BlockLocation): PressurePlate = copy(location = loc)
  override def withMaterial(material: PressurePlateMaterial): PressurePlate = copy(material = material)
}

case class Prismarine(
  location: BlockLocation,
  material: PrismarineMaterial
) extends MaterialBlock[PrismarineMaterial] {
  override def withLocation(loc: BlockLocation): Prismarine = copy(location = loc)
  override def withMaterial(mat: PrismarineMaterial): Prismarine = copy(material = mat)
}

case class Pumpkin(
  location: BlockLocation,
  facing: Option[BlockFace],
  carved: Boolean
) extends DirectableBlock {
  override def withLocation(loc: BlockLocation): Pumpkin = copy(location = loc)
  override def withFacing(facing: Option[BlockFace]): Pumpkin = copy(facing = facing)
  def withCarved(carved: Boolean): Pumpkin = copy(carved = carved)
}

case class PumpkinStem(
  location: BlockLocation,
  state: PumpkinStemState,
  facing: Option[BlockFace]
) extends StatefulBlock[PumpkinStemState] with DirectableBlock {
  override def withLocation(loc: BlockLocation): PumpkinStem = copy(location = loc)
  override def withState(state: PumpkinStemState): PumpkinStem = copy(state = state)
  override def withFacing(facing: Option[BlockFace]): PumpkinStem = copy(facing = facing)
}

case class Purpur(location: BlockLocation) extends Block {
  override def withLocation(loc: BlockLocation): Purpur = copy(location = loc)
}

case class Quartz(
  location: BlockLocation,
  material: QuartzMaterial
) extends MaterialBlock[QuartzMaterial] {
  override def withLocation(loc: BlockLocation): Quartz = copy(location = loc)
  override def withMaterial(mat: QuartzMaterial): Quartz = copy(material = mat)
}

case class Rails(
  location: BlockLocation,
  material: RailsMaterial,
  state: RailsState,
  powered: Boolean
) extends MaterialBlock[RailsMaterial] with StatefulBlock[RailsState] with PowerableBlock {
  override def withLocation(loc: BlockLocation): Rails = copy(location = loc)
  override def withMaterial(mat: RailsMaterial): Rails = copy(material = mat)
  override def withState(state: RailsState): Rails = copy(state = state)
  override def withPowered(powered: Boolean): Rails = copy(powered = powered)
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

// TODO add growth stage state
case class Sapling(
  location: BlockLocation,
  material: SaplingMaterial,
  state: SaplingState
) extends MaterialBlock[SaplingMaterial] with StatefulBlock[SaplingState] {
  override def withLocation(loc: BlockLocation): Sapling = copy(location = loc)
  override def withMaterial(material: SaplingMaterial): Sapling = copy(material = material)
  override def withState(state: SaplingState): Sapling = copy(state = state)
}

case class Scaffold(location: BlockLocation) extends Block {
  override def withLocation(loc: BlockLocation): Scaffold = copy(location = loc)
}

case class Seagrass(
  location: BlockLocation,
  section: BlockBisection,
  tall: Boolean
) extends BisectedBlock {
  override def withLocation(loc: BlockLocation): Seagrass = copy(location = loc)
  override def withSection(section: BlockBisection): Seagrass = copy(section = section)
}

case class SeaLantern(location: BlockLocation) extends Block {
  override def withLocation(loc: BlockLocation): SeaLantern = copy(location = loc)
}

case class SeaPickle(
  location: BlockLocation,
  state: SeaPickleState,
  flooded: Boolean
) extends StatefulBlock[SeaPickleState] with FloodableBlock {
  override def withLocation(loc: BlockLocation): SeaPickle = copy(location = loc)
  override def withState(state: SeaPickleState): SeaPickle = copy(state = state)
  override def withFlooded(flooded: Boolean): SeaPickle = copy(flooded = flooded)
}

case class ShulkerBox(
  location: BlockLocation,
  color: Option[BlockColor]
) extends ColorableBlock {
  override def withLocation(loc: BlockLocation): ShulkerBox = copy(location = loc)
  override def withColor(color: Option[BlockColor]): ShulkerBox = copy(color = color)
}

case class Sign(
  location: BlockLocation,
  facing: Option[BlockFace],
  rotation: Option[BlockRotation],
  flooded: Boolean
) extends DirectableBlock with RotatableBlock with FloodableBlock {
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
  override def withLocation(loc: BlockLocation): Slab = copy(location = loc)
  override def withMaterial(mat: SlabMaterial): Slab = copy(material = mat)
  override def withSection(section: BlockBisection): Slab = copy(section = section)
}

case class Slime(location: BlockLocation) extends Block {
  override def withLocation(loc: BlockLocation): Slime = copy(location = loc)
}

case class SmithingTable(location: BlockLocation) extends Block {
  override def withLocation(loc: BlockLocation): SmithingTable = copy(location = loc)
}

case class Smoker(
  location: BlockLocation,
  facing: BlockFace,
  lit: Boolean
) extends DirectionalBlock with LightableBlock {
  override def withLocation(loc: BlockLocation): Smoker = copy(location = loc)
  override def withFacing(facing: BlockFace): Smoker = copy(facing = facing)
  override def withLit(lit: Boolean): Smoker = copy(lit = lit)
}

case class Snow(location: BlockLocation) extends Block {
  override def withLocation(loc: BlockLocation): Snow = copy(location = loc)
}

// TODO add size of layer
case class SnowLayer(location: BlockLocation) extends Block {
  override def withLocation(loc: BlockLocation): SnowLayer = copy(location = loc)
}

case class Spawner(location: BlockLocation) extends Block {
  override def withLocation(loc: BlockLocation): Spawner = copy(location = loc)
}

case class Sponge(
  location: BlockLocation,
  wet: Boolean
) extends Block {
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
  override def withLocation(loc: BlockLocation): Stone = copy(location = loc)
  override def withMaterial(mat: StoneMaterial): Stone = copy(material = mat)
}

case class StoneCutter(
  location: BlockLocation,
  facing: BlockFace
) extends DirectionalBlock {
  override def withLocation(loc: BlockLocation): StoneCutter = copy(location = loc)
  override def withFacing(facing: BlockFace): StoneCutter = copy(facing = facing)
}

case class StructureBlock(
  location: BlockLocation,
  state: StructureBlockState
) extends StatefulBlock[StructureBlockState] {
  override def withLocation(loc: BlockLocation): StructureBlock = copy(location = loc)
  override def withState(state: StructureBlockState): StructureBlock = copy(state = state)
}

case class StructureVoid(location: BlockLocation) extends Block {
  override def withLocation(loc: BlockLocation): StructureVoid = copy(location = loc)
}

case class SugarCane(
  location: BlockLocation,
  state: SugarCaneState
) extends StatefulBlock[SugarCaneState] {
  override def withLocation(loc: BlockLocation): SugarCane = copy(state = state)
  override def withState(state: SugarCaneState): SugarCane = copy(state = state)
}

case class SweetBerryBush(
  location: BlockLocation,
  state: SweetBerryState
) extends StatefulBlock[SweetBerryState] {
  override val isSolid: Boolean = false
  override def withLocation(loc: BlockLocation): SweetBerryBush = copy(location = loc)
  override def withState(state: SweetBerryState): SweetBerryBush = copy(state = state)
}

case class Terracotta(
  location: BlockLocation,
  color: Option[BlockColor]
) extends ColorableBlock {
  override def withLocation(loc: BlockLocation): Terracotta = copy(location = loc)
  override def withColor(color: Option[BlockColor]): Terracotta = copy(color = color)
}

case class TNT(
  location: BlockLocation,
  unstable: Boolean
) extends Block {
  override def withLocation(loc: BlockLocation): TNT = copy(location = loc)
  def withUnstable(unstable: Boolean): TNT = copy(unstable = unstable)
}

case class Torch(
  location: BlockLocation,
  facing: BlockFace,
  wall: Boolean
) extends DirectionalBlock {
  override def withLocation(loc: BlockLocation): Torch = copy(location = loc)
  override def withFacing(facing: BlockFace): Torch = copy(facing = facing)
  def withWall(wall: Boolean): Torch = copy(wall = wall)
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

// TODO add direction vals
case class Wall(
  location: BlockLocation,
  material: WallMaterial
) extends MaterialBlock[WallMaterial] {
  override def withLocation(loc: BlockLocation): Wall = copy(location = loc)
  override def withMaterial(mat: WallMaterial): Wall = copy(material = mat)
}

case class Water(
  location: BlockLocation
) extends Block {
  override val isLiquid: Boolean = true
  override def withLocation(loc: BlockLocation): Water = copy(location = loc)
}

case class WeightedPressurePlate(
  location: BlockLocation,
  material: WeightedPressurePlateMaterial,
  state: WeightedPressurePlateState
) extends MaterialBlock[WeightedPressurePlateMaterial] with StatefulBlock[WeightedPressurePlateState] {
  override def withLocation(loc: BlockLocation): WeightedPressurePlate = copy(location = loc)
  override def withMaterial(material: WeightedPressurePlateMaterial): WeightedPressurePlate = copy(material = material)
  override def withState(state: WeightedPressurePlateState): WeightedPressurePlate = copy(state = state)
}

case class Wheat(
  location: BlockLocation,
  state: WheatState
) extends StatefulBlock[WheatState] {
  override def withLocation(loc: BlockLocation): Wheat = copy(location = loc)
  override def withState(state: WheatState): Wheat = copy(state = state)
}

case class Wood(
  location: BlockLocation,
  material: WoodMaterial,
  stripped: Boolean
) extends MaterialBlock[WoodMaterial] {
  override def withLocation(loc: BlockLocation): Wood = copy(location = loc)
  override def withMaterial(mat: WoodMaterial): Wood = copy(material = mat)
  def withStripped(stripped: Boolean): Wood = copy(stripped = stripped)
}

case class Wool(
  location: BlockLocation,
  color: BlockColor
) extends ColoredBlock {
  override def withLocation(loc: BlockLocation): Wool = copy(location = loc)
  override def withColor(color: BlockColor): Wool = copy(color = color)
}
