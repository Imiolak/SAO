package agh.sao.reversi.algorithm.player;

import agh.sao.reversi.engine.game.GameState;
import agh.sao.reversi.engine.game.Move;
import agh.sao.reversi.engine.game.PieceColor;
import agh.sao.reversi.engine.player.IPlayer;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class RandomMovesPlayer implements IPlayer {

    private PieceColor playerColor;

    @Override
    public void setPlayerColor(PieceColor playerColor) {
        this.playerColor = playerColor;
    }

    @Override
    public PieceColor getPlayerColor() {
        return this.playerColor;
    }

    @Override
    public Move chooseMoveToPerform(GameState board, List<Move> availableMoves) {
        return availableMoves.get(new Random().nextInt(availableMoves.size()));
    }

    @Override
    public IPlayer copy() {
        return new RandomMovesPlayer();
    }

    @Override
    public void setRandomParameters() {

    }

    @Override
    public String toString() {
        return "random";
    }
}
