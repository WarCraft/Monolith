package gg.warcraft.monolith.spigot.world.service;

import com.google.inject.Inject;
import gg.warcraft.monolith.api.item.Item;
import gg.warcraft.monolith.api.world.Direction;
import gg.warcraft.monolith.api.world.Sound;
import gg.warcraft.monolith.api.world.SoundCategory;
import gg.warcraft.monolith.api.world.WorldType;
import gg.warcraft.monolith.api.world.block.Block;
import gg.warcraft.monolith.api.world.block.BlockType;
import gg.warcraft.monolith.api.world.block.BlockTypeUtils;
import gg.warcraft.monolith.api.world.block.DirectionalBlock;
import gg.warcraft.monolith.api.world.block.Door;
import gg.warcraft.monolith.api.world.block.Gate;
import gg.warcraft.monolith.api.world.block.Hinge;
import gg.warcraft.monolith.api.world.block.Sign;
import gg.warcraft.monolith.api.world.block.Slab;
import gg.warcraft.monolith.api.world.block.Trapdoor;
import gg.warcraft.monolith.api.world.location.Location;
import gg.warcraft.monolith.api.world.service.WorldServerAdapter;
import gg.warcraft.monolith.app.world.block.DoorBlock;
import gg.warcraft.monolith.app.world.block.LeverBlock;
import gg.warcraft.monolith.app.world.block.PistonBlock;
import gg.warcraft.monolith.spigot.item.SpigotItemMapper;
import gg.warcraft.monolith.spigot.world.MaterialData;
import gg.warcraft.monolith.spigot.world.SpigotDirectionMapper;
import gg.warcraft.monolith.spigot.world.SpigotSoundCategoryMapper;
import gg.warcraft.monolith.spigot.world.SpigotSoundMapper;
import gg.warcraft.monolith.spigot.world.SpigotWorldMapper;
import gg.warcraft.monolith.spigot.world.block.SpigotBlockMapper;
import gg.warcraft.monolith.spigot.world.block.SpigotBlockTypeMapper;
import gg.warcraft.monolith.spigot.world.location.SpigotLocationMapper;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Button;
import org.bukkit.material.Chest;
import org.bukkit.material.Ladder;
import org.bukkit.material.Lever;
import org.bukkit.material.PistonBaseMaterial;
import org.bukkit.material.Stairs;
import org.bukkit.material.Step;
import org.bukkit.material.TrapDoor;
import org.bukkit.material.WoodenStep;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;
import org.joml.Vector3f;

import java.util.List;
import java.util.UUID;

// TODO this class can do with some cleaning up
public class SpigotWorldAdapter implements WorldServerAdapter {
    private final Server server;
    private final BlockTypeUtils blockTypeUtils;
    private final SpigotWorldMapper worldMapper;
    private final SpigotBlockMapper blockMapper;
    private final SpigotBlockTypeMapper blockTypeMapper;
    private final SpigotDirectionMapper directionMapper;
    private final SpigotLocationMapper locationMapper;
    private final SpigotItemMapper itemMapper;
    private final SpigotSoundMapper soundMapper;
    private final SpigotSoundCategoryMapper soundCategoryMapper;

    @Inject
    public SpigotWorldAdapter(Server server, BlockTypeUtils blockTypeUtils, SpigotWorldMapper worldMapper,
                              SpigotBlockMapper blockMapper, SpigotBlockTypeMapper blockTypeMapper,
                              SpigotDirectionMapper directionMapper, SpigotLocationMapper locationMapper,
                              SpigotItemMapper itemMapper, SpigotSoundMapper soundMapper,
                              SpigotSoundCategoryMapper soundCategoryMapper) {
        this.server = server;
        this.blockTypeUtils = blockTypeUtils;
        this.worldMapper = worldMapper;
        this.blockMapper = blockMapper;
        this.blockTypeMapper = blockTypeMapper;
        this.directionMapper = directionMapper;
        this.locationMapper = locationMapper;
        this.itemMapper = itemMapper;
        this.soundMapper = soundMapper;
        this.soundCategoryMapper = soundCategoryMapper;
    }

