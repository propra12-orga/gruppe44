package de.bomberman.network;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class BombermanClient implements Runnable {

	private static int port = 1986;
	private static String host = "192.168.1.2";
	private static Socket socket;
	private static BufferedOutputStream out;
	
	public void start() {
		try {
			if(InetAddress.getByName(host).isReachable( 2000 )){
				InetAddress ipAdress = InetAddress.getByName(host);
				InetSocketAddress adr = new InetSocketAddress(ipAdress, port);
				socket = new Socket();
				socket.connect(adr, 100);
			}
				new Thread(this).start();
				
				System.out.println("Client "+socket.getRemoteSocketAddress()+" with the IP "+socket.getLocalAddress()+" connect on Port "+socket.getLocalPort());
				out = new BufferedOutputStream(socket.getOutputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void stop() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void submit(int player, int actionCode, int xPos, int yPos, int key ) {
		String protocolString = Protocol.getProtocolString(player, actionCode, xPos, yPos, key);
		int control = player*4+actionCode*3+xPos*2+yPos;
		try {
			new SubmitAction(protocolString, out, new BufferedInputStream(socket.getInputStream()), control);
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
				//System.out.print(chr);
				tempProtocol.append(chr);
				String temp = tempProtocol.toString();
				
				//System.out.println(temp);
				if( temp.matches("y.{2,}x.{2,}x.{2,}x.{2,}y")) {
					tempProtocol = new StringBuilder();
					String [] tempo = temp.split("y");
					for( int k = 0; k < tempo.length; k++)
						if( tempo[k].length() > 10 ) {
							//System.out.println(tempo[k]);
							protocol = new Protocol(tempo[k]);
							if( protocol.isStatus())
								Protocol.submit(protocol);
						}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
