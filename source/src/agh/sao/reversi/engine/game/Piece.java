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
        if (currentColor == PieceColor.Dark) {
            currentColor = PieceColor.Light;
        } else {
            currentColor = PieceColor.Dark;
        }
    }

}
