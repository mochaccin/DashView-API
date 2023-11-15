package com.mochaccin.viewdash.service;

import com.mochaccin.viewdash.model.PlayerStats;

/**
 * Service interface for managing PlayerStats objects.
 */
public interface IPlayerStatsService {

    /**
     * Get player statistics by player ID.
     *
     * @param playerId The ID of the player for whom statistics are retrieved.
     * @return A PlayerStats object representing the statistics of the player if found, or null if not found.
     */
    PlayerStats getPlayerStatsByPlayerId(String playerId);

    /**
     * Add statistics to a player.
     *
     * @param playerId The ID of the player to whom statistics are added.
     * @return true if the statistics are successfully added to the player, false otherwise.
     */
    boolean addStatsToPlayer(String playerId);
}
