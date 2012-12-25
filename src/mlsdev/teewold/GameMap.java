package mlsdev.teewold;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GameMap {
	int[][] map;
	Point startPoint;
	PointUtil pointUtil;
	
	public GameMap(int[][] map) {
		this.map = map;
		this.startPoint = new Point(0,0);
		pointUtil = new PointUtil();
	}
	
	
	public PointUtil getPointUtil() {
		return pointUtil;
	}


	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
	
	public void calcDistance() {
		ArrayList<Point> nextStep = new ArrayList<Point>();
		ArrayList<Point> currStep = new ArrayList<Point>();
		currStep.add(startPoint);
		map[startPoint.y][startPoint.x] = 1;
		while (currStep.size() > 0) {
			for (Point point : currStep) {
				int value = map[point.y][point.x];
				/*
				if (analizeStep(point.x - 1, point.y , value)) {
					nextStep.add(new Point(point.x - 1, point.y));
				}
				if (analizeStep(point.x + 1, point.y, value)) {
					nextStep.add(new Point(point.x + 1, point.y));
				}
				if (analizeStep(point.x, point.y - 1, value)) {
					nextStep.add(new Point(point.x, point.y - 1));
				}
				if (analizeStep(point.x, point.y + 1, value)) {
					nextStep.add(new Point(point.x, point.y + 1));
				}*/
				for (Point p : point.getNear()) {
					if (analizeStep(p.x, p.y, value)) {
						nextStep.add(p);
					}
				}
			}
			currStep = nextStep;
			nextStep = new ArrayList<Point>();
		}
		printMap();
		System.out.println();
	}
	
	public PathNode findMinPath(Point endPoint) {
		PathNode path = new PathNode(endPoint);
		ArrayList<PathNode> currNode = new ArrayList<PathNode>();
		ArrayList<PathNode> nextNode = new ArrayList<PathNode>();
		Comparator<Point> pointComerator = new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				if (getField(o1) <= 0) {
					return 1;
				}
				if (getField(o2) <= 0) {
					return -1;
				}
				return getField(o1) - getField(o2);
			}
		};
		
		//Anilize fist point in path
		ArrayList<Point> nextPoints = pointUtil.getNear(endPoint);
		Point min = Collections.min(nextPoints, pointComerator);

		for (Point point : nextPoints) {
			if (getField(point) == getField(min)) {
				currNode.add(path.add(point));
			}
		}
		
		//Analize other point in path
		while (currNode.size() > 0) {
			for (PathNode node : currNode) {
				Point point = node.getData();
				int currLength = getField(point);
				if (currLength == 2) {
					break;
				}
				
				for (Point nextPoint : pointUtil.getNear(point)) {
					if (getField(nextPoint) == currLength - 1) {
						nextNode.add(node.add(nextPoint));
					}
				}
			}
			currNode = nextNode;
			nextNode = new ArrayList<PathNode>();
		}	
		return path;
	}
	
	public int getField(Point point) {
		return map[point.y][point.x];
	}
	
	public boolean analizeStep(int x, int y, int prevValue) {
		try {
			if (map[y][x] == -1) {
				return false;
			}
			if (map[y][x] <= 0 || map[y][x] > prevValue) {
				map[y][x] = ++prevValue;
				return true;
			}
			return false;
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}
	
	public boolean isAttainable(Point point) {
		for (Point p : pointUtil.getNear(point)) {
			int length = getField(p); 
			if (length != -1 && length != 0) {
				return true;
			}
		}
		return false;
	}
	
	public Point findPoint(int value) {
		int i = 0;
		int j = 0;
		while (map[i][j] != value) {
			if (j < map[0].length - 1) {
				j++;
			} else {
				j = 0;
				i++;
			}
		}
		return new Point(j, i);
	}
	
	public void printMap() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				System.out.printf("%3d ",map[i][j]);
			}
			System.out.println();
		}
	}
	
	class PointUtil {
		
		public Point getUp(Point point) {
			return checkPoint(point.getUp());
		}
		
		public Point getDown(Point point) {
			return checkPoint(point.getDown());
		}
		
		public Point getLef(Point point) {
			return checkPoint(point.getLeft());
		}
		
		public Point getRight(Point point) {
			return checkPoint(point.getRight());
		}
		
		private Point checkPoint(Point point) {
			try {
				getField(point);
			} catch (IndexOutOfBoundsException e) {
				return null;
			}
			return point;
		}
		
		public ArrayList<Point> getNear(Point point) {
			ArrayList<Point> list = new ArrayList<Point>();
			putPoint(list, getUp(point));
			putPoint(list, getDown(point));
			putPoint(list, getLef(point));
			putPoint(list, getRight(point));
			return list;
		}
		
		private void putPoint(ArrayList<Point> list, Point point) {
			if (point != null) {
				list.add(point);
			}
		}
		
	}
	
}
