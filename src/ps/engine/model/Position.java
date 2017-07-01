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

}
