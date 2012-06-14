package de.bomberman.gui;

import javax.swing.JDialog;
import javax.swing.JFrame;

import de.bomberman.menu.BombermanMenuBar;
import de.bomberman.playground.Playground;

public final class MainGUI extends JDialog {
	
	private static final long serialVersionUID = -8887936681830447475L;
	private static ScoreBoard sboard;
	public static final int FIELD_SIZE = 30;
	public MainGUI() {
		
		//init
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setLayout(null);
		setJMenuBar(new BombermanMenuBar());
		PlaygroundGUI pgGUI = new PlaygroundGUI();
		sboard = new ScoreBoard();
		
		//pg
		pgGUI.setSize(Playground.getSize()*MainGUI.FIELD_SIZE, Playground.getSize()*MainGUI.FIELD_SIZE);
		pgGUI.setLocation(50, 50);
		add(pgGUI);
		
		//scoreboard
		sboard.setLocation(Playground.getSize()*MainGUI.FIELD_SIZE+70, 50);
		sboard.setSize(200, Playground.getSize()*MainGUI.FIELD_SIZE);
		add(sboard);
		
		
		//main
		setSize(Playground.getSize()*MainGUI.FIELD_SIZE+300, Playground.getSize()*MainGUI.FIELD_SIZE+100);
		setResizable(false);
		setVisible(true);
	}
	
	public static void uploadScoreBoard(){
		sboard.uploadScoreBoard();
	}
}
