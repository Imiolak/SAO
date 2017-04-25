package agh.sao.reversi.engine.game;

/**
 * Created by Imiolak on 14-Mar-17.
 */
public class Move {

    public final PieceColor pieceColor;
    public final Position toPosition;

    public Move(PieceColor pieceColor, Position toPosition) {
        this.pieceColor = pieceColor;
        this.toPosition = toPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        if (pieceColor != move.pieceColor) return false;
        return toPosition != null ? toPosition.equals(move.toPosition) : move.toPosition == null;

    }

    @Override
    public int hashCode() {
        int result = pieceColor != null ? pieceColor.hashCode() : 0;
        result = 31 * result + (toPosition != null ? toPosition.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("Move: color - %s, position - %s", pieceColor, toPosition);
    }
}
