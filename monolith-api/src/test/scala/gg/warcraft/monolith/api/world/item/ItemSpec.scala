package gg.warcraft.monolith.api.world.item

import org.scalatest.{fixture, Outcome}

class ItemSpec extends fixture.FunSpec {
  type FixtureParam = PickAxe

  override def withFixture(test: OneArgTest): Outcome = {
    val fixture = PickAxe("Pick", Array(), 1)
    try test(fixture)
    finally {}
  }

  describe("PickAxe") {

    describe("::withName(String)") {

      it("creates a copy of itself with the new name") { fixture =>
        // Given
        val expectedCopy = PickAxe("Axe", Array(), 1)

        // When
        val copy = fixture.withName("Axe")

        // Then
        assert(copy.name == expectedCopy.name)
      }
    }
  }

  describe("PickAxe") {

    describe("::withDurability(Int)") {

      it("should throw an illegal argument exception") { fixture =>
        assertThrows[Throwable] {
          fixture.withDurability(2)
        }
      }
    }
  }
}
