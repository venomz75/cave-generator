package lib;

import net.slashie.libjcsi.CharKey;

public class Character {
	protected int x;
	protected int y;
	protected char symbol;
	protected char tileUnder = '<';
	protected int hp;
	boolean foundSpace = false;
	
	public Character(char symbol, int hp) {
		this.symbol = symbol;
		this.hp = hp;
	}
	
	public char getSymbol() {
		return symbol;
	}
	
	public char getTileUnder() {
		return tileUnder;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getHp() {
		return hp;
	}
	
	public void setTileUnder(char tileUnder) {
		this.tileUnder = tileUnder;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void spawnChar(Object grid) {
		Level level = (Level) grid;
		x = level.getX()/2;
		y = level.getY()/2;
		tileUnder = level.checkTile(x, y);
		level.modifyTile(x, y, symbol);
	}
	
	public void moveChar(CharKey direction, Object grid) {
		Level level = (Level) grid;
		level.modifyTile(x, y, tileUnder);
			if (direction.isUpArrow() && level.checkTile(x, y-1) != '#'){
				y -= 1;
			}

			if (direction.isLeftArrow() && level.checkTile(x-1, y) != '#') {
				x -= 1;
			}

			if (direction.isDownArrow() && level.checkTile(x, y+1) != '#') {
				y += 1;
			}
			
			if (direction.isRightArrow() && level.checkTile(x+1, y) != '#') {
				x += 1;
			}
		tileUnder = level.checkTile(x, y);
		level.modifyTile(x, y, symbol);
	}
	
	public void drawStats() {
		System.out.println("Location: "+x+ "," +y);
		System.out.println("Health: "+hp);
	}
	
	public void decayHealth() {
		hp -= 1;
	}
	
	public int[] findEnemy(char symbol, Object grid) {
		Level level = (Level) grid;
		for (int y = 0; y < level.getY(); y++) {
			for (int x = 0; x < level.getX(); x++) {
				if (level.checkTile(x, y) == symbol) {
					return new int[] {x,y};
				}
			}
		}
		return null;
	}
}
