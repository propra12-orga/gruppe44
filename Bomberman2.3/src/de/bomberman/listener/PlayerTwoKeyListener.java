package de.bomberman.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.TreeSet;

import de.bomberman.items.Bomb;
import de.bomberman.main.BombermanMain;
import de.bomberman.main.Player;
import de.bomberman.playground.LogicField;
import de.bomberman.playground.Playground;

public class PlayerTwoKeyListener extends Thread implements KeyListener{
	  // set of key codes currently pressed down
    private static TreeSet<Integer> keysDown = new TreeSet<Integer>();
    private static int delay = 120;
    
	@Override
	public void keyPressed(KeyEvent arg0) {
		if(!Playground.getPlayers()[1].isDead())
			keysDown.add(arg0.getKeyCode());
	}
	
    public static boolean isKeyPressed(int keycode) {
    	return keysDown.contains(keycode);
    }

	

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	@Override
    public void keyReleased(KeyEvent e) {
			keysDown.remove(e.getKeyCode());
    }
	
	@Override
	public void run(){
		while(true) {
			Thread.currentThread();
			try {
				Thread.sleep(this.delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (isKeyPressed(KeyEvent.VK_0)) {
				Player[] players = Playground.getPlayers();
				LogicField field = players[1].getField();
				if(field != null && players[1] != null){
						field.setBomb(new Bomb(field, 5, 2, players[1])); 
					if(BombermanMain.DEBUG)
						System.out.println("Bomb placed by Player 1 . . .");
				}
			}
			
			if (isKeyPressed(KeyEvent.VK_LEFT)) {
				if(move(-1,0) && BombermanMain.DEBUG)
					System.out.println("Player goes left. . .");
				else
					if(BombermanMain.DEBUG)
						System.out.println("You cannot go here, dumbass!");
			}
			
			if (isKeyPressed(KeyEvent.VK_RIGHT)) {
				if(move(1,0) && BombermanMain.DEBUG)
					System.out.println("Player goes right. . .");
				else
					if(BombermanMain.DEBUG)
						System.out.println("You cannot go here, dumbass!");
			}
			
			if (isKeyPressed(KeyEvent.VK_DOWN)) {
				if(move(0,1) && BombermanMain.DEBUG)
					System.out.println("Player goes down. . .");
				else
					if(BombermanMain.DEBUG)
						System.out.println("You cannot go here, dumbass!");
			}
		
			if (isKeyPressed(KeyEvent.VK_UP)) {
				if(move(0,-1) && BombermanMain.DEBUG)
					System.out.println("Player goes up. . .");
				else
					if(BombermanMain.DEBUG)
						System.out.println("You cannot go here, dumbass!");
			}
			
			//SO KÖNNTE MAN DIAGONAL GEHEN (BEISPIEL)... NICHT FÜRS EGTL SPIEL VORGESEHEN
			if (isKeyPressed(KeyEvent.VK_O)) {
				if(move(1,1) && BombermanMain.DEBUG)
					System.out.println("Player goes diaDings. . .");
				else
					if(BombermanMain.DEBUG)
						System.out.println("You cannot go here, dumbass!");
			}
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
		LogicField field = players[1].getField();
		if (field != null && players[1] != null) {
			int x = field.getPositionX();
			int y = field.getPositionY();
			if ((i < 0 && field.getPositionX() > 0 ) 
					|| (i > 0 && field.getPositionX() < (Playground.getSize()-1))
						|| (j < 0 && field.getPositionY() > 0)
							|| (j > 0 && field.getPositionY() < (Playground.getSize()-1))) {
				if(BombermanMain.DEBUG)
					System.out.println("next field exist");
				LogicField field2 = Playground.getFields()[x+i][y+j];
				if (field2.getWall() == null && field2.getBomb() == null && field2.getPlayer() == null) {
					field2.setPlayer(players[1]);
					field.setPlayer(null);
					return true;
				} else {
					return false;
				}
			}  else {
				return false;
			}
		}
		else
			return false;
		
	}

	//getter and setter
	public static int getDelay() {
		return delay;
	}

	public static void setDelay(int delay) {
		PlayerTwoKeyListener.delay = delay;
	}
}
