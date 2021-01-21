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

package gg.warcraft.monolith.api.block

import gg.warcraft.monolith.api.block.shape.{
  JigsawBlockShape, RailsShape, StairsShape
}
import gg.warcraft.monolith.api.block.state._
import gg.warcraft.monolith.api.block.variant._
import gg.warcraft.monolith.api.world.BlockLocation

import java.util
import scala.annotation.varargs
import scala.jdk.CollectionConverters._

final case class Air(
    location: BlockLocation,
    variant: AirVariant
) extends VariableBlock[AirVariant] {
  override val `type` = BlockType.AIR
  override val solid: Boolean = false
}

final case class AncientDebris(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.ANCIENT_DEBRIS
}

final case class Andesite(
    location: BlockLocation,
    variant: AndesiteVariant
) extends VariableBlock[AndesiteVariant] {
  override val `type` = BlockType.ANDESITE
}

final case class Anvil(
    location: BlockLocation,
    variant: AnvilVariant,
    direction: BlockFace
) extends VariableBlock[AnvilVariant]
    with DirectedBlock {
  override val `type` = BlockType.ANVIL
}

final case class Bamboo(
    location: BlockLocation,
    variant: BambooVariant,
    state: BambooState,
    thick: Boolean
) extends VariableBlock[BambooVariant]
    with StatefulBlock[BambooState] {
  override val `type` = BlockType.BAMBOO
  def withThick(thick: Boolean): Bamboo =
    copyWith("thick", thick)
}

final case class Banner(
    location: BlockLocation,
    variant: BannerVariant,
    rotation: Option[BlockRotation],
    direction: Option[BlockFace]
) extends VariableBlock[BannerVariant]
    with RotatableBlock
    with DirectableBlock {
  override val `type` = BlockType.BANNER
}

final case class Barrel(
    location: BlockLocation,
    direction: BlockFace,
    open: Boolean
) extends DirectedBlock
    with OpenableBlock {
  override val `type` = BlockType.BARREL
}

final case class Barrier(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.BARRIER
}

final case class Basalt(
    location: BlockLocation,
    variant: BasaltVariant,
    orientation: BlockOrientation
) extends VariableBlock[BasaltVariant]
    with OrientedBlock {
  override val `type` = BlockType.BASALT
}

final case class Beacon(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.BEACON
}

// TODO add occupied flag
final case class Bed(
    location: BlockLocation,
    variant: BedVariant,
    direction: BlockFace,
    section: BlockBisection
) extends VariableBlock[BedVariant]
    with DirectedBlock
    with BisectedBlock {
  override val `type` = BlockType.BED
}

final case class Bedrock(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.BEDROCK
}

final case class BeeNest(
    location: BlockLocation,
    state: BeeNestState,
    direction: BlockFace
) extends StatefulBlock[BeeNestState]
    with DirectedBlock {
  override val `type` = BlockType.BEE_NEST
}

final case class Beehive(
    location: BlockLocation,
    state: BeehiveState,
    direction: BlockFace
) extends StatefulBlock[BeehiveState]
    with DirectedBlock {
  override val `type` = BlockType.BEEHIVE
}

final case class Beetroots(
    location: BlockLocation,
    state: BeetrootState
) extends StatefulBlock[BeetrootState] {
  override val `type` = BlockType.BEETROOTS
  override val solid: Boolean = false
}

// TODO add attachment val (NOTE this one is different for Bells from attachedTo)
final case class Bell(
    location: BlockLocation,
    direction: BlockFace
) extends DirectedBlock {
  override val `type` = BlockType.BELL
}

final case class Blackstone(
    location: BlockLocation,
    variant: BlackstoneVariant
) extends VariableBlock[BlackstoneVariant] {
  override val `type` = BlockType.BLACKSTONE
}

final case class BlastFurnace(
    location: BlockLocation,
    direction: BlockFace,
    lit: Boolean
) extends DirectedBlock
    with LightableBlock {
  override val `type` = BlockType.BLAST_FURNACE
}

final case class BoneBlock(
    location: BlockLocation,
    orientation: BlockOrientation
) extends OrientedBlock {
  override val `type` = BlockType.BONE_BLOCK
}

final case class Bookshelf(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.BOOKSHELF
}

// TODO add val which denotes which vials are filled
final case class BrewingStand(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.BREWING_STAND
}

final case class BrickBlock(
    location: BlockLocation,
    variant: BrickBlockVariant
) extends VariableBlock[BrickBlockVariant] {
  override val `type` = BlockType.BRICK_BLOCK
}

final case class BubbleColumn(
    location: BlockLocation,
    drag: Boolean
) extends Block {
  override val `type` = BlockType.BUBBLE_COLUMN
  override val solid: Boolean = false
  override val liquid: Boolean = true
  def withDrag(drag: Boolean): BubbleColumn =
    copyWith("drag", drag)
}

