package de.bomberman.game;

import javax.swing.JPanel;

import de.bomberman.game.score.ScoreBoard;
import de.bomberman.main.BombermanMain;

/** Visualisiert ei Multiplayer Spiel
 * @author Gruppe 44 
 *
 */
public class MultiPlayerJPanel extends GamePanel {
	private ScoreBoard sb;
	
	public MultiPlayerJPanel(MultiPlayerGame game) {
		super(game);
		
		sb = new ScoreBoard(game.getPlayerCount());
		int fieldSize = BombermanMain.FIELD_SIZE;
		
		sb.setLocation(width*fieldSize + 20, 0);
		sb.setSize(200, height*fieldSize);
		sb.setVisible(true);
		add(sb);
	}
	/**
	 * läd das ScoreBoard (die Statistik) neu
	 */
	@Override
	public void uploadScoreBoard() {
		sb.uploadScoreBoard();
	}

}
