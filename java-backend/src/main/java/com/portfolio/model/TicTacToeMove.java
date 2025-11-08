package com.portfolio.model;

public class TicTacToeMove {
    private int row;
    private int col;
    private String[][] currentBoard;

    public TicTacToeMove() {}

    public TicTacToeMove(int row, int col, String[][] currentBoard) {
        this.row = row;
        this.col = col;
        this.currentBoard = currentBoard;
    }

    // Getters and setters
    public int getRow() { return row; }
    public void setRow(int row) { this.row = row; }
    
    public int getCol() { return col; }
    public void setCol(int col) { this.col = col; }
    
    public String[][] getCurrentBoard() { return currentBoard; }
    public void setCurrentBoard(String[][] currentBoard) { this.currentBoard = currentBoard; }
}
