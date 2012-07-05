package de.bomberman.game;

import javax.swing.JPanel;

import de.bomberman.items.Wall;
import de.bomberman.main.BombermanMain;
import de.bomberman.main.ScoreVO;
import de.bomberman.main.SettingsVO;

/** MultiplayerGame Objekt
 * @author Gruppe 44 
 *
 */
public class MultiPlayerGame extends Game {

	/**
	 * Damit lassen sich Konsolen Ausgaben die für die 
	 * Entwicklung Hilfreich sind EIN und AUS schalten.
	 */
	private static final boolean DEBUG = false;
	
	
	/**
	 * Regelt die Anzahl der zerstörbaren Wände die gesetzt werden
	 */
	private static final double INVERSE_BREAKABLE_WALL_FACTOR = 0.9;
	int width;
	int height;
	public MultiPlayerGame(SettingsVO vo) {
		super(vo);
		
		width = getPlaygroundWidth();
		height = getPlaygroundHeight();
		setPlayer();
		setWalls();
		}
	
	
	/**
	 * Setzt Unbreakable und Breakable Walls auf die Felder
	 */
	private void setWalls() {
		for(int i = 1; i < fields.length-1; i++) {
			for(int k = 1; k < fields[i].length-1; k++) {
				if( i%2 != 0 && k%2 != 0 ) {
					fields[i][k].setWall(new Wall());
				} else if(!BombermanMain.isClient() && !BombermanMain.isServer()){
					if( Math.random() > INVERSE_BREAKABLE_WALL_FACTOR)
					fields[i][k].setWall(new Wall(true, fields[i][k]));
				}
			}
		}
		
	}
	
	/**
	 * Generiert ein Multiplayer Panel welches das Multiplayer Spiel visualisiert
	 */
	public GamePanel generateGUI() {
		if( DEBUG ) {
			System.out.println("Generating GUI...");
		}
		return new MultiPlayerJPanel(this);
		
	}
	
	/**
	 * Stopt das Spiel und Zeigt die Multiplayer Statistic an
	 **/
	@Override
	public void stop(ScoreVO vo) {
		super.stop(vo);
		BombermanMain.showMultiplayerResult();
	}
}
