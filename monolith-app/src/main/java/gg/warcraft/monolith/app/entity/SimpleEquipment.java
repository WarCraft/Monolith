package gg.warcraft.monolith.app.entity;

import gg.warcraft.monolith.api.entity.Equipment;
import gg.warcraft.monolith.api.entity.EquipmentSlot;
import gg.warcraft.monolith.api.world.item.Item;

public class SimpleEquipment implements Equipment {
    private final Item head;
    private final Item chest;
    private final Item legs;
    private final Item feet;
    private final Item mainHand;
    private final Item offHand;

    public SimpleEquipment(Item head, Item chest, Item legs, Item feet, Item mainHand, Item offHand) {
        this.head = head;
        this.chest = chest;
        this.legs = legs;
        this.feet = feet;
        this.mainHand = mainHand;
        this.offHand = offHand;
    }

    @Override
    public Item getSlot(EquipmentSlot slot) {
        switch (slot) {
            case HEAD:
                return head;
            case CHEST:
                return chest;
            case LEGS:
                return legs;
            case FEET:
                return feet;
            case MAIN_HAND:
                return mainHand;
            case OFF_HAND:
                return offHand;
            default:
                throw new IllegalArgumentException("Failed to get equipment in illegal slot " + slot);
        }
    }
}
