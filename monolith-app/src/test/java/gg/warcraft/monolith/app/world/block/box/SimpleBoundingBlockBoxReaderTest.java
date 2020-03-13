package gg.warcraft.monolith.app.world.block.box;

import gg.warcraft.monolith.api.util.Offset;
import gg.warcraft.monolith.api.world.BlockLocation;
import gg.warcraft.monolith.api.world.Direction;
import gg.warcraft.monolith.api.world.World;
import gg.warcraft.monolith.api.block.Block;
import gg.warcraft.monolith.api.block.BlockType;
import gg.warcraft.monolith.api.block.box.BoundingBlockBox;
import gg.warcraft.monolith.api.world.service.WorldQueryService;
import gg.warcraft.monolith.app.util.SimpleOffset;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SimpleBoundingBlockBoxReaderTest {
    private SimpleBoundingBlockBoxReader northSimpleBoundingBlockBoxReader;
    private SimpleBoundingBlockBoxReader eastSimpleBoundingBlockBoxReader;
    private SimpleBoundingBlockBoxReader southSimpleBoundingBlockBoxReader;
    private SimpleBoundingBlockBoxReader westSimpleBoundingBlockBoxReader;

    @Mock private WorldQueryService mockWorldQueryService;
    @Mock private BoundingBlockBox mockBoundingBlockBox;
    @Mock private Block mockBlock11;
    @Mock private Block mockBlock13;
    @Mock private Block mockBlock31;
    @Mock private Block mockBlock33;

    @Before
    public void beforeEach() {
        World world = World.OVERWORLD;
        when(mockBoundingBlockBox.getWorld()).thenReturn(world);

        when(mockBoundingBlockBox.getNorthBoundary()).thenReturn(1);
        when(mockBoundingBlockBox.getEastBoundary()).thenReturn(3);
        when(mockBoundingBlockBox.getSouthBoundary()).thenReturn(3);
        when(mockBoundingBlockBox.getWestBoundary()).thenReturn(1);
        when(mockBoundingBlockBox.getLowerBoundary()).thenReturn(0);
        when(mockBoundingBlockBox.getUpperBoundary()).thenReturn(0);

        mockBlock11 = mock(Block.class);
        when(mockBlock11.type()).thenReturn(BlockType.AIR);
        BlockLocation mockBlock11Location = mock(BlockLocation.class);
        when(mockBlock11Location.x()).thenReturn(1);
        when(mockBlock11Location.y()).thenReturn(0);
        when(mockBlock11Location.z()).thenReturn(1);
        when(mockBlock11.location()).thenReturn(mockBlock11Location);

        mockBlock13 = mock(Block.class);
        when(mockBlock13.type()).thenReturn(BlockType.DIRT);
        BlockLocation mockBlock13Location = mock(BlockLocation.class);
        when(mockBlock13Location.x()).thenReturn(1);
        when(mockBlock13Location.y()).thenReturn(0);
        when(mockBlock13Location.z()).thenReturn(3);
        when(mockBlock13.location()).thenReturn(mockBlock13Location);

        mockBlock31 = mock(Block.class);
        when(mockBlock31.type()).thenReturn(BlockType.LAVA);
        BlockLocation mockBlock31Location = mock(BlockLocation.class);
        when(mockBlock31Location.x()).thenReturn(3);
        when(mockBlock31Location.y()).thenReturn(0);
        when(mockBlock31Location.z()).thenReturn(1);
        when(mockBlock31.location()).thenReturn(mockBlock31Location);

        mockBlock33 = mock(Block.class);
        when(mockBlock33.type()).thenReturn(BlockType.VINES);
        BlockLocation mockBlock33Location = mock(BlockLocation.class);
        when(mockBlock33Location.x()).thenReturn(3);
        when(mockBlock33Location.y()).thenReturn(0);
        when(mockBlock33Location.z()).thenReturn(3);
        when(mockBlock33.location()).thenReturn(mockBlock33Location);

        Block mockBlock12 = mock(Block.class);
        Block mockBlock21 = mock(Block.class);
        Block mockBlock22 = mock(Block.class);
        Block mockBlock23 = mock(Block.class);
        Block mockBlock32 = mock(Block.class);

        when(mockWorldQueryService.getBlockAt(world, 1, 0, 1)).thenReturn(mockBlock11);
        when(mockWorldQueryService.getBlockAt(world, 1, 0, 2)).thenReturn(mockBlock12);
        when(mockWorldQueryService.getBlockAt(world, 1, 0, 3)).thenReturn(mockBlock13);
        when(mockWorldQueryService.getBlockAt(world, 2, 0, 1)).thenReturn(mockBlock21);
        when(mockWorldQueryService.getBlockAt(world, 2, 0, 2)).thenReturn(mockBlock22);
        when(mockWorldQueryService.getBlockAt(world, 2, 0, 3)).thenReturn(mockBlock23);
        when(mockWorldQueryService.getBlockAt(world, 3, 0, 1)).thenReturn(mockBlock31);
        when(mockWorldQueryService.getBlockAt(world, 3, 0, 2)).thenReturn(mockBlock32);
        when(mockWorldQueryService.getBlockAt(world, 3, 0, 3)).thenReturn(mockBlock33);

        northSimpleBoundingBlockBoxReader = new SimpleBoundingBlockBoxReader(mockWorldQueryService, mockBoundingBlockBox, Direction.NORTH);
        eastSimpleBoundingBlockBoxReader = new SimpleBoundingBlockBoxReader(mockWorldQueryService, mockBoundingBlockBox, Direction.EAST);
        southSimpleBoundingBlockBoxReader = new SimpleBoundingBlockBoxReader(mockWorldQueryService, mockBoundingBlockBox, Direction.SOUTH);
        westSimpleBoundingBlockBoxReader = new SimpleBoundingBlockBoxReader(mockWorldQueryService, mockBoundingBlockBox, Direction.WEST);
    }

    @After
    public void afterEach() {
        reset(mockWorldQueryService, mockBoundingBlockBox, mockBlock11, mockBlock13, mockBlock31, mockBlock33);
    }

    @Test
    public void getOffsetFor_shouldReturnCorrectOffset() {
        // When
        Offset northMockBlock11Offset = northSimpleBoundingBlockBoxReader.getOffsetFor(mockBlock11);
        Offset eastMockBlock11Offset = eastSimpleBoundingBlockBoxReader.getOffsetFor(mockBlock11);
        Offset southMockBlock11Offset = southSimpleBoundingBlockBoxReader.getOffsetFor(mockBlock11);
        Offset westMockBlock11Offset = westSimpleBoundingBlockBoxReader.getOffsetFor(mockBlock11);

        Offset northMockBlock13Offset = northSimpleBoundingBlockBoxReader.getOffsetFor(mockBlock13);
        Offset eastMockBlock13Offset = eastSimpleBoundingBlockBoxReader.getOffsetFor(mockBlock13);
        Offset southMockBlock13Offset = southSimpleBoundingBlockBoxReader.getOffsetFor(mockBlock13);
        Offset westMockBlock13Offset = westSimpleBoundingBlockBoxReader.getOffsetFor(mockBlock13);

        Offset northMockBlock31Offset = northSimpleBoundingBlockBoxReader.getOffsetFor(mockBlock31);
        Offset eastMockBlock31Offset = eastSimpleBoundingBlockBoxReader.getOffsetFor(mockBlock31);
        Offset southMockBlock31Offset = southSimpleBoundingBlockBoxReader.getOffsetFor(mockBlock31);
        Offset westMockBlock31Offset = westSimpleBoundingBlockBoxReader.getOffsetFor(mockBlock31);

        Offset northMockBlock33Offset = northSimpleBoundingBlockBoxReader.getOffsetFor(mockBlock33);
        Offset eastMockBlock33Offset = eastSimpleBoundingBlockBoxReader.getOffsetFor(mockBlock33);
        Offset southMockBlock33Offset = southSimpleBoundingBlockBoxReader.getOffsetFor(mockBlock33);
        Offset westMockBlock33Offset = westSimpleBoundingBlockBoxReader.getOffsetFor(mockBlock33);

        // Then
        Assert.assertEquals(new SimpleOffset(0, 0, 2), northMockBlock11Offset);
        Assert.assertEquals(new SimpleOffset(0, 0, 0), eastMockBlock11Offset);
        Assert.assertEquals(new SimpleOffset(2, 0, 0), southMockBlock11Offset);
        Assert.assertEquals(new SimpleOffset(2, 0, 2), westMockBlock11Offset);

        Assert.assertEquals(new SimpleOffset(0, 0, 0), northMockBlock13Offset);
        Assert.assertEquals(new SimpleOffset(2, 0, 0), eastMockBlock13Offset);
        Assert.assertEquals(new SimpleOffset(2, 0, 2), southMockBlock13Offset);
        Assert.assertEquals(new SimpleOffset(0, 0, 2), westMockBlock13Offset);

        Assert.assertEquals(new SimpleOffset(2, 0, 2), northMockBlock31Offset);
        Assert.assertEquals(new SimpleOffset(0, 0, 2), eastMockBlock31Offset);
        Assert.assertEquals(new SimpleOffset(0, 0, 0), southMockBlock31Offset);
        Assert.assertEquals(new SimpleOffset(2, 0, 0), westMockBlock31Offset);

        Assert.assertEquals(new SimpleOffset(2, 0, 0), northMockBlock33Offset);
        Assert.assertEquals(new SimpleOffset(2, 0, 2), eastMockBlock33Offset);
        Assert.assertEquals(new SimpleOffset(0, 0, 2), southMockBlock33Offset);
        Assert.assertEquals(new SimpleOffset(0, 0, 0), westMockBlock33Offset);
    }

    @Test
    public void getBlockAt_shouldReturnCorrectBlock() {
        // When
        Block northFrontLeftBlock = northSimpleBoundingBlockBoxReader.getBlockAt(0, 0, 2);
        Block eastFrontLeftBlock = eastSimpleBoundingBlockBoxReader.getBlockAt(0, 0, 2);
        Block southFrontLeftBlock = southSimpleBoundingBlockBoxReader.getBlockAt(0, 0, 2);
        Block westFrontLeftBlock = westSimpleBoundingBlockBoxReader.getBlockAt(0, 0, 2);

        // Then
        Assert.assertEquals(mockBlock11.type(), northFrontLeftBlock.type());
        Assert.assertEquals(mockBlock31.type(), eastFrontLeftBlock.type());
        Assert.assertEquals(mockBlock33.type(), southFrontLeftBlock.type());
        Assert.assertEquals(mockBlock13.type(), westFrontLeftBlock.type());
    }
}
