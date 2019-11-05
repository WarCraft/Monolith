package gg.warcraft.monolith.api.math

import org.scalatest.{fixture, Outcome}

class Vector3iSpec extends fixture.FunSpec {
  type FixtureParam = Vector3i

  override def withFixture(test: OneArgTest): Outcome = {
    val fixture = Vector3i(2, 4, 6)
    try test(fixture)
    finally {}
  }

  describe("Vector3i") {
    describe("::add(Int, Int, Int)") {
      it("creates a copy of itself with the coordinates added") { fixture =>
        // Given
        val x = 2
        val y = 4
        val z = 6

        val expectedCopy = Vector3i(4, 8, 12)

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

        val expectedCopy = Vector3i(4, 8, 12)

        // When
        val copy = fixture.add(vector)

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

        val expectedCopy = Vector3i(-4, 0, 4)

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

        val expectedCopy = Vector3i(-4, 0, 4)

        // When
        val copy = fixture.subtract(vector)

        // Then
        assert(!copy.eq(expectedCopy))
        assert(copy == expectedCopy)
      }
    }

    describe("::multiply(Int)") {
      it("creates a copy of itself multiplied by the scalar") { fixture =>
        // Given
        val scalar = 2

        val expectedCopy = Vector3i(4, 8, 12)

        // When
        val copy = fixture.multiply(scalar)

        // Then
        assert(!copy.eq(expectedCopy))
        assert(copy == expectedCopy)
      }
    }

    describe("::multiply(Vector3i)") {
      it("creates a copy of itself multiplied by the individual scalars") {
        fixture =>
          // Given
          val scalars = Vector3i(2, 4, 6)

          val expectedCopy = Vector3i(4, 16, 36)

          // When
          val copy = fixture.multiply(scalars)

          // Then
          assert(!copy.eq(expectedCopy))
          assert(copy == expectedCopy)
      }
    }

    describe("::toVector3f") {
      it("creates a copy of itself as a Vector3f") { fixture =>
        // Given
        val expectedVector = Vector3f(2, 4, 6)

        // When
        val location = fixture.toVector3f

        // Then
        assert(location == expectedVector)
      }
    }

    /* Java interop */

    describe("::withX") {
      it("creates a copy of itself with the new x coordinate") { fixture =>
        // Given
        val x = 8

        val expectedCopy = Vector3i(8, 4, 6)

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

        val expectedCopy = Vector3i(2, 8, 6)

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

        val expectedCopy = Vector3i(2, 4, 8)

        // When
        val copy = fixture.withZ(z)

        // Then
        assert(!copy.eq(expectedCopy))
        assert(copy == expectedCopy)
      }
    }
  }
}
