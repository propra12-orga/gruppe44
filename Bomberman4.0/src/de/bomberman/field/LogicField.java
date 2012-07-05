package de.bomberman.field;

import java.net.MalformedURLException;

import de.bomberman.items.Bomb;
import de.bomberman.items.PowerUp;
import de.bomberman.items.Wall;
import de.bomberman.main.BombermanMain;
import de.bomberman.player.Player;
import de.bomberman.sound.SoundBib;

/** Logische Objecte der Felder. Beinhaltet den Status des Feldes und Reguliert bei Veränderungen dieser 

 * @author Gruppe 44
 */

public class LogicField extends Thread {

	/**
	 * Zaehlt die Anzahl der PowerUps auf alles Spielfeldern,
	 * damit die Anzahl der PowerUps begrenzt ist.
	 */
	private static int existingPowerUp;
	
	/**
	 * Beinhaltet die Maximale Anzahl für PowerUps im Spiel
	 */
	private static final int MAX_POWER_UPS = 3;
	
	/**
	 *  "Entwicklungs Variable", soll die möglichkeit geben den "Geruch"
	 *  zu Visualisieren an dem sich die KI orientiert.
	 */
	private static final boolean showSmell = false;
	
	/**
	 * Damit lassen sich Konsolen Ausgaben die für die 
	 * Entwicklung Hilfreich sind EIN und AUS schalten.
	 */
	private static final boolean DEBUG = true;
	
	
	/**
	 * Position des Feldes
	 */
	private int positionX, positionY;
	
	
	/**
	 * Player, KI die sich auf dem Feld befindet oder null
	 * wenn sich keine KI oder Player auf dem Feld befindet.
	 */
	private Player player;
	
	
	/**
	 * Bomb Object das sich auf dem Feld befindet, oder null
	 * wenn keins auf diesem Feld
	 */
	private Bomb bomb;
	
	
	/**
	 * Wall Objekt das sich auf dem Feld befindet, oder null
	 * wenn keins auf diesem Feld
	 */
	private Wall wall;
	
	
	/**
	 * PowerUp Object das sich auf dem Feld befindet, oder null
	 * wenn sich keins auf dem Feld befindet
	 */
	private PowerUp powerUp;
	
	
	/**
	 * true wenn sich auf diesem Feld der Ausgang befindet SingelPlayer
	 */
	private boolean exitField;
	
	
	/**
	 * Straerke des Geruchs des Players (Entfernung zum Spieler),
	 * 7 sehr sehr nah (das Feld des auf dem sich der Spieler befindet)
	 * 0 kein geruch wahrzunehmen.
	 */
	private int smell;
	
	
	/** Konstruktor des LogicFields
	 * @param positionX
	 * @param positionY
	 */
	public LogicField(int positionX, int positionY) {
		super();
		existingPowerUp = 0;
		this.exitField = false;
		this.positionX = positionX;
		this.positionY = positionY;
		
		start();
	}


	public Player getPlayer() {
		return player;
	}

