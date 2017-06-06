package agh.sao.reversi.algorithm.player;

import agh.sao.reversi.engine.game.GameState;
import agh.sao.reversi.engine.game.Move;
import agh.sao.reversi.engine.game.PieceColor;
import agh.sao.reversi.engine.player.IPlayer;

import java.util.List;

public class CombinedStrategiesPlayer implements IPlayer {

    private final IPlayer player1;
    private final int strategySwitchPoint;
    private final IPlayer player2;

    private int performedMoves = 0;
    private PieceColor playerColor;

    public CombinedStrategiesPlayer(IPlayer player1, int strategySwitchPoint, IPlayer player2) {
        this.player1 = player1;
        this.strategySwitchPoint = strategySwitchPoint;
        this.player2 = player2;
    }

    public IPlayer getPlayer1() {
        return player1;
    }

    public int getStrategySwitchPoint() {
        return strategySwitchPoint;
    }

    public IPlayer getPlayer2() {
        return player2;
    }

    @Override
    public void setPlayerColor(PieceColor playerColor) {
        this.playerColor = playerColor;
    }

    @Override
    public PieceColor getPlayerColor() {
        return playerColor;
    }

    @Override
    public Move chooseMoveToPerform(GameState board, List<Move> availableMoves) {
        if (strategySwitchPoint == 0 && performedMoves < strategySwitchPoint) {
            return player1.chooseMoveToPerform(board, availableMoves);
        } else {
            return player2.chooseMoveToPerform(board, availableMoves);
        }
    }

    @Override
    public IPlayer copy() {
        if (player2 != null) {
            return new CombinedStrategiesPlayer(player1.copy(), strategySwitchPoint, player2.copy());
        }
        else {
            return new CombinedStrategiesPlayer(player1.copy(), strategySwitchPoint, null);
        }
    }
}
