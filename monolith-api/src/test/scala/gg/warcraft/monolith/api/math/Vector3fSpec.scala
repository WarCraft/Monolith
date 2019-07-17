package gg.warcraft.monolith.api.math

import org.scalactic.TolerantNumerics
import org.scalatest.{FlatSpec, GivenWhenThen}

class Vector3fSpec extends FlatSpec with GivenWhenThen {
  implicit val _ = TolerantNumerics.tolerantFloatEquality(1e-6f)

  private val inverseTanOneDivSqrtTwoToDegrees =
    Math.toDegrees(Math.atan(1.0 / Math.sqrt(2.0))).toFloat

  "pitchYawFactory" should "accept the lower bound pitch edge case" in {
    Vector3f(-90, 0)
  }

  it should "accept the upper bound pitch edge case" in {
    Vector3f(-90, 0)
  }

  it should "reject the lower bound pitch edge case" in {
    assertThrows[IllegalArgumentException] {
      Vector3f(-90 - 1e-5f, 0)
    }
  }

  it should "reject the upper bound pitch edge case" in {
    assertThrows[IllegalArgumentException] {
      Vector3f(90 + 1e-5f, 0)
    }
  }

  it should "calculate up direction" in {
    // Given
    val pitch = -90f
    val yaw = 0f

    val expectedDirection = Vector3f(0, 1, 0)

    // When
    val direction = Vector3f(pitch, yaw)

    // Then
    assert(direction.x === expectedDirection.x)
    assert(direction.y === expectedDirection.y)
    assert(direction.z === expectedDirection.z)
  }

  it should "calculate down direction" in {
    // Given
    val pitch = 90f
    val yaw = 0f

    val expectedDirection = Vector3f(0, -1, 0)

    // When
    val direction = Vector3f(pitch, yaw)

    // Then
    assert(direction.x === expectedDirection.x)
    assert(direction.y === expectedDirection.y)
    assert(direction.z === expectedDirection.z)
  }

  it should "calculate north direction" in {
    // Given
    val pitch = 0f
    val yaw = -180f

    val expectedDirection = Vector3f(0, 0, -1)

    // When
    val direction = Vector3f(pitch, yaw)

    // Then
    assert(direction.x === expectedDirection.x)
    assert(direction.y === expectedDirection.y)
    assert(direction.z === expectedDirection.z)
  }

  it should "calculate east direction" in {
    // Given
    val pitch = 0f
    val yaw = -90f

    val expectedDirection = Vector3f(1, 0, 0)

    // When
    val direction = Vector3f(pitch, yaw)

    // Then
    assert(direction.x === expectedDirection.x)
    assert(direction.y === expectedDirection.y)
    assert(direction.z === expectedDirection.z)
  }

  it should "calculate south direction" in {
    // Given
    val pitch = 0f
    val yaw = 0f

    val expectedDirection = Vector3f(0, 0, 1)

    // When
    val direction = Vector3f(pitch, yaw)

    // Then
    assert(direction.x === expectedDirection.x)
    assert(direction.y === expectedDirection.y)
    assert(direction.z === expectedDirection.z)
  }

  it should "calculate west direction" in {
    // Given
    val pitch = 0f
    val yaw = 90f

    val expectedDirection = Vector3f(-1, 0, 0)

    // When
    val direction = Vector3f(pitch, yaw)

    // Then
    assert(direction.x === expectedDirection.x)
    assert(direction.y === expectedDirection.y)
    assert(direction.z === expectedDirection.z)
  }

  it should "calculate north-east-up direction" in {
    // Given
    val pitch = -inverseTanOneDivSqrtTwoToDegrees
    val yaw = -135f

    val expectedDirection = Vector3f(1, 1, -1).normalize

    // When
    val direction = Vector3f(pitch, yaw)

    // Then
    assert(direction.x === expectedDirection.x)
    assert(direction.y === expectedDirection.y)
    assert(direction.z === expectedDirection.z)
  }

  it should "calculate north-east-down direction" in {
    // Given
    val pitch = inverseTanOneDivSqrtTwoToDegrees
    val yaw = -135f

    val expectedDirection = Vector3f(1, -1, -1).normalize

    // When
    val direction = Vector3f(pitch, yaw)

    // Then
    assert(direction.x === expectedDirection.x)
    assert(direction.y === expectedDirection.y)
    assert(direction.z === expectedDirection.z)
  }