    @Override
    public gg.warcraft.monolith.api.world.World getWorld(WorldType type) {
        return worldMapper.map(worldMapper.map(type));
    }

    @Override
    public Block getBlockAt(WorldType world, int x, int y, int z) {
        World spigotWorld = worldMapper.map(world);
        org.bukkit.block.Block spigotBlock = spigotWorld.getBlockAt(x, y, z);
        return blockMapper.map(spigotBlock);
    }

    @Override
    public Block getHighestBlockAt(WorldType world, int x, int z) {
        World spigotWorld = worldMapper.map(world);
        org.bukkit.block.Block spigotBlock = spigotWorld.getHighestBlockAt(x, z);
        while (blockTypeUtils.getNonSolids().contains(blockMapper.map(spigotBlock).getType())) {
            spigotBlock = spigotBlock.getRelative(BlockFace.DOWN);
        }
        if (spigotBlock.getY() <= 255) {
            org.bukkit.block.Block relativeSpigotBlock = spigotBlock;
            do {
                relativeSpigotBlock = relativeSpigotBlock.getRelative(BlockFace.UP);
                if (!blockTypeUtils.getNonSolids().contains(blockMapper.map(relativeSpigotBlock).getType())) {
                    spigotBlock = relativeSpigotBlock;
                } else {
                    break;
                }
            } while (relativeSpigotBlock.getY() <= 255);
        }
        return blockMapper.map(spigotBlock);
    }

