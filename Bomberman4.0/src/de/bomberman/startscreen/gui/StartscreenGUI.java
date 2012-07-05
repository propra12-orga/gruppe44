package de.bomberman.startscreen.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import de.bomberman.main.gui.MainGUI;
import de.bomberman.startscreen.listener.StartScreenKeyListener;

/** Startscreen Panel (Auswähl der Spielmodi)
 * @author Nuni
 *
 */
public class StartscreenGUI extends JPanel {
	
	private static final boolean DEBUG = true;

	//private static BufferedImage MENUE_BACKGROUND;
	//Image tempImage = null;
	private static final int MENU_SIZE = 6;
	private JLabel[] menu = new JLabel[MENU_SIZE];
	private int activatedMenu;
	
	//public static void initImages() throws FileNotFoundException, IOException {
	//	MENUE_BACKGROUND = ImageIO.read(new FileInputStream("pics/MenueGraphik.jpg"));
	//}
	
	public StartscreenGUI(MainGUI gui) {
		
		activatedMenu = 0;
		int width = gui.getWidth();
		int height = gui.getHeight();
		
		setLayout(null);
		setSize(width,height);
		setBackground(Color.BLACK);
		//setForeground(MENUE_BACKGROUND);
		
		
		menu[0] = new JLabel("Singelplayer");
		menu[1] = new JLabel("Multiplayer");
		menu[2] = new JLabel("Network");
		menu[3] = new JLabel("Load a game");
		menu[4] = new JLabel("Highscore");
		menu[5] = new JLabel("Settings");
		
		for (int i = 0; i < menu.length; i++) {
			menu[i].setLocation(width/2-80, height/menu.length+50*i);
			menu[i].setSize(100, 100);
			menu[i].setHorizontalAlignment(SwingConstants.CENTER);
			menu[i].setForeground(Color.WHITE);
			add(menu[i]);
		}
		activate();
	}
	
	public void menuUp() {
		if (activatedMenu > 0) {
			activatedMenu--;
			activate();
		}
	}
	public void menuDown() {
		if (activatedMenu < ( MENU_SIZE - 1 ) ) {
			activatedMenu++;
			activate();
		}
	}
	
	/**
	 *  lässt den momentan ausgewählten Menüpunkt hervorheben
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
	

}
