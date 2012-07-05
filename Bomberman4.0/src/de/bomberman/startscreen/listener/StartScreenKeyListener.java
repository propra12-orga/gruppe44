package de.bomberman.startscreen.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.TreeSet;

import de.bomberman.main.BombermanMain;
import de.bomberman.startscreen.gui.StartscreenGUI;


/**KeyListener für den StartScreen
 * @author
 *
 */
public class StartScreenKeyListener extends Thread implements KeyListener {
	
	private static final int MULTIPLAYER = 1;

	private static final int SINGELPLAYER = 0;

	private static final int NETWORK = 2;
	
	private static final int HIGHSCORE = 4;
	
	private static final int SETTINGS = 5;

	private final boolean DEBUG = false;
	
	private boolean active = false;
	
	private static TreeSet<Integer> keysDown = new TreeSet<Integer>();
	
	private Thread t;
	private StartscreenGUI gui;
	
	public StartScreenKeyListener(StartscreenGUI gui) {
		this.gui = gui;
		this.t = Thread.currentThread();
		start();
	}
	
	public void keyPressed(KeyEvent arg0) {
		if( active )
			keysDown.add(arg0.getKeyCode());
	}
	@Override
    public void keyReleased(KeyEvent e) {
			keysDown.remove(e.getKeyCode());
    }

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	/** Wenn Enter gedrückt wird, wird das Programm entsprechend der
	 *  Menüauswähl weiter geleitet
	 * @see java.lang.Thread#run()
	 **/
	@Override
	public void run() {
		
		System.out.println("StartScreen KL Run");
		
		while(true) {
			try {
				t.sleep(100);
			} catch (InterruptedException e) {
				if( DEBUG )
					System.out.println("Startscreen Listener Stops");
					stop();
			}
			//System.out.println("SPAM");
			if( keysDown.contains(KeyEvent.VK_DOWN)) {			
				System.out.println("Menu DOWN");
				gui.menuDown();
			} else if( keysDown.contains(KeyEvent.VK_UP)) {
				System.out.println("Menu UP");
				gui.menuUp();
			} else if ( keysDown.contains(KeyEvent.VK_ENTER)) {
				int tempID = gui.getID();
					if ( gui.getID() == MULTIPLAYER ) {
						startMultiplayer();
					} else if ( gui.getID() == SINGELPLAYER ) {
						startSingelplayer();
					} else if ( gui.getID() == NETWORK ) {
						startNetwork();
					} else if( gui.getID() == HIGHSCORE ) {
						showHighscore();
					} else if (gui.getID() == SETTINGS ) {
						showSettings();
					}
			}
		}
	}

	private void showSettings() {
		//TO_DO
		
	}

	private void showHighscore() {
		BombermanMain.showSingelPlayerResult(0, null);
		active = false;
	}

	private void startNetwork() {
		BombermanMain.startNetWorkPanel();
		active = false;
	}

	private void startSingelplayer() {
		BombermanMain.startSingelPlayer();
		active = false;
	}

	private void startMultiplayer() {
		BombermanMain.startMulitplayer();
		active = false;
	}

	public  void setActive(boolean b) {
		active = b;
		
	}

}
