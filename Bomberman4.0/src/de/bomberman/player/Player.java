package de.bomberman.player;

import de.bomberman.field.LogicField;
import de.bomberman.items.PowerUp;
import de.bomberman.main.BombermanMain;
import de.bomberman.main.ScoreVO;
import de.bomberman.playground.Playground;
import de.bomberman.timer.PowerUpMoreBombs;
import de.bomberman.timer.RespawnTimer;
import de.bomberman.timer.PowerUpFasterTimer;
import de.bomberman.timer.PowerUpKiSlower;

	/**
	 * 
	 * @author Gruppe 44 
	 *
	 */
public class Player{
	
	/**
	 * Damit lassen sich Konsolen Ausgaben die für die 
	 * Entwicklung Hilfreich sind EIN und AUS schalten.
	 */
	private static final boolean DEBUG = false;

	private final int ID;
	
    private LogicField field;
    private boolean isDead;
    private int kills;
    private int deads;
    private String name;
    private ScoreVO scoreVO;
    private PowerUp powerUp;

	private int score;
    
   
    /** Kosntruktor fuer Spieler
     * @param ID
     */
    public Player (int ID){
    	this.name = "Player " + ID;
    	this.ID = ID;
    }
    /**
     * Konstruktor fuer KIs (aufgreufen durch super();)
     */
    public Player(){
    	this.ID = 9;
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
		return kills;
	}

	public int getDeads() {
		return deads;
	}

	public PowerUp getPowerUp() {
		return powerUp;
	}

	/** setzt die Auswirkungen des PlugIns auf den Spieler um
	 * @param powerUp
	 */
	public void setPowerUp(PowerUp powerUp) {
		
		int type = powerUp.getType();
		System.out.println("setPowerUp(), type: "+type);
		switch ( type ) {
		case 1:
			 new PowerUpFasterTimer(this.ID);
			 break;
		case 2:
			new PowerUpMoreBombs(this.ID);
			break;
		case 3:
			new PowerUpKiSlower();
			break;
		}
	}



	/**
	 * fügt in die Statistik einen Tod hinzu und Aktualisiert das ScoreBoard
	 */
	public void addDead() {
		this.deads++;
		scoreVO.setDeads(this.ID, deads);
		if ( DEBUG ) {
			System.out.println("Player "+ this.ID + " dies "+ this.deads);
		}
			BombermanMain.uploadScoreBoard();
	}

	/**
	 * fügt in die Statistik einen Abschuss hinzu und Aktualisiert das ScoreBoard
	 */
	public void addKills() {
		this.kills++;
		this.score++;
		scoreVO.setKills(this.ID, kills );
		scoreVO.setScore(this.ID, score );
		
		if ( DEBUG ) {
			System.out.println("Player "+ this.ID + " score "+ this.kills);
		}
		BombermanMain.uploadScoreBoard();
	}
    
	/**
	 * zieht in die Statistik einen Punkt ab und Aktualisiert das ScoreBoard
	 * ( Selbstmord )
	 */
	public void subScore() {
		this.score--;
		scoreVO.setScore(this.ID, score );
		if ( DEBUG ) {
			System.out.println("Player "+ this.ID + " score "+ this.kills);
		}
		BombermanMain.uploadScoreBoard();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		BombermanMain.uploadScoreBoard();
	}
	public ScoreVO getScoreVO() {
		return scoreVO;
	}
	public void setScoreVO(ScoreVO scoreVO) {
		this.scoreVO = scoreVO;
	}
	public void reset() {
		//do nothing
	}
    
    
    
}