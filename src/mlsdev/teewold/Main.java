package mlsdev.teewold;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main {

	/**
	 * @param args
	 */
	public final static String INPUT_FILE_NAME = "input.txt";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hellow world");
		File file = new File(INPUT_FILE_NAME);
		int width, height;
		int[][] map = new int[0][0];
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String param[] = reader.readLine().split(" ");
			height = Integer.parseInt(param[0]);
			width = Integer.parseInt(param[1]);
			map = new int[height][width];
			for (int i = 0; i < height; i++) {
				String line = reader.readLine();
				for (int j = 0; j < line.length(); j++) {
					//System.out.print(line.charAt(j));
					switch (line.charAt(j)) {
					case '0':
						map[i][j] = 0;
						break;
					case '1':
						map[i][j] = -1;
						break;
					case 'n':
						map[i][j] = -2;
						break;
					case 'x':
						map[i][j] = -3;
						break;
					case 'R':
						map[i][j] = -4;
						break;
					default:
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		MapLogic logic = new MapLogic(map);
		map = null;
		logic.work();
		//logic.printMap();
		//System.out.println();
		//logic.prepareMap();
		//logic.printMap();
		
	}
	
	

}
