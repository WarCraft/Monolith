package gg.warcraft.monolith.app.world.location

import org.joml.Vector3i
import org.scalatest.{FlatSpec, GivenWhenThen}

class SimpleBlockLocationSpec extends FlatSpec with GivenWhenThen {

  "A SimpleBlockLocation" should "expose its world" in {
    val location = random.world.simpleBlockLocation()
    assert(location.world == location.getWorld)
  }

  it should "expose its x coordinate" in {
    val location = random.world.simpleBlockLocation()
    assert(location.x == location.getX)
  }

  it should "expose its y coordinate" in {
    val location = random.world.simpleBlockLocation()
    assert(location.y == location.getY)
  }

  it should "expose its z coordinate" in {
    val location = random.world.simpleBlockLocation()
    assert(location.z == location.getZ)
  }

  it should "add coordinates to itself" in {
    val location = random.world.simpleBlockLocation()

    Given("a set of coordinates")
    val (x, y, z) = random.world.blockCoordinates()

    When("the coordinates are added to the location")
    val result = location.add(x, y, z)

    Then("the result is additively offset by the coordinates")
    assert(location.x + x == result.getX)
    assert(location.y + y == result.getY)
    assert(location.z + z == result.getZ)
  }

  it should "subtract coordinates from itself" in {
    val location = random.world.simpleBlockLocation()

    Given("a set of coordinates")
    val (x, y, z) = random.world.blockCoordinates()

    When("the coordinates are subtracted from the location")
    val result = location.sub(x, y, z)

    Then("the result is subtractively offset by the coordinates")
    assert(location.x - x == result.getX)
    assert(location.y - y == result.getY)
    assert(location.z - z == result.getZ)
  }

  it should "add a vector to itself" in {
    val location = random.world.simpleBlockLocation()

    Given("a vector")
    val (x, y, z) = random.world.blockCoordinates()
    val vector = new Vector3i(x, y, z)

    When("the vector is added to the location")
    val result = location.add(vector)

    Then("the result is additively offset by the vector")
    assert(location.x + vector.x == result.getX)
    assert(location.y + vector.y == result.getY)
    assert(location.z + vector.z == result.getZ)
  }

  it should "subtract a vector from itself" in {
    val location = random.world.simpleBlockLocation()

    Given("a vector")
    val (x, y, z) = random.world.blockCoordinates()
    val vector = new Vector3i(x, y, z)

    When("the vector is subtracted from the location")
    val result = location.sub(vector)

    Then("the result is subtractively offset by the vector")
    assert(location.x - vector.x == result.getX)
    assert(location.y - vector.y == result.getY)
    assert(location.z - vector.z == result.getZ)
  }

  it should "add a location to itself" in {
    val location = random.world.simpleBlockLocation()

    Given("a location")
    val other = random.world.simpleBlockLocation()

    When("the location is added to itself")
    val result = location.add(other)

    Then("the result is additively offset by the location")
    assert(location.x + other.x == result.getX)
    assert(location.y + other.y == result.getY)
    assert(location.z + other.z == result.getZ)
  }

  it should "subtract a location from itself" in {
    val location = random.world.simpleBlockLocation()

    Given("a location")
    val other = random.world.simpleBlockLocation()

    When("the location is subtracted from itself")
    val result = location.sub(other)

    Then("the result is subtractively offset by the vector")
    assert(location.x - other.x == result.getX)
    assert(location.y - other.y == result.getY)
    assert(location.z - other.z == result.getZ)
  }

  it should "copy itself with a new world" in {
    val location = random.world.simpleBlockLocation()

    Given("a world")
    val world = random.world.world()

    When("the location is copied")
    val result = location.withWorld(world)

    Then("the result is a copy with the new world")
    assert(world == result.getWorld)
    assert(location.x == result.getX)
    assert(location.y == result.getY)
    assert(location.z == result.getZ)
  }

  it should "copy itself with a new x coordinate" in {
    val location = random.world.simpleBlockLocation()

    Given("a coordinate")
    val (x, _, _) = random.world.blockCoordinates()

    When("the location is copied")
    val result = location.withX(x)

    Then("the result is a copy with the new coordinate")
    assert(location.world == result.getWorld)
    assert(x == result.getX)
    assert(location.y == result.getY)
    assert(location.z == result.getZ)
  }

  it should "copy itself with a new y coordinate" in {
    val location = random.world.simpleBlockLocation()

    Given("a coordinate")
    val (_, y, _) = random.world.blockCoordinates()

    When("the location is copied")
    val result = location.withY(y)

    Then("the result is a copy with the new coordinate")
    assert(location.world == result.getWorld)
    assert(location.x == result.getX)
    assert(y == result.getY)
    assert(location.z == result.getZ)
  }

  it should "copy itself with a new z coordinate" in {
    val location = random.world.simpleBlockLocation()

    Given("a coordinate")
    val (_, _, z) = random.world.blockCoordinates()

    When("the location is copied")
    val result = location.withZ(z)

    Then("the result is a copy with the new coordinate")
    assert(location.world == result.getWorld)
    assert(location.x == result.getX)
    assert(location.y == result.getY)
    assert(z == result.getZ)
  }

  it should "map itself to a vector" in {
    val location = random.world.simpleBlockLocation()
    val vector = location.toVector
    assert(location.x == vector.x)
    assert(location.y == vector.y)
    assert(location.z == vector.z)
  }

  it should "map itself to a SimpleLocation" in {
    val location = random.world.simpleBlockLocation()
    val simpleLocation = location.toLocation
    assert(location.world == simpleLocation.getWorld)
    assert(location.x == simpleLocation.getX)
    assert(location.y == simpleLocation.getY)
    assert(location.z == simpleLocation.getZ)
  }
}