  it should "calculate south-east-up direction" in {
    // Given
    val pitch = -inverseTanOneDivSqrtTwoToDegrees
    val yaw = -45f

    val expectedDirection = Vector3f(1, 1, 1).normalize

    // When
    val direction = Vector3f(pitch, yaw)

    // Then
    assert(direction.x === expectedDirection.x)
    assert(direction.y === expectedDirection.y)
    assert(direction.z === expectedDirection.z)
  }

  it should "calculate south-east-down direction" in {
    // Given
    val pitch = inverseTanOneDivSqrtTwoToDegrees
    val yaw = -45f

    val expectedDirection = Vector3f(1, -1, 1).normalize

    // When
    val direction = Vector3f(pitch, yaw)

    // Then
    assert(direction.x === expectedDirection.x)
    assert(direction.y === expectedDirection.y)
    assert(direction.z === expectedDirection.z)
  }

  it should "calculate south-west-up direction" in {
    // Given
    val pitch = -inverseTanOneDivSqrtTwoToDegrees
    val yaw = 45f

    val expectedDirection = Vector3f(-1, 1, 1).normalize

    // When
    val direction = Vector3f(pitch, yaw)

    // Then
    assert(direction.x === expectedDirection.x)
    assert(direction.y === expectedDirection.y)
    assert(direction.z === expectedDirection.z)
  }

  it should "calculate south-west-down direction" in {
    // Given
    val pitch = inverseTanOneDivSqrtTwoToDegrees
    val yaw = 45f

    val expectedDirection = Vector3f(-1, -1, 1).normalize

    // When
    val direction = Vector3f(pitch, yaw)

    // Then
    assert(direction.x === expectedDirection.x)
    assert(direction.y === expectedDirection.y)
    assert(direction.z === expectedDirection.z)
  }

  it should "calculate north-west-up direction" in {
    // Given
    val pitch = -inverseTanOneDivSqrtTwoToDegrees
    val yaw = 135f

    val expectedDirection = Vector3f(-1, 1, -1).normalize

    // When
    val direction = Vector3f(pitch, yaw)

    // Then
    assert(direction.x === expectedDirection.x)
    assert(direction.y === expectedDirection.y)
    assert(direction.z === expectedDirection.z)
  }

  it should "calculate north-west-down direction" in {
    // Given
    val pitch = inverseTanOneDivSqrtTwoToDegrees
    val yaw = 135f

    val expectedDirection = Vector3f(-1, -1, -1).normalize

    // When
    val direction = Vector3f(pitch, yaw)

    // Then
    assert(direction.x === expectedDirection.x)
    assert(direction.y === expectedDirection.y)
    assert(direction.z === expectedDirection.z)
  }

  "lengthSquared" should "should contain the squared length" in {
    // Given
    val vector = Vector3f(2, 2, 2)

    val expectedLengthSquared = 12f

    // When
    val lengthSquared = vector.lengthSquared

    // Then
    assert(lengthSquared === expectedLengthSquared)
  }

  "length" should "contain the length" in {
    // Given
    val vector = Vector3f(2, 2, 2)

    val expectedLength = Math.sqrt(12).toFloat

    // When
    val length = vector.length

    // Then
    assert(length === expectedLength)
  }

  "inverseLength" should "contain the inverse length" in {
    // Given
    val vector = Vector3f(2, 2, 2)

    val expectedInverseLength = 1f / Math.sqrt(12).toFloat

    // When
    val inverseLength = vector.inverseLength

    // Then
    assert(inverseLength === expectedInverseLength)
  }

  "add" should "add to vector" in {
    // Given
    val vector = Vector3f(2, 4, 6)

    val expectedUpdatedVector = Vector3f(4, 8, 12)

    // When
    val updatedVector = vector.add(2, 4, 6)

    // Then
    assert(updatedVector.x === expectedUpdatedVector.x)
    assert(updatedVector.y === expectedUpdatedVector.y)
    assert(updatedVector.z === expectedUpdatedVector.z)
  }

  "addVector" should "add to vector" in {
    // Given
    val vector = Vector3f(2, 4, 6)
    val updateVector = Vector3f(2, 4, 6)

    val expectedUpdatedVector = Vector3f(4, 8, 12)

    // When
    val updatedVector = vector.add(updateVector)

    // Then
    assert(updatedVector.x === expectedUpdatedVector.x)
    assert(updatedVector.y === expectedUpdatedVector.y)
    assert(updatedVector.z === expectedUpdatedVector.z)
  }

  "sub" should "subtract from vector" in {
    // Given
    val vector = Vector3f(2, 4, 6)

    val expectedUpdatedVector = Vector3f(-4, 0, 4)

    // When
    val updatedVector = vector.subtract(6, 4, 2)

    // Then
    assert(updatedVector.x === expectedUpdatedVector.x)
    assert(updatedVector.y === expectedUpdatedVector.y)
    assert(updatedVector.z === expectedUpdatedVector.z)
  }

