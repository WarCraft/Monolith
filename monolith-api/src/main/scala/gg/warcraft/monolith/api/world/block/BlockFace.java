package gg.warcraft.monolith.api.world.block;

import gg.warcraft.monolith.api.math.Vector3f;

public enum BlockFace {
    NORTH,
    EAST,
    SOUTH,
    WEST,
    UP,
    DOWN;

    BlockFace getOpposite() {
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

    Vector3f toVector() {
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
