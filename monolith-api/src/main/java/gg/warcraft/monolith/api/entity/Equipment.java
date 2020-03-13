package gg.warcraft.monolith.api.entity;

import gg.warcraft.monolith.api.item.Item;

public interface Equipment {

    /**
     * @param slot The equipment slot.
     * @return The item currently worn in the equipment slot. Can be null.
     */
    Item getSlot(EquipmentSlot slot);
}
