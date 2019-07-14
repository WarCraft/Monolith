package gg.warcraft.monolith.api.world.location

import org.scalatest.{FlatSpec, GivenWhenThen}

class LocationPackageSpec extends FlatSpec with GivenWhenThen {

  "validateWorld" should "accept any world" in {
    val world = random.world.world()
    validateWorld(world)
  }

  it should "reject null" in {
    assertThrows[IllegalArgumentException] {
      validateWorld(null)
    }
  }

  "validatePitch" should "accept the lower bound edge case" in {
    validatePitch(-90)
  }

  it should "accept the upper bound edge case" in {
    validatePitch(90)
  }

  it should "reject the lower bound edge case" in {
    assertThrows[IllegalArgumentException] {
      validatePitch(-90 - Float.MinPositiveValue)
    }
  }

  it should "reject the upper bound edge case" in {
    assertThrows[IllegalArgumentException] {
      validatePitch(90 + Float.MinPositiveValue)
    }
  }

  "validateYaw" should "accept the lower bound edge case" in {
    validateYaw(-180)
  }

  it should "accept the upper bound edge case" in {
    validateYaw(180 - Float.MinPositiveValue)
  }

  it should "reject the lower bound edge case" in {
    assertThrows[IllegalArgumentException] {
      validateYaw(-180 - Float.MinPositiveValue)
    }
  }

  it should "reject the upper bound edge case" in {
    assertThrows[IllegalArgumentException] {
      validateYaw(180)
    }
  }

  "orientationToDirection" should "" in {

  }

  "directionToOrientation" should "" in {

  }
}

