package agh.sao.reversi.tournament;

import java.util.List;

/**
 * Created by Imiolak on 06-Jun-17.
 */
public class OnlyChildSuccessionStratey implements ISpecimenSuccessionStrategy {

    @Override
    public int getNumberOfSpecimensAdvancingInEachStep() {
        return 1;
    }

    @Override
    public void decideSuccessors(List<StrategySpecimen> population, StrategySpecimen[] parentSpecimens, StrategySpecimen childSpecimen) {
        population.add(childSpecimen);
    }
}
