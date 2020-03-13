package gg.warcraft.monolith.api.entity;

import gg.warcraft.monolith.api.block.BlockIntersection;

/**
 * An {@code EntityTarget} represents the block and or entity that are currently being targeted by a principal entity
 * given a certain range.
 * If not null, the returned entity intersection holds the closest entity the principal entity has targeted with their
 * crosshair. If the returned intersection is null then the principal entity is either not looking at another entity or
 * they were out of range.
 * If not null, the returned block intersection holds the first block the principal entity has targeted given a set of
 * block types that were ignored. If the returned intersection is null the principal entity is either looking into the
 * sky or the first block who's type was not ignored was out of range.
 * A block intersection will be calculated regardless of whether an entity is standing in front of it. If an entity is
 * between the principal entity and a block is within range both will be returned.
 */
public interface EntityTarget {

    /**
     * @return The block intersection that represents the block currently targeted by the entity. Can be null.
     */
    BlockIntersection getBlockIntersection();

    /**
     * @return The entity intersection that represents the entity currently targeted by the entity. Can be null.
     */
    EntityIntersection getEntityIntersection();
}
