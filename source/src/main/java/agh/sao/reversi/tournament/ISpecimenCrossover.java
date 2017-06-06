package agh.sao.reversi.tournament;

/**
 * Created by Imiolak on 06-Jun-17.
 */
public interface ISpecimenCrossover {

    StrategySpecimen doCrossover(StrategySpecimen specimen1, StrategySpecimen specimen2);
}
