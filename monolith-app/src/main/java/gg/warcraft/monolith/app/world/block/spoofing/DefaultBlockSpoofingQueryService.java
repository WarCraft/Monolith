package gg.warcraft.monolith.app.world.block.spoofing;

import com.google.inject.Inject;
import gg.warcraft.monolith.api.world.BlockLocation;
import gg.warcraft.monolith.api.world.block.Block;
import gg.warcraft.monolith.api.world.block.spoofing.BlockSpoofingQueryService;
import gg.warcraft.monolith.api.world.block.spoofing.BlockSpoofingRepository;

import java.util.List;
import java.util.UUID;

public class DefaultBlockSpoofingQueryService implements BlockSpoofingQueryService {
    private final BlockSpoofingRepository blockSpoofingRepository;

    @Inject
    public DefaultBlockSpoofingQueryService(BlockSpoofingRepository blockSpoofingRepository) {
        this.blockSpoofingRepository = blockSpoofingRepository;
    }

    @Override
    public Block getSpoofedBlock(BlockLocation location, UUID playerId) {
        return blockSpoofingRepository.getSpoofedBlock(location, playerId);
    }

    @Override
    public List<Block> getSpoofedBlocks(UUID playerId) {
        return blockSpoofingRepository.getSpoofedBlocks(playerId);
    }
}
