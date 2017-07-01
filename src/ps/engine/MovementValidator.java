package ps.engine;

import java.util.logging.Logger;

import ps.engine.model.Board;
import ps.engine.model.Hole;
import ps.engine.model.Position;

class MovementValidator {

	private static final Logger LOGGER = Logger.getLogger(MovementValidator.class.getName());

	private MovementValidator() {
	}

	/**
	 * Check if the hole is empty.
	 * 
	 * @param finalHole
	 * @return
	 */
	static boolean checkFinalHole(Hole finalHole) {
		if (!finalHole.hasPeg()) {
			return true;
		}
		LOGGER.warning("Disabled or peg in final position");
		return false;
	}

	/**
	 * Check if the hole is has a peg.
	 * 
	 * @param originalHole
	 * @return
	 */
	static boolean checkOriginalHole(Hole originalHole) {
		if (originalHole.hasPeg()) {
			return true;
		}
		LOGGER.warning("Disabled or no peg in first position");
		return false;
	}

	/**
	 * Check the direction and size of the movement
	 * 
	 * @param originalPosition
	 * @param finalPosition
	 * @return
	 */
	static boolean checkCoherentMovement(Position originalPosition, Position finalPosition) {
		int xDisplacement = Math.abs(originalPosition.getRow() - finalPosition.getRow());
		int yDisplacement = Math.abs(originalPosition.getColumn() - finalPosition.getColumn());
		if ((xDisplacement == 0 && yDisplacement == 2)
				|| (xDisplacement == 2 && yDisplacement == 0)) {
			return true;
		}
		LOGGER.info("Invalid displacement");
		return false;

	}

	/**
	 * Check if the middle hole has a peg.
	 * 
	 * @param board
	 * @param originalPosition
	 * @param finalPosition
	 * @return
	 */
	static boolean checkMidHole(Board board, Position originalPosition,
			Position finalPosition) {

		Hole midHole = getMiddleHole(board, originalPosition, finalPosition);
		if (midHole.hasPeg()) {
			return true;
		} else {
			LOGGER.info("There's no peg to remove, invalid movement");
			return false;
		}
	}

	static Hole getMiddleHole(Board board, Position originalPosition, Position finalPosition) {
		int midHoleXPosition = (originalPosition.getRow() + finalPosition.getRow()) / 2;
		int midHoleYPosition = (originalPosition.getColumn() + finalPosition.getColumn()) / 2;
		return board.getHole(new Position(midHoleXPosition, midHoleYPosition));
	}
}
