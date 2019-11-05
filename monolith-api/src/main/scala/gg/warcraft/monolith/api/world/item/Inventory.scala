package gg.warcraft.monolith.api.world.item

import java.util

import scala.annotation.varargs
import scala.jdk.CollectionConverters._

trait Inventory {
  val items: List[Item]
  def getItems: util.List[Item] = items.asJava

  def hasSpaceFor(@varargs items: Item*): Boolean

  def contains(`type`: ItemType, count: Int): Boolean
  def contains(`type`: ItemType): Boolean
  def contains(variant: ItemVariant, count: Int): Boolean
  def contains(variant: ItemVariant): Boolean
  def contains(item: Item): Boolean
}
