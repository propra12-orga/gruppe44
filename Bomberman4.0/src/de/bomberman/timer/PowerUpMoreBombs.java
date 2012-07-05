package de.bomberman.timer;

import de.bomberman.player.PlayerOneKeyListener;
import de.bomberman.player.PlayerOneKeyListenerServer;
import de.bomberman.player.PlayerTwoKeyListener;
import de.bomberman.player.PlayerTwoKeyListenerServer;

/** Ermöglicht dem Spieler in kürzeren Intervallen neue Bomben zulegen
 * Nach ablauf des Timers setzt er die Verzögerung zwichen dem Bomben legen wieder
 * auf Normalzustand
 * @author Nuni
 *
 */
public class PowerUpMoreBombs extends Thread {

	private int ID;

	public PowerUpMoreBombs(int ID) {
		this.ID = ID;
		start();
	}
	
	public void run() {
		switch(ID) {
		case 1:
			PlayerOneKeyListener.moreBombs();
			PlayerOneKeyListenerServer.moreBombs();
			Thread.currentThread();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			PlayerOneKeyListener.normalBombs();
			PlayerOneKeyListenerServer.normalBombs();
			break;
		case 2:
			PlayerTwoKeyListener.moreBombs();
			PlayerTwoKeyListenerServer.moreBombs();
			Thread.currentThread();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			PlayerTwoKeyListener.normalBombs();
			PlayerTwoKeyListenerServer.normalBombs();
			break;
		}
	}
}
