package gg.warcraft.monolith.api.entity.status

import java.util.UUID

import gg.warcraft.monolith.api.core.event.Event

case class StatusEffectGainedEvent(
    entityId: UUID,
    effect: StatusEffect
) extends Event

case class StatusEffectLostEvent(
    entityId: UUID,
    effect: StatusEffect
) extends Event
