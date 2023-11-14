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

@RestController
@RequestMapping("/teams")
@CrossOrigin(origins = {"http://localhost:5173"})
public class TeamController {

    @Autowired
    ITeamService teamService;

    @GetMapping
    public ResponseEntity<List<TeamDTO>> getTeams() {
        return ResponseEntity.ok(teamService.getTeams());
    }

    @DeleteMapping("/{teamId}")
    public ResponseEntity<?> deleteTeam(@PathVariable String teamId) {
        if (teamService.deleteTeam(teamId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/name/{teamName}")
    public ResponseEntity<?> getTeamByName(@PathVariable String teamName){
        return Optional.ofNullable(teamService.getTeamByName(teamName))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

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

    @GetMapping("/{teamId}/playstyle")
    public ResponseEntity<?> getTeamPlaystyleByTeamId(@PathVariable String teamId){
        return Optional.ofNullable(teamService.getTeamPlaystyle(teamId))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping("/{teamId}/stats")
    public ResponseEntity<?> getTeamStatsByTeamId(@PathVariable String teamId){
        return Optional.ofNullable(teamService.getTeamStats(teamId))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping("/{teamId}/coach")
    public ResponseEntity<?> getTeamCoachByTeamId(@PathVariable String teamId){
        return Optional.ofNullable(teamService.getTeamCoach(teamId))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

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

    @PatchMapping("/{teamId}/players/add/{playerId}")
    public ResponseEntity<?> addPlayerToTeamPlayers(@PathVariable String teamId, @PathVariable String playerId) {
        if (teamService.addPlayerToTeam(teamId, playerId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PatchMapping("/{teamId}/players/remove/{playerId}")
    public ResponseEntity<?> removePlayerFromTeamPlayers(@PathVariable String teamId, @PathVariable String playerId) {
        if (teamService.removePlayerFromTeam(teamId, playerId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{teamId}/coach/remove")
    public ResponseEntity<?> removeCoach(@PathVariable String teamId) {
        if (teamService.removeCoachFromTeam(teamId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{teamId}/coach/add/{coachId}")
    public ResponseEntity<?> addCoach(@PathVariable String teamId, @PathVariable String coachId) {
        if (teamService.addCoachToTeam(teamId, coachId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
