package de.bomberman.gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.sound.sampled.ReverbType;
import javax.swing.JDialog;

import de.bomberman.mapeditor.gui.MapEditor;
import de.bomberman.menu.BombermanMenuBar;
import de.bomberman.playground.Playground;

public final class MainGUI extends JDialog {
	
	private static final long serialVersionUID = -8887936681830447475L;
	public static MainPanel panel;
	public MainGUI() {
		
		//main
		setLayout(new GridLayout());
		MainPanel panel = new MainPanel();
		add(panel);
		setJMenuBar(new BombermanMenuBar());
		setResizable(false);
		setSize(Playground.getSize()*MainGUI.panel.FIELD_SIZE+300, Playground.getSize()*MainGUI.panel.FIELD_SIZE+100);
		setVisible(true);
		

	}
	
}
