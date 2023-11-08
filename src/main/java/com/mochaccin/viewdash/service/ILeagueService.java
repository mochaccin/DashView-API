package com.mochaccin.viewdash.service;

import com.mochaccin.viewdash.model.League;
import com.mochaccin.viewdash.model.TeamLeagueDetails;
import com.mochaccin.viewdash.model.dto.LeagueDTO;
import com.mochaccin.viewdash.model.dto.TeamDTO;

import org.bson.types.ObjectId;

import java.util.HashMap;
import java.util.List;

public interface ILeagueService {
    List<LeagueDTO> getLeagues();

    void addLeague();

    void addTeamToLeague(ObjectId leagueId, ObjectId teamId);
    
    List<TeamDTO> getTeamsByLeagueId(String objectId);

    public HashMap<String, TeamLeagueDetails> getTeamDetailsByLeagueId(String leagueId, String teamId);

}
