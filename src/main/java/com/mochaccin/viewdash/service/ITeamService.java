package com.mochaccin.viewdash.service;
import com.mochaccin.viewdash.model.Player;
import com.mochaccin.viewdash.model.Team;

import java.util.List;

public interface ITeamService {
    List<Player> getTeamPlayers(String teamId);
    void addTeam();
    List<Team> getTeams();
}
