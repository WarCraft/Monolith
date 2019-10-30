package gg.warcraft.monolith.api.world.item

import gg.warcraft.monolith.api.world.block.BlockColor
import org.scalatest.{ fixture, Outcome }

class ItemSpec extends fixture.FunSpec {
  type FixtureParam = Bed

  override def withFixture(test: OneArgTest): Outcome = {
    val fixture = Bed(BlockColor.RED, ItemData(BlockColor.RED, "Bed"))
    try test(fixture)
    finally {}
  }

  describe("Bed") {

    describe("::withName(String)") {

      it("creates a copy of itself with the new name") { fixture =>
        // Given
        val expectedCopy = Bed(BlockColor.RED, ItemData("Super Bed"))

        // When
        val copy = fixture.withName("Super Bed")

        // Then
        assert(copy.name == expectedCopy.name)
      }

      it("grabs the correct mapper branch") { fixture =>
        val result = "ANDESITE" match {
          case r"ANVIL"      => 1
          case r".*ANDESITE" => 2
          case _             => 3
        }

        assert(result == 2)
      }

      it("grabs the correct mapper branch 2") { fixture =>
        val result = "POLISHED_ANDESITE" match {
          case r"ANVIL"      => 1
          case r".*ANDESITE" => 2
          case _             => 3
        }

        assert(result == 2)
      }
    }

    //  describe("PickAxe") {
    //
    //    describe("::withDurability(Int)") {
    //
    //      it("should throw an illegal argument exception") { fixture =>
    //        assertThrows[Throwable] {
    //          fixture.withDurability(2)
    //        }
    //      }
    //    }
    //  }
  }
}
