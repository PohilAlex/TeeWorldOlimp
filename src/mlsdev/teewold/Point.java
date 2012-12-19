package mlsdev.teewold;

import java.util.ArrayList;

class Point {
	int x;
	int y;
	
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public Point getUp() {
		return new Point(x, y - 1);
	}

	public Point getDown() {
		return new Point(x, y + 1);
	}

	public Point getLeft() {
		return new Point(x - 1, y);
	}

	public Point getRight() {
		return new Point(x + 1, y);
	}
	
	public ArrayList<Point> getNear() {
		ArrayList<Point> pointList = new ArrayList<Point>();
		pointList.add(getUp());
		pointList.add(getDown());
		pointList.add(getLeft());
		pointList.add(getRight());
		return pointList; 
	}
}
