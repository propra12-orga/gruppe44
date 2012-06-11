package de.bomberman.playground;

import de.bomberman.gui.FieldGUI;
import de.bomberman.gui.PlaygroundGUI;
import de.bomberman.items.Bomb;
import de.bomberman.items.PowerUp;
import de.bomberman.items.Wall;
import de.bomberman.main.BombermanMain;
import de.bomberman.main.Player;

public class LogicField {

	private int positionX, positionY;
	private Player player;
	private Bomb bomb;
	private Wall wall;
	private PowerUp powerUp;
	
	
	public LogicField(int positionX, int positionY) {
		super();
		this.positionX = positionX;
		this.positionY = positionY;
	}


	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
		if (player != null) {
				this.player.setField(this);
		}
		FieldGUI[][] fields = PlaygroundGUI.getFields();
		if (BombermanMain.DEBUG)
			System.out.println("firstStart:"+BombermanMain.firstStart);
		if (!BombermanMain.firstStart)
			fields[this.positionX][this.positionY].paintNew();
	}

	public Bomb getBomb() {
		return bomb;
	}

	public void setBomb(Bomb bomb) {
		this.bomb = bomb;
		FieldGUI[][] fields = PlaygroundGUI.getFields();
		if (fields != null)
			fields[this.positionX][this.positionY].paintNew();
	}

	public PowerUp getPowerUp() {
		return powerUp;
	}

	public void setPowerUp(PowerUp powerUp) {
		this.powerUp = powerUp;
	}

	public int getPositionX() {
		return positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public Wall getWall() {
		return wall;
	}

	public void setWall(Wall wall) {
		this.wall = wall;
	}
	
	
	
}
