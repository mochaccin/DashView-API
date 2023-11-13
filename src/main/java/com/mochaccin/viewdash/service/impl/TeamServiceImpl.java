package com.mochaccin.viewdash.service.impl;

import com.mochaccin.viewdash.dtomappers.PlayerDTOMapper;
import com.mochaccin.viewdash.dtomappers.TeamDTOMapper;
import com.mochaccin.viewdash.model.Coach;
import com.mochaccin.viewdash.model.League;
import com.mochaccin.viewdash.model.Team;
import com.mochaccin.viewdash.model.dto.PlayerDTO;
import com.mochaccin.viewdash.model.dto.TeamDTO;
import com.mochaccin.viewdash.repository.CoachRepository;
import com.mochaccin.viewdash.repository.LeagueRepository;
import com.mochaccin.viewdash.repository.PlayerRepository;
import com.mochaccin.viewdash.repository.TeamRepository;
import com.mochaccin.viewdash.service.ITeamService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;

@Service
public class TeamServiceImpl implements ITeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private LeagueRepository leagueRepository;

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private TeamDTOMapper teamDTOMapper;

    @Autowired
    private PlayerDTOMapper playerDTOMapper;

    @Override
    public List<PlayerDTO> getTeamPlayers(String teamId) {

        try {
            if (teamRepository.findById(new ObjectId(teamId)).isPresent()) {
                return teamRepository.findById(new ObjectId(teamId)).get().getPlayers().stream().map(playerDTOMapper).toList();
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
    @Override
    public boolean addPlayerToTeam(String teamId, String playerId) {

        if (playerId.length() != 24 || teamId.length() != 24) { return false; }

        ObjectId playerObjectID = new ObjectId(playerId);
        ObjectId teamObjectID = new ObjectId(teamId);

        if (teamRepository.existsById(teamObjectID) || playerRepository.existsById(playerObjectID)) {
            Team team = teamRepository.findById(teamObjectID).get();
            team.getPlayers().add(playerRepository.findById(playerObjectID).get());
            teamRepository.save(team);
            return true;
        }

        return false;
    }

    @Override
    public boolean removePlayerFromTeam(String teamId, String playerId) {

        if (playerId.length() != 24 || teamId.length() != 24) { return false; }

        ObjectId playerObjectID = new ObjectId(playerId);
        ObjectId teamObjectID = new ObjectId(teamId);

        if (teamRepository.existsById(teamObjectID) || playerRepository.existsById(playerObjectID)) {
            Team team = teamRepository.findById(teamObjectID).get();
            team.getPlayers().add(playerRepository.findById(playerObjectID).get());
            teamRepository.save(team);
            return true;
        }

        return false;
    }

    @Override
    public Team getTeamByName(String teamName) {
        if (teamName.isEmpty()) { return null; }

        if (!teamRepository.existsByName(teamName)) { return null; }

        return teamRepository.findTeamByName(teamName).get();
    }

    @Override
    public List<TeamDTO> getTeams() {
        return teamRepository.findAll().stream().map(teamDTOMapper).toList();
    }

    @Override
    public HashMap<String, String> getTeamPlaystyle(String teamId) {
        return teamRepository.findById(new ObjectId(teamId)).get().getTeamPlaystyle();
    }

    @Override
    public HashMap<String, String> getTeamStats(String teamId) {
        return teamRepository.findById(new ObjectId(teamId)).get().getTeamStats();
    }

    @Override
    public List<Coach> getTeamCoaches(String teamId) {

        try {
            if (teamRepository.findById(new ObjectId(teamId)).isPresent()) {
                return teamRepository.findById(new ObjectId(teamId)).get().getCoaches();
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    @Override
    public boolean createTeam(Team teamBody) {
        try {
            teamRepository.insert(teamBody);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteTeam(String teamId) {
        if (teamId.length() != 24) { return false; }

        try {
            teamRepository.findById(new ObjectId(teamId)).get().getPlayers().forEach(player -> {
                playerRepository.delete(player);
            });

            for (League league : leagueRepository.findAll()) {
                league.getTeams().removeIf(team ->
                    team.getObjectId().toHexString().equals(teamId)
                );
                leagueRepository.save(league);
            }

            teamRepository.deleteById(new ObjectId(teamId));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean removeCoachFromTeam(String teamId, String coachId) {
        if (coachId.length() != 24 || teamId.length() != 24) { return false; }

        ObjectId coachObjectID = new ObjectId(coachId);
        ObjectId teamObjectID = new ObjectId(teamId);

        if (teamRepository.existsById(teamObjectID) || coachRepository.existsById(coachObjectID)) {
            Team team = teamRepository.findById(teamObjectID).get();
            team.getCoaches().remove(coachRepository.findById(coachObjectID).get());
            teamRepository.save(team);
            return true;
        }

        return false;
    }

    @Override
    public boolean addCoachToTeam(String teamId, String coachId) {
        if (coachId.length() != 24 || teamId.length() != 24) { return false; }

        ObjectId coachObjectID = new ObjectId(coachId);
        ObjectId teamObjectID = new ObjectId(teamId);

        if (teamRepository.existsById(teamObjectID) || coachRepository.existsById(coachObjectID)) {
            Team team = teamRepository.findById(teamObjectID).get();
            team.getCoaches().add(coachRepository.findById(coachObjectID).get());
            teamRepository.save(team);
            return true;
        }

        return false;
    }
}
