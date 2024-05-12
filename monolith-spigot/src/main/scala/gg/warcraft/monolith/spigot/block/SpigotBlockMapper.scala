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

package gg.warcraft.monolith.spigot.block

import gg.warcraft.monolith.api.block._
import gg.warcraft.monolith.api.block.data.{BigDripleafTilt, DripstoneDirection}
import gg.warcraft.monolith.api.block.shape.{
  DripstoneShape, JigsawBlockShape, RailsShape, StairsShape
}
import gg.warcraft.monolith.api.block.state._
import gg.warcraft.monolith.api.block.variant._
import gg.warcraft.monolith.api.world.BlockLocation
import gg.warcraft.monolith.spigot.Extensions._
import gg.warcraft.monolith.spigot.world.SpigotLocationMapper
import org.bukkit.block.data.`type`.Bed.{Part => SpigotBedPart}
import org.bukkit.block.data.`type`.Door.{Hinge => SpigotDoorHinge}
import org.bukkit.block.data.`type`.Slab.{Type => SpigotSlabType}
import org.bukkit.block.data.`type`.Switch
import org.bukkit.block.data.{Rail => _, _}
import org.bukkit.{Bukkit, Material}

import java.util

private object SpigotBlockMapper {
  private final val cache: util.EnumMap[Material, SpigotBlock => Block] =
    new util.EnumMap(classOf[Material])
}

