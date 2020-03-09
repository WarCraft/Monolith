package gg.warcraft.monolith.api.world

import gg.warcraft.monolith.api.math.{Vector3f, Vector3i}
import org.scalatest.{fixture, Outcome}

class BlockLocationSpec extends fixture.FunSpec {
  type FixtureParam = BlockLocation

  override def withFixture(test: OneArgTest): Outcome = {
    val translation = Vector3i(2, 4, 6)
    val fixture = BlockLocation(World.OVERWORLD, translation)
    try test(fixture)
    finally {}
  }

  describe("BlockLocation") {
    describe("::add(Int, Int, Int)") {
      it("creates a copy of itself with the coordinates added") { fixture =>
        // Given
        val x = 2
        val y = 4
        val z = 6

        val expectedTranslation = Vector3i(4, 8, 12)
        val expectedCopy = BlockLocation(World.OVERWORLD, expectedTranslation)

        // When
        val copy = fixture.add(x, y, z)

        // Then
        assert(!copy.eq(expectedCopy))
        assert(copy == expectedCopy)
      }
    }

    describe("::add(Vector3i)") {
      it("creates a copy of itself with the vector added") { fixture =>
        // Given
        val vector = Vector3i(2, 4, 6)

        val expectedTranslation = Vector3i(4, 8, 12)
        val expectedCopy = BlockLocation(World.OVERWORLD, expectedTranslation)

        // When
        val copy = fixture.add(vector)

        // Then
        assert(!copy.eq(expectedCopy))
        assert(copy == expectedCopy)
      }
    }

    describe("::add(BlockLocation)") {
      it("creates a copy of itself with the location added") { fixture =>
        // Given
        val translation = Vector3i(2, 4, 6)
        val location = BlockLocation(World.OVERWORLD, translation)

        val expectedTranslation = Vector3i(4, 8, 12)
        val expectedCopy = BlockLocation(World.OVERWORLD, expectedTranslation)

        // When
        val copy = fixture.add(location)

        // Then
        assert(!copy.eq(expectedCopy))
        assert(copy == expectedCopy)
      }
    }

    describe("::subtract(Int, Int, Int)") {
      it("creates a copy of itself with the coordinates subtracted") { fixture =>
        // Given
        val x = 6
        val y = 4
        val z = 2

        val expectedTranslation = Vector3i(-4, 0, 4)
        val expectedCopy = BlockLocation(World.OVERWORLD, expectedTranslation)

        // When
        val copy = fixture.subtract(x, y, z)

        // Then
        assert(!copy.eq(expectedCopy))
        assert(copy == expectedCopy)
      }
    }

    describe("::subtract(Vector3i)") {
      it("creates a copy of itself with the vector subtracted") { fixture =>
        // Given
        val vector = Vector3i(6, 4, 2)

        val expectedTranslation = Vector3i(-4, 0, 4)
        val expectedCopy = BlockLocation(World.OVERWORLD, expectedTranslation)

        // When
        val copy = fixture.subtract(vector)

        // Then
        assert(!copy.eq(expectedCopy))
        assert(copy == expectedCopy)
      }
    }

    describe("::subtract(BlockLocation)") {
      it("creates a copy of itself with the location subtracted") { fixture =>
        // Given
        val translation = Vector3i(6, 4, 2)
        val location = BlockLocation(World.OVERWORLD, translation)

        val expectedTranslation = Vector3i(-4, 0, 4)
        val expectedCopy = BlockLocation(World.OVERWORLD, expectedTranslation)

        // When
        val copy = fixture.subtract(location)

        // Then
        assert(!copy.eq(expectedCopy))
        assert(copy == expectedCopy)
      }
    }

    describe("::toLocation") {
      it("creates a copy of itself as a Location") { fixture =>
        // Given
        val expectedTranslation = Vector3f(2, 4, 6)
        val expectedLocation = Location(World.OVERWORLD, expectedTranslation)

        // When
        val location: Location = fixture

        // Then
        assert(location == expectedLocation)
      }
    }

    /* Java interop */

    describe("::withWorld") {
      it("creates a copy of itself with the new world") { fixture =>
        // Given
        val world = World.THE_NETHER

        val expectedTranslation = Vector3i(2, 4, 6)
        val expectedCopy = BlockLocation(World.THE_NETHER, expectedTranslation)

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
        val translation = Vector3i(6, 4, 2)

        val expectedTranslation = Vector3i(6, 4, 2)
        val expectedCopy = BlockLocation(World.OVERWORLD, expectedTranslation)

        // When
        val copy = fixture.withTranslation(translation)

        // Then
        assert(!copy.eq(expectedCopy))
        assert(copy == expectedCopy)
      }
    }

    describe("::withX") {
      it("creates a copy of itself with the new x coordinate") { fixture =>
        // Given
        val x = 8

        val expectedTranslation = Vector3i(8, 4, 6)
        val expectedCopy = BlockLocation(World.OVERWORLD, expectedTranslation)

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

        val expectedTranslation = Vector3i(2, 8, 6)
        val expectedCopy = BlockLocation(World.OVERWORLD, expectedTranslation)

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

        val expectedTranslation = Vector3i(2, 4, 8)
        val expectedCopy = BlockLocation(World.OVERWORLD, expectedTranslation)

        // When
        val copy = fixture.withZ(z)

        // Then
        assert(!copy.eq(expectedCopy))
        assert(copy == expectedCopy)
      }
    }
  }
}
