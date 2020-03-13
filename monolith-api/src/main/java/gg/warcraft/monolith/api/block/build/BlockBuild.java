package gg.warcraft.monolith.api.block.build;

import gg.warcraft.monolith.api.block.box.BlockBox;

/**
 * A BlockBuild represents a specifically arranged collection of blocks on the server. It is basically a {@code
 * BoundingBlockBox} with some extra data.
 */
public interface BlockBuild {

    /**
     * @return The id of this build, which is a combination of its type and model. Never null or empty.
     */
    String getId();

    /**
     * @return The type of this build. Never null or empty.
     */
    String getType();

    /**
     * @return The model of this build. Never null or empty.
     */
    String getModel();

    /**
     * @return A copy of the sign text of this build. The first element will be the build type and model. All other
     * lines are completely up to you. Never null or empty. Items are never null, but can be empty.
     */
    String[] getData();

    /**
     * @return The bounding box of this build. Never null.
     */
    BlockBox getBoundingBox();
}
