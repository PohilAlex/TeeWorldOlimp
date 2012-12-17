package mlsdev.teewold;

import java.util.ArrayList;
public class GameMap {
	int[][] map;
	
	public GameMap(int[][] map) {
		this.map = map;
	}
	
	public void prepareMap(Point startPoint) {
		ArrayList<Point> nextStep = new ArrayList<Point>();
		ArrayList<Point> currStep = new ArrayList<Point>();
		currStep.add(startPoint);
		
		while (currStep.size() > 0) {
			for (Point point : currStep) {
				int value = map[point.y][point.x];
				if (value < 0) {
					value = 1;
				}
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
				}
			}
			currStep = nextStep;
			nextStep = new ArrayList<Point>();
			printMap();
			System.out.println();
		}
	}
	
	public boolean analizeStep(int x, int y, int prevValue) {
		try {
			if (map[y][x] < 0) {
				return false;
			}
			if (map[y][x] == 0 || map[y][x] > prevValue) {
				map[y][x] = ++prevValue;
				return true;
			}
			return false;
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
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
	
}
