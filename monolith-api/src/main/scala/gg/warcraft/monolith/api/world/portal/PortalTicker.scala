package gg.warcraft.monolith.api.world.portal

class PortalTicker(implicit service: PortalService) {
  def run(): Unit = service.portals.foreach { service.tick }
}
