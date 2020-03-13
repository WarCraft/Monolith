package gg.warcraft.monolith.api.block.build.service;

import gg.warcraft.monolith.api.block.build.BlockBuild;

import java.util.List;

/**
 * This service is injectable.
 * <p>
 * The BlockBuildQueryService serves as a point of entry into the build implementation. It provides methods to query the
 * Monolith domain for a {@code BlockBuild} by type and model.
 */
public interface BlockBuildQueryService {

    /**
     * @param type The type. Can not be null or empty.
     * @return All builds of the given type. Never null, but can be empty. Items are never null.
     */
    List<BlockBuild> getBuilds(String type);

    /**
     * @param type  The type. Can not be null or empty.
     * @param model The model. Can not be null or empty.
     * @return The build of the given type and model. Can be null.
     */
    BlockBuild getBuild(String type, String model);
}
