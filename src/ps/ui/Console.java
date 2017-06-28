package ps.ui;

import ps.engine.model.Board;
import ps.engine.GameEngine;
import ps.engine.model.Hole;

public class Console {

	private GameEngine game;

	public Console() {
		game = new GameEngine();
	}



	public void play() {
		game.newGame();

		printBoard(game.getBoard());
		
	}

	private void printBoard(Board board) {
		System.out.println("Board: ");
		for (Hole[] rows : board.getHoles()) {
			for (Hole hole : rows) {
				if (hole.isEnabled()) {
					if (hole.isHasPeg()) {
						System.out.print("1");
					} else {
						System.out.print("0");
					}
				} else {
					System.out.print(" ");
				}
				System.out.print("-");
			}
			System.out.println();
		}

	}
}
