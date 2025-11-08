package com.portfolio.service;

import com.portfolio.model.TicTacToeState;
import com.portfolio.model.TicTacToeMove;
import org.springframework.stereotype.Service;

@Service
public class TicTacToeService {

    /**
     * Process a player's move and return updated game state
     */
    public TicTacToeState makeMove(TicTacToeMove move) {
        TicTacToeState state = new TicTacToeState();
        
        // Copy the board from request
        if (move.getCurrentBoard() != null) {
            state.setBoard(move.getCurrentBoard());
        }
        
        int row = move.getRow();
        int col = move.getCol();
        
        // Validate move
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            state.setMessage("Invalid move: out of bounds");
            return state;
        }
        
        if (!state.getBoard()[row][col].isEmpty()) {
            state.setMessage("Invalid move: cell already occupied");
            return state;
        }
        
        // Make the move
        String currentPlayer = state.getCurrentPlayer();
        state.getBoard()[row][col] = currentPlayer;
        
        // Check for winner
        String winner = checkWinner(state.getBoard());
        if (winner != null) {
            state.setWinner(winner);
            state.setGameOver(true);
            state.setMessage(winner.equals("Draw") ? "It's a draw!" : "Player " + winner + " wins!");
            return state;
        }
        
        // Check for draw
        if (isBoardFull(state.getBoard())) {
            state.setWinner("Draw");
            state.setGameOver(true);
            state.setMessage("It's a draw!");
            return state;
        }
        
        // Switch player
        state.setCurrentPlayer(currentPlayer.equals("X") ? "O" : "X");
        state.setMessage("Player " + state.getCurrentPlayer() + "'s turn");
        
        return state;
    }

    /**
     * Make an AI move (simple random strategy)
     */
    public TicTacToeState makeAIMove(String[][] board) {
        TicTacToeState state = new TicTacToeState();
        state.setBoard(board);
        state.setCurrentPlayer("O");
        
        // Try to find a winning move first
        int[] winMove = findWinningMove(board, "O");
        if (winMove != null) {
            return executeMoveInternal(state, winMove[0], winMove[1]);
        }
        
        // Block opponent's winning move
        int[] blockMove = findWinningMove(board, "X");
        if (blockMove != null) {
            return executeMoveInternal(state, blockMove[0], blockMove[1]);
        }
        
        // Take center if available
        if (board[1][1].isEmpty()) {
            return executeMoveInternal(state, 1, 1);
        }
        
        // Take a corner
        int[][] corners = {{0,0}, {0,2}, {2,0}, {2,2}};
        for (int[] corner : corners) {
            if (board[corner[0]][corner[1]].isEmpty()) {
                return executeMoveInternal(state, corner[0], corner[1]);
            }
        }
        
        // Take any available space
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].isEmpty()) {
                    return executeMoveInternal(state, i, j);
                }
            }
        }
        
        state.setMessage("No valid moves available");
        return state;
    }

    /**
     * Find a winning move for a player
     */
    private int[] findWinningMove(String[][] board, String player) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].isEmpty()) {
                    // Try this move
                    board[i][j] = player;
                    if (checkWinner(board) != null && checkWinner(board).equals(player)) {
                        board[i][j] = ""; // Undo
                        return new int[]{i, j};
                    }
                    board[i][j] = ""; // Undo
                }
            }
        }
        return null;
    }

    /**
     * Execute a move internally
     */
    private TicTacToeState executeMoveInternal(TicTacToeState state, int row, int col) {
        state.getBoard()[row][col] = state.getCurrentPlayer();
        
        String winner = checkWinner(state.getBoard());
        if (winner != null) {
            state.setWinner(winner);
            state.setGameOver(true);
            state.setMessage(winner.equals("Draw") ? "It's a draw!" : "Player " + winner + " wins!");
            return state;
        }
        
        if (isBoardFull(state.getBoard())) {
            state.setWinner("Draw");
            state.setGameOver(true);
            state.setMessage("It's a draw!");
            return state;
        }
        
        state.setCurrentPlayer(state.getCurrentPlayer().equals("X") ? "O" : "X");
        state.setMessage("Player " + state.getCurrentPlayer() + "'s turn");
        return state;
    }

    /**
     * Check if there's a winner
     */
    private String checkWinner(String[][] board) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (!board[i][0].isEmpty() && 
                board[i][0].equals(board[i][1]) && 
                board[i][1].equals(board[i][2])) {
                return board[i][0];
            }
        }
        
        // Check columns
        for (int j = 0; j < 3; j++) {
            if (!board[0][j].isEmpty() && 
                board[0][j].equals(board[1][j]) && 
                board[1][j].equals(board[2][j])) {
                return board[0][j];
            }
        }
        
        // Check diagonals
        if (!board[0][0].isEmpty() && 
            board[0][0].equals(board[1][1]) && 
            board[1][1].equals(board[2][2])) {
            return board[0][0];
        }
        
        if (!board[0][2].isEmpty() && 
            board[0][2].equals(board[1][1]) && 
            board[1][1].equals(board[2][0])) {
            return board[0][2];
        }
        
        return null;
    }

    /**
     * Check if board is full
     */
    private boolean isBoardFull(String[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Create a new game
     */
    public TicTacToeState newGame() {
        TicTacToeState state = new TicTacToeState();
        state.setMessage("Player X's turn");
        return state;
    }

    /**
     * Validate game state
     */
    public boolean isValidState(TicTacToeState state) {
        if (state.getBoard() == null || state.getBoard().length != 3) {
            return false;
        }
        
        for (int i = 0; i < 3; i++) {
            if (state.getBoard()[i] == null || state.getBoard()[i].length != 3) {
                return false;
            }
        }
        
        return true;
    }
}