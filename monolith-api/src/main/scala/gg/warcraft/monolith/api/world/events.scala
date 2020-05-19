package gg.warcraft.monolith.api.world

import gg.warcraft.monolith.api.core.event.{Event, PreEvent}

trait WorldEvent

trait ChunkEvent extends WorldEvent

case class ChunkPreLoadEvent() extends PreEvent with ChunkEvent

case class ChunkLoadEvent() extends Event with ChunkEvent

case class ChunkPreUnloadEvent() extends PreEvent with ChunkEvent

case class ChunkUnloadEvent() extends Event with ChunkEvent
