package gg.warcraft.monolith.api.world.portal

class PortalTicker(service: PortalService) {
  def run(): Unit = service.portals foreach service.tick
}
