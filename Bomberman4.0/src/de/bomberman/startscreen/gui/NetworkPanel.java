package de.bomberman.startscreen.gui;

import java.awt.Color;
import java.awt.event.KeyListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import de.bomberman.main.gui.MainGUI;

/** Panel auf dem die Wahl zwischen Client und Server möglich ist
 * @author Gruppe 44 
 *
 */
public class NetworkPanel extends JPanel {

	private static final boolean DEBUG = true;

	private static final int MENU_SIZE = 3;
	private JLabel[] menu = new JLabel[MENU_SIZE];
	private int activatedMenu;
	private JLabel ip;
	private JTextField field;
	private JTextField ipField;
	private int width;
	private int height;
	
	public NetworkPanel(MainGUI gui) {
		
		activatedMenu = 0;
		width = gui.getWidth();
		height = gui.getHeight();
		
		setLayout(null);
		setSize(width,height);
		setBackground(Color.BLACK);
		String IP = "Error";
		try {
			IP = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		ip = new JLabel("IP: "+IP);
		ipField = new JTextField("IP");
		field = new JTextField("PORT");
		
		
		menu[0] = new JLabel("Start Game (Server)");
		menu[1] = new JLabel("Conecting Game (Client)");
		menu[2] = new JLabel("waiting for client...");

		
		for (int i = 0; i < menu.length; i++) {
			if( i == 3)
				menu[i].setLocation(width/2-80, height/menu.length+50*(i+4));
			else
				menu[i].setLocation(width/2-80, height/menu.length+50*i);
			menu[i].setSize(200, 100);
			menu[i].setHorizontalAlignment(SwingConstants.CENTER);
			menu[i].setForeground(Color.WHITE);
			add(menu[i]);
		}
		menu[2].setVisible(false);
		activate();
	}
	
	public void menuUp() {
		if (activatedMenu > 0) {
			activatedMenu--;
			activate();
		}
	}
	public void menuDown() {
		if (activatedMenu < ( MENU_SIZE - 2 ) ) {
			activatedMenu++;
			activate();
		}
	}
	
	/**
	 * Hebt den Selectierten Menüpunkt ab
	 */
	public void activate() {
		if( DEBUG )
			System.out.println("Menu ID: "+activatedMenu);
		
		for(int i = 0; i < MENU_SIZE; i++) {
			if ( i != activatedMenu ) {
				menu[i].setForeground(Color.WHITE);
			} else {
				menu[i].setForeground(Color.YELLOW);
			}
		}

	}

	public int getID() {
		return activatedMenu;
	}
	
	/**
	 *  Zeigt die IP (Server) und ermöglicht die Eingabe des 
	 *  Ports (mommentan nicht aktiv)
	 */
	public void showSettings() {
		add(ip);
		ip.setForeground(Color.WHITE);
		ip.setLocation(width/2-80, height/menu.length+50*(3));
		ip.setSize(200, 100);
		add(field);
		field.setLocation(width/2-80, height/menu.length+50*(5));
		field.setSize(200, 30);
	}
	
	/**
	 *  Lässt anzeigen das der Server auf dem Client wartet
	 *  (mommentan nicht aktiv) - nur über die Console zu sehen
	 */
	public void showWaiting() {
		ip.setVisible(false);
		field.setVisible(false);
		menu[2].setVisible(true);
		menu[2].setForeground(Color.GREEN);
		repaint();
	}

	public void setKeyListener(KeyListener listener) {
		field.addKeyListener(listener);
		
	}

	/**
	 *  Lässt 2 Textfelder erscheinen für die IP und den Port auf denen
	 *  der Server erreichbar sein soll.
	 *  (mommentan nicht aktiv)
	 */
	public void showSettingsClient() {
		add(ipField);
		ipField.setLocation(width/2-80, height/menu.length+50*(3));
		ipField.setSize(200, 30);
		add(field);
		field.setLocation(width/2-80, height/menu.length+50*(5));
		field.setSize(200, 30);
		
	}
	
}
