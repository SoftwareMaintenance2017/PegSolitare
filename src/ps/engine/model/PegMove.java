package ps.engine.model;

import java.util.List;

public class PegMove {

	private List<Position> positions;

	public PegMove(List<Position> positions) {
		super();
		this.positions = positions;
	}
	
	

	@Override
	public String toString() {
		return "Move [positions=" + positions + "]";
	}



	/**
	 * @return the positions
	 */
	public List<Position> getPositions() {
		return positions;
	}
	
	
	
	
}
