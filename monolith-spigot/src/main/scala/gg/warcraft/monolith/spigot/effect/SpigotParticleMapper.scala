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

package gg.warcraft.monolith.spigot.effect

import gg.warcraft.monolith.api.core.Color
import gg.warcraft.monolith.api.effect.Particle
import org.bukkit.{Color => SpigotColor, Particle => SpigotParticle}

class SpigotParticleMapper { // TODO optimize big match statement
  def map(particle: Particle): SpigotParticle = particle match {
    case Particle.BlockCrack          => SpigotParticle.BLOCK_CRACK
    case Particle.BlockDust           => SpigotParticle.BLOCK_DUST
    case Particle.Barrier             => SpigotParticle.BLOCK_MARKER // TODO rename
    case Particle.BubbleColumnUp      => SpigotParticle.BUBBLE_COLUMN_UP
    case Particle.BubblePop           => SpigotParticle.BUBBLE_POP
    case Particle.CampfireCosySmoke   => SpigotParticle.CAMPFIRE_COSY_SMOKE
    case Particle.CampfireSignalSmoke => SpigotParticle.CAMPFIRE_SIGNAL_SMOKE
    case Particle.Cloud               => SpigotParticle.CLOUD
    case Particle.Composter           => SpigotParticle.COMPOSTER
    case Particle.Crit                => SpigotParticle.CRIT
    case Particle.CritMagic           => SpigotParticle.CRIT_MAGIC
    case Particle.CurrentDown         => SpigotParticle.CURRENT_DOWN
    case Particle.DamageIndicator     => SpigotParticle.DAMAGE_INDICATOR
    case Particle.Dolphin             => SpigotParticle.DOLPHIN
    case Particle.DragonBreath        => SpigotParticle.DRAGON_BREATH
    case Particle.DripLava            => SpigotParticle.DRIPPING_HONEY
    case Particle.DripWater           => SpigotParticle.DRIP_LAVA
    case Particle.DrippingHoney       => SpigotParticle.DRIP_WATER
    case Particle.EnchantmentTable    => SpigotParticle.ENCHANTMENT_TABLE
    case Particle.EndRod              => SpigotParticle.END_ROD
    case Particle.ExplosionHuge       => SpigotParticle.EXPLOSION_HUGE
    case Particle.ExplosionLarge      => SpigotParticle.EXPLOSION_LARGE
    case Particle.ExplosionNormal     => SpigotParticle.EXPLOSION_NORMAL
    case Particle.FallingDust         => SpigotParticle.FALLING_DUST
    case Particle.FallingHoney        => SpigotParticle.FALLING_HONEY
    case Particle.FallingLava         => SpigotParticle.FALLING_LAVA
    case Particle.FallingNectar       => SpigotParticle.FALLING_NECTAR
    case Particle.FallingWater        => SpigotParticle.FALLING_WATER
    case Particle.FireworksSpark      => SpigotParticle.FIREWORKS_SPARK
    case Particle.Flame               => SpigotParticle.FLAME
    case Particle.Flash               => SpigotParticle.FLASH
    case Particle.Heart               => SpigotParticle.HEART
    case Particle.ItemCrack           => SpigotParticle.ITEM_CRACK
    case Particle.LandingHoney        => SpigotParticle.LANDING_HONEY
    case Particle.LandingLava         => SpigotParticle.LANDING_LAVA
    case Particle.Lava                => SpigotParticle.LAVA
    case Particle.LegacyBlockCrack    => SpigotParticle.LEGACY_BLOCK_CRACK
    case Particle.LegacyBlockDust     => SpigotParticle.LEGACY_BLOCK_DUST
    case Particle.LegacyFallingDust   => SpigotParticle.LEGACY_FALLING_DUST
    case Particle.MobAppearance       => SpigotParticle.MOB_APPEARANCE
    case Particle.Nautilus            => SpigotParticle.NAUTILUS
    case Particle.Note                => SpigotParticle.NOTE
    case Particle.Portal              => SpigotParticle.PORTAL
    case Particle.Redstone            => SpigotParticle.REDSTONE
    case Particle.Slime               => SpigotParticle.SLIME
    case Particle.SmokeLarge          => SpigotParticle.SMOKE_LARGE
    case Particle.SmokeNormal         => SpigotParticle.SMOKE_NORMAL
    case Particle.Sneeze              => SpigotParticle.SNEEZE
    case Particle.SnowShovel          => SpigotParticle.SNOWBALL
    case Particle.Snowball            => SpigotParticle.SNOW_SHOVEL
    case Particle.Spell               => SpigotParticle.SPELL
    case Particle.SpellInstant        => SpigotParticle.SPELL_INSTANT
    case Particle.SpellMob            => SpigotParticle.SPELL_MOB
    case Particle.SpellMobAmbient     => SpigotParticle.SPELL_MOB_AMBIENT
    case Particle.SpellWitch          => SpigotParticle.SPELL_WITCH
    case Particle.Spit                => SpigotParticle.SPIT
    case Particle.SquidInk            => SpigotParticle.SQUID_INK
    case Particle.Suspended           => SpigotParticle.SUSPENDED
    case Particle.SuspendedDepth      => SpigotParticle.SUSPENDED_DEPTH
    case Particle.SweepAttack         => SpigotParticle.SWEEP_ATTACK
    case Particle.Totem               => SpigotParticle.TOTEM
    case Particle.TownAura            => SpigotParticle.TOWN_AURA
    case Particle.VillagerAngry       => SpigotParticle.VILLAGER_ANGRY
    case Particle.VillagerHappy       => SpigotParticle.VILLAGER_HAPPY
    case Particle.WaterBubble         => SpigotParticle.WATER_BUBBLE
    case Particle.WaterDrop           => SpigotParticle.WATER_DROP
    case Particle.WaterSplash         => SpigotParticle.WATER_SPLASH
    case Particle.WaterWake           => SpigotParticle.WATER_WAKE
  }

