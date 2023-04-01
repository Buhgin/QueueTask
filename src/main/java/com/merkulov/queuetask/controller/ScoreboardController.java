package com.merkulov.queue.controller;

import com.merkulov.queue.entity.Scoreboard;
import com.merkulov.queue.service.ScoreboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ScoreboardController {
    private final ScoreboardService scoreboardService;
    // @PostMapping("scoreboard")

    public ResponseEntity<Scoreboard> createScoreboardCode() {

        return new ResponseEntity<>(scoreboardService.generateNextCode(), HttpStatus.OK);

    }

}
