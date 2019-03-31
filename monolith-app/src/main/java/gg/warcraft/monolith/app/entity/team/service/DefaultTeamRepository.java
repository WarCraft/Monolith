package gg.warcraft.monolith.app.entity.team.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import gg.warcraft.monolith.api.entity.team.Team;
import gg.warcraft.monolith.api.entity.team.service.TeamRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class DefaultTeamRepository implements TeamRepository {
    private final Map<String, Team> teams;

    @Inject
    public DefaultTeamRepository() {
        this.teams = new HashMap<>();
    }

    @Override
    public Team getTeam(String name) {
        return teams.get(name);
    }

    @Override
    public List<Team> getTeams() {
        return new ArrayList<>(teams.values());
    }

    @Override
    public void save(Team team) {
        teams.put(team.getName(), team);
    }
}
