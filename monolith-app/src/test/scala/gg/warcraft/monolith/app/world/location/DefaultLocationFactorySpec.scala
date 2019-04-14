package gg.warcraft.monolith.app.world.location

import org.joml.Vector3f
import org.scalatest.{FlatSpec, GivenWhenThen}

class DefaultLocationFactorySpec extends FlatSpec with GivenWhenThen {

  "DefaultLocationFactory" should "create a SimpleLocation" in {
    val factory = new DefaultLocationFactory()
    val world = random.world.world()
    val (x, y, z) = random.world.blockCoordinates()
    val location = factory.createLocation(world, x, y, z)

    assert(world == location.getWorld)
    assert(x == location.getX)
    assert(y == location.getY)
    assert(z == location.getZ)
  }

  it should "create a SimpleBlockLocation" in {
    val factory = new DefaultLocationFactory()
    val world = random.world.world()
    val (x, y, z) = random.world.locationCoordinates()
    val location = factory.createLocation(world, x, y, z)

    assert(world == location.getWorld)
    assert(x == location.getX)
    assert(y == location.getY)
    assert(z == location.getZ)
  }

  it should "create a SimpleOrientedLocation" in {
    val factory = new DefaultLocationFactory()
    val world = random.world.world()
    val (x, y, z) = random.world.locationCoordinates()
    val (pitch, yaw) = random.world.orientation()
    val location = factory.createOrientedLocation(world, x, y, z, pitch, yaw)

    assert(world == location.getWorld)
    assert(x == location.getX)
    assert(y == location.getY)
    assert(z == location.getZ)
    assert(pitch == location.getPitch)
    assert(yaw == location.getYaw)
  }

  it should "create a SimpleOrientedLocation from a direction" in {
    val factory = new DefaultLocationFactory()
    val world = random.world.world()
    val (x, y, z) = random.world.locationCoordinates()
    val direction = new Vector3f()
    val location = factory.createOrientedLocation(world, x, y, z, direction)

    val (pitch, yaw) = (0, 0)
    assert(world == location.getWorld)
    assert(x == location.getX)
    assert(y == location.getY)
    assert(z == location.getZ)
    assert(pitch == location.getPitch)
    assert(yaw == location.getYaw)
  }
}
