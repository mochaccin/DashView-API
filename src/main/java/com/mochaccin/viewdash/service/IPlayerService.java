package com.mochaccin.viewdash.service;

import com.mochaccin.viewdash.model.Player;
import com.mochaccin.viewdash.model.PlayerStats;

import java.util.List;

public interface IPlayerService {
    List<Player> getAllPlayers();
    void addPlayer();

    PlayerStats getPlayerStats(String id);
}
