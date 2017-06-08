package agh.sao.reversi.tournament;

import agh.sao.reversi.algorithm.player.*;
import agh.sao.reversi.engine.player.IPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class StrategySetCreator {

    public List<CombinedStrategiesPlayer> createInitialStrategyBase() {
        Random rng = new Random();
        List<CombinedStrategiesPlayer> players = new ArrayList<>();

        players.add(new CombinedStrategiesPlayer(new GreedyMovesPlayer()));
        players.add(new CombinedStrategiesPlayer(new CornersMovesPlayer()));
        players.add(new CombinedStrategiesPlayer(new MobilityMovesPlayer(1.0)));
        players.add(new CombinedStrategiesPlayer(new StableMovesPlayer(1.0)));
        for (int i = 0; i < 5; i++) {
            players.add(new CombinedStrategiesPlayer(new MobilityMovesPlayer(MobilityMovesPlayer.getRandomParameterValue())));
            players.add(new CombinedStrategiesPlayer(new StableMovesPlayer(StableMovesPlayer.getRandomParameterValue())));
        }
        for (int i = 0; i < 12; i++) {
            players.add(new CombinedStrategiesPlayer(new PositionalMovesPlayer(PositionalMovesPlayer.getRandomParameterValues())));
        }

        for (int i = 0; i < 34; i++) {
            IPlayer player1 = getRandomPlayer(rng);
            int strategySwitchPoint = rng.nextInt(20) + 5;
            IPlayer player2 = getRandomPlayer(rng);

            players.add(new CombinedStrategiesPlayer(player1.copy(), strategySwitchPoint, player2.copy()));
        }

        return players;
    }

    private IPlayer getRandomPlayer(Random rng) {
        switch (rng.nextInt(5)) {
            case 0: return new GreedyMovesPlayer();
            case 1: return new CornersMovesPlayer();
            case 2: return new MobilityMovesPlayer(MobilityMovesPlayer.getRandomParameterValue());
            case 3: return new StableMovesPlayer(StableMovesPlayer.getRandomParameterValue());
            case 4: return new PositionalMovesPlayer(PositionalMovesPlayer.getRandomParameterValues());
        }
        return null;
    }
}
