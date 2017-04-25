package agh.sao.reversi.engine.game;

import org.junit.Test;

import static agh.sao.reversi.engine.game.PieceColor.Dark;
import static agh.sao.reversi.engine.game.PieceColor.Light;
import static org.assertj.core.api.Assertions.assertThat;


public class MoveAvailabilityCheckerTest {

    private GameState gameState = new GameState();
    private MoveAvailabilityChecker moveChecker = new MoveAvailabilityChecker(gameState);

    @Test
    public void cantMoveWhenNoOpponentPieceNear() {
        assertThat(moveChecker.canMove(new Move(Light, new Position('a', 1)))).isFalse();
        assertThat(moveChecker.canMove(new Move(Light, new Position('a', 8)))).isFalse();
        assertThat(moveChecker.canMove(new Move(Light, new Position('h', 1)))).isFalse();
        assertThat(moveChecker.canMove(new Move(Light, new Position('h', 8)))).isFalse();
    }

    @Test
    public void cantMoveOnAlreadyOccupiedGameState(){
        GameState gameState = new GameState(new String[][]{
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "W", "W", "*", "*", "*"},
                {"*", "*", "*", "B", "W", "W", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
        });
        moveChecker = new MoveAvailabilityChecker(gameState);
        Move move = new Move(Dark, new Position('f', 4));
        assertThat(moveChecker.canMove(move)).isFalse();
    }

    // Moving Up

    @Test
    public void canMoveWhenCapturingSingleOpponentAbove() {
        Move move = new Move(Dark, new Position('e', 3));
        assertThat(moveChecker.canMove(move)).isTrue();
    }

    @Test
    public void cantMoveWhenSameColorAbove() {
        Move move = new Move(Dark, new Position('d', 3));
        assertThat(moveChecker.canMove(move)).isFalse();
    }

    @Test
    public void cantMoveWhenSingleOpponentAboveButImpossibleToCapture() {
        GameState gameState = new GameState(new String[][]{
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "W", "W", "*", "*", "*"},
                {"*", "*", "*", "B", "W", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
        });
        moveChecker = new MoveAvailabilityChecker(gameState);
        Move move = new Move(Dark, new Position('e', 3));
        assertThat(moveChecker.canMove(move)).isFalse();
    }

