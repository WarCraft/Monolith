package gg.warcraft.monolith.api.world.block;

import java.util.Set;

/**
 * This utility is injectable.
 * <p>
 * The BlockTypeUtils utility serves as a point of entry into the block implementation. It provides methods to get
 * block types by id and data values and to easily determine the family of a block type.
 */
public interface BlockTypeUtils {

    /**
     * @param id The type id.
     * @return The block type for the given id. Can be null.
     */
    BlockType getType(int id);

    /**
     * @param id   The type id.
     * @param data The type data.
     * @return The block type for the given id and data. Can be null.
     */
    BlockType getType(int id, int data);

    /**
     * @return A set of block types which are considered to be not solid.
     */
    Set<BlockType> getNonSolids();

    /**
     * @return A set of block types which are considered to be liquid.
     */
    Set<BlockType> getLiquids();

    /**
     * @param type The type.
     * @return True if the type is carpet, false otherwise.
     */
    boolean isCarpet(BlockType type);

    /**
     * @param type The type.
     * @return True if the type is concrete, false otherwise.
     */
    boolean isConcrete(BlockType type);

    /**
     * @param type The type.
     * @return True if the type is concrete powder, false otherwise.
     */
    boolean isConcretePowder(BlockType type);

    /**
     * @param type The type.
     * @return True if the type is a door, false otherwise.
     */
    boolean isDoor(BlockType type);

    /**
     * @param type The type.
     * @return True if the type is a double slab, false otherwise.
     */
    boolean isDoubleSlab(BlockType type);

    /**
     * @param type The type.
     * @return True if the type is a fence, false otherwise.
     */
    boolean isFence(BlockType type);

    /**
     * @param type The type.
     * @return True if the type is a fence gate, false otherwise.
     */
    boolean isFenceGate(BlockType type);

    /**
     * @param type The type.
     * @return True if the type is a flower, false otherwise.
     */
    boolean isFlower(BlockType type);

    /**
     * @param type The type.
     * @return True if the type is a glass pane, false otherwise.
     */
    boolean isGlassPane(BlockType type);

    /**
     * @param type The type.
     * @return True if the type is clay, false otherwise.
     */
    boolean isHardenedClay(BlockType type);

    /**
     * @param type The type.
     * @return True if the type is leaves, false otherwise.
     */
    boolean isLeaves(BlockType type);

    /**
     * @param type The type.
     * @return True if the type is a monster egg, false otherwise.
     */
    boolean isMonsterEgg(BlockType type);

    /**
     * @param type The type.
     * @return True if the type is ore, false otherwise.
     */
    boolean isOre(BlockType type);

    /**
     * @param type The type.
     * @return True if the type is a plank, false otherwise.
     */
    boolean isPlank(BlockType type);

    /**
     * @param type The type.
     * @return True if the type is a sapling, false otherwise.
     */
    boolean isSapling(BlockType type);

    /**
     * @param type The type.
     * @return True if the type is a shulker box, false otherwise.
     */
    boolean isShulkerBox(BlockType type);

    /**
     * @param type The type.
     * @return True if the type is a slab, false otherwise.
     */
    boolean isSlab(BlockType type);

    /**
     * @param type The type.
     * @return True if the type is stained glass, false otherwise.
     */
    boolean isStainedGlass(BlockType type);

    /**
     * @param type The type.
     * @return True if the type is stairs, false otherwise.
     */
    boolean isStairs(BlockType type);

    /**
     * @param type The type.
     * @return True if the type is terracotta, false otherwise.
     */
    boolean isTerracotta(BlockType type);

    /**
     * @param type The type.
     * @return True if the type is wheat, false otherwise.
     */
    boolean isWheat(BlockType type);

    /**
     * @param type The type.
     * @return True if the type is wood, false otherwise.
     */
    boolean isWood(BlockType type);

    /**
     * @param type The type.
     * @return True if the type is wool, false otherwise.
     */
    boolean isWool(BlockType type);
}