final case class Button(
    location: BlockLocation,
    variant: ButtonVariant,
    direction: BlockFace,
    attachment: BlockAttachment,
    powered: Boolean
) extends VariableBlock[ButtonVariant]
    with DirectedBlock
    with AttachedBlock
    with PowerableBlock {
  override val `type` = BlockType.BUTTON
}

final case class Cactus(
    location: BlockLocation,
    state: CactusState
) extends StatefulBlock[CactusState] {
  override val `type` = BlockType.CACTUS
}

final case class Cake(
    location: BlockLocation,
    state: CakeState
) extends StatefulBlock[CakeState] {
  override val `type` = BlockType.CAKE
}

final case class Campfire(
    location: BlockLocation,
    variant: CampfireVariant,
    direction: BlockFace,
    flooded: Boolean,
    lit: Boolean,
    signal: Boolean
) extends VariableBlock[CampfireVariant]
    with DirectedBlock
    with FloodableBlock
    with LightableBlock {
  override val `type` = BlockType.CAMPFIRE
  def withSignal(signal: Boolean): Campfire =
    copyWith("signal", signal)
}

final case class Carpet(
    location: BlockLocation,
    variant: CarpetVariant
) extends VariableBlock[CarpetVariant] {
  override val `type` = BlockType.CARPET
}

final case class Carrots(
    location: BlockLocation,
    state: CarrotState
) extends StatefulBlock[CarrotState] {
  override val `type` = BlockType.CARROTS
  override val solid: Boolean = false
}

final case class CartographyTable(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.CARTOGRAPHY_TABLE
}

final case class Cauldron(
    location: BlockLocation,
    state: CauldronState
) extends StatefulBlock[CauldronState] {
  override val `type` = BlockType.CAULDRON
}

final case class Chain(
    location: BlockLocation,
    orientation: BlockOrientation,
    flooded: Boolean
) extends OrientedBlock
    with FloodableBlock {
  override val `type` = BlockType.CHAIN
}

// TODO add DOUBLE_LEFT/DOUBLE_RIGHT/SINGLE
final case class Chest(
    location: BlockLocation,
    variant: ChestVariant,
    direction: BlockFace,
    flooded: Boolean
) extends VariableBlock[ChestVariant]
    with DirectedBlock
    with FloodableBlock {
  override val `type` = BlockType.CHEST
}

final case class ChorusFlower(
    location: BlockLocation,
    state: ChorusFlowerState
) extends StatefulBlock[ChorusFlowerState] {
  override val `type` = BlockType.CHORUS_FLOWER
}

final case class ChorusPlant(
    location: BlockLocation,
    extensions: Set[BlockFace]
) extends ExtendableBlock {
  override val `type` = BlockType.CHORUS_PLANT
}

final case class ClayBlock(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.CLAY_BLOCK
}

final case class CoalBlock(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.COAL_BLOCK
}

final case class CoalOre(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.COAL_ORE
}

final case class Cobblestone(
    location: BlockLocation,
    variant: CobblestoneVariant
) extends VariableBlock[CobblestoneVariant] {
  override val `type` = BlockType.COBBLESTONE
}

final case class Cobweb(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.COBWEB
}

final case class CocoaPod(
    location: BlockLocation,
    state: CocoaState,
    direction: BlockFace
) extends StatefulBlock[CocoaState]
    with DirectedBlock {
  override val `type` = BlockType.COCOA_POD
  override val solid: Boolean = false
}

final case class CommandBlock(
    location: BlockLocation,
    variant: CommandBlockVariant,
    direction: BlockFace,
    conditional: Boolean
) extends VariableBlock[CommandBlockVariant]
    with DirectedBlock {
  override val `type` = BlockType.COMMAND_BLOCK
  def withConditional(conditional: Boolean): CommandBlock =
    copyWith("conditional", conditional)
}

final case class Comparator(
    location: BlockLocation,
    variant: ComparatorVariant,
    direction: BlockFace,
    powered: Boolean
) extends VariableBlock[ComparatorVariant]
    with DirectedBlock
    with PowerableBlock {
  override val `type` = BlockType.COMPARATOR
}

final case class Composter(
    location: BlockLocation,
    state: ComposterState
) extends StatefulBlock[ComposterState] {
  override val `type` = BlockType.COMPOSTER
}

final case class Concrete(
    location: BlockLocation,
    variant: ConcreteVariant
) extends VariableBlock[ConcreteVariant] {
  override val `type` = BlockType.CONCRETE
}

final case class ConcretePowder(
    location: BlockLocation,
    variant: ConcretePowderVariant
) extends VariableBlock[ConcretePowderVariant] {
  override val `type` = BlockType.CONCRETE_POWDER
}

final case class Conduit(
    location: BlockLocation,
    flooded: Boolean
) extends FloodableBlock {
  override val `type` = BlockType.CONDUIT
}

final case class Coral(
    location: BlockLocation,
    variant: CoralVariant,
    flooded: Boolean
) extends VariableBlock[CoralVariant]
    with FloodableBlock {
  override val `type` = BlockType.CORAL
  override val solid: Boolean = false
}

