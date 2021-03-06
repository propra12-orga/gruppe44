package de.bomberman.timer;

import de.bomberman.field.FieldGUI;

/** nach Ablauf dieses Timers wird das Explosion Image vom FieldGUI
 *  wieder herunter genommen
 * @author
 *
 */
public class ExplosionGUITimer extends Thread {
	private static final long DELETE_TIME = 500;
	private FieldGUI field;
	 
	public ExplosionGUITimer (FieldGUI field) {
		this.field = field;
		start();
	}
	
	
	public void run() {
		Thread.currentThread();
		try {
			Thread.sleep(DELETE_TIME);
		}
		catch (InterruptedException e) {
		}
		field.setExplosionVertikal(false);
		field.setExplosionHorizontal(false);
		field.setExplosionCross(false);
		
	}
}
