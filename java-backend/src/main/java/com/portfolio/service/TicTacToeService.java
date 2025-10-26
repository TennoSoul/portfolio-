package com.portfolio.service;

import org.springframework.stereotype.Service;

@Service
public class TicTacToeService {
    public GameState makeMove(GameState state, int row, int col) {
        // Placeholder logic; implement actual game logic
        return state;
    }
}

class GameState {
    private String[][] board;
    private String currentPlayer;

    public GameState() {
        this.board = new String[3][3];
        this.currentPlayer = "X";
    }

    // Getters and setters
    public String[][] getBoard() { return board; }
    public void setBoard(String[][] board) { this.board = board; }
    public String getCurrentPlayer() { return currentPlayer; }
    public void setCurrentPlayer(String currentPlayer) { this.currentPlayer = currentPlayer; }
}