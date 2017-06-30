package ps.engine;

import ps.engine.model.Board;
import ps.engine.model.Hole;

public class BoardLoader {

	private BoardLoader() {
	}


	public static Board loadBoard(String[] savedBoard) {
		Board board = new Board();

		for (int x = 0; x < savedBoard.length; x++) {
			for (int y = 0; y < savedBoard[x].length(); y++) {
				char value = savedBoard[x].charAt(y);
				Hole hole = board.getHoles()[x][y];

				switch (value) {
				case '0':
					hole.setEnabled(true);
					break;
				case '1':
					hole.setEnabled(true);
					hole.setHasPeg(true);
					break;
				default:
					hole.setEnabled(false);
					break;
				}
			}
		}

		return board;
	}

}
