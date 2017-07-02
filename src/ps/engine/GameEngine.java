package ps.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import ps.engine.model.Board;
import ps.engine.model.Hole;
import ps.engine.model.PegMove;
import ps.engine.model.Position;

public class GameEngine {

    private static final Logger LOGGER = Logger.getLogger(GameEngine.class.getName());

    private Board board;

    private final List<PegMove> moveHistory = new ArrayList<>();

	/**
	 * Create a new instance with the default board.
	 */
    public GameEngine() {
	this.newGame();
    }

    /**
     * Start a new game with the LATIN CROSS board.
     */
    public void newGame() {
	this.newGame(PrebuildBoard.LATIN_CROSS);
    }

	/**
	 * Move a peg in the board from the initial position to the final position.
	 * 
	 * @param initialPosition
	 * @param finalPosition
	 */
    public void movePeg(Position initialPosition, Position finalPosition) {

	boolean validMovement = validateMovement(initialPosition, finalPosition);
	if (!validMovement)
	    return;

		board.getHole(initialPosition).setHasPeg(false);

		Hole middleHole = MovementValidator.getMiddleHole(board, initialPosition, finalPosition);

		middleHole.setHasPeg(false);

		board.getHole(finalPosition).setHasPeg(true);

	if (!moveHistory.isEmpty() && moveHistory.get(moveHistory.size() - 1).getLastPosition().equals(initialPosition)) {
	    moveHistory.get(moveHistory.size() - 1).getPositions().add(finalPosition);
	    LOGGER.info("Adding aditional position to last move");
	} else {
	    PegMove move = new PegMove(initialPosition, finalPosition);
	    moveHistory.add(move);
	    LOGGER.info("Adding new move to history");
	}

	LOGGER.info("Registering move " + moveHistory.get(moveHistory.size() - 1));
    }

    private boolean validateMovement(Position originalPosition, Position finalPosition) {
	try {
			Hole originalHole = board.getHole(originalPosition);
			Hole finalHole = board.getHole(finalPosition);

			boolean valid = MovementValidator.checkOriginalHole(originalHole);
			valid = valid && MovementValidator.checkFinalHole(finalHole);
			valid = valid
					&& MovementValidator.checkCoherentMovement(originalPosition, finalPosition);
			valid = valid && MovementValidator.checkMidHole(board, originalPosition, finalPosition);

	    return valid;
	} catch (Exception e) {
	    LOGGER.info("Invalid movement (error validating)");
	    return false;
	}
    }


    public Board getBoard() {
	return board;
    }

    public boolean isOver() {
	int pegs = 0;
	for (Hole[] row : board.getHoles()) {
	    for (Hole hole : row) {
		if (hole.hasPeg()) {
		    pegs++;
		}
	    }
	}

	return pegs == 1;
    }

    /**
     * Undo the last peg move.
     */
    public void undoMove() {
		if (moveHistory.isEmpty())
			return;

		PegMove lastMove = moveHistory.get(moveHistory.size() - 1);
		Position firstPosition = lastMove.getPositions().get(0);

		board.getHole(firstPosition).setHasPeg(true);
		LOGGER.info("Undoing move " + lastMove);
		for (Position lastPosition : lastMove.getPositions()) {
			board.getHole(lastPosition).setHasPeg(false);
			MovementValidator.getMiddleHole(board, firstPosition, lastPosition).setHasPeg(true);
			firstPosition = lastPosition;
		}
		moveHistory.remove(lastMove);
    }

    /**
     * Get the list of all peg moves done during the session game.
     * 
     * @return the peg move list
     * @see PegMove
     */
    public List<PegMove> getMoveHistory() {
	return moveHistory;
    }

    /**
     * Start a new game with the given board.
     * 
     * @param prebuildBoard
     */
    public void newGame(PrebuildBoard prebuildBoard) {
	board = BoardLoader.loadBoard(prebuildBoard);
    }

}
