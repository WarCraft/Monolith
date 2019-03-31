package gg.warcraft.monolith.app.entity.team.service;

import com.google.inject.Inject;
import gg.warcraft.monolith.api.entity.team.Team;
import gg.warcraft.monolith.api.entity.team.service.TeamCommandService;
import gg.warcraft.monolith.api.entity.team.service.TeamRepository;

public class DefaultTeamCommandService implements TeamCommandService {
    private final TeamRepository teamRepository;

    @Inject
    public DefaultTeamCommandService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public void registerTeam(Team team) {
        teamRepository.save(team);
    }
}
