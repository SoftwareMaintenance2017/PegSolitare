package ps.engine;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ps.engine.model.Board;
import ps.engine.model.Hole;
import ps.engine.util.ResourceLoader;

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

	/**
	 * Create a board from a pre-build board.
	 * 
	 * @param prebuildBoard
	 * @return
	 */
	public static Board loadBoard(PrebuildBoard prebuildBoard) {
		File boardFile = ResourceLoader.getSavedFile(prebuildBoard.getFile());
		try (Scanner scanner = new Scanner(boardFile)) {

			List<String> result = new ArrayList<>();

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				result.add(line);
			}

			scanner.close();

			return loadBoard(result.toArray(new String[result.size()]));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

}
