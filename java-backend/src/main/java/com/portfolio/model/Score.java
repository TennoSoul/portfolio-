package com.portfolio.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "scores")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String gameId;
    
    @Column(nullable = false)
    private String userId;
    
    @Column(nullable = false)
    private int points;
    
    @Column(nullable = false)
    private LocalDateTime timestamp;

    public Score() {
        this.timestamp = LocalDateTime.now();
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getGameId() { return gameId; }
    public void setGameId(String gameId) { this.gameId = gameId; }
    
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    
    public int getPoints() { return points; }
    public void setPoints(int points) { this.points = points; }
    
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}