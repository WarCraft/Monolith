package gg.warcraft.monolith.spigot.world.item;

import gg.warcraft.monolith.api.world.item.ItemVariant;
import gg.warcraft.monolith.api.world.item.variant.*;
import gg.warcraft.monolith.spigot.world.block.SpigotBlockVariantMapper;
import org.bukkit.Material;

public class JSpigotItemVariantMapper extends SpigotItemVariantMapper {
    private final SpigotBlockVariantMapper blockMapper;

    public JSpigotItemVariantMapper(SpigotBlockVariantMapper blockMapper) {
        super(blockMapper);
        this.blockMapper = blockMapper;
    }

    @Override
    public ItemVariant map(Material material) {
        switch (material) {
            // ARROW
            case ARROW:
                return ArrowVariant.NORMAL;
            case SPECTRAL_ARROW:
                return ArrowVariant.SPECTRAL;
            case TIPPED_ARROW:
                return ArrowVariant.TIPPED;

            // AXE
            case WOODEN_AXE:
                return AxeVariant.WOOD;
            case STONE_AXE:
                return AxeVariant.STONE;
            case IRON_AXE:
                return AxeVariant.IRON;
            case GOLDEN_AXE:
                return AxeVariant.GOLD;
            case DIAMOND_AXE:
                return AxeVariant.DIAMOND;

            // BANNER_PATTERN
            case CREEPER_BANNER_PATTERN:
                return BannerPatternVariant.CREEPER;
            case FLOWER_BANNER_PATTERN:
                return BannerPatternVariant.FLOWER;
            case GLOBE_BANNER_PATTERN:
                return BannerPatternVariant.GLOBE;
            case MOJANG_BANNER_PATTERN:
                return BannerPatternVariant.MOJANG;
            case SKULL_BANNER_PATTERN:
                return BannerPatternVariant.SKULL;

            // BEEF
            case BEEF:
                return BeefVariant.RAW;
            case COOKED_BEEF:
                return BeefVariant.COOKED;

            // BOAT
            case ACACIA_BOAT:
                return BoatVariant.ACACIA;
            case BIRCH_BOAT:
                return BoatVariant.BIRCH;
            case DARK_OAK_BOAT:
                return BoatVariant.DARK_OAK;
            case JUNGLE_BOAT:
                return BoatVariant.JUNGLE;
            case OAK_BOAT:
                return BoatVariant.OAK;
            case SPRUCE_BOAT:
                return BoatVariant.SPRUCE;

            // BOOTS
            case LEATHER_BOOTS:
                return BootsVariant.LEATHER;
            case CHAINMAIL_BOOTS:
                return BootsVariant.CHAINMAIL;
            case IRON_BOOTS:
                return BootsVariant.IRON;
            case GOLDEN_BOOTS:
                return BootsVariant.GOLD;
            case DIAMOND_BOOTS:
                return BootsVariant.DIAMOND;

            // BRICK
            case BRICK:
                return BrickVariant.NORMAL;
            case NETHER_BRICK:
                return BrickVariant.NETHER;

            // BUCKET
            case BUCKET:
                return BucketVariant.EMPTY;

            case COD_BUCKET:
                return BucketVariant.COD;
            case PUFFERFISH_BUCKET:
                return BucketVariant.PUFFERFISH;
            case SALMON_BUCKET:
                return BucketVariant.SALMON;
            case TROPICAL_FISH_BUCKET:
                return BucketVariant.TROPICAL_FISH;

            case LAVA_BUCKET:
                return BucketVariant.LAVA;
            case MILK_BUCKET:
                return BucketVariant.MILK;
            case WATER_BUCKET:
                return BucketVariant.WATER;

            // CHESTPLATE
            case LEATHER_CHESTPLATE:
                return ChestplateVariant.LEATHER;
            case CHAINMAIL_CHESTPLATE:
                return ChestplateVariant.CHAINMAIL;
            case IRON_CHESTPLATE:
                return ChestplateVariant.IRON;
            case GOLDEN_CHESTPLATE:
                return ChestplateVariant.GOLD;
            case DIAMOND_CHESTPLATE:
                return ChestplateVariant.DIAMOND;

            // CHICKEN
            case CHICKEN:
                return ChickenVariant.RAW;
            case COOKED_CHICKEN:
                return ChickenVariant.COOKED;

            // CHORUS_FRUIT
            case CHORUS_FRUIT:
                return ChorusFruitVariant.NORMAL;
            case POPPED_CHORUS_FRUIT:
                return ChorusFruitVariant.POPPED;

            // COD
            case COD:
                return CodVariant.RAW;
            case COOKED_COD:
                return CodVariant.COOKED;

            // DYE
            case BLACK_DYE:
                return DyeVariant.BLACK;
            case BLUE_DYE:
                return DyeVariant.BLUE;
            case BROWN_DYE:
                return DyeVariant.BROWN;
            case CYAN_DYE:
                return DyeVariant.CYAN;
            case GRAY_DYE:
                return DyeVariant.GRAY;
            case GREEN_DYE:
                return DyeVariant.GREEN;
            case LIGHT_BLUE_DYE:
                return DyeVariant.LIGHT_BLUE;
            case LIGHT_GRAY_DYE:
                return DyeVariant.LIGHT_GRAY;
            case LIME_DYE:
                return DyeVariant.LIME;
            case MAGENTA_DYE:
                return DyeVariant.MAGENTA;
            case ORANGE_DYE:
                return DyeVariant.ORANGE;
            case PINK_DYE:
                return DyeVariant.PINK;
            case PURPLE_DYE:
                return DyeVariant.PURPLE;
            case RED_DYE:
                return DyeVariant.RED;
            case WHITE_DYE:
                return DyeVariant.WHITE;
            case YELLOW_DYE:
                return DyeVariant.YELLOW;

            // HELMET
            case LEATHER_HELMET:
                return HelmetVariant.LEATHER;
            case CHAINMAIL_HELMET:
                return HelmetVariant.CHAINMAIL;
            case IRON_HELMET:
                return HelmetVariant.IRON;
            case GOLDEN_HELMET:
                return HelmetVariant.GOLD;
            case DIAMOND_HELMET:
                return HelmetVariant.DIAMOND;

            // HOE
            case WOODEN_HOE:
                return HoeVariant.WOOD;
            case STONE_HOE:
                return HoeVariant.STONE;
            case IRON_HOE:
                return HoeVariant.IRON;
            case GOLDEN_HOE:
                return HoeVariant.GOLD;
            case DIAMOND_HOE:
                return HoeVariant.DIAMOND;

            // HORSE_ARMOR
            case LEATHER_HORSE_ARMOR:
                return HorseArmorVariant.LEATHER;
            case IRON_HORSE_ARMOR:
                return HorseArmorVariant.IRON;
            case GOLDEN_HORSE_ARMOR:
                return HorseArmorVariant.GOLD;
            case DIAMOND_HORSE_ARMOR:
                return HorseArmorVariant.DIAMOND;

            // LEGGINGS
            case LEATHER_LEGGINGS:
                return LeggingsVariant.LEATHER;
            case CHAINMAIL_LEGGINGS:
                return LeggingsVariant.CHAINMAIL;
            case IRON_LEGGINGS:
                return LeggingsVariant.IRON;
            case GOLDEN_LEGGINGS:
                return LeggingsVariant.GOLD;
            case DIAMOND_LEGGINGS:
                return LeggingsVariant.DIAMOND;

            // MINECART
            case MINECART:
                return MinecartVariant.EMPTY;

            case CHEST_MINECART:
                return MinecartVariant.CHEST;
            case COMMAND_BLOCK_MINECART:
                return MinecartVariant.COMMAND_BLOCK;
            case FURNACE_MINECART:
                return MinecartVariant.FURNACE;
            case HOPPER_MINECART:
                return MinecartVariant.HOPPER;
            case TNT_MINECART:
                return MinecartVariant.TNT;

            // MUSIC_DISC
            case MUSIC_DISC_11:
                return MusicDiscVariant.DISC_11;
            case MUSIC_DISC_13:
                return MusicDiscVariant.DISC_13;
            case MUSIC_DISC_BLOCKS:
                return MusicDiscVariant.DISC_BLOCKS;
            case MUSIC_DISC_CAT:
                return MusicDiscVariant.DISC_CAT;
            case MUSIC_DISC_CHIRP:
                return MusicDiscVariant.DISC_CHIRP;
            case MUSIC_DISC_FAR:
                return MusicDiscVariant.DISC_FAR;
            case MUSIC_DISC_MALL:
                return MusicDiscVariant.DISC_MALL;
            case MUSIC_DISC_MELLOHI:
                return MusicDiscVariant.DISC_MELLOHI;
            case MUSIC_DISC_STAL:
                return MusicDiscVariant.DISC_STAL;
            case MUSIC_DISC_STRAD:
                return MusicDiscVariant.DISC_STRAD;
            case MUSIC_DISC_WAIT:
                return MusicDiscVariant.DISC_WAIT;
            case MUSIC_DISC_WARD:
                return MusicDiscVariant.DISC_WARD;

            // MUTTON
            case MUTTON:
                return MuttonVariant.RAW;
            case COOKED_MUTTON:
                return MuttonVariant.COOKED;

            // PICKAXE
            case WOODEN_PICKAXE:
                return PickaxeVariant.WOOD;
            case STONE_PICKAXE:
                return PickaxeVariant.STONE;
            case IRON_PICKAXE:
                return PickaxeVariant.IRON;
            case GOLDEN_PICKAXE:
                return PickaxeVariant.GOLD;
            case DIAMOND_PICKAXE:
                return PickaxeVariant.DIAMOND;

            // PORKCHOP
            case PORKCHOP:
                return PorkchopVariant.RAW;
            case COOKED_PORKCHOP:
                return PorkchopVariant.COOKED;

            // POTATO
            case POTATO:
                return PotatoVariant.NORMAL;
            case BAKED_POTATO:
                return PotatoVariant.BAKED;
            case POISONOUS_POTATO:
                return PotatoVariant.POISONOUS;

            // POTION
            case POTION:
                return PotionVariant.NORMAL;
            case LINGERING_POTION:
                return PotionVariant.LINGERING;
            case SPLASH_POTION:
                return PotionVariant.SPLASH;

            // RABBIT
            case RABBIT:
                return RabbitVariant.RAW;
            case COOKED_RABBIT:
                return RabbitVariant.COOKED;

            // SALMON
            case SALMON:
                return SalmonVariant.RAW;
            case COOKED_SALMON:
                return SalmonVariant.COOKED;

            // SEEDS
            case BEETROOT_SEEDS:
                return SeedsVariant.BEETROOT;
            case MELON_SEEDS:
                return SeedsVariant.MELON;
            case PUMPKIN_SEEDS:
                return SeedsVariant.PUMPKIN;
            case WHEAT_SEEDS:
                return SeedsVariant.WHEAT;

            // SHOVEL
            case WOODEN_SHOVEL:
                return ShovelVariant.WOOD;
            case STONE_SHOVEL:
                return ShovelVariant.STONE;
            case IRON_SHOVEL:
                return ShovelVariant.IRON;
            case GOLDEN_SHOVEL:
                return ShovelVariant.GOLD;
            case DIAMOND_SHOVEL:
                return ShovelVariant.DIAMOND;

            // SPAWN_EGG
            case BAT_SPAWN_EGG:
                return SpawnEggVariant.BAT;
            case BLAZE_SPAWN_EGG:
                return SpawnEggVariant.BLAZE;
            case CAT_SPAWN_EGG:
                return SpawnEggVariant.CAT;
            case CAVE_SPIDER_SPAWN_EGG:
                return SpawnEggVariant.CAVE_SPIDER;
            case CHICKEN_SPAWN_EGG:
                return SpawnEggVariant.CHICKEN;
            case COD_SPAWN_EGG:
                return SpawnEggVariant.COD;
            case COW_SPAWN_EGG:
                return SpawnEggVariant.COW;
            case CREEPER_SPAWN_EGG:
                return SpawnEggVariant.CREEPER;
            case DOLPHIN_SPAWN_EGG:
                return SpawnEggVariant.DOLPHIN;
            case DONKEY_SPAWN_EGG:
                return SpawnEggVariant.DONKEY;
            case DROWNED_SPAWN_EGG:
                return SpawnEggVariant.DROWNED;
            case ELDER_GUARDIAN_SPAWN_EGG:
                return SpawnEggVariant.ELDER_GUARDIAN;
            case ENDERMAN_SPAWN_EGG:
                return SpawnEggVariant.ENDERMAN;
            case ENDERMITE_SPAWN_EGG:
                return SpawnEggVariant.ENDERMITE;
            case EVOKER_SPAWN_EGG:
                return SpawnEggVariant.EVOKER;
            case FOX_SPAWN_EGG:
                return SpawnEggVariant.FOX;
            case GHAST_SPAWN_EGG:
                return SpawnEggVariant.GHAST;
            case GUARDIAN_SPAWN_EGG:
                return SpawnEggVariant.GUARDIAN;
            case HORSE_SPAWN_EGG:
                return SpawnEggVariant.HORSE;
            case HUSK_SPAWN_EGG:
                return SpawnEggVariant.HUSK;
            case LLAMA_SPAWN_EGG:
                return SpawnEggVariant.LLAMA;
            case MAGMA_CUBE_SPAWN_EGG:
                return SpawnEggVariant.MAGMA_CUBE;
            case MOOSHROOM_SPAWN_EGG:
                return SpawnEggVariant.MOOSHROOM;
            case MULE_SPAWN_EGG:
                return SpawnEggVariant.MULE;
            case OCELOT_SPAWN_EGG:
                return SpawnEggVariant.OCELOT;
            case PANDA_SPAWN_EGG:
                return SpawnEggVariant.PANDA;
            case PARROT_SPAWN_EGG:
                return SpawnEggVariant.PARROT;
            case PHANTOM_SPAWN_EGG:
                return SpawnEggVariant.PHANTOM;
            case PIG_SPAWN_EGG:
                return SpawnEggVariant.PIG;
            case PILLAGER_SPAWN_EGG:
                return SpawnEggVariant.PILLAGER;
            case POLAR_BEAR_SPAWN_EGG:
                return SpawnEggVariant.POLAR_BEAR;
            case PUFFERFISH_SPAWN_EGG:
                return SpawnEggVariant.PUFFERFISH;
            case RABBIT_SPAWN_EGG:
                return SpawnEggVariant.RABBIT;
            case RAVAGER_SPAWN_EGG:
                return SpawnEggVariant.RAVAGER;
            case SALMON_SPAWN_EGG:
                return SpawnEggVariant.SALMON;
            case SHEEP_SPAWN_EGG:
                return SpawnEggVariant.SHEEP;
            case SHULKER_SPAWN_EGG:
                return SpawnEggVariant.SHULKER;
            case SILVERFISH_SPAWN_EGG:
                return SpawnEggVariant.SILVERFISH;
            case SKELETON_HORSE_SPAWN_EGG:
                return SpawnEggVariant.SKELETON_HORSE;
            case SKELETON_SPAWN_EGG:
                return SpawnEggVariant.SKELETON;
            case SLIME_SPAWN_EGG:
                return SpawnEggVariant.SLIME;
            case SPIDER_SPAWN_EGG:
                return SpawnEggVariant.SPIDER;
            case SQUID_SPAWN_EGG:
                return SpawnEggVariant.SQUID;
            case STRAY_SPAWN_EGG:
                return SpawnEggVariant.STRAY;
            case TRADER_LLAMA_SPAWN_EGG:
                return SpawnEggVariant.TRADER_LLAMA;
            case TROPICAL_FISH_SPAWN_EGG:
                return SpawnEggVariant.TROPICAL_FISH;
            case TURTLE_SPAWN_EGG:
                return SpawnEggVariant.TURTLE;
            case VEX_SPAWN_EGG:
                return SpawnEggVariant.VEX;
            case VILLAGER_SPAWN_EGG:
                return SpawnEggVariant.VILLAGER;
            case VINDICATOR_SPAWN_EGG:
                return SpawnEggVariant.VINDICATOR;
            case WANDERING_TRADER_SPAWN_EGG:
                return SpawnEggVariant.WANDERING_TRADER;
            case WITCH_SPAWN_EGG:
                return SpawnEggVariant.WITCH;
            case WITHER_SKELETON_SPAWN_EGG:
                return SpawnEggVariant.WITHER_SKELETON;
            case WOLF_SPAWN_EGG:
                return SpawnEggVariant.WOLF;
            case ZOMBIE_HORSE_SPAWN_EGG:
                return SpawnEggVariant.ZOMBIE_HORSE;
            case ZOMBIE_PIGMAN_SPAWN_EGG:
                return SpawnEggVariant.ZOMBIE_PIGMAN;
            case ZOMBIE_SPAWN_EGG:
                return SpawnEggVariant.ZOMBIE;
            case ZOMBIE_VILLAGER_SPAWN_EGG:
                return SpawnEggVariant.ZOMBIE_VILLAGER;

            // SPIDER_EYE
            case SPIDER_EYE:
                return SpiderEyeVariant.NORMAL;
            case FERMENTED_SPIDER_EYE:
                return SpiderEyeVariant.FERMENTED;

            // STEW
            case BEETROOT_SOUP:
                return StewVariant.BEETROOT;
            case MUSHROOM_STEW:
                return StewVariant.MUSHROOM;
            case RABBIT_STEW:
                return StewVariant.RABBIT;
            case SUSPICIOUS_STEW:
                return StewVariant.SUSPICIOUS;

            // STRUCTURE_BLOCK
            case STRUCTURE_BLOCK:
                return StructureBlockVariant.BLOCK;
            case STRUCTURE_VOID:
                return StructureBlockVariant.VOID;

            // SWORD
            case WOODEN_SWORD:
                return SwordVariant.WOOD;
            case STONE_SWORD:
                return SwordVariant.STONE;
            case IRON_SWORD:
                return SwordVariant.IRON;
            case GOLDEN_SWORD:
                return SwordVariant.GOLD;
            case DIAMOND_SWORD:
                return SwordVariant.DIAMOND;

            default:
                return (ItemVariant) blockMapper.map(material);
        }
    }
}
