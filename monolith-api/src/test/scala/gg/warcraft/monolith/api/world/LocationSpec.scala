package gg.warcraft.monolith.api.world

import gg.warcraft.monolith.api.math.{Vector3f, Vector3i}
import org.scalatest.{fixture, Outcome}

class LocationSpec extends fixture.FunSpec {
  type FixtureParam = Location

  override def withFixture(test: OneArgTest): Outcome = {
    val translation = Vector3f(2, 4, 6)
    val fixture = Location(World.OVERWORLD, translation)
    try test(fixture)
    finally {}
  }

  describe("Location") {
    describe("::add(Float, Float, Float)") {
      it("creates a copy of itself with the coordinates added") { fixture =>
        // Given
        val x = 2f
        val y = 4f
        val z = 6f

        val expectedTranslation = Vector3f(4, 8, 12)
        val expectedCopy = Location(World.OVERWORLD, expectedTranslation)

        // When
        val copy = fixture.add(x, y, z)

        // Then
        assert(!copy.eq(expectedCopy))
        assert(copy == expectedCopy)
      }
    }

    describe("::add(Vector3f)") {
      it("creates a copy of itself with the vector added") { fixture =>
        // Given
        val vector = Vector3f(2, 4, 6)

        val expectedTranslation = Vector3f(4, 8, 12)
        val expectedCopy = Location(World.OVERWORLD, expectedTranslation)

        // When
        val copy = fixture.add(vector)

        // Then
        assert(!copy.eq(expectedCopy))
        assert(copy == expectedCopy)
      }
    }

    describe("::add(Location)") {
      it("creates a copy of itself with the location added") { fixture =>
        // Given
        val translation = Vector3f(2, 4, 6)
        val location = Location(World.OVERWORLD, translation)

        val expectedTranslation = Vector3f(4, 8, 12)
        val expectedCopy = Location(World.OVERWORLD, expectedTranslation)

        // When
        val copy = fixture.add(location)

        // Then
        assert(!copy.eq(expectedCopy))
        assert(copy == expectedCopy)
      }
    }

    describe("::subtract(Float, Float, Float)") {
      it("creates a copy of itself with the coordinates subtracted") { fixture =>
        // Given
        val x = 6f
        val y = 4f
        val z = 2f

        val expectedTranslation = Vector3f(-4, 0, 4)
        val expectedCopy = Location(World.OVERWORLD, expectedTranslation)

        // When
        val copy = fixture.subtract(x, y, z)

        // Then
        assert(!copy.eq(expectedCopy))
        assert(copy == expectedCopy)
      }
    }

    describe("::subtract(Vector3f)") {
      it("creates a copy of itself with the vector subtracted") { fixture =>
        // Given
        val vector = Vector3f(6, 4, 2)

        val expectedTranslation = Vector3f(-4, 0, 4)
        val expectedCopy = Location(World.OVERWORLD, expectedTranslation)

        // When
        val copy = fixture.subtract(vector)

        // Then
        assert(!copy.eq(expectedCopy))
        assert(copy == expectedCopy)
      }
    }

    describe("::subtract(Location)") {
      it("creates a copy of itself with the location subtracted") { fixture =>
        // Given
        val translation = Vector3f(6, 4, 2)
        val location = Location(World.OVERWORLD, translation)

        val expectedTranslation = Vector3f(-4, 0, 4)
        val expectedCopy = Location(World.OVERWORLD, expectedTranslation)

        // When
        val copy = fixture.subtract(location)

        // Then
        assert(!copy.eq(expectedCopy))
        assert(copy == expectedCopy)
      }
    }

    describe("::distanceTo(Location)") {
      it("calculates the distance between this and that") { fixture =>
        // Given
        val translation = Vector3f(6, 4, 2)
        val location = Location(World.OVERWORLD, translation)

        val expectedDistance = Math.sqrt(32).toFloat

        // When
        val distance = fixture.distanceTo(location)

        // Then
        assert(distance == expectedDistance)
      }
    }

    describe("::toBlockLocation") {
      it("creates a copy of itself as a BlockLocation") { fixture =>
        // Given
        val expectedTranslation = Vector3i(2, 4, 6)
        val expectedLocation = BlockLocation(World.OVERWORLD, expectedTranslation)

        // When
        val location: BlockLocation = fixture

        // Then
        assert(location == expectedLocation)
      }

      it("correctly floors negative coordinates") { _ =>
        // Given
        val translation = Vector3f(-2.5f, -4.5f, -6.5f)
        val fixture = Location(World.OVERWORLD, translation)

        val expectedTranslation = Vector3i(-3, -5, -7)
        val expectedLocation = BlockLocation(World.OVERWORLD, expectedTranslation)

        // When
        val location: BlockLocation = fixture

        // Then
        assert(location == expectedLocation)
      }
    }

    /* Java interop */

    describe("::withWorld") {
      it("creates a copy of itself with the new world") { fixture =>
        // Given
        val world = World.THE_NETHER

        val expectedTranslation = Vector3f(2, 4, 6)
        val expectedCopy = Location(World.THE_NETHER, expectedTranslation)

        // When
        val copy = fixture.withWorld(world)

        // Then
        assert(!copy.eq(expectedCopy))
        assert(copy == expectedCopy)
      }
    }

    describe("::withTranslation") {
      it("creates a copy of itself with the new translation") { fixture =>
        // Given
        val translation = Vector3f(6, 4, 2)

        val expectedTranslation = Vector3f(6, 4, 2)
        val expectedCopy = Location(World.OVERWORLD, expectedTranslation)

        // When
        val copy = fixture.withTranslation(translation)

        // Then
        assert(!copy.eq(expectedCopy))
        assert(copy == expectedCopy)
      }
    }

    describe("::withRotation") {
      it("creates a copy of itself with the new rotation") { fixture =>
        // Given
        val rotation = Vector3f(45, 90)

        val expectedTranslation = Vector3f(2, 4, 6)
        val expectedRotation = Vector3f(45, 90)
        val expectedCopy =
          Location(World.OVERWORLD, expectedTranslation, expectedRotation)

        // When
        val copy = fixture.withRotation(rotation)

        // Then
        assert(!copy.eq(expectedCopy))
        assert(copy == expectedCopy)
      }
    }

    describe("::withX") {
      it("creates a copy of itself with the new x coordinate") { fixture =>
        // Given
        val x = 8

        val expectedTranslation = Vector3f(8, 4, 6)
        val expectedCopy = Location(World.OVERWORLD, expectedTranslation)

        // When
        val copy = fixture.withX(x)

        // Then
        assert(!copy.eq(expectedCopy))
        assert(copy == expectedCopy)
      }
    }

    describe("::withY") {
      it("creates a copy of itself with the new y coordinate") { fixture =>
        // Given
        val y = 8

        val expectedTranslation = Vector3f(2, 8, 6)
        val expectedCopy = Location(World.OVERWORLD, expectedTranslation)

        // When
        val copy = fixture.withY(y)

        // Then
        assert(!copy.eq(expectedCopy))
        assert(copy == expectedCopy)
      }
    }

    describe("::withZ") {
      it("creates a copy of itself with the new z coordinate") { fixture =>
        // Given
        val z = 8

        val expectedTranslation = Vector3f(2, 4, 8)
        val expectedCopy = Location(World.OVERWORLD, expectedTranslation)

        // When
        val copy = fixture.withZ(z)

        // Then
        assert(!copy.eq(expectedCopy))
        assert(copy == expectedCopy)
      }
    }

    describe("::withPitch") {
      it("creates a copy of itself with the new pitch") { fixture =>
        // Given
        val pitch = 45f

        val expectedTranslation = Vector3f(2, 4, 6)
        val expectedRotation = Vector3f(45, 0)
        val expectedCopy =
          Location(World.OVERWORLD, expectedTranslation, expectedRotation)

        // When
        val copy = fixture.withPitch(pitch)

        // Then
        assert(!copy.eq(expectedCopy))
        assert(copy == expectedCopy)
      }
    }

    describe("::withYaw") {
      it("creates a copy of itself with the new z coordinate") { fixture =>
        // Given
        val yaw = 45f

        val expectedTranslation = Vector3f(2, 4, 6)
        val expectedRotation = Vector3f(0, 45)
        val expectedCopy =
          Location(World.OVERWORLD, expectedTranslation, expectedRotation)

        // When
        val copy = fixture.withYaw(yaw)

        // Then
        assert(!copy.eq(expectedCopy))
        assert(copy == expectedCopy)
      }
    }
  }
}
