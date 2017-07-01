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

    public void movePeg(Position originalPosition, Position finalPosition) {

	boolean validMovement = validateMovement(originalPosition, finalPosition);
	if (!validMovement)
	    return;

	board.getHoles()[originalPosition.getX()][originalPosition.getY()].setHasPeg(false);
	board.getHoles()[finalPosition.getX()][finalPosition.getY()].setHasPeg(true);

	if (!moveHistory.isEmpty() && moveHistory.get(moveHistory.size() - 1).getLastPosition().equals(originalPosition)) {
	    moveHistory.get(moveHistory.size() - 1).getPositions().add(finalPosition);
	    LOGGER.info("Adding aditional position to last move");
	} else {
	    PegMove move = new PegMove(originalPosition, finalPosition);
	    moveHistory.add(move);
	    LOGGER.info("Adding new move to history");
	}

	LOGGER.info("Registering move " + moveHistory.get(moveHistory.size() - 1));
    }

    private boolean validateMovement(Position originalPosition, Position finalPosition) {
	try {
	    Hole originalHole = board.getHoles()[originalPosition.getX()][originalPosition.getY()];
	    Hole finalHole = board.getHoles()[finalPosition.getX()][finalPosition.getY()];

	    boolean valid = checkOriginalHole(originalHole);
	    valid = valid && checkFinalHole(finalHole);
	    valid = valid && checkCoherentMovement(originalPosition, finalPosition);
	    valid = valid && checkValidMovement(originalPosition, finalPosition);

	    return valid;
	} catch (Exception e) {
	    LOGGER.info("Invalid movement (error validating)");
	    return false;
	}
    }

    private boolean checkValidMovement(Position originalPosition, Position finalPosition) {
	int midHoleXPosition = (originalPosition.getX() + finalPosition.getX()) / 2;
	int midHoleYPosition = (originalPosition.getY() + finalPosition.getY()) / 2;
	Hole midHole = board.getHoles()[midHoleXPosition][midHoleYPosition];
	if (midHole.isHasPeg()) {
	    midHole.setHasPeg(false);
	    return true;
	} else {
	    LOGGER.info("There's no peg to remove, invalid movement");
	    return false;
	}
    }

    private boolean checkCoherentMovement(Position originalPosition, Position finalPosition) {
	int xDisplacement = Math.abs(originalPosition.getX() - finalPosition.getX());
	int yDisplacement = Math.abs(originalPosition.getY() - finalPosition.getY());
	if ((xDisplacement == 0 && yDisplacement == 2) || (xDisplacement == 2 && yDisplacement == 0))
	    return true;
	LOGGER.info("Invalid displacement");
	return false;

    }

    private boolean checkOriginalHole(Hole originalHole) {
	if (originalHole.isHasPeg() && originalHole.isEnabled())
	    return true;
	LOGGER.info("Disabled or no peg in first position");
	return false;
    }

    private boolean checkFinalHole(Hole finalHole) {
	if (!finalHole.isHasPeg() && finalHole.isEnabled())
	    return true;
	LOGGER.info("Disabled or peg in final position");
	return false;
    }

    public Board getBoard() {
	return board;
    }

    public boolean isOver() {
	int pegs = 0;
	for (Hole[] row : board.getHoles()) {
	    for (Hole hole : row) {
		if (hole.isHasPeg()) {
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
	Position lastPosition;

	board.getHoles()[firstPosition.getX()][firstPosition.getY()].setHasPeg(true);
	LOGGER.info("Undoing move " + lastMove);
	for (int i = 1; i < lastMove.getPositions().size(); i++) {
	    lastPosition = lastMove.getPositions().get(i);
	    board.getHoles()[lastPosition.getX()][lastPosition.getY()].setHasPeg(false);
	    int midHoleXPosition = (firstPosition.getX() + lastPosition.getX()) / 2;
	    int midHoleYPosition = (firstPosition.getY() + lastPosition.getY()) / 2;
	    board.getHoles()[midHoleXPosition][midHoleYPosition].setHasPeg(true);
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
	board = new Board();
	board = BoardLoader.loadBoard(prebuildBoard);

    }

}
