package de.bomberman.main;

import de.bomberman.gui.MainGUI;
import de.bomberman.listener.PlayerOneKeyListener;
import de.bomberman.listener.PlayerTwoKeyListener;
import de.bomberman.playground.Playground;

public class BombermanMain {

	/**
	 * @param args
	 */
	public static final boolean INFO = true; 
	public static final boolean DEBUG = true;
	private static int cntPlayer = 4;
	private static int size = 15;
	private static MainGUI gui;
	public static boolean firstStart;
	
	private static PlayerOneKeyListener p1Listener;
	private static PlayerTwoKeyListener p2Listener;
	
	public static void main(String[] args) {
		init();
		

	}

	public static void init() {
		firstStart = true;
		System.out.println(""+size);
		new Playground(cntPlayer,size);
		gui = new MainGUI();
		
		//Player 1 Listener
		if( p1Listener != null ) {
			p1Listener.stop();
			gui.removeKeyListener(p1Listener);
		}
		p1Listener = new PlayerOneKeyListener();
		gui.addKeyListener(p1Listener);
		p1Listener.start();
		
		//Player 2 Listener
		if ( p2Listener != null ) {
			p2Listener.stop();
			gui.removeKeyListener(p2Listener);
		}
		PlayerTwoKeyListener p2Listener = new PlayerTwoKeyListener();
		gui.addKeyListener(p2Listener);
		p2Listener.start();
		firstStart = false;
		
	}

	public static int getSize() {
		return size;
	}

	public static void setSize(int size) {
		BombermanMain.size = size;
	}

	public static Object getGUI() {
		return gui;
	}

	public static void setCntPlayer(int cntPlayer) {
		BombermanMain.cntPlayer = cntPlayer;
	}

	public static int getCntPlayer() {
		return cntPlayer;
	}
	
	
}
