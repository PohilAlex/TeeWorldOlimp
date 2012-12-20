package mlsdev.teewold;

import java.util.ArrayList;
import java.util.HashSet;

//http://www.gamedev.ru/pages/zhekas/articles/Poisk_kratchayshego_puti
//http://www.policyalmanac.org/games/aStarTutorial_rus.htm
public class MapLogic {
	
	public static int FIRST_START_POSIRION_KEY = -2;
	public static int SECOND_START_POSITION_KEY = -3;
	public static int END_POSITION_KEY = -4;
	
	GameMap nMap;
	GameMap xMap;
	
	public MapLogic(int[][] map) {
		nMap = new GameMap(cloneArray(map));
		xMap = new GameMap(cloneArray(map));
	}
	
	public void work() {
		Point nStart = nMap.findPoint(FIRST_START_POSIRION_KEY);
		Point xStart = xMap.findPoint(SECOND_START_POSITION_KEY);
		Point endPoint = nMap.findPoint(END_POSITION_KEY);
		
		nMap.setStartPoint(nStart);
		nMap.calcDistance();
		
		if (!nMap.isAttainable(endPoint)) {
			System.out.println("This is bullshit!");
			return;
		}
		System.out.println("########################################");
		xMap.setStartPoint(xStart);
		xMap.calcDistance();
		
		PathNode path = nMap.findMinPath(endPoint);
		HashSet<PathNode> currNode = new HashSet<PathNode>();
		HashSet<PathNode> goodNode = path.getLastLeyer();
		
		boolean isNitroWin = false;
		while (goodNode.size() > 0 && !isNitroWin) {
			currNode.clear();
			for (PathNode node : goodNode) {
				if (node.parent == null) {
					System.out.println("nitro wins!");
					isNitroWin = true;
				} else {
					currNode.add(node.parent);
				}
			}
			goodNode.clear();
			for (PathNode node : currNode ) {
				if (nMap.getField(node.getData()) < xMap.getField(node.getData())) {
					goodNode.add(node);
				}
			}
			printLayer(goodNode, nMap);
		}
		if (!isNitroWin) {
			System.out.println("/////////////");
			printLayer(currNode, nMap);
		}
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
