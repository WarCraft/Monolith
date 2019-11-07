package gg.warcraft.monolith.spigot

import gg.warcraft.monolith.spigot.world.{ SpigotLocationMapper, SpigotSoundMapper, SpigotWorldService }
import gg.warcraft.monolith.spigot.world.block._
import gg.warcraft.monolith.spigot.world.item.{ JSpigotItemTypeMapper, JSpigotItemVariantMapper, SpigotItemMapper, SpigotItemService, SpigotItemTypeMapper, SpigotItemVariantMapper }
import org.bukkit.Server

object Implicits {
  implicit var server: Server = _

  implicit lazy val worldMapper: SpigotWorldMapper = new SpigotWorldMapper
  implicit lazy val locationMapper: SpigotLocationMapper = new SpigotLocationMapper

  implicit lazy val blockTypeMapper: SpigotBlockTypeMapper =
    new JSpigotBlockTypeMapper // TODO replace /w scala impl when switch table works
  implicit lazy val blockVariantMapper: SpigotBlockVariantMapper =
    new JSpigotBlockVariantMapper // TODO replace /w scala impl when switch table works
  implicit lazy val blockStateMapper: SpigotBlockStateMapper =
    new SpigotBlockStateMapper
  implicit lazy val blockAttachmentMapper: SpigotBlockAttachmentMapper =
    new SpigotBlockAttachmentMapper
  implicit lazy val blockBisectionMapper: SpigotBlockBisectionMapper =
    new SpigotBlockBisectionMapper
  implicit lazy val blockFaceMapper: SpigotBlockFaceMapper =
    new SpigotBlockFaceMapper
  implicit lazy val blockOrientationMapper: SpigotBlockOrientationMapper =
    new SpigotBlockOrientationMapper
  implicit lazy val blockRotationMapper: SpigotBlockRotationMapper =
    new SpigotBlockRotationMapper
  implicit lazy val blockShapeMapper: SpigotBlockShapeMapper =
    new SpigotBlockShapeMapper
  implicit lazy val blockMapper: SpigotBlockMapper = new SpigotBlockMapper

  implicit lazy val itemTypeMapper: SpigotItemTypeMapper =
    new JSpigotItemTypeMapper // TODO replace /w scala impl when switch table works
  implicit lazy val itemVariantMapper: SpigotItemVariantMapper =
    new JSpigotItemVariantMapper(blockVariantMapper) // TODO replace /w scala impl when switch table works
  implicit lazy val itemMapper: SpigotItemMapper = new SpigotItemMapper

  // TODO rewrite to scala class
  implicit lazy val soundMapper: SpigotSoundMapper = new SpigotSoundMapper

  implicit lazy val worldService: SpigotWorldService = new SpigotWorldService
  implicit lazy val itemService: SpigotItemService = new SpigotItemService
}
