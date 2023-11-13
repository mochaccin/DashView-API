package com.mochaccin.viewdash.service;
import com.mochaccin.viewdash.model.Coach;
import com.mochaccin.viewdash.model.Player;
import com.mochaccin.viewdash.model.Team;
import com.mochaccin.viewdash.model.dto.PlayerDTO;
import com.mochaccin.viewdash.model.dto.TeamDTO;
import org.bson.types.ObjectId;

import java.util.HashMap;
import java.util.List;

public interface ITeamService {
    List<PlayerDTO> getTeamPlayers(String teamId);
    List<TeamDTO> getTeams();

    void addPlayerToTeam(ObjectId teamId, ObjectId playerId);

    Team getTeamByName(String teamName);

    public HashMap<String, String> getTeamPlaystyle(String teamId);

    public HashMap<String, String> getTeamStats(String teamId);

    public List<Coach> getTeamCoaches(String teamId);

    boolean createTeam(Team teamBody);
}