final case class CoralBlock(
    location: BlockLocation,
    variant: CoralBlockVariant
) extends VariableBlock[CoralBlockVariant] {
  override val `type` = BlockType.CORAL_BLOCK
}

final case class CoralFan(
    location: BlockLocation,
    variant: CoralFanVariant,
    direction: Option[BlockFace],
    flooded: Boolean
) extends VariableBlock[CoralFanVariant]
    with DirectableBlock
    with FloodableBlock {
  override val `type` = BlockType.CORAL_FAN
  override val solid: Boolean = false
}

final case class CraftingTable(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.CRAFTING_TABLE
}

final case class CryingObsidian(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.CRYING_OBSIDIAN
}

final case class DaylightDetector(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.DAYLIGHT_DETECTOR
}

final case class DeadBush(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.DEAD_BUSH
  override val solid: Boolean = false
}

final case class DiamondBlock(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.DIAMOND_BLOCK
}

final case class DiamondOre(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.DIAMOND_ORE
}

final case class Diorite(
    location: BlockLocation,
    variant: DioriteVariant
) extends VariableBlock[DioriteVariant] {
  override val `type` = BlockType.DIORITE
}

final case class Dirt(
    location: BlockLocation,
    variant: DirtVariant
) extends VariableBlock[DirtVariant] {
  override val `type` = BlockType.DIRT
}

final case class Dispenser(
    location: BlockLocation,
    direction: BlockFace,
    powered: Boolean
) extends DirectedBlock
    with PowerableBlock {
  override val `type` = BlockType.DISPENSER
}

final case class Door(
    location: BlockLocation,
    variant: DoorVariant,
    direction: BlockFace,
    hinge: BlockHinge,
    section: BlockBisection,
    open: Boolean, // TODO should this be isOpen?
    powered: Boolean
) extends VariableBlock[DoorVariant]
    with DirectedBlock
    with HingedBlock
    with BisectedBlock
    with OpenableBlock
    with PowerableBlock {
  override val `type` = BlockType.DOOR
}

final case class DragonEgg(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.DRAGON_EGG
}

final case class DriedKelpBlock(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.DRIED_KELP_BLOCK
}

final case class Dropper(
    location: BlockLocation,
    direction: BlockFace,
    powered: Boolean
) extends DirectedBlock
    with PowerableBlock {
  override val `type` = BlockType.DROPPER
}

final case class EmeraldBlock(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.EMERALD_BLOCK
}

final case class EmeraldOre(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.EMERALD_ORE
}

final case class EnchantingTable(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.ENCHANTING_TABLE
}

final case class EndGateway(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.END_GATEWAY
}

final case class EndPortal(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.END_PORTAL
}

final case class EndPortalFrame(
    location: BlockLocation,
    direction: BlockFace,
    eye: Boolean
) extends DirectedBlock {
  override val `type` = BlockType.END_PORTAL_FRAME
  def withEye(eye: Boolean): EndPortalFrame =
    copyWith("eye", eye)
}

final case class EndRod(
    location: BlockLocation,
    direction: BlockFace
) extends DirectedBlock {
  override val `type` = BlockType.END_ROD
}

final case class EndStone(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.END_STONE
}

final case class EndStoneBrick(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.END_STONE_BRICK
}

// TODO add wetness state
final case class Farmland(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.FARMLAND
}

final case class Fence(
    location: BlockLocation,
    variant: FenceVariant,
    extensions: Set[BlockFace],
    flooded: Boolean
) extends VariableBlock[FenceVariant]
    with ExtendableBlock
    with FloodableBlock {
  override val `type` = BlockType.FENCE
}

final case class Fern(
    location: BlockLocation,
    variant: FernVariant,
    section: BlockBisection
) extends VariableBlock[FernVariant]
    with BisectedBlock {
  override val `type` = BlockType.FERN
  override val solid: Boolean = false
}

final case class Fire(
    location: BlockLocation,
    variant: FireVariant,
    state: FireState,
    extensions: Set[BlockFace]
) extends VariableBlock[FireVariant]
    with StatefulBlock[FireState]
    with ExtendableBlock {
  override val `type` = BlockType.FIRE
  override val solid: Boolean = false
}

final case class FletchingTable(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.FLETCHING_TABLE
}

final case class Flower(
    location: BlockLocation,
    variant: FlowerVariant
) extends VariableBlock[FlowerVariant] {
  override val `type` = BlockType.FLOWER
  override val solid: Boolean = false
}

final case class FlowerPot(
    location: BlockLocation,
    variant: FlowerPotVariant
) extends VariableBlock[FlowerPotVariant] {
  override val `type` = BlockType.FLOWER_POT
}

final case class Frost(
    location: BlockLocation,
    state: FrostState
) extends StatefulBlock[FrostState] {
  override val `type` = BlockType.FROST
}

final case class Furnace(
    location: BlockLocation,
    direction: BlockFace,
    lit: Boolean
) extends DirectedBlock
    with LightableBlock {
  override val `type` = BlockType.FURNACE
}

