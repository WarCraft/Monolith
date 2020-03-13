package gg.warcraft.monolith.app.entity;

import gg.warcraft.monolith.api.entity.EntityIntersection;
import gg.warcraft.monolith.api.entity.EntityTarget;
import gg.warcraft.monolith.api.block.BlockIntersection;

public class SimpleEntityTarget implements EntityTarget {
    private final BlockIntersection blockIntersection;
    private final EntityIntersection entityIntersection;

    public SimpleEntityTarget(BlockIntersection blockIntersection, EntityIntersection entityIntersection) {
        this.blockIntersection = blockIntersection;
        this.entityIntersection = entityIntersection;
    }

    @Override
    public BlockIntersection getBlockIntersection() {
        return blockIntersection;
    }

    @Override
    public EntityIntersection getEntityIntersection() {
        return entityIntersection;
    }
}
