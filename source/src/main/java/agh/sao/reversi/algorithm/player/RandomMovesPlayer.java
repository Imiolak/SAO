package agh.sao.reversi.algorithm.player;

import agh.sao.reversi.engine.game.GameState;
import agh.sao.reversi.engine.game.Move;
import agh.sao.reversi.engine.game.PieceColor;
import agh.sao.reversi.engine.player.IPlayer;

import java.util.List;

/**
 * Created by Imiolak on 15-Mar-17.
 */
public class RandomMovesPlayer implements IPlayer {
    @Override
    public PieceColor getPlayerColor() {
        return null;
    }

    @Override
    public Move chooseMoveToPerform(GameState board, List<Move> availableMoves) {
        return null;
    }
}
