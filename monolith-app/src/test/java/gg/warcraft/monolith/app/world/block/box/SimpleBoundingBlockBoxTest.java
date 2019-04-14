package gg.warcraft.monolith.app.world.block.box;

import gg.warcraft.monolith.api.world.World;
import gg.warcraft.monolith.api.world.block.box.BoundingBlockBox;
import gg.warcraft.monolith.api.world.location.BlockLocation;
import gg.warcraft.monolith.api.world.location.LocationFactory;
import gg.warcraft.monolith.api.world.service.WorldQueryService;
import org.joml.Vector3i;
import org.joml.Vector3ic;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SimpleBoundingBlockBoxTest {
    private SimpleBoundingBlockBox simpleBoundingBlockBox;

    @Mock private WorldQueryService mockWorldQueryService;
    @Mock private LocationFactory mockLocationFactory;
    @Mock private BlockLocation mockBlockLocation;

    @Before
    public void beforeEach() {
        World world = World.OVERWORLD;
        when(mockWorldQueryService.getWorld(world)).thenReturn(world);

        when(mockBlockLocation.getWorld()).thenReturn(world);

        Vector3i minimumCorner = new Vector3i(0, 0, 0);
        Vector3i maximumCorner = new Vector3i(9, 9, 9);

        BlockLocation mockBlockMinimumCorner = mock(BlockLocation.class);
        when(mockBlockMinimumCorner.toVector()).thenReturn(minimumCorner);
        BlockLocation mockBlockMaximumCorner = mock(BlockLocation.class);
        when(mockBlockMaximumCorner.toVector()).thenReturn(maximumCorner);
        when(mockLocationFactory.createBlockLocation(world, 0, 0, 0)).thenReturn(mockBlockMinimumCorner);
        when(mockLocationFactory.createBlockLocation(world, 9, 9, 9)).thenReturn(mockBlockMaximumCorner);

        simpleBoundingBlockBox = new SimpleBoundingBlockBox(mockWorldQueryService, mockLocationFactory, world,
                minimumCorner, maximumCorner);
    }

    @After
    public void afterEach() {
        reset(mockWorldQueryService, mockLocationFactory, mockBlockLocation);
    }

    @Test
    public void test_shouldAcceptBlockInsideBoundary() {
        // Given
        when(mockBlockLocation.getX()).thenReturn(1);
        when(mockBlockLocation.getY()).thenReturn(1);
        when(mockBlockLocation.getZ()).thenReturn(1);

        // When
        boolean result = simpleBoundingBlockBox.test(mockBlockLocation);

        // Then
        assertTrue(result);
    }

    @Test
    public void test_shouldAcceptBlockOnBoundary() {
        // Given
        when(mockBlockLocation.getX()).thenReturn(0);
        when(mockBlockLocation.getY()).thenReturn(0);
        when(mockBlockLocation.getZ()).thenReturn(0);

        // When
        boolean result = simpleBoundingBlockBox.test(mockBlockLocation);

        // Then
        assertTrue(result);
    }

    @Test
    public void test_shouldRejectBlockOutsideBoundary() {
        // Given
        when(mockBlockLocation.getX()).thenReturn(20);
        when(mockBlockLocation.getY()).thenReturn(20);
        when(mockBlockLocation.getZ()).thenReturn(20);

        // When
        boolean result = simpleBoundingBlockBox.test(mockBlockLocation);

        // Then
        assertFalse(result);
    }

    @Test
    public void translate_shouldCorrectlyMoveBoundingBox() {
        // Given
        Vector3ic translation = new Vector3i(3, -3, 3);

        // When
        BoundingBlockBox result = simpleBoundingBlockBox.translate(translation);

        // Then
        Assert.assertEquals(3, result.getNorthBoundary());
        Assert.assertEquals(12, result.getEastBoundary());
        Assert.assertEquals(12, result.getSouthBoundary());
        Assert.assertEquals(3, result.getWestBoundary());
        Assert.assertEquals(-3, result.getLowerBoundary());
        Assert.assertEquals(6, result.getUpperBoundary());
    }

    @Test
    public void rotate_shouldCorrectlyRotate0DegreesAroundPivot() {
        // Given
        when(mockBlockLocation.getX()).thenReturn(1);
        when(mockBlockLocation.getY()).thenReturn(1);
        when(mockBlockLocation.getZ()).thenReturn(1);

        // When
        BoundingBlockBox result = simpleBoundingBlockBox.rotateY(mockBlockLocation, 0);

        // Then
        Assert.assertEquals(0, result.getNorthBoundary());
        Assert.assertEquals(9, result.getEastBoundary());
        Assert.assertEquals(9, result.getSouthBoundary());
        Assert.assertEquals(0, result.getWestBoundary());
        Assert.assertEquals(0, result.getLowerBoundary());
        Assert.assertEquals(9, result.getUpperBoundary());
    }

    @Test
    public void rotate_shouldCorrectlyRotate90DegreesAroundPivot() {
        // Given
        when(mockBlockLocation.getX()).thenReturn(1);
        when(mockBlockLocation.getY()).thenReturn(1);
        when(mockBlockLocation.getZ()).thenReturn(1);

        // When
        BoundingBlockBox result = simpleBoundingBlockBox.rotateY(mockBlockLocation, 90);

        // Then
        Assert.assertEquals(0, result.getNorthBoundary());
        Assert.assertEquals(2, result.getEastBoundary());
        Assert.assertEquals(9, result.getSouthBoundary());
        Assert.assertEquals(-7, result.getWestBoundary());
        Assert.assertEquals(0, result.getLowerBoundary());
        Assert.assertEquals(9, result.getUpperBoundary());
    }

    @Test
    public void rotate_shouldCorrectlyRotate180DegreesAroundPivot() {
        // Given
        when(mockBlockLocation.getX()).thenReturn(1);
        when(mockBlockLocation.getY()).thenReturn(1);
        when(mockBlockLocation.getZ()).thenReturn(1);

        // When
        BoundingBlockBox result = simpleBoundingBlockBox.rotateY(mockBlockLocation, 180);

        // Then
        Assert.assertEquals(-7, result.getNorthBoundary());
        Assert.assertEquals(2, result.getEastBoundary());
        Assert.assertEquals(2, result.getSouthBoundary());
        Assert.assertEquals(-7, result.getWestBoundary());
        Assert.assertEquals(0, result.getLowerBoundary());
        Assert.assertEquals(9, result.getUpperBoundary());
    }

    @Test
    public void rotate_shouldCorrectlyRotate270DegreesAroundPivot() {
        // Given
        when(mockBlockLocation.getX()).thenReturn(1);
        when(mockBlockLocation.getY()).thenReturn(1);
        when(mockBlockLocation.getZ()).thenReturn(1);

        // When
        BoundingBlockBox result = simpleBoundingBlockBox.rotateY(mockBlockLocation, 270);

        // Then
        Assert.assertEquals(-7, result.getNorthBoundary());
        Assert.assertEquals(9, result.getEastBoundary());
        Assert.assertEquals(2, result.getSouthBoundary());
        Assert.assertEquals(0, result.getWestBoundary());
        Assert.assertEquals(0, result.getLowerBoundary());
        Assert.assertEquals(9, result.getUpperBoundary());
    }

    @Test
    public void rotate_shouldCorrectlyRotateLargeRotation() {
        // Given
        when(mockBlockLocation.getX()).thenReturn(1);
        when(mockBlockLocation.getY()).thenReturn(1);
        when(mockBlockLocation.getZ()).thenReturn(1);

        // When
        BoundingBlockBox result = simpleBoundingBlockBox.rotateY(mockBlockLocation, 2700);

        // Then
        Assert.assertEquals(-7, result.getNorthBoundary());
        Assert.assertEquals(2, result.getEastBoundary());
        Assert.assertEquals(2, result.getSouthBoundary());
        Assert.assertEquals(-7, result.getWestBoundary());
        Assert.assertEquals(0, result.getLowerBoundary());
        Assert.assertEquals(9, result.getUpperBoundary());
    }

    @Test
    public void rotate_shouldCorrectlyRotateNegativeRotation() {
        // Given
        when(mockBlockLocation.getX()).thenReturn(1);
        when(mockBlockLocation.getY()).thenReturn(1);
        when(mockBlockLocation.getZ()).thenReturn(1);

        // When
        BoundingBlockBox result = simpleBoundingBlockBox.rotateY(mockBlockLocation, -90);

        // Then
        Assert.assertEquals(-7, result.getNorthBoundary());
        Assert.assertEquals(9, result.getEastBoundary());
        Assert.assertEquals(2, result.getSouthBoundary());
        Assert.assertEquals(0, result.getWestBoundary());
        Assert.assertEquals(0, result.getLowerBoundary());
        Assert.assertEquals(9, result.getUpperBoundary());
    }

    @Test
    public void rotate_shouldCorrectlyRotateLargeNegativeRotation() {
        // Given
        when(mockBlockLocation.getX()).thenReturn(1);
        when(mockBlockLocation.getY()).thenReturn(1);
        when(mockBlockLocation.getZ()).thenReturn(1);

        // When
        BoundingBlockBox result = simpleBoundingBlockBox.rotateY(mockBlockLocation, -2070);

        // Then
        Assert.assertEquals(0, result.getNorthBoundary());
        Assert.assertEquals(2, result.getEastBoundary());
        Assert.assertEquals(9, result.getSouthBoundary());
        Assert.assertEquals(-7, result.getWestBoundary());
        Assert.assertEquals(0, result.getLowerBoundary());
        Assert.assertEquals(9, result.getUpperBoundary());
    }

    @Test(expected = IllegalArgumentException.class)
    public void rotate_shouldThrowOnOddRotation() {
        // Given
        when(mockBlockLocation.getX()).thenReturn(1);
        when(mockBlockLocation.getY()).thenReturn(1);
        when(mockBlockLocation.getZ()).thenReturn(1);

        // When
        simpleBoundingBlockBox.rotateY(mockBlockLocation, 60);
    }
}