final case class FenceGate(
    location: BlockLocation,
    variant: FenceGateVariant,
    direction: BlockFace,
    open: Boolean,
    powered: Boolean,
    wall: Boolean
) extends VariableBlock[FenceGateVariant]
    with DirectedBlock
    with OpenableBlock
    with PowerableBlock {
  override val `type` = BlockType.FENCE_GATE
  def withWall(wall: Boolean): FenceGate =
    copyWith("wall", wall)
}

final case class Fungus(
    location: BlockLocation,
    variant: FungusVariant
) extends VariableBlock[FungusVariant] {
  override val `type` = BlockType.FUNGUS
}

final case class GildedBlackstone(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.GILDED_BLACKSTONE
}

final case class Glass(
    location: BlockLocation,
    variant: GlassVariant
) extends VariableBlock[GlassVariant] {
  override val `type` = BlockType.GLASS
}

final case class GlassPane(
    location: BlockLocation,
    variant: GlassPaneVariant,
    extensions: Set[BlockFace],
    flooded: Boolean
) extends VariableBlock[GlassPaneVariant]
    with ExtendableBlock
    with FloodableBlock {
  override val `type` = BlockType.GLASS_PANE
}

final case class GlazedTerracotta(
    location: BlockLocation,
    variant: GlazedTerracottaVariant,
    direction: BlockFace
) extends VariableBlock[GlazedTerracottaVariant]
    with DirectedBlock {
  override val `type` = BlockType.GLAZED_TERRACOTTA
}

final case class Glowstone(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.GLOWSTONE
}

final case class GoldBlock(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.GOLD_BLOCK
}

final case class GoldOre(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.GOLD_ORE
}

final case class Granite(
    location: BlockLocation,
    variant: GraniteVariant
) extends VariableBlock[GraniteVariant] {
  override val `type` = BlockType.GRANITE
}

final case class Grass(
    location: BlockLocation,
    variant: GrassVariant,
    section: BlockBisection
) extends VariableBlock[GrassVariant]
    with BisectedBlock {
  override val `type` = BlockType.GRASS
  override val solid: Boolean = false
}

final case class GrassBlock(
    location: BlockLocation,
    snowy: Boolean
) extends SnowableBlock {
  override val `type` = BlockType.GRASS_BLOCK
}

final case class GrassPath(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.GRASS_PATH
}

final case class Gravel(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.GRAVEL
}

final case class Grindstone(
    location: BlockLocation,
    direction: BlockFace,
    attachment: BlockAttachment
) extends DirectedBlock
    with AttachedBlock {
  override val `type` = BlockType.GRINDSTONE
}

final case class HayBale(
    location: BlockLocation,
    orientation: BlockOrientation
) extends OrientedBlock {
  override val `type` = BlockType.HAY_BALE
}

final case class HoneyBlock(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.HONEY_BLOCK
}

final case class HoneycombBlock(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.HONEYCOMB_BLOCK
}

final case class Hopper(
    location: BlockLocation,
    direction: BlockFace,
    powered: Boolean
) extends DirectedBlock
    with PowerableBlock {
  override val `type` = BlockType.HOPPER
}

final case class Ice(
    location: BlockLocation,
    variant: IceVariant
) extends VariableBlock[IceVariant] {
  override val `type` = BlockType.ICE
}

final case class InfestedBlock(
    location: BlockLocation,
    variant: InfestedBlockVariant
) extends VariableBlock[InfestedBlockVariant] {
  override val `type` = BlockType.INFESTED_BLOCK
}

final case class IronBlock(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.IRON_BLOCK
}

final case class IronBars(
    location: BlockLocation,
    extensions: Set[BlockFace],
    flooded: Boolean
) extends ExtendableBlock
    with FloodableBlock {
  override val `type` = BlockType.IRON_BARS
}

final case class IronOre(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.IRON_ORE
}

final case class JigsawBlock(
    location: BlockLocation,
    shape: JigsawBlockShape
) extends ShapedBlock[JigsawBlockShape] {
  override val `type` = BlockType.JIGSAW_BLOCK
}

final case class Jukebox(
    location: BlockLocation,
    record: Boolean
) extends Block {
  override val `type` = BlockType.JUKEBOX
  def withRecord(record: Boolean): Jukebox =
    copyWith("record", record)
}

final case class Kelp(
    location: BlockLocation,
    state: KelpState
) extends StatefulBlock[KelpState] {
  override val `type` = BlockType.KELP
  override val solid: Boolean = false
  override val liquid: Boolean = true
}

final case class Ladder(
    location: BlockLocation,
    direction: BlockFace,
    flooded: Boolean
) extends DirectedBlock
    with FloodableBlock {
  override val `type` = BlockType.LADDER
}

