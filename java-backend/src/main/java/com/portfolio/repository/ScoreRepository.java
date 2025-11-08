package com.portfolio.repository;

import com.portfolio.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
    // Find all scores for a specific game, ordered by points (high to low)
    List<Score> findByGameIdOrderByPointsDesc(String gameId);
    
    // Find all scores for a specific user
    List<Score> findByUserId(String userId);
    
    // Find top N scores for a game (leaderboard)
    @Query("SELECT s FROM Score s WHERE s.gameId = ?1 ORDER BY s.points DESC")
    List<Score> findTopScoresByGameId(String gameId);
    
    // Get user's best score for a game
    @Query("SELECT MAX(s.points) FROM Score s WHERE s.gameId = ?1 AND s.userId = ?2")
    Integer findBestScoreForUser(String gameId, String userId);
}