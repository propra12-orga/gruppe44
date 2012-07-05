package de.bomberman.main;

import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.net.MalformedURLException;

import javax.swing.JPanel;

import de.bomberman.field.FieldGUI;
import de.bomberman.field.LogicField;
import de.bomberman.game.Game;
import de.bomberman.game.GamePanel;
import de.bomberman.game.MultiPlayerGame;
import de.bomberman.game.SingelPlayerGame;
import de.bomberman.main.gui.MainGUI;
import de.bomberman.network.BombermanClient;
import de.bomberman.network.BombermanServer;
import de.bomberman.player.Player;
import de.bomberman.result.MPResultPanel;
import de.bomberman.result.ResultListener;
import de.bomberman.result.ResultPanel;
import de.bomberman.sound.SoundBib;
import de.bomberman.startscreen.gui.NetworkPanel;
import de.bomberman.startscreen.gui.SettingsPanel;
import de.bomberman.startscreen.gui.StartscreenGUI;
import de.bomberman.startscreen.listener.NetworkPanelKeyListener;
import de.bomberman.startscreen.listener.StartScreenKeyListener;
import de.bomberman.timer.MultiPlayerTimer;
import de.bomberman.timer.WaitForEnter;

	/**
	 * 
	 * @author Gruppe 44 
	 *
	 */
public class BombermanMain {
	private static boolean server = false;
	private static boolean client = false;
	private static final boolean DEBUG = false;
	private static MainGUI gui;
	private static StartScreenKeyListener scl;
	private static NetworkPanelKeyListener npl;
	private static JPanel mainPanel;
	private static GamePanel gamePanel;
	private static Game game;
	public static final int FIELD_SIZE = 30;
	private static Thread timer;
	private static SettingsVO settingsVo;
	private static String gameType;
	private static KeyListener keyListener;
	private static WaitForEnter wfe;
	private static boolean started = false;
	private static int MPWidth = 31;
	private static int MPHeight = 21;
	private static StartScreenKeyListener sclSave;
	private static boolean firstStart =true;
	
	/** Einsteigspunkt
	 * @param args
	 */
	public static void main(String[] args)  {
		init();
		startMenu();
	}

	/**
	 * Startet das Startmenü (Auswahl zwichen Spieltypen etc.)
	 */
	private static void startMenu() {
		System.out.println("StartMenu");
		mainPanel = new StartscreenGUI(gui);
		gui.add(mainPanel); 
		scl = new StartScreenKeyListener((StartscreenGUI) mainPanel);
		scl.setActive(true);
		gui.addKeyListener(scl);
		gui.repaint();
		
	}

	/**
	 *  Initialisiert das Frame (Contaier für die GUI)
	 */
	private static void init() {
		
		gui = new MainGUI("Bomberman");
		Dimension screenDim = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		gui.setSize(screenDim.width, screenDim.height);
		gui.setVisible(true);
	}
	
	/**
	 * Startet ein Multiplayer Spiel
	 */
	public static void startMulitplayer() {
		gameType = "Multiplayer";
		settingsVo = new SettingsVO(2, MPWidth, MPHeight);
		if( DEBUG )
			System.out.println("Starting Mulitplayer..");
		scl.interrupt();
		gui.remove(mainPanel);
		game = new MultiPlayerGame(settingsVo);
		gamePanel = game.generateGUI();
		gui.add(gamePanel);
		gui.repaint();
		game.start();
		try {
			SoundBib.initSounds();
			SoundBib.playStart();
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		}
		timer = new MultiPlayerTimer(180);
	}
	
	/**
	 * Startet ein Einzelspieler Spiel
	 */
	public static void startSingelPlayer() {
		gameType = "Singelplayer";
		settingsVo = new SettingsVO(1, 20, 15);
		if( DEBUG )
			System.out.println("Starting Singelplayer..");
		scl.interrupt();
		gui.remove(mainPanel);
		game = new SingelPlayerGame(settingsVo);
		gamePanel = game.generateGUI();
		gui.add(gamePanel);
		gui.repaint();
		game.start();
		try {
			SoundBib.initSounds();
			SoundBib.playStart();
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		}
	}
	
	public static int getPlaygroundWidth() {
		return settingsVo.getPlaygroundWidth();
	}
	
	public static int getPlaygroundHeight() {
		return settingsVo.getPlaygroundHeight();
	}
	
	public static LogicField[][] getFields() {
		return game.getFields();
	}
	
	public static int getPlayerCnt() {
		return game.getPlayerCount();
	}

	public static Player[] getPlayers() {
		return game.getPlayers();
	}
	
	public static void addKeyListener(KeyListener listener) {
		gui.addKeyListener(listener);
	}
	
	public static FieldGUI[][] getGuiFields() {
		if(gamePanel != null)
			return gamePanel.getFields();
		
		return null;
	}

	/** Kontrolliert ob eine Spiel Visualisierung vorhanden ist
	 * @return
	 */
	public static boolean exitstGuiFields() {
		if( gamePanel == null)
			return false;
		else
			return true;
	}

