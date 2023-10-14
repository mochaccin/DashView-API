package com.mochaccin.viewdash.controller;


import com.mochaccin.viewdash.model.Player;
import com.mochaccin.viewdash.service.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/players")
@CrossOrigin(origins = {"http://localhost:5173"})
public class PlayerController {

    @Autowired
    private IPlayerService playerService;

    @GetMapping
    public ResponseEntity<List<Player>> getPlayers() {
        return ResponseEntity.ok(playerService.getAllPlayers());
    }

    @PostMapping
    public ResponseEntity addPlayer() {
        playerService.addPlayer();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPlayerStats(@PathVariable String id){
        return Optional.ofNullable(playerService.getPlayerStats(id))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }
}
