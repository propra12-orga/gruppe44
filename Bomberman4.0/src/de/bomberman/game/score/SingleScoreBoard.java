package de.bomberman.game.score;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.bomberman.main.BombermanMain;
import de.bomberman.main.ScoreVO;
import de.bomberman.playground.Playground;

/** einzelne Panels welche die Statistik der einzelnen Spieler 
 * wiedergeben
 * @author Gruppe 44 
 *
 */
public class SingleScoreBoard extends JPanel {

	private int ID;
	public static final String DEATH = "DEATH"; 
	public static final String KILLS = "KILLS";
	public static final String POSITION = "POSITION";
	
		
	/** Konstruktor eine SingelScoreBoard, mit Startwerten
	 * @param ID
	 */
	public SingleScoreBoard(int ID) {
		setVisible(true);

		setLayout(new GridLayout(4,2));
		this.ID = ID;
		setBorder(BorderFactory.createLineBorder(Color.red));
	}

	/** Konstruktor eine SingelScoreBoards, "mit Endwerten" 
	 * gedacht als Abschließende Statisktik
	 * @param ID die ID des Spielers
	 * @param scoreVO enthält die Statsitik des Spielers
	 */
	public SingleScoreBoard(int ID, ScoreVO scoreVO) {
		setVisible(true);

		setSize(250, 100);
		setLayout(new GridLayout(4,2));
		this.ID = ID;
		setBorder(BorderFactory.createLineBorder(Color.red));
		setzen(scoreVO.getKills()[ID], scoreVO.getDeads()[ID], scoreVO.getDeads()[ID]);
	}

	/**
	 * Für die Statistik wärend des Spiels
	 * Fügt JLabel zum Panel hinzu und lässt diese neu darstellen
	 */
	public void setzen() {
		add(new JLabel(BombermanMain.getPlayers()[ID].getName()));
		add(new JLabel());
		add(new JLabel(KILLS));
		add(new JLabel(Integer.toString(BombermanMain.getPlayers()[ID].getScore())));
		add(new JLabel(DEATH));
		add(new JLabel(Integer.toString(BombermanMain.getPlayers()[ID].getDeads())));
		add(new JLabel(POSITION));
		add(new JLabel("1st"));
		revalidate();
		doLayout();
	}
	
	/**
	 * Für die Statsitik nach dem Spiel
	 * Fügt JLabel zum Panel hinzu und lässt diese neu darstellen
	 */
	public void setzen(int kills, int deads, int score) {
		add(new JLabel(""));
		add(new JLabel());
		add(new JLabel(KILLS));
		add(new JLabel(Integer.toString(kills)));
		add(new JLabel(DEATH));
		add(new JLabel(Integer.toString(deads)));
		add(new JLabel(POSITION));
		add(new JLabel("1st"));
		revalidate();
		doLayout();
	}
	
	/**
	 * steckt die Werte des Panels neu da (Aktualisieren)
	 */
	public void uploadPanel() {
		removeAll();
		setzen();
		revalidate();
	}
}
