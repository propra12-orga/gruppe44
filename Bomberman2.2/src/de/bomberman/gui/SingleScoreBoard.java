package de.bomberman.gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.bomberman.playground.Playground;

/**
 * 
 * @author Gruppe44
 * Scoreboard fuer jeden einzelnen Spieler
 * 
 */

public class SingleScoreBoard extends JPanel {

	private int ID;
	public static final String DEATH = "DEATH"; 
	public static final String KILLS = "KILLS";
	public static final String POSITION = "POSITION";
	
		
	public SingleScoreBoard(int ID) {
		setLayout(new GridLayout(4,2));
		this.ID = ID;
		setBorder(BorderFactory.createLineBorder(Color.black));
		Setzen();
	}
	/**
	 * Adden von Kills, Deaths und Ranks
	 */
	
	// Positonen = Rank ?
	
	private void Setzen() {
		add(new JLabel(Playground.getPlayers()[ID].getName()));
		add(new JLabel());
		add(new JLabel(KILLS));
		add(new JLabel(Integer.toString(Playground.getPlayers()[ID].getScore())));
		add(new JLabel(DEATH));
		add(new JLabel(Integer.toString(Playground.getPlayers()[ID].getDeads())));
		add(new JLabel(POSITION));
		add(new JLabel("1st"));
	}
	
	/**
	 * Loeschen aller Panels, neue Panels werden gesetzt
	 * Upload der Panels
	 * Aktualisieren des Scoreboards
	 */
	public void uploadPanel() {
		removeAll();
		Setzen();
		revalidate();
	}
}