  private final val spigotAqua =
    new SpigotParticle.DustOptions(SpigotColor.AQUA, 1)
  private final val spigotBlack =
    new SpigotParticle.DustOptions(SpigotColor.BLACK, 1)
  private final val spigotBlue =
    new SpigotParticle.DustOptions(SpigotColor.BLUE, 1)
  private final val spigotFuchsia =
    new SpigotParticle.DustOptions(SpigotColor.FUCHSIA, 1)
  private final val spigotGray =
    new SpigotParticle.DustOptions(SpigotColor.GRAY, 1)
  private final val spigotGreen =
    new SpigotParticle.DustOptions(SpigotColor.GREEN, 1)
  private final val spigotLime =
    new SpigotParticle.DustOptions(SpigotColor.LIME, 1)
  private final val spigotMaroon =
    new SpigotParticle.DustOptions(SpigotColor.MAROON, 1)
  private final val spigotNavy =
    new SpigotParticle.DustOptions(SpigotColor.NAVY, 1)
  private final val spigotOlive =
    new SpigotParticle.DustOptions(SpigotColor.OLIVE, 1)
  private final val spigotOrange =
    new SpigotParticle.DustOptions(SpigotColor.ORANGE, 1)
  private final val spigotPurple =
    new SpigotParticle.DustOptions(SpigotColor.PURPLE, 1)
  private final val spigotRed =
    new SpigotParticle.DustOptions(SpigotColor.RED, 1)
  private final val spigotSilver =
    new SpigotParticle.DustOptions(SpigotColor.SILVER, 1)
  private final val spigotTeal =
    new SpigotParticle.DustOptions(SpigotColor.TEAL, 1)
  private final val spigotWhite =
    new SpigotParticle.DustOptions(SpigotColor.WHITE, 1)
  private final val spigotYellow =
    new SpigotParticle.DustOptions(SpigotColor.YELLOW, 1)

  def map(color: Color): SpigotParticle.DustOptions = color match {
    case Color.Aqua    => spigotAqua
    case Color.Black   => spigotBlack
    case Color.Blue    => spigotBlue
    case Color.Fuchsia => spigotFuchsia
    case Color.Gray    => spigotGray
    case Color.Green   => spigotGreen
    case Color.Lime    => spigotLime
    case Color.Maroon  => spigotMaroon
    case Color.Navy    => spigotNavy
    case Color.Olive   => spigotOlive
    case Color.Orange  => spigotOrange
    case Color.Purple  => spigotPurple
    case Color.Red     => spigotRed
    case Color.Silver  => spigotSilver
    case Color.Teal    => spigotTeal
    case Color.White   => spigotWhite
    case Color.Yellow  => spigotYellow
  }
}
