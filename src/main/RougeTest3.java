package main;

import java.util.Properties;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;
import lib.Level;
import lib.Character;

public class RougeTest3 {

	public static void main(String[] args) {
		Properties text = new Properties();
		text.setProperty("fontSize","20");
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
		boolean stop = false;
		int flr = 0;
		Level floor[] = new Level[3];
		floor[0] = new Level(80, 23);
		floor[1] = new Level(80, 23);
		floor[2] = new Level(80, 23);
		Character player = new Character('@', 5);
		floor[0].initGrid();
		floor[0].generateCave();
		floor[1].initGrid();
		floor[1].generateCave();
		floor[2].initGrid();
		floor[2].generateCave();
		player.spawnChar(floor[flr]);
		while(!stop){
			csi.cls();
			for (int y = 0; y < floor[flr].getY(); y++) {
				for (int x = 0; x < floor[flr].getX(); x++) {
					CSIColor colour;
					if (floor[flr].checkTile(x,y) == '>' || floor[flr].checkTile(x,y) == '<') {
						colour = CSIColor.WHEAT;
					} else 	if (floor[flr].checkTile(x,y) == '#') {
						colour = CSIColor.GRAY;
					} else 	if (floor[flr].checkTile(x,y) == '.') {
						colour = CSIColor.SLATE_GRAY;
					} else 	if (floor[flr].checkTile(x,y) == '@') {
						colour = CSIColor.WHITE;
					} else {
						colour = CSIColor.WHITE;
					}
					csi.print(x, y, floor[flr].checkTile(x,y), colour);
				}
			}
			csi.print(0, 24, "Level:" +flr+ " X:" +player.getX()+ " Y:" +player.getY(), CSIColor.WHITE);
			csi.refresh();
//			try {
//				level.drawGrid();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
			CharKey dir = csi.inkey();
			player.moveChar(dir, floor[flr]);
			if(dir.code == CharKey.MORETHAN && player.getTileUnder() == '>' && flr+1 < 3) {
				flr++;
				player = new Character('@', 5);
				player.spawnChar(floor[flr]);
//				player.spawnChar(floor[flr]);
//				for (int y = 0; y < floor[flr].getY(); y++) {
//					for (int x = 0; x < floor[flr].getX(); x++) {
//						csi.print(x, y, floor[flr].checkTile(x,y), CSIColor.WHITE);
//					}
//				}
			}
			if(dir.code == CharKey.LESSTHAN && player.getTileUnder() == '<' && flr-1 > -1) {
				flr--;
				player = new Character('@', 5);
				player.spawnChar(floor[flr]);
			}
			if(dir.code == CharKey.f){
				floor[flr].initGrid();
				floor[flr].generateCave();
				player.spawnChar(floor[flr]);
			}
			if(dir.code == CharKey.q){
				stop = true;
			}
		}
		System.exit(0);
	}

}
