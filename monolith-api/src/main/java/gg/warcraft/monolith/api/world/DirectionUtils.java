package gg.warcraft.monolith.api.world;

import gg.warcraft.monolith.api.math.Vector3f;
import gg.warcraft.monolith.api.world.block.BlockFace;

// TODO remove in favor of equivalent impl on BlockFace
public interface DirectionUtils {

    Direction getOpposite(Direction direction);
    Direction getOpposite(BlockFace direction);

    int getRotation(Direction from, Direction to);
    int getRotation(BlockFace from, BlockFace to);

    Direction rotate(Direction direction, int rotation);
    Direction rotate(BlockFace direction, int rotation);

    Direction toDirection(float yaw);

    Vector3f toVector(Direction direction);
    Vector3f toVector(BlockFace direction);
}
