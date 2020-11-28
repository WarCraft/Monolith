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

package gg.warcraft.monolith.api.block;

import gg.warcraft.monolith.api.math.Vector3f;

// TODO in Dotty reimpl this to extend a Direction interface and have individual
// TODO elements extends HorizontalDirection or VerticalDirection
public enum BlockFace {
    NORTH,
    EAST,
    SOUTH,
    WEST,
    UP,
    DOWN;

    public BlockFace getOpposite() {
        switch (this) {
            case NORTH:
                return BlockFace.SOUTH;
            case EAST:
                return BlockFace.WEST;
            case SOUTH:
                return BlockFace.NORTH;
            case WEST:
                return BlockFace.EAST;
            case UP:
                return BlockFace.DOWN;
            default:
                return BlockFace.UP;
        }
    }

    public Vector3f toVector() {
        switch (this) {
            case NORTH:
                return new Vector3f(0, 0, -1);
            case EAST:
                return new Vector3f(1, 0, 0);
            case SOUTH:
                return new Vector3f(0, 0, 1);
            case WEST:
                return new Vector3f(-1, 0, 0);
            case UP:
                return new Vector3f(0, 1, 0);
            default:
                return new Vector3f(0, -1, 0);
        }
    }
}
