/*
 * MIT License
 *
 * Copyright (c) 2020 WarCraft <https://github.com/WarCraft>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package gg.warcraft.monolith.spigot

import org.bukkit.block.data.`type`._
import org.bukkit.block.data.{BlockData, Rail}
import org.bukkit.block.{Block, BlockFace, BlockState, Sign}
import org.bukkit.event.Event
import org.bukkit.event.block.{BlockBreakEvent, BlockPlaceEvent}
import org.bukkit.event.player.PlayerInteractEvent

package object block {
  // Block meta types
  type SpigotBlock = Block
  type SpigotBlockData = BlockData
  type SpigotBlockFace = BlockFace
  type SpigotBlockState = BlockState

  // Spigot block types
  type SpigotBamboo = Bamboo
  type SpigotBed = Bed
  type SpigotBigDripleaf = BigDripleaf
  type SpigotBubbleColumn = BubbleColumn
  type SpigotCake = Cake
  type SpigotCampfire = Campfire
  type SpigotCaveVines = CaveVines
  type SpigotCaveVinesPlant = CaveVinesPlant
  type SpigotCommandBlock = CommandBlock
  type SpigotComparator = Comparator
  type SpigotCandle = Candle
  type SpigotDoor = Door
  type SpigotDispenser = Dispenser
  type SpigotDripstone = PointedDripstone
  type SpigotDropper = Dispenser
  type SpigotEndPortalFrame = EndPortalFrame
  type SpigotGlowLichen = GlowLichen
  type SpigotHopper = Hopper
  type SpigotJigsaw = Jigsaw
  type SpigotJukebox = Jukebox
  type SpigotLantern = Lantern
  type SpigotLectern = Lectern
  type SpigotLightBlock = Light
  type SpigotNoteBlock = NoteBlock
  type SpigotPinkPetals = PinkPetals
  type SpigotPiston = Piston
  type SpigotPistonHead = PistonHead
  type SpigotRail = Rail
  type SpigotRepeater = Repeater
  type SpigotRespawnAnchor = RespawnAnchor
  type SpigotSapling = Sapling
  type SpigotSculkVein = SculkVein
  type SpigotSeaPickle = SeaPickle
  type SpigotSign = Sign
  type SpigotSlab = Slab
  type SpigotStairs = Stairs
  type SpigotStructureBlock = StructureBlock
  type SpigotTNT = TNT
  type SpigotTripwire = Tripwire
  type SpigotTripwireHook = TripwireHook
  type SpigotTurtleEgg = TurtleEgg
  type SpigotWall = Wall

  // Block event types
  type SpigotEvent = Event
  type SpigotBlockBreakEvent = BlockBreakEvent
  type SpigotBlockPlaceEvent = BlockPlaceEvent
  type SpigotPlayerInteractEvent = PlayerInteractEvent
}
