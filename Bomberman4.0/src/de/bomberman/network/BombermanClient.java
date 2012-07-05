package de.bomberman.network;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import de.bomberman.player.PlayerOneKeyListener;
import de.bomberman.player.PlayerTwoKeyListener;

/**Regelt die Kommunikation des Clients mit dem Server
 * @author Gruppe 44 
 *
 */
public class BombermanClient implements Runnable {

	private static int port = 4000;
	private static String host = "134.99.36.101";  //IP Adresse vom SERVER PLAYER
	private static Socket socket;
	private static BufferedOutputStream out;
	
	/**
	 * Startet die Verbindung. Server sucht unter angegebener ip (host) und port einen Server
	 */
	public void start() {
		try {
			if(InetAddress.getByName(host).isReachable( 2000 )){
				InetAddress ipAdress = InetAddress.getByName(host);
				InetSocketAddress adr = new InetSocketAddress(ipAdress, port);
				socket = new Socket();
				socket.connect(adr, 100);
				new Thread(this).start();
				
				//System.out.println("Client "+socket.getRemoteSocketAddress()+" with the IP "+socket.getLocalAddress()+" connect on Port "+socket.getLocalPort());
				out = new BufferedOutputStream(socket.getOutputStream());
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * schlieﬂt die verbindung
	 */
	public void stop() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** teilt dem Server die Aktionen auf Client Seite mit
	 * @param i
	 */
	public static void submit(int i ) {
		//String protocolString = Protocol.getProtocolString(player, actionCode, xPos, yPos, key);
		//int control = player*4+actionCode*3+xPos*2+yPos;
		try {
			out.write(i);
			out.flush();
			//new SubmitAction(protocolString, out, new BufferedInputStream(socket.getInputStream()), control);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Laucht auf Mitteilungen des Server und gibt diese an den KeyListener weiter 
	 * der diese dann verarbeitet
	 * @see java.lang.Runnable#run()
	 **/
	@Override
	public void run() {
		
		BufferedInputStream in = null;
		try {
			in = new BufferedInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			for(int i =0 ; ( i = in.read() ) != -1;){
				char chr = (char)i;
				System.out.print(chr);
				System.out.println("i: " + i);
				PlayerOneKeyListener.virtualKeyPressed(i);
			}
			System.out.println("END");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
