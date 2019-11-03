package gg.warcraft.monolith.spigot.world.item

import gg.warcraft.monolith.api.world.block.BlockVariant
import gg.warcraft.monolith.api.world.item._
import gg.warcraft.monolith.api.world.item.variant._
import gg.warcraft.monolith.spigot.world.block.SpigotBlockVariantMapper
import javax.inject.Inject
import org.bukkit.Material

class SpigotItemVariantMapper @Inject() (
    blockMapper: SpigotBlockVariantMapper
) {
  def map(material: Material): ItemVariant = material match {
    // ARROW
    case Material.ARROW          => ArrowVariant.NORMAL
    case Material.SPECTRAL_ARROW => ArrowVariant.SPECTRAL
    case Material.TIPPED_ARROW   => ArrowVariant.TIPPED

    // AXE
    case Material.WOODEN_AXE  => AxeVariant.WOOD
    case Material.STONE_AXE   => AxeVariant.STONE
    case Material.IRON_AXE    => AxeVariant.IRON
    case Material.GOLDEN_AXE  => AxeVariant.GOLD
    case Material.DIAMOND_AXE => AxeVariant.DIAMOND

    // BANNER_PATTERN
    case Material.CREEPER_BANNER_PATTERN => BannerPatternVariant.CREEPER
    case Material.FLOWER_BANNER_PATTERN  => BannerPatternVariant.FLOWER
    case Material.GLOBE_BANNER_PATTERN   => BannerPatternVariant.GLOBE
    case Material.MOJANG_BANNER_PATTERN  => BannerPatternVariant.MOJANG
    case Material.SKULL_BANNER_PATTERN   => BannerPatternVariant.SKULL

    // BEEF
    case Material.BEEF        => BeefVariant.RAW
    case Material.COOKED_BEEF => BeefVariant.COOKED

    // BOAT
    case Material.ACACIA_BOAT   => BoatVariant.ACACIA
    case Material.BIRCH_BOAT    => BoatVariant.BIRCH
    case Material.DARK_OAK_BOAT => BoatVariant.DARK_OAK
    case Material.JUNGLE_BOAT   => BoatVariant.JUNGLE
    case Material.OAK_BOAT      => BoatVariant.OAK
    case Material.SPRUCE_BOAT   => BoatVariant.SPRUCE

    // BOOTS
    case Material.LEATHER_BOOTS   => BootsVariant.LEATHER
    case Material.CHAINMAIL_BOOTS => BootsVariant.CHAINMAIL
    case Material.IRON_BOOTS      => BootsVariant.IRON
    case Material.GOLDEN_BOOTS    => BootsVariant.GOLD
    case Material.DIAMOND_BOOTS   => BootsVariant.DIAMOND

    // BRICK
    case Material.BRICK        => BrickVariant.NORMAL
    case Material.NETHER_BRICK => BrickVariant.NETHER

    // BUCKET
    case Material.BUCKET => BucketVariant.EMPTY

    case Material.COD_BUCKET           => BucketVariant.COD
    case Material.PUFFERFISH_BUCKET    => BucketVariant.PUFFERFISH
    case Material.SALMON_BUCKET        => BucketVariant.SALMON
    case Material.TROPICAL_FISH_BUCKET => BucketVariant.TROPICAL_FISH

    case Material.LAVA_BUCKET  => BucketVariant.LAVA
    case Material.MILK_BUCKET  => BucketVariant.MILK
    case Material.WATER_BUCKET => BucketVariant.WATER

    // CHESTPLATE
    case Material.LEATHER_CHESTPLATE   => ChestplateVariant.LEATHER
    case Material.CHAINMAIL_CHESTPLATE => ChestplateVariant.CHAINMAIL
    case Material.IRON_CHESTPLATE      => ChestplateVariant.IRON
    case Material.GOLDEN_CHESTPLATE    => ChestplateVariant.GOLD
    case Material.DIAMOND_CHESTPLATE   => ChestplateVariant.DIAMOND

    // CHICKEN
    case Material.CHICKEN        => ChickenVariant.RAW
    case Material.COOKED_CHICKEN => ChickenVariant.COOKED

    // CHORUS_FRUIT
    case Material.CHORUS_FRUIT        => ChorusFruitVariant.NORMAL
    case Material.POPPED_CHORUS_FRUIT => ChorusFruitVariant.POPPED

    // COD
    case Material.COD        => CodVariant.RAW
    case Material.COOKED_COD => CodVariant.COOKED

    // HELMET
    case Material.LEATHER_HELMET   => HelmetVariant.LEATHER
    case Material.CHAINMAIL_HELMET => HelmetVariant.CHAINMAIL
    case Material.IRON_HELMET      => HelmetVariant.IRON
    case Material.GOLDEN_HELMET    => HelmetVariant.GOLD
    case Material.DIAMOND_HELMET   => HelmetVariant.DIAMOND

    // HOE
    case Material.WOODEN_HOE  => HoeVariant.WOOD
    case Material.STONE_HOE   => HoeVariant.STONE
    case Material.IRON_HOE    => HoeVariant.IRON
    case Material.GOLDEN_HOE  => HoeVariant.GOLD
    case Material.DIAMOND_HOE => HoeVariant.DIAMOND

    // HORSE_ARMOR
    case Material.LEATHER_HORSE_ARMOR => HorseArmorVariant.LEATHER
    case Material.IRON_HORSE_ARMOR    => HorseArmorVariant.IRON
    case Material.GOLDEN_HORSE_ARMOR  => HorseArmorVariant.GOLD
    case Material.DIAMOND_HORSE_ARMOR => HorseArmorVariant.DIAMOND

    // LEGGINGS
    case Material.LEATHER_LEGGINGS   => LeggingsVariant.LEATHER
    case Material.CHAINMAIL_LEGGINGS => LeggingsVariant.CHAINMAIL
    case Material.IRON_LEGGINGS      => LeggingsVariant.IRON
    case Material.GOLDEN_LEGGINGS    => LeggingsVariant.GOLD
    case Material.DIAMOND_LEGGINGS   => LeggingsVariant.DIAMOND

    // MINECART
    case Material.MINECART => MinecartVariant.EMPTY

    case Material.CHEST_MINECART         => MinecartVariant.CHEST
    case Material.COMMAND_BLOCK_MINECART => MinecartVariant.COMMAND_BLOCK
    case Material.FURNACE_MINECART       => MinecartVariant.FURNACE
    case Material.HOPPER_MINECART        => MinecartVariant.HOPPER
    case Material.TNT_MINECART           => MinecartVariant.TNT

    // MUSIC_DISC
    case Material.MUSIC_DISC_11      => MusicDiscVariant.DISC_11
    case Material.MUSIC_DISC_13      => MusicDiscVariant.DISC_13
    case Material.MUSIC_DISC_BLOCKS  => MusicDiscVariant.DISC_BLOCKS
    case Material.MUSIC_DISC_CAT     => MusicDiscVariant.DISC_CAT
    case Material.MUSIC_DISC_CHIRP   => MusicDiscVariant.DISC_CHIRP
    case Material.MUSIC_DISC_FAR     => MusicDiscVariant.DISC_FAR
    case Material.MUSIC_DISC_MALL    => MusicDiscVariant.DISC_MALL
    case Material.MUSIC_DISC_MELLOHI => MusicDiscVariant.DISC_MELLOHI
    case Material.MUSIC_DISC_STAL    => MusicDiscVariant.DISC_STAL
    case Material.MUSIC_DISC_STRAD   => MusicDiscVariant.DISC_STRAD
    case Material.MUSIC_DISC_WAIT    => MusicDiscVariant.DISC_WAIT
    case Material.MUSIC_DISC_WARD    => MusicDiscVariant.DISC_WARD

    // MUTTON
    case Material.MUTTON        => MuttonVariant.RAW
    case Material.COOKED_MUTTON => MuttonVariant.COOKED

    // PICKAXE
    case Material.WOODEN_PICKAXE  => PickaxeVariant.WOOD
    case Material.STONE_PICKAXE   => PickaxeVariant.STONE
    case Material.IRON_PICKAXE    => PickaxeVariant.IRON
    case Material.GOLDEN_PICKAXE  => PickaxeVariant.GOLD
    case Material.DIAMOND_PICKAXE => PickaxeVariant.DIAMOND

    // PORKCHOP
    case Material.PORKCHOP        => PorkchopVariant.RAW
    case Material.COOKED_PORKCHOP => PorkchopVariant.COOKED

    // POTATO
    case Material.POTATO           => PotatoVariant.NORMAL
    case Material.BAKED_POTATO     => PotatoVariant.BAKED
    case Material.POISONOUS_POTATO => PotatoVariant.POISONOUS

    // POTION
    case Material.POTION           => PotionVariant.NORMAL
    case Material.LINGERING_POTION => PotionVariant.LINGERING
    case Material.SPLASH_POTION    => PotionVariant.SPLASH

    // RABBIT
    case Material.RABBIT        => RabbitVariant.RAW
    case Material.COOKED_RABBIT => RabbitVariant.COOKED

    // SALMON
    case Material.SALMON        => SalmonVariant.RAW
    case Material.COOKED_SALMON => SalmonVariant.COOKED

    // SEEDS
    case Material.BEETROOT_SEEDS => SeedsVariant.BEETROOT
    case Material.MELON_SEEDS    => SeedsVariant.MELON
    case Material.PUMPKIN_SEEDS  => SeedsVariant.PUMPKIN
    case Material.WHEAT_SEEDS    => SeedsVariant.WHEAT

    // SHOVEL
    case Material.WOODEN_SHOVEL  => ShovelVariant.WOOD
    case Material.STONE_SHOVEL   => ShovelVariant.STONE
    case Material.IRON_SHOVEL    => ShovelVariant.IRON
    case Material.GOLDEN_SHOVEL  => ShovelVariant.GOLD
    case Material.DIAMOND_SHOVEL => ShovelVariant.DIAMOND

    // SPAWN_EGG
    case Material.BAT_SPAWN_EGG              => SpawnEggVariant.BAT
    case Material.BLAZE_SPAWN_EGG            => SpawnEggVariant.BLAZE
    case Material.CAT_SPAWN_EGG              => SpawnEggVariant.CAT
    case Material.CAVE_SPIDER_SPAWN_EGG      => SpawnEggVariant.CAVE_SPIDER
    case Material.CHICKEN_SPAWN_EGG          => SpawnEggVariant.CHICKEN
    case Material.COD_SPAWN_EGG              => SpawnEggVariant.COD
    case Material.COW_SPAWN_EGG              => SpawnEggVariant.COW
    case Material.CREEPER_SPAWN_EGG          => SpawnEggVariant.CREEPER
    case Material.DOLPHIN_SPAWN_EGG          => SpawnEggVariant.DOLPHIN
    case Material.DONKEY_SPAWN_EGG           => SpawnEggVariant.DONKEY
    case Material.DROWNED_SPAWN_EGG          => SpawnEggVariant.DROWNED
    case Material.ELDER_GUARDIAN_SPAWN_EGG   => SpawnEggVariant.ELDER_GUARDIAN
    case Material.ENDERMAN_SPAWN_EGG         => SpawnEggVariant.ENDERMAN
    case Material.ENDERMITE_SPAWN_EGG        => SpawnEggVariant.ENDERMITE
    case Material.EVOKER_SPAWN_EGG           => SpawnEggVariant.EVOKER
    case Material.FOX_SPAWN_EGG              => SpawnEggVariant.FOX
    case Material.GHAST_SPAWN_EGG            => SpawnEggVariant.GHAST
    case Material.GUARDIAN_SPAWN_EGG         => SpawnEggVariant.GUARDIAN
    case Material.HORSE_SPAWN_EGG            => SpawnEggVariant.HORSE
    case Material.HUSK_SPAWN_EGG             => SpawnEggVariant.HUSK
    case Material.LLAMA_SPAWN_EGG            => SpawnEggVariant.LLAMA
    case Material.MAGMA_CUBE_SPAWN_EGG       => SpawnEggVariant.MAGMA_CUBE
    case Material.MOOSHROOM_SPAWN_EGG        => SpawnEggVariant.MOOSHROOM
    case Material.MULE_SPAWN_EGG             => SpawnEggVariant.MULE
    case Material.OCELOT_SPAWN_EGG           => SpawnEggVariant.OCELOT
    case Material.PANDA_SPAWN_EGG            => SpawnEggVariant.PANDA
    case Material.PARROT_SPAWN_EGG           => SpawnEggVariant.PARROT
    case Material.PHANTOM_SPAWN_EGG          => SpawnEggVariant.PHANTOM
    case Material.PIG_SPAWN_EGG              => SpawnEggVariant.PIG
    case Material.PILLAGER_SPAWN_EGG         => SpawnEggVariant.PILLAGER
    case Material.POLAR_BEAR_SPAWN_EGG       => SpawnEggVariant.POLAR_BEAR
    case Material.PUFFERFISH_SPAWN_EGG       => SpawnEggVariant.PUFFERFISH
    case Material.RABBIT_SPAWN_EGG           => SpawnEggVariant.RABBIT
    case Material.RAVAGER_SPAWN_EGG          => SpawnEggVariant.RAVAGER
    case Material.SALMON_SPAWN_EGG           => SpawnEggVariant.SALMON
    case Material.SHEEP_SPAWN_EGG            => SpawnEggVariant.SHEEP
    case Material.SHULKER_SPAWN_EGG          => SpawnEggVariant.SHULKER
    case Material.SILVERFISH_SPAWN_EGG       => SpawnEggVariant.SILVERFISH
    case Material.SKELETON_HORSE_SPAWN_EGG   => SpawnEggVariant.SKELETON_HORSE
    case Material.SKELETON_SPAWN_EGG         => SpawnEggVariant.SKELETON
    case Material.SLIME_SPAWN_EGG            => SpawnEggVariant.SLIME
    case Material.SPIDER_SPAWN_EGG           => SpawnEggVariant.SPIDER
    case Material.SQUID_SPAWN_EGG            => SpawnEggVariant.SQUID
    case Material.STRAY_SPAWN_EGG            => SpawnEggVariant.STRAY
    case Material.TRADER_LLAMA_SPAWN_EGG     => SpawnEggVariant.TRADER_LLAMA
    case Material.TROPICAL_FISH_SPAWN_EGG    => SpawnEggVariant.TROPICAL_FISH
    case Material.TURTLE_SPAWN_EGG           => SpawnEggVariant.TURTLE
    case Material.VEX_SPAWN_EGG              => SpawnEggVariant.VEX
    case Material.VILLAGER_SPAWN_EGG         => SpawnEggVariant.VILLAGER
    case Material.VINDICATOR_SPAWN_EGG       => SpawnEggVariant.VINDICATOR
    case Material.WANDERING_TRADER_SPAWN_EGG => SpawnEggVariant.WANDERING_TRADER
    case Material.WITCH_SPAWN_EGG            => SpawnEggVariant.WITCH
    case Material.WITHER_SKELETON_SPAWN_EGG  => SpawnEggVariant.WITHER_SKELETON
    case Material.WOLF_SPAWN_EGG             => SpawnEggVariant.WOLF
    case Material.ZOMBIE_HORSE_SPAWN_EGG     => SpawnEggVariant.ZOMBIE_HORSE
    case Material.ZOMBIE_PIGMAN_SPAWN_EGG    => SpawnEggVariant.ZOMBIE_PIGMAN
    case Material.ZOMBIE_SPAWN_EGG           => SpawnEggVariant.ZOMBIE
    case Material.ZOMBIE_VILLAGER_SPAWN_EGG  => SpawnEggVariant.ZOMBIE_VILLAGER

    // SPIDER_EYE
    case Material.SPIDER_EYE           => SpiderEyeVariant.NORMAL
    case Material.FERMENTED_SPIDER_EYE => SpiderEyeVariant.FERMENTED

    // STEW
    case Material.BEETROOT_SOUP   => StewVariant.BEETROOT
    case Material.MUSHROOM_STEW   => StewVariant.MUSHROOM
    case Material.RABBIT_STEW     => StewVariant.RABBIT
    case Material.SUSPICIOUS_STEW => StewVariant.SUSPICIOUS

    // STRUCTURE_BLOCK
    case Material.STRUCTURE_BLOCK => StructureBlockVariant.BLOCK
    case Material.STRUCTURE_VOID  => StructureBlockVariant.VOID

    // SWORD
    case Material.WOODEN_SWORD  => SwordVariant.WOOD
    case Material.STONE_SWORD   => SwordVariant.STONE
    case Material.IRON_SWORD    => SwordVariant.IRON
    case Material.GOLDEN_SWORD  => SwordVariant.GOLD
    case Material.DIAMOND_SWORD => SwordVariant.DIAMOND

    case it: Material => blockMapper.map(it).asInstanceOf[ItemVariant]
  }

  def map(item: SpigotItemStack): ItemVariant = item match {
    // TODO map item specifics

    case it: SpigotItemStack => map(it.getType)
  }

  def map(variant: ItemVariant): Material = variant match {
    // ARROW
    case ArrowVariant.NORMAL   => Material.ARROW
    case ArrowVariant.SPECTRAL => Material.SPECTRAL_ARROW
    case ArrowVariant.TIPPED   => Material.TIPPED_ARROW

    // AXE
    case AxeVariant.WOOD    => Material.WOODEN_AXE
    case AxeVariant.STONE   => Material.STONE_AXE
    case AxeVariant.IRON    => Material.IRON_AXE
    case AxeVariant.GOLD    => Material.GOLDEN_AXE
    case AxeVariant.DIAMOND => Material.DIAMOND_AXE

    // BANNER_PATTERN
    case BannerPatternVariant.CREEPER => Material.CREEPER_BANNER_PATTERN
    case BannerPatternVariant.FLOWER  => Material.FLOWER_BANNER_PATTERN
    case BannerPatternVariant.GLOBE   => Material.GLOBE_BANNER_PATTERN
    case BannerPatternVariant.MOJANG  => Material.MOJANG_BANNER_PATTERN
    case BannerPatternVariant.SKULL   => Material.SKULL_BANNER_PATTERN

    // BEEF
    case BeefVariant.RAW    => Material.BEEF
    case BeefVariant.COOKED => Material.COOKED_BEEF

    // BOAT
    case BoatVariant.ACACIA   => Material.ACACIA_BOAT
    case BoatVariant.BIRCH    => Material.BIRCH_BOAT
    case BoatVariant.DARK_OAK => Material.DARK_OAK_BOAT
    case BoatVariant.JUNGLE   => Material.JUNGLE_BOAT
    case BoatVariant.OAK      => Material.OAK_BOAT
    case BoatVariant.SPRUCE   => Material.SPRUCE_BOAT

    // BOOTS
    case BootsVariant.LEATHER   => Material.LEATHER_BOOTS
    case BootsVariant.CHAINMAIL => Material.CHAINMAIL_BOOTS
    case BootsVariant.IRON      => Material.IRON_BOOTS
    case BootsVariant.GOLD      => Material.GOLDEN_BOOTS
    case BootsVariant.DIAMOND   => Material.DIAMOND_BOOTS

    // BRICK
    case BrickVariant.NORMAL => Material.BRICK
    case BrickVariant.NETHER => Material.NETHER_BRICK

    // BUCKET
    case BucketVariant.EMPTY => Material.BUCKET

    case BucketVariant.COD           => Material.COD_BUCKET
    case BucketVariant.PUFFERFISH    => Material.PUFFERFISH_BUCKET
    case BucketVariant.SALMON        => Material.SALMON_BUCKET
    case BucketVariant.TROPICAL_FISH => Material.TROPICAL_FISH_BUCKET

    case BucketVariant.LAVA  => Material.LAVA_BUCKET
    case BucketVariant.MILK  => Material.MILK_BUCKET
    case BucketVariant.WATER => Material.WATER_BUCKET

    // CHESTPLATE
    case ChestplateVariant.LEATHER   => Material.LEATHER_CHESTPLATE
    case ChestplateVariant.CHAINMAIL => Material.CHAINMAIL_CHESTPLATE
    case ChestplateVariant.IRON      => Material.IRON_CHESTPLATE
    case ChestplateVariant.GOLD      => Material.GOLDEN_CHESTPLATE
    case ChestplateVariant.DIAMOND   => Material.DIAMOND_CHESTPLATE

    // CHICKEN
    case ChickenVariant.RAW    => Material.CHICKEN
    case ChickenVariant.COOKED => Material.COOKED_CHICKEN

    // CHORUS_FRUIT
    case ChorusFruitVariant.NORMAL => Material.CHORUS_FRUIT
    case ChorusFruitVariant.POPPED => Material.POPPED_CHORUS_FRUIT

    // COD
    case CodVariant.RAW    => Material.COD
    case CodVariant.COOKED => Material.COOKED_COD

    // HELMET
    case HelmetVariant.LEATHER   => Material.LEATHER_HELMET
    case HelmetVariant.CHAINMAIL => Material.CHAINMAIL_HELMET
    case HelmetVariant.IRON      => Material.IRON_HELMET
    case HelmetVariant.GOLD      => Material.GOLDEN_HELMET
    case HelmetVariant.DIAMOND   => Material.DIAMOND_HELMET

    // HOE
    case HoeVariant.WOOD    => Material.WOODEN_HOE
    case HoeVariant.STONE   => Material.STONE_HOE
    case HoeVariant.IRON    => Material.IRON_HOE
    case HoeVariant.GOLD    => Material.GOLDEN_HOE
    case HoeVariant.DIAMOND => Material.DIAMOND_HOE

    // HORSE_ARMOR
    case HorseArmorVariant.LEATHER => Material.LEATHER_HORSE_ARMOR
    case HorseArmorVariant.IRON    => Material.IRON_HORSE_ARMOR
    case HorseArmorVariant.GOLD    => Material.GOLDEN_HORSE_ARMOR
    case HorseArmorVariant.DIAMOND => Material.DIAMOND_HORSE_ARMOR

    // LEGGINGS
    case LeggingsVariant.LEATHER   => Material.LEATHER_LEGGINGS
    case LeggingsVariant.CHAINMAIL => Material.CHAINMAIL_LEGGINGS
    case LeggingsVariant.IRON      => Material.IRON_LEGGINGS
    case LeggingsVariant.GOLD      => Material.GOLDEN_LEGGINGS
    case LeggingsVariant.DIAMOND   => Material.DIAMOND_LEGGINGS

    // MINECART
    case MinecartVariant.EMPTY => Material.MINECART

    case MinecartVariant.CHEST         => Material.CHEST_MINECART
    case MinecartVariant.COMMAND_BLOCK => Material.COMMAND_BLOCK_MINECART
    case MinecartVariant.FURNACE       => Material.FURNACE_MINECART
    case MinecartVariant.HOPPER        => Material.HOPPER_MINECART
    case MinecartVariant.TNT           => Material.TNT_MINECART

    // MUSIC_DISC
    case MusicDiscVariant.DISC_11      => Material.MUSIC_DISC_11
    case MusicDiscVariant.DISC_13      => Material.MUSIC_DISC_13
    case MusicDiscVariant.DISC_BLOCKS  => Material.MUSIC_DISC_BLOCKS
    case MusicDiscVariant.DISC_CAT     => Material.MUSIC_DISC_CAT
    case MusicDiscVariant.DISC_CHIRP   => Material.MUSIC_DISC_CHIRP
    case MusicDiscVariant.DISC_FAR     => Material.MUSIC_DISC_FAR
    case MusicDiscVariant.DISC_MALL    => Material.MUSIC_DISC_MALL
    case MusicDiscVariant.DISC_MELLOHI => Material.MUSIC_DISC_MELLOHI
    case MusicDiscVariant.DISC_STAL    => Material.MUSIC_DISC_STAL
    case MusicDiscVariant.DISC_STRAD   => Material.MUSIC_DISC_STRAD
    case MusicDiscVariant.DISC_WAIT    => Material.MUSIC_DISC_WAIT
    case MusicDiscVariant.DISC_WARD    => Material.MUSIC_DISC_WARD

    // MUTTON
    case MuttonVariant.RAW    => Material.MUTTON
    case MuttonVariant.COOKED => Material.COOKED_MUTTON

    // PICKAXE
    case PickaxeVariant.WOOD    => Material.WOODEN_PICKAXE
    case PickaxeVariant.STONE   => Material.STONE_PICKAXE
    case PickaxeVariant.IRON    => Material.IRON_PICKAXE
    case PickaxeVariant.GOLD    => Material.GOLDEN_PICKAXE
    case PickaxeVariant.DIAMOND => Material.DIAMOND_PICKAXE

    // PORKCHOP
    case PorkchopVariant.RAW    => Material.PORKCHOP
    case PorkchopVariant.COOKED => Material.COOKED_PORKCHOP

    // POTATO
    case PotatoVariant.NORMAL    => Material.POTATO
    case PotatoVariant.BAKED     => Material.BAKED_POTATO
    case PotatoVariant.POISONOUS => Material.POISONOUS_POTATO

    // POTION
    case PotionVariant.NORMAL    => Material.POTION
    case PotionVariant.LINGERING => Material.LINGERING_POTION
    case PotionVariant.SPLASH    => Material.SPLASH_POTION

    // RABBIT
    case RabbitVariant.RAW    => Material.RABBIT
    case RabbitVariant.COOKED => Material.COOKED_RABBIT

    // SALMON
    case SalmonVariant.RAW    => Material.SALMON
    case SalmonVariant.COOKED => Material.COOKED_SALMON

    // SEEDS
    case SeedsVariant.BEETROOT => Material.BEETROOT_SEEDS
    case SeedsVariant.MELON    => Material.MELON_SEEDS
    case SeedsVariant.PUMPKIN  => Material.PUMPKIN_SEEDS
    case SeedsVariant.WHEAT    => Material.WHEAT_SEEDS

    // SHOVEL
    case ShovelVariant.WOOD    => Material.WOODEN_SHOVEL
    case ShovelVariant.STONE   => Material.STONE_SHOVEL
    case ShovelVariant.IRON    => Material.IRON_SHOVEL
    case ShovelVariant.GOLD    => Material.GOLDEN_SHOVEL
    case ShovelVariant.DIAMOND => Material.DIAMOND_SHOVEL

    // SPAWN_EGG
    case SpawnEggVariant.BAT              => Material.BAT_SPAWN_EGG
    case SpawnEggVariant.BLAZE            => Material.BLAZE_SPAWN_EGG
    case SpawnEggVariant.CAT              => Material.CAT_SPAWN_EGG
    case SpawnEggVariant.CAVE_SPIDER      => Material.CAVE_SPIDER_SPAWN_EGG
    case SpawnEggVariant.CHICKEN          => Material.CHICKEN_SPAWN_EGG
    case SpawnEggVariant.COD              => Material.COD_SPAWN_EGG
    case SpawnEggVariant.COW              => Material.COW_SPAWN_EGG
    case SpawnEggVariant.CREEPER          => Material.CREEPER_SPAWN_EGG
    case SpawnEggVariant.DOLPHIN          => Material.DOLPHIN_SPAWN_EGG
    case SpawnEggVariant.DONKEY           => Material.DONKEY_SPAWN_EGG
    case SpawnEggVariant.DROWNED          => Material.DROWNED_SPAWN_EGG
    case SpawnEggVariant.ELDER_GUARDIAN   => Material.ELDER_GUARDIAN_SPAWN_EGG
    case SpawnEggVariant.ENDERMAN         => Material.ENDERMAN_SPAWN_EGG
    case SpawnEggVariant.ENDERMITE        => Material.ENDERMITE_SPAWN_EGG
    case SpawnEggVariant.EVOKER           => Material.EVOKER_SPAWN_EGG
    case SpawnEggVariant.FOX              => Material.FOX_SPAWN_EGG
    case SpawnEggVariant.GHAST            => Material.GHAST_SPAWN_EGG
    case SpawnEggVariant.GUARDIAN         => Material.GUARDIAN_SPAWN_EGG
    case SpawnEggVariant.HORSE            => Material.HORSE_SPAWN_EGG
    case SpawnEggVariant.HUSK             => Material.HUSK_SPAWN_EGG
    case SpawnEggVariant.LLAMA            => Material.LLAMA_SPAWN_EGG
    case SpawnEggVariant.MAGMA_CUBE       => Material.MAGMA_CUBE_SPAWN_EGG
    case SpawnEggVariant.MOOSHROOM        => Material.MOOSHROOM_SPAWN_EGG
    case SpawnEggVariant.MULE             => Material.MULE_SPAWN_EGG
    case SpawnEggVariant.OCELOT           => Material.OCELOT_SPAWN_EGG
    case SpawnEggVariant.PANDA            => Material.PANDA_SPAWN_EGG
    case SpawnEggVariant.PARROT           => Material.PARROT_SPAWN_EGG
    case SpawnEggVariant.PHANTOM          => Material.PHANTOM_SPAWN_EGG
    case SpawnEggVariant.PIG              => Material.PIG_SPAWN_EGG
    case SpawnEggVariant.PILLAGER         => Material.PILLAGER_SPAWN_EGG
    case SpawnEggVariant.POLAR_BEAR       => Material.POLAR_BEAR_SPAWN_EGG
    case SpawnEggVariant.PUFFERFISH       => Material.PUFFERFISH_SPAWN_EGG
    case SpawnEggVariant.RABBIT           => Material.RABBIT_SPAWN_EGG
    case SpawnEggVariant.RAVAGER          => Material.RAVAGER_SPAWN_EGG
    case SpawnEggVariant.SALMON           => Material.SALMON_SPAWN_EGG
    case SpawnEggVariant.SHEEP            => Material.SHEEP_SPAWN_EGG
    case SpawnEggVariant.SHULKER          => Material.SHULKER_SPAWN_EGG
    case SpawnEggVariant.SILVERFISH       => Material.SILVERFISH_SPAWN_EGG
    case SpawnEggVariant.SKELETON_HORSE   => Material.SKELETON_HORSE_SPAWN_EGG
    case SpawnEggVariant.SKELETON         => Material.SKELETON_SPAWN_EGG
    case SpawnEggVariant.SLIME            => Material.SLIME_SPAWN_EGG
    case SpawnEggVariant.SPIDER           => Material.SPIDER_SPAWN_EGG
    case SpawnEggVariant.SQUID            => Material.SQUID_SPAWN_EGG
    case SpawnEggVariant.STRAY            => Material.STRAY_SPAWN_EGG
    case SpawnEggVariant.TRADER_LLAMA     => Material.TRADER_LLAMA_SPAWN_EGG
    case SpawnEggVariant.TROPICAL_FISH    => Material.TROPICAL_FISH_SPAWN_EGG
    case SpawnEggVariant.TURTLE           => Material.TURTLE_SPAWN_EGG
    case SpawnEggVariant.VEX              => Material.VEX_SPAWN_EGG
    case SpawnEggVariant.VILLAGER         => Material.VILLAGER_SPAWN_EGG
    case SpawnEggVariant.VINDICATOR       => Material.VINDICATOR_SPAWN_EGG
    case SpawnEggVariant.WANDERING_TRADER => Material.WANDERING_TRADER_SPAWN_EGG
    case SpawnEggVariant.WITCH            => Material.WITCH_SPAWN_EGG
    case SpawnEggVariant.WITHER_SKELETON  => Material.WITHER_SKELETON_SPAWN_EGG
    case SpawnEggVariant.WOLF             => Material.WOLF_SPAWN_EGG
    case SpawnEggVariant.ZOMBIE_HORSE     => Material.ZOMBIE_HORSE_SPAWN_EGG
    case SpawnEggVariant.ZOMBIE_PIGMAN    => Material.ZOMBIE_PIGMAN_SPAWN_EGG
    case SpawnEggVariant.ZOMBIE           => Material.ZOMBIE_SPAWN_EGG
    case SpawnEggVariant.ZOMBIE_VILLAGER  => Material.ZOMBIE_VILLAGER_SPAWN_EGG

    // SPIDER_EYE
    case SpiderEyeVariant.NORMAL    => Material.SPIDER_EYE
    case SpiderEyeVariant.FERMENTED => Material.FERMENTED_SPIDER_EYE

    // STEW
    case StewVariant.BEETROOT   => Material.BEETROOT_SOUP
    case StewVariant.MUSHROOM   => Material.MUSHROOM_STEW
    case StewVariant.RABBIT     => Material.RABBIT_STEW
    case StewVariant.SUSPICIOUS => Material.SUSPICIOUS_STEW

    // STRUCTURE_BLOCK
    case StructureBlockVariant.BLOCK => Material.STRUCTURE_BLOCK
    case StructureBlockVariant.VOID  => Material.STRUCTURE_VOID

    // SWORD
    case SwordVariant.WOOD    => Material.WOODEN_SWORD
    case SwordVariant.STONE   => Material.STONE_SWORD
    case SwordVariant.IRON    => Material.IRON_SWORD
    case SwordVariant.GOLD    => Material.GOLDEN_SWORD
    case SwordVariant.DIAMOND => Material.DIAMOND_SWORD

    case it: BlockVariant => blockMapper.map(it)
  }

  def map(item: VariableItem[_ <: ItemVariant]): Material = item match {
    // TODO map item specifics

    case it => map(it.variant)
  }
}