	/**
	 *  lässt die Statistik aktualisieren
	 */
	public static void uploadScoreBoard() {
		gamePanel.uploadScoreBoard();
	}

	/** Stopt das laufende Spiel
	 * @param vo
	 */
	public static void stopGame(ScoreVO vo) {
		System.out.println("BombermanMain - Stop Game");
		game.stop(vo);
		gamePanel = null;
	}
	
	/** Stopt das laufende Spiel wenn der Spieler stirbt
	 * @param died
	 */
	public static void stopGame(boolean died) {
		System.out.println("BombermanMain - Stop Game");
		game.stop(died);
		gamePanel = null;
	}

	/** Lässt das Ergebnis anzeigen für Einzelspieler Spiele
	 * @param timePoints
	 * @param vo
	 */
	public static void showSingelPlayerResult(long timePoints, ScoreVO vo) {
		if( gamePanel != null )
			gui.remove(gamePanel);
		if( mainPanel != null )
			gui.remove(mainPanel);
		mainPanel = new ResultPanel(timePoints, vo);
		gui.add(mainPanel);
		gui.repaint();
		addKeyListener(new ResultListener());
	}

	/**
	 * Lässt die Multiplayer Statistik anzeigen (Endstand)
	 */
	public static void showMultiplayerResult() {
		gui.remove(gamePanel);
		mainPanel = new MPResultPanel(game);
		gui.add(mainPanel);
		gui.repaint();
		addKeyListener(new ResultListener());
	}
	
	/**
	 * Entfernt die "Result Panel" vom Frame
	 */
	public static void resetResultView() {
		gui.remove(mainPanel);
		gui.repaint();
		startMenu();
	}

	public static String getGametype() {
		return gameType;
	}

	public static boolean isServer() {
		return server;
	}
	
	public static boolean isClient() {
		// TODO Auto-generated method stub
		return client;
	}

	/**
	 * lässt das NetworkPanel anzeigen, (Wahl zwichen Server und Client)
	 */
	public static void startNetWorkPanel() {
		gui.removeKeyListener(scl);
		gui.remove(mainPanel);
		scl.interrupt();
		mainPanel = new NetworkPanel(gui);
		gui.add(mainPanel);
		npl = new NetworkPanelKeyListener((NetworkPanel) mainPanel);
		gui.addKeyListener(npl);
		mainPanel.repaint();
		gui.repaint();
	}
	
	/**
	 * Startet ein Spiel als Server
	 */
	public static void startAsServer() {
		if(!started) {
			started = true;
			server = true;
			client = false;
		BombermanServer server = new BombermanServer();
		server.start();
		startMulitplayer();
		}
		/*if ( DEBUG ) {
			System.out.println("Starting as Server...");
		}
		server = true;
		client = false;

		//((NetworkPanel)mainPanel).showSettings();
		
		gui.removeKeyListener(npl);
		//npl.stop();
		//wfe = new WaitForEnter();
		//wfe.start();
		showWaiting();*/
	}
	

	/**
	 * Startet ein Spiel als Client
	 */
	public static void startAsClient() {
		if (!started) {
			started = true;
			server = false;
			client = true;
			BombermanClient client = new BombermanClient();
			client.start();
			startMulitplayer();
		}
	}
		/*if ( DEBUG ) {
			System.out.println("Starting as Client...");
		}
		client = true;
		server = false;
		
		//((NetworkPanel)mainPanel).showSettingsClient();
		
		gui.removeKeyListener(npl);
		//npl.stop();
		//wfe = new WaitForEnter();
		//wfe.start();
		tryConnecting();
	}*/

	public static void setKeyListener(KeyListener listener) {
		((NetworkPanel)mainPanel).setKeyListener(listener);
	}
	
	public static void unKeyListener() {
		gui.removeKeyListener(keyListener);
	}

	/**
	 * soll anzeigen das das der Server auf einen Client wartet
	 * Kein Inhalt der Aktuellen Version (BUGGY)
	 */
	public static void showWaiting() {
		
		((NetworkPanel)mainPanel).showWaiting();
		mainPanel.repaint();
		BombermanServer server = new BombermanServer();
		server.start();
	}
	
	/**
	 * Kein Inhalt der Aktuellen Version
	 */
	public static void tryConnecting() {
		System.out.println("tryConnecting");
		BombermanClient client = new BombermanClient();
		client.start();
		startMulitplayer();
	}

	public static void showSettings() {
		if( gamePanel != null )
			gui.remove(gamePanel);
		if( mainPanel != null )
			gui.remove(mainPanel);
		mainPanel = new SettingsPanel();
		gui.add(mainPanel);
		gui.repaint();
		addKeyListener(new ResultListener());
	}

	public static void setSize(int width, int height) {
		MPWidth = width;
		MPHeight = height;
		if( gamePanel != null )
			gui.remove(gamePanel);
		if( mainPanel != null )
			gui.remove(mainPanel);
		startMenu();
		
	}
}