final case class Lantern(
    location: BlockLocation,
    variant: LanternVariant,
    flooded: Boolean,
    hanging: Boolean
) extends VariableBlock[LanternVariant]
    with FloodableBlock {
  override val `type` = BlockType.LANTERN
  def withHanging(hanging: Boolean): Lantern =
    copyWith("hanging", hanging)
}

final case class LapisBlock(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.LAPIS_BLOCK
}

final case class LapisOre(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.LAPIS_ORE
}

// TODO add falling: Boolean
final case class Lava(
    location: BlockLocation,
    state: LavaState
) extends StatefulBlock[LavaState] {
  override val `type` = BlockType.LAVA
  override val liquid: Boolean = true
}

final case class Leaves(
    location: BlockLocation,
    variant: LeavesVariant
) extends VariableBlock[LeavesVariant] {
  override val `type` = BlockType.LEAVES
  override val solid: Boolean = false
}

// NOTE book is read-only in Spigot
final case class Lectern(
    location: BlockLocation,
    direction: BlockFace,
    powered: Boolean,
    book: Boolean
) extends DirectedBlock
    with PowerableBlock {
  override val `type` = BlockType.LECTERN
}

final case class Lever(
    location: BlockLocation,
    direction: BlockFace,
    attachment: BlockAttachment,
    powered: Boolean
) extends DirectedBlock
    with AttachedBlock
    with PowerableBlock {
  override val `type` = BlockType.LEVER
}

final case class LilyPad(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.LILY_PAD
  override val solid: Boolean = false
}

final case class Lodestone(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.LODESTONE
}

final case class Log(
    location: BlockLocation,
    variant: LogVariant,
    orientation: BlockOrientation
) extends VariableBlock[LogVariant]
    with OrientedBlock {
  override val `type` = BlockType.LOG
}

final case class Loom(
    location: BlockLocation,
    direction: BlockFace
) extends DirectedBlock {
  override val `type` = BlockType.LOOM
}

final case class MagmaBlock(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.MAGMA_BLOCK
}

final case class Melon(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.MELON
}

final case class MelonStem(
    location: BlockLocation,
    state: MelonStemState,
    direction: Option[BlockFace]
) extends StatefulBlock[MelonStemState]
    with DirectableBlock {
  override val `type` = BlockType.MELON_STEM
}

final case class MobHead(
    location: BlockLocation,
    variant: MobHeadVariant,
    direction: Option[BlockFace],
    rotation: Option[BlockRotation]
) extends VariableBlock[MobHeadVariant]
    with DirectableBlock
    with RotatableBlock {
  override val `type` = BlockType.MOB_HEAD
}

final case class Mushroom(
    location: BlockLocation,
    variant: MushroomVariant
) extends VariableBlock[MushroomVariant] {
  override val `type` = BlockType.MUSHROOM
  override val solid: Boolean = false
}

final case class MushroomBlock(
    location: BlockLocation,
    variant: MushroomBlockVariant,
    extensions: Set[BlockFace]
) extends VariableBlock[MushroomBlockVariant]
    with ExtendableBlock {
  override val `type` = BlockType.MUSHROOM_BLOCK
}

final case class Mycelium(
    location: BlockLocation,
    snowy: Boolean
) extends SnowableBlock {
  override val `type` = BlockType.MYCELIUM
}

final case class NetherPortal(
    location: BlockLocation,
    orientation: BlockOrientation
) extends OrientedBlock {
  override val `type` = BlockType.NETHER_PORTAL
}

final case class Netherrack(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.NETHERRACK
}

final case class NetherGoldOre(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.NETHER_GOLD_ORE
}

final case class NetherSprouts(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.NETHER_SPROUTS
  override val solid = false
}

final case class NetherVines(
    location: BlockLocation,
    variant: NetherVinesVariant,
    state: NetherVinesState
) extends VariableBlock[NetherVinesVariant]
    with StatefulBlock[NetherVinesState] {
  override val `type` = BlockType.NETHER_VINES
}

final case class NetherWartBlock(
    location: BlockLocation,
    variant: NetherWartBlockVariant
) extends VariableBlock[NetherWartBlockVariant] {
  override val `type` = BlockType.NETHER_WART_BLOCK
}

final case class NetherWarts(
    location: BlockLocation,
    state: NetherWartState
) extends StatefulBlock[NetherWartState] {
  override val `type` = BlockType.NETHER_WARTS
  override val solid: Boolean = false
}

final case class NetheriteBlock(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.NETHERITE_BLOCK
}

final case class NoteBlock(
    location: BlockLocation,
    variant: NoteBlockVariant,
    state: NoteBlockState,
    powered: Boolean
) extends VariableBlock[NoteBlockVariant]
    with StatefulBlock[NoteBlockState]
    with PowerableBlock {
  override val `type` = BlockType.NOTE_BLOCK
}

final case class Nylium(
    location: BlockLocation,
    variant: NyliumVariant
) extends VariableBlock[NyliumVariant] {
  override val `type` = BlockType.NYLIUM
}

