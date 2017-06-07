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
        List<IPlayer> playerBase = new ArrayList<>();
        List<CombinedStrategiesPlayer> players = new ArrayList<>();

        playerBase.add(new GreedyMovesPlayer());
        playerBase.add(new CornersMovesPlayer());
        for (int i = 0; i < 8; i++) {
            playerBase.add(new MobilityMovesPlayer(MobilityMovesPlayer.getRandomParameterValue()));
            playerBase.add(new StableMovesPlayer(StableMovesPlayer.getRandomParameterValue()));
            playerBase.add(new PositionalMovesPlayer(PositionalMovesPlayer.getRandomParameterValues()));
        }

        players.addAll(playerBase.stream()
                .map(basePlayer -> new CombinedStrategiesPlayer(basePlayer.copy()))
                .collect(Collectors.toList()));

        for (int i = 0; i < 34; i++) {
            IPlayer player1 = playerBase.get(rng.nextInt(playerBase.size()));
            int strategySwitchPoint = rng.nextInt(20);
            IPlayer player2 = playerBase.get(rng.nextInt(playerBase.size()));

            players.add(new CombinedStrategiesPlayer(player1.copy(), strategySwitchPoint, player2.copy()));
        }

        return players;
    }
}
