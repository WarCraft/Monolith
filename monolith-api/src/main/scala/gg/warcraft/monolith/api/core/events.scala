package gg.warcraft.monolith.api.core

/** A DailyTickEvent is fired exactly once every day on the first server tick of that
  * day. It's purpose is to provide a simple mechanism to listen to to kick off tasks
  * that should run once a day.
  * If the server was taken offline before 12AM and restarted at 2AM this event will
  * fire once at 2AM and then 12AM every subsequent day for as long as the server is
  * online.
  * If the server has been offline for more than a day this event is only fired for
  * the current day when the server is restarted and will not retroactively fire for
  * any days where the server was offline for 24 hours.
  */
case class DailyTickEvent() extends Event

/** The ServerShutdownEvent is a utility event that allows subscribers to perform
  * shutdown tasks without having to be notified from their own plugin's onDisable
  * method. This event is fired synchronously and the server WILL shut down at the
  * end of this tick.
  */
case class ServerShutdownEvent() extends Event
