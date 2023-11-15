package com.mochaccin.viewdash.controller;

import com.mochaccin.viewdash.service.IPlayerStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * <h2>REST Controller for managing player statistics.</h2>
 * <p>Provides endpoints for CRUD operations related to player statistics.</p>
 */
@RestController
@RequestMapping("/playerStats")
@CrossOrigin(origins = {"http://localhost:5173"})
public class PlayerStatsController {

    @Autowired
    IPlayerStatsService playerStatsService;

    /**
     * Get statistics for a specific player by their ID.
     *
     * @param playerId The ID of the player whose statistics are being requested.
     * @return A ResponseEntity containing the player's statistics or a not found status.
     */
    @GetMapping("/{playerId}")
    public ResponseEntity<?> getPlayerStats(@PathVariable String playerId){
        return Optional.ofNullable(playerStatsService.getPlayerStatsByPlayerId(playerId))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    /**
     * Add statistics to a specific player by their ID.
     *
     * @param playerId The ID of the player to whom statistics are to be added.
     * @return A ResponseEntity indicating the result of the operation.
     */
    @PutMapping("/{playerId}")
    public ResponseEntity<?> addStatsToPlayer(@PathVariable String playerId) {
        if (playerStatsService.addStatsToPlayer(playerId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
