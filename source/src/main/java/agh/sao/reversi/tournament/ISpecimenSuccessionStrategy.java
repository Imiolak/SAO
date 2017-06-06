package agh.sao.reversi.tournament;

import java.util.List;

/**
 * Created by Imiolak on 06-Jun-17.
 */
public interface ISpecimenSuccessionStrategy {

    int getNumberOfSpecimensAdvancingInEachStep();
    void decideSuccessors(List<StrategySpecimen> population, StrategySpecimen[] parentSpecimens, StrategySpecimen childSpecimen);
}
