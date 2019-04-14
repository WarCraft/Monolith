package gg.warcraft.monolith.app.world.service;

import com.google.inject.Inject;
import gg.warcraft.monolith.api.world.World;
import gg.warcraft.monolith.api.world.block.Block;
import gg.warcraft.monolith.api.world.location.BlockLocation;
import gg.warcraft.monolith.api.world.location.Location;
import gg.warcraft.monolith.api.world.service.WorldQueryService;
import gg.warcraft.monolith.api.world.service.WorldServerAdapter;
import org.joml.Vector3ic;

public class DefaultWorldQueryService implements WorldQueryService {
    private final WorldServerAdapter serverAdapter;

    @Inject
    public DefaultWorldQueryService(WorldServerAdapter serverAdapter) {
        this.serverAdapter = serverAdapter;
    }

    @Override
    public World getWorld(World type) {
        return serverAdapter.getWorld(type);
    }

    @Override
    public Block getBlockAt(World world, int x, int y, int z) {
        return serverAdapter.getBlockAt(world, x, y, z);
    }

    @Override
    public Block getBlockAt(World world, Vector3ic vector) {
        return serverAdapter.getBlockAt(world, vector.x(), vector.y(), vector.z());
    }

    @Override
    public Block getBlockAt(BlockLocation location) {
        return serverAdapter.getBlockAt(location.getWorld(),
                location.getX(), location.getY(), location.getZ());
    }

    @Override
    public Block getBlockAt(Location location) {
        return getBlockAt(location.toBlockLocation());
    }

    @Override
    public Block getHighestBlockAt(World world, int x, int z) {
        return serverAdapter.getHighestBlockAt(world, x, z);
    }

    @Override
    public Block getHighestBlockAt(BlockLocation location) {
        World world = location.getWorld();
        return serverAdapter.getHighestBlockAt(world, location.getX(), location.getZ());
    }
}
