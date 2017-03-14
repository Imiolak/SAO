package agh.sao.reversi.engine.game;

/**
 * Created by Imiolak on 14-Mar-17.
 */
public class GameState {

    private final Piece[][] board;

    public GameState() {
        this.board = new Piece[8][8];
        this.board[3][3] = new Piece(PieceColor.Light);
        this.board[4][4] = new Piece(PieceColor.Light);
        this.board[3][4] = new Piece(PieceColor.Light);
        this.board[4][3] = new Piece(PieceColor.Light);
    }

    public void applyMove(Move move) {

    }
}
