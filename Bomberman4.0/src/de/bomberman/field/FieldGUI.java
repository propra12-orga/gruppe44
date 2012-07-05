package de.bomberman.field;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import de.bomberman.items.Bomb;
import de.bomberman.items.Wall;
import de.bomberman.main.BombermanMain;
import de.bomberman.player.Player;
import de.bomberman.timer.ExplosionGUITimer;


/** Regelt die Visualisierung der LogicFields
 * @author Gruppe 44
 *
 */
public class FieldGUI extends JPanel{ 
	
	//unnötig?
	private static final long serialVersionUID = -4858368222783600824L;
	
	/**
	 * Damit lassen sich Konsolen Ausgaben die für die 
	 * Entwicklung Hilfreich sind EIN und AUS schalten.
	 */
	private static final boolean DEBUG = false;
	//colors
	public static final boolean SHOW_FIELD_BORDER = false;
	public static final Color FIELD_BORDER_COLOR = Color.BLACK;
	public static final Color COLOR_PLAYER_ONE = Color.RED;
	public static final Color COLOR_PLAYER_TWO = Color.YELLOW;
	public static final Color COLOR_PLAYER_THREE = new Color(0,150,0);
	public static final Color COLOR_PLAYER_FOUR = Color.BLACK;
	public static final Color BOMB_COLOR = Color.PINK;
	public static final Color UNBREAKABLE_WALL_COLOR = Color.gray;
	public static final Color BREAKABLE_WALL_COLOR_1 = new Color(238,154,73);
	public static final Color BREAKABLE_WALL_COLOR_2 = new Color(205,133,63);
	public static final Color BREAKABLE_WALL_COLOR_3 = new Color(139,90,43);
	private static final Color EMPTY_FIELD_COLOR = new Color(0,255,0);
	private static final Color EXPLOSION_COLOR = Color.CYAN;
	private static final Color SMELL_1 = new Color(255,193,193);
	private static final Color SMELL_2 = new Color(225,163,163);
	private static final Color SMELL_3 = new Color(195,133,133);
	private static final Color SMELL_4 = new Color(165,103,103);
	private static final Color SMELL_5 = new Color(135,73,73);
	private static final Color SMELL_6 = new Color(105,43,43);
	private static final Color SMELL_7 = new Color(75,13,13);
	
	//images
	private static BufferedImage EMPTY_FIELD_IMAGE;
	private static BufferedImage UNBREAKABLE_WALL;
	private static BufferedImage BOMB;
	private static BufferedImage EXPLOSION_HORIZONTAL;
	private static BufferedImage EXPLOSION_VERTIKAL;
	private static BufferedImage EXPLOSION_CENTRAL;
	private static BufferedImage PLAYER_ONE;
	private static BufferedImage PLAYER_TWO;
	private static BufferedImage PLAYER_THREE;
	private static BufferedImage PLAYER_FOUR;
	private static BufferedImage ITEM_RUN;
	private static BufferedImage ITEM_BOMBE_PLUS;
	private static BufferedImage KI_SLOWER;
	private static BufferedImage KI_PLAYER_IMAGE;
	private static BufferedImage HIGHSCORE_IMAGE;
	private static BufferedImage EXIT_IMAGE;
	private static BufferedImage BREAKABLE_WALL_1;
	private static BufferedImage BREAKABLE_WALL_2;
	private static BufferedImage BREAKABLE_WALL_3;

	
	private boolean explosionHorizontal = false;
	private boolean explosionVertikal = false;
	private boolean explosionCross = false;
	private int positionX, positionY; 
	
