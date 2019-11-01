package gg.warcraft.monolith.app.entity.event;

import gg.warcraft.monolith.api.entity.EntityType;
import gg.warcraft.monolith.api.entity.event.AbstractEntityEvent;
import gg.warcraft.monolith.api.entity.event.EntityDeathEvent;
import gg.warcraft.monolith.api.world.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SimpleEntityDeathEvent extends AbstractEntityEvent implements EntityDeathEvent {
    private List<Item> drops;

    public SimpleEntityDeathEvent(UUID entityId, EntityType entityType, List<Item> drops) {
        super(entityId, entityType);
        this.drops = drops;
    }

    @Override
    public List<Item> getDrops() {
        return new ArrayList<>(drops);
    }

    @Override
    public void setDrops(List<Item> drops) {
        this.drops = new ArrayList<>(drops);
    }
}
