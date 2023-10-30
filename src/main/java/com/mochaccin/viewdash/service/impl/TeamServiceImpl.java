package com.mochaccin.viewdash.service.impl;

import com.mochaccin.viewdash.dtomappers.TeamDTOMapper;
import com.mochaccin.viewdash.model.Coach;
import com.mochaccin.viewdash.model.Player;
import com.mochaccin.viewdash.model.Team;
import com.mochaccin.viewdash.model.dto.TeamDTO;
import com.mochaccin.viewdash.repository.PlayerRepository;
import com.mochaccin.viewdash.repository.TeamRepository;
import com.mochaccin.viewdash.service.ITeamService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements ITeamService {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    TeamDTOMapper teamDTOMapper;

    @Override
    public List<Player> getTeamPlayers(String teamId) {

        try {
            if (teamRepository.findById(new ObjectId(teamId)).isPresent()) {
                return teamRepository.findById(new ObjectId(teamId)).get().getPlayers();
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    @Override
    public void addTeam() {

        List<Player> players = new ArrayList<>();
        players.add(playerRepository.findById(new ObjectId("653ec136904fcd74a89a88ce")).get());
        Coach coach = new Coach("guerri", "Nicholas Nogueira", "Brazilian", "", "Primary Coach");
        List<Coach> coaches = new ArrayList<>();
        coaches.add(coach);
        HashMap<String, String> teamPlaystyle = new HashMap<>();
        HashMap<String, String> teamStats = new HashMap<>();

        teamPlaystyle.put("aggresive", "96");
        teamPlaystyle.put("tactical", "98");
        teamPlaystyle.put("support", "90");
        teamPlaystyle.put("rusher", "85");
        teamPlaystyle.put("patient", "87");
        teamPlaystyle.put("saver", "85");
        teamPlaystyle.put("secure", "96");
        teamPlaystyle.put("effective", "92");
        teamPlaystyle.put("teamwork", "99");
        teamPlaystyle.put("focus", "92");
        teamPlaystyle.put("ambition", "100");

        teamStats.put("leaguePosition", "#8");
        teamStats.put("leaguePoints", "3");
        teamStats.put("totalEarnings", "$1,669,298");
        teamStats.put("matchesPlayed", "4");
        teamStats.put("matchesWon", "1");
        teamStats.put("matchesLost", "3");
        teamStats.put("winRate", "25");

        Team team = new Team(new ObjectId(), "FURIA Esports", "", players, coaches, teamPlaystyle, teamStats);
        teamRepository.insert(team);
    }

    @Override
    public void addPlayerToTeam(ObjectId teamId, ObjectId playerId) {
        Team team;
        Player player;
        if (teamRepository.findById(teamId).isPresent() || playerRepository.findById(playerId).isPresent()) {
            team = teamRepository.findById(teamId).get();
            List<Player> players = team.getPlayers();
            player = playerRepository.findById(playerId).get();
            players.add(player);
            team.setPlayers(players);
            teamRepository.save(team);
        }
    }

    @Override
    public List<TeamDTO> getTeams() {
        return teamRepository.findAll().stream().map(teamDTOMapper).toList();
    }
}