final case class Observer(
    location: BlockLocation,
    direction: BlockFace,
    powered: Boolean
) extends DirectedBlock
    with PowerableBlock {
  override val `type` = BlockType.OBSERVER
}

final case class Obsidian(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.OBSIDIAN
}

final case class Pillar(
    location: BlockLocation,
    variant: PillarVariant,
    orientation: BlockOrientation
) extends VariableBlock[PillarVariant]
    with OrientedBlock {
  override val `type` = BlockType.PILLAR
}

final case class Piston(
    location: BlockLocation,
    variant: PistonVariant,
    direction: BlockFace,
    extended: Boolean
) extends VariableBlock[PistonVariant]
    with DirectedBlock {
  override val `type` = BlockType.PISTON
  def withExtended(extended: Boolean): Piston =
    copyWith("extended", extended)
}

final case class PistonHead(
    location: BlockLocation,
    variant: PistonHeadVariant,
    direction: BlockFace,
    short: Boolean
) extends VariableBlock[PistonHeadVariant]
    with DirectedBlock {
  override val `type` = BlockType.PISTON
  def withShort(short: Boolean): PistonHead =
    copyWith("short", short)
}

final case class Planks(
    location: BlockLocation,
    variant: PlanksVariant
) extends VariableBlock[PlanksVariant] {
  override val `type` = BlockType.PLANKS
}

final case class Plant(
    location: BlockLocation,
    variant: PlantVariant,
    section: BlockBisection
) extends VariableBlock[PlantVariant]
    with BisectedBlock {
  override val `type` = BlockType.PLANT
}

final case class Podzol(
    location: BlockLocation,
    snowy: Boolean
) extends SnowableBlock {
  override val `type` = BlockType.PODZOL
}

final case class Potatoes(
    location: BlockLocation,
    state: PotatoState
) extends StatefulBlock[PotatoState] {
  override val `type` = BlockType.POTATOES
  override val solid: Boolean = false
}

final case class PressurePlate(
    location: BlockLocation,
    variant: PressurePlateVariant,
    powered: Boolean
) extends VariableBlock[PressurePlateVariant]
    with PowerableBlock {
  override val `type` = BlockType.PRESSURE_PLATE
}

final case class Prismarine(
    location: BlockLocation,
    variant: PrismarineVariant
) extends VariableBlock[PrismarineVariant] {
  override val `type` = BlockType.PRISMARINE
}

final case class Pumpkin(
    location: BlockLocation,
    direction: Option[BlockFace],
    lit: Boolean,
    carved: Boolean
) extends DirectableBlock
    with LightableBlock {
  override val `type` = BlockType.PUMPKIN
  def withCarved(carved: Boolean): Pumpkin =
    copyWith("carved", carved)
}

final case class PumpkinStem(
    location: BlockLocation,
    state: PumpkinStemState,
    direction: Option[BlockFace]
) extends StatefulBlock[PumpkinStemState]
    with DirectableBlock {
  override val `type` = BlockType.PUMPKIN_STEM
}

final case class PurpurBlock(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.PURPUR_BLOCK
}

final case class QuartzBlock(
    location: BlockLocation,
    variant: QuartzBlockVariant
) extends VariableBlock[QuartzBlockVariant] {
  override val `type` = BlockType.QUARTZ_BLOCK
}

final case class QuartzOre(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.QUARTZ_ORE
}

final case class Rail(
    location: BlockLocation,
    variant: RailVariant,
    shape: RailsShape,
    powered: Boolean
) extends VariableBlock[RailVariant]
    with ShapedBlock[RailsShape]
    with PowerableBlock {
  override val `type` = BlockType.RAIL
  override val solid: Boolean = false
}

final case class RedstoneBlock(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.REDSTONE_BLOCK
}

final case class RedstoneLamp(
    location: BlockLocation,
    lit: Boolean
) extends LightableBlock {
  override val `type` = BlockType.REDSTONE_LAMP
}

final case class RedstoneOre(
    location: BlockLocation,
    lit: Boolean
) extends LightableBlock {
  override val `type` = BlockType.REDSTONE_ORE
}

final case class RedstoneTorch(
    location: BlockLocation,
    direction: Option[BlockFace],
    lit: Boolean
) extends DirectableBlock
    with LightableBlock {
  override val `type` = BlockType.REDSTONE_TORCH
  override val solid: Boolean = false
}

// TODO add NESW connections
final case class RedstoneWire(
    location: BlockLocation,
    state: RedstoneWireState
) extends StatefulBlock[RedstoneWireState] {
  override val `type` = BlockType.REDSTONE_WIRE
  override val solid: Boolean = false
}

final case class Repeater(
    location: BlockLocation,
    state: RepeaterState,
    direction: BlockFace,
    powered: Boolean,
    locked: Boolean
) extends StatefulBlock[RepeaterState]
    with DirectedBlock
    with PowerableBlock {
  override val `type` = BlockType.REPEATER
  def withLocked(locked: Boolean): Repeater =
    copyWith("locked", locked)
}