	/** Initialisiert die Bild Objeckte aus den Dateien im "pics" Ordner für
	 * 	Verscheidenen Gui Elemente
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void initImages() throws FileNotFoundException, IOException {
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
		KI_PLAYER_IMAGE = ImageIO.read(new FileInputStream("pics/Boese.png"));
		ITEM_RUN = ImageIO.read(new FileInputStream("pics/LaufenSchneller.png"));
		ITEM_BOMBE_PLUS = ImageIO.read(new FileInputStream("pics/BombePlus.png"));
		KI_SLOWER = ImageIO.read(new FileInputStream("pics/Engel.png"));
		HIGHSCORE_IMAGE = ImageIO.read(new FileInputStream("pics/Highscore.png"));
		EXIT_IMAGE = ImageIO.read(new FileInputStream("pics/Wuerfel.png"));
		BREAKABLE_WALL_1 = ImageIO.read(new FileInputStream("pics/Wand1.jpg"));
		BREAKABLE_WALL_2 = ImageIO.read(new FileInputStream("pics/Wand2.jpg"));
		BREAKABLE_WALL_3 = ImageIO.read(new FileInputStream("pics/Wand3.jpg"));
		
	}
	
	/** Konstruktor eines FieldGUI Object
	 * @param positionX
	 * @param positionY
	 */
	public FieldGUI(int positionX,int positionY) {
		
		int fieldSize = BombermanMain.FIELD_SIZE;
		this.positionX = positionX;
		this.positionY = positionY;
		setSize(fieldSize, fieldSize);
		setLocation(positionX*fieldSize, positionY*fieldSize);
		setBackground(Color.BLUE);
		if(SHOW_FIELD_BORDER)
			setBorder(BorderFactory.createLineBorder(FIELD_BORDER_COLOR));
		setVisible(true);
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g){
		Image tempImage = null;
		g.drawImage(EMPTY_FIELD_IMAGE, 0, 0, 30, 30, this);
		boolean pictureNotFound = false;
		LogicField tempField = BombermanMain.getFields()[this.positionX][this.positionY];
		Player tempPlayer = BombermanMain.getFields()[this.positionX][this.positionY].getPlayer();
		Bomb tempBomb = BombermanMain.getFields()[this.positionX][this.positionY].getBomb();
		Wall tempWall = BombermanMain.getFields()[this.positionX][this.positionY].getWall();
	
		if(tempPlayer != null) { // NEU ;-)
			
			switch (BombermanMain.getPlayerCnt()){
			case 4:
				if( DEBUG )
					System.out.println("Player of field "+this.positionX+"/"+this.positionY+" comparing with player 4");
				if(tempPlayer == BombermanMain.getPlayers()[3]) {
					if( DEBUG ) {
						System.out.println("player 4 found on field "+this.positionX+"/"+this.positionY);
					}
						g.setColor(COLOR_PLAYER_FOUR);
				}
			case 3:
				if( DEBUG )
					System.out.println("Player of field "+this.positionX+"/"+this.positionY+" comparing with player 3");
				if(tempPlayer == BombermanMain.getPlayers()[2]) {
					if( DEBUG ) {
						System.out.println("player 3 found on field "+this.positionX+"/"+this.positionY);
					}
					g.setColor(COLOR_PLAYER_THREE);
				}
					
			case 2:
				if( DEBUG )
					System.out.println("Player of field "+this.positionX+"/"+this.positionY+" comparing with player 2");
				if(tempPlayer == BombermanMain.getPlayers()[1]) {
					if( DEBUG ) {
						System.out.println("player 2 found on field "+this.positionX+"/"+this.positionY);
					}
					tempImage = PLAYER_TWO;
					g.setColor(COLOR_PLAYER_TWO);
				}
			case 1:
				if( DEBUG )
					System.out.println("Player of field "+this.positionX+"/"+this.positionY+" comparing with player 1");
				if(tempPlayer == BombermanMain.getPlayers()[0]) {
					if( DEBUG ){
						System.out.println("player 1 found on field "+this.positionX+"/"+this.positionY);
					}
					tempImage = PLAYER_ONE;
					g.setColor(COLOR_PLAYER_ONE);
				}
			}
			if( tempPlayer.getID() == 9) {
				tempImage = KI_PLAYER_IMAGE;
				g.setColor(Color.PINK);
			}
		} else {
			if (tempBomb != null) {
				tempImage = BOMB;
				g.setColor(BOMB_COLOR);
			} else {
				if( tempWall != null) {
					if( !tempWall.isBreakable() ) {
						tempImage = UNBREAKABLE_WALL;
						g.setColor(UNBREAKABLE_WALL_COLOR);
					} else {
						switch(tempWall.getPower()) {
						case 3:
							tempImage = BREAKABLE_WALL_3;
							g.setColor(BREAKABLE_WALL_COLOR_3);
							break;
						case 2:
							tempImage = BREAKABLE_WALL_2;
							g.setColor(BREAKABLE_WALL_COLOR_2);
							break;
						case 1:
							tempImage = BREAKABLE_WALL_1;
							g.setColor(BREAKABLE_WALL_COLOR_1);
							break;
						}
					}
				} else if (!tempField.isExitField()) {
					if(tempField.getPowerUp() == null) {
						tempImage = EMPTY_FIELD_IMAGE;
						g.setColor(EMPTY_FIELD_COLOR); 
					} else {
						int type = tempField.getPowerUp().getType();
						switch(type) {
						case 1:
							tempImage = ITEM_RUN;
							g.setColor(Color.ORANGE);
							break;
						case 2:
							tempImage = ITEM_BOMBE_PLUS;
							g.setColor(Color.ORANGE);
							break;
						case 3:
							tempImage = KI_SLOWER;
							g.setColor(Color.ORANGE);
							break;
						}
					}
				}
			}
			if (explosionVertikal) {
				tempImage = EXPLOSION_VERTIKAL;
				g.setColor(EXPLOSION_COLOR);
			}
			if (explosionHorizontal) {
				tempImage = EXPLOSION_HORIZONTAL;
				g.setColor(EXPLOSION_COLOR);
			}
			if (explosionCross) {
				tempImage = EXPLOSION_CENTRAL;
				g.setColor(EXPLOSION_COLOR);
			}
			
			/*switch(tempField.getSmell()) {
			case 1:
				g.setColor(SMELL_1);
				tempImage = null;
				break;
			case 2:
				g.setColor(SMELL_2);
				tempImage = null;
				break;
			case 3:
				g.setColor(SMELL_3);
				tempImage = null;
				break;
			case 4:
				g.setColor(SMELL_4);
				tempImage = null;
				break;
			case 5:
				g.setColor(SMELL_5);
				tempImage = null;
				break;
			case 6:
				g.setColor(SMELL_6);
				tempImage = null;
				break;
			case 7:
				g.setColor(SMELL_7);
				tempImage = null;
				break;
			}*/
			
			if( tempField.isExitField())
				tempImage = EXIT_IMAGE;
				g.setColor(Color.BLACK);
		}
		if (tempImage != null) {
			if ( DEBUG ) {
				System.out.println("Draw Image on Field " + this.positionX + "/" + this.positionY);
			}
			g.drawImage(tempImage, 0, 0, 30, 30, this);
		} else {
			if ( DEBUG ) {
				System.out.println("Draw fillRect on Field " + this.positionX + "/" + this.positionY);
			}
			g.fillRect(0, 0, BombermanMain.FIELD_SIZE, BombermanMain.FIELD_SIZE);
		}
	}

	public void paintNew() {
		repaint();
	}

	/** Regelt die Darstellung der Explosion in horizontaler Richtung
	 * @param explosionHorizontal
	 */
	public void setExplosionHorizontal(boolean explosionHorizontal) {
		this.explosionHorizontal = explosionHorizontal;
		repaint();
		if (explosionHorizontal)
			new ExplosionGUITimer(this);
	}

	/** Regelt die Darstellung der Explosion in vertikaler Richtung
	 * @param explosionVertikal
	 */
	public void setExplosionVertikal(boolean explosionVertikal) {
		this.explosionVertikal = explosionVertikal;
		repaint();
		if (explosionVertikal)
			new ExplosionGUITimer(this);
	}

	/**  Regelt die Darstellung der Explosion am Explosions-Uhrsprung Richtung
	 * @param explosionCross
	 */
	public void setExplosionCross(boolean explosionCross) {
		this.explosionCross = explosionCross;
		repaint();
		if (explosionCross)
			new ExplosionGUITimer(this);
	}
	
	
	
}
