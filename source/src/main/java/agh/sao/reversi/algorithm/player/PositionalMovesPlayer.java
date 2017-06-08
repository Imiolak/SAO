package agh.sao.reversi.algorithm.player;

import agh.sao.reversi.engine.game.GameState;
import agh.sao.reversi.engine.game.Move;
import agh.sao.reversi.engine.game.PieceColor;
import agh.sao.reversi.engine.game.Position;
import agh.sao.reversi.engine.player.IPlayer;

import java.util.List;
import java.util.Comparator;
import java.util.Collections;
import java.util.Random;

public class PositionalMovesPlayer implements IPlayer {

    private PieceColor playerColor;
    private int[][] riskBoard;
    private final int[] riskBoardValues;

    public PositionalMovesPlayer(int[] params) {
        this(params[0], params[1], params[2], params[3], params[4]);
    }

    public PositionalMovesPlayer(int a, int b, int c, int d, int e) {
        this.riskBoardValues = new int[]{a, b, c, d, e};
        this.makeRiskBoard();
    }

    @Override
    public PieceColor getPlayerColor() {
        return this.playerColor;
    }

    @Override
    public void setPlayerColor(PieceColor playerColor) {
        this.playerColor = playerColor;
    }

    @Override
    public IPlayer copy() {
        return new PositionalMovesPlayer( this.riskBoardValues[0]
                                        , this.riskBoardValues[1]
                                        , this.riskBoardValues[2]
                                        , this.riskBoardValues[3]
                                        , this.riskBoardValues[4]);
    }

    public int[] getParameters() { return this.riskBoardValues; }

    public void setParameters(int a, int b, int c, int d, int e) {
        this.riskBoardValues[0] = a;
        this.riskBoardValues[1] = b;
        this.riskBoardValues[2] = c;
        this.riskBoardValues[3] = d;
        this.riskBoardValues[4] = e;
        this.makeRiskBoard();
    }

    private void makeRiskBoard() {
        int a, b, c, e, d;
        a = this.riskBoardValues[0];
        b = this.riskBoardValues[1];
        c = this.riskBoardValues[2];
        d = this.riskBoardValues[3];
        e = this.riskBoardValues[4];
        this.riskBoard = new int[][] { 	{ e, d, c, c, c, c, d, e },
                                        { d, d, b, b, b, b, d, d },
                                        { c, b, a, a, a, a, b, c },
                                        { c, b, a, a, a, a, b, c },
                                        { c, b, a, a, a, a, b, c },
                                        { c, b, a, a, a, a, b, c },
                                        { d, d, b, b, b, b, d, d },
                                        { e, d, c, c, c, c, d, e }};
    }

    private int compareMoves(Move o1, Move o2) {
        int risk1Move = this.riskBoard[o1.toPosition.getRowNumberToFieldIndex()][o1.toPosition.getColumnNameToFieldIndex()];
        int risk2Move = this.riskBoard[o2.toPosition.getRowNumberToFieldIndex()][o2.toPosition.getColumnNameToFieldIndex()];
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

    public static int[] getRandomParameterValues() {
        Random rng = new Random();
        return new int[] {
                rng.nextInt(30) + 30,
                rng.nextInt(20) + 15,
                rng.nextInt(30) + 50,
                rng.nextInt(15),
                rng.nextInt(20) + 80
        };
    }

    @Override
    public String toString() {
        return "positional" + riskBoardValues[0] + "," + riskBoardValues[1] + "," + riskBoardValues[2] + ","
                + riskBoardValues[3] + "," + riskBoardValues[4];
    }
}
