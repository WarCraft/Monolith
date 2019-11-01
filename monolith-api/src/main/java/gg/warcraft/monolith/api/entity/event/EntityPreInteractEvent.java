package gg.warcraft.monolith.api.entity.event;

import gg.warcraft.monolith.api.world.Location;
import gg.warcraft.monolith.api.world.item.Item;

import java.util.UUID;

public interface EntityPreInteractEvent extends EntityPreEvent {

    UUID getPlayerId();

    Item getItemInHand();

    Location getInteractLocation();
}
