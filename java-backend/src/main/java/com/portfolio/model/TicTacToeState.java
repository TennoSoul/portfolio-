package com.portfolio.model;

import java.util.Arrays;

public class TicTacToeState {
    private String[][] board;
    private String currentPlayer;
    private String winner;
    private boolean gameOver;
    private String message;

    public TicTacToeState() {
        this.board = new String[3][3];
        for (int i = 0; i < 3; i++) {
            Arrays.fill(board[i], "");
        }
        this.currentPlayer = "X";
        this.gameOver = false;
    }

    // Getters and setters
    public String[][] getBoard() { return board; }
    public void setBoard(String[][] board) { this.board = board; }
    
    public String getCurrentPlayer() { return currentPlayer; }
    public void setCurrentPlayer(String currentPlayer) { this.currentPlayer = currentPlayer; }
    
    public String getWinner() { return winner; }
    public void setWinner(String winner) { this.winner = winner; }
    
    public boolean isGameOver() { return gameOver; }
    public void setGameOver(boolean gameOver) { this.gameOver = gameOver; }
    
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}