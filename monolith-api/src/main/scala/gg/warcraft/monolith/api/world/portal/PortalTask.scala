package gg.warcraft.monolith.api.world.portal

class PortalTask(service: PortalService) {
  def run(): Unit = service.portals foreach service.tick
}
