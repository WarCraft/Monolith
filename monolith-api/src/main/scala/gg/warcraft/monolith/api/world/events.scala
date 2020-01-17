package gg.warcraft.monolith.api.world

import gg.warcraft.monolith.api.core.event.{Event, PreEvent}

trait WorldEvent

trait ChunkEvent extends WorldEvent

case class ChunkPreLoadEvent() extends ChunkEvent with PreEvent

case class ChunkLoadEvent() extends ChunkEvent with Event

case class ChunkPreUnloadEvent() extends ChunkEvent with PreEvent

case class ChunkUnloadEvent() extends ChunkEvent with Event
