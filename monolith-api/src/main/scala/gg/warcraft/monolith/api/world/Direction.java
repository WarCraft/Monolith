package gg.warcraft.monolith.api.world;

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

    int getRotation(Direction to) {
        int fromOrdinal = ordinal();
        int toOrdinal = to.ordinal();
        int ordinalDiff = toOrdinal - fromOrdinal;
        while (ordinalDiff < 0) ordinalDiff += 4;
        return ordinalDiff * 90;
    }

    Direction rotate(int rotation) {
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
}
