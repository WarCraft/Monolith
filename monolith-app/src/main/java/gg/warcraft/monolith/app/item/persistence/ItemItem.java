package gg.warcraft.monolith.app.item.persistence;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ItemItem {
    private final String type;
    private final String name;
    private final int stackSize;
    private final int damage;
    private final List<String> lore;

    @JsonCreator
    public ItemItem(@JsonProperty("type") String type,
                    @JsonProperty("name") String name,
                    @JsonProperty("stackSize") int stackSize,
                    @JsonProperty("damage") int damage,
                    @JsonProperty("lore") List<String> lore) {
        this.type = type;
        this.name = name;
        this.stackSize = stackSize;
        this.damage = damage;
        this.lore = lore;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getStackSize() {
        return stackSize;
    }

    public int getDamage() {
        return damage;
    }

    public List<String> getLore() {
        return lore;
    }
}
