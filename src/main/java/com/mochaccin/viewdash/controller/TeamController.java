package com.mochaccin.viewdash.controller;


import com.mochaccin.viewdash.model.Player;
import com.mochaccin.viewdash.model.Team;
import com.mochaccin.viewdash.service.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
@CrossOrigin(origins = {"http://localhost:5173"})
public class TeamController {

    @Autowired
    ITeamService teamService;

    @GetMapping
    public ResponseEntity<List<Team>> getTeams() {
        return ResponseEntity.ok(teamService.getTeams());
    }

    @PostMapping
    public ResponseEntity addTeam() {
        teamService.addTeam();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
