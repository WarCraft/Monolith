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

package gg.warcraft.monolith.spigot.item

import gg.warcraft.monolith.api.item.ItemType
import org.bukkit.Material

import java.util

private object SpigotItemTypeMapper {
  private final val cache: util.EnumMap[ItemType, Material] =
    new util.EnumMap(classOf[ItemType])
}

class SpigotItemTypeMapper {
  def map(`type`: ItemType): Material =
    SpigotItemTypeMapper.cache.computeIfAbsent(`type`, compute)

  private def compute(`type`: ItemType): Material = `type` match {
    case ItemType.BOTTLE_OF_ENCHANTING => Material.EXPERIENCE_BOTTLE
    case ItemType.BRICKS_BLOCK         => Material.BRICKS
    case ItemType.CLAY                 => Material.CLAY_BALL
    case ItemType.CLAY_BLOCK           => Material.CLAY
    case ItemType.DISC_FRAGMENT        => Material.DISC_FRAGMENT_5
    case ItemType.GOLDEN_MELON_SLICE   => Material.GLISTERING_MELON_SLICE
    case ItemType.HAY_BALE             => Material.HAY_BLOCK
    case ItemType.INFESTED_BLOCK       => Material.INFESTED_STONE
    case ItemType.JACK_OF_THE_LANTERN  => Material.JACK_O_LANTERN
    case ItemType.JIGSAW_BLOCK         => Material.JIGSAW
    case ItemType.LAPIS                => Material.LAPIS_LAZULI
    case ItemType.LIGHT_BLOCK          => Material.LIGHT
    case ItemType.MUD_BRICK            => Material.MUD_BRICKS
    case ItemType.QUARTZ_ORE           => Material.NETHER_QUARTZ_ORE
    case ItemType.SLIMEBALL            => Material.SLIME_BALL
    case ItemType.BOOK_AND_QUILL       => Material.WRITABLE_BOOK

    // variable items with no clear default
    case ItemType.AXE                     => Material.WOODEN_AXE
    case ItemType.BANNER                  => Material.RED_BANNER
    case ItemType.BANNER_PATTERN          => Material.CREEPER_BANNER_PATTERN
    case ItemType.BED                     => Material.RED_BED
    case ItemType.BOAT                    => Material.OAK_BOAT
    case ItemType.BOOTS                   => Material.LEATHER_BOOTS
    case ItemType.BUTTON                  => Material.OAK_BUTTON
    case ItemType.CARPET                  => Material.RED_CARPET
    case ItemType.CHESTPLATE              => Material.LEATHER_CHESTPLATE
    case ItemType.CONCRETE                => Material.RED_CONCRETE
    case ItemType.CONCRETE_POWDER         => Material.RED_CONCRETE_POWDER
    case ItemType.CORAL                   => Material.BRAIN_CORAL
    case ItemType.CORAL_BLOCK             => Material.BRAIN_CORAL_BLOCK
    case ItemType.CORAL_FAN               => Material.BRAIN_CORAL_FAN
    case ItemType.DOOR                    => Material.OAK_DOOR
    case ItemType.DYE                     => Material.RED_DYE
    case ItemType.FENCE                   => Material.OAK_FENCE
    case ItemType.FLOWER                  => Material.POPPY
    case ItemType.FENCE_GATE              => Material.OAK_FENCE_GATE
    case ItemType.GLAZED_TERRACOTTA       => Material.RED_GLAZED_TERRACOTTA
    case ItemType.HELMET                  => Material.LEATHER_HELMET
    case ItemType.HOE                     => Material.WOODEN_HOE
    case ItemType.HORSE_ARMOR             => Material.LEATHER_HORSE_ARMOR
    case ItemType.LEAVES                  => Material.OAK_LEAVES
    case ItemType.LEGGINGS                => Material.LEATHER_LEGGINGS
    case ItemType.LOG                     => Material.OAK_LOG
    case ItemType.MOB_HEAD                => Material.CREEPER_HEAD
    case ItemType.MUSHROOM                => Material.RED_MUSHROOM
    case ItemType.MUSHROOM_BLOCK          => Material.RED_MUSHROOM_BLOCK
    case ItemType.MUSIC_DISC              => Material.MUSIC_DISC_CAT
    case ItemType.PICKAXE                 => Material.WOODEN_PICKAXE
    case ItemType.PILLAR                  => Material.QUARTZ_PILLAR
    case ItemType.PLANKS                  => Material.OAK_PLANKS
    case ItemType.PLANT                   => Material.LILAC
    case ItemType.PRESSURE_PLATE          => Material.OAK_PRESSURE_PLATE
    case ItemType.SAPLING                 => Material.OAK_SAPLING
    case ItemType.SEEDS                   => Material.WHEAT_SEEDS
    case ItemType.SHOVEL                  => Material.WOODEN_SHOVEL
    case ItemType.SIGN                    => Material.OAK_SIGN
    case ItemType.SLAB                    => Material.OAK_SLAB
    case ItemType.SPAWN_EGG               => Material.CAT_SPAWN_EGG
    case ItemType.STAIRS                  => Material.OAK_STAIRS
    case ItemType.STEW                    => Material.MUSHROOM_STEW
    case ItemType.SWORD                   => Material.WOODEN_SWORD
    case ItemType.TRAPDOOR                => Material.OAK_TRAPDOOR
    case ItemType.WALL                    => Material.COBBLESTONE_WALL
    case ItemType.WEIGHTED_PRESSURE_PLATE => Material.LIGHT_WEIGHTED_PRESSURE_PLATE
    case ItemType.WOOD                    => Material.OAK_WOOD
    case ItemType.WOOL                    => Material.RED_WOOL

    case it => Material.valueOf(it.name)
  }
}
