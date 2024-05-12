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

package gg.warcraft.monolith.api.block.state;

import gg.warcraft.monolith.api.block.BlockState;

public enum CaveVinesState implements BlockState {
    AGE_0,
    AGE_1,
    AGE_2,
    AGE_3,
    AGE_4,
    AGE_5,
    AGE_6,
    AGE_7,
    AGE_8,
    AGE_9,
    AGE_10,
    AGE_11,
    AGE_12,
    AGE_13,
    AGE_14,
    AGE_15,
    AGE_16,
    AGE_17,
    AGE_18,
    AGE_19,
    AGE_20,
    AGE_21,
    AGE_22,
    AGE_23,
    AGE_24,
    AGE_25,

    FULLY_GROWN;

    private static final CaveVinesState[] finalValues = values();

    public static CaveVinesState valueOf(int data) {
        return finalValues[data];
    }

    @Override
    public int toInt() {
        return ordinal();
    }
}
