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

}
