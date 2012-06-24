package backups;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerTwoKeyListener implements KeyListener{

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if( arg0.getKeyChar() == KeyEvent.VK_K ) {
			System.out.println("Bomb placed by Player TWO . . .");
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

}
