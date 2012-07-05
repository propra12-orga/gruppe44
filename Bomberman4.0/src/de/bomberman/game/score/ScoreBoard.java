package de.bomberman.game.score;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import de.bomberman.main.BombermanMain;
import de.bomberman.playground.Playground;
	/**
	 * 
	 * @author Gruppe 44 
	 *
	 */

public class ScoreBoard extends JPanel {
	private SingleScoreBoard panelP1;
	private SingleScoreBoard panelP2;
	private SingleScoreBoard panelP3;
	private SingleScoreBoard panelP4;
	private int playerCnt;
	
	/** Konstruktor eine ScoreBoards
	 * @param playerCnt
	 */
	public ScoreBoard(int playerCnt) {
		
		
		setBackground(Color.GREEN);
		setLayout(new GridLayout(playerCnt,1));
		this.playerCnt = playerCnt;
		switch ( playerCnt ) {
		case 4:
			panelP4 = new SingleScoreBoard(3);
			add(panelP4);
		case 3:
			panelP3 = new SingleScoreBoard(2);
			add(panelP3);
		case 2:
			panelP2 = new SingleScoreBoard(1);
			add(panelP2);
		case 1:
			panelP1 = new SingleScoreBoard(0);
			add(panelP1);
		}
		panelP1.setBackground(Color.RED);
		panelP2.setBackground(Color.BLUE);

	}
	
	/**
	 *  Läd das ScoreBoard mit aktuellen Werten neu
	 */
	public void uploadScoreBoard() {
		switch (playerCnt) {
		case 4:
			panelP4.uploadPanel();
		case 3:
			panelP3.uploadPanel();
		case 2:
			panelP2.uploadPanel();
		case 1:
			panelP1.uploadPanel();
		}
	}
	/** setzt die Größe des ScoreBords und gleichzeigt die Größen der Einzelnen
	 *  Panels auf ihm
	 * @see java.awt.Component#setSize(int, int)
	 **/
	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
		
		int singelSBHeight = ((int)(getHeight()/playerCnt) - 1);
		Dimension dimension = new Dimension(400, singelSBHeight); 
		switch (playerCnt) {
		case 4:
			panelP4.setSize(dimension);
			panelP4.setzen();
		case 3:
			panelP3.setSize(dimension);
			panelP3.setzen();
		case 2:
			panelP2.setSize(dimension);
			panelP2.setzen();
		case 1:
			panelP1.setSize(dimension);
			panelP1.setzen();
			//panelP1.setLocation(0, singelSBHeight);
		}
		doLayout();
	}
}
