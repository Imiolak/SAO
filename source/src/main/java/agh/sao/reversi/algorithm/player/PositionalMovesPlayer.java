package agh.sao.reversi.algorithm.player;

import agh.sao.reversi.engine.game.GameState;
import agh.sao.reversi.engine.game.Move;
import agh.sao.reversi.engine.game.PieceColor;
import agh.sao.reversi.engine.player.IPlayer;

import java.util.List;
import java.util.Comparator;
import java.util.Collections;

public class PositionalMovesPlayer implements IPlayer {
    private final int[][] riskBoard;

    private PieceColor playerColor;

    public PositionalMovesPlayer(int a,int b,int c,int d,int e) {
        this.riskBoard = new int[][] { 	{ e, d, c, c, c, c, d, e },
                                        { d, d, b, b, b, b, d, d },
                                        { c, b, a, a, a, a, b, c },
                                        { c, b, a, a, a, a, b, c },
                                        { c, b, a, a, a, a, b, c },
                                        { c, b, a, a, a, a, b, c },
                                        { d, d, b, b, b, b, d, d },
                                        { e, d, c, c, c, c, d, e }};
    }

    @Override
    public void setPlayerColor(PieceColor playerColor) {
        this.playerColor = playerColor;
    }

    @Override
    public PieceColor getPlayerColor() {
        return this.playerColor;
    }

    private int compareMoves(Move o1, Move o2) {
        int risk1Move = this.riskBoard[o1.toPosition.getRowNumberToFieldIndex()-1][o1.toPosition.getColumnNameToFieldIndex()];
        int risk2Move = this.riskBoard[o2.toPosition.getRowNumberToFieldIndex()-1][o2.toPosition.getColumnNameToFieldIndex()];
        return risk1Move - risk2Move;
    }

    @Override
    public Move chooseMoveToPerform(GameState board, List<Move> availableMoves) {
        Collections.shuffle(availableMoves);
        Move move = availableMoves.stream().max(new Comparator<Move>() {
            @Override
            public int compare(Move o1, Move o2) {
                return compareMoves(o1, o2);
            }
        }).get();
        return move;
    }

    @Override
    public IPlayer copy() {
        return null;
    }
}
