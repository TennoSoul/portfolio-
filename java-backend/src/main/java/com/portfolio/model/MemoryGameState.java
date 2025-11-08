package com.portfolio.model;

import java.util.List;

public class MemoryGameState {
    private List<String> cards;
    private int moves;
    private int matchedPairs;
    private boolean gameWon;

    public MemoryGameState() {}

    public MemoryGameState(List<String> cards, int moves, int matchedPairs, boolean gameWon) {
        this.cards = cards;
        this.moves = moves;
        this.matchedPairs = matchedPairs;
        this.gameWon = gameWon;
    }

    // Getters and setters
    public List<String> getCards() { return cards; }
    public void setCards(List<String> cards) { this.cards = cards; }
    
    public int getMoves() { return moves; }
    public void setMoves(int moves) { this.moves = moves; }
    
    public int getMatchedPairs() { return matchedPairs; }
    public void setMatchedPairs(int matchedPairs) { this.matchedPairs = matchedPairs; }
    
    public boolean isGameWon() { return gameWon; }
    public void setGameWon(boolean gameWon) { this.gameWon = gameWon; }
}
