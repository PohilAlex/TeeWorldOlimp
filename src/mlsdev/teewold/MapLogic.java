package mlsdev.teewold;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Currency;
import java.util.HashSet;

//http://www.gamedev.ru/pages/zhekas/articles/Poisk_kratchayshego_puti
//http://www.policyalmanac.org/games/aStarTutorial_rus.htm
public class MapLogic {
	
	public static int FIRST_START_POSIRION_KEY = -2;
	public static int SECOND_START_POSITION_KEY = -3;
	public static int END_POSITION_KEY = -4;
	
	public static String BULLSHIT_KEY = "This is bullshit!";
	public static String WIN_KEY = "nitro wins!";
	
	GameMap nMap;
	GameMap xMap;
	
	public MapLogic(int[][] map) {
		nMap = new GameMap(cloneArray(map));
		xMap = new GameMap(cloneArray(map));
	}
	
	public String work() {
		Point nStart = nMap.findPoint(FIRST_START_POSIRION_KEY);
		Point xStart = xMap.findPoint(SECOND_START_POSITION_KEY);
		Point endPoint = nMap.findPoint(END_POSITION_KEY);
		
		nMap.setStartPoint(nStart);
		nMap.calcDistance();
		
		//Check finish attainable
		if (!nMap.isAttainable(endPoint)) {
			System.out.println(BULLSHIT_KEY);
			return BULLSHIT_KEY;
		}
		xMap.setStartPoint(xStart);
		xMap.calcDistance();
		
		//Check nitro win
		if (nMap.getField(endPoint) < xMap.getField(endPoint) || xMap.getField(endPoint) <= 0) {
			System.out.println(WIN_KEY);
			return WIN_KEY;
		}
		PathNode path = nMap.findMinPath(endPoint);
		if (nMap.getField(endPoint) == xMap.getField(endPoint)) {
			for (PathNode node : path.children) {
				Point point = node.getData();
				if (nMap.getField(point) < xMap.getField(point)) {
					System.out.println(WIN_KEY);
					return WIN_KEY;
				}
			}
		}
		
		//Find cross point
		ArrayList<Point> bestPath = new ArrayList<Point>();
		HashSet<PathNode> currNode = path.getLastLeyer();
		
		while (currNode != null) {
			int xMax = Integer.MIN_VALUE;
			PathNode bestNode = null;
			for (PathNode node : currNode ) {
				int length = xMap.getField(node.getData());
				if (xMax < length) {
					xMax = length;
					bestNode = node;
				}
			}
			bestPath.add(bestNode.getData());
			currNode = bestNode.parents;
		}

		int xMin = Integer.MAX_VALUE;
		Point bestPoint = null;
		for (Point point : bestPath) {
			int xLenght = xMap.getField(point);
			if (nMap.getField(point) >= xLenght && xLenght < xMin) {
				xMin = xLenght;
				bestPoint = point;
			}
		}
		String result = bestPoint.y + " " + bestPoint.x + " " + (nMap.getField(bestPoint) - 1);
		System.out.println(result);
		return result;
	}
	
	public void printLayer(HashSet<PathNode> layer, GameMap map) {
		for (PathNode node : layer) {
			System.out.println(node.getData().y + " " + node.getData().x + " - " + map.getField(node.getData()));
		}
		System.out.println("&&&&&");
	}
	
	public int[][] cloneArray(int[][] array) {
		int rows = array.length;
		int[][] newArray = array.clone();
		for (int row = 0; row < rows; row++) {
			newArray[row] = array[row].clone();
		}
		return newArray;
	}
}
