package com.portfolio.service;

import com.portfolio.model.Game;
import com.portfolio.model.Score;
import com.portfolio.model.ScoreRequest;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class GameService {
    public Score saveScore(ScoreRequest request) {
        Score score = new Score();
        score.setUserId(request.getUserId());
        score.setPoints(request.getPoints());
        score.setGameId("memory-game"); // Example
        score.setTimestamp(LocalDateTime.now());
        return score; // In a real app, save to a database
    }

    public List<Game> getAllGames() {
        return Arrays.asList(
            new Game("1", "Memory Match"),
            new Game("2", "Tic-Tac-Toe")
        );
    }
}