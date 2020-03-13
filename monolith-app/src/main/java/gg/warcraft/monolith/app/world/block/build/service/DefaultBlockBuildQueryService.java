package gg.warcraft.monolith.app.world.block.build.service;

import com.google.inject.Inject;
import gg.warcraft.monolith.api.block.build.BlockBuild;
import gg.warcraft.monolith.api.block.build.service.BlockBuildQueryService;
import gg.warcraft.monolith.api.block.build.service.BlockBuildRepository;

import java.util.List;

public class DefaultBlockBuildQueryService implements BlockBuildQueryService {
    private final BlockBuildRepository repository;

    @Inject
    public DefaultBlockBuildQueryService(BlockBuildRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<BlockBuild> getBuilds(String type) {
        return repository.getBuilds(type);
    }

    @Override
    public BlockBuild getBuild(String type, String model) {
        return repository.getBuild(type, model);
    }
}
