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

package gg.warcraft.monolith.api.world;

import gg.warcraft.monolith.api.math.Vector3f;

public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    public static Direction fromYaw(float yaw) {
        if (yaw > 180) yaw -= 360;
        if (yaw < -180) yaw += 360;
        if (yaw >= -45) {
            if (yaw <= 45) return Direction.SOUTH;
            else if (yaw <= 135) return Direction.WEST;
            else return Direction.NORTH;
        } else {
            if (yaw >= -135) return Direction.EAST;
            else return Direction.NORTH;
        }
    }

    public int getRotation(Direction to) {
        int fromOrdinal = ordinal();
        int toOrdinal = to.ordinal();
        int ordinalDiff = toOrdinal - fromOrdinal;
        while (ordinalDiff < 0) ordinalDiff += 4;
        return ordinalDiff * 90;
    }

    public Direction rotate(int rotation) {
        if (rotation % 90 != 0) {
            throw new IllegalArgumentException("Illegal rotation of " + rotation + ", needs to be a multiple of 90");
        }
        int adjustedRotation = rotation % 360;
        int ordinalDifference = adjustedRotation / 90;
        int startingOrdinal = ordinal();
        int targetOrdinal = startingOrdinal + ordinalDifference;
        int adjustedOrdinal = targetOrdinal % 4;
        return Direction.values()[adjustedOrdinal];
    }

    public Vector3f toVector3f() {
        switch (this) {
            case NORTH:
                return new Vector3f(0, 0, -1);
            case EAST:
                return new Vector3f(1, 0, 0);
            case SOUTH:
                return new Vector3f(0, 0, 1);
            case WEST:
                return new Vector3f(-1, 0, 0);
            default:
                throw new IllegalArgumentException();
        }
    }
}
