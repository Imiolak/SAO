package agh.sao.reversi.algorithm.player;

import agh.sao.reversi.engine.game.GameState;
import agh.sao.reversi.engine.game.Move;
import agh.sao.reversi.engine.game.PieceColor;
import agh.sao.reversi.engine.player.IPlayer;

import java.util.List;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Collections;
import java.lang.Math;

public class CornersMovesPlayer implements IPlayer{
    private PieceColor playerColor;
    private final int[][] corners;

    public CornersMovesPlayer(){
        corners = new int[][]{{0,0}, {0,8}, {8,0}, {8,8}};
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
        return new CornersMovesPlayer();
    }

    @Override
    public void setRandomParameters() {

    }

    private int getDistBeetween(int[] pos1, int[] pos2) {
        return (int) Math.sqrt(Math.pow((pos1[0]-pos2[0]), 2) + Math.pow((pos1[1]-pos2[1]), 2));
    }

    private int compareMoves(Move o1, Move o2) {
        int[] move1Cord = new int[]{o1.toPosition.getRowNumberToFieldIndex()-1, o1.toPosition.getColumnNameToFieldIndex()};
        int[] move2Cord = new int[]{o2.toPosition.getRowNumberToFieldIndex()-1, o2.toPosition.getColumnNameToFieldIndex()};
        Integer[] move1Dist = new Integer[]{8, 8, 8, 8};
        Integer[] move2Dist = new Integer[]{8, 8, 8, 8};
        for(int i=0; i<4; i++) {
            move1Dist[i] = getDistBeetween(move1Cord, this.corners[i]);
            move2Dist[i] = getDistBeetween(move2Cord, this.corners[i]);
        }
        int move1Distance = (int) Collections.min(Arrays.asList(move1Dist));
        int move2Distance = (int) Collections.min(Arrays.asList(move2Dist));
        return move1Distance - move2Distance;
    }

    @Override
    public Move chooseMoveToPerform(GameState board, List<Move> availableMoves) {
        Collections.shuffle(availableMoves);
        Move move = availableMoves.stream().min(new Comparator<Move>() {
            @Override
            public int compare(Move o1, Move o2) {
                return compareMoves(o1, o2);
            }
        }).get();
        return move;
    }

    @Override
    public String toString() {
        return "corners";
    }
}
