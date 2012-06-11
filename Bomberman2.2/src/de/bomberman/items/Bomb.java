package de.bomberman.items;

import de.bomberman.gui.FieldGUI;
import de.bomberman.gui.PlaygroundGUI;
import de.bomberman.main.Player;
import de.bomberman.playground.LogicField;
import de.bomberman.playground.Playground;
import de.bomberman.timer.BombTimer;
import de.bomberman.timer.InterruptBombTimer;

/**
 * @author Giulia Kirstein (2019594)
 *
 */
public class Bomb extends Item {

	//private int positionX, positionY;
	private int range;
	private int time;
	private BombTimer timer; // neu
	
	public Bomb(LogicField field, int radius, int time, Player player) {
		super(player,field);
		this.range = radius;
		this.time = time;
		this.timer = new BombTimer(this); // neu
	}
	
	public void exploding() {
		int x = field.getPositionX();
		int y = field.getPositionY();
		
		//kills the player of the field where the bomb is
		Player tempPlayer = Playground.getFields()[x][y].getPlayer();
		killPlayer(tempPlayer);
		
		//ExplosionCross
		FieldGUI tempFieldGUI;
		tempFieldGUI = PlaygroundGUI.getFields()[x][y];
		tempFieldGUI.setExplosionCross(true);
		
		//kills the players who are top,bottom,right,left from the bomb position in range
		LogicField tempField;
		BombTimer tempBombTimer;
		Bomb tempBomb;
		Wall tempWall;
		boolean noHumpRight = true;
		boolean noHumpDown = true;
		boolean noHumpLeft = true;
		boolean noHumpUp = true;
		
		/*
		 * 	 	-------------> x
		 * 		|
		 * 		|
		 * 		|
		 * 		|
		 * 		v
		 * 		y
		 */
		for (int i = 1; i <= this.range; i++){
			//######## rechts ######################################################
			if ((x+i) < Playground.getSize() && noHumpRight) {
				tempField = Playground.getFields()[x+i][y];
				tempBomb = tempField.getBomb(); // Kettenreaktion!!
				tempWall = tempField.getWall(); // explosion blocking by wall
				if (tempWall != null){
					noHumpRight = false;
				} else { 
					if (tempBomb != null) { // Kettenreaktion!!
						noHumpRight = false;
						new InterruptBombTimer(tempBomb.getTimer()); // Kettenreaktion!!
					} else {
						tempFieldGUI = PlaygroundGUI.getFields()[x+i][y];
						tempFieldGUI.setExplosionHorizontal(true);
						tempPlayer = tempField.getPlayer();
					}
				}
				
				killPlayer(tempPlayer);
			}
			//######### runter #####################################################
			if ((y+i) < Playground.getSize() && noHumpDown) {
				tempField = Playground.getFields()[x][y+i];
				tempBomb = tempField.getBomb(); // Kettenreaktion!!
				tempWall = tempField.getWall(); // explosion blocking by wall
				if (tempWall != null){
					noHumpDown = false;
				} else { 
					if (tempBomb != null) { // Kettenreaktion!!
						noHumpDown = false;
						new InterruptBombTimer(tempBomb.getTimer()); // Kettenreaktion!!
					} else {
						tempFieldGUI = PlaygroundGUI.getFields()[x][y+i];
						tempFieldGUI.setExplosionVertikal(true);
						tempPlayer = tempField.getPlayer();
					}
				}
				
				killPlayer(tempPlayer);
			}
			//########### links ###################################################
			if ((x-i) >= 0 && noHumpLeft) {
				tempField = Playground.getFields()[x-i][y];
				tempBomb = tempField.getBomb(); // Kettenreaktion!!
				tempWall = tempField.getWall(); // explosion blocking by wall
				if (tempWall != null){
					noHumpLeft = false;
				} else { 
					if (tempBomb != null) { // Kettenreaktion!!
						noHumpLeft = false;
						new InterruptBombTimer(tempBomb.getTimer()); // Kettenreaktion!!
					} else {
						tempFieldGUI = PlaygroundGUI.getFields()[x-i][y];
						tempFieldGUI.setExplosionHorizontal(true);
						tempPlayer = tempField.getPlayer();
					}
				}
				
				killPlayer(tempPlayer);
			}
			//############# hoch #################################################
			if ((y-i) >= 0 && noHumpUp) {
				tempField = Playground.getFields()[x][y-i];
				tempBomb = tempField.getBomb(); // Kettenreaktion!!
				tempWall = tempField.getWall(); // explosion blocking by wall
				if (tempWall != null){
					noHumpUp = false;
				} else { 
					if (tempBomb != null) { // Kettenreaktion!!
						noHumpUp = false;
						new InterruptBombTimer(tempBomb.getTimer()); // Kettenreaktion!!
					} else {
						tempFieldGUI = PlaygroundGUI.getFields()[x][y-i];
						tempFieldGUI.setExplosionVertikal(true);
						tempPlayer = tempField.getPlayer();
					}
				}
				
				killPlayer(tempPlayer);
			}
			//################################################################
		}
	}
	// method for killing Player
	private void killPlayer(Player player) {
		if (player != null) {
			player.setDead(true);
			player.addDead();
			if(player != this.player) {
				this.player.addScore();
			} else {
				this.player.subScore();
			}
			System.out.println("Player "+ player.getID() +"  was killed by Player "+this.player.getID());
		}
		
	}

	public int getRadius() {
		return this.range;
	}
	
	public int getTime() {
		return this.time;
	}
	
	public Player getPlayer() {
		return this.player;
	}

	public BombTimer getTimer() {
		return timer;
	}
	
}
	