package de.bomberman.network;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.ResourceBundle.Control;

public class SubmitAction extends Thread {
	private BufferedOutputStream out;
	private BufferedInputStream in;
	private String protocolString;
	private byte[] protocolBytes;
	private int control;
	
	public SubmitAction(String protocolString, BufferedOutputStream out,
			BufferedInputStream in, int control) {
		this.out = out;
		this.in = in;
		this.protocolBytes = protocolString.getBytes();
		this.control = control;
		this.start();
	}
	@Override
	public void run() {
		try {
			Thread t = Thread.currentThread();
			for( int i = 0; (i = in.read()) != -1 ;) {
				if ( i == control) {
						break;
				}
				t.sleep(100);
				out.write(this.protocolBytes);
				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



}
