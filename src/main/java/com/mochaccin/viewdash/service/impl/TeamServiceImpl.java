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

/**
 * Service implementation for managing team information.
 */
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

        if (teamId.length() != 24) { return null; }

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
            team.getPlayers().remove(playerRepository.findById(playerObjectID).get());
            teamRepository.save(team);
            return true;
        }

        return false;
    }

    @Override
    public TeamDTO getTeamByName(String teamName) {
        if (teamName.isEmpty()) { return null; }

        if (!teamRepository.existsByName(teamName)) { return null; }

        return teamDTOMapper.apply(teamRepository.findTeamByName(teamName).get());
    }

    @Override
    public List<TeamDTO> getTeams() {
        if (!teamRepository.findAll().isEmpty()) {
            return teamRepository.findAll().stream().map(teamDTOMapper).toList();
        }
        return null;
    }

    @Override
    public HashMap<String, String> getTeamPlaystyle(String teamId) {
        if (teamId.length() != 24) { return null; }
        if (teamRepository.existsById(new ObjectId(teamId))) {
            return teamRepository.findById(new ObjectId(teamId)).get().getTeamPlaystyle();
        }
        return null;
    }

    @Override
    public HashMap<String, String> getTeamStats(String teamId) {
        if (teamId.length() != 24) { return null; }
        if (teamRepository.existsById(new ObjectId(teamId))) {
            return teamRepository.findById(new ObjectId(teamId)).get().getTeamStats();
        }
        return null;
    }

    @Override
    public Coach getTeamCoach(String teamId) {

        if (teamId.length() != 24) { return null; }

        if (teamRepository.existsById(new ObjectId(teamId))) {
            try {
                if (teamRepository.findById(new ObjectId(teamId)).isPresent()) {
                    return teamRepository.findById(new ObjectId(teamId)).get().getCoach();
                }
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    @Override
    public boolean createTeam(Team teamBody) {

        if (!teamRepository.existsByName(teamBody.getName())) {
            try {
                teamRepository.insert(teamBody);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean deleteTeam(String teamId) {
        if (teamId.length() != 24) { return false; }

        if (teamRepository.existsById(new ObjectId(teamId))) {

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
        return false;
    }

    @Override
    public boolean removeCoachFromTeam(String teamId) {
        if (teamId.length() != 24) { return false; }

        ObjectId teamObjectID = new ObjectId(teamId);

        if (teamRepository.existsById(teamObjectID)) {
            Team team = teamRepository.findById(teamObjectID).get();
            team.setCoach(null);
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
            team.setCoach(coachRepository.findById(coachObjectID).get());
            teamRepository.save(team);
            return true;
        }

        return false;
    }
}
