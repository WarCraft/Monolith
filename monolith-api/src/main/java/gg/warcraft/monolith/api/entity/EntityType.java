package gg.warcraft.monolith.api.entity;

/**
 * EntityType serves as an abstraction layer between Monolith plugin code and the eventual server implementation the
 * plugin is run on. It is identical to Spigot's {@code EntityType} and the server adapter will substitute its
 * values with the correct implementation during runtime.
 */
public enum EntityType {
    DROPPED_ITEM,
    EXPERIENCE_ORB,
    AREA_EFFECT_CLOUD,
    ELDER_GUARDIAN,
    WITHER_SKELETON,
    STRAY,
    EGG,
    LEASH_HITCH,
    PAINTING,
    ARROW,
    SNOWBALL,
    FIREBALL,
    SMALL_FIREBALL,
    ENDER_PEARL,
    ENDER_SIGNAL,
    SPLASH_POTION,
    THROWN_EXP_BOTTLE,
    ITEM_FRAME,
    WITHER_SKULL,
    PRIMED_TNT,
    FALLING_BLOCK,
    FIREWORK,
    HUSK,
    SPECTRAL_ARROW,
    SHULKER_BULLET,
    DRAGON_FIREBALL,
    ZOMBIE_VILLAGER,
    SKELETON_HORSE,
    ZOMBIE_HORSE,
    ARMOR_STAND,
    DONKEY,
    MULE,
    EVOKER_FANGS,
    EVOKER,
    VEX,
    VINDICATOR,
    ILLUSIONER,
    MINECART_COMMAND,
    BOAT,
    MINECART,
    MINECART_CHEST,
    MINECART_FURNACE,
    MINECART_TNT,
    MINECART_HOPPER,
    MINECART_MOB_SPAWNER,
    CREEPER,
    SKELETON,
    SPIDER,
    GIANT,
    ZOMBIE,
    SLIME,
    GHAST,
    PIG_ZOMBIE,
    ENDERMAN,
    CAVE_SPIDER,
    SILVERFISH,
    BLAZE,
    MAGMA_CUBE,
    ENDER_DRAGON,
    WITHER,
    BAT,
    WITCH,
    ENDERMITE,
    GUARDIAN,
    SHULKER,
    PIG,
    SHEEP,
    COW,
    CHICKEN,
    SQUID,
    WOLF,
    MUSHROOM_COW,
    SNOWMAN,
    OCELOT,
    IRON_GOLEM,
    HORSE,
    RABBIT,
    POLAR_BEAR,
    LLAMA,
    LLAMA_SPIT,
    PARROT,
    VILLAGER,
    ENDER_CRYSTAL,
    TURTLE,
    PHANTOM,
    TRIDENT,
    COD,
    SALMON,
    PUFFERFISH,
    TROPICAL_FISH,
    DROWNED,
    DOLPHIN,
    CAT,
    PANDA,
    PILLAGER,
    RAVAGER,
    TRADER_LLAMA,
    WANDERING_TRADER,
    FOX,
    FISHING_HOOK,
    LIGHTNING,
    PLAYER,
}
