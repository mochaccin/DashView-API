package com.mochaccin.viewdash.controller;


import com.mochaccin.viewdash.model.League;
import com.mochaccin.viewdash.model.dto.LeagueDTO;
import com.mochaccin.viewdash.model.dto.TeamDTO;
import com.mochaccin.viewdash.service.ILeagueService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/leagues")
@CrossOrigin(origins = {"http://localhost:5173"})
public class LeagueController {

    @Autowired
    ILeagueService leagueService;

    @GetMapping
    public ResponseEntity<List<LeagueDTO>> getLeagues() {
        return ResponseEntity.ok(leagueService.getLeagues());
    }



    @PostMapping
    public ResponseEntity addLeague() {
        leagueService.addLeague();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/addTeams")
    public ResponseEntity addPlayerToTeam() {
        leagueService.addTeamToLeague(new ObjectId("652b1b2848dc890a9cad0ca9"), new ObjectId("653d7c19e5569e735015b8b5"));
        return ResponseEntity.status(HttpStatus.CREATED).build();
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

       // @GetMapping("/{leagueId}/league/team/details")
        




}