    @Override
    public void setBlock(Block block) {
        BlockType type = block.getType();
        int typeId = type.getId();
        byte typeData = (byte) type.getData();

        org.bukkit.block.Block spigotBlock = blockMapper.map(block);
        BlockState spigotBlockState = spigotBlock.getState();
        spigotBlockState.setTypeId(typeId);
        spigotBlockState.setRawData(typeData);

        org.bukkit.material.MaterialData spigotMaterialData = null;
        Direction facingDirection = block instanceof DirectionalBlock
                ? ((DirectionalBlock) block).getFacing()
                : null;
        BlockFace spigotFacingDirection = directionMapper.map(facingDirection);
        switch (block.getType()) {
            case CHEST:
                Chest chest = new Chest(typeId, typeData);
                chest.setFacingDirection(spigotFacingDirection);
                spigotMaterialData = chest;
                break;
            case STONE_BUTTON:
            case WOODEN_BUTTON:
                Button button = new Button(typeId, typeData);
                button.setFacingDirection(spigotFacingDirection);
                spigotMaterialData = button;
                break;
            case LADDER:
                Ladder ladder = new Ladder(typeId, typeData);
                ladder.setFacingDirection(spigotFacingDirection);
                spigotMaterialData = ladder;
                break;
            case LEVER_BOTTOM_OFF_EAST:
            case LEVER_EAST_OFF:
            case LEVER_WEST_OFF:
            case LEVER_SOUTH_OFF:
            case LEVER_NORTH_OFF:
            case LEVER_TOP_OFF_SOUTH:
            case LEVER_TOP_OFF_EAST:
            case LEVER_BOTTOM_OFF_SOUTH:
            case LEVER_BOTTOM_ON_WEST:
            case LEVER_EAST_ON:
            case LEVER_WEST_ON:
            case LEVER_SOUTH_ON:
            case LEVER_NORTH_ON:
            case LEVER_TOP_ON_NORTH:
            case LEVER_TOP_ON_WEST:
            case LEVER_BOTTOM_ON_NORTH:
                LeverBlock leverBlock = (LeverBlock) block;
                byte leverAdjustedTypeData;
                switch (spigotFacingDirection) {
                    case NORTH:
                        leverAdjustedTypeData = (byte) 4;
                        break;
                    case EAST:
                        leverAdjustedTypeData = (byte) 1;
                        break;
                    case SOUTH:
                        leverAdjustedTypeData = (byte) 3;
                        break;
                    case WEST:
                        leverAdjustedTypeData = (byte) 2;
                        break;
                    case UP:
                        leverAdjustedTypeData = (byte) 5;
                        break;
                    case DOWN:
                        leverAdjustedTypeData = (byte) 0;
                        break;
                    default:
                        leverAdjustedTypeData = (byte) 5;
                        break;
                }
                Lever lever = new Lever(typeId, leverAdjustedTypeData);
                lever.setFacingDirection(spigotFacingDirection);
                lever.setPowered(leverBlock.isPowered());
                spigotMaterialData = lever;
                break;
            case SANDSTONE_STAIRS:
            case SPRUCE_WOOD_STAIRS:
            case STONE_BRICK_STAIRS:
            case ACACIA_WOOD_STAIRS:
            case BIRCH_WOOD_STAIRS:
            case BRICK_STAIRS:
            case COBBLESTONE_STAIRS:
            case DARK_OAK_WOOD_STAIRS:
            case JUNGLE_WOOD_STAIRS:
            case NETHER_BRICK_STAIRS:
            case OAK_WOOD_STAIRS:
            case PURPUR_STAIRS:
            case QUARTZ_STAIRS:
            case RED_SANDSTONE_STAIRS:
                // FIXME no idea what is happening here, but stairs blocks are not faced correctly without this
                BlockFace adjustedStairsDirection = spigotFacingDirection.getOppositeFace();
                boolean isInverted = ((gg.warcraft.monolith.api.world.block.Stairs) block).isInverted();
                Stairs stairs = new Stairs(typeId, typeData);
                stairs.setFacingDirection(adjustedStairsDirection);
                stairs.setInverted(isInverted);
                spigotMaterialData = stairs;
                break;
            case SANDSTONE_SLAB:
            case STONE_BRICK_SLAB:
            case STONE_SLAB:
            case BRICK_SLAB:
            case COBBLESTONE_SLAB:
            case PURPUR_SLAB:
            case QUARTZ_SLAB:
            case RED_SANDSTONE_SLAB:
                Slab slab = (Slab) block;
                Step step = new Step(typeId, typeData);
                step.setInverted(slab.isTop());
                spigotMaterialData = step;
                break;
            case ACACIA_WOOD_SLAB:
            case BIRCH_WOOD_SLAB:
            case SPRUCE_WOOD_SLAB:
            case DARK_OAK_WOOD_SLAB:
            case JUNGLE_WOOD_SLAB:
            case WOODEN_SLAB:
            case OAK_WOOD_SLAB:
                Slab woodSlab = (Slab) block;
                WoodenStep woodStep = new WoodenStep(typeId, typeData);
                woodStep.setInverted(woodSlab.isTop());
                spigotMaterialData = woodStep;
                break;
            case IRON_TRAPDOOR:
            case WOODEN_TRAPDOOR:
                Trapdoor trapdoor = (Trapdoor) block;
                TrapDoor trapDoor = new TrapDoor(typeId, typeData);
                trapDoor.setFacingDirection(spigotFacingDirection);
                trapDoor.setInverted(trapdoor.isInverted());
                trapDoor.setOpen(trapdoor.isOpen());
                spigotMaterialData = trapDoor;
                break;
            case ACACIA_FENCE_GATE:
            case BIRCH_FENCE_GATE:
            case DARK_OAK_FENCE_GATE:
            case JUNGLE_FENCE_GATE:
            case OAK_FENCE_GATE:
            case SPRUCE_FENCE_GATE:
                Gate gate = (Gate) block;
                org.bukkit.material.Gate spigotGate = new org.bukkit.material.Gate(typeId, typeData);
                spigotGate.setFacingDirection(spigotFacingDirection);
                spigotGate.setOpen(gate.isOpen());
                spigotMaterialData = spigotGate;
                break;
            case PISTON:
            case STICKY_PISTON:
                PistonBlock piston = (PistonBlock) block;
                PistonBaseMaterial spigotPiston = new PistonBaseMaterial(typeId, typeData);
                spigotPiston.setFacingDirection(spigotFacingDirection);
                spigotPiston.setPowered(piston.isPowered());
                spigotMaterialData = spigotPiston;
                break;
            case DARK_OAK_DOOR:
            case ACACIA_DOOR:
            case BIRCH_DOOR:
            case IRON_DOOR:
            case JUNGLE_DOOR:
            case OAK_DOOR:
            case SPRUCE_DOOR:
                Door door = (DoorBlock) block;
                org.bukkit.material.Door spigotDoor = new org.bukkit.material.Door(typeId, typeData);
                spigotDoor.setFacingDirection(spigotFacingDirection);
                spigotDoor.setTopHalf(door.isTopHalf());
                spigotDoor.setHinge(door.getHinge() == Hinge.RIGHT);
                spigotDoor.setOpen(door.isOpen());
                spigotMaterialData = spigotDoor;
                break;
        }

        if (spigotMaterialData != null) {
            spigotBlockState.setData(spigotMaterialData);
        }

        spigotBlockState.update(true, false);
    }

