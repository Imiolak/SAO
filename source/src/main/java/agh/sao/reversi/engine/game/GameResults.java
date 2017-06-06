package agh.sao.reversi.engine.game;

import agh.sao.reversi.engine.player.IPlayer;

/**
 * Created by Imiolak on 14-Mar-17.
 */
public class GameResults {

    private IPlayer winner;

    public GameResults(IPlayer winner) {
        this.winner = winner;
    }

    public IPlayer getWinner() {
        return winner;
    }
}
