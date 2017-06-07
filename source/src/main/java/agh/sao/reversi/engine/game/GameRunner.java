package agh.sao.reversi.engine.game;

import agh.sao.reversi.engine.player.IPlayer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static agh.sao.reversi.engine.game.PieceColor.Light;

public class GameRunner {
    private final IPlayer player1;
    private final IPlayer player2;

    private GameState gameState;
    private PlayerToMoveResolver playerToMoveResolver;
    private AvailableMovesResolver availableMovesResolver;

    public GameRunner(IPlayer player1, IPlayer player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public GameResults runGame() {
        System.out.println("game " + player1 + " " + player2);

        initializeGame();
        gameLoop();
        return new GameResults(decideWinner());
    }

    private IPlayer decideWinner() {
        int dark = gameState.countDark();
        int light = gameState.countLight();
        if (light > dark)
            return player1.getPlayerColor() == Light ? player1 : player2;
        else if (light < dark)
            return player1.getPlayerColor() == Light ? player2 : player1;
        else
            return null;
    }

    private void initializeGame() {
        this.gameState = new GameState();
        this.playerToMoveResolver = new PlayerToMoveResolver(player1, player2);
        this.availableMovesResolver = new AvailableMovesResolver();
    }

    private void gameLoop() {
        Map<PieceColor, Boolean> lastSkipped = new HashMap<>();
        lastSkipped.put(player1.getPlayerColor(), false);
        lastSkipped.put(player2.getPlayerColor(), false);

        while (gameEndConditionMet(lastSkipped)) {
            IPlayer playerToMove = this.playerToMoveResolver.getNextPlayer();
            List<Move> availableMoves = this.availableMovesResolver.getAvailableMovesForPlayer(
                    playerToMove.getPlayerColor(),
                    this.gameState
            );
            if (availableMoves.isEmpty()) {
                lastSkipped.put(playerToMove.getPlayerColor(), true);
            } else {
                lastSkipped.put(playerToMove.getPlayerColor(), false);
                Move move = playerToMove.chooseMoveToPerform(this.gameState, availableMoves);
                this.gameState.applyMove(move);
            }
        }
    }

    private boolean gameEndConditionMet(Map<PieceColor, Boolean> lastSkipped) {
        return !(lastSkipped.get(player1.getPlayerColor()) && lastSkipped.get(player2.getPlayerColor()));
    }
}
