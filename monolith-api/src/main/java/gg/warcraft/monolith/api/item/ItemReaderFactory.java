package gg.warcraft.monolith.api.item;

import gg.warcraft.monolith.api.item.Item;

public interface ItemReaderFactory {

    ItemReader createItemReader(Item item);
}
