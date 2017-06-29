package ps.engine;

import ps.engine.model.Board;
import ps.engine.model.Hole;
import ps.engine.model.Position;

public class GameEngine {

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
	    
	    if (!board.getHoles()[originalPosition.getX()][originalPosition.getY()].isHasPeg()) {
		System.out.println("No peg in actual position");
		return false;
	    }
	    
	    if (board.getHoles()[finalPosition.getX()][finalPosition.getY()].isHasPeg()) {
		System.out.println("There must be no peg in final position");
		return false;
	    }

	    if (!board.getHoles()[originalPosition.getX()][originalPosition.getY()].isEnabled() || !board.getHoles()[finalPosition.getX()][finalPosition.getY()].isEnabled()) {
		System.out.println("Displacement outside board valid space");
		return false;
	    }

	    int xDisplacement = Math.abs(originalPosition.getX() - finalPosition.getX());
	    int yDisplacement = Math.abs(originalPosition.getY() - finalPosition.getY());
	    if (!(xDisplacement == 0 && yDisplacement == 2) && !(xDisplacement == 2 && yDisplacement == 0)) {
		System.out.println("Invalid displacement");
		return false;
	    }

	    int midHoleXPosition = (originalPosition.getX() + finalPosition.getX()) / 2;
	    int midHoleYPosition = (originalPosition.getY() + finalPosition.getY()) / 2;
	    Hole midHole = board.getHoles()[midHoleXPosition][midHoleYPosition];
	    if (midHole.isHasPeg()) {
		midHole.setHasPeg(false);
	    } else {
		System.out.println("There's no peg to remove, invalid movement");
		return false;
	    }

	    return true;
	} catch (Exception e) {
	    System.out.println("Invalid movement (error validating)");
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
		if (hole.isHasPeg()) {
		    pegs++;
		}
	    }
	}
	return pegs == 1;
    }

}
