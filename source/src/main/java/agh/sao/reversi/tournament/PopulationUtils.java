package agh.sao.reversi.tournament;

import java.util.*;

public class PopulationUtils {

    public static StrategySpecimen[] getRandomSpecimens(List<StrategySpecimen> population, int numberOfSpecimen) {
        Random rng = new Random();
        Set<Integer> takenSpecimen = new HashSet<>();
        StrategySpecimen[] randomSpecimen = new StrategySpecimen[numberOfSpecimen];

        for (int i = 0; i < numberOfSpecimen; i++) {
            int specimenToTake;
            do {
                specimenToTake = rng.nextInt(population.size());
            } while (takenSpecimen.contains(specimenToTake));

            takenSpecimen.add(specimenToTake);
            randomSpecimen[i] = population.get(specimenToTake);
        }

        return randomSpecimen;
    }
}
