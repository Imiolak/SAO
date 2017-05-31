package agh.sao.reversi.tournament;

import java.util.List;

/**
 * Created by Imiolak on 31-May-17.
 */
public class Experiment {

    private final int iterations;
    private final StrategySpecimenCrossover specimenCrossover;

    private List<StrategySpecimen> initialPopulation;

    public Experiment(int iterations, StrategySpecimenCrossover specimenCrossover) {

        this.iterations = iterations;
        this.specimenCrossover = specimenCrossover;
    }

    public void injectInitialPopulation(List<StrategySpecimen> initialPopulation) {

        this.initialPopulation = initialPopulation;
    }

    public void perform() {
        for (int i = 0; i < iterations; i++) {
            //TODO experiment body
        }
    }

    public void writeResults() {
        //TODO write results
    }
}
