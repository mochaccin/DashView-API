package com.mochaccin.viewdash.service;

import com.mochaccin.viewdash.model.League;
import com.mochaccin.viewdash.model.dto.LeagueDTO;
import org.bson.types.ObjectId;

import java.util.List;

public interface ILeagueService {
    List<LeagueDTO> getLeagues();

    void addLeague();

    void addTeamToLeague(ObjectId leagueId, ObjectId teamId);
}
