package com.mochaccin.viewdash.service.impl;

import com.mochaccin.viewdash.dtomappers.LeagueDTOMapper;
import com.mochaccin.viewdash.dtomappers.TeamDTOMapper;
import com.mochaccin.viewdash.model.League;
import com.mochaccin.viewdash.model.Player;
import com.mochaccin.viewdash.model.Team;
import com.mochaccin.viewdash.model.TeamLeagueDetails;
import com.mochaccin.viewdash.model.dto.LeagueDTO;
import com.mochaccin.viewdash.model.dto.TeamDTO;
import com.mochaccin.viewdash.repository.LeagueRepository;
import com.mochaccin.viewdash.repository.TeamRepository;
import com.mochaccin.viewdash.service.ILeagueService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeagueServiceImpl implements ILeagueService {

    @Autowired
    LeagueRepository leagueRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    LeagueDTOMapper leagueDTOMapper;
    @Autowired
    TeamDTOMapper teamDTOMapper;

    @Override
    public List<LeagueDTO> getLeagues() {
        return leagueRepository.findAll().stream().map(leagueDTOMapper).toList();
    }

    @Override
    public boolean createLeague(League leagueBody) {
        try {
            leagueRepository.insert(leagueBody);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void addTeamToLeague(ObjectId leagueId, ObjectId teamId) {
        League league;
        Team team;
        if (leagueRepository.findById(leagueId).isPresent() || teamRepository.findById(teamId).isPresent()) {
            league = leagueRepository.findById(leagueId).get();
            team = teamRepository.findById(teamId).get();
            List<Team> teams = league.getTeams();
            teams.add(team);
            league.setTeams(teams);
            leagueRepository.save(league);
        }
    }


    @Override
    public List<TeamDTO> getTeamsByLeagueId(String leagueId) {
        if(leagueRepository.findById(new ObjectId(leagueId)).isPresent()){
             return leagueRepository.findById(new ObjectId(leagueId)).get().getTeams().stream().map(teamDTOMapper).toList();
        }else{
            return null;
        }
    }

    @Override
    public HashMap<String, TeamLeagueDetails> getTeamDetailsByLeagueId(String leagueId) {
        if(leagueRepository.findById(new ObjectId(leagueId)).isPresent()){
           return leagueRepository.findById(new ObjectId(leagueId)).get().getTeamsDetails();
        }
        return null;
    }

}
