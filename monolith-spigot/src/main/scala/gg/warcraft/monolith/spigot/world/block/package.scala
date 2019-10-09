package gg.warcraft.monolith.spigot.world

import org.bukkit.{Bukkit, Material}
import org.bukkit.block.{Block, BlockState, Sign}
import org.bukkit.block.data.BlockData
import org.bukkit.block.data.`type`._

package object block {

  // Bukkit and Spigot types
  type Spigot = Bukkit
  type SpigotBlock = Block
  type SpigotBlockData = BlockData
  type SpigotBlockState = BlockState

  // Spigot block types
  type SpigotBamboo = Bamboo
  type SpigotBed = Bed
  type SpigotBubbleColumn = BubbleColumn
  type SpigotCampfire = Campfire
  type SpigotCommandBlock = CommandBlock
  type SpigotDoor = Door
  type SpigotEndPortalFrame = EndPortalFrame
  type SpigotHopper = Hopper
  type SpigotJukebox = Jukebox
  type SpigotLantern = Lantern
  type SpigotLectern = Lectern
  type SpigotNoteBlock = NoteBlock
  type SpigotPiston = Piston
  type SpigotRepeater = Repeater
  type SpigotSign = Sign
  type SpigotTNT = TNT

  // Mapper utility functions
  def dataAs[T <: SpigotBlockData](implicit data: SpigotBlockData): T =
    data.asInstanceOf[T]

  implicit class MaterialExtensions(val material: Material) {
    def is(s: String): Boolean = material.name().startsWith(s)
  }
}
