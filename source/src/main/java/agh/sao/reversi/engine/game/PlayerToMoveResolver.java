package agh.sao.reversi.engine.game;

import agh.sao.reversi.engine.player.IPlayer;

/**
 * Created by Imiolak on 14-Mar-17.
 */
public class PlayerToMoveResolver {
    private final IPlayer player1;
    private final IPlayer player2;

    private boolean playerSwitch;

    public PlayerToMoveResolver(IPlayer player1, IPlayer player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.playerSwitch = false;
    }

    public IPlayer getNextPlayer() {
        this.playerSwitch = !this.playerSwitch;

        return this.playerSwitch
                ? this.player1
                : this.player2;
    }
}
