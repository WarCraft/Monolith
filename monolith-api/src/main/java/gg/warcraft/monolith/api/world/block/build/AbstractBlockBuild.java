package gg.warcraft.monolith.api.world.block.build;

import gg.warcraft.monolith.api.world.block.box.BlockBox;

import java.util.Arrays;

public abstract class AbstractBlockBuild implements BlockBuild {
    private final String type;
    private final String model;
    private final String[] data;
    private final BlockBox boundingBox;

    public AbstractBlockBuild(String type, String model, String[] data, BlockBox boundingBox) {
        this.type = type;
        this.model = model;
        this.data = data;
        this.boundingBox = boundingBox;
    }

    @Override
    public String getId() {
        return type + ":" + model;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public String[] getData() {
        return Arrays.copyOf(data, data.length);
    }

    @Override
    public BlockBox getBoundingBox() {
        return boundingBox;
    }
}
