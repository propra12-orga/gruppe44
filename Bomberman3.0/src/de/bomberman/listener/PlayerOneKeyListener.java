package de.bomberman.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.TreeSet;

import de.bomberman.items.Bomb;
import de.bomberman.main.BombermanMain;
import de.bomberman.main.Player;
import de.bomberman.network.BombermanClient;
import de.bomberman.network.BombermanServer;
import de.bomberman.playground.LogicField;
import de.bomberman.playground.Playground;

public class PlayerOneKeyListener extends Thread implements KeyListener{
	  private static final int BOMB_EXPLO_TIME = 2;
	private static final int BOMB_RADIUS = 5;
	// set of key codes currently pressed down
    private static TreeSet<Integer> keysDown = new TreeSet<Integer>();
    private static TreeSet<Integer> virtualKeysDown = new TreeSet<Integer>();
    private static int delay = 120;
    
	@Override
	public void keyPressed(KeyEvent arg0) {
		if(!Playground.getPlayers()[0].isDead()) {
			keysDown.add(arg0.getKeyCode());
			System.out.println(arg0.getKeyCode());
		}
	}
	
	public static void virtualKeyPressed(int actionCode){
		switch(actionCode) {
		case 1:
			virtualKeysDown.clear();
			virtualKeysDown.add(KeyEvent.VK_D);
			break;
		case 2:
			virtualKeysDown.clear();
			virtualKeysDown.add(KeyEvent.VK_S);
			break;
		case 3:
			virtualKeysDown.clear();
			virtualKeysDown.add(KeyEvent.VK_A);
			break;
		case 4:
			virtualKeysDown.clear();
			virtualKeysDown.add(KeyEvent.VK_W);
			break;
		case 5:
			virtualKeysDown.clear();
			virtualKeysDown.add(KeyEvent.VK_SPACE);
			break;
		case 6:
			virtualKeysDown.clear();
			break;
		}
	}
    public static boolean isKeyPressed(int keycode) {
    	return keysDown.contains(keycode);
    }
    public static boolean isVirtualKeyPressed(int keycode) {
    	return virtualKeysDown.contains(keycode);
    }

	

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	@Override
    public void keyReleased(KeyEvent e) {
		say(6);
		if(e.getKeyChar() != KeyEvent.VK_SPACE)
			keysDown.remove(e.getKeyCode());
    }
	
	@Override
	public void run(){
		
		Thread t = currentThread(); // weil nicht ganz billig
		while(true) {
			try {
				t.sleep(delay);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			if (isKeyPressed(KeyEvent.VK_SPACE) || isVirtualKeyPressed(KeyEvent.VK_SPACE)) {
				say(5);
				Player[] players = Playground.getPlayers();
				LogicField field = players[0].getField();
				if (field != null && players[0] != null) {
						field.setBomb(new Bomb(field, BOMB_RADIUS, BOMB_EXPLO_TIME, players[0])); 
						keysDown.remove(KeyEvent.VK_SPACE);
					if (BombermanMain.DEBUG)
						System.out.println("Bomb placed by Player 1 . . .");
				}
			}
			
			if (isKeyPressed(KeyEvent.VK_A) || isVirtualKeyPressed(KeyEvent.VK_A)) {
				say(3);
				if(move(-1,0) && BombermanMain.DEBUG)
					System.out.println("Player goes left. . .");
				else
					if(BombermanMain.DEBUG)
						System.out.println("You cannot go here, dumbass!");
			}
			
			if (isKeyPressed(KeyEvent.VK_D ) || isVirtualKeyPressed(KeyEvent.VK_D)) {
				say(1);
				if(move(1,0) && BombermanMain.DEBUG)
					System.out.println("Player goes right. . .");
				else
					if(BombermanMain.DEBUG)
						System.out.println("You cannot go here, dumbass!");
			}
			
			if (isKeyPressed(KeyEvent.VK_S) || isVirtualKeyPressed(KeyEvent.VK_S)) {
				say(2);
				if(move(0,1) && BombermanMain.DEBUG)
					System.out.println("Player goes down. . .");
				else
					if(BombermanMain.DEBUG)
						System.out.println("You cannot go here, dumbass!");
			}
		
			if (isKeyPressed(KeyEvent.VK_W ) || isVirtualKeyPressed(KeyEvent.VK_W)) {
				say(4);
				if(move(0,-1) && BombermanMain.DEBUG)
					System.out.println("Player goes up. . .");
				else
					if(BombermanMain.DEBUG)
						System.out.println("You cannot go here, dumbass!");
			}
			
			/*//SO KÖNNTE MAN DIAGONAL GEHEN (BEISPIEL)... NICHT FÜRS EGTL SPIEL VORGESEHEN
			if (isKeyPressed(KeyEvent.VK_L)) {
				if(move(1,1) && BombermanMain.DEBUG)
					System.out.println("Player goes diaDings. . .");
				else
					if(BombermanMain.DEBUG)
						System.out.println("You cannot go here, dumbass!");
			}
			*/
		}
	}
	
	
	/**
	 * @param i
	 *  1: move right
	 *  2: move down
	 *  3: move left 
	 *  4: move up
	 *  5: set bomb
	 */
	private void say(int i) {
		if( BombermanMain.isServer() ) {
			int xPos = Playground.getPlayers()[0].getField().getPositionX();
			int yPos = Playground.getPlayers()[0].getField().getPositionY();
			BombermanServer.submit(1, i, xPos, yPos);
		}
		/*if( BombermanMain.isClient() ) {
			int xPos = Playground.getPlayers()[0].getField().getPositionX();
			int yPos = Playground.getPlayers()[0].getField().getPositionY();
			BombermanClient.submit(1, i, xPos, yPos);
		}*/
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
		if (field != null && players[0] != null) {
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
		else
			return false;
	}
	
	//getter and setter
	public static int getDelay() {
		return delay;
	}

	public static void setDelay(int delay) {
		PlayerOneKeyListener.delay = delay;
	}

	public static void setPosition(int xPosition, int yPosition) {
		Player player = Playground.getPlayers()[0];
		player.getField().setPlayer(null);
		Playground.getFields()[xPosition][yPosition].setPlayer(player);
	}
}
