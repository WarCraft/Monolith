package gg.warcraft.monolith.spigot.world.location;

import gg.warcraft.monolith.api.world.location.BlockLocation;
import gg.warcraft.monolith.api.world.location.Location;
import gg.warcraft.monolith.api.world.location.OrientedLocation;

public interface SpigotLocationMapper {

    org.bukkit.Location map(Location location);

    org.bukkit.Location map(BlockLocation location);

    OrientedLocation map(org.bukkit.Location location);

    BlockLocation map(org.bukkit.block.Block block);
}
