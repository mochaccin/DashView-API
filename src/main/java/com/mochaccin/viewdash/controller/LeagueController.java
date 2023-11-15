package com.mochaccin.viewdash.controller;


import com.mochaccin.viewdash.model.League;
import com.mochaccin.viewdash.model.TeamLeagueDetails;
import com.mochaccin.viewdash.model.dto.LeagueDTO;
import com.mochaccin.viewdash.model.dto.TeamDTO;
import com.mochaccin.viewdash.service.ILeagueService;
import jakarta.validation.Valid;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * <h2>REST Controller for managing leagues.</h2>
 * <p>Provides endpoints for CRUD operations related to leagues.</p>
 */
@RestController
@RequestMapping("/leagues")
@CrossOrigin(origins = {"http://localhost:5173"})
public class LeagueController {

    @Autowired
    ILeagueService leagueService;

    /**
     * Get all leagues.
     *
     * @return A list of all leagues.
     */
    @GetMapping
    public ResponseEntity<?> getLeagues() {
        return ResponseEntity.ok(leagueService.getLeagues());
    }

    /**
     * Create a new league.
     *
     * @param leagueBody The league information to be created.
     * @param bindingResult Contains any validation errors.
     * @return A ResponseEntity indicating the result of the creation operation.
     */
    @PostMapping
    public ResponseEntity<?> createLeague(@Valid @RequestBody League leagueBody, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Uno o mas campos estan vacios.");
        }

        if (leagueService.createLeague(leagueBody)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body("Ocurrio un error en el servidor.");
    }

    /**
     * Delete a league by its ID.
     *
     * @param leagueId The ID of the league to delete.
     * @return A ResponseEntity indicating the result of the deletion operation.
     */
    @DeleteMapping("/{leagueId}")
    public ResponseEntity<?> deleteLeague(@PathVariable String leagueId) {
        if (leagueService.deleteLeague(leagueId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * Get a league by its name.
     *
     * @param leagueName The name of the league to retrieve.
     * @return A ResponseEntity containing the league details or a not found status.
     */
    @GetMapping("/{leagueName}")
    public ResponseEntity<LeagueDTO> getLeagueByName(@PathVariable String leagueName) {
        return Optional.ofNullable(leagueService.getLeagueByName(leagueName))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    /**
     * Get teams by league ID.
     *
     * @param leagueId The ID of the league.
     * @return A ResponseEntity containing a list of teams in the league or a not found status.
     */
    @GetMapping("/{leagueId}/teams")
    public ResponseEntity<List<TeamDTO>> getTeamsByLeagueId(@PathVariable String leagueId) {
    List<TeamDTO> teams = leagueService.getTeamsByLeagueId(leagueId);
        if (teams != null && !teams.isEmpty()) {
            return ResponseEntity.ok(teams);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Get team details by league ID.
     *
     * @param leagueId The ID of the league.
     * @return A ResponseEntity containing details of teams in the league or a not found status.
     */
    @GetMapping("/{leagueId}/leagueTeamDetails")
    public ResponseEntity<?> getLeagueTeamDetailsByLeagueId(@PathVariable String leagueId) {
        HashMap<String, TeamLeagueDetails> leagueTeamsDetails = leagueService.getTeamDetailsByLeagueId(leagueId);
        if (leagueTeamsDetails != null && !leagueTeamsDetails.isEmpty()) {
            return ResponseEntity.ok(leagueTeamsDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Add a team to a league.
     *
     * @param leagueId The ID of the league.
     * @param teamId The ID of the team to add.
     * @return A ResponseEntity indicating the result of the operation.
     */
    @PatchMapping("{leagueId}/teams/add/{teamId}")
    public ResponseEntity<?> addTeamToLeagueTeams(@PathVariable String leagueId, @PathVariable String teamId) {
        if (leagueService.addTeamToLeague(leagueId, teamId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * Remove a team from a league.
     *
     * @param leagueId The ID of the league.
     * @param teamId The ID of the team to remove.
     * @return A ResponseEntity indicating the result of the operation.
     */
    @PatchMapping("{leagueId}/teams/remove/{teamId}")
    public ResponseEntity<?> removeTeamFromLeagueTeams(@PathVariable String leagueId, @PathVariable String teamId) {
        if (leagueService.removeTeamFromLeague(leagueId, teamId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
