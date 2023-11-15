package com.mochaccin.viewdash.controller;


import com.mochaccin.viewdash.model.League;
import com.mochaccin.viewdash.model.Player;
import com.mochaccin.viewdash.model.Team;
import com.mochaccin.viewdash.model.TeamLeagueDetails;
import com.mochaccin.viewdash.model.dto.TeamDTO;
import com.mochaccin.viewdash.service.ITeamService;
import jakarta.validation.Valid;

import org.apache.catalina.connector.Response;
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
 * <h2>REST Controller for managing teams.</h2>
 * <p>Provides endpoints for CRUD operations related to teams.</p>
 */
@RestController
@RequestMapping("/teams")
@CrossOrigin(origins = {"http://localhost:5173"})
public class TeamController {

    @Autowired
    ITeamService teamService;

    /**
     * Get all teams.
     *
     * @return A ResponseEntity containing a list of all teams.
     */
    @GetMapping
    public ResponseEntity<List<TeamDTO>> getTeams() {
        return ResponseEntity.ok(teamService.getTeams());
    }

    /**
     * Delete a team by its ID.
     *
     * @param teamId The ID of the team to delete.
     * @return A ResponseEntity indicating the result of the deletion operation. It returns OK status in case of success,
     * and a Bad Request status in case of failure.
     */
    @DeleteMapping("/{teamId}")
    public ResponseEntity<?> deleteTeam(@PathVariable String teamId) {
        if (teamService.deleteTeam(teamId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * Get a team by its name.
     *
     * @param teamName The name of the team to retrieve.
     * @return A ResponseEntity containing the team details or a not found status.
     */
    @GetMapping("/name/{teamName}")
    public ResponseEntity<?> getTeamByName(@PathVariable String teamName){
        return Optional.ofNullable(teamService.getTeamByName(teamName))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    /**
     * Create a new team.
     *
     * @param teamBody The team information to be created.
     * @param bindingResult Contains any validation errors.
     * @return A ResponseEntity indicating the result of the creation operation. It returns OK status in case of success,
     * and a Bad Request status in case of failure.
     */
    @PostMapping
    public ResponseEntity<?> createTeam(@Valid @RequestBody Team teamBody, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Uno o mas campos estan vacios.");
        }

        if (teamService.createTeam(teamBody)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body("Ocurrio un error en el servidor.");
    }

    /**
     * Get the playstyle of a team by its ID.
     *
     * @param teamId The ID of the team.
     * @return A ResponseEntity containing the team's playstyle or a not found status.
     */
    @GetMapping("/{teamId}/playstyle")
    public ResponseEntity<?> getTeamPlaystyleByTeamId(@PathVariable String teamId){
        return Optional.ofNullable(teamService.getTeamPlaystyle(teamId))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    /**
     * Get the statistics of a team by its ID.
     *
     * @param teamId The ID of the team.
     * @return A ResponseEntity containing the team's statistics or a not found status.
     */
    @GetMapping("/{teamId}/stats")
    public ResponseEntity<?> getTeamStatsByTeamId(@PathVariable String teamId){
        return Optional.ofNullable(teamService.getTeamStats(teamId))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    /**
     * Get the coach of a team by its ID.
     *
     * @param teamId The ID of the team.
     * @return A ResponseEntity containing the team's coach details or a not found status.
     */
    @GetMapping("/{teamId}/coach")
    public ResponseEntity<?> getTeamCoachByTeamId(@PathVariable String teamId){
        return Optional.ofNullable(teamService.getTeamCoach(teamId))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    /**
     * Get the players of a team by its ID.
     *
     * @param teamId The ID of the team.
     * @return A ResponseEntity containing the team's players or a not found status.
     */
    @GetMapping("/{teamId}/players")
    public ResponseEntity<?> getTeamPlayersByTeamId(@PathVariable String teamId){
        try{
        return Optional.ofNullable(teamService.getTeamPlayers(teamId))
                        .map(ResponseEntity::ok)
                        .orElseGet(ResponseEntity.notFound()::build);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Ocurrio un error en el servidor.");
        }
    }

    /**
     * Add a player to a team.
     *
     * @param teamId The ID of the team.
     * @param playerId The ID of the player to add.
     * @return A ResponseEntity indicating the result of the operation. It returns OK status in case of success,
     * and a Bad Request status in case of failure.
     */
    @PatchMapping("/{teamId}/players/add/{playerId}")
    public ResponseEntity<?> addPlayerToTeamPlayers(@PathVariable String teamId, @PathVariable String playerId) {
        if (teamService.addPlayerToTeam(teamId, playerId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * Remove a player from a team.
     *
     * @param teamId The ID of the team.
     * @param playerId The ID of the player to remove.
     * @return A ResponseEntity indicating the result of the operation. It returns OK status in case of success,
     * and a Bad Request status in case of failure.
     */
    @PatchMapping("/{teamId}/players/remove/{playerId}")
    public ResponseEntity<?> removePlayerFromTeamPlayers(@PathVariable String teamId, @PathVariable String playerId) {
        if (teamService.removePlayerFromTeam(teamId, playerId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * Remove a coach from a team.
     *
     * @param teamId The ID of the team.
     * @return A ResponseEntity indicating the result of the operation. It returns OK status in case of success,
     * and a Bad Request status in case of failure.
     */
    @DeleteMapping("/{teamId}/coach/remove")
    public ResponseEntity<?> removeCoach(@PathVariable String teamId) {
        if (teamService.removeCoachFromTeam(teamId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * Adds a coach to a specified team.
     *
     * @param teamId The unique identifier of the team to which the coach will be added.
     * @param coachId The unique identifier of the coach to be added to the team.
     * @return ResponseEntity representing the outcome of the operation. It returns an
     *         OK status in case of success, and a Bad Request status in case of failure.
     */
    @PutMapping("/{teamId}/coach/add/{coachId}")
    public ResponseEntity<?> addCoach(@PathVariable String teamId, @PathVariable String coachId) {
        if (teamService.addCoachToTeam(teamId, coachId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
