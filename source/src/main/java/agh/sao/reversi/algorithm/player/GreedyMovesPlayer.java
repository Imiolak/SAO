package agh.sao.reversi.algorithm.player;

import agh.sao.reversi.engine.game.GameState;
import agh.sao.reversi.engine.game.Move;
import agh.sao.reversi.engine.game.PieceColor;
import agh.sao.reversi.engine.player.IPlayer;

import java.util.List;
import java.util.Comparator;
import java.util.Collections;

public class GreedyMovesPlayer implements IPlayer{
    private PieceColor playerColor;

    public GreedyMovesPlayer(){}

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
        return new GreedyMovesPlayer();
    }

    private int compareMoves(GameState board, Move o1, Move o2) {
        GameState move1Board = new GameState(board.toStringBoard());
        GameState move2Board = new GameState(board.toStringBoard());
        move1Board.applyMove(o1);
        move2Board.applyMove(o2);
        int move1Points = this.playerColor == PieceColor.Dark ? move1Board.countDark() : move1Board.countLight();
        int move2Points = this.playerColor == PieceColor.Dark ? move2Board.countDark() : move2Board.countLight();
        return move1Points - move2Points;
    }

    @Override
    public Move chooseMoveToPerform(GameState board, List<Move> availableMoves) {
        Collections.shuffle(availableMoves);
        Move move = availableMoves.stream().max(new Comparator<Move>() {
            @Override
            public int compare(Move o1, Move o2) {
                return compareMoves(board, o1, o2);
            }
        }).get();
        return move;
    }
}
