package de.bomberman.game;

import javax.swing.JPanel;

import de.bomberman.field.LogicField;
import de.bomberman.main.BombermanMain;
import de.bomberman.main.ScoreVO;
import de.bomberman.main.SettingsVO;
import de.bomberman.player.Player;
import de.bomberman.player.PlayerOneKeyListener;
import de.bomberman.player.PlayerOneKeyListenerServer;
import de.bomberman.player.PlayerTwoKeyListener;
import de.bomberman.player.PlayerTwoKeyListenerServer;
import de.bomberman.timer.StopWatch;

/**
 * Mutterklasse fŸr SingelPlayerGame und MultiPlayerGame
 * @author Gruppe 44
 * 
 */
public class Game extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -715901455727481114L;
	
	private static final boolean DEBUG = true;
	protected int playerCount;
	private int width;
	private int height;
	protected Player[] players;
	protected LogicField[][] fields;
	protected SettingsVO vO;
	protected boolean exit;
	protected StopWatch stopWatch;
	protected PlayerOneKeyListener p1Listener;
	protected PlayerTwoKeyListener p2listener;
	protected ScoreVO scoreVO;
	
	/**
	 * Setzt Grundsätzliche Attribute, für jeden Spieltyp, aus einem Settings Value Object
	 */
	public Game(SettingsVO vo) {
		this.vO = vo;
		this.playerCount = vo.getPlayerCnt();
		this.width = vo.getPlaygroundWidth();
		this.height = vo.getPlaygroundHeight();
		scoreVO = new ScoreVO();
		//player
		players = new Player[playerCount];
		for (int i = 0; i <  this.playerCount ; i++) {
			if( DEBUG ) {
				System.out.println("Spieler "+(i+1)+" erstellt");
			}
			players[i] = new Player(i+1);
			players[i].setScoreVO(scoreVO);
		}
		
		//fields
		fields = new LogicField[width][height];
		for (int i = 0; i < width; i++) {
			for (int k = 0; k < height; k++) {
						
				if( DEBUG )
					System.out.println("Feld "+i+"/"+k+" erstellt");
				fields[i][k] = new LogicField(i, k);
			}
		}
		

	}


	public int getPlaygroundWidth() {
		return this.width;
	}

	public int getPlaygroundHeight() {
		return this.height;
	}
	
	public LogicField[][] getFields(){
		return this.fields;
	}

	/** generiert ein Panel mit dem Spielfeld, als Referenze 
	 * dienen die Attriubte des Game Objects
	 * @return
	 */
	public GamePanel generateGUI() {
		return new GamePanel(this);
	}


	public int getPlayerCount() {
		return playerCount;
	}
	public Player[] getPlayers() {
		return players;
	}


	/**
	 * Startet das Spiel, Listener werden geladen/gesetzt
	 * StopUhr gestartet
	 */
	public void start() {
		if(BombermanMain.isClient()) {
			p1Listener = new PlayerOneKeyListener();
			p2listener = new PlayerTwoKeyListener();
		} else if (BombermanMain.isServer()){
			p1Listener = new PlayerOneKeyListenerServer();
			p2listener = new PlayerTwoKeyListenerServer();
		} else {
			p1Listener = new PlayerOneKeyListener();
			p2listener = new PlayerTwoKeyListenerServer();
		}
		p1Listener.start();
		p2listener.start();
		BombermanMain.addKeyListener(p1Listener);
		BombermanMain.addKeyListener(p2listener);
		stopWatch = new StopWatch(180000);
		
	}
	
	protected void setPlayer() {
		switch(playerCount) {
		case 4:
			fields[width - 1][height - 1].setPlayer(players[3]);
		case 3:
			fields[0][height - 1].setPlayer(players[2]);
		case 2:
			fields[width - 1][0].setPlayer(players[1]);
		case 1:
			fields[0][0].setPlayer(players[0]);
		}
	}
	
	/** stopt das Spiel
	 * @param vo
	 */
	public void stop(ScoreVO vo) {
		System.out.println("Game - Stop");
		p1Listener.interrupt();
		p2listener.interrupt();
	}

	public ScoreVO getScoreVO() {
		return scoreVO;
	}


	public void stop(boolean died) {
		//do nothing
	}

	
}
