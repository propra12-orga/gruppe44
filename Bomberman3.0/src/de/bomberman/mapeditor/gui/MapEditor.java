package de.bomberman.mapeditor.gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.bomberman.gui.MainGUI;
import de.bomberman.playground.Playground;

public class MapEditor extends JPanel {
	
	private static int guiWidth = Playground.getSize()*MainGUI.panel.FIELD_SIZE + 180;
	
	public MapEditor() {
		setLayout(null);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		setBackground(new Color(255,255,255));
		MapMainProperties main = new MapMainProperties();
		MapFieldProperties field = new MapFieldProperties();
		add(main);
		add(field);
		main.setLocation(10, 10);
		field.setLocation(10, 110);
		setVisible(false);
	}

	public static int getGuiWidth() {
		return guiWidth;
	}

	
}
