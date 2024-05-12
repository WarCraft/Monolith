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

package gg.warcraft.monolith.api.block.variant;

import gg.warcraft.monolith.api.block.BlockVariant;
import gg.warcraft.monolith.api.item.ItemVariant;

// TODO make WaxableBlock base trait? also for signs

// TODO split some of these into separate classes: StoneStairs, CopperStairs, PrismarineStairs etc
// TODO consider Item implementation, might not make sense to split up
public enum StairsVariant implements BlockVariant, ItemVariant {
    // BLACKSTONE
    BLACKSTONE,
    POLISHED_BLACKSTONE,
    POLISHED_BLACKSTONE_BRICK,

    // BRICKS
    BRICKS,
    NETHER_BRICKS,
    RED_NETHER_BRICKS,

    // COBBLESTONE
    COBBLESTONE,
    MOSSY_COBBLESTONE,

    // COPPER
    CUT_COPPER,
    EXPOSED_CUT_COPPER,
    WEATHERED_CUT_COPPER,
    OXIDIZED_CUT_COPPER,

    WAXED_CUT_COPPER,
    WAXED_EXPOSED_CUT_COPPER,
    WAXED_WEATHERED_CUT_COPPER,
    WAXED_OXIDIZED_CUT_COPPER,

    // DEEPSLATE
    DEEPSLATE_BRICK,
    DEEPSLATE_BRICKS,
    COBBLED_DEEPSLATE,
    POLISHED_DEEPSLATE,

    // END_STONE_BRICK
    END_STONE_BRICK,

    // FUNGI
    CRIMSON_FUNGI,
    WARPED_FUNGI,

    // MUD
    MUD_BRICK,

    // PRISMARINE
    PRISMARINE,
    PRISMARINE_BRICK,
    DARK_PRISMARINE,

    // PURPUR
    PURPUR,

    // QUARTZ
    QUARTZ,
    SMOOTH_QUARTZ,

    // SANDSTONE
    SANDSTONE,
    SMOOTH_SANDSTONE,

    RED_SANDSTONE,
    SMOOTH_RED_SANDSTONE,

    // STONE
    STONE,

    // STONE_BRICK
    STONE_BRICK,
    MOSSY_STONE_BRICK,

    // STONITE
    ANDESITE,
    POLISHED_ANDESITE,

    DIORITE,
    POLISHED_DIORITE,

    GRANITE,
    POLISHED_GRANITE,

    // WOOD
    ACACIA,
    BIRCH,
    CHERRY,
    DARK_OAK,
    JUNGLE,
    MANGROVE,
    OAK,
    SPRUCE,

    BAMBOO,
    BAMBOO_MOSAIC
}
