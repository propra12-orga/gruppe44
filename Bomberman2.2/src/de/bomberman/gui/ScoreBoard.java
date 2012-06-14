package de.bomberman.gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import de.bomberman.playground.Playground;


/**
 * @author Gruppe 44
 * Initialisierung des Scoreboards -> einzelne Panels fuer Player und ihre Scores
 */
public class ScoreBoard extends JPanel {
	private static SingleScoreBoard panelP1;
	private static SingleScoreBoard panelP2;
	private static SingleScoreBoard panelP3;
	private static SingleScoreBoard panelP4;

	public ScoreBoard() {
		setBackground(Color.GRAY);
		setLayout(new GridLayout(4,1));
		switch ( Playground.getCntPlayer() ) {
		case 4:
			panelP4 = new SingleScoreBoard(3);
			add(panelP4);
		case 3:
			panelP3 = new SingleScoreBoard(2);
			add(panelP3);
		case 2:
			panelP2 = new SingleScoreBoard(1);
			add(panelP2);
		case 1:
			panelP1 = new SingleScoreBoard(0);
			add(panelP1);
		}

	}
	
/**
 * Upload des initialiesierten Scoreboards
 */
	public void uploadScoreBoard() {
		switch (Playground.getCntPlayer()) {
		case 4:
			panelP4.uploadPanel();
		case 3:
			panelP3.uploadPanel();
		case 2:
			panelP2.uploadPanel();
		case 1:
			panelP1.uploadPanel();
		}
	}
}
