package gg.warcraft.monolith.api.world.item;

public enum ItemFlag {
    HIDE_ATTRIBUTES, // ArmorItem and ToolItem -> also opens option of protected wood / iron / diamond durability levels for tool
    HIDE_DESTROYS, // ?
    HIDE_ENCHANTS, // EnchantableItem
    HIDE_PLACED_ON, // ?
    HIDE_POTION_EFFECTS, // Potion
    HIDE_UNBREAKABLE, // DurableItem
}
