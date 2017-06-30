package ps.engine;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import ps.engine.model.Board;
import ps.engine.model.Hole;
import ps.engine.model.PegMove;
import ps.engine.model.Position;

public class GameEngine {

    private static final Logger LOGGER = Logger.getLogger(GameEngine.class.getName());

    private Board board;

    public GameEngine() {
	this.newGame();
    }

    public void newGame() {
	board = new Board();

	String[] latinCross = new String[] { "xx000xx", "xx010xx", "0011100", "0001000", "0001000", "xx000xx", "xx000xx" };
	board = BoardLoader.loadBoard(latinCross);

    }

    public void movePeg(Position originalPosition, Position finalPosition) {

	boolean validMovement = validateMovement(originalPosition, finalPosition);
	if (!validMovement)
	    return;

	board.getHoles()[originalPosition.getX()][originalPosition.getY()].setHasPeg(false);
	board.getHoles()[finalPosition.getX()][finalPosition.getY()].setHasPeg(true);

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
		// Unimplemented
	}

	/**
	 * Get the list of all peg moves done during the session game.
	 * 
	 * @return the peg move list
	 * @see PegMove
	 */
	public List<PegMove> moveHistory() {
		return Collections.emptyList();
	}

}
