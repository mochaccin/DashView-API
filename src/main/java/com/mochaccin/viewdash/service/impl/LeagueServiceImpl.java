package com.mochaccin.viewdash.service.impl;

import com.mochaccin.viewdash.dtomappers.LeagueDTOMapper;
import com.mochaccin.viewdash.dtomappers.TeamDTOMapper;
import com.mochaccin.viewdash.model.League;
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

import java.util.HashMap;
import java.util.List;
import java.util.Random;

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
        if (!leagueRepository.findAll().isEmpty()) {
            return leagueRepository.findAll().stream().map(leagueDTOMapper).toList();
        }

        return null;
    }

    @Override
    public boolean createLeague(League leagueBody) {

        if (!leagueRepository.existsByName(leagueBody.getName())) {
            try {
                leagueRepository.insert(leagueBody);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean deleteLeague(String leagueId) {
        if (leagueId.length() != 24) { return false; }

        try {
            leagueRepository.deleteById(new ObjectId(leagueId));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean addTeamToLeague(String leagueId, String teamId) {

        if (leagueId.length() != 24 || teamId.length() != 24) { return false; }

        ObjectId leagueObjectID = new ObjectId(leagueId);
        ObjectId teamObjectID = new ObjectId(teamId);

        if (leagueRepository.existsById(leagueObjectID) || teamRepository.existsById(teamObjectID)) {
            League league = leagueRepository.findById(leagueObjectID).get();
            league.getTeams().add(teamRepository.findById(teamObjectID).get());
            league.setTeamsDetails(updateLeagueTeamsDetail(league.getTeams()));
            leagueRepository.save(league);
            return true;
        }

        return false;
    }

    @Override
    public boolean removeTeamFromLeague(String leagueId, String teamId) {

        if (leagueId.length() != 24 || teamId.length() != 24) { return false; }

        ObjectId leagueObjectID = new ObjectId(leagueId);
        ObjectId teamObjectID = new ObjectId(teamId);

        if (leagueRepository.existsById(leagueObjectID) || teamRepository.existsById(teamObjectID)) {
            League league = leagueRepository.findById(leagueObjectID).get();
            league.getTeams().remove(teamRepository.findById(teamObjectID).get());
            league.setTeamsDetails(updateLeagueTeamsDetail(league.getTeams()));
            leagueRepository.save(league);
            return true;
        }

        return false;
    }


    @Override
    public List<TeamDTO> getTeamsByLeagueId(String leagueId) {
        if (leagueId.length() != 24) { return null; }

        if(leagueRepository.findById(new ObjectId(leagueId)).isPresent()){
             return leagueRepository.findById(new ObjectId(leagueId)).get().getTeams().stream().map(teamDTOMapper).toList();
        }else{
            return null;
        }
    }

    @Override
    public HashMap<String, TeamLeagueDetails> getTeamDetailsByLeagueId(String leagueId) {
        if (leagueId.length() != 24) { return null; }

        if(leagueRepository.findById(new ObjectId(leagueId)).isPresent()){
           return leagueRepository.findById(new ObjectId(leagueId)).get().getTeamsDetails();
        }
        return null;
    }

    @Override
    public LeagueDTO getLeagueByName(String leagueName) {
        if (leagueName.isEmpty() || leagueName.isBlank()) { return null; }

        if (!leagueRepository.existsByName(leagueName)) { return null; }

        return leagueDTOMapper.apply(leagueRepository.findLeagueByName(leagueName).get());
    }

    private HashMap<String, TeamLeagueDetails> updateLeagueTeamsDetail(List<Team> teams) {

        if (teams.isEmpty()) { return null; }

        Random rnum = new Random();
        HashMap<String, TeamLeagueDetails> leagueTeamsDetail = new HashMap<>();

        teams.forEach(team -> {
            leagueTeamsDetail.put(team.getObjectId().toHexString(), new TeamLeagueDetails(rnum.nextInt(1,12), rnum.nextInt(1,9)));
        });

        return leagueTeamsDetail;
    }

}
