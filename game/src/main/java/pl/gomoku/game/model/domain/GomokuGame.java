package pl.gomoku.game.model.domain;

public class GomokuGame {

    private final String whitePlayer;
    private final String blackPlayer;
    private final int[][] gameBoard;

    public GomokuGame(String whitePlayer, String blackPlayer, int boardSize) {
        if (boardSize < 10) {
            throw new IllegalArgumentException("BoardSize cannot be less then 10");
        }
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.gameBoard = new int[boardSize][boardSize];
    }

    public String getWhitePlayer() {
        return whitePlayer;
    }

    public int[][] getGameBoard() {
        return gameBoard;
    }

    public String getBlackPlayer() {
        return blackPlayer;
    }
}
