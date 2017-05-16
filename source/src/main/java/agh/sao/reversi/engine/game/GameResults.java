package agh.sao.reversi.engine.game;

import agh.sao.reversi.engine.player.IPlayer;

/**
 * Created by Imiolak on 14-Mar-17.
 */
public class GameResults {

    IPlayer winner;

    GameResults(IPlayer winner) {
        this.winner = winner;
    }
}