	/** Regelt die Logic wenn ein Spieler auf ein bestimmtest Feld gesetzt wird 
	 * @param player
	 */
	public void setPlayer(Player player) {
		this.player = player;
		if (player != null) { 
				this.player.setField(this);
				
				/* wenn ein PowerUp auf dem Feld ist
				*  PowerUp Effekt auf Spieler übertragen. 
				*/
				if(this.powerUp != null) {
					player.setPowerUp(this.powerUp);
					try {
						SoundBib.playPowerUp();
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					this.setPowerUp(null);
					existingPowerUp--;
				}
					
				int heigth = BombermanMain.getPlaygroundHeight();
				int width = BombermanMain.getPlaygroundWidth();
				
				try{
					LogicField[][] fields = BombermanMain.getFields();
					
					
					if(player.getID() != 9 && BombermanMain.getGametype() == "Singelplayer") {
						
						unsetSmell();
						this.setSmell(7);
						
						// setzt das Smell Kreuz
						for(int i = 1; i < 7; i++) {
							if( (positionX+i) < (width) )
								fields[this.positionX+i][this.positionY].setSmell(7-i);
							if( (positionX-i) > 0 )
								fields[this.positionX-i][this.positionY].setSmell(7-i);
							if( (positionY+i) < (heigth) )
								fields[this.positionX][this.positionY+i].setSmell(7-i);
							if( (positionY-i) > 0 )
								fields[this.positionX][this.positionY-i].setSmell(7-i);
						}
					}
				} catch (Exception e) {
				}
		}
		
		//Feld neu zeichnen
		if (BombermanMain.exitstGuiFields()) {
			FieldGUI[][] gfields = BombermanMain.getGuiFields();
			FieldGUI tempField = gfields[this.positionX][this.positionY];
			tempField.paintNew();
		}
		// wenn das ExitField erreicht wurde, Spiel beenden
		if( this.exitField && player.getID() != 9) {
			BombermanMain.stopGame(player.getScoreVO());
		}
	}

	/**
	 *  stellt die Smell werte für alle Felder auf 0
	 */
	private void unsetSmell() {
		int width = BombermanMain.getPlaygroundWidth();
		int height = BombermanMain.getPlaygroundHeight();
		LogicField fields[][] =BombermanMain.getFields();
		for(int i = 0; i < width ; i++) {
			for(int k = 0; k < height; k++) {
				fields[i][k].setSmell(0);
			}
		}
		
	}


	public Bomb getBomb() {
		return bomb;
	}

	public void setBomb(Bomb bomb) {
		this.bomb = bomb;
		FieldGUI[][] fields = BombermanMain.getGuiFields();
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


	/** Setzt eine neue Bombe auf das Feld, die Bombe startet den Explosions Timer
	 * @param field
	 * @param bombRadius
	 * @param bombExploTime
	 * @param player
	 */
	public void setBomb(LogicField field, int bombRadius, int bombExploTime,
			Player player) {
		if(this.bomb == null)
			setBomb(new Bomb(field, bombRadius, bombExploTime, player));
		
	}
	public void setExitField(boolean b) {
		this.exitField = b;
	}

	public boolean isExitField() {
		return exitField;
	}


	public int getSmell() {
		return smell;
	}


	public void setSmell(int smell) {
		this.smell = smell;
		
		if( showSmell ) {
			FieldGUI[][] fields = BombermanMain.getGuiFields();
			if (fields != null)
				fields[this.positionX][this.positionY].paintNew();
		}
		
	}
	
	/**
	 * Es wird alle 2 sekunden nach einen neuen PowerUp gewürfelt
	 * wenn weniger PowerUps als erlaubt im Spiel sind und keine Unbreakable Wall
	 * Object auf dem Feld ist.
	 */
	public void run() {
		Thread t = Thread.currentThread();
		while( true ) {
			
			if( this.wall != null) {
				if(!this.wall.isBreakable()) {
					if( DEBUG ) {
						System.out.println("close Thread because of unbreakable wall, PowerUp");
					}
					break;
				}
			}
			try {
				t.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if( powerUp == null && existingPowerUp < MAX_POWER_UPS )
				powerUp = rollsPowerUp();
			if ( powerUp != null) {
				if(BombermanMain.exitstGuiFields()) {
					FieldGUI[][] gfields = BombermanMain.getGuiFields();
					FieldGUI tempField = gfields[this.positionX][this.positionY];
					tempField.paintNew();
				}
			}
		}
	}


	/** Ermittelt per Zufall ob ein PowerUp erstellt wird oder nicht und gibt dieses zurück
	 * @return
	 */
	private PowerUp rollsPowerUp() {
		double rnd = Math.random();
		//System.out.println(""+rnd);
		if ( rnd > 0.999 ) {
			existingPowerUp++;
			return new PowerUp(this);
		}
		return null;
	}
	
	
}
