package gg.warcraft.monolith.api.entity.event;

import gg.warcraft.monolith.api.world.item.Item;

import java.util.List;

public interface EntityDeathEvent extends EntityEvent {

    List<Item> getDrops();

    void setDrops(List<Item> drops);
}
