package agh.sao.reversi.engine.game;

/**
 * Created by Imiolak on 14-Mar-17.
 */
public enum PieceColor {
    Dark,
    Light;

    static PieceColor opposite(PieceColor original) {
        if (original == PieceColor.Dark) {
            return PieceColor.Light;
        } else {
            return PieceColor.Dark;
        }
    }

    public static PieceColor fromString(String stringValue) {
        if ("B".equals(stringValue)) return Dark;
        if ("W".equals(stringValue)) return Light;
        throw new IllegalArgumentException("Invalid string representation of piece color, possible: W,B");
    }

    @Override
    public String toString() {
        if (this == Dark) return "B";
        else return "W";
    }
}
