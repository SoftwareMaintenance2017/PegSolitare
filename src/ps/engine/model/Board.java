package ps.engine.model;

public class Board {

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
		return holes[position.getX()][position.getY()];
	}


	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		for (int x = 0; x < DEFAULT_WIDTH; x++) {
			for (int y = 0; y < DEFAULT_HEIGHT; y++) {
				Hole hole = holes[x][y];
				if (hole.isEnabled()) {
					if (hole.hasPeg()) {
						string.append("Y");
					} else {
						string.append("O");
					}
				} else {
					string.append(" ");
				}
				string.append(" ");
			}
			string.append("\n");
		}
		return string.toString();
	}
}
