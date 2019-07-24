package gg.warcraft.monolith.spigot.world.block;

import com.google.inject.Inject;
import gg.warcraft.monolith.api.world.BlockLocation;
import gg.warcraft.monolith.api.world.Direction;
import gg.warcraft.monolith.api.world.block.Block;
import gg.warcraft.monolith.api.world.block.BlockType;
import gg.warcraft.monolith.api.world.block.Hinge;
import gg.warcraft.monolith.app.world.block.*;
import gg.warcraft.monolith.spigot.world.SpigotDirectionMapper;
import gg.warcraft.monolith.spigot.world.SpigotLocationMapper;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.material.*;

public class SpigotBlockMapper {
    private final SpigotLocationMapper locationMapper;
    private final SpigotBlockTypeMapper blockTypeMapper;
    private final SpigotBlockFaceMapper blockFaceMapper;
    private final SpigotDirectionMapper directionMapper;

    @Inject
    public SpigotBlockMapper(SpigotLocationMapper locationMapper, SpigotBlockTypeMapper blockTypeMapper,
                             SpigotBlockFaceMapper blockFaceMapper, SpigotDirectionMapper directionMapper) {
        this.locationMapper = locationMapper;
        this.blockTypeMapper = blockTypeMapper;
        this.blockFaceMapper = blockFaceMapper;
        this.directionMapper = directionMapper;
    }

    public org.bukkit.block.Block map(Block block) {
        Location spigotLocation = locationMapper.map(block.getLocation());
        org.bukkit.block.Block spigotBlock = spigotLocation.getBlock();
        return spigotBlock;
    }

    public Block map(org.bukkit.block.Block block) {
        if (block == null) {
            return null;
        }

        BlockType type = blockTypeMapper.map(block.getType(), block.getData());
        BlockLocation location = locationMapper.map(block);
        Block mappedBlock;
        switch (block.getType()) {
            case CHEST:
                Chest chest = (Chest) block.getState().getData();
                Direction chestDirection = directionMapper.map(chest.getFacing());
                mappedBlock = new ChestBlock(type, location, chestDirection);
                break;
            case SANDSTONE_STAIRS:
            case SPRUCE_WOOD_STAIRS:
            case SMOOTH_STAIRS:
            case ACACIA_STAIRS:
            case BIRCH_WOOD_STAIRS:
            case BRICK_STAIRS:
            case COBBLESTONE_STAIRS:
            case DARK_OAK_STAIRS:
            case JUNGLE_WOOD_STAIRS:
            case NETHER_BRICK_STAIRS:
            case WOOD_STAIRS:
            case PURPUR_STAIRS:
            case QUARTZ_STAIRS:
            case RED_SANDSTONE_STAIRS:
                Stairs stairs = (Stairs) block.getState().getData();
                Direction stairsDirection = directionMapper.map(stairs.getFacing());
                boolean inverted = stairs.isInverted();
                mappedBlock = new StairsBlock(type, location, stairsDirection, inverted);
                break;
            case SIGN_POST:
            case WALL_SIGN:
                Sign sign = (Sign) block.getState();
                org.bukkit.material.Sign signMaterial = (org.bukkit.material.Sign) sign.getData();
                Direction facing = directionMapper.map(signMaterial.getFacing());
                org.bukkit.block.Block spigotAttachedBlock = block.getRelative(signMaterial.getAttachedFace());
                Block attachedBlock = map(spigotAttachedBlock);
                String[] lines = sign.getLines();
                mappedBlock = new SignBlock(type, location, facing, attachedBlock, lines);
                break;
            case PURPUR_SLAB:
            case STONE_SLAB2:
            case STEP:
                Step step = (Step) block.getState().getData();
                boolean isTop = step.isInverted();
                mappedBlock = new SlabBlock(type, location, isTop, !isTop);
                break;
            case WOOD_STEP:
                WoodenStep woodenStep = (WoodenStep) block.getState().getData();
                boolean woodIsTop = woodenStep.isInverted();
                mappedBlock = new SlabBlock(type, location, woodIsTop, !woodIsTop);
                break;
            case TRAP_DOOR:
            case IRON_TRAPDOOR:
                TrapDoor trapDoor = (TrapDoor) block.getState().getData();
                Direction trapdoorFacing = directionMapper.map(trapDoor.getFacing());
                mappedBlock = new TrapdoorBlock(type, location, trapdoorFacing, trapDoor.isInverted(), trapDoor.isOpen());
                break;
            case LEVER:
                Lever lever = (Lever) block.getState().getData();
                Direction leverFacing = directionMapper.map(lever.getFacing());
                mappedBlock = new LeverBlock(type, location, leverFacing, lever.isPowered());
                break;
            case ACACIA_FENCE_GATE:
            case BIRCH_FENCE_GATE:
            case DARK_OAK_FENCE_GATE:
            case JUNGLE_FENCE_GATE:
            case FENCE_GATE:
            case SPRUCE_FENCE_GATE:
                Gate gate = (Gate) block.getState().getData();
                Direction gateFacing = directionMapper.map(gate.getFacing());
                mappedBlock = new GateBlock(type, location, gateFacing, gate.isOpen());
                break;
            case PISTON_BASE:
            case PISTON_STICKY_BASE:
                PistonBaseMaterial pistonBase = (PistonBaseMaterial) block.getState().getData();
                Direction pistonFacing = directionMapper.map(pistonBase.getFacing());
                mappedBlock = new PistonBlock(type, location, pistonFacing, pistonBase.isPowered(), pistonBase.isSticky());
                break;
            case DARK_OAK_DOOR:
            case ACACIA_DOOR:
            case BIRCH_DOOR:
            case JUNGLE_DOOR:
            case SPRUCE_DOOR:
            case WOODEN_DOOR:
            case IRON_DOOR_BLOCK:
                Door door = (Door) block.getState().getData();
                Direction doorFacing = directionMapper.map(door.getFacing());
                Hinge hinge = door.getHinge() ? Hinge.RIGHT : Hinge.LEFT;
                mappedBlock = new DoorBlock(type, location, doorFacing, hinge, door.isTopHalf(), door.isOpen());
                break;
            default:
                org.bukkit.material.MaterialData blockData = block.getState().getData();
                if (blockData instanceof Directional) {
                    Directional spigotDirection = (Directional) blockData;
                    Direction direction = directionMapper.map(spigotDirection.getFacing());
                    mappedBlock = new SimpleDirectionalBlock(type, location, direction);
                    break;
                }
                mappedBlock = new SimpleBlock(type, location);
                break;
        }

        return mappedBlock;
    }
}
