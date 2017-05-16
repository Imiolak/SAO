package agh.sao.reversi.algorithm.player;

import agh.sao.reversi.engine.game.GameState;
import agh.sao.reversi.engine.game.Move;
import agh.sao.reversi.engine.game.PieceColor;
import agh.sao.reversi.engine.game.Position;
import agh.sao.reversi.engine.player.IPlayer;

import java.util.List;
import java.util.Scanner;


public class RealPersonPlayer implements IPlayer {

    private final PieceColor playerColor;

    public RealPersonPlayer(PieceColor playerColor) {
        this.playerColor = playerColor;
    }

    @Override
    public PieceColor getPlayerColor() {
        return playerColor;
    }

    @Override
    public Move chooseMoveToPerform(GameState board, List<Move> availableMoves) {
        System.out.println(board);
        availableMoves.forEach(System.out::println);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Select move:");
        String position = scanner.next();
        return new Move(playerColor, new Position(position));
    }
}
