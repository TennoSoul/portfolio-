package com.portfolio.controller;

import com.portfolio.model.*;
import com.portfolio.service.GameService;
import com.portfolio.service.TicTacToeService;
import com.portfolio.service.MemoryGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {
    
    @Autowired
    private GameService gameService;
    
    @Autowired
    private TicTacToeService ticTacToeService;
    
    @Autowired
    private MemoryGameService memoryGameService;

    /**
     * Get all available games
     */
    @GetMapping
    public ResponseEntity<List<Game>> getGames() {
        return ResponseEntity.ok(gameService.getAllGames());
    }

    /**
     * Submit score for any game
     */
    @PostMapping("/{gameId}/score")
    public ResponseEntity<Score> submitScore(
            @PathVariable String gameId,
            @RequestBody ScoreRequest request) {
        Score score = gameService.saveScore(gameId, request);
        return ResponseEntity.ok(score);
    }

    /**
     * Get leaderboard for a game
     */
    @GetMapping("/{gameId}/leaderboard")
    public ResponseEntity<List<Score>> getLeaderboard(
            @PathVariable String gameId,
            @RequestParam(defaultValue = "10") int limit) {
        List<Score> leaderboard = gameService.getLeaderboard(gameId, limit);
        return ResponseEntity.ok(leaderboard);
    }

    /**
     * Get user's scores
     */
    @GetMapping("/user/{userId}/scores")
    public ResponseEntity<List<Score>> getUserScores(@PathVariable String userId) {
        return ResponseEntity.ok(gameService.getUserScores(userId));
    }

    // ========== TIC-TAC-TOE ENDPOINTS ==========

    /**
     * Start new Tic-Tac-Toe game
     */
    @PostMapping("/tictactoe/new")
    public ResponseEntity<TicTacToeState> newTicTacToeGame() {
        return ResponseEntity.ok(ticTacToeService.newGame());
    }

    /**
     * Make a move in Tic-Tac-Toe
     */
    @PostMapping("/tictactoe/move")
    public ResponseEntity<TicTacToeState> makeTicTacToeMove(@RequestBody TicTacToeMove move) {
        TicTacToeState state = ticTacToeService.makeMove(move);
        return ResponseEntity.ok(state);
    }

    /**
     * Get AI move for Tic-Tac-Toe
     */
    @PostMapping("/tictactoe/ai-move")
    public ResponseEntity<TicTacToeState> getAIMove(@RequestBody TicTacToeState currentState) {
        TicTacToeState state = ticTacToeService.makeAIMove(currentState.getBoard());
        return ResponseEntity.ok(state);
    }

    // ========== MEMORY GAME ENDPOINTS ==========

    /**
     * Create new Memory Game
     */
    @PostMapping("/memory/new")
    public ResponseEntity<MemoryGameState> newMemoryGame() {
        return ResponseEntity.ok(memoryGameService.createNewGame());
    }

    /**
     * Calculate score for Memory Game
     */
    @PostMapping("/memory/calculate-score")
    public ResponseEntity<Integer> calculateMemoryScore(
            @RequestParam int moves,
            @RequestParam int timeInSeconds) {
        int score = memoryGameService.calculateScore(moves, timeInSeconds);
        return ResponseEntity.ok(score);
    }

    /**
     * Get optimal score for Memory Game
     */
    @GetMapping("/memory/optimal-score")
    public ResponseEntity<Integer> getOptimalScore() {
        return ResponseEntity.ok(memoryGameService.getOptimalScore());
    }
}
