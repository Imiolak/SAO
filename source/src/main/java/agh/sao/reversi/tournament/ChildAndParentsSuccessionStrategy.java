package agh.sao.reversi.tournament;

import java.util.List;

/**
 * Created by Imiolak on 06-Jun-17.
 */
public class ChildAndParentsSuccessionStrategy implements ISpecimenSuccessionStrategy {
    @Override
    public int getNumberOfSpecimensAdvancingInEachStep() {
        return 3;
    }

    @Override
    public void decideSuccessors(List<StrategySpecimen> population, StrategySpecimen[] parentSpecimens, StrategySpecimen childSpecimen) {
        population.add(parentSpecimens[0]);
        population.add(parentSpecimens[1]);
        population.add(childSpecimen);
    }
}
