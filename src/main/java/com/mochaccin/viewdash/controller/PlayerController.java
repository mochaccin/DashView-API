package com.mochaccin.viewdash.controller;

import com.mochaccin.viewdash.model.Player;
import com.mochaccin.viewdash.model.Team;
import com.mochaccin.viewdash.model.dto.PlayerDTO;
import com.mochaccin.viewdash.service.IPlayerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST Controller for managing players.
 * Provides endpoints for operations related to players.
 */
@RestController
@RequestMapping("/players")
@CrossOrigin(origins = {"http://localhost:5173"})
public class PlayerController {

    @Autowired
    private IPlayerService playerService;

    /**
     * Get all players.
     *
     * @return A ResponseEntity containing a list of all players.
     */
    @GetMapping
    public ResponseEntity<List<PlayerDTO>> getPlayers() {
        return ResponseEntity.ok(playerService.getAllPlayers());
    }

    /**
     * Get a player by their nickname.
     *
     * @param playerNickname The nickname of the player.
     * @return A ResponseEntity containing the player details or a not found status.
     */
    @GetMapping("/nickname/{playerNickname}")
    public ResponseEntity<?> getPlayerByNickname(@PathVariable String playerNickname){
        return Optional.ofNullable(playerService.getPlayerByNickname(playerNickname))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    /**
     * Create a new player.
     *
     * @param playerBody The player information to be created.
     * @param bindingResult Contains any validation errors.
     * @return A ResponseEntity indicating the result of the creation operation.
     */
    @PostMapping
    public ResponseEntity<?> createPlayer(@Valid @RequestBody Player playerBody, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Uno o mas campos estan vacios.");
        }

        if (playerService.createPlayer(playerBody)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body("Ocurrio un error en el servidor.");
    }

    /**
     * Get a player by their ID.
     *
     * @param playerId The ID of the player to retrieve.
     * @return A ResponseEntity containing the player details or a not found status.
     */
    @GetMapping("/{playerId}")
    public ResponseEntity<?> getPlayerById(@PathVariable String playerId){
        return Optional.ofNullable(playerService.getPlayerById(playerId))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    /**
     * Delete a player by their ID.
     *
     * @param playerId The ID of the player to delete.
     * @return A ResponseEntity indicating the result of the deletion operation.
     */
    @DeleteMapping("/{playerId}")
    public ResponseEntity<?> deletePlayer(@PathVariable String playerId) {
        if (playerService.deletePlayer(playerId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
