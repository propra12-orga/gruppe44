package de.bomberman.timer;

import de.bomberman.player.PlayerOneKeyListener;
import de.bomberman.player.PlayerOneKeyListenerServer;
import de.bomberman.player.PlayerTwoKeyListener;
import de.bomberman.player.PlayerTwoKeyListenerServer;

/** Regelt das Bombenlegen, Timer verzögert das Bombenlegen
 * @author Nuni
 *
 */
public class AllowBombTimer extends Thread {
	private int ID;
	public AllowBombTimer(int i) {
		this.ID = i;
		start();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		switch(ID) {
		case 1:
			int bombDelayOne = PlayerOneKeyListener.getBombDelay();
			PlayerOneKeyListener.setAlowBomb(false);
			PlayerOneKeyListenerServer.setAlowBomb(false);
			Thread.currentThread();
			try {
				Thread.sleep(bombDelayOne);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			PlayerOneKeyListener.setAlowBomb(true);
			PlayerOneKeyListenerServer.setAlowBomb(true);
		case 2:
			int bombDelayTwo = PlayerTwoKeyListener.getBombDelay();
			PlayerTwoKeyListener.setAlowBomb(false);
			PlayerTwoKeyListenerServer.setAlowBomb(false);
			Thread.currentThread();
			try {
				Thread.sleep(bombDelayTwo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			PlayerTwoKeyListener.setAlowBomb(true);
			PlayerTwoKeyListenerServer.setAlowBomb(true);
		}

	}
}
