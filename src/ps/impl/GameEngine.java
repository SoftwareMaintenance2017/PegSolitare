package ps.impl;

import ps.api.GameController;
import ps.api.model.Board;
import ps.api.model.Position;

public class GameEngine implements GameController {

	private Board board;
	
	public GameEngine() {
		this.newGame();
	}
	
	@Override
	public void newGame() {
		board = new Board();

	}

	@Override
	public void movePeg(Position originalPosition, Position finalPosition) {
		board.getHoles()[originalPosition.getX()][originalPosition.getY()].setHasPeg(false);
		board.getHoles()[finalPosition.getX()][finalPosition.getY()].setHasPeg(true);

	}

	@Override
	public Board getBoard() {
		return board;
	}

}