/*
   private static final float DELTA = 1E-10f;

    @Test
    public void constructor_shouldCalculateCorrectDirectionLookingUp() {
        // Given
        float pitch = -90;
        float yaw = 0;

        Vector3fc expectedDirection = new Vector3f(0, 1, 0);

        // When
        OrientedLocation location = new SimpleOrientedLocation(null, 0, 0, 0, pitch, yaw);
        Vector3fc direction = location.toDirection();

        // Then
        Assert.assertEquals(expectedDirection.x(), direction.x(), DELTA);
        Assert.assertEquals(expectedDirection.y(), direction.y(), DELTA);
        Assert.assertEquals(expectedDirection.z(), direction.z(), DELTA);
    }

    @Test
    public void constructor_shouldCalculateCorrectPitchYawLookingUp() {
        // Given
        Vector3fc direction = new Vector3f(0, 1, 0);

        float expectedPitch = -90;
        float expectedYaw = 0;

        // When
        OrientedLocation location = new SimpleOrientedLocation(null, 0, 0, 0, direction);
        float pitch = location.getPitch();
        float yaw = location.getYaw();

        // Then
        Assert.assertEquals(expectedPitch, pitch, DELTA);
        Assert.assertEquals(expectedYaw, yaw, DELTA);
    }

    @Test
    public void constructor_shouldCorrectNonNormalizedDirectionLookingUp() {
        // Given
        Vector3fc direction = new Vector3f(0, 5, 0);

        float expectedPitch = -90;
        float expectedYaw = 0;

        // When
        OrientedLocation location = new SimpleOrientedLocation(null, 0, 0, 0, direction);
        float pitch = location.getPitch();
        float yaw = location.getYaw();

        // Then
        Assert.assertEquals(expectedPitch, pitch, DELTA);
        Assert.assertEquals(expectedYaw, yaw, DELTA);
    }

    @Test
    public void constructor_shouldCalculateCorrectDirectionLookingDown() {
        // Given
        float pitch = 90;
        float yaw = 0;

        Vector3fc expectedDirection = new Vector3f(0, -1, 0);

        // When
        OrientedLocation location = new SimpleOrientedLocation(null, 0, 0, 0, pitch, yaw);
        Vector3fc direction = location.toDirection();

        // Then
        Assert.assertEquals(expectedDirection.x(), direction.x(), DELTA);
        Assert.assertEquals(expectedDirection.y(), direction.y(), DELTA);
        Assert.assertEquals(expectedDirection.z(), direction.z(), DELTA);
    }

    @Test
    public void constructor_shouldCalculateCorrectPitchYawLookingDown() {
        // Given
        Vector3fc direction = new Vector3f(0, -1, 0);

        float expectedPitch = 90;
        float expectedYaw = 0;

        // When
        OrientedLocation location = new SimpleOrientedLocation(null, 0, 0, 0, direction);
        float pitch = location.getPitch();
        float yaw = location.getYaw();

        // Then
        Assert.assertEquals(expectedPitch, pitch, DELTA);
        Assert.assertEquals(expectedYaw, yaw, DELTA);
    }

    @Test
    public void constructor_shouldCalculateCorrectDirectionLookingNorth() {
        // Given
        float pitch = 0;
        float yaw = -180;

        Vector3fc expectedDirection = new Vector3f(0, 0, -1);

        // When
        OrientedLocation location = new SimpleOrientedLocation(null, 0, 0, 0, pitch, yaw);
        Vector3fc direction = location.toDirection();

        // Then
        Assert.assertEquals(expectedDirection.x(), direction.x(), DELTA);
        Assert.assertEquals(expectedDirection.y(), direction.y(), DELTA);
        Assert.assertEquals(expectedDirection.z(), direction.z(), DELTA);
    }

    @Test
    public void constructor_shouldCalculateCorrectPitchYawLookingNorth() {
        // Given
        Vector3fc direction = new Vector3f(0, 0, -1);

        float expectedPitch = 0;
        float expectedYaw = 180;

        // When
        OrientedLocation location = new SimpleOrientedLocation(null, 0, 0, 0, direction);
        float pitch = location.getPitch();
        float yaw = location.getYaw();

        // Then
        Assert.assertEquals(expectedPitch, pitch, DELTA);
        Assert.assertEquals(expectedYaw, yaw, DELTA);
    }

    @Test
    public void constructor_shouldCalculateCorrectDirectionLookingEast() {
        // Given
        float pitch = 0;
        float yaw = -90;

        Vector3fc expectedDirection = new Vector3f(1, 0, 0);

        // When
        OrientedLocation location = new SimpleOrientedLocation(null, 0, 0, 0, pitch, yaw);
        Vector3fc direction = location.toDirection();

        // Then
        Assert.assertEquals(expectedDirection.x(), direction.x(), DELTA);
        Assert.assertEquals(expectedDirection.y(), direction.y(), DELTA);
        Assert.assertEquals(expectedDirection.z(), direction.z(), DELTA);
    }

    @Test
    public void constructor_shouldCalculateCorrectPitchYawLookingEast() {
        // Given
        Vector3fc direction = new Vector3f(1, 0, 0);

        float expectedPitch = 0;
        float expectedYaw = -90;

        // When
        OrientedLocation location = new SimpleOrientedLocation(null, 0, 0, 0, direction);
        float pitch = location.getPitch();
        float yaw = location.getYaw();

        // Then
        Assert.assertEquals(expectedPitch, pitch, DELTA);
        Assert.assertEquals(expectedYaw, yaw, DELTA);
    }

    @Test
    public void constructor_shouldCalculateCorrectDirectionLookingSouth() {
        // Given
        float pitch = 0;
        float yaw = 0;

        Vector3fc expectedDirection = new Vector3f(0, 0, 1);

        // When
        OrientedLocation location = new SimpleOrientedLocation(null, 0, 0, 0, pitch, yaw);
        Vector3fc direction = location.toDirection();

        // Then
        Assert.assertEquals(expectedDirection.x(), direction.x(), DELTA);
        Assert.assertEquals(expectedDirection.y(), direction.y(), DELTA);
        Assert.assertEquals(expectedDirection.z(), direction.z(), DELTA);
    }

    @Test
    public void constructor_shouldCalculateCorrectPitchYawLookingSouth() {
        // Given
        Vector3fc direction = new Vector3f(0, 0, 1);

        float expectedPitch = 0;
        float expectedYaw = 0;

        // When
        OrientedLocation location = new SimpleOrientedLocation(null, 0, 0, 0, direction);
        float pitch = location.getPitch();
        float yaw = location.getYaw();

        // Then
        Assert.assertEquals(expectedPitch, pitch, DELTA);
        Assert.assertEquals(expectedYaw, yaw, DELTA);
    }

    @Test
    public void constructor_shouldCalculateCorrectDirectionLookingWest() {
        // Given
        float pitch = 0;
        float yaw = 90;

        Vector3fc expectedDirection = new Vector3f(-1, 0, 0);

        // When
        OrientedLocation location = new SimpleOrientedLocation(null, 0, 0, 0, pitch, yaw);
        Vector3fc direction = location.toDirection();

        // Then
        Assert.assertEquals(expectedDirection.x(), direction.x(), DELTA);
        Assert.assertEquals(expectedDirection.y(), direction.y(), DELTA);
        Assert.assertEquals(expectedDirection.z(), direction.z(), DELTA);
    }

    @Test
    public void constructor_shouldCalculateCorrectPitchYawLookingWest() {
        // Given
        Vector3fc direction = new Vector3f(-1, 0, 0);

        float expectedPitch = 0;
        float expectedYaw = 90;

        // When
        OrientedLocation location = new SimpleOrientedLocation(null, 0, 0, 0, direction);
        float pitch = location.getPitch();
        float yaw = location.getYaw();

        // Then
        Assert.assertEquals(expectedPitch, pitch, DELTA);
        Assert.assertEquals(expectedYaw, yaw, DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_shouldRejectOutOfRangePositivePitch() {
        // Given
        float pitch = 90.1f;
        float yaw = 0;

        // When
        new SimpleOrientedLocation(null, 0, 0, 0, pitch, yaw);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_shouldRejectOutOfRangeNegativePitch() {
        // Given
        float pitch = -90.1f;
        float yaw = 0;

        // When
        new SimpleOrientedLocation(null, 0, 0, 0, pitch, yaw);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_shouldRejectOutOfRangePositiveYaw() {
        // Given
        float pitch = 0;
        float yaw = 180;

        // When
        new SimpleOrientedLocation(null, 0, 0, 0, pitch, yaw);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_shouldRejectOutOfRangeNegativeYaw() {
        // Given
        float pitch = 0;
        float yaw = -180.1f;

        // When
        new SimpleOrientedLocation(null, 0, 0, 0, pitch, yaw);
    }
 */