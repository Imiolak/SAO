package agh.sao.reversi.tournament;

import agh.sao.reversi.algorithm.player.CombinedStrategiesPlayer;

import java.util.Random;

public class SpecimenCrossover implements ISpecimenCrossover {

    @Override
    public StrategySpecimen doCrossover(StrategySpecimen specimen1, StrategySpecimen specimen2) {
        Random rnd = new Random();

        int strategySwitchPoint = rnd.nextDouble() < 0.5
                ? specimen1.getPlayer().getStrategySwitchPoint()
                : specimen2.getPlayer().getStrategySwitchPoint();

        if (rnd.nextDouble() < 0.5) {
            return new StrategySpecimen(new CombinedStrategiesPlayer(
                    specimen1.getPlayer().getPlayer1(),
                    strategySwitchPoint,
                    specimen2.getPlayer().getPlayer2()
            ));
        } else {
            return new StrategySpecimen(new CombinedStrategiesPlayer(
                    specimen2.getPlayer().getPlayer1(),
                    strategySwitchPoint,
                    specimen1.getPlayer().getPlayer2()
            ));
        }
    }
}
