package gg.warcraft.monolith.api.math

import org.scalatest.{FlatSpec, GivenWhenThen}

class Vector3iSpec extends FlatSpec with GivenWhenThen {

  "add" should "add to vector" in {
    // Given
    val vector = Vector3i(2, 4, 6)

    val expectedUpdatedVector = Vector3i(4, 8, 12)

    // When
    val updatedVector = vector.add(2, 4, 6)

    // Then
    assert(updatedVector.x === expectedUpdatedVector.x)
    assert(updatedVector.y === expectedUpdatedVector.y)
    assert(updatedVector.z === expectedUpdatedVector.z)
  }

  "addVector" should "add to vector" in {
    // Given
    val vector = Vector3i(2, 4, 6)
    val updateVector = Vector3i(2, 4, 6)

    val expectedUpdatedVector = Vector3i(4, 8, 12)

    // When
    val updatedVector = vector.add(updateVector)

    // Then
    assert(updatedVector.x === expectedUpdatedVector.x)
    assert(updatedVector.y === expectedUpdatedVector.y)
    assert(updatedVector.z === expectedUpdatedVector.z)
  }

  "sub" should "subtract from vector" in {
    // Given
    val vector = Vector3i(2, 4, 6)

    val expectedUpdatedVector = Vector3i(-4, 0, 4)

    // When
    val updatedVector = vector.sub(6, 4, 2)

    // Then
    assert(updatedVector.x === expectedUpdatedVector.x)
    assert(updatedVector.y === expectedUpdatedVector.y)
    assert(updatedVector.z === expectedUpdatedVector.z)
  }

  "subVector" should "subtract from vector" in {
    // Given
    val vector = Vector3i(2, 4, 6)
    val updateVector = Vector3i(6, 4, 2)

    val expectedUpdatedVector = Vector3i(-4, 0, 4)

    // When
    val updatedVector = vector.sub(updateVector)

    // Then
    assert(updatedVector.x === expectedUpdatedVector.x)
    assert(updatedVector.y === expectedUpdatedVector.y)
    assert(updatedVector.z === expectedUpdatedVector.z)
  }

  "mul" should "multiply by scalar" in {
    // Given
    val vector = Vector3i(2, 4, 6)

    val expectedUpdatedVector = Vector3i(4, 8, 12)

    // When
    val updatedVector = vector.mul(2)

    // Then
    assert(updatedVector.x === expectedUpdatedVector.x)
    assert(updatedVector.y === expectedUpdatedVector.y)
    assert(updatedVector.z === expectedUpdatedVector.z)
  }

  "mulVector" should "multiply by individual scalars" in {
    // Given
    val vector = Vector3i(2, 4, 6)
    val updateVector = Vector3i(2, 4, 6)

    val expectedUpdatedVector = Vector3i(4, 16, 36)

    // When
    val updatedVector = vector.mul(updateVector)

    // Then
    assert(updatedVector.x === expectedUpdatedVector.x)
    assert(updatedVector.y === expectedUpdatedVector.y)
    assert(updatedVector.z === expectedUpdatedVector.z)
  }

  "toVector3f" should "create a Vector3f" in {
    // Given
    val vector3i = Vector3i(2, 4, 6)

    val expectedVector3f = Vector3f(2, 4, 6)

    // When
    val vector3f = vector3i.toVector3f

    // Then
    assert(vector3f.x === expectedVector3f.x)
    assert(vector3f.y === expectedVector3f.y)
    assert(vector3f.z === expectedVector3f.z)
  }

  /* Java interop */

  "getX" should "return x" in {
    // Given
    val vector = Vector3i(2, 4, 6)

    val expectedX = 2

    // When
    val x = vector.getX

    // Then
    assert(x === expectedX)
  }

  "getY" should "return y" in {
    // Given
    val vector = Vector3i(2, 4, 6)

    val expectedY = 4

    // When
    val y = vector.getY

    // Then
    assert(y === expectedY)
  }

  "getZ" should "return z" in {
    // Given
    val vector = Vector3i(2, 4, 6)

    val expectedZ = 6

    // When
    val z = vector.getZ

    // Then
    assert(z === expectedZ)
  }

  "withX" should "copy with x" in {
    // Given
    val vector = Vector3i(2, 4, 6)

    val expectedCopy = Vector3i(8, 4, 6)

    // When
    val copy = vector.withX(8)

    // Then
    assert(copy.x === expectedCopy.x)
    assert(copy.y === expectedCopy.y)
    assert(copy.z === expectedCopy.z)
  }

  "withY" should "copy with y" in {
    // Given
    val vector = Vector3i(2, 4, 6)

    val expectedCopy = Vector3i(2, 8, 6)

    // When
    val copy = vector.withY(8)

    // Then
    assert(copy.x === expectedCopy.x)
    assert(copy.y === expectedCopy.y)
    assert(copy.z === expectedCopy.z)
  }

  "withZ" should "copy with z" in {
    // Given
    val vector = Vector3i(2, 4, 6)

    val expectedCopy = Vector3i(2, 4, 8)

    // When
    val copy = vector.withZ(8)

    // Then
    assert(copy.x === expectedCopy.x)
    assert(copy.y === expectedCopy.y)
    assert(copy.z === expectedCopy.z)
  }
}
