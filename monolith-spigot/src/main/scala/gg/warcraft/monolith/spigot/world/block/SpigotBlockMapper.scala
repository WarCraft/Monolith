package gg.warcraft.monolith.spigot.world.block

import java.util

import gg.warcraft.monolith.api.world.block._
import gg.warcraft.monolith.api.world.block.shape.{RailsShape, StairsShape}
import gg.warcraft.monolith.api.world.block.state._
import gg.warcraft.monolith.api.world.block.variant._
import gg.warcraft.monolith.spigot.Extensions._
import gg.warcraft.monolith.spigot.world.SpigotLocationMapper
import org.bukkit.{Bukkit, Material}
import org.bukkit.block.data._
import org.bukkit.block.data.`type`.Bed.{Part => SpigotBedPart}
import org.bukkit.block.data.`type`.Door.{Hinge => SpigotDoorHinge}
import org.bukkit.block.data.`type`.Slab.{Type => SpigotSlabType}
import org.bukkit.block.data.`type`.Switch

private object SpigotBlockMapper {
  private final val materialCache: util.EnumMap[Material, SpigotBlock => Block] =
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
    private implicit val variantMapper: SpigotBlockVariantMapper
) {
  def map(block: SpigotBlock): Block =
    SpigotBlockMapper.materialCache
      .computeIfAbsent(block.getType, compute)
      .apply(block)

  def compute(material: Material): SpigotBlock => Block = {
    def data[T](block: SpigotBlock): T = block.getBlockData.asInstanceOf[T]

    val loc = (block: SpigotBlock) =>
      locationMapper.map(block.getLocation).toBlockLocation

    // Lazily compute generic block data
    val flooded = (block: SpigotBlock) => data[Waterlogged](block).isWaterlogged
    val lit = (block: SpigotBlock) => data[Lightable](block).isLit
    val open = (block: SpigotBlock) => data[Openable](block).isOpen
    val powered = (block: SpigotBlock) => data[Powerable](block).isPowered
    val snowy = (block: SpigotBlock) => data[Snowable](block).isSnowy

    lazy val attached = (block: SpigotBlock) => // attachment builder
      attachmentMapper.map(data[Switch](block))
    val bisection = (block: SpigotBlock) => // bisection builder
      bisectionMapper.map(data[Bisected](block).getHalf)
    val dir = (block: SpigotBlock) => // direction builder
      faceMapper.map(data[Directional](block).getFacing)
    val extensions = (block: SpigotBlock) => // extensions builder
      faceMapper.map(data[MultipleFacing](block).getFaces)
    val orientation = (block: SpigotBlock) => // orientation builder
      orientationMapper.map(data[Orientable](block).getAxis)
    lazy val rotation = (block: SpigotBlock) => // rotation builder
      rotationMapper.map(data[Rotatable](block).getRotation)

    def v[T <: BlockVariant](block: SpigotBlock): T =
      variantMapper.map(block).asInstanceOf[T]
    def s[T <: BlockState](block: SpigotBlock): T =
      stateMapper.map(block).asInstanceOf[T]
    def shape[T <: BlockShape](block: SpigotBlock): T =
      shapeMapper.map(block).asInstanceOf[T]

    // Map block builder
    material match {
      case Material.BARRIER        => (b => Barrier(loc(b)))
      case Material.BEACON         => (b => Beacon(loc(b)))
      case Material.BEDROCK        => (b => Bedrock(loc(b)))
      case Material.BEETROOTS      => (b => Beetroots(loc(b), s[BeetrootState](b)))
      case Material.BELL           => (b => Bell(loc(b), dir(b)))
      case Material.BLAST_FURNACE  => (b => BlastFurnace(loc(b), dir(b), lit(b)))
      case Material.BONE_BLOCK     => (b => BoneBlock(loc(b), orientation(b)))
      case Material.BOOKSHELF      => (b => Bookshelf(loc(b)))
      case Material.BREWING_STAND  => (b => BrewingStand(loc(b)))
      case Material.CACTUS         => (b => Cactus(loc(b), s[CactusState](b)))
      case Material.CAKE           => (b => Cake(loc(b), s[CakeState](b)))
      case Material.CARROTS        => (b => Carrots(loc(b), s[CarrotState](b)))
      case Material.CAULDRON       => (b => Cauldron(loc(b), s[CauldronState](b)))
      case Material.CHORUS_PLANT   => (b => ChorusPlant(loc(b), extensions(b)))
      case Material.CLAY           => (b => ClayBlock(loc(b)))
      case Material.COAL_BLOCK     => (b => CoalBlock(loc(b)))
      case Material.COAL_ORE       => (b => CoalOre(loc(b)))
      case Material.COBWEB         => (b => Cobweb(loc(b)))
      case Material.COCOA          => (b => CocoaPod(loc(b), s[CocoaState](b), dir(b)))
      case Material.COMPOSTER      => (b => Composter(loc(b), s[ComposterState](b)))
      case Material.CONDUIT        => (b => Conduit(loc(b), flooded(b)))
      case Material.CRAFTING_TABLE => (b => CraftingTable(loc(b)))
      case Material.DEAD_BUSH      => (b => DeadBush(loc(b)))
      case Material.DIAMOND_BLOCK  => (b => DiamondBlock(loc(b)))
      case Material.DIAMOND_ORE    => (b => DiamondOre(loc(b)))
      case Material.DRAGON_EGG     => (b => DragonEgg(loc(b)))
      case Material.EMERALD_BLOCK  => (b => EmeraldBlock(loc(b)))
      case Material.EMERALD_ORE    => (b => EmeraldOre(loc(b)))
      case Material.END_GATEWAY    => (b => EndGateway(loc(b)))
      case Material.END_PORTAL     => (b => EndPortal(loc(b)))
      case Material.END_ROD        => (b => EndRod(loc(b), dir(b)))
      case Material.END_STONE      => (b => EndStone(loc(b)))
      case Material.FARMLAND       => (b => Farmland(loc(b)))
      case Material.FIRE           => (b => Fire(loc(b), s[FireState](b), extensions(b)))
      case Material.FROSTED_ICE    => (b => Frost(loc(b), s[FrostState](b)))
      case Material.FURNACE        => (b => Furnace(loc(b), dir(b), lit(b)))
      case Material.GLOWSTONE      => (b => Glowstone(loc(b)))
      case Material.GOLD_BLOCK     => (b => GoldBlock(loc(b)))
      case Material.GOLD_ORE       => (b => GoldOre(loc(b)))
      case Material.GRASS_BLOCK    => (b => GrassBlock(loc(b), snowy(b)))
      case Material.GRASS_PATH     => (b => GrassPath(loc(b)))
      case Material.GRAVEL         => (b => Gravel(loc(b)))
      case Material.HAY_BLOCK      => (b => HayBale(loc(b), orientation(b)))
      case Material.IRON_BLOCK     => (b => IronBlock(loc(b)))
      case Material.IRON_BARS      => (b => IronBars(loc(b), extensions(b), flooded(b)))
      case Material.IRON_ORE       => (b => IronOre(loc(b)))
      case Material.JIGSAW         => (b => JigsawBlock(loc(b), dir(b)))
      case Material.LADDER         => (b => Ladder(loc(b), dir(b), flooded(b)))
      case Material.LAPIS_BLOCK    => (b => LapisBlock(loc(b)))
      case Material.LAPIS_ORE      => (b => LapisOre(loc(b)))
      case Material.LAVA           => (b => Lava(loc(b), s[LavaState](b)))
      case Material.LEVER          => (b => Lever(loc(b), dir(b), attached(b), powered(b)))
      case Material.LILY_PAD       => (b => LilyPad(loc(b)))
      case Material.LOOM           => (b => Loom(loc(b), dir(b)))
      case Material.MAGMA_BLOCK    => (b => MagmaBlock(loc(b)))
      case Material.MELON          => (b => Melon(loc(b)))
      case Material.MYCELIUM       => (b => Mycelium(loc(b), snowy(b)))
      case Material.NETHERRACK     => (b => Netherrack(loc(b)))
      case Material.NETHER_PORTAL  => (b => NetherPortal(loc(b), orientation(b)))
      case Material.NETHER_WART    => (b => NetherWarts(loc(b), s[NetherWartState](b)))
      case Material.OBSERVER       => (b => Observer(loc(b), dir(b), powered(b)))
      case Material.OBSIDIAN       => (b => Obsidian(loc(b)))
      case Material.PODZOL         => (b => Podzol(loc(b), snowy(b)))
      case Material.POTATOES       => (b => Potatoes(loc(b), s[PotatoState](b)))
      case Material.PURPUR_BLOCK   => (b => PurpurBlock(loc(b)))
      case Material.REDSTONE_BLOCK => (b => RedstoneBlock(loc(b)))
      case Material.REDSTONE_LAMP  => (b => RedstoneLamp(loc(b), lit(b)))
      case Material.REDSTONE_ORE   => (b => RedstoneOre(loc(b), lit(b)))
      case Material.REDSTONE_TORCH => (b => RedstoneTorch(loc(b), None, lit(b)))
      case Material.SCAFFOLDING    => (b => Scaffolding(loc(b), flooded(b)))
      case Material.SEA_LANTERN    => (b => SeaLantern(loc(b)))
      case Material.SLIME_BLOCK    => (b => SlimeBlock(loc(b)))
      case Material.SMOKER         => (b => Smoker(loc(b), dir(b), lit(b)))
      case Material.SNOW           => (b => Snow(loc(b)))
      case Material.SNOW_BLOCK     => (b => SnowBlock(loc(b)))
      case Material.SOUL_SAND      => (b => SoulSand(loc(b)))
      case Material.SPAWNER        => (b => Spawner(loc(b)))
      case Material.STONECUTTER    => (b => Stonecutter(loc(b), dir(b)))
      case Material.SUGAR_CANE     => (b => SugarCane(loc(b), s[SugarCaneState](b)))
      case Material.TURTLE_EGG     => (b => TurtleEgg(loc(b), s[TurtleEggState](b)))
      case Material.VINE           => (b => Vines(loc(b), extensions(b)))
      case Material.WATER          => (b => Water(loc(b), s[WaterState](b)))
      case Material.WHEAT          => (b => Wheat(loc(b), s[WheatState](b)))

      // materials that were pushing the alignment above too far to the right
      case Material.CARTOGRAPHY_TABLE => (b => CartographyTable(loc(b)))
      case Material.DAYLIGHT_DETECTOR => (b => DaylightDetector(loc(b)))
      case Material.DRIED_KELP_BLOCK  => (b => DriedKelpBlock(loc(b)))
      case Material.ENCHANTING_TABLE  => (b => EnchantingTable(loc(b)))
      case Material.END_STONE_BRICKS  => (b => EndStoneBrick(loc(b)))
      case Material.FLETCHING_TABLE   => (b => FletchingTable(loc(b)))
      case Material.NETHER_WART_BLOCK => (b => NetherWartBlock(loc(b)))
      case Material.NETHER_QUARTZ_ORE => (b => QuartzOre(loc(b)))
      case Material.SMITHING_TABLE    => (b => SmithingTable(loc(b)))

      // materials that were simply too long all together
      case Material.CHORUS_FLOWER =>
        (b => ChorusFlower(loc(b), s[ChorusFlowerState](b)))
      case Material.COMPARATOR =>
        (b => Comparator(loc(b), v[ComparatorVariant](b), dir(b), powered(b)))
      case Material.REDSTONE_WALL_TORCH =>
        (b => RedstoneTorch(loc(b), Some(dir(b)), lit(b)))
      case Material.REDSTONE_WIRE =>
        (b => RedstoneWire(loc(b), s[RedstoneWireState](b)))
      case Material.SEA_PICKLE =>
        (b => SeaPickle(loc(b), s[SeaPickleState](b), flooded(b)))

      // BAMBOO
      case Material.BAMBOO =>
        block =>
          val thick = data[SpigotBamboo](block).getAge == 1
          Bamboo(loc(block), v[BambooVariant](block), s[BambooState](block), thick)

      // TODO Split SAPLING into its own block, has different data values than BAMBOO
      case Material.BAMBOO_SAPLING =>
        val saplingVariant = BambooVariant.SAPLING
        val saplingState = BambooState.STAGE_0
        (block => Bamboo(loc(block), saplingVariant, saplingState, thick = false))

      // BARREL TODO open is currently not exposed by the Spigot API
      case Material.BARREL =>
        (block => Barrel(loc(block), dir(block), open = false))

      // BUBBLE_COLUMN
      case Material.BUBBLE_COLUMN =>
        block =>
          val drag = data[SpigotBubbleColumn](block).isDrag
          BubbleColumn(loc(block), drag)

      // CAMPFIRE TODO direction is currently not exposed by Spigot API
      case Material.CAMPFIRE =>
        block =>
          val signal = data[SpigotCampfire](block).isSignalFire
          Campfire(loc(block), BlockFace.NORTH, flooded(block), lit(block), signal)

      // DISPENSER
      case Material.DISPENSER =>
        block =>
          val power = data[SpigotDispenser](block).isTriggered
          Dispenser(loc(block), dir(block), power)

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
        (block => Fern(loc(block), v[FernVariant](block), bisection(block)))

      // GRASS
      case Material.GRASS =>
        (block => Grass(loc(block), v[GrassVariant](block), BlockBisection.BOTTOM))
      case Material.TALL_GRASS =>
        (block => Grass(loc(block), v[GrassVariant](block), bisection(block)))

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

      // LANTERN
      case Material.LANTERN =>
        block =>
          val hanging = data[SpigotLantern](block).isHanging
          Lantern(loc(block), hanging)

      // LECTERN
      case Material.LECTERN =>
        block =>
          val book = data[SpigotLectern](block).hasBook
          Lectern(loc(block), dir(block), powered(block), book)

      // MELON_STEM
      case Material.MELON_STEM =>
        (block => MelonStem(loc(block), s[MelonStemState](block), None))

      case Material.ATTACHED_MELON_STEM =>
        (block => MelonStem(loc(block), s[MelonStemState](block), Some(dir(block))))

      // NOTE_BLOCK
      case Material.NOTE_BLOCK =>
        b =>
          NoteBlock(loc(b), v[NoteBlockVariant](b), s[NoteBlockState](b), powered(b))

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
          Repeater(loc(b), s[RepeaterState](b), dir(b), powered(b), locked)

      // SEAGRASS
      case Material.SEAGRASS      =>
        (block => Seagrass(loc(block), BlockBisection.BOTTOM, tall = false))
      case Material.TALL_SEAGRASS =>
        (block => Seagrass(loc(block), bisection(block), tall = true))

      // SWEET_BERRY_BUSH
      case Material.SWEET_BERRY_BUSH =>
        (block => SweetBerryBush(loc(block), s[SweetBerryState](block)))

        // TNT
      case Material.TNT =>
        block =>
          val unstable = data[SpigotTNT](block).isUnstable
          TNT(loc(block), unstable)

      // TORCH
      case Material.TORCH      =>
        (block => Torch(loc(block), BlockFace.UP, wall = false))
      case Material.WALL_TORCH =>
        (block => Torch(loc(block), dir(block), wall = true))

      // TRIPWIRE
      case Material.TRIPWIRE =>
        b =>
          val connected = data[SpigotTripwire](b).isAttached
          val disarmed = data[SpigotTripwire](b).isDisarmed
          Tripwire(loc(b), extensions(b), powered(b), connected, disarmed)

      // TRIPWIRE_HOOK
      case Material.TRIPWIRE_HOOK =>
        block =>
          val connected = data[SpigotTripwireHook](block).isAttached
          TripwireHook(loc(block), dir(block), powered(block), connected)

      case it =>
        it match {
          case m if m.isAir        => Air(loc, v[AirVariant])
          case m if m.isAndesite   => Andesite(loc, v[AndesiteVariant])
          case m if m.isAnvil      => Anvil(loc, v[AnvilVariant], dir)
          case m if m.isBanner     => Banner(loc, v[BannerVariant], Some(rotation), None)
          case m if m.isWallBanner => Banner(loc, v[BannerVariant], None, Some(dir))
          case m if m.isBrickBlock => BrickBlock(loc, v[BrickBlockVariant])
          case m if m.isButton =>
            Button(loc, v[ButtonVariant], dir, attached, powered)
          case m if m.isCarpet      => Carpet(loc, v[CarpetVariant])
          case m if m.isCobblestone => Cobblestone(loc, v[CobblestoneVariant])
          case m if m.isChest       => Chest(loc, v[ChestVariant], dir, flooded)
          case m if m.isConcrete    => Concrete(loc, v[ConcreteVariant])
          case m if m.isConcretePowder =>
            ConcretePowder(loc, v[ConcretePowderVariant])
          case m if m.isCoral         => Coral(loc, v[CoralVariant], flooded)
          case m if m.isCoralBlock    => CoralBlock(loc, v[CoralBlockVariant])
          case m if m.isCoralFan      => CoralFan(loc, v[CoralFanVariant], None, flooded)
          case m if m.isDiorite       => Diorite(loc, v[DioriteVariant])
          case m if m.isDirt          => Dirt(loc, v[DirtVariant])
          case m if m.isFence         => Fence(loc, v[FenceVariant], extensions, flooded)
          case m if m.isFlower        => Flower(loc, v[FlowerVariant])
          case m if m.isFlowerPot     => FlowerPot(loc, v[FlowerPotVariant])
          case m if m.isGlass         => Glass(loc, v[GlassVariant])
          case m if m.isGranite       => Granite(loc, v[GraniteVariant])
          case m if m.isIce           => Ice(loc, v[IceVariant])
          case m if m.isInfestedBlock => InfestedBlock(loc, v[InfestedBlockVariant])
          case m if m.isLeaves        => Leaves(loc, v[LeavesVariant])
          case m if m.isLog           => Log(loc, v[LogVariant], orientation)
          case m if m.isMobHead =>
            MobHead(loc, v[MobHeadVariant], None, Some(rotation))
          case m if m.isWallMobHead =>
            MobHead(loc, v[MobHeadVariant], Some(dir), None)
          case m if m.isMushroom    => Mushroom(loc, v[MushroomVariant])
          case m if m.isPillar      => Pillar(loc, v[PillarVariant], orientation)
          case m if m.isPlanks      => Planks(loc, v[PlanksVariant])
          case m if m.isPlant       => Plant(loc, v[PlantVariant], bisection)
          case m if m.isPrismarine  => Prismarine(loc, v[PrismarineVariant])
          case m if m.isQuartzBlock => QuartzBlock(loc, v[QuartzBlockVariant])
          case m if m.isSand        => Sand(loc, v[SandVariant])
          case m if m.isSandstone   => Sandstone(loc, v[SandstoneVariant])
          case m if m.isSapling     => Sapling(loc, v[SaplingVariant], s[SaplingState])
          case m if m.isShulkerBox  => ShulkerBox(loc, v[ShulkerBoxVariant], dir)
          case m if m.isSponge      => Sponge(loc, v[SpongeVariant])
          case m if m.isStone       => Stone(loc, v[StoneVariant])
          case m if m.isStoneBrick  => StoneBrick(loc, v[StoneBrickVariant])
          case m if m.isStructureBlock =>
            StructureBlock(loc, v[StructureBlockVariant])
          case m if m.isTerracotta => Terracotta(loc, v[TerracottaVariant])
          case m if m.isWall       => Wall(loc, v[WallVariant], extensions, flooded)
          case m if m.isWood       => Wood(loc, v[WoodVariant], orientation)
          case m if m.isWool       => Wool(loc, v[WoolVariant])

          // BED
          case m if m.isBed =>
            block => // TODO use occupied flag
              val bed = data[SpigotBed]
              val occupied = bed.isOccupied
              val _bisection = mapBedPart(bed.getPart)
              Bed(loc, v[BedVariant], dir, _bisection)

            // COMMAND_BLOCK
          case m if m.isCommandBlock =>
            block =>
              val conditional = data[SpigotCommandBlock].isConditional
              CommandBlock(loc, v[CommandBlockVariant], dir, conditional)

          // CORAL_WALL_FAN
          case m if m.isCoralWallFan =>
            block =>
              CoralFan(loc, v[CoralFanVariant], Some(dir), flooded)

            // DOOR
          case m if m.isDoor =>
            block =>
              val hinge = mapDoorHinge(data[SpigotDoor].getHinge)
              Door(loc, v[DoorVariant], dir, hinge, bisection, open, powered)

          // FENCE_GATE
          case m if m.isFenceGate =>
            block =>
              FenceGate(loc, v[FenceGateVariant], dir, open, powered, wall = false)

            // GLASS_PANE
          case m if m.isGlassPane =>
            block =>
              GlassPane(loc, v[GlassPaneVariant], extensions, flooded)

            // GLAZED_TERRACOTTA
          case m if m.isGlazedTerracotta =>
            block =>
              GlazedTerracotta(loc, v[GlazedTerracottaVariant], dir)

            // MUSHROOM_BLOCK
          case m if m.isMushroomBlock =>
            block =>
              MushroomBlock(loc, v[MushroomBlockVariant], extensions)

            // PRESSURE_PLATE
          case m if m.isPressurePlate =>
            block =>
              PressurePlate(loc, v[PressurePlateVariant], powered)

            // RAIL
          case m if m.isRail =>
            block =>
              // TODO powered only applies to PoweredRail
              val _variant = v[RailVariant]
              val _powered =
                if (_variant == RailVariant.POWERED) powered
                else false
              Rail(loc, v[RailVariant], shapeAs[RailsShape], _powered)

            // SIGN
          case m if m.isSign =>
            block =>
              val sign = spigotState.asInstanceOf[SpigotSign]
              val lines = sign.getLines.toList // TODO keep as array? seem to be immutable
              val editable = sign.isEditable
              Sign(
                loc,
                v[SignVariant],
                None,
                Some(rotation),
                flooded,
                lines,
                editable
              )

          case m if m.isWallSign =>
            block =>
              val sign = spigotState.asInstanceOf[SpigotSign]
              val lines = sign.getLines.toList
              val editable = sign.isEditable
              Sign(loc, v[SignVariant], Some(dir), None, flooded, lines, editable)

          // SLAB
          case m if m.isSlab =>
            block =>
              val _bisection = mapSlabType(data[SpigotSlab].getType)
              if (_bisection.isDefined)
                Slab(loc, v[SlabVariant], _bisection.get, flooded)
              else {
                // TODO map to Planks/non-double-slab type
                Slab(loc, v[SlabVariant], BlockBisection.TOP, flooded)
              }

          // STAIRS
          case m if m.isStairs =>
            block =>
              Stairs(
                loc,
                v[StairsVariant],
                shapeAs[StairsShape],
                dir,
                bisection,
                flooded
              )

            // TRAPDOOR
          case m if m.isTrapdoor =>
            block =>
              Trapdoor(
                loc,
                v[TrapdoorVariant],
                dir,
                bisection,
                powered,
                flooded,
                open
              )

            // WEIGHTED_PRESSURE_PLATE
          case m if m.isWeightedPressurePlate =>
            block =>
              val _variant = v[WeightedPressurePlateVariant]
              val _state = s[WeightedPressurePlateState]
              WeightedPressurePlate(loc, _variant, _state)
        }
    }
  }

  def map(block: Block): SpigotBlockData = {
    // Create new block data object
    val material = block match {
      case it: VariableBlock[_] => variantMapper.map(it)

      case it: Kelp =>
        if (it.state == KelpState.FULLY_GROWN) Material.KELP_PLANT
        else Material.KELP

      case it: MelonStem =>
        if (it.state == MelonStemState.ATTACHED) Material.ATTACHED_MELON_STEM
        else Material.MELON_STEM

      case it: PumpkinStem =>
        if (it.state == PumpkinStemState.ATTACHED) Material.ATTACHED_PUMPKIN_STEM
        else Material.PUMPKIN_STEM

      case it: RedstoneTorch =>
        if (it.direction.isEmpty) Material.REDSTONE_TORCH
        else Material.REDSTONE_WALL_TORCH

      case it: Seagrass => // TODO SeagrassVariant (BlockVariant only)
        if (it.tall) Material.TALL_SEAGRASS
        else Material.SEAGRASS

      case it => typeMapper.map(it.`type`)
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

    // Set specific block data
    def dataAs[T <: SpigotBlockData]: T = data.asInstanceOf[T]

    if (block.isInstanceOf[StatefulBlock[_ <: BlockState]])
      stateMapper.map(block.asInstanceOf[StatefulBlock[_ <: BlockState]], data)

    block match {
      case it: Bamboo         => variantMapper.map(it, data)
      case it: Comparator     => variantMapper.map(it, data)
      case it: NoteBlock      => variantMapper.map(it, data)
      case it: StructureBlock => variantMapper.map(it, data)
      case _                  => ()
    }

    block match {
      case it: BubbleColumn   => dataAs[SpigotBubbleColumn].setDrag(it.drag)
      case it: Campfire       => dataAs[SpigotCampfire].setSignalFire(it.signal)
      case it: EndPortalFrame => dataAs[SpigotEndPortalFrame].setEye(it.eye)
      case it: Hopper         => dataAs[SpigotHopper].setEnabled(it.powered)
      case it: Piston         => dataAs[SpigotPiston].setExtended(it.extended)
      case it: Repeater       => dataAs[SpigotRepeater].setLocked(it.locked)
      case it: TNT            => dataAs[SpigotTNT].setUnstable(it.unstable)

      case it: Bamboo =>
        if (it.variant != BambooVariant.SAPLING) {
          val age = if (it.thick) 1 else 0
          dataAs[SpigotBamboo].setAge(age)
        }

      case it: Bed =>
        val part = mapBedPart(it.section)
        dataAs[SpigotBed].setPart(part)

      case it: CommandBlock =>
        dataAs[SpigotCommandBlock].setConditional(it.conditional)

      case it: Dispenser =>
        val triggered = it.powered
        dataAs[SpigotDispenser].setTriggered(triggered)

      case it: Dropper =>
        val triggered = it.powered
        dataAs[SpigotDispenser].setTriggered(triggered)

      case it: Lantern =>
        data.asInstanceOf[SpigotLantern].setHanging(it.hanging)

      case it: Slab =>
        val `type` = mapSlabType(it.section)
        data.asInstanceOf[SpigotSlab].setType(`type`)

      case it: Tripwire =>
        data.asInstanceOf[SpigotTripwire].setAttached(it.connected)
        data.asInstanceOf[SpigotTripwire].setDisarmed(it.disarmed)

      // TODO this match seems to be missing quite a few custom block data updates

      case _ => ()
    }

    // Return block data object
    data
  }

  def map(block: Block, spigotState: SpigotBlockState): Unit = {
    def stateAs[T <: SpigotBlockState]: T = spigotState.asInstanceOf[T]

    block match {
      case Sign(_, _, _, _, _, lines, editable) =>
        val sign = stateAs[SpigotSign]
        lines.zipWithIndex foreach { case (line, i) => sign.setLine(i, line) }
        sign.setEditable(editable)
    }
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
