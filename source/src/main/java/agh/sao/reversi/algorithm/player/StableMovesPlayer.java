package agh.sao.reversi.algorithm.player;

import agh.sao.reversi.engine.game.GameState;
import agh.sao.reversi.engine.game.Move;
import agh.sao.reversi.engine.game.PieceColor;
import agh.sao.reversi.engine.player.IPlayer;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.Math;
import java.util.Random;

public class StableMovesPlayer implements IPlayer{
    private PieceColor playerColor;
    private double parameter;
    private final IPlayer tmpPl;

    public StableMovesPlayer(double parameter){
        this.parameter = parameter;
        tmpPl = new GreedyMovesPlayer();
    }

    @Override
    public PieceColor getPlayerColor() {
        return this.playerColor;
    }

    @Override
    public void setPlayerColor(PieceColor playerColor) {
        this.playerColor = playerColor;
        tmpPl.setPlayerColor(this.playerColor);
    }

    @Override
    public IPlayer copy() {
        return new StableMovesPlayer(this.parameter);
    }

    @Override
    public void setRandomParameters() {
        parameter = getRandomParameterValue();
    }

    public double getParameter() { return this.parameter; }

    public void setParameter(double parameter) { this.parameter = parameter; }

    private boolean outOfBoard(int X, int Y) {
        if (X < 0 || X>7 || Y < 0 || Y>7)
            return true;
        return false;
    }

    private List<int[]> getAllDirectionsCords(Move o) {
        int cordX = o.toPosition.getRowNumberToFieldIndex()-1;
        int cordY = o.toPosition.getColumnNameToFieldIndex();
        int tmpX, tmpY;
        int [][] diffs = new int[][]{{1,1},{1,-1},{-1,1},{-1,-1}};
        List<int[]> cords = new ArrayList<int[]>();
        for(int i=0; i<8; i++)
            if(i != cordX)
                cords.add(new int[]{i,cordY});
        for(int i=0; i<8; i++)
            if(i != cordY)
                cords.add(new int[]{cordX,i});
        for(int[] diff : diffs) {
            tmpX = cordX;
            tmpY = cordY;
            while (true) {
                tmpX += diff[0];
                tmpY += diff[1];
                if(outOfBoard(tmpX, tmpY)) break;
                cords.add(new int[]{tmpX, tmpY});
            }
        }
        return cords;
    }

    private boolean isStable(GameState board, Move o) {
        List<int[]> cords = getAllDirectionsCords(o);
        if(cords.size() < 1) return false;
        for(int[] cord : cords) {
            if(board.valueAt(cord[0], cord[1]) == null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Move chooseMoveToPerform(GameState board, List<Move> availableMoves) {
        Collections.shuffle(availableMoves);
        if(Math.random() <= this.parameter) {
            for(Move o : availableMoves) {
                if (isStable(board, o)){
                    return o;
                }
            }
        }
        else {
            return tmpPl.chooseMoveToPerform(board, availableMoves);
        }
        return availableMoves.get(new Random().nextInt(availableMoves.size()));
    }

    public static double getRandomParameterValue() {
        Random rng = new Random();
        return rng.nextDouble();
    }

    @Override
    public String toString() {
        return "stable" + String.format("%.2f", parameter);
    }
}
