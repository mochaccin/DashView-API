package com.mochaccin.viewdash.service;

import com.mochaccin.viewdash.model.League;

import java.util.List;

public interface ILeagueService {
    List<League> getLeagues();

    void addLeague();
}
