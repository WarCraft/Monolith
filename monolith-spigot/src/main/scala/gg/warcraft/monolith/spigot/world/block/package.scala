package gg.warcraft.monolith.spigot.world

import org.bukkit.block.{Block, BlockState, Sign}
import org.bukkit.block.data.{BlockData, Rail}
import org.bukkit.block.data.`type`._
import org.bukkit.event.block.{BlockBreakEvent, BlockPlaceEvent}
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.Event

package object block {
  // Block meta types
  type SpigotBlock = Block
  type SpigotBlockData = BlockData
  type SpigotBlockState = BlockState

  // Spigot block types
  type SpigotBamboo = Bamboo
  type SpigotBed = Bed
  type SpigotBubbleColumn = BubbleColumn
  type SpigotCake = Cake
  type SpigotCampfire = Campfire
  type SpigotCommandBlock = CommandBlock
  type SpigotComparator = Comparator
  type SpigotDoor = Door
  type SpigotDispenser = Dispenser
  type SpigotDropper = Dispenser
  type SpigotEndPortalFrame = EndPortalFrame
  type SpigotHopper = Hopper
  type SpigotJukebox = Jukebox
  type SpigotLantern = Lantern
  type SpigotLectern = Lectern
  type SpigotNoteBlock = NoteBlock
  type SpigotPiston = Piston
  type SpigotPistonHead = PistonHead
  type SpigotRail = Rail
  type SpigotRepeater = Repeater
  type SpigotSapling = Sapling
  type SpigotSeaPickle = SeaPickle
  type SpigotSign = Sign
  type SpigotSlab = Slab
  type SpigotStairs = Stairs
  type SpigotStructureBlock = StructureBlock
  type SpigotTNT = TNT
  type SpigotTripwire = Tripwire
  type SpigotTripwireHook = TripwireHook
  type SpigotTurtleEgg = TurtleEgg

  // Block event types
  type SpigotEvent = Event
  type SpigotBlockBreakEvent = BlockBreakEvent
  type SpigotBlockPlaceEvent = BlockPlaceEvent
  type SpigotPlayerInteractEvent = PlayerInteractEvent
}
