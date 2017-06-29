package ps.ui.console;

import java.util.Scanner;

import ps.engine.GameEngine;
import ps.engine.model.Board;
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

			ConsoleWriter.println("actual row num:");
	    originalPosition.setX(sc.nextInt());
			ConsoleWriter.println("actual column num:");
	    originalPosition.setY(sc.nextInt());

	    Position finalPosition = new Position();

			ConsoleWriter.println("next row num:");
	    finalPosition.setX(sc.nextInt());
			ConsoleWriter.println("next column num:");
	    finalPosition.setY(sc.nextInt());

	    game.movePeg(originalPosition, finalPosition);
	    
	} catch (Exception e) {
	    e.printStackTrace();
			ConsoleWriter.println("\n\nMovement not valid, please try again");
	}

    }



	private void printBoard(Board board) {
		ConsoleWriter.println("Board: ");

		ConsoleWriter.print("  ");
	for (int c = 0; c < board.getHoles().length; c++) {
			ConsoleWriter.print(c + " ");
	}
		ConsoleWriter.println("");

	int r = 0;

	for (Hole[] row : board.getHoles()) {
			ConsoleWriter.print((r++) + " ");
	    for (Hole hole : row) {
		if (hole.isEnabled()) {
		    if (hole.isHasPeg()) {
						ConsoleWriter.print("Y");
		    } else {
						ConsoleWriter.print("O");
		    }
		} else {
					ConsoleWriter.print(" ");
		}
				ConsoleWriter.print(" ");
	    }
			ConsoleWriter.println("");
	}

    }
}
