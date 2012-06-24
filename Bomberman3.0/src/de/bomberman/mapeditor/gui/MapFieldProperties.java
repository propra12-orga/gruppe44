package de.bomberman.mapeditor.gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import de.bomberman.gui.MainGUI;
import de.bomberman.playground.Playground;

public class MapFieldProperties extends JPanel {

	public MapFieldProperties() {
		setLayout(new GridLayout(5,1));
		int width = MapEditor.getGuiWidth();
		setBackground(new Color(255,0,0));
		setSize(width , Playground.getSize()*MainGUI.panel.FIELD_SIZE -120);
		WallPanel wallPanel = new WallPanel(new GridLayout(4,1));
		wallPanel.setSize(width -100 , Playground.getSize()*MainGUI.panel.FIELD_SIZE -220);
		add(wallPanel);
		setVisible(true);
		
	}
}
