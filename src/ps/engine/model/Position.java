package ps.engine.model;

import java.util.Objects;

public class Position {

    private int x;
    private int y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getY() {
	return y;
    }



    public int getX() {
	return x;
    }

    @Override
    public String toString() {
	return "[" + x + "," + y + "]";
    }

    @Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if (!(other instanceof Position)) {
			return false;
		}
		Position position = (Position) other;
		return Objects.equals(this.x, position.x) && Objects.equals(this.y, position.y);
    }

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

}
