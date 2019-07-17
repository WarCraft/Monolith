package gg.warcraft.monolith.api.math

import org.scalactic.TolerantNumerics
import org.scalatest.{FlatSpec, GivenWhenThen}

class Vector3fSpec extends FlatSpec with GivenWhenThen {
  implicit val _ = TolerantNumerics.tolerantFloatEquality(1e-6f)

  private val inverseTanOneDivSqrtTwoToDegrees =
    Math.toDegrees(Math.atan(1.0 / Math.sqrt(2.0))).toFloat

  "pitch yaw factory" should "accept the lower bound pitch edge case" in {
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

  // TODO have a look at these 4
  //  it should "accept the lower bound yaw edge case" in {
  //    assert(clampYaw(-180) === -180f)
  //  }
  //
  //  it should "accept the upper bound yaw edge case" in {
  //    assert(clampYaw(180 - 1e-5f) === 180f - 1e-5f)
  //  }
  //
  //  it should "adjust the lower bound yaw edge case" in {
  //    assert(clampYaw(-180 - 1e-5f) === 180f - 1e-5f)
  //  }
  //
  //  it should "adjust the upper bound yaw edge case" in {
  //    assert(clampYaw(180) === -180f)
  //  }

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

  "toPitchYaw" should "calculate up pitch yaw" in {
    // Given
    val direction = new Vector3f(0, 1, 0)

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
    val direction = new Vector3f(0, 5, 0)

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
    val direction = new Vector3f(0, -1, 0)

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
    val direction = new Vector3f(0, 0, -1)

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
    val direction = new Vector3f(1, 0, 0)

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
    val direction = new Vector3f(0, 0, 1)

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
    val direction = new Vector3f(-1, 0, 0)

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
    val direction = new Vector3f(1, 1, -1)

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
    val direction = new Vector3f(1, -1, -1)

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
    val direction = new Vector3f(1, 1, 1)

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
    val direction = new Vector3f(1, -1, 1)

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
    val direction = new Vector3f(-1, 1, 1)

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
    val direction = new Vector3f(-1, -1, 1)

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
    val direction = new Vector3f(-1, 1, -1)

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
    val direction = new Vector3f(-1, -1, -1)

    val expectedPitch = inverseTanOneDivSqrtTwoToDegrees
    val expectedYaw = 135f

    // When
    val rotation = direction.toPitchYaw

    // Then
    assert(rotation._1 === expectedPitch)
    assert(rotation._2 === expectedYaw)
  }
}
