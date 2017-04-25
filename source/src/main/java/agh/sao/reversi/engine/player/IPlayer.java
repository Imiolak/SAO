package agh.sao.reversi.engine.player;

import agh.sao.reversi.engine.game.GameState;
import agh.sao.reversi.engine.game.Move;
import agh.sao.reversi.engine.game.PieceColor;

import java.util.List;

/**
 * Created by Imiolak on 14-Mar-17.
 */
public interface IPlayer {

    PieceColor getPlayerColor();

    Move chooseMoveToPerform(GameState board, List<Move> availableMoves);
}
