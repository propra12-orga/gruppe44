package de.bomberman.timer;

import de.bomberman.ki.KI;

/** setzt die Geschwindigkeit der KI nach Unten und nach einer gewissen
 * Zeit wieder auf Normalzustand
 * @author Nuni
 *
 */
public class PowerUpKiSlower extends Thread {

	public PowerUpKiSlower() {
		start();
	}
	
	public void run() {
		System.out.println("Ki Speed down!");
		KI.slowerSpeed();
		Thread.currentThread();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Normal Speed!");
		KI.normalSpeed();
	}
}
