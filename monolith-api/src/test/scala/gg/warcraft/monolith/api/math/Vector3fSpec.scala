package gg.warcraft.monolith.api.math

import org.scalactic.{Equality, TolerantNumerics}
import org.scalatest.{fixture, Outcome}

class Vector3fSpec extends fixture.FunSpec {
  private implicit val floatEq: Equality[Float] =
    TolerantNumerics.tolerantFloatEquality(1e-6f)

  private val inverseTanOneDivSqrtTwoToDegrees =
    Math.toDegrees(Math.atan(1.0 / Math.sqrt(2.0))).toFloat

  type FixtureParam = Vector3f

  override def withFixture(test: OneArgTest): Outcome = {
    val fixture = Vector3f(2, 4, 6)
    try test(fixture)
    finally {}
  }

  describe("Vector3f") {
    describe(".lengthSquared") {
      it("contains the squared length") { fixture =>
        // Given
        val expectedLengthSquared = 56f

        // When
        val lengthSquared = fixture.lengthSquared

        // Then
        assert(lengthSquared === expectedLengthSquared)
      }
    }

    describe(".length") {
      it("contains the length") { fixture =>
        // Given
        val expectedLength = Math.sqrt(56f).toFloat

        // When
        val length = fixture.length

        // Then
        assert(length === expectedLength)
      }
    }

    describe(".inverseLength") {
      it("contains the inverse length") { fixture =>
        // Given
        val expectedInverseLength = 1f / Math.sqrt(56f).toFloat

        // When
        val inverseLength = fixture.inverseLength

        // Then
        assert(inverseLength === expectedInverseLength)
      }
    }

    describe("::this(Float, Float)") {
      it("accepts the lower bound pitch edge case") { _ =>
        Vector3f(-90, 0)
      }

      it("accepts the upper bound pitch edge case") { _ =>
        Vector3f(-90, 0)
      }

      it("rejects the lower bound pitch edge case") { _ =>
        assertThrows[IllegalArgumentException] {
          Vector3f(-90 - 1e-5f, 0)
        }
      }

      it("rejects the upper bound pitch edge case") { _ =>
        assertThrows[IllegalArgumentException] {
          Vector3f(90 + 1e-5f, 0)
        }
      }

      it("calculates an up rotation") { _ =>
        // Given
        val pitch = -90f
        val yaw = 0f

        val expectedRotation = Vector3f(0, 1, 0)

        // When
        val rotation = Vector3f(pitch, yaw)

        // Then
        assert(rotation.x === expectedRotation.x)
        assert(rotation.y === expectedRotation.y)
        assert(rotation.z === expectedRotation.z)
      }

      it("calculates a down rotation") { _ =>
        // Given
        val pitch = 90f
        val yaw = 0f

        val expectedRotation = Vector3f(0, -1, 0)

        // When
        val rotation = Vector3f(pitch, yaw)

        // Then
        assert(rotation.x === expectedRotation.x)
        assert(rotation.y === expectedRotation.y)
        assert(rotation.z === expectedRotation.z)
      }

      it("calculates a north rotation") { _ =>
        // Given
        val pitch = 0f
        val yaw = -180f

        val expectedRotation = Vector3f(0, 0, -1)

        // When
        val rotation = Vector3f(pitch, yaw)

        // Then
        assert(rotation.x === expectedRotation.x)
        assert(rotation.y === expectedRotation.y)
        assert(rotation.z === expectedRotation.z)
      }

      it("calculates an east rotation") { _ =>
        // Given
        val pitch = 0f
        val yaw = -90f

        val expectedRotation = Vector3f(1, 0, 0)

        // When
        val rotation = Vector3f(pitch, yaw)

        // Then
        assert(rotation.x === expectedRotation.x)
        assert(rotation.y === expectedRotation.y)
        assert(rotation.z === expectedRotation.z)
      }

      it("calculates a south rotation") { _ =>
        // Given
        val pitch = 0f
        val yaw = 0f

        val expectedRotation = Vector3f(0, 0, 1)

        // When
        val rotation = Vector3f(pitch, yaw)

        // Then
        assert(rotation.x === expectedRotation.x)
        assert(rotation.y === expectedRotation.y)
        assert(rotation.z === expectedRotation.z)
      }

      it("calculates a west rotation") { _ =>
        // Given
        val pitch = 0f
        val yaw = 90f

        val expectedRotation = Vector3f(-1, 0, 0)

        // When
        val rotation = Vector3f(pitch, yaw)

        // Then
        assert(rotation.x === expectedRotation.x)
        assert(rotation.y === expectedRotation.y)
        assert(rotation.z === expectedRotation.z)
      }

      it("calculates a north-east-up rotation") { _ =>
        // Given
        val pitch = -inverseTanOneDivSqrtTwoToDegrees
        val yaw = -135f

        val expectedRotation = Vector3f(1, 1, -1).normalize

        // When
        val rotation = Vector3f(pitch, yaw)

        // Then
        assert(rotation == expectedRotation)
      }

      it("calculates a north-east-down rotation") { _ =>
        // Given
        val pitch = inverseTanOneDivSqrtTwoToDegrees
        val yaw = -135f

        val expectedRotation = Vector3f(1, -1, -1).normalize

        // When
        val rotation = Vector3f(pitch, yaw)

        // Then
        assert(rotation == expectedRotation)
      }

      it("calculates a south-east-up rotation") { _ =>
        // Given
        val pitch = -inverseTanOneDivSqrtTwoToDegrees
        val yaw = -45f

        val expectedRotation = Vector3f(1, 1, 1).normalize

        // When
        val rotation = Vector3f(pitch, yaw)

        // Then
        assert(rotation == expectedRotation)
      }

      it("calculates a south-east-down rotation") { _ =>
        // Given
        val pitch = inverseTanOneDivSqrtTwoToDegrees
        val yaw = -45f

        val expectedRotation = Vector3f(1, -1, 1).normalize

        // When
        val rotation = Vector3f(pitch, yaw)

        // Then
        assert(rotation == expectedRotation)
      }

      it("calculates a south-west-up rotation") { _ =>
        // Given
        val pitch = -inverseTanOneDivSqrtTwoToDegrees
        val yaw = 45f

        val expectedRotation = Vector3f(-1, 1, 1).normalize

        // When
        val rotation = Vector3f(pitch, yaw)

        // Then
        assert(rotation == expectedRotation)
      }

      it("calculates a south-west-down rotation") { _ =>
        // Given
        val pitch = inverseTanOneDivSqrtTwoToDegrees
        val yaw = 45f

        val expectedRotation = Vector3f(-1, -1, 1).normalize

        // When
        val rotation = Vector3f(pitch, yaw)

        // Then
        assert(rotation == expectedRotation)
      }

      it("calculates a north-west-up rotation") { _ =>
        // Given
        val pitch = -inverseTanOneDivSqrtTwoToDegrees
        val yaw = 135f

        val expectedRotation = Vector3f(-1, 1, -1).normalize

        // When
        val rotation = Vector3f(pitch, yaw)

        // Then
        assert(rotation == expectedRotation)
      }

      it("calculates a north-west-down rotation") { _ =>
        // Given
        val pitch = inverseTanOneDivSqrtTwoToDegrees
        val yaw = 135f

        val expectedRotation = Vector3f(-1, -1, -1).normalize

        // When
        val rotation = Vector3f(pitch, yaw)

        // Then
        assert(rotation == expectedRotation)
      }
    }

    describe("::add(Float, Float, Float)") {
      it("creates a copy of itself with the coordinates added") { fixture =>
        // Given
        val x = 2
        val y = 4
        val z = 6

        val expectedCopy = Vector3f(4, 8, 12)

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

        val expectedCopy = Vector3f(4, 8, 12)

        // When
        val copy = fixture.add(vector)

        // Then
        assert(!copy.eq(expectedCopy))
        assert(copy == expectedCopy)
      }
    }

    describe("::subtract(Float, Float, Float)") {
      it("creates a copy of itself with the coordinates subtracted") { fixture =>
        // Given
        val x = 6
        val y = 4
        val z = 2

        val expectedCopy = Vector3f(-4, 0, 4)

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

        val expectedCopy = Vector3f(-4, 0, 4)

        // When
        val copy = fixture.subtract(vector)

        // Then
        assert(!copy.eq(expectedCopy))
        assert(copy == expectedCopy)
      }
    }

    describe("::multiply(Float)") {
      it("creates a copy of itself multiplied by the scalar") { fixture =>
        // Given
        val scalar = 2

        val expectedCopy = Vector3f(4, 8, 12)

        // When
        val copy = fixture.multiply(scalar)

        // Then
        assert(!copy.eq(expectedCopy))
        assert(copy == expectedCopy)
      }
    }

    describe("::multiply(Vector3f)") {
      it("creates a copy of itself multiplied by the individual scalars") {
        fixture =>
          // Given
          val scalars = Vector3f(2, 4, 6)

          val expectedCopy = Vector3f(4, 16, 36)

          // When
          val copy = fixture.multiply(scalars)

          // Then
          assert(!copy.eq(expectedCopy))
          assert(copy == expectedCopy)
      }
    }

    describe("::distanceTo(Vector3f)") {
      it("calculates the distance between this and that") { fixture =>
        // Given
        val that = Vector3f(6, 4, 2)

        val expectedDistance = Math.sqrt(32).toFloat

        // When
        val distance = fixture.distanceTo(that)

        // Then
        assert(distance == expectedDistance)
      }
    }

    describe("::toPitchYaw") {
      it("calculate up pitch yaw") { _ =>
        // Given
        val direction = Vector3f(0, 1, 0)

        val expectedPitch = -90f
        val expectedYaw = 0f

        // When
        val rotation = direction.toPitchYaw

        // Then
        assert(rotation == (expectedPitch, expectedYaw))
      }

      it("should normalize vector calculating up pitch yaw") { _ =>
        // Given
        val direction = Vector3f(0, 5, 0)

        val expectedPitch = -90f
        val expectedYaw = 0f

        // When
        val rotation = direction.toPitchYaw

        // Then
        assert(rotation == (expectedPitch, expectedYaw))
      }

      it("calculate down pitch yaw") { _ =>
        // Given
        val direction = Vector3f(0, -1, 0)

        val expectedPitch = 90f
        val expectedYaw = 0f

        // When
        val rotation = direction.toPitchYaw

        // Then
        assert(rotation == (expectedPitch, expectedYaw))
      }

      it("calculate north pitch yaw") { _ =>
        // Given
        val direction = Vector3f(0, 0, -1)

        val expectedPitch = 0f
        val expectedYaw = -180f

        // When
        val rotation = direction.toPitchYaw

        // Then
        assert(rotation == (expectedPitch, expectedYaw))
      }

      it("calculate east pitch yaw") { _ =>
        // Given
        val direction = Vector3f(1, 0, 0)

        val expectedPitch = 0f
        val expectedYaw = -90f

        // When
        val rotation = direction.toPitchYaw

        // Then
        assert(rotation == (expectedPitch, expectedYaw))
      }

      it("calculate south pitch yaw") { _ =>
        // Given
        val direction = Vector3f(0, 0, 1)

        val expectedPitch = 0f
        val expectedYaw = 0f

        // When
        val rotation = direction.toPitchYaw

        // Then
        assert(rotation == (expectedPitch, expectedYaw))
      }

      it("calculate west pitch yaw") { _ =>
        // Given
        val direction = Vector3f(-1, 0, 0)

        val expectedPitch = 0f
        val expectedYaw = 90f

        // When
        val rotation = direction.toPitchYaw

        // Then
        assert(rotation == (expectedPitch, expectedYaw))
      }

      it("calculate north-east-up pitch yaw") { _ =>
        // Given
        val direction = Vector3f(1, 1, -1)

        val expectedPitch = -inverseTanOneDivSqrtTwoToDegrees
        val expectedYaw = -135f

        // When
        val rotation = direction.toPitchYaw

        // Then
        assert(rotation == (expectedPitch, expectedYaw))
      }

      it("calculate north-east-down pitch yaw") { _ =>
        // Given
        val direction = Vector3f(1, -1, -1)

        val expectedPitch = inverseTanOneDivSqrtTwoToDegrees
        val expectedYaw = -135f

        // When
        val rotation = direction.toPitchYaw

        // Then
        assert(rotation == (expectedPitch, expectedYaw))
      }

      it("calculate south-east-up pitch yaw") { _ =>
        // Given
        val direction = Vector3f(1, 1, 1)

        val expectedPitch = -inverseTanOneDivSqrtTwoToDegrees
        val expectedYaw = -45f

        // When
        val rotation = direction.toPitchYaw

        // Then
        assert(rotation == (expectedPitch, expectedYaw))
      }

      it("calculate south-east-down pitch yaw") { _ =>
        // Given
        val direction = Vector3f(1, -1, 1)

        val expectedPitch = inverseTanOneDivSqrtTwoToDegrees
        val expectedYaw = -45f

        // When
        val rotation = direction.toPitchYaw

        // Then
        assert(rotation == (expectedPitch, expectedYaw))
      }

      it("calculate south-west-up pitch yaw") { _ =>
        // Given
        val direction = Vector3f(-1, 1, 1)

        val expectedPitch = -inverseTanOneDivSqrtTwoToDegrees
        val expectedYaw = 45f

        // When
        val rotation = direction.toPitchYaw

        // Then
        assert(rotation == (expectedPitch, expectedYaw))
      }

      it("calculate south-west-down pitch yaw") { _ =>
        // Given
        val direction = Vector3f(-1, -1, 1)

        val expectedPitch = inverseTanOneDivSqrtTwoToDegrees
        val expectedYaw = 45f

        // When
        val rotation = direction.toPitchYaw

        // Then
        assert(rotation == (expectedPitch, expectedYaw))
      }

      it("calculate north-west-up pitch yaw") { _ =>
        // Given
        val direction = Vector3f(-1, 1, -1)

        val expectedPitch = -inverseTanOneDivSqrtTwoToDegrees
        val expectedYaw = 135f

        // When
        val rotation = direction.toPitchYaw

        // Then
        assert(rotation == (expectedPitch, expectedYaw))
      }

      it("calculate north-west-down pitch yaw") { _ =>
        // Given
        val direction = Vector3f(-1, -1, -1)

        val expectedPitch = inverseTanOneDivSqrtTwoToDegrees
        val expectedYaw = 135f

        // When
        val rotation = direction.toPitchYaw

        // Then
        assert(rotation == (expectedPitch, expectedYaw))
      }
    }

    describe("::toVector3i") {
      it("creates a copy of itself as a Vector3i") { fixture =>
        // Given
        val expectedVector = Vector3i(2, 4, 6)

        // When
        val location: Vector3i = fixture

        // Then
        assert(location == expectedVector)
      }
    }

    /* Java interop */

    describe("::withX") {
      it("creates a copy of itself with the new x coordinate") { fixture =>
        // Given
        val x = 8

        val expectedCopy = Vector3f(8, 4, 6)

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

        val expectedCopy = Vector3f(2, 8, 6)

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

        val expectedCopy = Vector3f(2, 4, 8)

        // When
        val copy = fixture.withZ(z)

        // Then
        assert(!copy.eq(expectedCopy))
        assert(copy == expectedCopy)
      }
    }
  }
}
