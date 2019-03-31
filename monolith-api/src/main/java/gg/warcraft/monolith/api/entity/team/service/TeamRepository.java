package gg.warcraft.monolith.api.entity.team.service;

import gg.warcraft.monolith.api.entity.team.Team;

import java.util.List;

public interface TeamRepository {

    Team getTeam(String name);

    List<Team> getTeams();

    void save(Team team);
}
