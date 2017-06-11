package agh.sao.reversi.algorithm.player;

import agh.sao.reversi.engine.game.GameState;
import agh.sao.reversi.engine.game.Move;
import agh.sao.reversi.engine.game.PieceColor;
import agh.sao.reversi.engine.player.IPlayer;

import java.util.List;

public class CombinedStrategiesPlayer implements IPlayer {

    private IPlayer player1;
    private int strategySwitchPoint;
    private IPlayer player2;

    private int performedMoves = 0;
    private PieceColor playerColor;

    public CombinedStrategiesPlayer(IPlayer player1) {
        this(player1, 0, null);
    }

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

    public void setPlayer1(IPlayer player1) {
        this.player1 = player1;
    }

    public void setStrategySwitchPoint(int strategySwitchPoint) {
        this.strategySwitchPoint = strategySwitchPoint;
    }

    public void setPlayer2(IPlayer player2) {
        this.player2 = player2;
    }

    @Override
    public void setPlayerColor(PieceColor playerColor) {
        player1.setPlayerColor(playerColor);
        if (player2 != null) {
            player2.setPlayerColor(playerColor);
        }
        this.playerColor = playerColor;
    }

    @Override
    public PieceColor getPlayerColor() {
        return playerColor;
    }

    @Override
    public Move chooseMoveToPerform(GameState board, List<Move> availableMoves) {
        Move move;
        if (strategySwitchPoint == 0) {
            move = player1.chooseMoveToPerform(board, availableMoves);
        } else if (performedMoves < strategySwitchPoint) {
            move = player1.chooseMoveToPerform(board, availableMoves);
        } else {
            //TODO null pointer here
            if (player2 != null) {
                move = player2.chooseMoveToPerform(board, availableMoves);
            } else {
                move = player1.chooseMoveToPerform(board, availableMoves);
            }
        }
        performedMoves++;

        return move;
    }

    @Override
    public IPlayer copy() {
        if (player2 != null) {
            return new CombinedStrategiesPlayer(player1.copy(), strategySwitchPoint, player2.copy());
        }
        else {
            return new CombinedStrategiesPlayer(player1.copy());
        }
    }

    @Override
    public void setRandomParameters() {
        player1.setRandomParameters();
        player2.setRandomParameters();
    }

    @Override
    public String toString() {
        if (player2 == null) {
            return player1.toString();
        }
        return player1.toString() + "-" + strategySwitchPoint + "-" + player2.toString();
    }


}
