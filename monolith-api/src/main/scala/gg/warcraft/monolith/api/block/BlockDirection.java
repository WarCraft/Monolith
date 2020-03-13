package gg.warcraft.monolith.api.block;

public enum BlockDirection {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    public static BlockDirection fromYaw(float yaw) {
        if (yaw > 180) yaw -= 360;
        if (yaw < -180) yaw += 360;
        if (yaw >= -45) {
            if (yaw <= 45) return BlockDirection.SOUTH;
            else if (yaw <= 135) return BlockDirection.WEST;
            else return BlockDirection.NORTH;
        } else {
            if (yaw >= -135) return BlockDirection.EAST;
            else return BlockDirection.NORTH;
        }
    }

    int getRotation(BlockDirection from, BlockDirection to) {
        int fromOrdinal = from.ordinal();
        int toOrdinal = to.ordinal();
        int ordinalDiff = toOrdinal - fromOrdinal;
        while (ordinalDiff < 0) ordinalDiff += 4;
        return ordinalDiff * 90;
    }

    BlockDirection rotate(BlockDirection direction, int rotation) {
        if (rotation % 90 != 0) {
            throw new IllegalArgumentException("Illegal rotation of " + rotation + ", needs to be a multiple of 90");
        }
        int adjustedRotation = rotation % 360;
        int ordinalDifference = adjustedRotation / 90;
        int startingOrdinal = direction.ordinal();
        int targetOrdinal = startingOrdinal + ordinalDifference;
        int adjustedOrdinal = targetOrdinal % 4;
        return BlockDirection.values()[adjustedOrdinal];
    }
}
