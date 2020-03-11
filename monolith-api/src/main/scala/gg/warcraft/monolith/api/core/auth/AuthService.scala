package gg.warcraft.monolith.api.core.auth

import gg.warcraft.monolith.api.core.MonolithConfig
import gg.warcraft.monolith.api.world.item.ItemType

class AuthService {
  var devPermission: String = _
  var staffPermission: String = _
  var modPermission: String = _
  var adminPermission: String = _
  var debuggingItem: ItemType = _
  var moderatingItem: ItemType = _

  def isDev(principal: Principal): Boolean =
    principal hasPermission devPermission

  def isDebugging(principal: Principal): Boolean =
    isDev(principal) && (principal.offhand contains debuggingItem)

  def isStaff(principal: Principal): Boolean =
    principal hasPermission staffPermission

  def isMod(principal: Principal): Boolean =
    principal hasPermission modPermission

  def isAdmin(principal: Principal): Boolean =
    principal hasPermission adminPermission

  def isModerating(principal: Principal): Boolean =
    isMod(principal) && (principal.offhand contains moderatingItem)

  def readConfig(config: MonolithConfig): Unit = {
    devPermission = config.devPermission
    staffPermission = config.staffPermission
    modPermission = config.modPermission
    adminPermission = config.adminPermission
    debuggingItem = config.debuggingItem
    moderatingItem = config.moderatingItem
  }
}
