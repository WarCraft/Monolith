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

public enum CoralBlockVariant implements BlockVariant, ItemVariant {
    BRAIN(true, false, false, false, false, false),
    BUBBLE(false, true, false, false, false, false),
    FIRE(false, false, true, false, false, false),
    HORN(false, false, false, true, false, false),
    TUBE(false, false, false, false, true, false),

    DEAD_BRAIN(true, false, false, false, false, true),
    DEAD_BUBBLE(false, true, false, false, false, true),
    DEAD_FIRE(false, false, true, false, false, true),
    DEAD_HORN(false, false, false, true, false, true),
    DEAD_TUBE(false, false, false, false, true, true);

    public final boolean brain;
    public final boolean bubble;
    public final boolean fire;
    public final boolean horn;
    public final boolean tube;
    public final boolean dead;

    CoralBlockVariant(
            boolean brain,
            boolean bubble,
            boolean fire,
            boolean horn,
            boolean tube,
            boolean dead
    ) {
        this.brain = brain;
        this.bubble = bubble;
        this.fire = fire;
        this.horn = horn;
        this.tube = tube;
        this.dead = dead;
    }
}
