package lib;

import java.util.Properties;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.CSIColor;

public class Level {
	private char[][] grid;	
	private int minX = 5;
	private int minY = 3;
	private int x;
	private int y;
	private static char stairDown = '>';
	private static char stairUp = '<';
	private static char wallTile = '#';
	private static char floorTile = '.';
	boolean madeStair = false;
	//private int doorCount = 0;
	//private static char emptyTile = ' ';
	//private static char doorTile = '+';
	//private boolean doorLastTile = false;
	
	public Level(int x, int y) {
		this.grid = new char[y][x];
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void initGrid() {
		for (int y = 0; y < grid.length; y++) {
			for (int x = 0; x < grid[y].length; x++) {
				grid[y][x] = wallTile;
			}
		}
	}

	public void generateCave() {
		int posX = x/2, posY = y/2;
		int upper = y/2 , lower = y/2, left = x/2, right = x/2;
		int convertedCells = 0;
		while (convertedCells < (x*y)/4) {
			if (grid[posY][posX] == wallTile) {
				if (convertedCells == 0) {
					grid[posY][posX] = stairUp;
				}
				if (convertedCells == (x*y)/4-2) {
					grid[posY][posX] = stairDown;
				} else{
					grid[posY][posX] = floorTile;
				}
				convertedCells++;
			}
			double r = Math.random();
            if (r <= 0.25 && posX > 1) {
            	posX--; 
            	if (posX < left) {
            		left = posX;
            	}
            }
            else if (r <= 0.50 && posX < x-2) {
            	posX++; 
            	if (posX > right) {
            		right = posX;
            	} 
            }
            else if (r <= 0.75 && posY > 1) {
            	posY--; 
            	if (posY < upper) {
            		upper = posY;
            	}
            } 
            else if (r <= 1.00 && posY < y-2) {  	
           		posY++; 
           		if (posX > lower) {
            		lower = posY;
            	}
            }
		}
	}
	
	public void generateDungeon(Level section[][]) {
		
		while (true) {
			double r = Math.random();
			if (r > 0.5) {
				int randomX = (int) Math.random() * x;
				for (int y = 0; y < section[y].length; y++) {
					
				}
			} else {
				int randomY = (int) Math.random() * y;
			}
			
			
		}
	}
	
	public void drawGrid(){
		Properties text = new Properties();
		text.setProperty("fontSize","16");
		text.setProperty("font", "Lucida Console");
		
		ConsoleSystemInterface csi = null;
		try{
				csi = new WSwingConsoleInterface("Roguelike Test 3!", text);
		}
		catch (ExceptionInInitializerError eiie) {
			System.out.println("*** Error: Swing Console Box cannot be initialized. Exiting...");
			eiie.printStackTrace();
			System.exit(-1);
		}
		for (int y = 0; y < grid.length; y++) {
			for (int x = 0; x < grid[y].length; x++) {
				csi.print(x,y, grid[y][x], CSIColor.WHITE);
			}
		}
		csi.refresh();
	}
	
	public void modifyTile(int x, int y, char symbol) {
		grid[y][x] = symbol;
	}
	
	public char checkTile(int x, int y) {
		return grid[y][x];
	}
	
	public char[][] getGrid() {
		return grid;
	}
}
