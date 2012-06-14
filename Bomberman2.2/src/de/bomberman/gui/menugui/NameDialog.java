package de.bomberman.gui.menugui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextArea;

import de.bomberman.playground.Playground;

/** 
 * @author Gruppe 44
 * Diese Klasse ermöglicht im Menü das Vergeben eines Namens an die einzelnen Spieler (playerID)
 */
public class NameDialog extends JDialog {
	
	private int playerID;
	private JTextArea name;
	
/**
 * Ermöglicht dem User einen Spieler auszuwählen um ihm einen individuellen Namen zu geben
 * Layout des Eingabebereiches: 1 Spalte, 2 Zeilen
 * JButton -> Ermöglicht Bestätigung des Namens via Submit-Button
 * @param playerID bezeichnet Nummer des Spielers
 */
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

/**
 * ActionListener erlaubt Eingabe des Spielernamens
 * 
 */
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
