package agh.sao.reversi.tournament;

import agh.sao.reversi.algorithm.player.CombinedStrategiesPlayer;
import agh.sao.reversi.engine.player.IPlayer;

public class StrategySpecimen {

    private final CombinedStrategiesPlayer player;

    private int gamesPlayed = 0;
    private int gamesWon = 0;
    private int gamesLost = 0;

    public StrategySpecimen(CombinedStrategiesPlayer player) {

        this.player = player;
    }

    public CombinedStrategiesPlayer getPlayer() {
        return player;
    }

    public double getWinningPercentage() {
        return (double) gamesWon / gamesPlayed;
    }

    public void registerWin() {
        gamesPlayed++;
        gamesWon++;
    }

    public void registerDraw() {
        gamesPlayed++;
    }

    public void registerLoss() {
        gamesPlayed++;
        gamesLost++;
    }

    public String getResultsString() {
        return player.toString() + "\t" + getWinningPercentage() + "\t" +
                gamesWon + "\t" + gamesLost + "\t" + gamesPlayed;

    }
}
