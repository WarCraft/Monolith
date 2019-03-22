package gg.warcraft.monolith.app.item;

import gg.warcraft.monolith.api.item.ItemType;
import gg.warcraft.monolith.api.item.ItemTypeUtils;

import java.util.HashMap;
import java.util.Map;

public class DefaultItemTypeUtils implements ItemTypeUtils {
    private static final Map<String, ItemType> types = new HashMap<>();

    static {
        for (ItemType type : ItemType.values()) {
            String typeKey = type.getId() + ":" + type.getData();
            types.put(typeKey, type);
        }
    }

    @Override
    public ItemType getType(int id) {
        return getType(id, 0);
    }

    @Override
    public ItemType getType(int id, int data) {
        int adjustedData = data & 0x3; // TODO does this even apply here?
        ItemType type = types.get(id + ":" + adjustedData);
        if (type == null) {
            return types.get(id + ":0");
        }
        return type;
    }

    @Override
    public boolean isWool(ItemType type) {
        switch (type) {
            case WHITE_WOOL:
            case ORANGE_WOOL:
            case MAGENTA_WOOL:
            case LIGHT_BLUE_WOOL:
            case YELLOW_WOOL:
            case LIME_WOOL:
            case PINK_WOOL:
            case GRAY_WOOL:
            case LIGHT_GRAY_WOOL:
            case CYAN_WOOL:
            case PURPLE_WOOL:
            case BLUE_WOOL:
            case BROWN_WOOL:
            case GREEN_WOOL:
            case RED_WOOL:
            case BLACK_WOOL:
                return true;
            default:
                return false;
        }
    }
}
