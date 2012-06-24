package de.bomberman.gui;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JPanel;

import de.bomberman.mapeditor.gui.MapEditor;
import de.bomberman.menu.BombermanMenuBar;
import de.bomberman.playground.Playground;

public class MainPanel extends JPanel {
	private static final long serialVersionUID = -8887936681830447475L;

	public static final int FIELD_SIZE = 30;
	private static PlaygroundGUI pgGUI;
	private static MapEditor me;
	private static ScoreBoard sboard;

	public MainPanel() {

			//init
			setLayout(null);
			setBackground(new Color(50,50,50));

			
				//pg
				pgGUI = new PlaygroundGUI();
				pgGUI.setSize(Playground.getSize()*MainGUI.panel.FIELD_SIZE, Playground.getSize()*MainGUI.panel.FIELD_SIZE);
				pgGUI.setLocation(50, 50);
				add(pgGUI);
				//setSize(Playground.getSize()*MainGUI.panel.FIELD_SIZE+300, Playground.getSize()*MainGUI.panel.FIELD_SIZE+100);
			
				//scoreboard
				sboard = new ScoreBoard();
				sboard.setLocation(Playground.getSize()*MainGUI.panel.FIELD_SIZE+70, 50);
				sboard.setSize(200, Playground.getSize()*MainGUI.panel.FIELD_SIZE);
				add(sboard);

				me = new MapEditor();
				me.setLocation(50, 50);
				me.setSize(Playground.getSize()*MainGUI.panel.FIELD_SIZE + 200, Playground.getSize()*MainGUI.panel.FIELD_SIZE);
				add(me);
			
			

			//main
			setVisible(true);
		}
	
		public static void uploadScoreBoard(){
			sboard.uploadScoreBoard();
		}

		public static void showMapEditor() {
			pgGUI.setVisible(false);
			sboard.setVisible(false);
			me.setVisible(true);
		}
		
		public static void showMultiPlayer() {
			pgGUI.setVisible(true);
			sboard.setVisible(true);
			me.setVisible(false);
		}
		
}
