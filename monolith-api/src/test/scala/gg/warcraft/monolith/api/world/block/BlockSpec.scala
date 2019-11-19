package gg.warcraft.monolith.api.world.block

import gg.warcraft.monolith.api.math.Vector3i
import gg.warcraft.monolith.api.world.{BlockLocation, World}
import gg.warcraft.monolith.api.world.block.variant.DoorVariant
import org.scalatest.{fixture, Outcome}

class BlockSpec extends fixture.FunSpec {
  type FixtureParam = Door
  val location: BlockLocation = BlockLocation(World.OVERWORLD, Vector3i(1, 1, 1))

  override def withFixture(test: OneArgTest): Outcome = {
    val fixture = Door(
      location,
      DoorVariant.OAK,
      BlockFace.NORTH,
      BlockHinge.LEFT,
      BlockBisection.BOTTOM,
      false,
      false
    )
    try test(fixture)
    finally {}
  }

  describe("Door") {
    describe("::withOpen(Boolean)") {
      it("creates a copy of itself with the new state") { fixture =>
        // Given
        val expectedCopy =
          Door(
            location,
            DoorVariant.OAK,
            BlockFace.NORTH,
            BlockHinge.LEFT,
            BlockBisection.BOTTOM,
            true,
            false
          )

        // When
        val copy = fixture.withOpen(true)

        // Then
        assert(copy.open == expectedCopy.open)
      }
    }
  }
}