final case class RespawnAnchor(
    location: BlockLocation,
    state: RespawnAnchorState
) extends StatefulBlock[RespawnAnchorState] {
  override val `type` = BlockType.RESPAWN_ANCHOR
}

final case class Roots(
    location: BlockLocation,
    variant: RootsVariant
) extends VariableBlock[RootsVariant] {
  override val `type` = BlockType.ROOTS
}

final case class Sand(
    location: BlockLocation,
    variant: SandVariant
) extends VariableBlock[SandVariant] {
  override val `type` = BlockType.SAND
}

final case class Sandstone(
    location: BlockLocation,
    variant: SandstoneVariant
) extends VariableBlock[SandstoneVariant] {
  override val `type` = BlockType.SANDSTONE
}

// TODO add growth stage state
final case class Sapling(
    location: BlockLocation,
    variant: SaplingVariant,
    state: SaplingState
) extends VariableBlock[SaplingVariant]
    with StatefulBlock[SaplingState] {
  override val `type` = BlockType.SAPLING
  override val solid: Boolean = false
}

// TODO bottom and distance vals
final case class Scaffolding(
    location: BlockLocation,
    flooded: Boolean
) extends FloodableBlock {
  override val `type` = BlockType.SCAFFOLDING
}

final case class Seagrass(
    location: BlockLocation,
    section: BlockBisection,
    tall: Boolean
) extends BisectedBlock {
  override val `type` = BlockType.SEAGRASS
  override val solid: Boolean = false
  override val liquid: Boolean = true
  def withTall(tall: Boolean): Seagrass =
    copyWith("tall", tall)
}

final case class SeaLantern(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.SEA_LANTERN
}

final case class SeaPickle(
    location: BlockLocation,
    state: SeaPickleState,
    flooded: Boolean
) extends StatefulBlock[SeaPickleState]
    with FloodableBlock {
  override val `type` = BlockType.SEA_PICKLE
  override val solid: Boolean = false
}

final case class Shroomlight(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.SHROOMLIGHT
}

final case class ShulkerBox(
    location: BlockLocation,
    variant: ShulkerBoxVariant,
    direction: BlockFace
) extends VariableBlock[ShulkerBoxVariant]
    with DirectedBlock {
  override val `type` = BlockType.SHULKER_BOX
}

final case class Sign(
    location: BlockLocation,
    variant: SignVariant,
    direction: Option[BlockFace],
    rotation: Option[BlockRotation],
    flooded: Boolean,
    lines: List[String],
    editable: Boolean
) extends VariableBlock[SignVariant]
    with DirectableBlock
    with RotatableBlock
    with FloodableBlock {
  override val `type` = BlockType.SIGN
  def getLines: util.List[String] = lines.asJava
  @varargs def withLines(lines: String*): Sign =
    copyWith("lines", lines.slice(0, 4).toList)
  def withEditable(editable: Boolean): Sign =
    copyWith("editable", editable)
}

final case class Slab(
    location: BlockLocation,
    variant: SlabVariant,
    section: BlockBisection,
    flooded: Boolean
) extends VariableBlock[SlabVariant]
    with BisectedBlock
    with FloodableBlock {
  override val `type` = BlockType.SLAB
}

final case class SlimeBlock(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.SLIME_BLOCK
}

final case class SmithingTable(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.SMITHING_TABLE
}

final case class Smoker(
    location: BlockLocation,
    direction: BlockFace,
    lit: Boolean
) extends DirectedBlock
    with LightableBlock {
  override val `type` = BlockType.SMOKER
}

// TODO add size of layer
final case class Snow(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.SNOW
  override val solid: Boolean = false
}

final case class SnowBlock(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.SNOW_BLOCK
}

final case class SoulSand(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.SOUL_SAND
}

final case class SoulSoil(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.SOUL_SOIL
}

final case class Spawner(
    location: BlockLocation
) extends Block {
  override val `type` = BlockType.SPAWNER
}

final case class Sponge(
    location: BlockLocation,
    variant: SpongeVariant
) extends VariableBlock[SpongeVariant] {
  override val `type` = BlockType.SPONGE
}

final case class Stairs(
    location: BlockLocation,
    variant: StairsVariant,
    shape: StairsShape,
    direction: BlockFace,
    section: BlockBisection,
    flooded: Boolean
) extends VariableBlock[StairsVariant]
    with ShapedBlock[StairsShape]
    with DirectedBlock
    with BisectedBlock
    with FloodableBlock {
  override val `type` = BlockType.STAIRS
}

final case class Stone(
    location: BlockLocation,
    variant: StoneVariant
) extends VariableBlock[StoneVariant] {
  override val `type` = BlockType.STONE
}

final case class StoneBrick(
    location: BlockLocation,
    variant: StoneBrickVariant
) extends VariableBlock[StoneBrickVariant] {
  override val `type` = BlockType.STONE_BRICK
}

final case class Stonecutter(
    location: BlockLocation,
    direction: BlockFace
) extends DirectedBlock {
  override val `type` = BlockType.STONECUTTER
}

