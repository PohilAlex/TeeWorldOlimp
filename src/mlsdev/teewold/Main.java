package mlsdev.teewold;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;

public class Main {

	/**
	 * @param args
	 */
	public final static String INPUT_FILE_NAME = "input.txt";
	public final static String OUTPUT_FILE_NAME = "output.txt";
	
	public static void main(String[] args) {
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
					switch (line.charAt(j)) {
					case '0':
						map[i][j] = 0;
						break;
					case '1':
						map[i][j] = -1;
						break;
					case 'n':
						map[i][j] = MapLogic.FIRST_START_POSIRION_KEY;
						break;
					case 'x':
						map[i][j] = MapLogic.SECOND_START_POSITION_KEY;
						break;
					case 'R':
						map[i][j] = MapLogic.END_POSITION_KEY;
						break;
					default:
						break;
					}
				}
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		MapLogic logic = new MapLogic(map);
		map = null;
		String result = logic.work();
		try {
			file = new File(OUTPUT_FILE_NAME);
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(result);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	

}
