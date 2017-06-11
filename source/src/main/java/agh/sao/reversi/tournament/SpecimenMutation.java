package agh.sao.reversi.tournament;

import agh.sao.reversi.algorithm.player.*;
import agh.sao.reversi.engine.player.IPlayer;

import java.util.Random;

/**
 * Created by Imiolak on 12-Jun-17.
 */
public class SpecimenMutation implements ISpecimenMutation {
    @Override
    public void decideAndPerformMutation(StrategySpecimen specimen) {
        Random rng = new Random();

        if (rng.nextDouble() < specimen.getWinningPercentage())
            return;

        if (disturbEarlyGameStrategy(rng)) {
            if (replaceEarlyGameStrategy(rng)) {
                IPlayer newPlayer1 = createRandomPlayer(rng);
                specimen.getPlayer().setPlayer1(newPlayer1);
            } else {
                specimen.getPlayer().getPlayer1().setRandomParameters();
            }
        }

        if (disturbStrategyChangePoint(rng)) {
            if (shouldUseOneStrategy(rng)) {
                specimen.getPlayer().setStrategySwitchPoint(0);
                specimen.getPlayer().setPlayer2(null);
            } else {
                specimen.getPlayer().setStrategySwitchPoint(rng.nextInt(20) + 5);
            }
        }

        if (specimen.getPlayer().getStrategySwitchPoint() == 0) {
            specimen.getPlayer().setPlayer2(null);
        } else {
            if (specimen.getPlayer().getPlayer2() == null
                    || rng.nextDouble() < 0.5) {
                IPlayer newPlayer2 = createRandomPlayer(rng);
                specimen.getPlayer().setPlayer2(newPlayer2);
            } else {
                specimen.getPlayer().getPlayer2().setRandomParameters();
            }
        }

        specimen.resetStats();
    }

    private IPlayer createRandomPlayer(Random rng) {
        switch (rng.nextInt(5)) {
            case 0:
                return new GreedyMovesPlayer();
            case 1:
                return new CornersMovesPlayer();
            case 2:
                return new MobilityMovesPlayer(MobilityMovesPlayer.getRandomParameterValue());
            case 3:
                return new PositionalMovesPlayer(PositionalMovesPlayer.getRandomParameterValues());
            default:
                return new StableMovesPlayer(StableMovesPlayer.getRandomParameterValue());
        }
    }

    private boolean shouldUseOneStrategy(Random rng) {
        return rng.nextDouble() < 0.4;
    }

    private boolean disturbStrategyChangePoint(Random rng) {
        return rng.nextDouble() < 0.5;
    }

    private boolean replaceEarlyGameStrategy(Random rng) {
        return rng.nextDouble() < 0.5;
    }

    private boolean disturbEarlyGameStrategy(Random rng) {
        return rng.nextDouble() < 0.5;
    }
}
