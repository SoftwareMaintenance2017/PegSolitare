package ps.engine.model;

public class Position {

    private int x;
    private int y;

    public int getY() {
	return y;
    }

    public void setY(int y) {
	this.y = y;
    }

    public int getX() {
	return x;
    }

    public void setX(int x) {
	this.x = x;
    }

    @Override
    public String toString() {
	return "[" + x + "," + y + "]";
    }

    @Override
    public boolean equals(Object object) {
	if (object instanceof Position) {
	Position other = (Position) object;
	return other.x==this.x && other.y==this.y;
	} else
	    return false;
    }

}
