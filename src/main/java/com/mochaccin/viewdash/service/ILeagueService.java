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

    boolean createLeague(League leagueBody);

    boolean deleteLeague(String leagueId);

    boolean addTeamToLeague(String leagueId, String teamId);

     boolean removeTeamFromLeague(String leagueId, String teamId);
    
    List<TeamDTO> getTeamsByLeagueId(String objectId);

    public HashMap<String, TeamLeagueDetails> getTeamDetailsByLeagueId(String leagueId);

    LeagueDTO getLeagueByName(String leagueName);
}
