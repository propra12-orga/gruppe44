package de.bomberman.startscreen.gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import de.bomberman.main.BombermanMain;

public class SettingsPanel extends JPanel {

	private JTextField widthField; 
	private JTextField heightField;
	private JButton button;
	public SettingsPanel() {
		setSize(500, 300);
		setVisible(true);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.YELLOW);
		heightField = new JTextField("20");
		heightField.setSize(100, 30);
		heightField.setHorizontalAlignment(SwingConstants.CENTER);
		widthField = new JTextField("30");
		widthField.setSize(100, 30);
		widthField.setHorizontalAlignment(SwingConstants.CENTER);
		button = new JButton("Submit");
		button.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				BombermanMain.setSize(Integer.parseInt(widthField.getText()), Integer.parseInt(heightField.getText()));
			}
		});
		add(new JLabel("Height:"));
		add(heightField);
		add(new JLabel("Width:"));
		add(widthField);
		add(button);
		
		doLayout();
	}
}
