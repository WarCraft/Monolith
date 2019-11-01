package gg.warcraft.monolith.app.world.block.event;

import gg.warcraft.monolith.api.world.block.Block;
import gg.warcraft.monolith.api.world.block.BlockFace;
import gg.warcraft.monolith.api.world.block.event.AbstractBlockEvent;
import gg.warcraft.monolith.api.world.block.event.BlockInteractEvent;
import gg.warcraft.monolith.api.world.block.event.BlockInteraction;
import gg.warcraft.monolith.api.world.item.Item;

import java.util.UUID;

public class SimpleBlockInteractEvent extends AbstractBlockEvent implements BlockInteractEvent {
    private final BlockInteraction interaction;
    private final BlockFace face;
    private final Item itemInHand;
    private final UUID playerId;

    public SimpleBlockInteractEvent(Block block, BlockInteraction interaction, BlockFace face, Item itemInHand,
                                    UUID playerId) {
        super(block);
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
