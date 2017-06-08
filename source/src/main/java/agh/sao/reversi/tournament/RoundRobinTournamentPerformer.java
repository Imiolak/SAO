package agh.sao.reversi.tournament;

import agh.sao.reversi.engine.game.GameResults;
import agh.sao.reversi.engine.game.GameRunner;
import agh.sao.reversi.engine.game.PieceColor;
import agh.sao.reversi.engine.player.IPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class RoundRobinTournamentPerformer implements ITournamentPerformer {

    private Map<StrategySpecimen, Integer> winsBySpecimen = new HashMap<>();

    @Override
    public void setCompetingStrategies(StrategySpecimen[] tournamentCompetitors) {
        winsBySpecimen.clear();
        for (StrategySpecimen specimen : tournamentCompetitors) {
            winsBySpecimen.put(specimen, 0);
        }
    }

    @Override
    public void performTournament() {
        StrategySpecimen[] specimens = winsBySpecimen.keySet()
                .toArray(new StrategySpecimen[winsBySpecimen.keySet().size()]);

        for (int i = 0; i < specimens.length - 1; i++) {
            for (int j = i + 1; j < specimens.length; j++) {
                performMatch(specimens[i], specimens[j]);
                performMatch(specimens[j], specimens[i]);
            }
        }
    }

    @Override
    public StrategySpecimen[] getTopPerformers(int numberOfTopPerformers) {
        return winsBySpecimen.entrySet()
                .stream()
                .sorted((o1, o2) -> Double.compare(o2.getKey().getWinningPercentage(), o1.getKey().getWinningPercentage()))
                .limit(numberOfTopPerformers)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList())
                .toArray(new StrategySpecimen[numberOfTopPerformers]);
    }

    private void performMatch(StrategySpecimen darkPiecesPlayer, StrategySpecimen lightPiecesPlayer) {
        darkPiecesPlayer.getPlayer().setPlayerColor(PieceColor.Dark);
        lightPiecesPlayer.getPlayer().setPlayerColor(PieceColor.Light);

        GameRunner gameRunner = new GameRunner(darkPiecesPlayer.getPlayer(), lightPiecesPlayer.getPlayer());
        GameResults gameResult = gameRunner.runGame();
        //System.out.println("game Result " + gameResult);
        IPlayer winner = gameResult.getWinner();
        if (winner != null) {
            if (winner.getPlayerColor() == PieceColor.Dark) {
                darkPiecesPlayer.registerWin();
                lightPiecesPlayer.registerLoss();
            } else {
                lightPiecesPlayer.registerWin();
                darkPiecesPlayer.registerLoss();
            }
        }
    }
}
