package agh.sao.reversi.engine.game;

import agh.sao.reversi.engine.player.IPlayer;

import java.util.List;

/**
 * Created by Imiolak on 14-Mar-17.
 */
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
        initializeGame();
        gameLoop();

        return new GameResults();
    }

    private void initializeGame() {
        this.gameState = new GameState();
        this.playerToMoveResolver = new PlayerToMoveResolver(player1, player2);
        this.availableMovesResolver = new AvailableMovesResolver();
    }

    private void gameLoop() {
        int turnCounter = 0;
        IPlayer playerToMove = this.playerToMoveResolver.getNextPlayer();
        List<Move> availableMoves = this.availableMovesResolver.getAvailableMovesForPlayer(
                playerToMove.getPlayerColor(),
                this.gameState
        );

        do {
            Move move = playerToMove.chooseMoveToPerform(this.gameState, availableMoves);
            this.gameState.applyMove(move);

            turnCounter++;
            playerToMove = this.playerToMoveResolver.getNextPlayer();
            availableMoves = this.availableMovesResolver.getAvailableMovesForPlayer(
                    playerToMove.getPlayerColor(),
                    this.gameState
            );
        } while (!gameEndConditionMet(turnCounter, availableMoves));
    }

    //TODO not correct game is not finished in player does not have move, player just skips the move
    //TODO game is finished only when both players dont have a move
    private boolean gameEndConditionMet(int turnCounter, List<Move> availableMoves) {
        return turnCounter >= 60
                || availableMoves.isEmpty();
    }

}
