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

public enum WoodVariant implements BlockVariant, ItemVariant {
    ACACIA(false),
    BIRCH(false),
    DARK_OAK(false),
    JUNGLE(false),
    OAK(false),
    SPRUCE(false),

    CRIMSON_FUNGI(false),
    WARPED_FUNGI(false),

    STRIPPED_ACACIA(true),
    STRIPPED_BIRCH(true),
    STRIPPED_DARK_OAK(true),
    STRIPPED_JUNGLE(true),
    STRIPPED_OAK(true),
    STRIPPED_SPRUCE(true),

    STRIPPED_CRIMSON_FUNGI(true),
    STRIPPED_WARPED_FUNGI(true);

    public final boolean stripped;

    WoodVariant(boolean stripped) {
        this.stripped = stripped;
    }
}
