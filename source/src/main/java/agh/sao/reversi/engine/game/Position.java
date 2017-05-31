package agh.sao.reversi.engine.game;

import java.util.List;

import static agh.sao.reversi.engine.game.PieceColor.opposite;
import static com.google.common.collect.Lists.newArrayList;

/**
 * Position in field
 * rows are counted from bottom row that has index 1
 * columns are counted from left column that has name a
 * so left bottom corner position is equal to (1,a)
 */
public class Position {

    private static final List<Character> COLUMN_NAMES = newArrayList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h');
    final int rowNumber;
    final char columnName;

    public Position(String position) {
        this(position.charAt(0), Integer.valueOf(String.valueOf(position.charAt(1))));
    }

    Position(char columnName, int rowNumber) {
        if(rowNumber < 1 || rowNumber > 8){
            throw new IllegalArgumentException("Row number can have values 1-8 was - " + rowNumber);
        }
        if(!COLUMN_NAMES.contains(columnName)){
            throw new IllegalArgumentException("Column name can have values a-h was - "+ columnName);
        }
        this.rowNumber = rowNumber;
        this.columnName = columnName;
    }

    int columnNameToFieldIndex(){
        return COLUMN_NAMES.indexOf(columnName);
    }

    int rowNumberToFieldIndex(){
        return 8 - rowNumber;
    }

    public int getColumnNameToFieldIndex() { return columnNameToFieldIndex(); }

    public int getRowNumberToFieldIndex() { return rowNumberToFieldIndex(); }

    boolean isOppositeTo(PieceColor otherColor, GameState field) {
        Piece myPiece = field.valueAt(this);
        return myPiece != null && myPiece.getColor().equals(opposite(otherColor));
    }

    static char columnNameForIndex(int columnIndex){
        return COLUMN_NAMES.get(columnIndex);
    }

    static Position above(Position from) {
        return from.rowNumber < 8 ? new Position(from.columnName, from.rowNumber + 1) : null;
    }

    static Position below(Position from) {
        return from.rowNumber > 1 ? new Position(from.columnName, from.rowNumber - 1) : null;
    }

    static Position left(Position from) {
        int leftPositionIndex = COLUMN_NAMES.indexOf(from.columnName) - 1;
        return from.columnName != 'a' ? new Position(COLUMN_NAMES.get(leftPositionIndex), from.rowNumber) : null;
    }

    static Position right(Position from) {
        int rightPositionIndex = COLUMN_NAMES.indexOf(from.columnName) + 1;
        return from.columnName != 'h' ? new Position(COLUMN_NAMES.get(rightPositionIndex), from.rowNumber) : null;
    }

    static Position leftAbove(Position from) {
        int leftPositionIndex = COLUMN_NAMES.indexOf(from.columnName) - 1;
        return from.columnName != 'a' && from.rowNumber < 8 ? new Position(COLUMN_NAMES.get(leftPositionIndex), from.rowNumber + 1) : null;
    }

    static Position rightAbove(Position from) {
        int rightPositionIndex = COLUMN_NAMES.indexOf(from.columnName) + 1;
        return from.columnName != 'h' && from.rowNumber < 8 ? new Position(COLUMN_NAMES.get(rightPositionIndex), from.rowNumber + 1) : null;
    }

    static Position leftBelow(Position from) {
        int leftPositionIndex = COLUMN_NAMES.indexOf(from.columnName) - 1;
        return from.columnName != 'a' && from.rowNumber > 1 ? new Position(COLUMN_NAMES.get(leftPositionIndex), from.rowNumber - 1) : null;
    }

    static Position rightBelow(Position from) {
        int rightPositionIndex = COLUMN_NAMES.indexOf(from.columnName) + 1;
        return from.columnName != 'h' && from.rowNumber > 1 ? new Position(COLUMN_NAMES.get(rightPositionIndex), from.rowNumber - 1) : null;
    }

    @Override
    public String toString() {
        return String.format("Position: {%s,%s}", columnName, rowNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (rowNumber != position.rowNumber) return false;
        return columnName == position.columnName;

    }

    @Override
    public int hashCode() {
        int result = rowNumber;
        result = 31 * result + (int) columnName;
        return result;
    }
}
