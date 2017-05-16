package agh.sao.reversi;

import agh.sao.reversi.algorithm.player.RealPersonPlayer;
import agh.sao.reversi.engine.game.GameRunner;
import agh.sao.reversi.engine.game.PieceColor;
import agh.sao.reversi.engine.player.IPlayer;

public class Main {

    public static void main(String... args) {
        IPlayer firstPl = new RealPersonPlayer(PieceColor.Light);
        IPlayer secondPl = new RealPersonPlayer(PieceColor.Dark);
        GameRunner gameRunner = new GameRunner(secondPl, firstPl);
        System.out.println("Game ended result - " + gameRunner.runGame());
    }

}
