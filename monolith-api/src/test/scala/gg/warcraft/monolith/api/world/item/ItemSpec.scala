package gg.warcraft.monolith.api.world.item

import gg.warcraft.monolith.api.world.item.`type`.SpawnEgg
import gg.warcraft.monolith.api.world.item.variant.SpawnEggVariant
import org.scalatest.{fixture, Outcome}

class ItemSpec extends fixture.FunSpec {
  type FixtureParam = SpawnEgg

  override def withFixture(test: OneArgTest): Outcome = {
    val fixture = SpawnEgg("Spawn", Array(), 0, 0, SpawnEggVariant.BAT)
    try test(fixture)
    finally {}
  }

  describe("SpawnEgg") {

    describe("::withName(String)") {

      it("creates a copy of itself with the new name") { fixture =>
        // Given
        val expectedCopy = SpawnEgg("Egg", Array(), 0, 0, SpawnEggVariant.BAT)

        // When
        val copy = fixture.withName("Egg")

        // Then
        assert(copy.name == expectedCopy.name)
      }
    }
  }
}
