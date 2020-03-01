package gg.warcraft.monolith.app.world.block.build;

import gg.warcraft.monolith.api.world.block.box.BlockBox;
import gg.warcraft.monolith.api.world.block.build.AbstractBlockBuild;

public class SimpleBlockBuild extends AbstractBlockBuild {

    public SimpleBlockBuild(String type, String model, String[] data, BlockBox boundingBox) {
        super(type, model, data, boundingBox);
    }
}
