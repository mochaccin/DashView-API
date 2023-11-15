package com.mochaccin.viewdash.service;

import com.mochaccin.viewdash.model.Coach;
import com.mochaccin.viewdash.model.Player;
import com.mochaccin.viewdash.model.Team;
import com.mochaccin.viewdash.model.dto.PlayerDTO;
import com.mochaccin.viewdash.model.dto.TeamDTO;
import org.bson.types.ObjectId;

import java.util.HashMap;
import java.util.List;

/**
 * Service interface for managing Team objects.
 */
public interface ITeamService {

    /**
     * Get a list of players in a team.
     *
     * @param teamId The ID of the team for which players are retrieved.
     * @return A list of PlayerDTO objects representing the players in the team.
     */
    List<PlayerDTO> getTeamPlayers(String teamId);

    /**
     * Get a list of all teams.
     *
     * @return A list of TeamDTO objects representing the teams.
     */
    List<TeamDTO> getTeams();

    /**
     * Add a player to a team.
     *
     * @param teamId   The ID of the team to which the player will be added.
     * @param playerId The ID of the player to be added.
     * @return true if the player is successfully added to the team, false otherwise.
     */
    boolean addPlayerToTeam(String teamId, String playerId);

    /**
     * Remove a player from a team.
     *
     * @param teamId   The ID of the team from which the player will be removed.
     * @param playerId The ID of the player to be removed.
     * @return true if the player is successfully removed from the team, false otherwise.
     */
    boolean removePlayerFromTeam(String teamId, String playerId);

    /**
     * Get a team by its name.
     *
     * @param teamName The name of the team to retrieve.
     * @return A TeamDTO object representing the team if found, or null if not found.
     */
    TeamDTO getTeamByName(String teamName);

    /**
     * Get the playstyle of a team.
     *
     * @param teamId The ID of the team for which playstyle is retrieved.
     * @return A HashMap containing playstyle information by category as keys.
     */
    public HashMap<String, String> getTeamPlaystyle(String teamId);

    /**
     * Get the statistics of a team.
     *
     * @param teamId The ID of the team for which statistics are retrieved.
     * @return A HashMap containing team statistics by category as keys.
     */
    public HashMap<String, String> getTeamStats(String teamId);

    /**
     * Get the coach of a team.
     *
     * @param teamId The ID of the team for which the coach is retrieved.
     * @return The Coach object representing the coach of the team if found, or null if not found.
     */
    public Coach getTeamCoach(String teamId);

    /**
     * Create a new team.
     *
     * @param teamBody The Team object representing the new team to be created.
     * @return true if the team is successfully created, false otherwise.
     */
    boolean createTeam(Team teamBody);

    /**
     * Delete a team by its ID.
     *
     * @param teamId The ID of the team to be deleted.
     * @return true if the team is successfully deleted, false otherwise.
     */
    boolean deleteTeam(String teamId);

    /**
     * Remove a coach from a team.
     *
     * @param teamId The ID of the team from which the coach will be removed.
     * @return true if the coach is successfully removed from the team, false otherwise.
     */
    boolean removeCoachFromTeam(String teamId);

    /**
     * Add a coach to a team.
     *
     * @param teamId  The ID of the team to which the coach will be added.
     * @param coachId The ID of the coach to be added.
     * @return true if the coach is successfully added to the team, false otherwise.
     */
    boolean addCoachToTeam(String teamId, String coachId);
}
