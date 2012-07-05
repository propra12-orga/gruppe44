package de.bomberman.result;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import de.bomberman.game.Game;
import de.bomberman.game.SingelPlayerGame;
import de.bomberman.game.score.SingleScoreBoard;

/** Panel welches das MultiPlayer ergebnis beinhaltet
 * @author Gruppe 44 
 *
 */
public class MPResultPanel extends JPanel {

	private SingleScoreBoard p1Panel;
	private SingleScoreBoard p2Panel;
	private SingleScoreBoard p3Panel;
	private SingleScoreBoard p4Panel;
	
	public MPResultPanel(Game game) {
		setSize(500, 200);
		setLayout(new GridLayout(2,2));
		setBackground(Color.YELLOW);
		
		setVisible(true);
		
		p1Panel = new SingleScoreBoard(0, game.getScoreVO());
		p2Panel = new SingleScoreBoard(1, game.getScoreVO());
		p3Panel = new SingleScoreBoard(2, game.getScoreVO());
		p4Panel = new SingleScoreBoard(3, game.getScoreVO());
		add(p1Panel);
		add(p2Panel);
		add(p3Panel);
		add(p4Panel);
		doLayout();
	}
}
