package com.mochaccin.viewdash.service;

import com.mochaccin.viewdash.model.PlayerStats;

public interface IPlayerStatsService {
    PlayerStats getPlayerStatsByPlayerId(String playerId);

    boolean addStatsToPlayer(String playerId);
}
