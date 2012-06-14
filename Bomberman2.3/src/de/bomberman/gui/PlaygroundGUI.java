package de.bomberman.gui;

import java.awt.Color;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import de.bomberman.main.BombermanMain;
import de.bomberman.playground.Playground;

public class PlaygroundGUI extends JPanel {
	
	private static final long serialVersionUID = 8543194726501404948L;
	
	private static FieldGUI[][] fields;
	
	//pictures
	public static Image EMPTY_FIELD_IMAGE;  
	public static Image UNBREAKABLE_WALL;
	public static Image BOMB;
	public static Image EXPLOSION_HORIZONTAL;
	public static Image EXPLOSION_VERTIKAL;

	public PlaygroundGUI() {
		try {
			EMPTY_FIELD_IMAGE = ImageIO.read(new FileInputStream("pics/rasen2.gif"));
			UNBREAKABLE_WALL = ImageIO.read(new FileInputStream("pics/stein2.jpg"));
			BOMB = ImageIO.read(new FileInputStream("pics/bombe.gif"));
			EXPLOSION_HORIZONTAL = ImageIO.read(new FileInputStream("pics/explohori.jpg"));
			EXPLOSION_VERTIKAL = ImageIO.read(new FileInputStream("pics/exploverti.jpg"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(BombermanMain.INFO)
			System.out.println("Initalisiere FieldGUIs...");
		setLayout(null); // absolutes Layout
		setBackground(new Color(0,255,0));

		
		fields = new FieldGUI[Playground.getSize()][Playground.getSize()]; //Tabelle für die einzelnen Felder [x][y]
		
		for (int i = 0; i < Playground.getSize() ; i++) {
			for (int j = 0; j < Playground.getSize() ; j++) {
				this.fields[i][j] = new FieldGUI(i,j);
				this.fields[i][j].setSize(MainGUI.FIELD_SIZE, MainGUI.FIELD_SIZE);
				this.fields[i][j].setLocation(i*MainGUI.FIELD_SIZE, j*MainGUI.FIELD_SIZE);
				add(this.fields[i][j]);
				if (BombermanMain.DEBUG)
					System.out.println("fieldGUI "+i+" "+j+" erstellt.");
			}
		}
		setVisible(true);
	}

	public static FieldGUI[][] getFields() {
		return fields;
	}
	
}
