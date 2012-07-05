package de.bomberman.network;

import java.io.BufferedInputStream;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import de.bomberman.main.BombermanMain;
import de.bomberman.player.PlayerTwoKeyListener;
import de.bomberman.player.PlayerTwoKeyListenerServer;


/**Regelt die Kommunikation des Servers mit dem Client
 * @author Gruppe 44 
 *
 */
public class BombermanServer implements Runnable {

	private static Socket socket;
	private static ServerSocket ssocket;
	private static BufferedOutputStream out;
	private static final int port = 4000;
	

	/**
	 * Startet den Verbindungs Aufbau des Servers.
	 * Laucht auf dem port und wartet auf einen Client der Conected
	 */
	public void start() {
		
		try {
			ssocket = new ServerSocket(port);
			System.out.println("Server wartet auf Client!");
			socket = ssocket.accept();
			new Thread(this).start();
			
			System.out.println("Client "+socket.getRemoteSocketAddress()+" with the IP "+socket.getLocalAddress()+" connect on Port "+socket.getLocalPort());
			out = new BufferedOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * stoppt die Verbindung
	 */
	public static void stop() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** teilt dem Client die Aktionen des Servers mit
	 * @param i
	 */
	public static void submit(int i) {
		//String protocolString = Protocol.getProtocolString(player, actionCode, xPos, yPos, 0);
		try {
			out.write(i);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	 /** Laucht auf Mitteilungen des Client und gibt diese an den KeyListener weiter 
	 * der diese dann verarbeitet
	 * @see java.lang.Runnable#run()
	 **/
	@Override
	public void run() {
		System.out.println("run");
		BufferedInputStream in = null;
		try {
			in = new BufferedInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			for(int i =0 ; ( i = in.read() ) != -1;){
				//char chr = (char)i;
				//System.out.print(chr);
				System.out.println("i: "+i);
				if(BombermanMain.isClient())
					PlayerTwoKeyListener.virtualKeyPressed(i);
				else 
					PlayerTwoKeyListenerServer.virtualKeyPressed(i);
				}
			System.out.println("END");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
