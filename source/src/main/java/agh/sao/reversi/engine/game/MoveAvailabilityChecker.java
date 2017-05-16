package agh.sao.reversi.engine.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static agh.sao.reversi.engine.game.Position.*;
import static java.util.Collections.emptyList;

/**
 * Created by Maxim on 4/25/2017.
 */
public class MoveAvailabilityChecker {

    private final List<MoveOpportunity> moveOpportunities = new ArrayList<>();
    private final GameState gameState;

    public MoveAvailabilityChecker(GameState gameState) {
        this.gameState = gameState;
        moveOpportunities.add(new AboveMoveOpportunity(gameState));
        moveOpportunities.add(new BelowMoveOpportunity(gameState));
        moveOpportunities.add(new LeftMoveOpportunity(gameState));
        moveOpportunities.add(new RightMoveOpportunity(gameState));
        moveOpportunities.add(new LeftAboveDiagonalMoveOpportunity(gameState));
        moveOpportunities.add(new RightAboveDiagonalMoveOpportunity(gameState));
        moveOpportunities.add(new LeftBelowDiagonalMoveOpportunity(gameState));
        moveOpportunities.add(new RightBelowDiagonalMoveOpportunity(gameState));
    }

    public boolean canMove(Move move){
        return !willCapture(move).isEmpty();
    }

    public List<Position> willCapture(Move move) {
        List<Position> willCapture = new ArrayList<>();
        if (isTargetPositionEmpty(move)) {
            for (MoveOpportunity opportunity : moveOpportunities) {
                willCapture.addAll(opportunity.willCapture(move));
            }
        }
        return willCapture;
    }

    private boolean isTargetPositionEmpty(Move move){
        Piece movePositionValue = gameState.valueAt(move.toPosition);
        return movePositionValue == null;
    }
}

abstract class MoveOpportunity {

    private final GameState gameState;

    MoveOpportunity(GameState gameState) {
        this.gameState = gameState;
    }

    Collection<Position> willCapture(Move wantedMove) {
        Position closestNeighbour = neighbour(wantedMove.toPosition);
        if (isClosestNeighbourOpposite(closestNeighbour, wantedMove.pieceColor)) {
            return captureWhenClosing(closestNeighbour, wantedMove.pieceColor);
        } else {
            return emptyList();
        }
    }

    private boolean isClosestNeighbourOpposite(Position closestNeighbour, PieceColor moveColor) {
        return closestNeighbour != null && closestNeighbour.isOppositeTo(moveColor, gameState);
    }

    private List<Position> captureWhenClosing(Position closestNeighbour, PieceColor moveColor) {
        Position currentPosition = closestNeighbour;
        List<Position> willCapture = new ArrayList<>();
        willCapture.add(closestNeighbour);
        while ((currentPosition = neighbour(currentPosition)) != null) {
            Piece currentPiece = gameState.valueAt(currentPosition);
            if(currentPiece == null){
                break;
            }
            else if(moveColor.equals(currentPiece.getColor())) {
                return willCapture;
            } else {
                willCapture.add(currentPosition);
            }
        }
        return emptyList();
    }

    abstract Position neighbour(Position position);

}

class AboveMoveOpportunity extends MoveOpportunity {

    AboveMoveOpportunity(GameState gameState) {
        super(gameState);
    }

    @Override
    Position neighbour(Position position) {
        return above(position);
    }

}

class BelowMoveOpportunity extends MoveOpportunity {

    BelowMoveOpportunity(GameState gameState) {
        super(gameState);
    }

    @Override
    Position neighbour(Position position) {
        return below(position);
    }

}

class LeftMoveOpportunity extends MoveOpportunity {

    LeftMoveOpportunity(GameState gameState) {
        super(gameState);
    }

    @Override
    Position neighbour(Position position) {
        return left(position);
    }

}

class RightMoveOpportunity extends MoveOpportunity {

    RightMoveOpportunity(GameState gameState) {
        super(gameState);
    }

    @Override
    Position neighbour(Position position) {
        return right(position);
    }

}

class LeftAboveDiagonalMoveOpportunity extends MoveOpportunity {

    LeftAboveDiagonalMoveOpportunity(GameState gameState) {
        super(gameState);
    }

    @Override
    Position neighbour(Position position) {
        return leftAbove(position);
    }

}

class RightAboveDiagonalMoveOpportunity extends MoveOpportunity {

    RightAboveDiagonalMoveOpportunity(GameState gameState) {
        super(gameState);
    }

    @Override
    Position neighbour(Position position) {
        return rightAbove(position);
    }

}

class LeftBelowDiagonalMoveOpportunity extends MoveOpportunity {

    LeftBelowDiagonalMoveOpportunity(GameState gameState) {
        super(gameState);
    }

    @Override
    Position neighbour(Position position) {
        return leftBelow(position);
    }

}

class RightBelowDiagonalMoveOpportunity extends MoveOpportunity {

    RightBelowDiagonalMoveOpportunity(GameState gameState) {
        super(gameState);
    }

    @Override
    Position neighbour(Position position) {
        return rightBelow(position);
    }

}
