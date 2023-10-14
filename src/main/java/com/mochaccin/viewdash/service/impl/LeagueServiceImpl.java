package com.mochaccin.viewdash.service.impl;

import com.mochaccin.viewdash.model.League;
import com.mochaccin.viewdash.model.Team;
import com.mochaccin.viewdash.repository.LeagueRepository;
import com.mochaccin.viewdash.repository.TeamRepository;
import com.mochaccin.viewdash.service.ILeagueService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeagueServiceImpl implements ILeagueService {

    @Autowired
    LeagueRepository leagueRepository;

    @Autowired
    TeamRepository teamRepository;

    @Override
    public List<League> getLeagues() {
        return leagueRepository.findAll();
    }

    @Override
    public void addLeague() {
        List<Team> teams = new ArrayList<>();
        teams.add(teamRepository.findById(new ObjectId("652b15f0e5f83d10943d45f0")).get());
        League league = new League(new ObjectId(), "ESL PRO LEAGUE", teams, "https://img-cdn.hltv.org/eventlogo/PbunDRVk171inaIYCiN8QE.png?ixlib=java-2.1.0&s=ac138ad77192b828aaa88bcdaeb66057");
        leagueRepository.insert(league);
    }
}
