package ps.ui;

import ps.api.GameController;
import ps.api.model.Board;
import ps.api.model.Hole;
import ps.impl.GameEngine;

public class Console {

	private GameController game;

	public Console() {
		game = new GameEngine();
	}

	public static void main(String[] args) {
		
		Console console = new Console();
		console.play();

	}

	private void play() {
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
