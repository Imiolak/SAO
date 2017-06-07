package agh.sao.reversi;

import agh.sao.reversi.tournament.*;

public class Main {

    public static void main(String... args) {

        ISpecimenCrossover crossover = new SpecimenCrossover();
        ITournamentPerformer tournament = new RoundRobinTournamentPerformer();

        Experiment[] experiments = {
                new Experiment(10, crossover, new OnlyChildSuccessionStratey(), tournament)
                //new Experiment(50, crossover, new ChildAndParentsSuccessionStrategy(), tournament),
        };
        ExperimentPlatform platform = new ExperimentPlatform(
                new StrategySetCreator(),
                experiments
        );

        platform.performExperiments();
    }
}
