package gg.warcraft.monolith.api.entity.event;

import gg.warcraft.monolith.api.item.Item;
import gg.warcraft.monolith.api.world.Location;

import java.util.UUID;

public interface EntityPreInteractEvent extends EntityPreEvent {

    UUID getPlayerId();

    Item getItemInHand();

    Location getInteractLocation();
}
