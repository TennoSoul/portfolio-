package com.portfolio.controller;

import com.portfolio.model.Game;
import com.portfolio.model.Score;
import com.portfolio.model.ScoreRequest;
import com.portfolio.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {
    @Autowired
    private GameService gameService;

    @PostMapping("/{gameId}/score")
    public ResponseEntity<Score> submitScore(
        @PathVariable String gameId,
        @RequestBody ScoreRequest request) {
        Score score = gameService.saveScore(request);
        return ResponseEntity.ok(score);
    }

    @GetMapping
    public List<Game> getGames() {
        return gameService.getAllGames();
    }
}