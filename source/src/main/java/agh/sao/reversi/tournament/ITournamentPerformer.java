package agh.sao.reversi.tournament;

/**
 * Created by Imiolak on 06-Jun-17.
 */
public interface ITournamentPerformer {

    void setCompetingStrategies(StrategySpecimen[] tourmanetCompetitors);
    void performTournament();
    StrategySpecimen[] getTopPerformers(int numberOfTopPerformers);
}
