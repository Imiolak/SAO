package agh.sao.reversi.engine.game;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

public class GameStateTest {

    private static final String[][] INITIAL_FIELD_STATE = new String[][]{
            {"*", "*", "*", "*", "*", "*", "*", "*"},
            {"*", "*", "*", "*", "*", "*", "*", "*"},
            {"*", "*", "*", "*", "*", "*", "*", "*"},
            {"*", "*", "*", "W", "B", "*", "*", "*"},
            {"*", "*", "*", "B", "W", "*", "*", "*"},
            {"*", "*", "*", "*", "*", "*", "*", "*"},
            {"*", "*", "*", "*", "*", "*", "*", "*"},
            {"*", "*", "*", "*", "*", "*", "*", "*"},
    };

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void initialStateOfField() {
        GameState expectedField = new GameState(INITIAL_FIELD_STATE);
        assertThat(new GameState()).isEqualTo(expectedField);
    }

    @Test
    public void getsValueForFieldPosition() {
        GameState gameState = new GameState();
        assertThat(gameState.valueAt(new Position('d', 4)).getColor()).isEqualTo(PieceColor.Dark);
        assertThat(gameState.valueAt(new Position('d', 5)).getColor()).isEqualTo(PieceColor.Light);
        assertThat(gameState.valueAt(new Position('e', 4)).getColor()).isEqualTo(PieceColor.Light);
        assertThat(gameState.valueAt(new Position('e', 5)).getColor()).isEqualTo(PieceColor.Dark);
        assertThat(gameState.valueAt(new Position('h', 8))).isNull();
    }

    @Test
    public void throwsIllegalArgumentExceptionForRowNumberLessThenOne() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Row number can have values 1-8 was - 0");
        new Position('a', 0);
    }

    @Test
    public void throwsIllegalArgumentExceptionForRowNumberMoreThenEight() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Row number can have values 1-8 was - 9");
        new Position('a', 9);
    }

    @Test
    public void throwsIllegalArgumentExceptionForInvalidColumnName() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Column name can have values a-h was - z");
        new Position('z', 1);
    }

    @Test
    public void applyMoveChangesFieldStateInAllDirections() {
        GameState initialState = new GameState(
                new String[][]{
                        {"*", "*", "*", "*", "*", "*", "*", "*"},
                        {"*", "*", "W", "W", "W", "W", "W", "W"},
                        {"*", "*", "W", "B", "B", "B", "B", "W"},
                        {"*", "*", "W", "B", "B", "B", "B", "W"},
                        {"*", "*", "W", "B", "B", "*", "B", "W"},
                        {"*", "*", "W", "B", "B", "B", "B", "W"},
                        {"*", "*", "W", "W", "W", "W", "W", "W"},
                        {"*", "*", "*", "*", "*", "*", "*", "*"}}
        );

        initialState.applyMove(new Move(PieceColor.Light, new Position('f', 4)));

        GameState expectedState = new GameState(
                new String[][]{
                        {"*", "*", "*", "*", "*", "*", "*", "*"},
                        {"*", "*", "W", "W", "W", "W", "W", "W"},
                        {"*", "*", "W", "W", "B", "W", "B", "W"},
                        {"*", "*", "W", "B", "W", "W", "W", "W"},
                        {"*", "*", "W", "W", "W", "W", "W", "W"},
                        {"*", "*", "W", "B", "W", "W", "W", "W"},
                        {"*", "*", "W", "W", "W", "W", "W", "W"},
                        {"*", "*", "*", "*", "*", "*", "*", "*"}}
        );

        assertThat(initialState).isEqualTo(expectedState);
    }


}