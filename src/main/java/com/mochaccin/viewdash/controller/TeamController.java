package com.mochaccin.viewdash.controller;


import com.mochaccin.viewdash.model.Player;
import com.mochaccin.viewdash.model.Team;
import com.mochaccin.viewdash.model.dto.TeamDTO;
import com.mochaccin.viewdash.service.ITeamService;
import org.bson.types.ObjectId;
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
    public ResponseEntity<List<TeamDTO>> getTeams() {
        return ResponseEntity.ok(teamService.getTeams());
    }

    @PostMapping
    public ResponseEntity addTeam() {
        teamService.addTeam();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/addPlayer")
    public ResponseEntity addPlayerToTeam() {
        teamService.addPlayerToTeam(new ObjectId("653ec29d9fd2f316d033a019"), new ObjectId("653ebe636f374330d1c2dee9"));
        teamService.addPlayerToTeam(new ObjectId("653ec29d9fd2f316d033a019"), new ObjectId("653ec2fb5efb474b11d333a3"));
        teamService.addPlayerToTeam(new ObjectId("653ec29d9fd2f316d033a019"), new ObjectId("653ec441975d6c3483d005cc"));
        teamService.addPlayerToTeam(new ObjectId("653ec29d9fd2f316d033a019"), new ObjectId("653ec47f80289904d84d6dff"));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
