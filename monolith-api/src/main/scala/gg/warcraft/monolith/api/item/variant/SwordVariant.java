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

package gg.warcraft.monolith.api.item.variant;

import gg.warcraft.monolith.api.item.ItemVariant;

public enum SwordVariant implements ItemVariant {
    WOOD(true, false, false, false, false, false),
    STONE(false, true, false, false, false, false),
    IRON(false, false, true, false, false, false),
    GOLD(false, false, false, true, false, false),
    DIAMOND(false, false, false, false, true, false),
    NETHERITE(false, false, false, false, false, true);

    public final boolean wood;
    public final boolean stone;
    public final boolean iron;
    public final boolean gold;
    public final boolean diamond;
    public final boolean netherite;

    SwordVariant(
            boolean wood,
            boolean stone,
            boolean iron,
            boolean gold,
            boolean diamond,
            boolean netherite
    ) {
        this.wood = wood;
        this.stone = stone;
        this.iron = iron;
        this.gold = gold;
        this.diamond = diamond;
        this.netherite = netherite;
    }
}
