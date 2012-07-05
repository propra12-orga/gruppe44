package de.bomberman.game;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import de.bomberman.field.FieldGUI;
import de.bomberman.main.BombermanMain;
import de.bomberman.main.SettingsVO;

/**GamePanel Objekt, Visualisiert das Spiel
 * @author Gruppe 44 
 *
 */
public class GamePanel extends JPanel {
	

	/**
	 * Damit lassen sich Konsolen Ausgaben die für die 
	 * Entwicklung Hilfreich sind EIN und AUS schalten.
	 */
	private static final boolean DEBUG = false;
	private FieldGUI fields[][];
	protected int width;
	protected int height;
	protected Game game;
	
	/**Konstruktor
	 * @param game
	 */
	public GamePanel(Game game) {
		this.game = game;
		init();
		initFields();
		initPlayer();
	}

	private void initPlayer() {
		if( DEBUG ) {
			System.out.println("Start creating Player...");
		}
	}

	/**
	 * initialisert FieldGUI Objekte
	 */
	private void initFields() {
		SettingsVO vo = game.vO;
		if( DEBUG ) {
			System.out.println("Start creating FieldGUIs...");
		}
		try {
			FieldGUI.initImages();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fields = new FieldGUI[width][height];
		for (int i = 0; i < width; i++) {
			for(int k = 0; k < height; k++) {
				
				if( DEBUG ) {
					System.out.println("Creating FieldGUI "+i+"/"+k+" ....");
				}
				fields[i][k] = new FieldGUI(i,k);
				fields[i][k].setVisible(true);
				
				add(fields[i][k]);
			}
		}
		
	}

	/**
	 * Initialisiert Grundsätzliche Gamepanel Attribute
	 */
	private void init() {
		if( DEBUG ) {
			System.out.println("Initialising Game-Panel...");
		}
		int fieldSize = BombermanMain.FIELD_SIZE;
		width =  BombermanMain.getPlaygroundWidth();
		height = BombermanMain.getPlaygroundHeight();
		setLayout(null); ////new GridLayout(width,height)
		setSize(width * fieldSize + 220, height * fieldSize);
		//setBackground(Color.RED);
		
	}

	public FieldGUI[][] getFields() {
		return fields;
	}

	public void uploadScoreBoard() {
		//do nothing
	}
	
	

}
