package de.bomberman.main;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;

import de.bomberman.gui.MainGUI;
import de.bomberman.listener.PlayerOneKeyListener;
import de.bomberman.listener.PlayerTwoKeyListener;
import de.bomberman.network.BombermanClient;
import de.bomberman.network.BombermanServer;
import de.bomberman.network.Protocol;
import de.bomberman.playground.Playground;
import de.bomberman.sound.SoundBib;

public class BombermanMain {

	/**
	* @author gruppe44
	* Die Klasse BomberMain initialisiert mindestens eine neue Figur und ihre
	* Position auf dem Spielfeld.
	*/
	public static final boolean INFO = false; 
	public static final boolean DEBUG = false;
	private static boolean server = false;
	private static boolean client = false;
	private static int cntPlayer = 4;
	private static int size = 15;
	private static MainGUI gui;
	public static boolean firstStart;
	public static BufferedOutputStream out;
	
	/**
	 * Steuerung
	 */
	private static PlayerOneKeyListener p1Listener;
	private static PlayerTwoKeyListener p2Listener;
	
	public static void main(String[] args) throws MalformedURLException {
		if( server )
			new BombermanServer().start();
		if( client )
			new BombermanClient().start();
		SoundBib.initSounds();
		SoundBib.playStart();
		init();
	}
	
	/**
	 * Initialisiert ein neues Spielfeld und zwei KeyListener
	 */
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
	
	public static boolean isServer() {
		return server;
	}
	
	public static void setServer(boolean server) {
		BombermanMain.server = server;
		if( server )
			BombermanMain.client = false;
	}
	
	public static boolean isClient() {
		return client;
	}
	
	public static void setClient(boolean client) {
		BombermanMain.client = client;
		if( client )
			BombermanMain.server = false;
	}
}
