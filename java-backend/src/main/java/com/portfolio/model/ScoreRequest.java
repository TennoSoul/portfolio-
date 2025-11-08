package com.portfolio.model;

public class ScoreRequest {
    private String userId;
    private int points;

    public ScoreRequest() {}

    public ScoreRequest(String userId, int points) {
        this.userId = userId;
        this.points = points;
    }

    // Getters and setters
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    
    public int getPoints() { return points; }
    public void setPoints(int points) { this.points = points; }
}