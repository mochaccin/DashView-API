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

@RestController
@RequestMapping("/leagues")
@CrossOrigin(origins = {"http://localhost:5173"})
public class LeagueController {

    @Autowired
    ILeagueService leagueService;

    @GetMapping
    public ResponseEntity<?> getLeagues() {
        return ResponseEntity.ok(leagueService.getLeagues());
    }

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

    @DeleteMapping("/{leagueId}")
    public ResponseEntity<?> deleteLeague(@PathVariable String leagueId) {
        if (leagueService.deleteLeague(leagueId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{leagueName}")
    public ResponseEntity<LeagueDTO> getLeagueByName(@PathVariable String leagueName) {
        return Optional.ofNullable(leagueService.getLeagueByName(leagueName))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping("/{leagueId}/teams")
    public ResponseEntity<List<TeamDTO>> getTeamsByLeagueId(@PathVariable String leagueId) {
    List<TeamDTO> teams = leagueService.getTeamsByLeagueId(leagueId);
        if (teams != null && !teams.isEmpty()) {
            return ResponseEntity.ok(teams);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{leagueId}/leagueTeamDetails")
    public ResponseEntity<?> getLeagueTeamDetailsByLeagueId(@PathVariable String leagueId) {
        HashMap<String, TeamLeagueDetails> leagueTeamsDetails = leagueService.getTeamDetailsByLeagueId(leagueId);
        if (leagueTeamsDetails != null && !leagueTeamsDetails.isEmpty()) {
            return ResponseEntity.ok(leagueTeamsDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("{leagueId}/teams/add/{teamId}")
    public ResponseEntity<?> addTeamToLeagueTeams(@PathVariable String leagueId, @PathVariable String teamId) {
        if (leagueService.addTeamToLeague(leagueId, teamId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PatchMapping("{leagueId}/teams/remove/{teamId}")
    public ResponseEntity<?> removeTeamFromLeagueTeams(@PathVariable String leagueId, @PathVariable String teamId) {
        if (leagueService.removeTeamFromLeague(leagueId, teamId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