class SpigotBlockMapper(
    private implicit val locationMapper: SpigotLocationMapper,
    private implicit val typeMapper: SpigotBlockTypeMapper,
    private implicit val attachmentMapper: SpigotBlockAttachmentMapper,
    private implicit val bisectionMapper: SpigotBlockBisectionMapper,
    private implicit val faceMapper: SpigotBlockFaceMapper,
    private implicit val orientationMapper: SpigotBlockOrientationMapper,
    private implicit val rotationMapper: SpigotBlockRotationMapper,
    private implicit val shapeMapper: SpigotBlockShapeMapper,
    private implicit val stateMapper: SpigotBlockStateMapper,
    private implicit val variantMapper: SpigotBlockVariantMapper,
    private implicit val wallHeightMapper: SpigotWallHeightMapper
) {
  def mapBlockToOption(block: SpigotBlock): Option[Block] =
    if (block != null) {
      val builder = SpigotBlockMapper.cache
        .computeIfAbsent(block.getType, findMaterialToBlock)
      Some(builder.apply(block))
    } else None

  def findMaterialToBlock(material: Material): SpigotBlock => Block = {
    def data[T](block: SpigotBlock): T = block.getBlockData.asInstanceOf[T]

    val loc: SpigotBlock => BlockLocation =
      (block: SpigotBlock) => locationMapper.map(block.getLocation)

    // Lazily compute generic block data
    lazy val flood = (block: SpigotBlock) => data[Waterlogged](block).isWaterlogged
    lazy val lit = (block: SpigotBlock) => data[Lightable](block).isLit
    lazy val open = (block: SpigotBlock) => data[Openable](block).isOpen
    lazy val pow = (block: SpigotBlock) => data[Powerable](block).isPowered
    lazy val snowy = (block: SpigotBlock) => data[Snowable](block).isSnowy

    lazy val att = (block: SpigotBlock) => // attachment builder
      attachmentMapper.map(data[Switch](block))
    lazy val bis = (block: SpigotBlock) => // bisection builder
      bisectionMapper.map(data[Bisected](block).getHalf)
    lazy val dir = (block: SpigotBlock) => // direction builder
      faceMapper.map(data[Directional](block).getFacing)
    lazy val ext = (block: SpigotBlock) => // extensions builder
      faceMapper.map(data[MultipleFacing](block).getFaces)
    lazy val ori = (block: SpigotBlock) => // orientation builder
      orientationMapper.map(data[Orientable](block).getAxis)
    lazy val rot = (block: SpigotBlock) => // rotation builder
      rotationMapper.map(data[Rotatable](block).getRotation)

    def v[T <: BlockVariant](block: SpigotBlock): T =
      variantMapper.mapBlockToVariant(block).asInstanceOf[T]
    def s[T <: BlockState](block: SpigotBlock): T =
      stateMapper.mapBlockToState(block).asInstanceOf[T]
    def shape[T <: BlockShape](block: SpigotBlock): T =
      shapeMapper.mapMaterialToShape(block).asInstanceOf[T]

    // Map block builder
    material match {
      case Material.AMETHYST_BLOCK    => (b => AmethystBlock(loc(b)))
      case Material.ANCIENT_DEBRIS    => (b => AncientDebris(loc(b)))
      case Material.BAMBOO_MOSAIC     => (b => BambooMosaic(loc(b)))
      case Material.BAMBOO_SAPLING    => (b => BambooSapling(loc(b)))
      case Material.BARRIER           => (b => Barrier(loc(b)))
      case Material.BEACON            => (b => Beacon(loc(b)))
      case Material.BEDROCK           => (b => Bedrock(loc(b)))
      case Material.BEE_NEST          => (b => BeeNest(loc(b), s[BeeNestState](b), dir(b)))
      case Material.BEEHIVE           => (b => Beehive(loc(b), s[BeehiveState](b), dir(b)))
      case Material.BEETROOTS         => (b => Beetroots(loc(b), s[BeetrootState](b)))
      case Material.BELL              => (b => Bell(loc(b), dir(b)))
      case Material.BLAST_FURNACE     => (b => BlastFurnace(loc(b), dir(b), lit(b)))
      case Material.BONE_BLOCK        => (b => BoneBlock(loc(b), ori(b)))
      case Material.BOOKSHELF         => (b => Bookshelf(loc(b)))
      case Material.BREWING_STAND     => (b => BrewingStand(loc(b)))
      case Material.BUDDING_AMETHYST  => (b => BuddingAmethyst(loc(b)))
      case Material.CACTUS            => (b => Cactus(loc(b), s[CactusState](b)))
      case Material.CAKE              => (b => Cake(loc(b), s[CakeState](b)))
      case Material.CALCITE           => (b => Calcite(loc(b)))
      case Material.CARROTS           => (b => Carrots(loc(b), s[CarrotState](b)))
      case Material.CHAIN             => (b => Chain(loc(b), ori(b), flood(b)))
      case Material.CHORUS_PLANT      => (b => ChorusPlant(loc(b), ext(b)))
      case Material.CLAY              => (b => ClayBlock(loc(b)))
      case Material.COAL_BLOCK        => (b => CoalBlock(loc(b)))
      case Material.COBWEB            => (b => Cobweb(loc(b)))
      case Material.COCOA             => (b => CocoaPod(loc(b), s[CocoaState](b), dir(b)))
      case Material.COMPOSTER         => (b => Composter(loc(b), s[ComposterState](b)))
      case Material.CONDUIT           => (b => Conduit(loc(b), flood(b)))
      case Material.CRAFTING_TABLE    => (b => CraftingTable(loc(b)))
      case Material.CRYING_OBSIDIAN   => (b => CryingObsidian(loc(b)))
      case Material.DEAD_BUSH         => (b => DeadBush(loc(b)))
      case Material.DEEPSLATE         => (b => Deepslate(loc(b), ori(b)))
      case Material.DIAMOND_BLOCK     => (b => DiamondBlock(loc(b)))
      case Material.DIRT_PATH         => (b => DirtPath(loc(b)))
      case Material.DRAGON_EGG        => (b => DragonEgg(loc(b)))
      case Material.DRIPSTONE_BLOCK   => (b => DripstoneBlock(loc(b)))
      case Material.EMERALD_BLOCK     => (b => EmeraldBlock(loc(b)))
      case Material.END_GATEWAY       => (b => EndGateway(loc(b)))
      case Material.END_PORTAL        => (b => EndPortal(loc(b)))
      case Material.END_ROD           => (b => EndRod(loc(b), dir(b)))
      case Material.END_STONE         => (b => EndStone(loc(b)))
      case Material.FARMLAND          => (b => Farmland(loc(b)))
      case Material.FROGSPAWN         => (b => Frogspawn(loc(b)))
      case Material.FROSTED_ICE       => (b => Frost(loc(b), s[FrostState](b)))
      case Material.FURNACE           => (b => Furnace(loc(b), dir(b), lit(b)))
      case Material.GILDED_BLACKSTONE => (b => GildedBlackstone(loc(b)))
      case Material.GLOW_LICHEN       => (b => GlowLichen(loc(b), ext(b), flood(b)))
      case Material.GLOWSTONE         => (b => Glowstone(loc(b)))
      case Material.GOLD_BLOCK        => (b => GoldBlock(loc(b)))
      case Material.GRASS_BLOCK       => (b => GrassBlock(loc(b), snowy(b)))
      case Material.GRAVEL            => (b => Gravel(loc(b)))
      case Material.HANGING_ROOTS     => (b => HangingRoots(loc(b)))
      case Material.HAY_BLOCK         => (b => HayBale(loc(b), ori(b)))
      case Material.HONEY_BLOCK       => (b => HoneyBlock(loc(b)))
      case Material.HONEYCOMB_BLOCK   => (b => HoneycombBlock(loc(b)))
      case Material.IRON_BLOCK        => (b => IronBlock(loc(b)))
      case Material.IRON_BARS         => (b => IronBars(loc(b), ext(b), flood(b)))
      case Material.JIGSAW            => (b => JigsawBlock(loc(b), shape[JigsawBlockShape](b)))
      case Material.LADDER            => (b => Ladder(loc(b), dir(b), flood(b)))
      case Material.LAPIS_BLOCK       => (b => LapisBlock(loc(b)))
      case Material.LAVA              => (b => Lava(loc(b), s[LavaState](b)))
      case Material.LEVER             => (b => Lever(loc(b), dir(b), att(b), pow(b)))
      case Material.LILY_PAD          => (b => LilyPad(loc(b)))
      case Material.LODESTONE         => (b => Lodestone(loc(b)))
      case Material.LOOM              => (b => Loom(loc(b), dir(b)))
      case Material.MAGMA_BLOCK       => (b => MagmaBlock(loc(b)))
      case Material.MANGROVE_ROOTS    => (b => MangroveRoots(loc(b), flood(b)))
      case Material.MELON             => (b => Melon(loc(b)))
      case Material.MOSS_BLOCK        => (b => MossBlock(loc(b)))
      case Material.MOSS_CARPET       => (b => MossCarpet(loc(b)))
      case Material.MUD               => (b => Mud(loc(b)))
      case Material.MUD_BRICKS        => (b => MudBrick(loc(b)))
      case Material.MYCELIUM          => (b => Mycelium(loc(b), snowy(b)))
      case Material.NETHERRACK        => (b => Netherrack(loc(b)))
      case Material.NETHER_GOLD_ORE   => (b => NetherGoldOre(loc(b)))
      case Material.NETHER_PORTAL     => (b => NetherPortal(loc(b), ori(b)))
      case Material.NETHER_SPROUTS    => (b => NetherSprouts(loc(b)))
      case Material.NETHER_WART       => (b => NetherWarts(loc(b), s[NetherWartState](b)))
      case Material.NETHERITE_BLOCK   => (b => NetheriteBlock(loc(b)))
      case Material.OBSERVER          => (b => Observer(loc(b), dir(b), pow(b)))
      case Material.OBSIDIAN          => (b => Obsidian(loc(b)))
      case Material.PACKED_MUD        => (b => PackedMud(loc(b)))
      case Material.PITCHER_PLANT     => (b => PitcherPlant(loc(b), bis(b)))
      case Material.PODZOL            => (b => Podzol(loc(b), snowy(b)))
      case Material.POTATOES          => (b => Potatoes(loc(b), s[PotatoState](b)))
      case Material.POWDER_SNOW       => (b => PowderSnow(loc(b)))
      case Material.PURPUR_BLOCK      => (b => PurpurBlock(loc(b)))
      case Material.RAW_COPPER_BLOCK  => (b => RawCopperBlock(loc(b)))
      case Material.RAW_GOLD_BLOCK    => (b => RawGoldBlock(loc(b)))
      case Material.RAW_IRON_BLOCK    => (b => RawIronBlock(loc(b)))
      case Material.REDSTONE_BLOCK    => (b => RedstoneBlock(loc(b)))
      case Material.REDSTONE_LAMP     => (b => RedstoneLamp(loc(b), lit(b)))
      case Material.REDSTONE_TORCH    => (b => RedstoneTorch(loc(b), None, lit(b)))
      case Material.SCAFFOLDING       => (b => Scaffolding(loc(b), flood(b)))
      case Material.SCULK             => (b => Sculk(loc(b)))
      case Material.SCULK_VEIN        => (b => SculkVein(loc(b), ext(b), flood(b)))
      case Material.SEA_LANTERN       => (b => SeaLantern(loc(b)))
      case Material.SHROOMLIGHT       => (b => Shroomlight(loc(b)))
      case Material.SLIME_BLOCK       => (b => SlimeBlock(loc(b)))
      case Material.SMOKER            => (b => Smoker(loc(b), dir(b), lit(b)))
      case Material.SMOOTH_BASALT     => (b => SmoothBasalt(loc(b)))
      case Material.SNIFFER_EGG       => (b => SnifferEgg(loc(b), s[SnifferEggState](b)))
      case Material.SNOW              => (b => Snow(loc(b)))
      case Material.SNOW_BLOCK        => (b => SnowBlock(loc(b)))
      case Material.SOUL_SAND         => (b => SoulSand(loc(b)))
      case Material.SOUL_SOIL         => (b => SoulSoil(loc(b)))
      case Material.SPAWNER           => (b => Spawner(loc(b)))
      case Material.SPORE_BLOSSOM     => (b => SporeBlossom(loc(b)))
      case Material.STONECUTTER       => (b => Stonecutter(loc(b), dir(b)))
      case Material.SUGAR_CANE        => (b => SugarCane(loc(b), s[SugarCaneState](b)))
      case Material.TINTED_GLASS      => (b => TintedGlass(loc(b)))
      case Material.TUFF              => (b => Tuff(loc(b)))
      case Material.VINE              => (b => Vines(loc(b), ext(b)))
      case Material.WATER             => (b => Water(loc(b), s[WaterState](b)))
      case Material.WHEAT             => (b => Wheat(loc(b), s[WheatState](b)))

      // materials that were pushing the alignment above too far to the right
      case Material.CARTOGRAPHY_TABLE => (b => CartographyTable(loc(b)))
      case Material.DAYLIGHT_DETECTOR => (b => DaylightDetector(loc(b)))
      case Material.DRIED_KELP_BLOCK  => (b => DriedKelpBlock(loc(b)))
      case Material.ENCHANTING_TABLE  => (b => EnchantingTable(loc(b)))
      case Material.END_STONE_BRICKS  => (b => EndStoneBrick(loc(b)))
      case Material.FLETCHING_TABLE   => (b => FletchingTable(loc(b)))
      case Material.NETHER_QUARTZ_ORE => (b => QuartzOre(loc(b)))
      case Material.SMITHING_TABLE    => (b => SmithingTable(loc(b)))

      // materials that were simply too long all together
      case Material.BIG_DRIPLEAF_STEM =>
        (block => BigDripleafStem(loc(block), dir(block), flood(block)))
      case Material.CHORUS_FLOWER =>
        (block => ChorusFlower(loc(block), s[ChorusFlowerState](block)))
      case Material.COMPARATOR =>
        (b => Comparator(loc(b), v[ComparatorVariant](b), dir(b), pow(b)))
      case Material.LIGHT =>
        (b => LightBlock(loc(b), s[LightBlockState](b), flood(b)))
      case Material.LIGHTNING_ROD =>
        (b => LightningRod(loc(b), dir(b), flood(b), pow(b)))
      case Material.MUDDY_MANGROVE_ROOTS =>
        (b => MuddyMangroveRoots(loc(b), ori(b)))
      case Material.PITCHER_CROP =>
        (b => PitcherCrop(loc(b), s[PitcherCropState](b), bis(b)))
      case Material.REDSTONE_WALL_TORCH =>
        (block => RedstoneTorch(loc(block), Some(dir(block)), lit(block)))
      case Material.REDSTONE_WIRE =>
        (block => RedstoneWire(loc(block), s[RedstoneWireState](block)))
      case Material.REINFORCED_DEEPSLATE =>
        (b => ReinforcedDeepslate(loc(b)))
      case Material.RESPAWN_ANCHOR =>
        (b => RespawnAnchor(loc(b), s[RespawnAnchorState](b)))
      case Material.SEA_PICKLE =>
        (block => SeaPickle(loc(block), s[SeaPickleState](block), flood(block)))
      case Material.SMALL_DRIPLEAF =>
        (block => SmallDripleaf(loc(block), bis(block), dir(block), flood(block)))
      case Material.SUSPICIOUS_GRAVEL =>
        (b => SuspiciousGravel(loc(b), s[SuspiciousGravelState](b)))
      case Material.SUSPICIOUS_SAND =>
        (b => SuspiciousSand(loc(b), s[SuspiciousSandState](b)))
      case Material.TORCHFLOWER_CROP =>
        (b => TorchflowerCrop(loc(b), s[TorchflowerCropState](b)))

      // BAMBOO
      case Material.BAMBOO =>
        block =>
          val thick = data[SpigotBamboo](block).getAge == 1
          Bamboo(loc(block), v[BambooVariant](block), s[BambooState](block), thick)

      // BARREL
      case Material.BARREL =>
        (block => Barrel(loc(block), dir(block), open(block)))

      // BIG_DRIPLEAF
      case Material.BIG_DRIPLEAF =>
        block =>
          val spigotTilt = data[SpigotBigDripleaf](block).getTilt
          val tilt = BigDripleafTilt.valueOf(spigotTilt.name())
          BigDripleaf(loc(block), dir(block), flood(block), tilt)

      // BUBBLE_COLUMN
      case Material.BUBBLE_COLUMN =>
        block =>
          val drag = data[SpigotBubbleColumn](block).isDrag
          BubbleColumn(loc(block), drag)

      // CAVE_VINES
      case Material.CAVE_VINES =>
        block =>
          val berries = data[SpigotCaveVines](block).isBerries
          CaveVines(loc(block), s[CaveVinesState](block), berries)
      case Material.CAVE_VINES_PLANT =>
        block =>
          val berries = data[SpigotCaveVinesPlant](block).isBerries
          CaveVines(loc(block), CaveVinesState.FULLY_GROWN, berries)

      // DISPENSER
      case Material.DISPENSER =>
        block =>
          val power = data[SpigotDispenser](block).isTriggered
          Dispenser(loc(block), dir(block), power)

      // DRIPSTONE
      case Material.POINTED_DRIPSTONE =>
        block =>
          val spigotDir = data[SpigotDripstone](block).getVerticalDirection
          val dir = mapDripstoneDirection(spigotDir)
          Dripstone(loc(block), shape[DripstoneShape](block), flood(block), dir)

      // DROPPER
      case Material.DROPPER =>
        block =>
          val power = data[SpigotDropper](block).isTriggered
          Dropper(loc(block), dir(block), power)

      // END_PORTAL_FRAME
      case Material.END_PORTAL_FRAME =>
        block =>
          val eye = data[SpigotEndPortalFrame](block).hasEye
          EndPortalFrame(loc(block), dir(block), eye)

      // FERN TODO should section be an Option?
      case Material.FERN =>
        (block => Fern(loc(block), v[FernVariant](block), BlockBisection.BOTTOM))
      case Material.LARGE_FERN =>
        (block => Fern(loc(block), v[FernVariant](block), bis(block)))

      // GRASS
      case Material.GRASS =>
        (block => Grass(loc(block), v[GrassVariant](block), BlockBisection.BOTTOM))
      case Material.TALL_GRASS =>
        (block => Grass(loc(block), v[GrassVariant](block), bis(block)))

      // GRINDSTONE TODO properly match attachment for grindstone
      case Material.GRINDSTONE =>
        (block => Grindstone(loc(block), dir(block), BlockAttachment.FLOOR))

      // HOPPER
      case Material.HOPPER =>
        block =>
          val enabled = data[SpigotHopper](block).isEnabled
          Hopper(loc(block), dir(block), !enabled)

      // JUKEBOX
      case Material.JUKEBOX =>
        block =>
          val record = data[SpigotJukebox](block).hasRecord
          Jukebox(loc(block), record)

      // KELP
      case Material.KELP       => (block => Kelp(loc(block), s[KelpState](block)))
      case Material.KELP_PLANT => (block => Kelp(loc(block), KelpState.FULLY_GROWN))

      // LECTERN
      case Material.LECTERN =>
        block =>
          val book = data[SpigotLectern](block).hasBook
          Lectern(loc(block), dir(block), pow(block), book)

      // MELON_STEM
      case Material.MELON_STEM =>
        (block => MelonStem(loc(block), s[MelonStemState](block), None))

      case Material.ATTACHED_MELON_STEM =>
        (block => MelonStem(loc(block), s[MelonStemState](block), Some(dir(block))))

      // NOTE_BLOCK
      case Material.NOTE_BLOCK =>
        b => NoteBlock(loc(b), v[NoteBlockVariant](b), s[NoteBlockState](b), pow(b))

      // PINK_PETALS
      case Material.PINK_PETALS =>
        block =>
          val count = data[SpigotPinkPetals](block).getFlowerAmount
          PinkPetals(loc(block), dir(block), count)

      // PISTON
      case Material.PISTON | Material.STICKY_PISTON =>
        block =>
          val extended = data[SpigotPiston](block).isExtended
          Piston(loc(block), v[PistonVariant](block), dir(block), extended)

      // PISTON_HEAD
      case Material.PISTON_HEAD =>
        block =>
          val short = data[SpigotPistonHead](block).isShort
          PistonHead(loc(block), v[PistonHeadVariant](block), dir(block), short)

      case Material.MOVING_PISTON =>
        (block => throw new IllegalArgumentException(s"Technical block: $block"))

      // PUMPKIN
      case Material.PUMPKIN =>
        (block => Pumpkin(loc(block), None, lit = false, carved = false))

      case Material.CARVED_PUMPKIN =>
        (block => Pumpkin(loc(block), Some(dir(block)), lit = false, carved = true))

      case Material.JACK_O_LANTERN =>
        (block => Pumpkin(loc(block), Some(dir(block)), lit = true, carved = true))

      // PUMPKIN_STEM
      case Material.PUMPKIN_STEM =>
        (block => PumpkinStem(loc(block), s[PumpkinStemState](block), None))

      case Material.ATTACHED_PUMPKIN_STEM =>
        (b => PumpkinStem(loc(b), s[PumpkinStemState](b), Some(dir(b))))

      // REPEATER
      case Material.REPEATER =>
        b =>
          val locked = data[SpigotRepeater](b).isLocked
          Repeater(loc(b), s[RepeaterState](b), dir(b), pow(b), locked)

      // SEAGRASS
      case Material.SEAGRASS =>
        (block => Seagrass(loc(block), BlockBisection.BOTTOM, tall = false))
      case Material.TALL_SEAGRASS =>
        (block => Seagrass(loc(block), bis(block), tall = true))

      // SWEET_BERRY_BUSH
      case Material.SWEET_BERRY_BUSH =>
        (block => SweetBerryBush(loc(block), s[SweetBerryState](block)))

      // TARGET
      case Material.TARGET =>
        (block => Target(loc(block), s[TargetState](block)))

      // TNT
      case Material.TNT =>
        block =>
          val unstable = data[SpigotTNT](block).isUnstable
          TNT(loc(block), unstable)

      // TRIPWIRE
      case Material.TRIPWIRE =>
        block =>
          val connected = data[SpigotTripwire](block).isAttached
          val disarmed = data[SpigotTripwire](block).isDisarmed
          Tripwire(loc(block), ext(block), pow(block), connected, disarmed)

      // TRIPWIRE_HOOK
      case Material.TRIPWIRE_HOOK =>
        block =>
          val connected = data[SpigotTripwireHook](block).isAttached
          TripwireHook(loc(block), dir(block), pow(block), connected)

      // TURTLE_EGG
      case Material.TURTLE_EGG =>
        block =>
          val eggs = data[SpigotTurtleEgg](block).getEggs
          TurtleEgg(loc(block), s[TurtleEggState](block), eggs)

      // materials that come in many forms
      case m if m.isAir        => (b => Air(loc(b), v[AirVariant](b)))
      case m if m.isAndesite   => (b => Andesite(loc(b), v[AndesiteVariant](b)))
      case m if m.isAnvil      => (b => Anvil(loc(b), v[AnvilVariant](b), dir(b)))
      case m if m.isAzalea     => (b => Azalea(loc(b), v[AzaleaVariant](b)))
      case m if m.isBasalt     => (b => Basalt(loc(b), v[BasaltVariant](b), ori(b)))
      case m if m.isBlackstone => (b => Blackstone(loc(b), v[BlackstoneVariant](b)))
      case m if m.isCarpet     => (b => Carpet(loc(b), v[CarpetVariant](b)))
      case m if m.isCoalOre    => (b => CoalOre(loc(b), v[CoalOreVariant](b)))
      case m if m.isConcrete   => (b => Concrete(loc(b), v[ConcreteVariant](b)))
      case m if m.isCopperOre  => (b => CopperOre(loc(b), v[CopperOreVariant](b)))
      case m if m.isCoral      => (b => Coral(loc(b), v[CoralVariant](b), flood(b)))
      case m if m.isCoralBlock => (b => CoralBlock(loc(b), v[CoralBlockVariant](b)))
      case m if m.isDiamondOre => (b => DiamondOre(loc(b), v[DiamondOreVariant](b)))
      case m if m.isDiorite    => (b => Diorite(loc(b), v[DioriteVariant](b)))
      case m if m.isDirt       => (b => Dirt(loc(b), v[DirtVariant](b)))
      case m if m.isEmeraldOre => (b => EmeraldOre(loc(b), v[EmeraldOreVariant](b)))
      case m if m.isFlower     => (b => Flower(loc(b), v[FlowerVariant](b)))
      case m if m.isFlowerPot  => (b => FlowerPot(loc(b), v[FlowerPotVariant](b)))
      case m if m.isFungus     => (b => Fungus(loc(b), v[FungusVariant](b)))
      case m if m.isGlass      => (b => Glass(loc(b), v[GlassVariant](b)))
      case m if m.isGoldOre    => (b => GoldOre(loc(b), v[GoldOreVariant](b)))
      case m if m.isGranite    => (b => Granite(loc(b), v[GraniteVariant](b)))
      case m if m.isIce        => (b => Ice(loc(b), v[IceVariant](b)))
      case m if m.isIronOre    => (b => IronOre(loc(b), v[IronOreVariant](b)))
      case m if m.isLapisOre   => (b => LapisOre(loc(b), v[LapisOreVariant](b)))
      case m if m.isLeaves     => (b => Leaves(loc(b), v[LeavesVariant](b)))
      case m if m.isLog        => (b => Log(loc(b), v[LogVariant](b), ori(b)))
      case m if m.isMushroom   => (b => Mushroom(loc(b), v[MushroomVariant](b)))
      case m if m.isNylium     => (b => Nylium(loc(b), v[NyliumVariant](b)))
      case m if m.isPillar     => (b => Pillar(loc(b), v[PillarVariant](b), ori(b)))
      case m if m.isPlanks     => (b => Planks(loc(b), v[PlanksVariant](b)))
      case m if m.isPlant      => (b => Plant(loc(b), v[PlantVariant](b), bis(b)))
      case m if m.isPrismarine => (b => Prismarine(loc(b), v[PrismarineVariant](b)))
      case m if m.isRoots      => (b => Roots(loc(b), v[RootsVariant](b)))
      case m if m.isSand       => (b => Sand(loc(b), v[SandVariant](b)))
      case m if m.isSandstone  => (b => Sandstone(loc(b), v[SandstoneVariant](b)))
      case m if m.isSponge     => (b => Sponge(loc(b), v[SpongeVariant](b)))
      case m if m.isStone      => (b => Stone(loc(b), v[StoneVariant](b)))
      case m if m.isStoneBrick => (b => StoneBrick(loc(b), v[StoneBrickVariant](b)))
      case m if m.isTerracotta => (b => Terracotta(loc(b), v[TerracottaVariant](b)))
      case m if m.isWood       => (b => Wood(loc(b), v[WoodVariant](b), ori(b)))
      case m if m.isWool       => (b => Wool(loc(b), v[WoolVariant](b)))

      // many form materials that were too long all together
      case m if m.isBambooBlock =>
        (b => BambooBlock(loc(b), v[BambooBlockVariant](b), ori(b)))
      case m if m.isBanner =>
        (b => Banner(loc(b), v[BannerVariant](b), Some(rot(b)), None))
      case m if m.isWallBanner =>
        (b => Banner(loc(b), v[BannerVariant](b), None, Some(dir(b))))
      case m if m.isBlackstoneBrick =>
        (b => BlackstoneBrick(loc(b), v[BlackstoneBrickVariant](b)))
      case m if m.isBricksBlock =>
        (b => BricksBlock(loc(b), v[BricksBlockVariant](b)))
      case m if m.isButton =>
        (b => Button(loc(b), v[ButtonVariant](b), dir(b), att(b), pow(b)))
      case m if m.isCandleCake =>
        (b => CandleCake(loc(b), v[CandleCakeVariant](b), lit(b)))
      case m if m.isCauldron =>
        (b => Cauldron(loc(b), v[CauldronVariant](b), s[CauldronState](b)))
      case m if m.isCobblestone =>
        (block => Cobblestone(loc(block), v[CobblestoneVariant](block)))
      case m if m.isCopperBlock =>
        (b => CopperBlock(loc(b), v[CopperBlockVariant](b)))
      case m if m.isChest =>
        (b => Chest(loc(b), v[ChestVariant](b), dir(b), flood(b)))
      case m if m.isConcretePowder =>
        (block => ConcretePowder(loc(block), v[ConcretePowderVariant](block)))
      case m if m.isCoralFan =>
        (b => CoralFan(loc(b), v[CoralFanVariant](b), None, flood(b)))
      case m if m.isDeepslateBrick =>
        (b => DeepslateBrick(loc(b), v[DeepslateBrickVariant](b)))
      case m if m.isDeepslateStone =>
        (b => DeepslateStone(loc(b), v[DeepslateStoneVariant](b)))
      case m if m.isFence =>
        (b => Fence(loc(b), v[FenceVariant](b), ext(b), flood(b)))
      case m if m.isFroglight =>
        (b => Froglight(loc(b), v[FroglightVariant](b), ori(b)))
      case m if m.isInfestedBlock =>
        (block => InfestedBlock(loc(block), v[InfestedBlockVariant](block)))
      case m if m.isMobHead =>
        (b => MobHead(loc(b), v[MobHeadVariant](b), None, Some(rot(b))))
      case m if m.isWallMobHead =>
        (b => MobHead(loc(b), v[MobHeadVariant](b), Some(dir(b)), None))
      case m if m.isNetherWartBlock =>
        (b => NetherWartBlock(loc(b), v[NetherWartBlockVariant](b)))
      case m if m.isQuartzBlock =>
        (block => QuartzBlock(loc(block), v[QuartzBlockVariant](block)))
      case m if m.isRedstoneOre =>
        (b => RedstoneOre(loc(b), v[RedstoneOreVariant](b), lit(b)))
      case m if m.isSapling =>
        (b => Sapling(loc(b), v[SaplingVariant](b), s[SaplingState](b)))
      case m if m.isShulkerBox =>
        (block => ShulkerBox(loc(block), v[ShulkerBoxVariant](block), dir(block)))
      case m if m.isStructureBlock =>
        (block => StructureBlock(loc(block), v[StructureBlockVariant](block)))

      // AMETHYST_CLUSTER
      case m if m.isAmethystCluster =>
        block =>
          val variant = v[AmethystClusterVariant](block)
          AmethystCluster(loc(block), variant, dir(block), flood(block))

      // BED TODO map occupied flag
      case m if m.isBed =>
        block =>
          val bisection = mapBedPart(data[SpigotBed](block).getPart)
          Bed(loc(block), v[BedVariant](block), dir(block), bisection)

      // CAMPFIRE
      case Material.CAMPFIRE =>
        b =>
          val variant = v[CampfireVariant](b)
          val signal = data[SpigotCampfire](b).isSignalFire
          Campfire(loc(b), variant, dir(b), flood(b), lit(b), signal)

      // COMMAND_BLOCK
      case m if m.isCommandBlock =>
        block =>
          val conditional = data[SpigotCommandBlock](block).isConditional
          val variant = v[CommandBlockVariant](block)
          CommandBlock(loc(block), variant, dir(block), conditional)

      // CANDLE
      case m if m.isCandle =>
        b =>
          val candles = data[SpigotCandle](b).getCandles
          Candle(loc(b), v[CandleVariant](b), flood(b), candles, lit(b))

      // CORAL_WALL_FAN
      case m if m.isCoralWallFan =>
        (b => CoralFan(loc(b), v[CoralFanVariant](b), Some(dir(b)), flood(b)))

      // DOOR
      case m if m.isDoor =>
        b =>
          val hinge = mapDoorHinge(data[SpigotDoor](b).getHinge)
          Door(loc(b), v[DoorVariant](b), dir(b), hinge, bis(b), open(b), pow(b))

      // FENCE_GATE
      case m if m.isFenceGate =>
        b =>
          val variant = v[FenceGateVariant](b)
          FenceGate(loc(b), variant, dir(b), open(b), pow(b), wall = false)

      // FIRE
      case m if m.isFire =>
        (b => Fire(loc(b), v[FireVariant](b), s[FireState](b), ext(b)))

      // GLASS_PANE
      case m if m.isGlassPane =>
        (b => GlassPane(loc(b), v[GlassPaneVariant](b), ext(b), flood(b)))

      // GLAZED_TERRACOTTA
      case m if m.isGlazedTerracotta =>
        (b => GlazedTerracotta(loc(b), v[GlazedTerracottaVariant](b), dir(b)))

      // LANTERN
      case m if m.isLantern =>
        block =>
          val hanging = data[SpigotLantern](block).isHanging
          Lantern(loc(block), v[LanternVariant](block), flood(block), hanging)

      // MUSHROOM_BLOCK
      case m if m.isMushroomBlock =>
        (b => MushroomBlock(loc(b), v[MushroomBlockVariant](b), ext(b)))

      // NETHER_VINES
      case m if m.isNetherVines =>
        (b => NetherVines(loc(b), v[NetherVinesVariant](b), s[NetherVinesState](b)))

      // PRESSURE_PLATE
      case m if m.isPressurePlate =>
        (b => PressurePlate(loc(b), v[PressurePlateVariant](b), pow(b)))

      // RAIL TODO powered only applies to PoweredRail
      case m if m.isRail =>
        block =>
          val variant = v[RailVariant](block)
          val powered =
            if (variant == RailVariant.POWERED) pow(block)
            else false
          Rail(loc(block), variant, shape[RailsShape](block), powered)

      // SIGN
      case m if m.isSign =>
        b =>
          val sign = b.getState.asInstanceOf[SpigotSign]
          val lines = sign.getLines.toList
          val editable = sign.isEditable
          val variant = v[SignVariant](b)
          Sign(loc(b), variant, None, Some(rot(b)), flood(b), lines, editable)

      case m if m.isWallSign =>
        b =>
          val sign = b.getState.asInstanceOf[SpigotSign]
          val lines = sign.getLines.toList
          val editable = sign.isEditable
          val variant = v[SignVariant](b)
          Sign(loc(b), variant, Some(dir(b)), None, flood(b), lines, editable)

      // HANGING_SIGN
      case m if m.isHangingSign =>
        b =>
          val sign = b.getState.asInstanceOf[SpigotSign]
          val lines = sign.getLines.toList
          val editable = sign.isEditable
          val variant = v[HangingSignVariant](b)
          HangingSign(loc(b), variant, None, Some(rot(b)), flood(b), lines, editable)

      case m if m.isHangingWallSign =>
        b =>
          val sign = b.getState.asInstanceOf[SpigotSign]
          val lines = sign.getLines.toList
          val editable = sign.isEditable
          val variant = v[HangingSignVariant](b)
          HangingSign(loc(b), variant, Some(dir(b)), None, flood(b), lines, editable)

      // SLAB
      case m if m.isSlab =>
        b =>
          val bisection = mapSlabType(data[SpigotSlab](b).getType)
          if (bisection.isDefined)
            Slab(loc(b), v[SlabVariant](b), bisection.get, flood(b))
          else // TODO map to Planks/non-double-slab type
            Slab(loc(b), v[SlabVariant](b), BlockBisection.TOP, flood(b))

      // STAIRS
      case m if m.isStairs =>
        block =>
          val variant = v[StairsVariant](block)
          val _shape = shape[StairsShape](block)
          Stairs(loc(block), variant, _shape, dir(block), bis(block), flood(block))

      // TORCH
      case Material.TORCH =>
        (b => Torch(loc(b), v[TorchVariant](b), None))
      case Material.WALL_TORCH =>
        (b => Torch(loc(b), v[TorchVariant](b), Some(dir(b))))

      // TRAPDOOR
      case m if m.isTrapdoor =>
        b =>
          val variant = v[TrapdoorVariant](b)
          Trapdoor(loc(b), variant, dir(b), bis(b), pow(b), flood(b), open(b))

      // WALL
      case m if m.isWall =>
        b =>
          val heights = wallHeightMapper.map(data[SpigotWall](b))
          Wall(loc(b), v[WallVariant](b), heights, flood(b))

      // WEIGHTED_PRESSURE_PLATE
      case m if m.isWeightedPressurePlate =>
        block =>
          val variant = v[WeightedPressurePlateVariant](block)
          val state = s[WeightedPressurePlateState](block)
          WeightedPressurePlate(loc(block), variant, state)
    }
  }

  def mapBlockToData(block: Block): SpigotBlockData = {
    // Create new block data object
    val material =
      block match { // TODO match on block.type for jump table performance in Scala 3
        case it: NetherVines => it.variant match {
            case NetherVinesVariant.TWISTING => it.state match {
                case NetherVinesState.FULLY_GROWN => Material.TWISTING_VINES_PLANT
                case _                            => Material.TWISTING_VINES
              }
            case NetherVinesVariant.WEEPING => it.state match {
                case NetherVinesState.FULLY_GROWN => Material.WEEPING_VINES_PLANT
                case _                            => Material.WEEPING_VINES
              }
          } // TODO is it possible to move this into variantMapper.map?

        case it: RedstoneTorch =>
          if (it.direction.isEmpty) Material.REDSTONE_TORCH
          else Material.REDSTONE_WALL_TORCH

        case it: Seagrass => // TODO SeagrassVariant (BlockVariant only)
          if (it.tall) Material.TALL_SEAGRASS
          else Material.SEAGRASS

        case it: VariableBlock[_] => variantMapper.mapBlockToMaterial(it)
        case it: StatefulBlock[_] => stateMapper.mapStateToMaterial(it.state)
        case it                   => typeMapper.map(it.`type`)
      }

    val data = Bukkit.createBlockData(material)

    // Lazily compute generic block data
    def blockAs[T <: Block]: T = block.asInstanceOf[T]

    lazy val flooded = blockAs[FloodableBlock].flooded
    lazy val lit = blockAs[LightableBlock].lit
    lazy val open = blockAs[OpenableBlock].open
    lazy val powered = blockAs[PowerableBlock].powered
    lazy val snowy = blockAs[SnowableBlock].snowy

    lazy val attached = {
      val attachment = blockAs[AttachedBlock].attachment
      attachmentMapper.map(attachment)
    }

    lazy val bisection = {
      val bisection = blockAs[BisectedBlock].section
      bisectionMapper.map(bisection)
    }

    lazy val direction = block match {
      case it: DirectedBlock   => faceMapper.map(it.direction)
      case it: DirectableBlock => faceMapper.map(it.direction.get)
    }

    lazy val extensions = {
      val extensions = blockAs[ExtendableBlock].extensions
      faceMapper.map(extensions)
    }

    lazy val orientation = {
      val orientation = blockAs[OrientedBlock].orientation
      orientationMapper.map(orientation)
    }

    lazy val rotation = {
      val rotation = blockAs[RotatableBlock].rotation.orNull
      rotationMapper.map(rotation)
    }

    // Set generic block data
    if (data.isInstanceOf[Bisected])
      data.asInstanceOf[Bisected].setHalf(bisection)
    if (data.isInstanceOf[Directional])
      data.asInstanceOf[Directional].setFacing(direction)
    if (data.isInstanceOf[Lightable])
      data.asInstanceOf[Lightable].setLit(lit)
    if (data.isInstanceOf[Openable])
      data.asInstanceOf[Openable].setOpen(open)
    if (data.isInstanceOf[Orientable])
      data.asInstanceOf[Orientable].setAxis(orientation)
    if (data.isInstanceOf[Powerable])
      data.asInstanceOf[Powerable].setPowered(powered)
    if (data.isInstanceOf[Rotatable])
      data.asInstanceOf[Rotatable].setRotation(rotation)
    if (data.isInstanceOf[Snowable])
      data.asInstanceOf[Snowable].setSnowy(snowy)
    if (data.isInstanceOf[Switch])
      data.asInstanceOf[Switch].setFace(attached)
    if (data.isInstanceOf[Waterlogged])
      data.asInstanceOf[Waterlogged].setWaterlogged(flooded)
    if (data.isInstanceOf[MultipleFacing]) {
      val multipleFacing = data.asInstanceOf[MultipleFacing]
      // TODO check if this is necessary on fresh BlockData objects
      multipleFacing.getAllowedFaces.forEach(multipleFacing.setFace(_, false))
      extensions.forEach(multipleFacing.setFace(_, true))
    }

    // Set state, variant and shape block data
    def dataAs[T <: SpigotBlockData]: T = data.asInstanceOf[T]

    if (block.isInstanceOf[StatefulBlock[_ <: BlockState]]) {
      val state = block.asInstanceOf[StatefulBlock[_ <: BlockState]].state
      stateMapper.mapStateToData(state, data)
    }

    if (block.isInstanceOf[VariableBlock[_ <: BlockVariant]]) {
      val variant = block.asInstanceOf[VariableBlock[_ <: BlockVariant]].variant
      variantMapper.mapVariantToData(variant, data)
    }

    if (block.isInstanceOf[ShapedBlock[_ <: BlockShape]]) {
      val shape = block.asInstanceOf[ShapedBlock[_ <: BlockShape]].shape
      shapeMapper.mapShapeToData(shape, data)
    }

    // TODO move into separate SpigotBlockDataMapper class
    // Set custom block data
    block match {
      case it: BubbleColumn   => dataAs[SpigotBubbleColumn].setDrag(it.drag)
      case it: Campfire       => dataAs[SpigotCampfire].setSignalFire(it.signal)
      case it: Candle         => dataAs[SpigotCandle].setCandles(it.count)
      case it: Dispenser      => dataAs[SpigotDispenser].setTriggered(it.powered)
      case it: Dropper        => dataAs[SpigotDropper].setTriggered(it.powered)
      case it: EndPortalFrame => dataAs[SpigotEndPortalFrame].setEye(it.eye)
      case it: Hopper         => dataAs[SpigotHopper].setEnabled(it.powered)
      case it: Lantern        => dataAs[SpigotLantern].setHanging(it.hanging)
      case it: Piston         => dataAs[SpigotPiston].setExtended(it.extended)
      case it: Repeater       => dataAs[SpigotRepeater].setLocked(it.locked)
      case it: TNT            => dataAs[SpigotTNT].setUnstable(it.unstable)
      case it: TurtleEgg      => dataAs[SpigotTurtleEgg].setEggs(it.count)

      case it: Bamboo =>
        val age = if (it.thick) 1 else 0
        dataAs[SpigotBamboo].setAge(age)

      case it: Bed =>
        val part = mapBedPart(it.section)
        dataAs[SpigotBed].setPart(part)

      case it: CaveVines =>
        if (it.state == CaveVinesState.FULLY_GROWN) {
          dataAs[SpigotCaveVinesPlant].setBerries(it.berries)
        } else dataAs[SpigotCaveVines].setBerries(it.berries)

      case it: CommandBlock =>
        dataAs[SpigotCommandBlock].setConditional(it.conditional)

      case it: Dripstone =>
        val direction = mapDripstoneDirection(it.direction)
        dataAs[SpigotDripstone].setVerticalDirection(direction)

      case it: PinkPetals =>
        dataAs[SpigotPinkPetals].setFlowerAmount(it.count)

      case it: Slab =>
        val `type` = mapSlabType(it.section)
        dataAs[SpigotSlab].setType(`type`)

      case it: Tripwire =>
        dataAs[SpigotTripwire].setAttached(it.connected)
        dataAs[SpigotTripwire].setDisarmed(it.disarmed)

      case it: Wall =>
        wallHeightMapper.map(dataAs[SpigotWall], it.heights)

      // TODO this match seems to be missing quite a few custom block data updates

      case _ => ()
    }

    // Return block data object
    data
  }

  def mapBlockToState(block: Block, state: SpigotBlockState): Unit = block match {
    // TODO update to latest multi side signs and glowable text
    case Sign(_, _, _, _, _, lines, editable) =>
      val sign = state.asInstanceOf[SpigotSign]
      lines.zipWithIndex foreach { case (line, i) => sign.setLine(i, line) }
      sign.setEditable(editable)

    case HangingSign(_, _, _, _, _, lines, editable) =>
      val sign = state.asInstanceOf[SpigotSign]
      lines.zipWithIndex foreach { case (line, i) => sign.setLine(i, line) }
      sign.setEditable(editable)

    case _ => ()
  }

  def mapBedPart(part: SpigotBedPart): BlockBisection = part match {
    case SpigotBedPart.HEAD => BlockBisection.TOP
    case SpigotBedPart.FOOT => BlockBisection.BOTTOM
  }

  def mapBedPart(part: BlockBisection): SpigotBedPart = part match {
    case BlockBisection.TOP    => SpigotBedPart.HEAD
    case BlockBisection.BOTTOM => SpigotBedPart.FOOT
  }

  def mapDoorHinge(hinge: SpigotDoorHinge): BlockHinge = hinge match {
    case SpigotDoorHinge.LEFT  => BlockHinge.LEFT
    case SpigotDoorHinge.RIGHT => BlockHinge.RIGHT
  }

  def mapDoorHinge(hinge: BlockHinge): SpigotDoorHinge = hinge match {
    case BlockHinge.LEFT  => SpigotDoorHinge.LEFT
    case BlockHinge.RIGHT => SpigotDoorHinge.RIGHT
  }

  def mapDripstoneDirection(dir: SpigotBlockFace): DripstoneDirection =
    faceMapper.map(dir) match {
      case BlockFace.UP   => DripstoneDirection.UP
      case BlockFace.DOWN => DripstoneDirection.DOWN
    }

  def mapDripstoneDirection(dir: DripstoneDirection): SpigotBlockFace = {
    val result = dir match {
      case DripstoneDirection.UP   => BlockFace.UP
      case DripstoneDirection.DOWN => BlockFace.DOWN
    }
    faceMapper.map(result)
  }

  def mapSlabType(`type`: SpigotSlabType): Option[BlockBisection] = `type` match {
    case SpigotSlabType.TOP    => Some(BlockBisection.TOP)
    case SpigotSlabType.BOTTOM => Some(BlockBisection.BOTTOM)
    case SpigotSlabType.DOUBLE => None
  }

  def mapSlabType(`type`: BlockBisection): SpigotSlabType = `type` match {
    case BlockBisection.TOP    => SpigotSlabType.TOP
    case BlockBisection.BOTTOM => SpigotSlabType.BOTTOM
  }
}