  "subVector" should "subtract from vector" in {
    // Given
    val vector = Vector3f(2, 4, 6)
    val updateVector = Vector3f(6, 4, 2)

    val expectedUpdatedVector = Vector3f(-4, 0, 4)

    // When
    val updatedVector = vector.subtract(updateVector)

    // Then
    assert(updatedVector.x === expectedUpdatedVector.x)
    assert(updatedVector.y === expectedUpdatedVector.y)
    assert(updatedVector.z === expectedUpdatedVector.z)
  }

  "mul" should "multiply by scalar" in {
    // Given
    val vector = Vector3f(2, 4, 6)

    val expectedUpdatedVector = Vector3f(4, 8, 12)

    // When
    val updatedVector = vector.multiply(2)

    // Then
    assert(updatedVector.x === expectedUpdatedVector.x)
    assert(updatedVector.y === expectedUpdatedVector.y)
    assert(updatedVector.z === expectedUpdatedVector.z)
  }

  "mulVector" should "multiply by individual scalars" in {
    // Given
    val vector = Vector3f(2, 4, 6)
    val updateVector = Vector3f(2, 4, 6)

    val expectedUpdatedVector = Vector3f(4, 16, 36)

    // When
    val updatedVector = vector.multiply(updateVector)

    // Then
    assert(updatedVector.x === expectedUpdatedVector.x)
    assert(updatedVector.y === expectedUpdatedVector.y)
    assert(updatedVector.z === expectedUpdatedVector.z)
  }

  "dist" should "return distance from this vector to that vector" in {
    // Given
    val vector = Vector3f(2, 4, 6)
    val otherVector = Vector3f(6, 4, 2)

    val expectedDistance = Math.sqrt(32).toFloat

    // When
    val distance = vector.distanceTo(otherVector)

    // Then
    assert(distance === expectedDistance)
  }

  "toPitchYaw" should "calculate up pitch yaw" in {
    // Given
    val direction = Vector3f(0, 1, 0)

    val expectedPitch = -90f
    val expectedYaw = 0f

    // When
    val rotation = direction.toPitchYaw

    // Then
    assert(rotation._1 === expectedPitch)
    assert(rotation._2 === expectedYaw)
  }

  it should "should normalize vector calculating up pitch yaw" in {
    // Given
    val direction = Vector3f(0, 5, 0)

    val expectedPitch = -90f
    val expectedYaw = 0f

    // When
    val rotation = direction.toPitchYaw

    // Then
    assert(rotation._1 === expectedPitch)
    assert(rotation._2 === expectedYaw)
  }

  it should "calculate down pitch yaw" in {
    // Given
    val direction = Vector3f(0, -1, 0)

    val expectedPitch = 90f
    val expectedYaw = 0f

    // When
    val rotation = direction.toPitchYaw

    // Then
    assert(rotation._1 === expectedPitch)
    assert(rotation._2 === expectedYaw)
  }

  it should "calculate north pitch yaw" in {
    // Given
    val direction = Vector3f(0, 0, -1)

    val expectedPitch = 0f
    val expectedYaw = -180f

    // When
    val rotation = direction.toPitchYaw

    // Then
    assert(rotation._1 === expectedPitch)
    assert(rotation._2 === expectedYaw)
  }

  it should "calculate east pitch yaw" in {
    // Given
    val direction = Vector3f(1, 0, 0)

    val expectedPitch = 0f
    val expectedYaw = -90f

    // When
    val rotation = direction.toPitchYaw

    // Then
    assert(rotation._1 === expectedPitch)
    assert(rotation._2 === expectedYaw)
  }

  it should "calculate south pitch yaw" in {
    // Given
    val direction = Vector3f(0, 0, 1)

    val expectedPitch = 0f
    val expectedYaw = 0f

    // When
    val rotation = direction.toPitchYaw

    // Then
    assert(rotation._1 === expectedPitch)
    assert(rotation._2 === expectedYaw)
  }

  it should "calculate west pitch yaw" in {
    // Given
    val direction = Vector3f(-1, 0, 0)

    val expectedPitch = 0f
    val expectedYaw = 90f

    // When
    val rotation = direction.toPitchYaw

    // Then
    assert(rotation._1 === expectedPitch)
    assert(rotation._2 === expectedYaw)
  }

