package com.merkulov.queuetask.controller;

import com.merkulov.queuetask.entity.Scoreboard;
import com.merkulov.queuetask.service.ScoreboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/scoreboard")
public class ScoreboardController {
    private final ScoreboardService scoreboardService;
    @PostMapping
    public ResponseEntity<Scoreboard> createScoreboardCode() {
        return new ResponseEntity<>(scoreboardService.generateNextCode(), HttpStatus.OK);
    }
}