final case class StructureBlock(
    location: BlockLocation,
    variant: StructureBlockVariant
) extends VariableBlock[StructureBlockVariant] {
  override val `type` = BlockType.STRUCTURE_BLOCK
}

final case class SugarCane(
    location: BlockLocation,
    state: SugarCaneState
) extends StatefulBlock[SugarCaneState] {
  override val `type` = BlockType.SUGAR_CANE
  override val solid: Boolean = false
}

final case class SweetBerryBush(
    location: BlockLocation,
    state: SweetBerryState
) extends StatefulBlock[SweetBerryState] {
  override val `type` = BlockType.SWEET_BERRY_BUSH
  override val solid: Boolean = false
}

final case class Target(
    location: BlockLocation,
    state: TargetState
) extends StatefulBlock[TargetState] {
  override val `type` = BlockType.TARGET
}

final case class Terracotta(
    location: BlockLocation,
    variant: TerracottaVariant
) extends VariableBlock[TerracottaVariant] {
  override val `type` = BlockType.TERRACOTTA
}

final case class TNT(
    location: BlockLocation,
    unstable: Boolean
) extends Block {
  override val `type` = BlockType.TNT
  def withUnstable(unstable: Boolean): TNT =
    copyWith("unstable", unstable)
}

final case class Torch(
    location: BlockLocation,
    variant: TorchVariant,
    direction: BlockFace,
    wall: Boolean
) extends VariableBlock[TorchVariant]
    with DirectedBlock {
  override val `type` = BlockType.TORCH
  override val solid: Boolean = false
  def withWall(wall: Boolean): Torch =
    copyWith("wall", wall) // TODO this is superseded by direction?
}

final case class Trapdoor(
    location: BlockLocation,
    variant: TrapdoorVariant,
    direction: BlockFace,
    section: BlockBisection,
    powered: Boolean,
    flooded: Boolean,
    open: Boolean
) extends VariableBlock[TrapdoorVariant]
    with DirectedBlock
    with BisectedBlock
    with PowerableBlock
    with FloodableBlock
    with OpenableBlock {
  override val `type` = BlockType.TRAPDOOR
  override val solid: Boolean = false
}

final case class Tripwire(
    location: BlockLocation,
    extensions: Set[BlockFace],
    powered: Boolean,
    connected: Boolean,
    disarmed: Boolean
) extends ExtendableBlock
    with PowerableBlock {
  override val `type` = BlockType.TRIPWIRE
  override val solid: Boolean = false
  def withConnected(connected: Boolean): Tripwire =
    copyWith("connected", connected)
  def withDisarmed(disarmed: Boolean): Tripwire =
    copyWith("disarmed", disarmed)
}

final case class TripwireHook(
    location: BlockLocation,
    direction: BlockFace,
    powered: Boolean,
    connected: Boolean
) extends DirectedBlock
    with PowerableBlock {
  override val `type` = BlockType.TRIPWIRE_HOOK
  override val solid: Boolean = false
  def withConnected(connected: Boolean): TripwireHook =
    copyWith("connected", connected)
}

final case class TurtleEgg(
    location: BlockLocation,
    state: TurtleEggState
) extends StatefulBlock[TurtleEggState] {
  override val `type` = BlockType.TURTLE_EGG
  override val solid: Boolean = false
}

final case class Vines(
    location: BlockLocation,
    extensions: Set[BlockFace]
) extends ExtendableBlock {
  override val `type` = BlockType.VINES
  override val solid: Boolean = false
}

final case class Wall(
    location: BlockLocation,
    variant: WallVariant,
    heights: Map[BlockFace, WallHeight],
    flooded: Boolean
) extends VariableBlock[WallVariant]
    with FloodableBlock {
  override val `type` = BlockType.WALL
}

// TODO add falling: Boolean
final case class Water(
    location: BlockLocation,
    state: WaterState
) extends StatefulBlock[WaterState] {
  override val `type` = BlockType.WATER
  override val liquid: Boolean = true
}

final case class WeightedPressurePlate(
    location: BlockLocation,
    variant: WeightedPressurePlateVariant,
    state: WeightedPressurePlateState
) extends VariableBlock[WeightedPressurePlateVariant]
    with StatefulBlock[WeightedPressurePlateState] {
  override val `type` = BlockType.WEIGHTED_PRESSURE_PLATE
}

final case class Wheat(
    location: BlockLocation,
    state: WheatState
) extends StatefulBlock[WheatState] {
  override val `type` = BlockType.WHEAT
  override val solid: Boolean = false
}

final case class Wood(
    location: BlockLocation,
    variant: WoodVariant,
    orientation: BlockOrientation
) extends VariableBlock[WoodVariant]
    with OrientedBlock {
  override val `type` = BlockType.WOOD
}

final case class Wool(
    location: BlockLocation,
    variant: WoolVariant
) extends VariableBlock[WoolVariant] {
  override val `type` = BlockType.WOOL
}