    @Test
    public void canMoveCapturingMoreThenOneOpponentAbove() {
        GameState gameState = new GameState(new String[][]{
                {"*", "*", "*", "*", "B", "*", "*", "*"},
                {"*", "*", "*", "*", "W", "*", "*", "*"},
                {"*", "*", "*", "*", "W", "*", "*", "*"},
                {"*", "*", "*", "W", "W", "*", "*", "*"},
                {"*", "*", "*", "B", "W", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
        });
        moveChecker = new MoveAvailabilityChecker(gameState);
        Move move = new Move(Dark, new Position('e', 3));
        assertThat(moveChecker.canMove(move)).isTrue();
    }

    // Moving Down

    @Test
    public void canMoveWhenCapturingSingleOpponentBelow() {
        Move move = new Move(Dark, new Position('d', 6));
        assertThat(moveChecker.canMove(move)).isTrue();
    }

    @Test
    public void cantMoveWhenSameColorBelow() {
        Move move = new Move(Dark, new Position('e', 6));
        assertThat(moveChecker.canMove(move)).isFalse();
    }

    @Test
    public void cantMoveWhenSingleOpponentBelowButImpossibleToCapture() {
        GameState gameState = new GameState(new String[][]{
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "W", "W", "*", "*", "*"},
                {"*", "*", "*", "B", "W", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
        });
        moveChecker = new MoveAvailabilityChecker(gameState);
        Move move = new Move(Dark, new Position('e', 6));
        assertThat(moveChecker.canMove(move)).isFalse();
    }

    @Test
    public void canMoveCapturingMoreThenOneOpponentBelow() {
        GameState gameState = new GameState(new String[][]{
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "B", "*", "*", "*"},
                {"*", "*", "*", "*", "B", "*", "*", "*"},
                {"*", "*", "*", "W", "W", "*", "*", "*"},
                {"*", "*", "*", "B", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
        });
        Move move = new Move(Light, new Position('e', 8));
        moveChecker = new MoveAvailabilityChecker(gameState);
        assertThat(moveChecker.canMove(move)).isTrue();
    }

    // Moving Left

    @Test
    public void canMoveWhenCapturingSingleOpponentFromLeft() {
        Move move = new Move(Dark, new Position('f', 4));
        assertThat(moveChecker.canMove(move)).isTrue();
    }

    @Test
    public void cantMoveWhenSameColorFromLeft() {
        Move move = new Move(Dark, new Position('f', 5));
        assertThat(moveChecker.canMove(move)).isFalse();
    }

    @Test
    public void cantMoveWhenSingleOpponentFromLeftButImpossibleToCapture() {
        GameState gameState = new GameState(new String[][]{
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "W", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "W", "B", "*", "*", "*"},
                {"*", "*", "*", "B", "W", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
        });
        Move move = new Move(Dark, new Position('c', 6));
        moveChecker = new MoveAvailabilityChecker(gameState);
        assertThat(moveChecker.canMove(move)).isFalse();
    }

    @Test
    public void canMoveCapturingMoreThenOneOpponentFromLeft() {
        GameState gameState = new GameState(new String[][]{
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "B", "*", "*", "*"},
                {"*", "*", "*", "*", "B", "*", "*", "*"},
                {"*", "B", "W", "W", "W", "*", "*", "*"},
                {"*", "*", "*", "B", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
        });
        Move move = new Move(Dark, new Position('f', 5));
        moveChecker = new MoveAvailabilityChecker(gameState);
        assertThat(moveChecker.canMove(move)).isTrue();
    }

    // Moving Right

    @Test
    public void canMoveWhenCapturingSingleOpponentFromRight() {
        Move move = new Move(Dark, new Position('c', 5));
        assertThat(moveChecker.canMove(move)).isTrue();
    }

    @Test
    public void cantMoveWhenSameColorFromRight() {
        Move move = new Move(Dark, new Position('c', 4));
        assertThat(moveChecker.canMove(move)).isFalse();
    }

    @Test
    public void cantMoveWhenSingleOpponentFromRightButImpossibleToCapture() {
        GameState gameState = new GameState(new String[][]{
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "W", "*", "*", "*", "*"},
                {"*", "*", "*", "B", "W", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
        });
        Move move = new Move(Dark, new Position('c', 5));
        moveChecker = new MoveAvailabilityChecker(gameState);
        assertThat(moveChecker.canMove(move)).isFalse();
    }

    @Test
    public void canMoveCapturingMoreThenOneOpponentFromRight() {
        GameState gameState = new GameState(new String[][]{
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "B", "*", "*", "*"},
                {"*", "*", "*", "*", "B", "*", "*", "*"},
                {"*", "*", "W", "W", "W", "B", "*", "*"},
                {"*", "*", "*", "B", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
        });
        Move move = new Move(Dark, new Position('b', 5));
        moveChecker = new MoveAvailabilityChecker(gameState);
        assertThat(moveChecker.canMove(move)).isTrue();
    }

    // Moving Left Top Diagonal

    @Test
    public void canMoveWhenCapturingSingleOpponentFromLeftTopDiagonal() {
        GameState gameState = new GameState(new String[][]{
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "B", "*", "*", "*"},
                {"*", "B", "*", "*", "B", "*", "*", "*"},
                {"*", "*", "W", "W", "W", "B", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
        });
        Move move = new Move(Dark, new Position('d', 4));
        moveChecker = new MoveAvailabilityChecker(gameState);
        assertThat(moveChecker.canMove(move)).isTrue();
    }

    @Test
    public void cantMoveWhenSameColorFromLeftTopDiagonal() {
        GameState gameState = new GameState(new String[][]{
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "B", "*", "*", "*"},
                {"*", "B", "*", "*", "B", "*", "*", "*"},
                {"*", "*", "B", "W", "W", "B", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
        });
        Move move = new Move(Dark, new Position('d', 4));
        moveChecker = new MoveAvailabilityChecker(gameState);
        assertThat(moveChecker.canMove(move)).isFalse();
    }

    @Test
    public void cantMoveWhenSingleOpponentFromLeftTopDiagonalButImpossibleToCapture() {
        GameState gameState = new GameState(new String[][]{
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "B", "*", "*", "*"},
                {"*", "*", "*", "*", "B", "*", "*", "*"},
                {"*", "*", "W", "W", "W", "B", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
        });
        Move move = new Move(Dark, new Position('d', 4));
        moveChecker = new MoveAvailabilityChecker(gameState);
        assertThat(moveChecker.canMove(move)).isFalse();
    }

}
