package ps.engine.model;

import java.util.Objects;

public class Position {

    private int row;
    private int column;

	public Position(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public int getColumn() {
	return column;
    }

    public int getRow() {
	return row;
    }

    @Override
    public String toString() {
	return "[" + row + "," + column + "]";
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
		return Objects.equals(this.row, position.row) && Objects.equals(this.column, position.column);
    }

	@Override
	public int hashCode() {
		return Objects.hash(row, column);
	}

}
