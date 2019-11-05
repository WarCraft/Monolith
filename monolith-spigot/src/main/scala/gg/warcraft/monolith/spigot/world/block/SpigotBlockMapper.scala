package gg.warcraft.monolith.spigot.world.block

import gg.warcraft.monolith.api.world.block.{Rail, _}
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
  def map(block: SpigotBlock): Block = {
    val loc = locationMapper.map(block.getLocation).toBlockLocation
    val spigotData = block.getBlockData
    val spigotState = block.getState

    // Lazily compute generic block data
    lazy val flooded = spigotData.asInstanceOf[Waterlogged].isWaterlogged
    lazy val lit = spigotData.asInstanceOf[Lightable].isLit
    lazy val open = spigotData.asInstanceOf[Openable].isOpen
    lazy val powered = spigotData.asInstanceOf[Powerable].isPowered
    lazy val shape = shapeMapper.map(block)
    lazy val snowy = spigotData.asInstanceOf[Snowable].isSnowy
    lazy val state = stateMapper.map(block)
    lazy val variant = variantMapper.map(block)

    lazy val attached = {
      // TODO properly map Grindstone
      if (block.getType == Material.GRINDSTONE) {
        BlockAttachment.FLOOR
      } else {
        val switch = spigotData.asInstanceOf[Switch]
        attachmentMapper.map(switch)
      }
    }

    lazy val bisection = {
      val bisected = spigotData.asInstanceOf[Bisected]
      bisectionMapper.map(bisected.getHalf)
    }

    lazy val dir = {
      val directional = spigotData.asInstanceOf[Directional]
      faceMapper.map(directional.getFacing)
    }

    lazy val extensions = {
      val multipleFacing = spigotData.asInstanceOf[MultipleFacing]
      faceMapper.map(multipleFacing.getFaces)
    }

    lazy val orientation = {
      val orientable = spigotData.asInstanceOf[Orientable]
      orientationMapper.map(orientable.getAxis)
    }

    lazy val rotation = {
      val rotatable = spigotData.asInstanceOf[Rotatable]
      rotationMapper.map(rotatable.getRotation)
    }

    // Map block
    def v[T <: BlockVariant]: T = variant.asInstanceOf[T]
    def s[T <: BlockState]: T = state.asInstanceOf[T]
    def shapeAs[T <: BlockShape]: T = shape.asInstanceOf[T]
    def dataAs[T <: SpigotBlockData]: T = spigotData.asInstanceOf[T]

    block.getType match {
      case Material.BARRIER             => Barrier(loc)
      case Material.BEACON              => Beacon(loc)
      case Material.BEDROCK             => Bedrock(loc)
      case Material.BEETROOTS           => Beetroots(loc, s[BeetrootState])
      case Material.BELL                => Bell(loc, dir)
      case Material.BLAST_FURNACE       => BlastFurnace(loc, dir, lit)
      case Material.BONE_BLOCK          => BoneBlock(loc, orientation)
      case Material.BOOKSHELF           => Bookshelf(loc)
      case Material.BREWING_STAND       => BrewingStand(loc)
      case Material.CACTUS              => Cactus(loc, s[CactusState])
      case Material.CAKE                => Cake(loc, s[CakeState])
      case Material.CARTOGRAPHY_TABLE   => CartographyTable(loc)
      case Material.CARROTS             => Carrots(loc, s[CarrotState])
      case Material.CAULDRON            => Cauldron(loc, s[CauldronState])
      case Material.CHORUS_FLOWER       => ChorusFlower(loc, s[ChorusFlowerState])
      case Material.CHORUS_PLANT        => ChorusPlant(loc, extensions)
      case Material.CLAY                => ClayBlock(loc)
      case Material.COAL_BLOCK          => CoalBlock(loc)
      case Material.COAL_ORE            => CoalOre(loc)
      case Material.COBWEB              => Cobweb(loc)
      case Material.COCOA               => CocoaPod(loc, s[CocoaState], dir)
      case Material.COMPARATOR          => Comparator(loc, v[ComparatorVariant], dir, powered)
      case Material.COMPOSTER           => Composter(loc, s[ComposterState])
      case Material.CONDUIT             => Conduit(loc, flooded)
      case Material.CRAFTING_TABLE      => CraftingTable(loc)
      case Material.DAYLIGHT_DETECTOR   => DaylightDetector(loc)
      case Material.DEAD_BUSH           => DeadBush(loc)
      case Material.DIAMOND_BLOCK       => DiamondBlock(loc)
      case Material.DIAMOND_ORE         => DiamondOre(loc)
      case Material.DRAGON_EGG          => DragonEgg(loc)
      case Material.DRIED_KELP_BLOCK    => DriedKelpBlock(loc)
      case Material.EMERALD_BLOCK       => EmeraldBlock(loc)
      case Material.EMERALD_ORE         => EmeraldOre(loc)
      case Material.ENCHANTING_TABLE    => EnchantingTable(loc)
      case Material.END_GATEWAY         => EndGateway(loc)
      case Material.END_PORTAL          => EndPortal(loc)
      case Material.END_ROD             => EndRod(loc, dir)
      case Material.END_STONE           => EndStone(loc)
      case Material.END_STONE_BRICKS    => EndStoneBrick(loc)
      case Material.FARMLAND            => Farmland(loc)
      case Material.FIRE                => Fire(loc, s[FireState], extensions)
      case Material.FLETCHING_TABLE     => FletchingTable(loc)
      case Material.FROSTED_ICE         => Frost(loc, s[FrostState])
      case Material.FURNACE             => Furnace(loc, dir, lit)
      case Material.GLOWSTONE           => Glowstone(loc)
      case Material.GOLD_BLOCK          => GoldBlock(loc)
      case Material.GOLD_ORE            => GoldOre(loc)
      case Material.GRASS_BLOCK         => GrassBlock(loc, snowy)
      case Material.GRASS_PATH          => GrassPath(loc)
      case Material.GRAVEL              => Gravel(loc)
      case Material.GRINDSTONE          => Grindstone(loc, dir, attached)
      case Material.HAY_BLOCK           => HayBale(loc, orientation)
      case Material.IRON_BLOCK          => IronBlock(loc)
      case Material.IRON_BARS           => IronBars(loc, extensions, flooded)
      case Material.IRON_ORE            => IronOre(loc)
      case Material.JIGSAW              => JigsawBlock(loc, dir)
      case Material.LADDER              => Ladder(loc, dir, flooded)
      case Material.LAPIS_BLOCK         => LapisBlock(loc)
      case Material.LAPIS_ORE           => LapisOre(loc)
      case Material.LAVA                => Lava(loc, s[LavaState])
      case Material.LEVER               => Lever(loc, dir, attached, powered)
      case Material.LILY_PAD            => LilyPad(loc)
      case Material.LOOM                => Loom(loc, dir)
      case Material.MAGMA_BLOCK         => MagmaBlock(loc)
      case Material.MELON               => Melon(loc)
      case Material.MYCELIUM            => Mycelium(loc, snowy)
      case Material.NETHERRACK          => Netherrack(loc)
      case Material.NETHER_PORTAL       => NetherPortal(loc, orientation)
      case Material.NETHER_WART         => NetherWarts(loc, s[NetherWartState])
      case Material.NETHER_WART_BLOCK   => NetherWartBlock(loc)
      case Material.OBSERVER            => Observer(loc, dir, powered)
      case Material.OBSIDIAN            => Obsidian(loc)
      case Material.PODZOL              => Podzol(loc, snowy)
      case Material.POTATOES            => Potatoes(loc, s[PotatoState])
      case Material.PURPUR_BLOCK        => PurpurBlock(loc)
      case Material.NETHER_QUARTZ_ORE   => QuartzOre(loc)
      case Material.REDSTONE_BLOCK      => RedstoneBlock(loc)
      case Material.REDSTONE_LAMP       => RedstoneLamp(loc, lit)
      case Material.REDSTONE_ORE        => RedstoneOre(loc, lit)
      case Material.REDSTONE_TORCH      => RedstoneTorch(loc, None, lit)
      case Material.REDSTONE_WALL_TORCH => RedstoneTorch(loc, Some(dir), lit)
      case Material.REDSTONE_WIRE       => RedstoneWire(loc, s[RedstoneWireState])
      case Material.SCAFFOLDING         => Scaffolding(loc, flooded)
      case Material.SEA_LANTERN         => SeaLantern(loc)
      case Material.SEA_PICKLE          => SeaPickle(loc, s[SeaPickleState], flooded)
      case Material.SLIME_BLOCK         => SlimeBlock(loc)
      case Material.SMITHING_TABLE      => SmithingTable(loc)
      case Material.SMOKER              => Smoker(loc, dir, lit)
      case Material.SNOW                => Snow(loc)
      case Material.SNOW_BLOCK          => SnowBlock(loc)
      case Material.SOUL_SAND           => SoulSand(loc)
      case Material.SPAWNER             => Spawner(loc)
      case Material.STONECUTTER         => Stonecutter(loc, dir)
      case Material.SUGAR_CANE          => SugarCane(loc, s[SugarCaneState])
      case Material.TURTLE_EGG          => TurtleEgg(loc, s[TurtleEggState])
      case Material.VINE                => Vine(loc, extensions)
      case Material.WATER               => Water(loc, s[WaterState])
      case Material.WHEAT               => Wheat(loc, s[WheatState])

      // BAMBOO
      case Material.BAMBOO =>
        val thick = dataAs[SpigotBamboo].getAge == 1
        Bamboo(loc, v[BambooVariant], s[BambooState], thick)

      // TODO Split SAPLING into its own block, has different data values than BAMBOO
      case Material.BAMBOO_SAPLING =>
        Bamboo(loc, BambooVariant.SAPLING, BambooState.STAGE_0, thick = false)

      // BARREL
      case Material.BARREL =>
        val _open = false // TODO not currently exposed by the Spigot API
        Barrel(loc, dir, _open)

      // BUBBLE_COLUMN
      case Material.BUBBLE_COLUMN =>
        val drag = dataAs[SpigotBubbleColumn].isDrag
        BubbleColumn(loc, drag)

      // CAMPFIRE
      case Material.CAMPFIRE =>
        val _dir = BlockFace.NORTH // TODO currently not exposed by Spigot API
        val signal = dataAs[SpigotCampfire].isSignalFire
        Campfire(loc, _dir, flooded, lit, signal)

      // DISPENSER
      case Material.DISPENSER =>
        val _powered = dataAs[SpigotDispenser].isTriggered
        Dispenser(loc, dir, _powered)

      // DROPPER
      case Material.DROPPER =>
        val _powered = dataAs[SpigotDropper].isTriggered
        Dropper(loc, dir, _powered)

      // END_PORTAL_FRAME
      case Material.END_PORTAL_FRAME =>
        val eye = dataAs[SpigotEndPortalFrame].hasEye
        EndPortalFrame(loc, dir, eye)

      // FERN TODO should section be an Option?
      case Material.FERN       => Fern(loc, v[FernVariant], BlockBisection.BOTTOM)
      case Material.LARGE_FERN => Fern(loc, v[FernVariant], bisection)

      // GRASS
      case Material.GRASS      => Grass(loc, v[GrassVariant], BlockBisection.BOTTOM)
      case Material.TALL_GRASS => Grass(loc, v[GrassVariant], bisection)

      // HOPPER
      case Material.HOPPER =>
        val enabled = dataAs[SpigotHopper].isEnabled
        Hopper(loc, dir, !enabled)

      // JUKEBOX
      case Material.JUKEBOX =>
        val record = dataAs[SpigotJukebox].hasRecord
        Jukebox(loc, record)

      // KELP
      case Material.KELP       => Kelp(loc, s[KelpState])
      case Material.KELP_PLANT => Kelp(loc, KelpState.FULLY_GROWN)

      // LANTERN
      case Material.LANTERN =>
        val hanging = spigotData.asInstanceOf[SpigotLantern].isHanging
        Lantern(loc, hanging)

      // LECTERN
      case Material.LECTERN =>
        val book = dataAs[SpigotLectern].hasBook
        Lectern(loc, dir, powered, book)

      // MELON_STEM
      case Material.MELON_STEM =>
        MelonStem(loc, s[MelonStemState], None)

      case Material.ATTACHED_MELON_STEM =>
        MelonStem(loc, s[MelonStemState], Some(dir))

      // NOTE_BLOCK
      case Material.NOTE_BLOCK =>
        NoteBlock(loc, v[NoteBlockVariant], s[NoteBlockState], powered)

      // PISTON
      case Material.PISTON | Material.STICKY_PISTON =>
        val extended = dataAs[SpigotPiston].isExtended
        Piston(loc, v[PistonVariant], dir, extended)

      // PISTON_HEAD
      case Material.PISTON_HEAD =>
        val short = dataAs[SpigotPistonHead].isShort
        PistonHead(loc, v[PistonHeadVariant], dir, short)

      case Material.MOVING_PISTON =>
        throw new IllegalArgumentException(s"Technical block: $block")

      // PUMPKIN
      case Material.PUMPKIN =>
        Pumpkin(loc, None, lit = false, carved = false)

      case Material.CARVED_PUMPKIN =>
        Pumpkin(loc, Some(dir), lit = false, carved = true)

      case Material.JACK_O_LANTERN =>
        Pumpkin(loc, Some(dir), lit = true, carved = true)

      // PUMPKIN_STEM
      case Material.PUMPKIN_STEM =>
        PumpkinStem(loc, s[PumpkinStemState], None)

      case Material.ATTACHED_PUMPKIN_STEM =>
        PumpkinStem(loc, s[PumpkinStemState], Some(dir))

      // REPEATER
      case Material.REPEATER =>
        val locked = dataAs[SpigotRepeater].isLocked
        Repeater(loc, s[RepeaterState], dir, powered, locked)

      // SEAGRASS
      case Material.SEAGRASS      => Seagrass(loc, BlockBisection.BOTTOM, tall = false)
      case Material.TALL_SEAGRASS => Seagrass(loc, bisection, tall = true)

      // SWEET_BERRY_BUSH
      case Material.SWEET_BERRY_BUSH =>
        SweetBerryBush(loc, s[SweetBerryState])

      // TNT
      case Material.TNT =>
        val unstable = dataAs[SpigotTNT].isUnstable
        TNT(loc, unstable)

      // TORCH
      case Material.TORCH      => Torch(loc, BlockFace.UP, wall = false)
      case Material.WALL_TORCH => Torch(loc, dir, wall = true)

      // TRIPWIRE
      case Material.TRIPWIRE =>
        val connected = dataAs[SpigotTripwire].isAttached
        val disarmed = dataAs[SpigotTripwire].isDisarmed
        Tripwire(loc, extensions, powered, connected, disarmed)

      // TRIPWIRE_HOOK
      case Material.TRIPWIRE_HOOK =>
        val connected = dataAs[SpigotTripwireHook].isAttached
        TripwireHook(loc, dir, powered, connected)

      case m if m.isAir            => Air(loc, v[AirVariant])
      case m if m.isAndesite       => Andesite(loc, v[AndesiteVariant])
      case m if m.isAnvil          => Anvil(loc, v[AnvilVariant], dir)
      case m if m.isBanner         => Banner(loc, v[BannerVariant], Some(rotation), None)
      case m if m.isWallBanner     => Banner(loc, v[BannerVariant], None, Some(dir))
      case m if m.isBrickBlock     => BrickBlock(loc, v[BrickBlockVariant])
      case m if m.isButton         => Button(loc, v[ButtonVariant], dir, attached, powered)
      case m if m.isCarpet         => Carpet(loc, v[CarpetVariant])
      case m if m.isCobblestone    => Cobblestone(loc, v[CobblestoneVariant])
      case m if m.isChest          => Chest(loc, v[ChestVariant], dir, flooded)
      case m if m.isConcrete       => Concrete(loc, v[ConcreteVariant])
      case m if m.isConcretePowder => ConcretePowder(loc, v[ConcretePowderVariant])
      case m if m.isCoral          => Coral(loc, v[CoralVariant], flooded)
      case m if m.isCoralBlock     => CoralBlock(loc, v[CoralBlockVariant])
      case m if m.isCoralFan       => CoralFan(loc, v[CoralFanVariant], None, flooded)
      case m if m.isDiorite        => Diorite(loc, v[DioriteVariant])
      case m if m.isDirt           => Dirt(loc, v[DirtVariant])
      case m if m.isFence          => Fence(loc, v[FenceVariant], extensions, flooded)
      case m if m.isFlower         => Flower(loc, v[FlowerVariant])
      case m if m.isFlowerPot      => FlowerPot(loc, v[FlowerPotVariant])
      case m if m.isGlass          => Glass(loc, v[GlassVariant])
      case m if m.isGranite        => Granite(loc, v[GraniteVariant])
      case m if m.isIce            => Ice(loc, v[IceVariant])
      case m if m.isInfestedBlock  => InfestedBlock(loc, v[InfestedBlockVariant])
      case m if m.isLeaves         => Leaves(loc, v[LeavesVariant])
      case m if m.isLog            => Log(loc, v[LogVariant], orientation)
      case m if m.isMobHead        => MobHead(loc, v[MobHeadVariant], None, Some(rotation))
      case m if m.isWallMobHead    => MobHead(loc, v[MobHeadVariant], Some(dir), None)
      case m if m.isMushroom       => Mushroom(loc, v[MushroomVariant])
      case m if m.isPillar         => Pillar(loc, v[PillarVariant], orientation)
      case m if m.isPlanks         => Planks(loc, v[PlanksVariant])
      case m if m.isPlant          => Plant(loc, v[PlantVariant], bisection)
      case m if m.isPrismarine     => Prismarine(loc, v[PrismarineVariant])
      case m if m.isQuartzBlock    => QuartzBlock(loc, v[QuartzBlockVariant])
      case m if m.isSand           => Sand(loc, v[SandVariant])
      case m if m.isSandstone      => Sandstone(loc, v[SandstoneVariant])
      case m if m.isSapling        => Sapling(loc, v[SaplingVariant], s[SaplingState])
      case m if m.isShulkerBox     => ShulkerBox(loc, v[ShulkerBoxVariant], dir)
      case m if m.isSponge         => Sponge(loc, v[SpongeVariant])
      case m if m.isStone          => Stone(loc, v[StoneVariant])
      case m if m.isStoneBrick     => StoneBrick(loc, v[StoneBrickVariant])
      case m if m.isStructureBlock => StructureBlock(loc, v[StructureBlockVariant])
      case m if m.isTerracotta     => Terracotta(loc, v[TerracottaVariant])
      case m if m.isWall           => Wall(loc, v[WallVariant], extensions, flooded)
      case m if m.isWood           => Wood(loc, v[WoodVariant], orientation)
      case m if m.isWool           => Wool(loc, v[WoolVariant])

      // BED
      case m if m.isBed => // TODO use occupied flag
        val bed = dataAs[SpigotBed]
        val occupied = bed.isOccupied
        val _bisection = mapBedPart(bed.getPart)
        Bed(loc, v[BedVariant], dir, _bisection)

      // COMMAND_BLOCK
      case m if m.isCommandBlock =>
        val conditional = dataAs[SpigotCommandBlock].isConditional
        CommandBlock(loc, v[CommandBlockVariant], dir, conditional)

      // CORAL_WALL_FAN
      case m if m.isCoralWallFan =>
        CoralFan(loc, v[CoralFanVariant], Some(dir), flooded)

      // DOOR
      case m if m.isDoor =>
        val hinge = mapDoorHinge(dataAs[SpigotDoor].getHinge)
        Door(loc, v[DoorVariant], dir, hinge, bisection, open, powered)

      // FENCE_GATE
      case m if m.isFenceGate =>
        FenceGate(loc, v[FenceGateVariant], dir, open, powered, wall = false)

      // GLASS_PANE
      case m if m.isGlassPane =>
        GlassPane(loc, v[GlassPaneVariant], extensions, flooded)

      // GLAZED_TERRACOTTA
      case m if m.isGlazedTerracotta =>
        GlazedTerracotta(loc, v[GlazedTerracottaVariant], dir)

      // MUSHROOM_BLOCK
      case m if m.isMushroomBlock =>
        MushroomBlock(loc, v[MushroomBlockVariant], extensions)

      // PRESSURE_PLATE
      case m if m.isPressurePlate =>
        PressurePlate(loc, v[PressurePlateVariant], powered)

      // RAIL
      case m if m.isRail =>
        // TODO powered only applies to PoweredRail
        val _variant = v[RailVariant]
        val _powered =
          if (_variant == RailVariant.POWERED) powered
          else false
        Rail(loc, v[RailVariant], shapeAs[RailsShape], _powered)

      // SIGN
      case m if m.isSign =>
        val sign = spigotState.asInstanceOf[SpigotSign]
        val lines = sign.getLines.toList // TODO keep as array? seem to be immutable
        val editable = sign.isEditable
        Sign(loc, v[SignVariant], None, Some(rotation), flooded, lines, editable)

      case m if m.isWallSign =>
        val sign = spigotState.asInstanceOf[SpigotSign]
        val lines = sign.getLines.toList
        val editable = sign.isEditable
        Sign(loc, v[SignVariant], Some(dir), None, flooded, lines, editable)

      // SLAB
      case m if m.isSlab =>
        val _bisection = mapSlabType(dataAs[SpigotSlab].getType)
        if (_bisection.isDefined) Slab(loc, v[SlabVariant], _bisection.get, flooded)
        else {
          // TODO map to Planks/non-double-slab type
          println("SpigotBlockMapper encountered DoubleSlab, mapping to TOP Slab!")
          Slab(loc, v[SlabVariant], BlockBisection.TOP, flooded)
        }

      // STAIRS
      case m if m.isStairs =>
        Stairs(loc, v[StairsVariant], shapeAs[StairsShape], dir, bisection, flooded)

      // TRAPDOOR
      case m if m.isTrapdoor =>
        Trapdoor(loc, v[TrapdoorVariant], dir, bisection, powered, flooded, open)

      // WEIGHTED_PRESSURE_PLATE
      case m if m.isWeightedPressurePlate =>
        val _variant = v[WeightedPressurePlateVariant]
        val _state = s[WeightedPressurePlateState]
        WeightedPressurePlate(loc, _variant, _state)
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
