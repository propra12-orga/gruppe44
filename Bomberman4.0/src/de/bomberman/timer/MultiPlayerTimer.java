package de.bomberman.timer;

import java.net.MalformedURLException;

import de.bomberman.main.BombermanMain;
import de.bomberman.sound.SoundBib;

/** Timer für die Multiplayer Spielzeit, ist die Spielzeit vorbei
 * sorgt dieser Timer für das Beenden des Spiels
 * @author Nuni
 *
 */
public class MultiPlayerTimer extends Thread {
	private int time;
	public MultiPlayerTimer(int time) {
		this.time = time;
		start();
	}
	
	public void run() {
		Thread.currentThread();
		try {
			Thread.sleep((time -15 ) * 1000);
			System.out.println("Hurry Up!");
			SoundBib.playHurryUp();
			Thread.sleep(15000);
			SoundBib.playTimeOver();
			System.out.println("Time Over!");
			BombermanMain.stopGame(null);
		} catch (InterruptedException e) {
			e.printStackTrace();
			stop();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
