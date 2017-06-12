package agh.sao.reversi;

import agh.sao.reversi.tournament.*;

public class Main {

    public static void main(String... args) {

        ISpecimenCrossover crossover = new SpecimenCrossover();
        ISpecimenMutation mutation = new SpecimenMutation();
        ITournamentPerformer tournament = new RoundRobinTournamentPerformer();

        Experiment[] experiments = {
                new Experiment(30, 5, crossover, mutation, new OnlyChildSuccessionStratey(), tournament),
                new Experiment(30, 5, crossover, mutation, new ChildAndParentsSuccessionStrategy(), tournament),
        };
        ExperimentPlatform platform = new ExperimentPlatform(
                new StrategySetCreator(),
                experiments
        );

        platform.performExperiments(true);
    }
}
