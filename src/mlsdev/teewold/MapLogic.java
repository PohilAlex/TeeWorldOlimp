package mlsdev.teewold;

import java.util.ArrayList;

//http://www.gamedev.ru/pages/zhekas/articles/Poisk_kratchayshego_puti
//http://www.policyalmanac.org/games/aStarTutorial_rus.htm
public class MapLogic {
	int[][] map;
	
	public MapLogic(int[][] map) {
		this.map = map;
	}
	
	public void calculate() {
		
	}
	
	public void prepareMap() {
		ArrayList<Point> nextStep = new ArrayList<Point>();
		ArrayList<Point> currStep = new ArrayList<Point>();
		currStep.add(findStartPoint());
		
		while (currStep.size() > 0) {
			for (Point point : currStep) {
				int value = map[point.y][point.x];
				if (analizeStep(point.x - 1, point.y , value)) {
					nextStep.add(new Point(point.x - 1, point.y));
				}
				if (analizeStep(point.x + 1, point.y, value)) {
					nextStep.add(new Point(point.x - 1, point.y));
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
	
	public void findMinWay() {
		findEndPoint();
		
		//ArrayList<ArrayList<Point>>;
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
	
	
	public Point findStartPoint() {
		return findPoint(1);
	}
	
	public Point findEndPoint() {
		return findPoint(-2);
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
	
	class Point {
		int x;
		int y;
		
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	
}
