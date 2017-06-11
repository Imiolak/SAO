package agh.sao.reversi.algorithm.player;

import agh.sao.reversi.engine.game.AvailableMovesResolver;
import agh.sao.reversi.engine.game.GameState;
import agh.sao.reversi.engine.game.Move;
import agh.sao.reversi.engine.game.PieceColor;
import agh.sao.reversi.engine.player.IPlayer;

import java.util.*;
import java.lang.Math;

public class MobilityMovesPlayer implements IPlayer{
    private PieceColor playerColor;
    private double parameter;
    private final AvailableMovesResolver availableMovesResolver;

    public MobilityMovesPlayer(double parameter){
        this.parameter = parameter;
        this.availableMovesResolver = new AvailableMovesResolver();
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
        return new MobilityMovesPlayer(this.parameter);
    }

    @Override
    public void setRandomParameters() {
        parameter = getRandomParameterValue();
    }

    public double getParameter() { return this.parameter; }

    public void setParameter(double parameter) { this.parameter = parameter; }

    private int compareMoves(GameState board, Move o1, Move o2) {
        int min1 = 99;
        int min2 = 99;
        GameState tmpBoard1 = new GameState(board.toStringBoard());
        GameState tmpBoard2 = new GameState(board.toStringBoard());
        tmpBoard1.applyMove(o1);
        tmpBoard2.applyMove(o2);
        List<Move> newMoves1 = this.availableMovesResolver.getAvailableMovesForPlayer(
                this.playerColor == PieceColor.Light ? PieceColor.Dark : PieceColor.Light,
                tmpBoard1);
        List<Move> newMoves2 = this.availableMovesResolver.getAvailableMovesForPlayer(
                this.playerColor == PieceColor.Light ? PieceColor.Dark : PieceColor.Light,
                tmpBoard2);
        for(Move o : newMoves1) {
            GameState subBoard = new GameState(tmpBoard1.toStringBoard());
            subBoard.applyMove(o);
            List<Move> subMoves = this.availableMovesResolver.getAvailableMovesForPlayer(
                    this.playerColor,
                    tmpBoard1);
            min1 = min1 > subMoves.size() ? subMoves.size() :  min1;
        }
        for(Move o : newMoves2) {
            GameState subBoard = new GameState(tmpBoard2.toStringBoard());
            subBoard.applyMove(o);
            List<Move> subMoves = this.availableMovesResolver.getAvailableMovesForPlayer(
                    this.playerColor,
                    tmpBoard1);
            min1 = min1 > subMoves.size() ? subMoves.size() :  min1;
        }
        return min1 - min2;
    }

    private int compareMovesOpponent(GameState board, Move o1, Move o2) {
        GameState tmpBoard1 = new GameState(board.toStringBoard());
        GameState tmpBoard2 = new GameState(board.toStringBoard());
        tmpBoard1.applyMove(o1);
        tmpBoard2.applyMove(o2);
        List<Move> newMoves1 = this.availableMovesResolver.getAvailableMovesForPlayer(
                this.playerColor == PieceColor.Light ? PieceColor.Dark : PieceColor.Light,
                tmpBoard1);
        List<Move> newMoves2 = this.availableMovesResolver.getAvailableMovesForPlayer(
                this.playerColor == PieceColor.Light ? PieceColor.Dark : PieceColor.Light,
                tmpBoard2);
        return newMoves1.size() - newMoves2.size();
    }

    @Override
    public Move chooseMoveToPerform(GameState board, List<Move> availableMoves) {
        Collections.shuffle(availableMoves);
        if(Math.random() <= this.parameter) {
            Move move = availableMoves.stream().max(new Comparator<Move>() {
                @Override
                public int compare(Move o1, Move o2) {
                    return compareMoves(board, o1, o2);
                }
            }).get();
            return move;
        }
        else {
            Move move = availableMoves.stream().min(new Comparator<Move>() {
                @Override
                public int compare(Move o1, Move o2) {
                    return compareMovesOpponent(board, o1, o2);
                }
            }).get();
            return move;
        }
    }

    public static double getRandomParameterValue() {
        Random rng = new Random();
        return rng.nextDouble();
    }

    @Override
    public String toString() {
        return "mobility" + String.format("%.2f", parameter);
    }
}
