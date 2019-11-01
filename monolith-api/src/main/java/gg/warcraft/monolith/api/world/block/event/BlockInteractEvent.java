package gg.warcraft.monolith.api.world.block.event;

import gg.warcraft.monolith.api.world.block.BlockFace;
import gg.warcraft.monolith.api.world.item.Item;

import java.util.UUID;

public interface BlockInteractEvent extends BlockEvent {

    BlockInteraction getInteraction();

    BlockFace getFace();

    Item getItemInHand();

    UUID getPlayerId();
}
