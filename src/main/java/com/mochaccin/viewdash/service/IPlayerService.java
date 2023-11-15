package com.mochaccin.viewdash.service;

import com.mochaccin.viewdash.model.Player;
import com.mochaccin.viewdash.model.dto.PlayerDTO;

import java.util.List;

/**
 * Service interface for managing Player objects.
 */
public interface IPlayerService {

    /**
     * Get a list of all players.
     *
     * @return A list of PlayerDTO objects representing the players.
     */
    List<PlayerDTO> getAllPlayers();

    /**
     * Get a player by their ID.
     *
     * @param id The ID of the player to retrieve.
     * @return The Player object if found, or null if not found.
     */
    Player getPlayerById(String id);

    /**
     * Get a player by their nickname.
     *
     * @param playerNickname The nickname of the player to retrieve.
     * @return A PlayerDTO object representing the player if found, or null if not found.
     */
    PlayerDTO getPlayerByNickname(String playerNickname);

    /**
     * Create a new player.
     *
     * @param playerBody The Player object representing the new player to be created.
     * @return true if the player is successfully created, false otherwise.
     */
    boolean createPlayer(Player playerBody);

    /**
     * Delete a player by their ID.
     *
     * @param playerId The ID of the player to be deleted.
     * @return true if the player is successfully deleted, false otherwise.
     */
    boolean deletePlayer(String playerId);
}
