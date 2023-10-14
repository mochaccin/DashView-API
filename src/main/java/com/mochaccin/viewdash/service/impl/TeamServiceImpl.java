package com.mochaccin.viewdash.service.impl;

import com.mochaccin.viewdash.model.Coach;
import com.mochaccin.viewdash.model.Player;
import com.mochaccin.viewdash.model.Team;
import com.mochaccin.viewdash.repository.PlayerRepository;
import com.mochaccin.viewdash.repository.TeamRepository;
import com.mochaccin.viewdash.service.ITeamService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class TeamServiceImpl implements ITeamService {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    PlayerRepository playerRepository;

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
        players.add(playerRepository.findById(new ObjectId("652afbcf44b79e068da1c1e6")).get());
        Coach coach = new Coach("B1ad3", "Andrii Horodenskyi", "Ukrainian", "https://d3dwep9z8m8y9r.cloudfront.net/players/2023/08/players-4823/navi_portrait/40865/conversions/b1ad3_830x1044-webp.webp", "Primary Coach");
        List<Coach> coaches = new ArrayList<>();
        coaches.add(coach);
        HashMap<String, String> teamPlaystyle = new HashMap<>();
        HashMap<String, String> teamStats = new HashMap<>();

        teamPlaystyle.put("aggresive", "91");
        teamPlaystyle.put("tactical", "86");
        teamPlaystyle.put("support", "61");
        teamPlaystyle.put("rusher", "76");
        teamPlaystyle.put("patient", "67");
        teamPlaystyle.put("saver", "53");
        teamPlaystyle.put("secure", "87");
        teamPlaystyle.put("effective", "98");
        teamPlaystyle.put("teamwork", "80");
        teamPlaystyle.put("focus", "89");
        teamPlaystyle.put("ambition", "100");

        teamStats.put("leaguePosition", "#1");
        teamStats.put("leaguePoints", "12");
        teamStats.put("totalEarnings", "20,779,480.99");
        teamStats.put("matchesPlayed", "4");
        teamStats.put("matchesWon", "4");
        teamStats.put("matchesLost", "0");
        teamStats.put("winRate", "100");

        Team team = new Team(new ObjectId(), "Natus Vincere", players, coaches, teamPlaystyle, teamStats);
        teamRepository.insert(team);
    }

    @Override
    public List<Team> getTeams() {
        return teamRepository.findAll();
    }
}
