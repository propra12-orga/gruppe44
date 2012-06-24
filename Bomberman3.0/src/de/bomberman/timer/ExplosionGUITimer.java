package de.bomberman.timer;

import de.bomberman.gui.FieldGUI;
/**
 * @author Giulia Kirstein (2019594)
 * Zeitmesser der Explosion der Bombe
*/

public class ExplosionGUITimer extends Thread {
	private static final long DELETE_TIME = 500;
	private FieldGUI field;
	 
	public ExplosionGUITimer (FieldGUI field) {
		this.field = field;
		start();
	}
	
/**
 * Startet wenn der Timer abgelaufen ist
*/
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
