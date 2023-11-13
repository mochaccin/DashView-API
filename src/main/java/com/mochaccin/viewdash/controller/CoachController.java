package com.mochaccin.viewdash.controller;

import com.mochaccin.viewdash.model.Coach;
import com.mochaccin.viewdash.service.ICoachService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coaches")
@CrossOrigin(origins = {"http://localhost:5173"})
public class CoachController {
    @Autowired
    ICoachService coachService;
    @PostMapping
    public ResponseEntity<?> createCoach(@Valid @RequestBody Coach coachBody, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Uno o mas campos estan vacios o erroneos.");
        }

        if (coachService.createCoach(coachBody)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("Ocurrio un error en el servidor.");
        }

    }

    @DeleteMapping("/{coachId}")
    public ResponseEntity<?> deleteCoach(@PathVariable String coachId) {
        if (coachService.deleteCoach(coachId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
