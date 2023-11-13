package com.mochaccin.viewdash.controller;

import com.mochaccin.viewdash.service.IPlayerStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/playerStats")
@CrossOrigin(origins = {"http://localhost:5173"})
public class PlayerStatsController {

    @Autowired
    IPlayerStatsService playerStatsService;
    @GetMapping("/{playerId}")
    public ResponseEntity<?> getPlayerStats(@PathVariable String playerId){
        return Optional.ofNullable(playerStatsService.getPlayerStatsByPlayerId(playerId))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @PutMapping("/{playerId}")
    public ResponseEntity<?> addStatsToPlayer(@PathVariable String playerId) {
        if (playerStatsService.addStatsToPlayer(playerId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
