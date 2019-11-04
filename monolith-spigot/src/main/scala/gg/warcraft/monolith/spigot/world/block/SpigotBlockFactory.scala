package gg.warcraft.monolith.spigot.world.block

import gg.warcraft.monolith.api.world.block.{
  Block,
  BlockFactory,
  BlockState,
  BlockType,
  BlockVariant,
  VariableBlock
}
import javax.inject.Inject

class SpigotBlockFactory @Inject() (
    blockMapper: SpigotBlockMapper,
//    typeMapper: SpigotBlockTypeMapper,
    variantMapper: SpigotBlockVariantMapper
) extends BlockFactory {
  private final val blockVariantPackage =
    "gg.warcraft.monolith.api.world.block.variant"

  private final val blockStatePackage =
    "gg.warcraft.monolith.api.world.block.state"

  override def create(`type`: BlockType): Block = {
    //  val material = typeMapper.map(`type`)
//    val block = new SpigotBlockStack(material)
    //   blockMapper.map(block).get
    null
  }

  override def create[T <: BlockVariant](variant: T): VariableBlock[T] = {
    val material = variantMapper.map(variant)
//    val block = new SpigotBlockStack(material)
    //  blockMapper.map(block).get.asInstanceOf[VariableBlock[T]]
    null
  }

  override def create(`type`: String): Block = {
    if (`type`.contains(':')) {
      val Array(enum, value) = `type`.split(':')
      if (enum.endsWith("Variant")) {
        val clazz = Class.forName(s"$blockVariantPackage.$enum")
        val valueOf = clazz.getMethod("valueOf", classOf[String])
        val variant = valueOf.invoke(null, value).asInstanceOf[BlockVariant]
        create(variant).asInstanceOf[Block]
      } else {
        val clazz = Class.forName(s"$blockStatePackage.$enum")
        val valueOf = clazz.getMethod("valueOf", classOf[String])
        val state = valueOf.invoke(null, value).asInstanceOf[BlockState]
        //  create(state).asInstanceOf[Block]
        null
      }
    } else {
      create(BlockType.valueOf(`type`))
    }
  }
}
