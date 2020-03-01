package gg.warcraft.monolith.app.world.block.build.service;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import gg.warcraft.monolith.api.core.PluginLogger;
import gg.warcraft.monolith.api.math.Vector3i;
import gg.warcraft.monolith.api.world.BlockLocation;
import gg.warcraft.monolith.api.world.World;
import gg.warcraft.monolith.api.world.WorldService;
import gg.warcraft.monolith.api.world.block.*;
import gg.warcraft.monolith.api.world.block.box.BlockBox;
import gg.warcraft.monolith.api.world.block.box.BlockBoxReader;
import gg.warcraft.monolith.api.world.block.build.BlockBuild;
import gg.warcraft.monolith.api.world.block.build.service.BlockBuildCommandService;
import gg.warcraft.monolith.api.world.block.build.service.BlockBuildRepository;
import gg.warcraft.monolith.app.world.block.build.SimpleBlockBuild;
import scala.collection.immutable.LazyList;
import scala.jdk.javaapi.CollectionConverters;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class DefaultBlockBuildCommandService implements BlockBuildCommandService {
    private final WorldService worldService;
    private final BlockBuildRepository buildRepository;
    private final BlockUtils blockUtils;
    private final Logger logger;
    private final BlockBox buildRepositoryBoundingBox;

    @Inject
    public DefaultBlockBuildCommandService(WorldService worldService,
                                           BlockBuildRepository buildRepository,
                                           BlockUtils blockUtils,
                                           @PluginLogger Logger logger, @Named("BuildRepositoryWorld") World world,
                                           @Named("BuildRepositoryMinimumCorner") Vector3i minimumCorner,
                                           @Named("BuildRepositoryMaximumCorner") Vector3i maximumCorner) {
        this.worldService = worldService;
        this.buildRepository = buildRepository;
        this.blockUtils = blockUtils;
        this.logger = logger;
        this.buildRepositoryBoundingBox = new BlockBox(world, minimumCorner, maximumCorner);
    }

    int findMinMaxCoordinate(Set<Block> blocks, Function<Block, Integer> coordinate, BinaryOperator<Integer> reducer) {
        return blocks.stream()
                .map(coordinate)
                .reduce(reducer)
                .orElseThrow(IllegalStateException::new);
    }

    Set<Block> searchGlassFoundation(Sign sign) {
        Block attachedTo;
        switch (sign.direction().get()) {
            case NORTH:
                BlockLocation attachedLocation = sign.location().add(0, 0, 1);
                attachedTo = worldService.getBlock(attachedLocation);
                break;
            case EAST:
                BlockLocation attachedLocation2 = sign.location().add(-1, 0, 0);
                attachedTo = worldService.getBlock(attachedLocation2);
                break;
            case WEST:
                BlockLocation attachedLocation4 = sign.location().add(1, 0, 0);
                attachedTo = worldService.getBlock(attachedLocation4);
                break;
            case SOUTH:
            default:
                BlockLocation attachedLocation3 = sign.location().add(0, 0, -1);
                attachedTo = worldService.getBlock(attachedLocation3);
                break;
        }
        if (attachedTo.type() != BlockType.GLASS) {
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
                    .filter(block -> block.type() == BlockType.GLASS)
                    .filter(block -> !glassBlocks.contains(block))
                    .collect(Collectors.toSet());
            if (newGlassBlocks.isEmpty()) {
                searching = false;
            }
            glassBlocks.addAll(newGlassBlocks);
        }
        return glassBlocks;
    }

    BlockBox getBuildBoundingBox(Sign sign) {
        Set<Block> glassBlocks = searchGlassFoundation(sign);
        if (glassBlocks.isEmpty()) {
            return null;
        }

        World world = buildRepositoryBoundingBox.world();
        int minX = findMinMaxCoordinate(glassBlocks, block -> block.location().x(), Integer::min);
        int maxX = findMinMaxCoordinate(glassBlocks, block -> block.location().x(), Integer::max);
        int minZ = findMinMaxCoordinate(glassBlocks, block -> block.location().z(), Integer::min);
        int maxZ = findMinMaxCoordinate(glassBlocks, block -> block.location().z(), Integer::max);

        int minY = sign.location().y() + 1;
        int maxY = findMinMaxCoordinate(glassBlocks, block -> {
            Block highestBlock = worldService.getHighestBlock(block.location());
            return highestBlock.location().y();
        }, Integer::max);

        Vector3i minimumCorner = new Vector3i(minX, minY, minZ);
        Vector3i maximumCorner = new Vector3i(maxX, maxY, maxZ);
        return new BlockBox(world, minimumCorner, maximumCorner);
    }

    BlockBuild initializeBuild(Sign sign) {
        String firstLine = sign.lines().head();
        if (firstLine.isEmpty()) {
            // sign is extra data for other build
            return null;
        }

        Preconditions.checkArgument(firstLine.contains(":"), "Encountered build sign with illegal type and model. " +
                "All bottom level wall mounted signs in the build repository need to have the build type and model " +
                "specified on the first sign line as type:model.");

        String[] typeAndModel = firstLine.split(":");
        String type = typeAndModel[0];
        String model = typeAndModel[1];

        BlockBox boundingBox = getBuildBoundingBox(sign);
        if (boundingBox == null) {
            return null;
        }

        List<Sign> extraSigns = new ArrayList<>();
        Block nextSign = blockUtils.getRelative(sign, BlockFace.EAST);
        while (nextSign.type() == BlockType.SIGN &&
                ((Sign) nextSign).direction().isDefined()) {
            extraSigns.add((Sign) nextSign);
            nextSign = blockUtils.getRelative(nextSign, BlockFace.EAST);
        }

        List<String> allLines = new ArrayList<>(sign.getLines());
        extraSigns.forEach(s -> allLines.addAll(s.getLines()));
        return new SimpleBlockBuild(type, model, allLines.toArray(new String[0]), boundingBox);
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
        BlockBoxReader reader = new BlockBoxReader(buildRepositoryBoundingBox, BlockDirection.NORTH, worldService);
        LazyList<Block> floor = reader.sliceY(buildRepositoryBoundingBox.lower());
        List<BlockBuild> builds = CollectionConverters.asJava(floor
                .filter(block -> block.type() == BlockType.SIGN)
                .map(block -> (Sign) block)
                .filter(sign -> sign.direction().isDefined())
                .map(this::initializeBuild)
                .filter(Objects::nonNull));
        logInitializedBuilds(builds);
        builds.forEach(buildRepository::save);
    }
}
