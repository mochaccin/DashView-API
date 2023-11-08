package com.mochaccin.viewdash.controller;


import com.mochaccin.viewdash.model.Player;
import com.mochaccin.viewdash.model.Team;
import com.mochaccin.viewdash.model.TeamLeagueDetails;
import com.mochaccin.viewdash.model.dto.TeamDTO;
import com.mochaccin.viewdash.service.ITeamService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/name/{teamName}")
    public ResponseEntity<?> getTeamByName(@PathVariable String teamName){
        return Optional.ofNullable(teamService.getTeamByName(teamName))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @PostMapping
    public ResponseEntity addTeam() {
        teamService.addTeam();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/addPlayer")
    public ResponseEntity addPlayerToTeam() {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
