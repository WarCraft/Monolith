package gg.warcraft.monolith.app.world.block.event;

import gg.warcraft.monolith.api.world.block.Block;
import gg.warcraft.monolith.api.world.block.BlockFace;
import gg.warcraft.monolith.api.world.block.event.AbstractBlockPreEvent;
import gg.warcraft.monolith.api.world.block.event.BlockInteraction;
import gg.warcraft.monolith.api.world.block.event.BlockPreInteractEvent;
import gg.warcraft.monolith.api.world.item.Item;

import java.util.UUID;

public class SimpleBlockPreInteractEvent extends AbstractBlockPreEvent implements BlockPreInteractEvent {
    private final BlockInteraction interaction;
    private final BlockFace face;
    private final Item itemInHand;
    private final UUID playerId;

    public SimpleBlockPreInteractEvent(Block block, BlockInteraction interaction, BlockFace face, Item itemInHand,
                                       UUID playerId, boolean cancelled) {
        super(block, cancelled);
        this.interaction = interaction;
        this.face = face;
        this.itemInHand = itemInHand;
        this.playerId = playerId;
    }

    @Override
    public BlockInteraction getInteraction() {
        return interaction;
    }

    @Override
    public BlockFace getFace() {
        return face;
    }

    @Override
    public Item getItemInHand() {
        return itemInHand;
    }

    @Override
    public UUID getPlayerId() {
        return playerId;
    }
}
