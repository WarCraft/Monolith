package gg.warcraft.monolith.app.world.service;

import com.google.inject.Inject;
import gg.warcraft.monolith.api.item.Item;
import gg.warcraft.monolith.api.math.Vector3f;
import gg.warcraft.monolith.api.world.Location;
import gg.warcraft.monolith.api.world.Sound;
import gg.warcraft.monolith.api.world.SoundCategory;
import gg.warcraft.monolith.api.world.block.Block;
import gg.warcraft.monolith.api.world.service.WorldCommandService;
import gg.warcraft.monolith.api.world.service.WorldServerAdapter;

import java.util.List;
import java.util.UUID;

public class DefaultWorldCommandService implements WorldCommandService {
    private final WorldServerAdapter worldServerAdapter;

    @Inject
    public DefaultWorldCommandService(WorldServerAdapter worldServerAdapter) {
        this.worldServerAdapter = worldServerAdapter;
    }

    @Override
    public void setBlock(Block block) {
        worldServerAdapter.updateBlock(block);
    }

    @Override
    public List<UUID> dropItemsAt(List<Item> items, Location location) {
        return worldServerAdapter.dropItemsAt(items, location);
    }

    @Override
    public void playSound(Location location, Sound sound, SoundCategory category, float volume, float pitch) {
        worldServerAdapter.playSound(location, sound, category, volume, pitch);
    }

    @Override
    public void strikeLightning(Location location, boolean ambient) {
        worldServerAdapter.strikeLightning(location, ambient);
    }

    @Override
    public void createExplosion(Location location, boolean ambient) {
        worldServerAdapter.createExplosion(location, ambient);
    }

    @Override
    public UUID createArrow(UUID shooterId, Location location, Vector3f direction, float speed, float spread) {
        return worldServerAdapter.createArrow(shooterId, location, direction, speed, spread);
    }
}
