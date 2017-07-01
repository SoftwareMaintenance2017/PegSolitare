package ps.engine.model;

import java.util.ArrayList;
import java.util.List;

public class PegMove {

    private List<Position> positions = new ArrayList<>();

    public PegMove(List<Position> positions) {
	this.positions = positions;
    }

    @Override
    public String toString() {
	StringBuilder positionsBuilder = new StringBuilder("");
	positions.stream().forEach(position -> positionsBuilder.append(" -> " + position.toString()));
	positionsBuilder.replace(0, 3, "");
	return positionsBuilder.toString();
    }

    /**
     * @return the positions
     */
    public List<Position> getPositions() {
	return positions;
    }

}
