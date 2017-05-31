package agh.sao.reversi.tournament;

import agh.sao.reversi.algorithm.player.CombinedStrategiesPlayer;
import agh.sao.reversi.engine.player.IPlayer;

/**
 * Created by Imiolak on 26-Apr-17.
 */
public class StrategySpecimen {

    private final CombinedStrategiesPlayer player;

    public StrategySpecimen(CombinedStrategiesPlayer player) {

        this.player = player;
    }

    public CombinedStrategiesPlayer getPlayer() {
        return player;
    }
}
