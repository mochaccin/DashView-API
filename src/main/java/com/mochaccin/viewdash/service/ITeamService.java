package com.mochaccin.viewdash.service;
import com.mochaccin.viewdash.model.Player;
import com.mochaccin.viewdash.model.Team;
import com.mochaccin.viewdash.model.dto.TeamDTO;
import org.bson.types.ObjectId;

import java.util.List;

public interface ITeamService {
    List<Player> getTeamPlayers(String teamId);
    void addTeam();
    List<TeamDTO> getTeams();

    void addPlayerToTeam(ObjectId teamId, ObjectId playerId);

    Team getTeamByName(String teamName);
}
