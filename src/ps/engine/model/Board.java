package ps.engine.model;

public class Board {

	private static final String SPACE = " ";
	private static final int DEFAULT_WIDTH = 7;
	private static final int DEFAULT_HEIGHT = 7;

	private Hole[][] holes;
	 
	 public Board() {
		holes = new Hole[DEFAULT_WIDTH][DEFAULT_HEIGHT];

		for (int x = 0; x < DEFAULT_WIDTH; x++) {
			for (int y = 0; y < DEFAULT_HEIGHT; y++) {
				holes[x][y] = new Hole(false, false);
			}
		}
	}

	public Hole[][] getHoles() {
		return holes;
	}

	/**
	 * Get the hole object for the given position.
	 * 
	 * @param position
	 * @return
	 */
	public Hole getHole(Position position) {
		return holes[position.getRow()][position.getColumn()];
	}


	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();

		string.append("  ");

		for (int x = 0; x < holes[0].length; x++) {
			string.append(x).append(SPACE);
		}
		string.append("\n");

		for (int row = 0; row < holes.length; row++) {
			string.append(row).append(SPACE);
			for (int column = 0; column < holes[row].length; column++) {
				Hole hole = holes[row][column];
				if (hole.isEnabled()) {
					if (hole.hasPeg()) {
						string.append("Y");
					} else {
						string.append("O");
					}
				} else {
					string.append(SPACE);
				}
				string.append(SPACE);
			}
			string.append("\n");
		}
		return string.toString();
	}
}
