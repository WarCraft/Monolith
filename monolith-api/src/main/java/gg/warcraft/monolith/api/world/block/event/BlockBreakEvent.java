package gg.warcraft.monolith.api.world.block.event;

import gg.warcraft.monolith.api.world.item.Item;

import java.util.List;
import java.util.UUID;

public interface BlockBreakEvent extends BlockEvent {

    List<Item> getAlternativeDrops();

    UUID getPlayerId();
}
