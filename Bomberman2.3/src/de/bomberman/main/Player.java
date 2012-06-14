package de.bomberman.main;

import de.bomberman.gui.MainGUI;
import de.bomberman.items.PowerUp;
import de.bomberman.playground.LogicField;
import de.bomberman.playground.Playground;
import de.bomberman.timer.RespawnTimer;

public class Player{
	
	private final int ID;
	
    private LogicField field;
    private boolean isDead;
    private int score;
    private int deads;
    private String name;
    private PowerUp powerUp;
    
    //constructor
    public Player (int ID, LogicField field){
    	this.name = "Player " + ID;
    	this.ID = ID;
    	this.field = field;
    }
    
    
    
    // getter and setter
	public LogicField getField() {
		return field;
	}

	public void setField(LogicField field) {
		this.field = field;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
		if(isDead) {
			new RespawnTimer(2, this).start();
			if (this.field != null) {
				field.setPlayer(null);
				this.field = null;
			}
		}
	}

	public int getID() {
		return ID;
	}

	public int getScore() {
		return score;
	}

	public int getDeads() {
		return deads;
	}

	public PowerUp getPowerUp() {
		return powerUp;
	}

	public void setPowerUp(PowerUp powerUp) {
		this.powerUp = powerUp;
	}



	public void addDead() {
		this.deads++;
		if (BombermanMain.INFO) {
			System.out.println("Player "+ this.ID + " dies "+ this.deads);
		}
		MainGUI.uploadScoreBoard();
	}

	public void addScore() {
		this.score++;
		if (BombermanMain.INFO) {
			System.out.println("Player "+ this.ID + " score "+ this.score);
		}
		MainGUI.uploadScoreBoard();
	}
    
	public void subScore() {
		this.score--;
		if (BombermanMain.INFO) {
			System.out.println("Player "+ this.ID + " score "+ this.score);
		}
		MainGUI.uploadScoreBoard();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		MainGUI.uploadScoreBoard();
	}
    
    
    
}