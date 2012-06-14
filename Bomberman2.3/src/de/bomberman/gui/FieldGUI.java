package de.bomberman.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.FileInputStream;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import de.bomberman.items.Bomb;
import de.bomberman.items.Wall;
import de.bomberman.main.BombermanMain;
import de.bomberman.main.Player;
import de.bomberman.playground.Playground;
import de.bomberman.timer.ExplosionGUITimer;

public class FieldGUI extends JPanel{

	private static final long serialVersionUID = -4858368222783600824L;
	
	//colors
	public static final boolean SHOW_FIELD_BORDER = false;
	public static final Color FIELD_BORDER_COLOR = Color.BLACK;
	public static final Color COLOR_PLAYER_ONE = Color.RED;
	public static final Color COLOR_PLAYER_TWO = Color.YELLOW;
	public static final Color COLOR_PLAYER_THREE = Color.BLUE;
	public static final Color COLOR_PLAYER_FOUR = Color.BLACK;
	public static final Color BOMB_COLOR = Color.PINK;
	public static final Color UNBREAKABLE_WALL_COLOR = Color.gray;
	private static final Color EMPTY_FIELD_COLOR = new Color(0,255,0);
	private static final Color EXPLOSION_COLOR = Color.CYAN;
	
	private boolean explosionHorizontal = false;
	private boolean explosionVertikal = false;
	private boolean explosionCross = false;
	private int positionX, positionY; 
	
	public FieldGUI(int positionX,int positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
		setBackground(Color.BLUE);
		if(SHOW_FIELD_BORDER)
			setBorder(BorderFactory.createLineBorder(FIELD_BORDER_COLOR));
		setVisible(true);
	}
	
	public void paintComponent(Graphics g){
		Image tempImage = null;
		boolean pictureNotFound = false;
		Player tempPlayer = Playground.getFields()[this.positionX][this.positionY].getPlayer();
		Bomb tempBomb = Playground.getFields()[this.positionX][this.positionY].getBomb();
		Wall tempWall = Playground.getFields()[this.positionX][this.positionY].getWall();
	
		if(tempPlayer != null) { // NEU ;-)
			switch (BombermanMain.getCntPlayer()){
			case 4:
				if(BombermanMain.DEBUG)
					System.out.println("Player of field "+this.positionX+"/"+this.positionY+" comparing with player 4");
				if(tempPlayer == Playground.getPlayers()[3]) {
					if(BombermanMain.DEBUG) {
						System.out.println("player 4 found on field "+this.positionX+"/"+this.positionY);
					g.setColor(COLOR_PLAYER_FOUR);
					}
				}
			case 3:
				if(BombermanMain.DEBUG)
					System.out.println("Player of field "+this.positionX+"/"+this.positionY+" comparing with player 3");
				if(tempPlayer == Playground.getPlayers()[2])
					if(BombermanMain.DEBUG) {
						System.out.println("player 3 found on field "+this.positionX+"/"+this.positionY);
					g.setColor(COLOR_PLAYER_THREE);
					}
			case 2:
				if(BombermanMain.DEBUG)
					System.out.println("Player of field "+this.positionX+"/"+this.positionY+" comparing with player 2");
				if(tempPlayer == Playground.getPlayers()[1])
					if(BombermanMain.DEBUG) {
						System.out.println("player 2 found on field "+this.positionX+"/"+this.positionY);
					g.setColor(COLOR_PLAYER_TWO);
					}
			case 1:
				if(BombermanMain.DEBUG)
					System.out.println("Player of field "+this.positionX+"/"+this.positionY+" comparing with player 1");
				if(tempPlayer == Playground.getPlayers()[0]) {
					if(BombermanMain.DEBUG){
						System.out.println("player 1 found on field "+this.positionX+"/"+this.positionY);
					g.setColor(COLOR_PLAYER_ONE);
					}
				}
			}
		} else {
			if (tempBomb != null) {
				tempImage = PlaygroundGUI.BOMB;
				g.setColor(BOMB_COLOR);
			} else {
				if( tempWall != null) {
					tempImage = PlaygroundGUI.UNBREAKABLE_WALL;
					g.setColor(UNBREAKABLE_WALL_COLOR);
				} else {
						tempImage = PlaygroundGUI.EMPTY_FIELD_IMAGE;
						g.setColor(EMPTY_FIELD_COLOR); 
				}
			}
			if (explosionVertikal) {
				tempImage = PlaygroundGUI.EXPLOSION_VERTIKAL;
				g.setColor(EXPLOSION_COLOR);
			}
			if (explosionHorizontal) {
				tempImage = PlaygroundGUI.EXPLOSION_HORIZONTAL;
				g.setColor(EXPLOSION_COLOR);
			}
			if (explosionCross) {
				tempImage = null;
				g.setColor(EXPLOSION_COLOR);
			}
				
		}
		if (tempImage != null) {
			if (BombermanMain.DEBUG) {
				System.out.println("Draw Image on Field " + this.positionX + "/" + this.positionY);
			}
			g.drawImage(tempImage, 0, 0, 30, 30, this);
		} else {
			if (BombermanMain.DEBUG) {
				System.out.println("Draw fillRect on Field " + this.positionX + "/" + this.positionY);
			}
			g.fillRect(0, 0, MainGUI.FIELD_SIZE, MainGUI.FIELD_SIZE);
		}
	}

	public void paintNew() {
		repaint();
	}

	public void setExplosionHorizontal(boolean explosionHorizontal) {
		this.explosionHorizontal = explosionHorizontal;
		repaint();
		if (explosionHorizontal)
			new ExplosionGUITimer(this);
	}

	public void setExplosionVertikal(boolean explosionVertikal) {
		this.explosionVertikal = explosionVertikal;
		repaint();
		if (explosionVertikal)
			new ExplosionGUITimer(this);
	}

	public void setExplosionCross(boolean explosionCross) {
		this.explosionCross = explosionCross;
		repaint();
		if (explosionCross)
			new ExplosionGUITimer(this);
	}
	
	
	
}