    @Override
    public void setBlockType(Block block, BlockType type) {
        org.bukkit.block.Block spigotBlock = blockMapper.map(block);
        spigotBlock.setTypeIdAndData(type.getId(), (byte) type.getData(), false);
    }

    @Override
    public void setSignLines(Sign sign, String[] lines) {
        org.bukkit.block.Block spigotBlock = blockMapper.map(sign);
        org.bukkit.block.Sign spigotSign = (org.bukkit.block.Sign) spigotBlock.getState();
        spigotSign.setLine(0, lines[0]);
        spigotSign.setLine(1, lines[1]);
        spigotSign.setLine(2, lines[2]);
        spigotSign.setLine(3, lines[3]);
        spigotSign.update(true, false);
    }

    @Override
    public void dropItemsAt(List<Item> items, Location location) {
        org.bukkit.Location spigotLocation = locationMapper.map(location);
        World world = spigotLocation.getWorld();
        items.forEach(item -> {
            ItemStack itemStack = itemMapper.map(item);
            world.dropItem(spigotLocation, itemStack);
        });
    }

    @Override
    public void spoofBlock(Block fakeBlock, UUID playerId) {
        Player player = server.getPlayer(playerId);
        if (player != null) {
            org.bukkit.Location spigotLocation = locationMapper.map(fakeBlock.getLocation());
            MaterialData spigotBlockType = blockTypeMapper.map(fakeBlock.getType());
            player.sendBlockChange(spigotLocation, spigotBlockType.getMaterial(), spigotBlockType.getData());
        }
    }

    @Override
    public void playSound(Location location, Sound sound, SoundCategory category, float volume, float pitch) {
        org.bukkit.Location spigotLocation = locationMapper.map(location);
        World world = spigotLocation.getWorld();
        org.bukkit.Sound spigotSound = soundMapper.map(sound);
        org.bukkit.SoundCategory spigotSoundCategory = soundCategoryMapper.map(category);
        world.playSound(spigotLocation, spigotSound, spigotSoundCategory, volume, pitch);
    }

    @Override
    public void strikeLightning(Location location, boolean ambient) {
        org.bukkit.Location spigotLocation = locationMapper.map(location);
        World world = spigotLocation.getWorld();
        if (ambient) {
            world.strikeLightningEffect(spigotLocation);
        } else {
            world.strikeLightning(spigotLocation);
        }
    }

    @Override
    public void createExplosion(Location location, boolean ambient) {
        org.bukkit.Location spigotLocation = locationMapper.map(location);
        World world = spigotLocation.getWorld();
        int strength = ambient ? 0 : 1;
        world.createExplosion(location.getX(), location.getY(), location.getZ(), strength, !ambient, !ambient);
    }

    @Override
    public UUID createArrow(UUID shooterId, Location location, Vector3f direction, float speed, float spread) {
        org.bukkit.Location spigotLocation = locationMapper.map(location);
        World spigotWorld = spigotLocation.getWorld();
        Vector spigotDirection = new Vector(direction.x(), direction.y(), direction.z());
        Arrow arrow = spigotWorld.spawnArrow(spigotLocation, spigotDirection, speed, spread);

        Entity shooter = server.getEntity(shooterId);
        if (shooter instanceof ProjectileSource) {
            arrow.setShooter((ProjectileSource) shooter);
        }
        return arrow.getUniqueId();
    }
}
