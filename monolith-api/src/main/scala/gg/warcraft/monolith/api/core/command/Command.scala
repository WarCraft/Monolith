package gg.warcraft.monolith.api.core.command

case class Command(
    name: String,
    label: String,
    args: Array[String]
)
