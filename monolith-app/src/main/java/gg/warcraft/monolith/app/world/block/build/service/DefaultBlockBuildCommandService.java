package gg.warcraft.monolith.app.world.block.build.service;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import gg.warcraft.monolith.api.core.PluginLogger;
import gg.warcraft.monolith.api.world.World;
import gg.warcraft.monolith.api.world.block.Block;
import gg.warcraft.monolith.api.world.block.BlockFace;
import gg.warcraft.monolith.api.world.block.BlockType;
import gg.warcraft.monolith.api.world.block.BlockUtils;
import gg.warcraft.monolith.api.world.block.Sign;
import gg.warcraft.monolith.api.world.block.box.BoundingBlockBox;
import gg.warcraft.monolith.api.world.block.box.BoundingBlockBoxFactory;
import gg.warcraft.monolith.api.world.block.build.BlockBuild;
import gg.warcraft.monolith.api.world.block.build.service.BlockBuildCommandService;
import gg.warcraft.monolith.api.world.block.build.service.BlockBuildRepository;
import gg.warcraft.monolith.api.world.service.WorldCommandService;
import gg.warcraft.monolith.api.world.service.WorldQueryService;
import gg.warcraft.monolith.app.world.block.build.SimpleBlockBuild;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DefaultBlockBuildCommandService implements BlockBuildCommandService {
    private final WorldQueryService worldQueryService;
    private final WorldCommandService worldCommandService;
    private final BlockBuildRepository buildRepository;
    private final BlockUtils blockUtils;
    private final Logger logger;
    private final BoundingBlockBoxFactory blockBoxFactory;
    private final BoundingBlockBox buildRepositoryBoundingBox;

    @Inject
    public DefaultBlockBuildCommandService(WorldQueryService worldQueryService, WorldCommandService worldCommandService,
                                           BlockBuildRepository buildRepository,
                                           BlockUtils blockUtils, BoundingBlockBoxFactory blockBoxFactory,
                                           @PluginLogger Logger logger, @Named("BuildRepositoryWorld") World world,
                                           @Named("BuildRepositoryMinimumCorner") Vector3ic minimumCorner,
                                           @Named("BuildRepositoryMaximumCorner") Vector3ic maximumCorner) {
        this.worldQueryService = worldQueryService;
        this.worldCommandService = worldCommandService;
        this.buildRepository = buildRepository;
        this.blockUtils = blockUtils;
        this.logger = logger;
        this.blockBoxFactory = blockBoxFactory;
        this.buildRepositoryBoundingBox = blockBoxFactory.createBoundingBlockBox(world, minimumCorner, maximumCorner);
    }

    int findMinMaxCoordinate(Set<Block> blocks, Function<Block, Integer> coordinate, BinaryOperator<Integer> reducer) {
        return blocks.stream()
                .map(coordinate)
                .reduce(reducer)
                .orElseThrow(IllegalStateException::new);
    }

    Set<Block> searchGlassFoundation(Sign sign) {
        Block attachedTo = sign.getAttachedTo();
        if (attachedTo.getType() != BlockType.GLASS) {
            return new HashSet<>();
        }

        boolean searching = true;
        Set<Block> searchedBlocks = new HashSet<>();
        Set<Block> glassBlocks = new HashSet<>();
        Set<Block> newGlassBlocks = new HashSet<>();
        newGlassBlocks.add(attachedTo);
        while (searching) {
            Set<Block> currentBlocks = newGlassBlocks.stream()
                    .flatMap(block -> blockUtils.getAdjacentBlocksXZ(block).stream())
                    .filter(block -> !searchedBlocks.contains(block))
                    .collect(Collectors.toSet());

            searchedBlocks.addAll(currentBlocks);

            newGlassBlocks = currentBlocks.stream()
                    .filter(block -> block.getType() == BlockType.GLASS)
                    .filter(block -> !glassBlocks.contains(block))
                    .collect(Collectors.toSet());
            if (newGlassBlocks.isEmpty()) {
                searching = false;
            }
            glassBlocks.addAll(newGlassBlocks);
        }
        return glassBlocks;
    }

    BoundingBlockBox getBuildBoundingBox(Sign sign) {
        Set<Block> glassBlocks = searchGlassFoundation(sign);
        if (glassBlocks.isEmpty()) {
            return null;
        }

        World world = buildRepositoryBoundingBox.getWorld();
        int minX = findMinMaxCoordinate(glassBlocks, block -> block.getLocation().getX(), Integer::min);
        int maxX = findMinMaxCoordinate(glassBlocks, block -> block.getLocation().getX(), Integer::max);
        int minZ = findMinMaxCoordinate(glassBlocks, block -> block.getLocation().getZ(), Integer::min);
        int maxZ = findMinMaxCoordinate(glassBlocks, block -> block.getLocation().getZ(), Integer::max);

        int minY = sign.getLocation().getY() + 1;
        int maxY = findMinMaxCoordinate(glassBlocks, block -> {
            Block highestBlock = worldQueryService.getHighestBlockAt(block.getLocation());
            return highestBlock.getLocation().getY();
        }, Integer::max);

        Vector3i minimumCorner = new Vector3i(minX, minY, minZ);
        Vector3i maximumCorner = new Vector3i(maxX, maxY, maxZ);
        return blockBoxFactory.createBoundingBlockBox(world, minimumCorner, maximumCorner);
    }

    BlockBuild initializeBuild(Sign sign) {
        String[] lines = sign.getLines();
        if (lines[0].isEmpty()) {
            // sign is extra data for other build
            return null;
        }

        Preconditions.checkArgument(lines[0].contains(":"), "Encountered build sign with illegal type and model. " +
                "All bottom level wall mounted signs in the build repository need to have the build type and model " +
                "specified on the first sign line as type:model.");

        String[] typeAndModel = lines[0].split(":");
        String type = typeAndModel[0];
        String model = typeAndModel[1];

        BoundingBlockBox boundingBox = getBuildBoundingBox(sign);
        if (boundingBox == null) {
            return null;
        }

        List<Sign> extraSigns = new ArrayList<>();
        Block nextSign = blockUtils.getRelative(sign, BlockFace.EAST);
        while (nextSign.getType() == BlockType.WALL_MOUNTED_SIGN) {
            extraSigns.add((Sign) nextSign);
            nextSign = blockUtils.getRelative(nextSign, BlockFace.EAST);
        }

        String[] extraLines = extraSigns.stream()
                .map(Sign::getLines)
                .flatMap(Stream::of)
                .toArray(String[]::new);

        String[] allLines = Stream.of(lines, extraLines)
                .flatMap(Stream::of)
                .toArray(String[]::new);

        return new SimpleBlockBuild(type, model, allLines, boundingBox);
    }

    void logInitializedBuilds(List<BlockBuild> builds) {
        Map<String, Integer> buildCountByType = new HashMap<>();
        builds.forEach(build -> {
            int count = buildCountByType.getOrDefault(build.getType(), 0);
            buildCountByType.put(build.getType(), count + 1);
        });
        StringBuilder buildCountByTypeStringBuilder = new StringBuilder();
        buildCountByType.forEach((type, count) -> buildCountByTypeStringBuilder
                .append(", ").append(count).append("x ").append(type));
        String buildCountByTypeString = buildCountByTypeStringBuilder.toString();
        logger.info("Initialized " + builds.size() + " builds in build repository" + buildCountByTypeString);
    }

    @Override
    public void initializeBuilds() {
        Stream<Block> floor = buildRepositoryBoundingBox.sliceY(buildRepositoryBoundingBox.getLowerBoundary());
        List<BlockBuild> builds = floor
                .filter(block -> block.getType() == BlockType.WALL_MOUNTED_SIGN)
                .map(block -> (Sign) block)
                .map(this::initializeBuild)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        logInitializedBuilds(builds);
        builds.forEach(buildRepository::save);
    }
}
