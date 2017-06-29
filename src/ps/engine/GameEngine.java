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

	}


	public void movePeg(Position originalPosition, Position finalPosition) {
		board.getHoles()[originalPosition.getX()][originalPosition.getY()].setHasPeg(false);
		board.getHoles()[finalPosition.getX()][finalPosition.getY()].setHasPeg(true);

	}


	public Board getBoard() {
		return board;
	}

	public boolean isOver() {
		for (Hole[] row : board.getHoles()) {
			for (Hole hole : row) {
				if (hole.isHasPeg()) {
					return false;
				}
			}
		}
		return true;
	}

}
