package mlsdev.teewold;

import java.util.ArrayList;

class Point {
	int x;
	int y;
	
	public Point(int x, int y) {
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
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Point point = (Point) obj;
		return (x == point.x && y == point.y);
	}
	
	@Override
	public int hashCode() {
		final int prime = 311;
		int result = 12;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
}
