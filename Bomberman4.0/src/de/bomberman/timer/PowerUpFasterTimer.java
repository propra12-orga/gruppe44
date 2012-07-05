package de.bomberman.timer;

import de.bomberman.player.PlayerOneKeyListener;
import de.bomberman.player.PlayerOneKeyListenerServer;
import de.bomberman.player.PlayerTwoKeyListener;
import de.bomberman.player.PlayerTwoKeyListenerServer;

/** setzt die Geschwindigkeit des Spielers nach Oben und nach einer gewissen
 * Zeit wieder auf Normalzustand
 * @author Nuni
 *
 */
public class PowerUpFasterTimer extends Thread {
	private int ID;
	public PowerUpFasterTimer(int ID) {
		this.ID = ID;
		start();
	}
	
	public void run() {
	switch (ID) {
	case 1:
		PlayerOneKeyListener.fastSpeed();
		PlayerOneKeyListenerServer.fastSpeed();
		Thread.currentThread();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PlayerOneKeyListener.normalSpeed();
		PlayerOneKeyListenerServer.normalSpeed();
		break;
	case 2:
		PlayerTwoKeyListener.fastSpeed();
		PlayerTwoKeyListenerServer.fastSpeed();
		Thread.currentThread();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PlayerTwoKeyListener.normalSpeed();
		PlayerTwoKeyListenerServer.normalSpeed();
		break;
		
	}
	}
}
