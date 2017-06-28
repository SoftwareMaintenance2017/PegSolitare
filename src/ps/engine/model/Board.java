package ps.engine.model;

public class Board {

	private static final int DEFAULT_WIDTH = 7;
	private static final int DEFAULT_HEIGHT = 7;

	private Hole[][] holes;
	 
	 public Board() {
		holes = new Hole[DEFAULT_WIDTH][DEFAULT_HEIGHT];

		for (int x = 0; x < DEFAULT_WIDTH; x++) {
			for (int y = 0; y < DEFAULT_HEIGHT; y++) {
				holes[x][y] = new Hole();
			}
		}
	}

	public Hole[][] getHoles() {
		return holes;
	}

	public void setHoles(Hole[][] holes) {
		this.holes = holes;
	}
}
