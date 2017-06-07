package agh.sao.reversi.engine.game;

import java.util.Arrays;

/**
 * Created by Imiolak on 14-Mar-17.
 */
public class GameState {

    private final Piece[][] board;

    public GameState() {
        this(new String[][]{
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "W", "B", "*", "*", "*"},
                {"*", "*", "*", "B", "W", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
        });
    }

    public GameState(String[][] initialState) {
        this.board = fromStringBoard(initialState);
    }

    public void applyMove(Move move) {
        MoveAvailabilityChecker availabilityChecker = new MoveAvailabilityChecker(this);
        if (!availabilityChecker.canMove(move)) {
            throw new IllegalArgumentException("Move " + move + " not allowed, you nasty player");
        }
        availabilityChecker.willCapture(move).forEach(this::flipPosition);
        board[move.toPosition.rowNumberToFieldIndex()][move.toPosition.columnNameToFieldIndex()] = new Piece(move.pieceColor);
    }

    private void flipPosition(Position position) {
        board[position.rowNumberToFieldIndex()][position.columnNameToFieldIndex()].flip();
    }

    public Piece valueAt(Position position) {
        return board[position.rowNumberToFieldIndex()][position.columnNameToFieldIndex()];
    }

    public Piece valueAt(int row, int col) {
        return board[row][col];
    }

    private Piece[][] fromStringBoard(String[][] initialState) {
        Piece[][] board = new Piece[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ("*".equals(initialState[i][j])) {
                    board[i][j] = null;
                } else {
                    board[i][j] = new Piece(PieceColor.fromString(initialState[i][j]));
                }
            }
        }
        return board;
    }

    public String[][] toStringBoard() {
        String[][] stringBoard = new String[8][8];
        int i = 0;
        for (Piece[] row : board) {
            int k = 0;
            for (Piece value : row) {
                if (value != null) {
                    stringBoard[i][k++] = value.toString();
                } else {
                    stringBoard[i][k++] = "*";
                }
            }
            i++;
        }
        return stringBoard;
    }

    @Override
    public String toString() {
        StringBuilder boardAsString = new StringBuilder();
        for (Piece[] row : board) {
            for (Piece value : row) {
                if (value != null) {
                    boardAsString.append(value);
                } else {
                    boardAsString.append("*");
                }
                boardAsString.append(" ");
            }
            boardAsString.append("\n");
        }
        return boardAsString.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        GameState gameState = (GameState) other;
        return Arrays.deepEquals(board, gameState.board);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board);
    }

    public int countDark() {
        int count = 0;
        for (Piece[] pieces : board)
            for (Piece piece : pieces)
                if (piece != null && piece.getColor() == PieceColor.Dark)
                    count++;
        return count;
    }

    public int countLight() {
        int count = 0;
        for (Piece[] pieces : board)
            for (Piece piece : pieces)
                if (piece != null && piece.getColor() == PieceColor.Light)
                    count++;
        return count;
    }
}
