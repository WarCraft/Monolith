package gg.warcraft.monolith.app.item;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import gg.warcraft.monolith.api.entity.attribute.Attribute;
import gg.warcraft.monolith.api.item.ItemReader;
import gg.warcraft.monolith.api.util.StringUtils;
import gg.warcraft.monolith.api.world.item.Item;

import javax.annotation.Nullable;

public class SimpleItemReader implements ItemReader {
    private final StringUtils stringUtils;
    private final Item item;

    @Inject
    public SimpleItemReader(StringUtils stringUtils,
                            @Assisted @Nullable Item item) {
        this.stringUtils = stringUtils;
        this.item = item;
    }

    @Override
    public String getType() {
        if (item == null) {
            return null;
        }

        String[] lore = item.tooltip();
        if (lore.length == 0) {
            return null;
        }

        if (lore.length == 1 || stringUtils.removeChatCodes(lore[1]).isEmpty()) {
            return stringUtils.removeChatCodes(lore[0]);
        }
        return null;
    }

    @Override
    public int getAttribute(Attribute attribute) {
        if (item == null) {
            return 0;
        }

        String[] lore = item.tooltip();
        for (String line : lore) {
            if (line.contains(attribute.getName())) {
                String rawLine = stringUtils.removeChatCodes(line);
                String onlyNumbers = rawLine.replaceAll("[\\D]", "");
                try {
                    return Integer.parseInt(onlyNumbers);
                } catch (NumberFormatException ex) {
                    return 0;
                }
            }
        }
        return 0;
    }
}
