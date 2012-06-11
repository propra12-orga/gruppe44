package backups;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.TreeSet;

import de.bomberman.items.Bomb;
import de.bomberman.main.BombermanMain;
import de.bomberman.main.Player;
import de.bomberman.playground.LogicField;
import de.bomberman.playground.Playground;

public class PlayerOneKeyListener implements KeyListener{

	@Override
	public void keyReleased(KeyEvent arg0) {
		if( arg0.getKeyChar() == KeyEvent.VK_SPACE ) {
			Player[] players = Playground.getPlayers();
			LogicField field = players[0].getField();
			field.setBomb(new Bomb(field, 5, 2, players[0])); 
			if(BombermanMain.DEBUG)
				System.out.println("Bomb placed by Player 1 . . .");
		}
		
		if( arg0.getKeyChar() == KeyEvent.VK_A ) {
			if(move(-1,0) && BombermanMain.DEBUG)
				System.out.println("Player goes left. . .");
			else
				if(BombermanMain.DEBUG)
					System.out.println("You cannot go here, dumbass!");
		}
		
		if( arg0.getKeyChar() == KeyEvent.VK_D ) {
			if(move(1,0) && BombermanMain.DEBUG)
				System.out.println("Player goes right. . .");
			else
				if(BombermanMain.DEBUG)
					System.out.println("You cannot go here, dumbass!");
		}
		
		if( arg0.getKeyChar() == KeyEvent.VK_S ) {
			if(move(0,1) && BombermanMain.DEBUG)
				System.out.println("Player goes down. . .");
			else
				if(BombermanMain.DEBUG)
					System.out.println("You cannot go here, dumbass!");
		}
	
		if( arg0.getKeyChar() == KeyEvent.VK_W ) {
			if(move(0,-1) && BombermanMain.DEBUG)
				System.out.println("Player goes up. . .");
			else
				if(BombermanMain.DEBUG)
					System.out.println("You cannot go here, dumbass!");
		}
		
		//SO KÖNNTE MAN DIAGONAL GEHEN (BEISPIEL)... NICHT FÜRS EGTL SPIEL VORGESEHEN
		if( arg0.getKeyChar() == KeyEvent.VK_L ) {
			if(move(1,1) && BombermanMain.DEBUG)
				System.out.println("Player goes diaDings. . .");
			else
				if(BombermanMain.DEBUG)
					System.out.println("You cannot go here, dumbass!");
		}
	}
	
	
	/**
	 * example: move (1,0) move 1 field right
	 * 			move (-1,0) move 1 field left 
	 * 			move (0,1) move 1 field down
	 * 			move (0,-1) move 1 field up 
	 * @param i is the step-width in x 
	 * @param j is the step-width in y 
	 * @return
	 */
	private boolean move(int i, int j) {
		if(BombermanMain.DEBUG)
			System.out.println("call move("+i+","+j+")");
		Player[] players = Playground.getPlayers();
		LogicField field = players[0].getField();
		int x = field.getPositionX();
		int y = field.getPositionY();
		if ((i < 0 && field.getPositionX() > 0 ) 
				|| (i > 0 && field.getPositionX() < 14)
					|| (j < 0 && field.getPositionY() > 0)
						|| (j > 0 && field.getPositionY() < 14)) {
			if(BombermanMain.DEBUG)
				System.out.println("next field exist");
			LogicField field2 = Playground.getFields()[x+i][y+j];
			if (field2.getWall() == null && field2.getBomb() == null && field2.getPlayer() == null) {
				field2.setPlayer(players[0]);
				field.setPlayer(null);
				return true;
			} else {
				return false;
			}
		}  else {
			return false;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}


	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
