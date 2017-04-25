package agh.sao.reversi.engine.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static agh.sao.reversi.engine.game.Position.columnNameForIndex;

/**
 * Created by Imiolak on 14-Mar-17.
 */
public class AvailableMovesResolver {

    public List<Move> getAvailableMovesForPlayer(PieceColor playerColor, GameState board) {
        List<Move> availableMoves = new ArrayList<>();
        MoveAvailabilityChecker moveChecker = new MoveAvailabilityChecker(board);
        for(int i = 0; i < 8 ; i++){
            for(int j = 0; j < 8 ; j++){
                Piece currentPiece = board.valueAt(i,j);
                if(currentPiece == null){
                    Move tryMove = new Move(playerColor, new Position(columnNameForIndex(j), 8 - i));
                    if(moveChecker.canMove(tryMove)){
                        availableMoves.add(tryMove);
                    }
                }
            }
        }
        return availableMoves;
    }
}
