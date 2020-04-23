package gg.warcraft.monolith.spigot.math

import gg.warcraft.monolith.api.math.AABBf
import org.bukkit.util.BoundingBox

class SpigotAABBfMapper {
  def map(box: BoundingBox): AABBf = AABBf(
    box.getMinX.toFloat,
    box.getMinY.toFloat,
    box.getMinZ.toFloat,
    box.getMaxX.toFloat,
    box.getMaxY.toFloat,
    box.getMaxZ.toFloat
  )

  def map(box: AABBf): BoundingBox = new BoundingBox(
    box.minX,
    box.minY,
    box.minZ,
    box.maxX,
    box.maxY,
    box.maxZ
  )
}
