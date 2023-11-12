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
}
