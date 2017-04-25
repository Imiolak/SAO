package agh.sao.reversi.engine.game;

import org.junit.Test;

import java.util.List;

import static agh.sao.reversi.engine.game.PieceColor.Dark;
import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

public class AvailableMovesResolverTest {

    @Test
    public void returnsAvailableMoves() {
        GameState initialState = new GameState();
        AvailableMovesResolver movesResolver = new AvailableMovesResolver();
        List<Move> expectedMoves = newArrayList(
                new Move(Dark, new Position('d', 6)),
                new Move(Dark, new Position('c', 5)),
                new Move(Dark, new Position('e', 3)),
                new Move(Dark, new Position('f', 4))
        );
        assertThat(movesResolver.getAvailableMovesForPlayer(Dark, initialState)).containsOnlyElementsOf(expectedMoves);
    }

    @Test
    public void acceptanceTest() {
        GameState initialState = new GameState(new String[][]{
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "B", "W", "*", "W", "*", "*"},
                {"*", "*", "W", "W", "W", "*", "*", "*"},
                {"*", "*", "B", "W", "B", "*", "*", "*"},
                {"*", "*", "B", "B", "B", "B", "B", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
        });
        AvailableMovesResolver movesResolver = new AvailableMovesResolver();
        List<Move> expectedMoves = newArrayList(
                new Move(Dark, new Position('d', 7)),
                new Move(Dark, new Position('g', 7)),
                new Move(Dark, new Position('b', 6)),
                new Move(Dark, new Position('e', 6))
        );
        assertThat(movesResolver.getAvailableMovesForPlayer(Dark, initialState)).containsOnlyElementsOf(expectedMoves);
    }

}
