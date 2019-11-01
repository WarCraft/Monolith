package gg.warcraft.monolith.spigot.world.item

import gg.warcraft.monolith.api.world.item.ItemType
import org.bukkit.Material

class SpigotItemTypeMapper {
  def map(`type`: ItemType): Material = {
    case ItemType.AXE                     => Material.WOODEN_AXE
    case ItemType.BANNER                  => Material.BLACK_BANNER
    case ItemType.BANNER_PATTERN          => Material.CREEPER_BANNER_PATTERN
    case ItemType.BED                     => Material.RED_BED
    case ItemType.BOAT                    => Material.OAK_BOAT
    case ItemType.BOOK_AND_QUILL          => Material.WRITABLE_BOOK
    case ItemType.BOOTS                   => Material.LEATHER_BOOTS
    case ItemType.BOTTLE_OF_ENCHANTING    => Material.EXPERIENCE_BOTTLE
    case ItemType.BRICK_BLOCK             => Material.BRICKS
    case ItemType.BUTTON                  => Material.OAK_BUTTON
    case ItemType.CARPET                  => Material.BLACK_CARPET
    case ItemType.CHESTPLATE              => Material.LEATHER_CHESTPLATE
    case ItemType.CLAY                    => Material.CLAY_BALL
    case ItemType.CLAY_BLOCK              => Material.CLAY
    case ItemType.CONCRETE                => Material.BLACK_CONCRETE
    case ItemType.CONCRETE_POWDER         => Material.BLACK_CONCRETE_POWDER
    case ItemType.CORAL                   => Material.BRAIN_CORAL
    case ItemType.CORAL_BLOCK             => Material.BRAIN_CORAL_BLOCK
    case ItemType.CORAL_FAN               => Material.BRAIN_CORAL_FAN
    case ItemType.DOOR                    => Material.OAK_DOOR
    case ItemType.DYE                     => Material.BLACK_DYE
    case ItemType.FENCE                   => Material.OAK_FENCE
    case ItemType.FLOWER                  => Material.POPPY
    case ItemType.GATE                    => Material.OAK_FENCE_GATE
    case ItemType.GLAZED_TERRACOTTA       => Material.BLACK_GLAZED_TERRACOTTA
    case ItemType.GOLDEN_MELON_SLICE      => Material.GLISTERING_MELON_SLICE
    case ItemType.HELMET                  => Material.LEATHER_HELMET
    case ItemType.HOE                     => Material.WOODEN_HOE
    case ItemType.HORSE_ARMOR             => Material.LEATHER_HORSE_ARMOR
    case ItemType.INFESTED_BLOCK          => Material.INFESTED_STONE
    case ItemType.JACK_OF_THE_LANTERN     => Material.JACK_O_LANTERN
    case ItemType.JIGSAW_BLOCK            => Material.JIGSAW
    case ItemType.LAPIS                   => Material.LAPIS_LAZULI
    case ItemType.LEAVES                  => Material.OAK_LEAVES
    case ItemType.LEGGINGS                => Material.LEATHER_LEGGINGS
    case ItemType.LOG                     => Material.OAK_LOG
    case ItemType.MOB_HEAD                => Material.ZOMBIE_HEAD
    case ItemType.MUSHROOM                => Material.RED_MUSHROOM
    case ItemType.MUSHROOM_BLOCK          => Material.RED_MUSHROOM_BLOCK
    case ItemType.MUSIC_DISC              => Material.MUSIC_DISC_CAT
    case ItemType.PICKAXE                 => Material.WOODEN_PICKAXE
    case ItemType.PILLAR                  => Material.QUARTZ_PILLAR
    case ItemType.PLANKS                  => Material.OAK_PLANKS
    case ItemType.PLANT                   => Material.LILAC
    case ItemType.PRESSURE_PLATE          => Material.OAK_PRESSURE_PLATE
    case ItemType.QUARTZ_ORE              => Material.NETHER_QUARTZ_ORE
    case ItemType.SAPLING                 => Material.OAK_SAPLING
    case ItemType.SEEDS                   => Material.WHEAT_SEEDS
    case ItemType.SHOVEL                  => Material.WOODEN_SHOVEL
    case ItemType.SIGN                    => Material.OAK_SIGN
    case ItemType.SLAB                    => Material.OAK_SLAB
    case ItemType.SLIMEBALL               => Material.SLIME_BALL
    case ItemType.SPAWN_EGG               => Material.CAT_SPAWN_EGG
    case ItemType.STAIRS                  => Material.OAK_STAIRS
    case ItemType.STEW                    => Material.MUSHROOM_STEW
    case ItemType.SWORD                   => Material.WOODEN_SWORD
    case ItemType.TRAPDOOR                => Material.OAK_TRAPDOOR
    case ItemType.WALL                    => Material.COBBLESTONE_WALL
    case ItemType.WEIGHTED_PRESSURE_PLATE => Material.LIGHT_WEIGHTED_PRESSURE_PLATE
    case ItemType.WOOD                    => Material.OAK_WOOD
    case ItemType.WOOL                    => Material.BLACK_WOOL

    case it: ItemType => Material.valueOf(it.name)
  }
}
