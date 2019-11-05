package gg.warcraft.monolith.spigot

import gg.warcraft.monolith.spigot.world.{
  SpigotLocationMapper,
  SpigotSoundMapper,
  SpigotWorldService
}
import gg.warcraft.monolith.spigot.world.block._
import gg.warcraft.monolith.spigot.world.item.{
  SpigotItemMapper,
  SpigotItemService,
  SpigotItemTypeMapper,
  SpigotItemVariantMapper
}
import org.bukkit.Server

object Implicits {
  implicit var server: Server = _

  implicit val worldMapper: SpigotWorldMapper = new SpigotWorldMapper
  implicit val locationMapper: SpigotLocationMapper = new SpigotLocationMapper

  implicit val blockTypeMapper: SpigotBlockTypeMapper = new SpigotBlockTypeMapper
  implicit val blockVariantMapper: SpigotBlockVariantMapper = new SpigotBlockVariantMapper
  implicit val blockStateMapper: SpigotBlockStateMapper = new SpigotBlockStateMapper
  implicit val blockAttachmentMapper: SpigotBlockAttachmentMapper = new SpigotBlockAttachmentMapper
  implicit val blockBisectionMapper: SpigotBlockBisectionMapper = new SpigotBlockBisectionMapper
  implicit val blockFaceMapper: SpigotBlockFaceMapper = new SpigotBlockFaceMapper
  implicit val blockOrientationMapper: SpigotBlockOrientationMapper = new SpigotBlockOrientationMapper
  implicit val blockRotationMapper: SpigotBlockRotationMapper = new SpigotBlockRotationMapper
  implicit val blockShapeMapper: SpigotBlockShapeMapper = new SpigotBlockShapeMapper
  implicit val blockMapper: SpigotBlockMapper = new SpigotBlockMapper

  implicit val itemTypeMapper: SpigotItemTypeMapper = new SpigotItemTypeMapper
  implicit val itemVariantMapper: SpigotItemVariantMapper = new SpigotItemVariantMapper
  implicit val itemMapper: SpigotItemMapper = new SpigotItemMapper

  // TODO rewrite to scala class
  implicit val soundMapper: SpigotSoundMapper = new SpigotSoundMapper

  implicit val worldService: SpigotWorldService = new SpigotWorldService
  implicit val itemService: SpigotItemService = new SpigotItemService
}
