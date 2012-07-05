package de.bomberman.timer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.TreeSet;

import de.bomberman.main.BombermanMain;

/** momentan kein aktiver Inhalt des Projects
 * @author Nuni
 *
 */
public class WaitForEnter extends Thread implements KeyListener {

	private static TreeSet<Integer> keysDown = new TreeSet<Integer>();
	private static boolean run;
	
	public WaitForEnter() {
	System.out.println("New WaitForEnter");
	BombermanMain.setKeyListener(this);
	run = true;
	}
	
	@Override
	public void run() {
		
		while( run ) {
			//System.out.println("run: "+run);
			try {
				Thread.currentThread();
				Thread.sleep(100);
				if( keysDown.contains(KeyEvent.VK_ENTER)) {
					run = false;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ThreadDeath td) {
				System.out.println("TD");
				run = false;
				
			
			}
		}
		keysDown.clear();
		BombermanMain.unKeyListener();
		BombermanMain.showWaiting();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//System.out.println("e: "+(char)e.getKeyChar());
		keysDown.add(e.getKeyCode());
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
