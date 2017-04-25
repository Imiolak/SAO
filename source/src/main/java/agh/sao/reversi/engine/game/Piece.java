package agh.sao.reversi.engine.game;

/**
 * Created by Imiolak on 14-Mar-17.
 */
public class Piece {
    private PieceColor currentColor;

    public Piece(PieceColor initialColor) {
        this.currentColor = initialColor;
    }

    public PieceColor getColor() {
        return currentColor;
    }

    public void flip() {
        currentColor = PieceColor.opposite(currentColor);
    }

    @Override
    public String toString() {
        return currentColor.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Piece piece = (Piece) other;
        return currentColor == piece.currentColor;
    }

    @Override
    public int hashCode() {
        return currentColor != null ? currentColor.hashCode() : 0;
    }
}
