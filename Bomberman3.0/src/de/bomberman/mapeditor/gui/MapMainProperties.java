package de.bomberman.mapeditor.gui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.bomberman.gui.MainGUI;
import de.bomberman.playground.Playground;

public class MapMainProperties extends JPanel {
	
	public MapMainProperties() {
		
		int width = MapEditor.getGuiWidth();
		setLayout(new GridLayout(5,2));
		add( new JLabel());
		add( new JLabel());
		add( new JLabel("Size:"));
		add( new JTextField());
		add( new JLabel("Player:"));
		add( new JTextField());
		add( new JLabel("Map-Name:"));
		add( new JTextField());
		add( new JLabel());
		add( new JLabel());
		setSize(width, 100);
		setVisible(true);
	}
}
