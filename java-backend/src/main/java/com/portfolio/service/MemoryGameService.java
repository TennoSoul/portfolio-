package com.portfolio.service;

import com.portfolio.model.MemoryGameState;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class MemoryGameService {
    
    private static final List<String> CARD_SYMBOLS = Arrays.asList(
        "🎮", "🎯", "🎲", "🎪", "🎨", "🎭", "🎬", "🎸"
    );

    /**
     * Create a new shuffled game
     */
    public MemoryGameState createNewGame() {
        List<String> cards = new ArrayList<>();
        
        // Add pairs of cards
        for (String symbol : CARD_SYMBOLS) {
            cards.add(symbol);
            cards.add(symbol);
        }
        
        // Shuffle
        Collections.shuffle(cards);
        
        MemoryGameState state = new MemoryGameState();
        state.setCards(cards);
        state.setMoves(0);
        state.setMatchedPairs(0);
        state.setGameWon(false);
        
        return state;
    }

    /**
     * Validate if two cards match
     */
    public boolean cardsMatch(List<String> cards, int index1, int index2) {
        if (index1 < 0 || index1 >= cards.size() || 
            index2 < 0 || index2 >= cards.size() || 
            index1 == index2) {
            return false;
        }
        
        return cards.get(index1).equals(cards.get(index2));
    }

    /**
     * Calculate score based on moves and time
     */
    public int calculateScore(int moves, int timeInSeconds) {
        // Base score starts at 1000
        int score = 1000;
        
        // Deduct points for each move (fewer moves = higher score)
        score -= (moves * 10);
        
        // Deduct points for time (faster = higher score)
        score -= (timeInSeconds / 2);
        
        // Ensure minimum score of 0
        return Math.max(score, 0);
    }

    /**
     * Calculate optimal score for perfect game
     */
    public int getOptimalScore() {
        // Perfect game: 8 pairs = 8 moves minimum
        int optimalMoves = 8;
        int optimalTime = 30; // 30 seconds
        return calculateScore(optimalMoves, optimalTime);
    }

    /**
     * Check if game is complete
     */
    public boolean isGameComplete(int matchedPairs, int totalPairs) {
        return matchedPairs >= totalPairs;
    }

    /**
     * Get hint for next move (for easy mode)
     */
    public int[] getHint(List<String> cards, Set<Integer> flippedIndices) {
        Map<String, List<Integer>> cardPositions = new HashMap<>();
        
        // Build map of card positions
        for (int i = 0; i < cards.size(); i++) {
            if (!flippedIndices.contains(i)) {
                String card = cards.get(i);
                cardPositions.computeIfAbsent(card, k -> new ArrayList<>()).add(i);
            }
        }
        
        // Find first pair
        for (List<Integer> positions : cardPositions.values()) {
            if (positions.size() >= 2) {
                return new int[]{positions.get(0), positions.get(1)};
            }
        }
        
        return null;
    }
}