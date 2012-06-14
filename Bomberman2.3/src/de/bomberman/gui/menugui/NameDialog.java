package de.bomberman.gui.menugui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextArea;

import de.bomberman.playground.Playground;

public class NameDialog extends JDialog {
	
	private int playerID;
	private JTextArea name;
	
	public NameDialog(int playerID) {
		this.playerID = playerID;
		setTitle("name player " + this.playerID);
		setLayout(new GridLayout(2,1));
		
		name = new JTextArea(Playground.getPlayers()[playerID-1].getName());
		JButton submit = new JButton("submit");
		submit.addActionListener(getSubmitListerner());
		add(name);
		add(submit);
		setVisible(true);
	}

	private ActionListener getSubmitListerner() {
		
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Playground.getPlayers()[playerID-1].setName(name.getText());
				setVisible(false);
				dispose();
				
			}
		};
	}
}
