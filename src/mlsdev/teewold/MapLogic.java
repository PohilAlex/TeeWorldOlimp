package mlsdev.teewold;

import java.util.ArrayList;

//http://www.gamedev.ru/pages/zhekas/articles/Poisk_kratchayshego_puti
//http://www.policyalmanac.org/games/aStarTutorial_rus.htm
public class MapLogic {
	GameMap nMap;
	GameMap xMap;
	
	public MapLogic(int[][] map) {
		nMap = new GameMap(cloneArray(map));
		xMap = new GameMap(cloneArray(map));
	}
	
	public void calculate() {
		Point nStart = nMap.findPoint(-2);
		nMap.prepareMap(nStart);
		System.out.println("########################################");
		Point xStart = xMap.findPoint(-3);
		xMap.prepareMap(xStart);
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
