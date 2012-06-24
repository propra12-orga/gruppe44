package de.bomberman.gui;

import java.awt.Color;

import java.awt.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.midi.SoundbankResource;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import de.bomberman.main.BombermanMain;
import de.bomberman.playground.Playground;
/**
 * @author gruppe44
 * Klasse erstellen (Spielfläche), erbt von JPanel
 * Die Klasse initialisiert den Untergrund
 * Das Spielfeld wird auf einem 2D-Array erzeugt
 */
public class PlaygroundGUI extends JPanel {
	
	private static final long serialVersionUID = 8543194726501404948L;
	
	private static FieldGUI[][] fields;
	/**
	 * Bilder werden initialisiert
	 */
	//pictures
	public static Image EMPTY_FIELD_IMAGE;  
	public static Image UNBREAKABLE_WALL;
	public static Image BOMB;
	public static Image EXPLOSION_HORIZONTAL;
	public static Image EXPLOSION_VERTIKAL;
	public static Image EXPLOSION_CENTRAL;
	public static Image PLAYER_ONE;
	public static Image PLAYER_TWO;
	public static Image PLAYER_THREE;
	public static Image PLAYER_FOUR;

	/**
	 * Konstruktor
	 * Wenn ein Fehler auftritt, wirft die Methode eine IOException aus. 
	 */
	public PlaygroundGUI() {
		try {
			EMPTY_FIELD_IMAGE = ImageIO.read(new FileInputStream("pics/rasen2.gif"));
			UNBREAKABLE_WALL = ImageIO.read(new FileInputStream("pics/stein2.jpg"));
			BOMB = ImageIO.read(new FileInputStream("pics/bombe.png"));
			EXPLOSION_HORIZONTAL = ImageIO.read(new FileInputStream("pics/explohori.jpg"));
			EXPLOSION_VERTIKAL = ImageIO.read(new FileInputStream("pics/exploverti.jpg"));
			EXPLOSION_CENTRAL = ImageIO.read(new FileInputStream("pics/explomitte.jpg"));
			PLAYER_ONE = ImageIO.read(new FileInputStream("pics/Rot.png"));
			PLAYER_TWO = ImageIO.read(new FileInputStream("pics/Gruen.png"));
			PLAYER_THREE = ImageIO.read(new FileInputStream("pics/Blau.png"));
			PLAYER_FOUR = ImageIO.read(new FileInputStream("pics/Gelb.png"));
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(BombermanMain.INFO)
			System.out.println("Initalisiere FieldGUIs...");
		/**
		 *  Layout und Hintergrund für JPanel festlegen
		 */
		setLayout(null); // absolutes Layout
		setBackground(new Color(0,255,0));
		
		 /**
		  * Feld erzeugen
		  */
		fields = new FieldGUI[Playground.getSize()][Playground.getSize()]; //Tabelle f√ºr die einzelnen Felder [x][y]
		
		for (int i = 0; i < Playground.getSize() ; i++) {
			for (int j = 0; j < Playground.getSize() ; j++) {
				this.fields[i][j] = new FieldGUI(i,j);
				this.fields[i][j].setSize(MainGUI.panel.FIELD_SIZE, MainGUI.panel.FIELD_SIZE);
				this.fields[i][j].setLocation(i*MainGUI.panel.FIELD_SIZE, j*MainGUI.panel.FIELD_SIZE);
				add(this.fields[i][j]);
				if (BombermanMain.DEBUG)
					System.out.println("fieldGUI "+i+" "+j+" erstellt.");
			}
		}
		/**
		 * Macht das Fenster sichtbar
		 */
		setVisible(true);
	}
	/**
	 * Rückgabe
	 */
	public static FieldGUI[][] getFields() {
		return fields;
	}
	
}
