package de.bomberman.game;

import java.net.MalformedURLException;

import de.bomberman.field.LogicField;
import de.bomberman.items.Wall;
import de.bomberman.ki.KI;
import de.bomberman.main.BombermanMain;
import de.bomberman.main.ScoreVO;
import de.bomberman.main.SettingsVO;
import de.bomberman.sound.SoundBib;

/** Einzelspieler Spiel
 * @author Gruppe 44  
 *
 */
public class SingelPlayerGame extends Game {
	
	/**
	 * Damit lassen sich Konsolen Ausgaben die für die 
	 * Entwicklung Hilfreich sind EIN und AUS schalten.
	 */
	private static final boolean DEBUG = false;
	private int exitFieldX;
	private int exitFieldY;
	private KI ki;
	
	
	/**
	 * regelt die Wahrscheinlichkeit das eine Wand auf einem Feld gesetzt wird.
	 */
	private static final double INVERSE_WALL_CHANCE = 0.8;
	
	/**
	 * regelt die chance das es sich um eine Unzerstörbare Wand handelt
	 */
	private static final double INVERSE_UNBREAKABLE_WALL_CHANCE = 0.5;
	
	/** Konstruktor einen Einzelspieler Spiels
	 * @param vo
	 */
	public SingelPlayerGame(SettingsVO vo) {
		super(vo);
		setPlayer();
		
		setExit();
		setEnemy();
		setWalls();
	}

	/**
	 * setzt die Wände
	 */
	private void setWalls() {
		int height = getPlaygroundHeight();
		int width = getPlaygroundWidth();
		
		for ( int i = 1; i < width - 1; i++) {
			for( int k = 1; k < height - 1; k++) {
				double rnd = Math.random();
				if( rnd > INVERSE_WALL_CHANCE ) {
					double rnd2 = Math.random();
					if( rnd2 > INVERSE_UNBREAKABLE_WALL_CHANCE )
						fields[i][k].setWall(new Wall());
					else
						fields[i][k].setWall(new Wall(true, fields[i][k]));
				}
				
			}
		}
	}

	/**
	 * Setzt die Gegnerische KI aufs Feld
	 */
	private void setEnemy() {
		LogicField tempField = fields[vO.getPlaygroundWidth()-1][vO.getPlaygroundHeight()-1];
		ki = new KI(tempField, 3, 1);
		tempField.setPlayer(ki);
		
	}

	/**
	 *  Erwürfelt die Position des Ausgangs und setzt diese
	 */
	private void setExit() {
		this.exit = true;
		while(true){
			exitFieldX = (int)(Math.random()*(vO.getPlaygroundWidth()));
			exitFieldY = (int)(Math.random()*(vO.getPlaygroundHeight()));
			if(fields[exitFieldX][exitFieldY].getWall() == null) {
				fields[exitFieldX][exitFieldY].setExitField(true);
				break;
			}
		}
	}
	
	/** Stopt das Einzelspieler Spiel, bei erfolgreicher Flucht 
	 * @see de.bomberman.game.Game#stop(de.bomberman.main.ScoreVO)
	 **/
	public void stop(ScoreVO vo) {
		super.stop(vo);
		try {
			SoundBib.playGameOver();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ki.stopListener();
		System.out.println("Trying to catch TimePoints");
		long timePoints = stopWatch.stop();
		
		if ( DEBUG )
			System.out.println("Timepoints: "+timePoints);
		showSingelPlayerResult(timePoints, vo);
	}
	
	/** Zeigt das Ergebnis und die Highscores des Einzelspieler Spiels an
	 * @param timePoints
	 * @param vo
	 */
	private void showSingelPlayerResult(long timePoints, ScoreVO vo) {
		BombermanMain.showSingelPlayerResult(timePoints, vo);
	}
	
	/** Stopt das Einzelspieler Spiel
	 * @see de.bomberman.game.Game#stop(boolean)
	 **/
	@Override
	public void stop(boolean died) {
		super.stop(null);
		ki.stopListener();
		System.out.println("Trying to catch TimePoints");
		long timePoints = stopWatch.stop();
		
		if ( DEBUG )
			System.out.println("Timepoints: "+timePoints);
		showSingelPlayerResult(0, null);

	}
}
