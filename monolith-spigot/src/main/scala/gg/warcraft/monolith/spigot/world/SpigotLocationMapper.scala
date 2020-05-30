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

package gg.warcraft.monolith.spigot.world

import gg.warcraft.monolith.api.math.{Vector3f, Vector3i}
import gg.warcraft.monolith.api.world.{BlockLocation, Location}
import org.bukkit.block.{Block => SpigotBlock}

class SpigotLocationMapper(
    private implicit val worldMapper: SpigotWorldMapper
) {
  def map(loc: BlockLocation): SpigotLocation = {
    val world = worldMapper.map(loc.world)
    val Vector3i(x, y, z) = loc.translation
    new SpigotLocation(world, x, y, z)
  }

  def map(loc: Location): SpigotLocation = {
    val world = worldMapper.map(loc.world)
    val Vector3f(x, y, z) = loc.translation
    new SpigotLocation(world, x, y, z)
  }

  def map(loc: Location, pitchYaw: (Float, Float)): SpigotLocation = {
    val world = worldMapper.map(loc.world)
    val Vector3f(x, y, z) = loc.translation
    val (pitch, yaw) = pitchYaw
    new SpigotLocation(world, x, y, z, yaw, pitch)
  }

  def map(block: SpigotBlock): BlockLocation = {
    val world = worldMapper.map(block.getLocation.getWorld)
    val translation = Vector3i(block.getX, block.getY, block.getZ)
    BlockLocation(world, translation)
  }

  def map(loc: SpigotLocation): Location = {
    val world = worldMapper.map(loc.getWorld)
    val translation = Vector3f(loc.getX, loc.getY, loc.getZ)
    val rotation = Vector3f(loc.getPitch, loc.getYaw)
    Location(world, translation, rotation)
  }
}
