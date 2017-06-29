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
	try  {

	    Scanner sc = new Scanner(System.in);
	    Position originalPosition = new Position();

	    System.out.println("actual row num:");
	    originalPosition.setX(sc.nextInt());
	    System.out.println("actual column num:");
	    originalPosition.setY(sc.nextInt());

	    Position finalPosition = new Position();

	    System.out.println("next row num:");
	    finalPosition.setX(sc.nextInt());
	    System.out.println("next column num:");
	    finalPosition.setY(sc.nextInt());

	    game.movePeg(originalPosition, finalPosition);
	    
	} catch (Exception e) {
	    e.printStackTrace();
	    System.out.println("\n\nMovement not valid, please try again");
	}

    }

    private void printBoard(Board board) {
	System.out.println("Board: ");

	System.out.print("  ");
	for (int c = 0; c < board.getHoles().length; c++) {
	    System.out.print(c + " ");
	}
	System.out.println();

	int r = 0;

	for (Hole[] row : board.getHoles()) {
	    System.out.print((r++) + " ");
	    for (Hole hole : row) {
		if (hole.isEnabled()) {
		    if (hole.isHasPeg()) {
			System.out.print("Y");
		    } else {
			System.out.print("O");
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
