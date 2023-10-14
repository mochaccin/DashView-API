package com.mochaccin.viewdash.controller;


import com.mochaccin.viewdash.model.League;
import com.mochaccin.viewdash.service.ILeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leagues")
@CrossOrigin(origins = {"http://localhost:5173"})
public class LeagueController {

    @Autowired
    ILeagueService leagueService;

    @GetMapping
    public ResponseEntity<List<League>> getLeagues() {
        return ResponseEntity.ok(leagueService.getLeagues());
    }

    @PostMapping
    public ResponseEntity addLeague() {
        leagueService.addLeague();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