  it should "calculate north-east-up pitch yaw" in {
    // Given
    val direction = Vector3f(1, 1, -1)

    val expectedPitch = -inverseTanOneDivSqrtTwoToDegrees
    val expectedYaw = -135f

    // When
    val rotation = direction.toPitchYaw

    // Then
    assert(rotation._1 === expectedPitch)
    assert(rotation._2 === expectedYaw)
  }

  it should "calculate north-east-down pitch yaw" in {
    // Given
    val direction = Vector3f(1, -1, -1)

    val expectedPitch = inverseTanOneDivSqrtTwoToDegrees
    val expectedYaw = -135f

    // When
    val rotation = direction.toPitchYaw

    // Then
    assert(rotation._1 === expectedPitch)
    assert(rotation._2 === expectedYaw)
  }

  it should "calculate south-east-up pitch yaw" in {
    // Given
    val direction = Vector3f(1, 1, 1)

    val expectedPitch = -inverseTanOneDivSqrtTwoToDegrees
    val expectedYaw = -45f

    // When
    val rotation = direction.toPitchYaw

    // Then
    assert(rotation._1 === expectedPitch)
    assert(rotation._2 === expectedYaw)
  }

  it should "calculate south-east-down pitch yaw" in {
    // Given
    val direction = Vector3f(1, -1, 1)

    val expectedPitch = inverseTanOneDivSqrtTwoToDegrees
    val expectedYaw = -45f

    // When
    val rotation = direction.toPitchYaw

    // Then
    assert(rotation._1 === expectedPitch)
    assert(rotation._2 === expectedYaw)
  }

  it should "calculate south-west-up pitch yaw" in {
    // Given
    val direction = Vector3f(-1, 1, 1)

    val expectedPitch = -inverseTanOneDivSqrtTwoToDegrees
    val expectedYaw = 45f

    // When
    val rotation = direction.toPitchYaw

    // Then
    assert(rotation._1 === expectedPitch)
    assert(rotation._2 === expectedYaw)
  }

  it should "calculate south-west-down pitch yaw" in {
    // Given
    val direction = Vector3f(-1, -1, 1)

    val expectedPitch = inverseTanOneDivSqrtTwoToDegrees
    val expectedYaw = 45f

    // When
    val rotation = direction.toPitchYaw

    // Then
    assert(rotation._1 === expectedPitch)
    assert(rotation._2 === expectedYaw)
  }

  it should "calculate north-west-up pitch yaw" in {
    // Given
    val direction = Vector3f(-1, 1, -1)

    val expectedPitch = -inverseTanOneDivSqrtTwoToDegrees
    val expectedYaw = 135f

    // When
    val rotation = direction.toPitchYaw

    // Then
    assert(rotation._1 === expectedPitch)
    assert(rotation._2 === expectedYaw)
  }

  it should "calculate north-west-down pitch yaw" in {
    // Given
    val direction = Vector3f(-1, -1, -1)

    val expectedPitch = inverseTanOneDivSqrtTwoToDegrees
    val expectedYaw = 135f

    // When
    val rotation = direction.toPitchYaw

    // Then
    assert(rotation._1 === expectedPitch)
    assert(rotation._2 === expectedYaw)
  }

  "toVector3i" should "create a Vector3i" in {
    // Given
    val vector3f = Vector3f(1.5f, 2.7f, -3.9f)

    val expectedVector3i = Vector3i(1, 2, -3)

    // When
    val vector3i = vector3f.toVector3i

    // Then
    assert(vector3i.x === expectedVector3i.x)
    assert(vector3i.y === expectedVector3i.y)
    assert(vector3i.z === expectedVector3i.z)
  }

  /* Java interop */

  "withX" should "copy with x" in {
    // Given
    val vector = Vector3f(2, 4, 6)

    val expectedCopy = Vector3f(8, 4, 6)

    // When
    val copy = vector.withX(8)

    // Then
    assert(copy.x === expectedCopy.x)
    assert(copy.y === expectedCopy.y)
    assert(copy.z === expectedCopy.z)
  }

  "withY" should "copy with y" in {
    // Given
    val vector = Vector3f(2, 4, 6)

    val expectedCopy = Vector3f(2, 8, 6)

    // When
    val copy = vector.withY(8)

    // Then
    assert(copy.x === expectedCopy.x)
    assert(copy.y === expectedCopy.y)
    assert(copy.z === expectedCopy.z)
  }

  "withZ" should "copy with z" in {
    // Given
    val vector = Vector3f(2, 4, 6)

    val expectedCopy = Vector3f(2, 4, 8)

    // When
    val copy = vector.withZ(8)

    // Then
    assert(copy.x === expectedCopy.x)
    assert(copy.y === expectedCopy.y)
    assert(copy.z === expectedCopy.z)
  }
}
