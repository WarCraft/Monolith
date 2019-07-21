package gg.warcraft.monolith.app.world.block.spoofing;

import com.google.inject.Inject;
import gg.warcraft.monolith.api.world.BlockLocation;
import gg.warcraft.monolith.api.world.block.Block;
import gg.warcraft.monolith.api.world.block.spoofing.BlockSpoofingCommandService;
import gg.warcraft.monolith.api.world.block.spoofing.BlockSpoofingRepository;
import gg.warcraft.monolith.api.world.service.WorldQueryService;
import gg.warcraft.monolith.api.world.service.WorldServerAdapter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DefaultBlockSpoofingCommandService implements BlockSpoofingCommandService {
    private final BlockSpoofingRepository blockSpoofingRepository;
    private final WorldQueryService worldQueryService;
    private final WorldServerAdapter worldServerAdapter;

    @Inject
    public DefaultBlockSpoofingCommandService(BlockSpoofingRepository blockSpoofingRepository,
                                              WorldQueryService worldQueryService,
                                              WorldServerAdapter worldServerAdapter) {
        this.blockSpoofingRepository = blockSpoofingRepository;
        this.worldQueryService = worldQueryService;
        this.worldServerAdapter = worldServerAdapter;
    }

    @Override
    public void spoofBlock(Block fakeBlock, UUID... playerIds) {
        Arrays.stream(playerIds).forEach(playerId -> {
            worldServerAdapter.spoofBlock(fakeBlock, playerId);
            blockSpoofingRepository.save(fakeBlock, playerId);
        });
    }

    @Override
    public void unspoofBlock(BlockLocation location, UUID... playerIds) {
        Block realBlock = worldQueryService.getBlockAt(location);
        Arrays.stream(playerIds).forEach(playerId -> {
            worldServerAdapter.spoofBlock(realBlock, playerId);
            blockSpoofingRepository.delete(location, playerId);
        });
    }

    @Override
    public void unspoofAll(UUID... playerIds) {
        Map<BlockLocation, Block> realBlocks = new HashMap<>();
        Arrays.stream(playerIds).forEach(playerId -> {
            List<Block> fakeBlocks = blockSpoofingRepository.getSpoofedBlocks(playerId);
            fakeBlocks.forEach(fakeBlock -> {
                BlockLocation location = fakeBlock.location();
                Block realBlock = realBlocks.computeIfAbsent(location, worldQueryService::getBlockAt);
                worldServerAdapter.spoofBlock(realBlock, playerId);
                blockSpoofingRepository.delete(location, playerId);
            });
        });
    }
}
