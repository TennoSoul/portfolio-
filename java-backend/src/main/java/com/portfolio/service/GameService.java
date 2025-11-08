package com.portfolio.service;

import com.portfolio.model.Game;
import com.portfolio.model.Score;
import com.portfolio.model.ScoreRequest;
import com.portfolio.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class GameService {
    
    @Autowired
    private ScoreRepository scoreRepository;

    public List<Game> getAllGames() {
        return Arrays.asList(
            new Game("1", "Memory Match", "Match pairs of cards"),
            new Game("2", "Tic-Tac-Toe", "Classic 3x3 game")
        );
    }

    public Score saveScore(String gameId, ScoreRequest request) {
        Score score = new Score();
        score.setGameId(gameId);
        score.setUserId(request.getUserId());
        score.setPoints(request.getPoints());
        score.setTimestamp(LocalDateTime.now());
        
        return scoreRepository.save(score);
    }

    public List<Score> getLeaderboard(String gameId, int limit) {
        List<Score> allScores = scoreRepository.findByGameIdOrderByPointsDesc(gameId);
        return allScores.stream().limit(limit).toList();
    }

    public List<Score> getUserScores(String userId) {
        return scoreRepository.findByUserId(userId);
    }

    public Integer getBestScore(String gameId, String userId) {
        return scoreRepository.findBestScoreForUser(gameId, userId);
    }
}
