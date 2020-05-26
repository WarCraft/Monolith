/*
 * MIT License
 *
 * Copyright (c) 2020 WarCraft <https://github.com/WarCraft>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package gg.warcraft.monolith.api.core.auth

import gg.warcraft.monolith.api.core.MonolithConfig
import gg.warcraft.monolith.api.item.ItemType

class AuthService {
  var devPermission: String = _
  var staffPermission: String = _
  var modPermission: String = _
  var adminPermission: String = _
  var debuggingItem: ItemType = _
  var moderatingItem: ItemType = _

  def readConfig(config: MonolithConfig): Unit = {
    devPermission = config.devPermission
    staffPermission = config.staffPermission
    modPermission = config.modPermission
    adminPermission = config.adminPermission
    debuggingItem = config.debuggingItem
    moderatingItem = config.moderatingItem
  }

  private def hasOffHand(principal: Principal, offHand: ItemType): Boolean =
    principal.equipment.offHand match {
      case Some(item) => item.`type` == offHand
      case None       => false
    }

  def isDev(principal: Principal): Boolean =
    principal.hasPermission(devPermission)

  def isDebugging(principal: Principal): Boolean =
    isDev(principal) && hasOffHand(principal, debuggingItem)

  def isStaff(principal: Principal): Boolean =
    principal.hasPermission(staffPermission)

  def isMod(principal: Principal): Boolean =
    principal.hasPermission(modPermission)

  def isAdmin(principal: Principal): Boolean =
    principal.hasPermission(adminPermission)

  def isModerating(principal: Principal): Boolean =
    isMod(principal) && hasOffHand(principal, moderatingItem)
}
