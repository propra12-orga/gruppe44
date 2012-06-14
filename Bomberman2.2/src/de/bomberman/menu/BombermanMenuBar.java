package de.bomberman.menu;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import de.bomberman.gui.MainGUI;
import de.bomberman.gui.menugui.NameDialog;
import de.bomberman.gui.menugui.PropertiesDialog;


/**
 * 
 * @author Gruppe44
 * Dropdown-Menue mit einzelnen Auswahlmöglichkeiten wird erstellt
 *
 */
public class BombermanMenuBar extends JMenuBar {
	public BombermanMenuBar(){
		JMenu data = new JMenu("data");

		JMenu names = new JMenu("names");
		JMenu nameP1 = new JMenu("player 1");
		JMenu nameP2 = new JMenu("player 2");
		JMenu nameP3 = new JMenu("player 3");
		JMenu nameP4 = new JMenu("player 4");
		names.add(getName(1));
		names.add(getName(2));
		names.add(getName(3));
		names.add(getName(4));
		data.add(getProperties());
		data.add(names);
		data.add(getExit());
		add(data);
	}
	
	/**
	 * Schliessen des Spiels und Fenster durch Klicken des Exit-Buttons
	 * @return action 
	 */
	private AbstractAction getExit(){
	 	AbstractAction action = new AbstractAction("exit") {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		};
		return action;
	}

	/**
	 * Ermoeglicht in "Properites" die Spielfeldgroesse und Spieleranzahl zu aendern
	 * @return action 
	 */
	private AbstractAction getProperties(){
	 	AbstractAction action = new AbstractAction("Properties") {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JDialog  dialog = new PropertiesDialog();
				dialog.setSize(PropertiesDialog.COLUMNS*250, PropertiesDialog.ROWS*30);
			}
		};
		return action;
	}
	
/**
 * Ermoeglicht die Auswahl der vier verschiedenen Spieler um Namen zu aendern/zuzuordnen
 * @param playerID (gibt die Spielernummer wieder)
 * @return action
 */
	private AbstractAction getName(final int playerID){
	 	AbstractAction action = new AbstractAction("Player " + playerID) {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JDialog  dialog = new NameDialog(playerID);
				dialog.setSize(250, 30);
			}
		};
		return action;
	}
}


