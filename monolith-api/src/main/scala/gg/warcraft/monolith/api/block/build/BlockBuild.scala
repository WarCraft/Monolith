package gg.warcraft.monolith.api.block.build

import gg.warcraft.monolith.api.block.box.BlockBox

case class BlockBuild(
    id: String,
    typed: String,
    model: String,
    data: List[String],
    boundingBox: BlockBox
)
