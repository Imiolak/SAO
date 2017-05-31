package agh.sao.reversi.tournament;

import agh.sao.reversi.algorithm.player.CombinedStrategiesPlayer;
import agh.sao.reversi.engine.player.IPlayer;

import java.rmi.server.ExportException;
import java.util.List;

/**
 * Created by Imiolak on 31-May-17.
 */
public class ExperimentPlatform {

    private final StrategySetCreator strategySetCreator;
    private final Experiment[] experiments;

    public ExperimentPlatform(StrategySetCreator strategySetCreator, Experiment[] experiments){

        this.strategySetCreator = strategySetCreator;
        this.experiments = experiments;
    }

    public void performExperiments() {
        List<CombinedStrategiesPlayer> strategySet = strategySetCreator.createInitialStrategyBase();

        for (Experiment experiment : this.experiments) {
            List<StrategySpecimen> specimenPopulation = createSpecimensFromCopiesOfStrategies(strategySet);
            experiment.injectInitialPopulation(specimenPopulation);

            experiment.perform();
            experiment.writeResults();
        }
    }

    private List<StrategySpecimen> createSpecimensFromCopiesOfStrategies(List<CombinedStrategiesPlayer> strategySet) {
        return null;
    }
}
