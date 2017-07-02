package ps.engine.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PegMove {

    private List<Position> positions = new ArrayList<>();

    public PegMove(Position... positions) {
	this.positions.addAll(Arrays.asList(positions));
    }

    @Override
    public String toString() {
	StringBuilder positionsBuilder = new StringBuilder("");
	positions.stream().forEach(position -> positionsBuilder.append(" -> " + position.toString()));
	positionsBuilder.replace(0, 4, "");
	return positionsBuilder.toString();
    }

    /**
     * @return the positions
     */
    public List<Position> getPositions() {
	return positions;
    }

    /**
     * @return the positions
     */
    public Position getLastPosition() {
		if (positions.isEmpty())
	    return null;
	return positions.get(positions.size() - 1);
    }

}
