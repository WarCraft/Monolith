package gg.warcraft.monolith.api.menu;

import com.google.inject.assistedinject.Assisted;
import com.google.inject.name.Named;
import gg.warcraft.monolith.api.world.item.ItemTypeOrVariant;

public interface ButtonBuilderFactory {

    @Named("simple")
    ButtonBuilder createSimpleButtonBuilder(ItemTypeOrVariant icon, String title);

    @Named("skull")
    ButtonBuilder createSkullButtonBuilder(@Assisted("name") String skullName, @Assisted("title") String title);
}
