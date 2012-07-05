package de.bomberman.timer;

/** Verzögert das Explodieren einer Bombe welche durch eine
 *  Bombe zum Explodieren angeregt wird
 * @author 
 *
 */
public class InterruptBombTimer extends Thread { 
	private static final long CHAIN_EXPLOSION_DELAY = 500;

	BombTimer timer;
	public InterruptBombTimer(BombTimer timer){
		this.timer = timer;
		start();
	}
	
	@Override
	public void run(){
		Thread.currentThread();
		try {
			Thread.sleep(CHAIN_EXPLOSION_DELAY);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		timer.interrupt();
	}
}
