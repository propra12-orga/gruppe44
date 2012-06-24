package de.bomberman.network;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BombermanServer implements Runnable {

	private static Socket socket;
	private static ServerSocket ssocket;
	private static BufferedOutputStream out;
	

	public void start() {
		
		try {
			ssocket = new ServerSocket(1986);
			System.out.println("Server wartet auf Client!");
			socket = ssocket.accept();
			new Thread(this).start();
			
			System.out.println("Client "+socket.getRemoteSocketAddress()+" with the IP "+socket.getLocalAddress()+" connect on Port "+socket.getLocalPort());
			out = new BufferedOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void stop() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void submit(int player, int actionCode, int xPos, int yPos) {
		String protocolString = Protocol.getProtocolString(player, actionCode, xPos, yPos, 0);
		try {
			out.write(protocolString.getBytes());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void run() {
		BufferedInputStream in = null;
		try {
			in = new BufferedInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		StringBuilder tempProtocol = new StringBuilder();
		Protocol protocol;
		try {
			for(int i =0 ; ( i = in.read() ) != -1;){
				char chr = (char)i;
				System.out.print(chr);
				tempProtocol.append(chr);
				String temp = tempProtocol.toString();
				
				System.out.println(temp);
				if( temp.matches("y.{2,}x.{2,}x.{2,}x.{2,}y")) {
					tempProtocol = new StringBuilder();
					String [] tempo = temp.split("y");
					for( int k = 0; k < tempo.length; k++)
						if( tempo[k].length() > 10 ) {
							System.out.println(tempo[k]);
							protocol = new Protocol(tempo[k]);
							if( protocol.isStatus()) {
								Protocol.submit(protocol);
								int control = protocol.getControl();
								System.out.println("Corntrol: "+ control);
								out.write(control);
								out.flush();
								System.out.println("Corntrol code send");
							}
						}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
