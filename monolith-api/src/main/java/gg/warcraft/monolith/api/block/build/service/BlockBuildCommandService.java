package gg.warcraft.monolith.api.block.build.service;

/**
 * This service is injectable.
 * <p>
 * The BlockBuildCommandService serves as a point of entry into the build module implementation. It provides a method to
 * initialize all {@code BlockBuild}s within the boundaries of the configured repository on the server.
 */
public interface BlockBuildCommandService {

    /**
     * Initializes all builds within the boundaries of the configured repository on the server.
     */
    void initializeBuilds();
}
