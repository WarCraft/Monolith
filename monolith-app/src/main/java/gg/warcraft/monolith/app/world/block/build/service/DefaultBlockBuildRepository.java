package gg.warcraft.monolith.app.world.block.build.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import gg.warcraft.monolith.api.block.build.BlockBuild;
import gg.warcraft.monolith.api.block.build.service.BlockBuildRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Singleton
public class DefaultBlockBuildRepository implements BlockBuildRepository {
    private final HashMap<String, Set<BlockBuild>> buildsByType;
    private final HashMap<String, BlockBuild> buildsByTypeAndModel;

    @Inject
    public DefaultBlockBuildRepository() {
        this.buildsByType = new HashMap<>();
        this.buildsByTypeAndModel = new HashMap<>();
    }

    @Override
    public List<BlockBuild> getBuilds(String type) {
        Set<BlockBuild> builds = buildsByType.computeIfAbsent(type, key -> new HashSet<>());
        return new ArrayList<>(builds);
    }

    @Override
    public BlockBuild getBuild(String type, String model) {
        return buildsByTypeAndModel.get(type + ":" + model);
    }

    @Override
    public void save(BlockBuild build) {
        Set<BlockBuild> buildsOfType = buildsByType.computeIfAbsent(build.getType(), key -> new HashSet<>());
        buildsOfType.add(build);
        buildsByTypeAndModel.put(build.getType() + ":" + build.getModel(), build);
    }
}
