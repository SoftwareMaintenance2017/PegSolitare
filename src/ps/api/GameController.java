package ps.api;

import ps.api.model.Board;
import ps.api.model.Position;

public interface GameController {
	public void newGame();
	public void movePeg(Position originalPosition,Position finalPosition);
	public Board getBoard();
}
