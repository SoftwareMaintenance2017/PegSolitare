package ps.ui;

import ps.engine.model.Board;

import java.util.Scanner;

import ps.engine.GameEngine;
import ps.engine.model.Hole;
import ps.engine.model.Position;

public class Console {

	private GameEngine game;

	public Console() {
		game = new GameEngine();
	}



	public void play() {
		game.newGame();
		printBoard(game.getBoard());

		while (!game.isOver()) {
			makeMove();
			printBoard(game.getBoard());
		}
		System.out.println("Game over!");

	}

	private void makeMove() {
		Scanner sc = new Scanner(System.in);

		Position originalPosition = new Position();

		System.out.println("actual X:");
		originalPosition.setX(sc.nextInt());
		System.out.println("actual Y:");
		originalPosition.setY(sc.nextInt());

		Position finalPosition = new Position();

		System.out.println("next X:");
		finalPosition.setX(sc.nextInt());
		System.out.println("next Y:");
		finalPosition.setY(sc.nextInt());


		game.movePeg(originalPosition, finalPosition);
		
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
				System.out.print(" ");
			}
			System.out.println();
		}

	}
}
