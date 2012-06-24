package de.bomberman.mapeditor.gui;

import java.awt.Color;
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class WallPanel extends JPanel {

	public WallPanel(LayoutManager layout){
		
		super(layout);
		setBackground(Color.WHITE);
		JRadioButton rdWall = new JRadioButton("Wall");
		rdWall.setBackground(Color.WHITE);
		JRadioButton rdBreakable = new JRadioButton("Breakabble");
		rdBreakable.setBackground(Color.WHITE);
		JTextField wallPower = new JTextField(""+3);
		add(rdWall);
		add(rdBreakable);
		add(new JLabel("Wall-Power: "));
		add(wallPower);
		setVisible(true);
	}
}
