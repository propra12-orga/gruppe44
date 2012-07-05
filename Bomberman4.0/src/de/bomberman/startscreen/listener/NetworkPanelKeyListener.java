package de.bomberman.startscreen.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.TreeSet;

import de.bomberman.main.BombermanMain;
import de.bomberman.startscreen.gui.NetworkPanel;

/**KeyListener für das NetworkPanel
 * @author Nuni
 *
 */
public class NetworkPanelKeyListener extends Thread implements KeyListener {


		private static final int CLIENT = 1;

		private static final int SERVER = 0;


		private final boolean DEBUG = false;
		
		private static TreeSet<Integer> keysDown = new TreeSet<Integer>();
		
		private Thread t;
		private NetworkPanel gui;
		
		public NetworkPanelKeyListener(NetworkPanel gui) {
			this.gui = gui;
			this.t = Thread.currentThread();
			start();
		}
		
		public void keyPressed(KeyEvent arg0) {
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
					System.out.println("Enter pressed!");
					int tempID = gui.getID();
						if ( gui.getID() == CLIENT ) {
							startAsClient();
						} else if ( gui.getID() == SERVER ) {
							startAsServer();
						} 
			}
		}



	}

		private void startAsServer() {
			System.out.println("Start as Server, Listener");
			BombermanMain.startAsServer();
		}

		private void startAsClient() {
			System.out.println("Start as Client, Listener");
			BombermanMain.startAsClient();
		}


}
