package gg.warcraft.monolith.api.entity

import gg.warcraft.monolith.api.item.Item

object Equipment extends Enumeration {
  type Slot = Value
  val HEAD, CHEST, LEGS, FEET, MAIN_HAND, OFF_HAND = Value
}

case class Equipment(
    head: Option[Item] = None,
    chest: Option[Item] = None,
    legs: Option[Item] = None,
    feet: Option[Item] = None,
    mainHand: Option[Item] = None,
    offHand: Option[Item] = None
) {
  def getItems: List[Item] =
    (head :: chest :: legs :: feet :: mainHand :: offHand :: Nil)
      .filter { _.isDefined }
      .map { _.get }

  def get(slot: Equipment.Slot): Option[Item] = slot match {
    case Equipment.HEAD      => head
    case Equipment.CHEST     => chest
    case Equipment.LEGS      => legs
    case Equipment.FEET      => feet
    case Equipment.MAIN_HAND => mainHand
    case Equipment.OFF_HAND  => offHand
  }
}
