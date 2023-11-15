package com.mochaccin.viewdash.service;

import com.mochaccin.viewdash.model.League;
import com.mochaccin.viewdash.model.TeamLeagueDetails;
import com.mochaccin.viewdash.model.dto.LeagueDTO;
import com.mochaccin.viewdash.model.dto.TeamDTO;
import org.bson.types.ObjectId;

import java.util.HashMap;
import java.util.List;

/**
 * Service interface for managing League objects.
 */
public interface ILeagueService {

    /**
     * Get a list of all leagues.
     *
     * @return A list of LeagueDTO objects representing the leagues.
     */
    List<LeagueDTO> getLeagues();

    /**
     * Create a new league.
     *
     * @param leagueBody The League object representing the new league to be created.
     * @return true if the league is successfully created, false otherwise.
     */
    boolean createLeague(League leagueBody);

    /**
     * Delete a league by its ID.
     *
     * @param leagueId The ID of the league to be deleted.
     * @return true if the league is successfully deleted, false otherwise.
     */
    boolean deleteLeague(String leagueId);

    /**
     * Add a team to a league.
     *
     * @param leagueId The ID of the league to which the team will be added.
     * @param teamId   The ID of the team to be added.
     * @return true if the team is successfully added to the league, false otherwise.
     */
    boolean addTeamToLeague(String leagueId, String teamId);

    /**
     * Remove a team from a league.
     *
     * @param leagueId The ID of the league from which the team will be removed.
     * @param teamId   The ID of the team to be removed.
     * @return true if the team is successfully removed from the league, false otherwise.
     */
    boolean removeTeamFromLeague(String leagueId, String teamId);

    /**
     * Get a list of teams by league ID.
     *
     * @param objectId The ID of the league.
     * @return A list of TeamDTO objects representing the teams in the league.
     */
    List<TeamDTO> getTeamsByLeagueId(String objectId);

    /**
     * Get team details by league ID.
     *
     * @param leagueId The ID of the league.
     * @return A HashMap containing team details (TeamLeagueDetails) by team ID as keys.
     */
    public HashMap<String, TeamLeagueDetails> getTeamDetailsByLeagueId(String leagueId);

    /**
     * Get a league by its name.
     *
     * @param leagueName The name of the league to retrieve.
     * @return A LeagueDTO object representing the league if found, or null if not found.
     */
    LeagueDTO getLeagueByName(String leagueName);
}
