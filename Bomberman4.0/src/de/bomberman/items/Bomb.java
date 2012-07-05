package de.bomberman.items;

import java.net.MalformedURLException;

import de.bomberman.field.FieldGUI;

import de.bomberman.field.LogicField;
//import de.bomberman.gui.FieldGUI;
//import de.bomberman.gui.PlaygroundGUI;
import de.bomberman.main.BombermanMain;
import de.bomberman.player.Player;
import de.bomberman.sound.SoundBib;
import de.bomberman.timer.BombTimer;
import de.bomberman.timer.InterruptBombTimer;

/** Das Bomben Objekt
 * @author Giulia Kirstein (2019594)
 *
 */
public class Bomb extends Item {

	//private int positionX, positionY;
	private int range;
	private int time;
	private BombTimer timer; // neu
	
	/** Konstruktor der Bomben
	 * @param field
	 * @param radius
	 * @param time
	 * @param player
	 */
	public Bomb(LogicField field, int radius, int time, Player player) {
		super(player,field);
		this.range = radius;
		this.time = time;
		this.timer = new BombTimer(this); // neu
	}
	
	/**
	 * Lässt die Bombe Explodieren und regelt die Auswirkungen.
	 */
	public void exploding() {
		int width = BombermanMain.getPlaygroundWidth();
		int height = BombermanMain.getPlaygroundHeight();
		int x = field.getPositionX();
		int y = field.getPositionY();
		try {
			SoundBib.playExplosion();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		//kills the player of the field where the bomb is
		Player tempPlayer = BombermanMain.getFields()[x][y].getPlayer();
		killPlayer(tempPlayer);
		
		//ExplosionCross
		FieldGUI tempFieldGUI;
		tempFieldGUI = BombermanMain.getGuiFields()[x][y];
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
			if ((x+i) < width  && noHumpRight) {
				
				tempField = BombermanMain.getFields()[x+i][y];
				checkForKI(tempField);
				tempBomb = tempField.getBomb(); // Kettenreaktion!!
				tempWall = tempField.getWall(); // explosion blocking by wall
				if (tempWall != null){
					if( tempWall.isBreakable())
						tempWall.subPower();
					noHumpRight = false;
				} else { 
					if (tempBomb != null) { // Kettenreaktion!!
						noHumpRight = false;
						new InterruptBombTimer(tempBomb.getTimer()); // Kettenreaktion!!
					} else {
						tempFieldGUI = BombermanMain.getGuiFields()[x+i][y];
						tempFieldGUI.setExplosionHorizontal(true);
						tempPlayer = tempField.getPlayer();
					}
				}
				
				killPlayer(tempPlayer);
			}
			//######### runter #####################################################
			if ((y+i) < height && noHumpDown) {
				tempField = BombermanMain.getFields()[x][y+i];
				checkForKI(tempField);
				tempBomb = tempField.getBomb(); // Kettenreaktion!!
				tempWall = tempField.getWall(); // explosion blocking by wall
				if (tempWall != null){
					if( tempWall.isBreakable() )
						tempWall.subPower();
					noHumpDown = false;
				} else { 
					if (tempBomb != null) { // Kettenreaktion!!
						noHumpDown = false;
						new InterruptBombTimer(tempBomb.getTimer()); // Kettenreaktion!!
					} else {
						tempFieldGUI = BombermanMain.getGuiFields()[x][y+i];
						tempFieldGUI.setExplosionVertikal(true);
						tempPlayer = tempField.getPlayer();
					}
				}
				
				killPlayer(tempPlayer);
			}
			//########### links ###################################################
			if ((x-i) >= 0 && noHumpLeft) {
				tempField = BombermanMain.getFields()[x-i][y];
				checkForKI(tempField);
				tempBomb = tempField.getBomb(); // Kettenreaktion!!
				tempWall = tempField.getWall(); // explosion blocking by wall
				if (tempWall != null){
					if( tempWall.isBreakable())
						tempWall.subPower();
					noHumpLeft = false;
				} else { 
					if (tempBomb != null) { // Kettenreaktion!!
						noHumpLeft = false;
						new InterruptBombTimer(tempBomb.getTimer()); // Kettenreaktion!!
					} else {
						tempFieldGUI = BombermanMain.getGuiFields()[x-i][y];
						tempFieldGUI.setExplosionHorizontal(true);
						tempPlayer = tempField.getPlayer();
					}
				}
				
				killPlayer(tempPlayer);
			}
			//############# hoch #################################################
			if ((y-i) >= 0 && noHumpUp) {
				tempField = BombermanMain.getFields()[x][y-i];
				checkForKI(tempField);
				tempBomb = tempField.getBomb(); // Kettenreaktion!!
				tempWall = tempField.getWall(); // explosion blocking by wall
				if (tempWall != null){
					if( tempWall.isBreakable())
						tempWall.subPower();
					noHumpUp = false;
				} else { 
					if (tempBomb != null) { // Kettenreaktion!!
						noHumpUp = false;
						new InterruptBombTimer(tempBomb.getTimer()); // Kettenreaktion!!
					} else {
						tempFieldGUI = BombermanMain.getGuiFields()[x][y-i];
						tempFieldGUI.setExplosionVertikal(true);
						tempPlayer = tempField.getPlayer();
					}
				}
				
				killPlayer(tempPlayer);
			}
			//################################################################
		}
	}
	/** Sucht nach eine KI auf dem Feld un setzt sie zurück zum Ausgangs Punkt
	 * @param tempField
	 */
	private void checkForKI(LogicField tempField) {
		Player tempPlayer = tempField.getPlayer();
		System.out.println("Check for Enemy!");
		if ( tempPlayer != null ) {
			if( tempPlayer.getID() == 9) {
				this.player.getScoreVO().addScore(0);
				System.out.println("reset Enemy!");
				tempPlayer.reset();
			}
		}
		
	}

	/** Regelt nach dem ein getroffener Spieler detectiert wurde, 
	 * das geschehen um ihn, und setzt die neue Punktzahl
	 * @param player
	 */
	private void killPlayer(Player player) {
		if (player != null) {
			if(player.getID() != 9) {
				player.setDead(true);
				player.addDead();
				if(player != this.player) {
					this.player.addKills();
				} else {
					this.player.subScore();
				}
				System.out.println("Player "+ player.getID() +"  was killed by Player "+this.player.getID());
			}
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
	