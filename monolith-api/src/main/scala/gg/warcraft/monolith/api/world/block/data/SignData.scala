package gg.warcraft.monolith.api.world.block.data

import gg.warcraft.monolith.api.world.block.BlockData

case class SignData(
  val lines: Array[String]
) extends BlockData

/*
banner
beacon
bed?
blastfurnace
brewing stand
campfire
chest/double chest/ender chest
command block
container
creature spawner
end gateway
flower pot?
furnace
jukebox
lectern
lockable
noteblock?
shulkerbox
sign
skull
smoker (furnace)
structure
*/
