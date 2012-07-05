package de.bomberman.result;

import java.awt.Color;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilePermission;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.bomberman.game.score.SingleScoreBoard;
import de.bomberman.main.ScoreVO;

/** Beinhaltet das Einzelspieler Resultat und die Highscores
 * @author Gruppe 44 
 *
 */
public class ResultPanel extends JPanel {
	
	private static final int HIGHSCORE_CNT = 15;
	
	private JLabel timePointResult;
	private JLabel killNPCResult;
	private JLabel totalPointResult;
	public ResultPanel(long timePoints, ScoreVO vo) {
		long totalpoints = timePoints;
		
		setBackground(Color.YELLOW);
		setSize(500, 300);
		
		timePointResult = new JLabel("Time Points: "+timePoints);
		timePointResult.setVisible(true);
		timePointResult.setForeground(Color.BLACK);
		
		int killScore;
		if(vo != null) {
			killScore = vo.getScore()[0]*1000;
			totalpoints += killScore;
		} else {
			killScore = 0;
		}
		
		killNPCResult = new JLabel("NPC Kill-Points: "+killScore);
		killNPCResult.setVisible(true);
		killNPCResult.setForeground(Color.BLACK);
		
		totalPointResult = new JLabel("Total Points: "+totalpoints);
		totalPointResult.setVisible(true);
		totalPointResult.setForeground(Color.BLACK);
		
		add(totalPointResult);
		add(timePointResult);
		add(killNPCResult);
		
		loadHighscore(totalpoints);
		
		setVisible(true);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		doLayout();
	}
	
	
	/** Läd die Highscore aus der "highscore.properties" Datei, und sortiert die
	 * werte aus dieser Datei mit der Aktuellen Punktzahl um aus diesen dann eine Neue
	 * Highscore Datei "highscore.properties" zu erstellen.
	 * @param timePoints
	 */
	public void loadHighscore(long timePoints) {
		
		File file = new File("highscore.properties");
		
		Properties prop = new Properties();
		try {
			prop.load(new BufferedInputStream(new FileInputStream(file)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		long[] highscore = new long[HIGHSCORE_CNT];
		for(int i = 0; i < HIGHSCORE_CNT; i++) {
			highscore[i] = Long.parseLong(prop.getProperty(""+(i+1)));
		}
		
		highscore = bubbelSort(highscore, timePoints);
		
		prop = new Properties();
		
		for(int i = 0; i < HIGHSCORE_CNT;i++)
			prop.put(Integer.toString(i+1), Long.toString(highscore[i]));
		
		try {
			prop.store(new BufferedOutputStream(new FileOutputStream(file)), "Highscore");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(int i = 0; i < HIGHSCORE_CNT; i++) {
			add(new JLabel((i+1)+": "+highscore[i]));
		}
		
		
	}


	/** Sortiert das highscore Array (alter Highscore)
	 * zusammen mit totalPoints und gibt eine Array ohne den kleinsten Wert zurück.
	 * Für den neuen Highscore.
	 * @param highscore
	 * @param totalPoints
	 * @return
	 */
	private long[] bubbelSort(long[] highscore, long totalPoints) {
		long  tempScore[] = new long[HIGHSCORE_CNT+1];
		
		for( int i = 0; i < HIGHSCORE_CNT; i++)
			tempScore[i] = highscore[i];
		
		tempScore[HIGHSCORE_CNT] = totalPoints;
		boolean finish = false;
		while(!finish) {
			finish = true;
			for(int i = 0; i < HIGHSCORE_CNT; i++) {
				if(tempScore[i] < tempScore[i+1]) {
					long temp = tempScore[i];
					tempScore[i] = tempScore[i+1];
					tempScore[i+1] = temp;
					finish = false;
				}
			}
		}
		
		long newScore[] = new long[HIGHSCORE_CNT];
		for ( int i = 0; i < HIGHSCORE_CNT; i++ ) {
			newScore[i] = tempScore[i];
		}
		
		return newScore;
		
	}
}
