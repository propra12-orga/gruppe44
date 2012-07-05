package de.bomberman.result;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Hashtable;
import java.util.TreeSet;

import de.bomberman.main.BombermanMain;

/** Bringt uns zurueck zum Startbildschirm nach dem Enterdruecken, waehrend
 * wir auf einem Ereignis Panel sind.
 * @author Gruppe 44 
 *
 */
public class ResultListener extends Thread implements KeyListener {

	private static TreeSet<Integer> keysDown;
	boolean active = false;
	
	public ResultListener() {
		active = true;
	}
	
	
	
	public void run() {
		keysDown = new TreeSet<Integer>();
		while(true) {
			if(keysDown.contains(KeyEvent.VK_ENTER)) {
				keysDown.clear();
				BombermanMain.resetResultView();
				break;
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//keysDown.add(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(active && e.getKeyChar() == KeyEvent.VK_ENTER ) {
			BombermanMain.resetResultView();
			active = false;
		}
		
	}
}
