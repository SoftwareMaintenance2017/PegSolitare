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

		board.getHoles()[4][2].setEnabled(true);
		board.getHoles()[3][3].setEnabled(true);
		board.getHoles()[4][3].setEnabled(true);
		board.getHoles()[5][3].setEnabled(true);
		board.getHoles()[4][4].setEnabled(true);
		board.getHoles()[4][5].setEnabled(true);

		board.getHoles()[4][2].setHasPeg(true);
		board.getHoles()[3][3].setHasPeg(true);
		board.getHoles()[4][3].setHasPeg(true);
		board.getHoles()[5][3].setHasPeg(true);
		board.getHoles()[4][4].setHasPeg(true);
		board.getHoles()[4][5].setHasPeg(true);

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
