package de.bomberman.player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.TreeSet;

import de.bomberman.field.LogicField;
import de.bomberman.main.BombermanMain;
import de.bomberman.network.BombermanServer;
import de.bomberman.timer.AllowBombTimer;

	/**
	 * 
	 * @author Gruppe 44 
	 *
	 */
public class PlayerOneKeyListenerServer extends PlayerOneKeyListener implements KeyListener{
	private static final int BOMB_EXPLO_TIME = 2;
	private static final int BOMB_RADIUS = 5;
	private static final boolean DEBUG = false;
	// set of key codes currently pressed down
    private static TreeSet<Integer> keysDown = new TreeSet<Integer>();
    private static TreeSet<Integer> virtualKeysDown = new TreeSet<Integer>();
    private static int delay = 120;
    private static int bombDelay = 1000;
	private static boolean alowBomb = true;
    
	@Override
	public void keyPressed(KeyEvent arg0) {
		if(this.isAlive() && !BombermanMain.getPlayers()[0].isDead()) {
			keysDown.add(arg0.getKeyCode());
			
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
		//say(6);
		if(e.getKeyChar() != KeyEvent.VK_SPACE)
			keysDown.remove(e.getKeyCode());
    }
	/**
	 *  Spieler 1 wird durch die Tasten A, S, D, W und SPACE gesteuert
	 *  es wird ueberprueft ob der Spieler sich innerhalb des Spielfeldes bewegt, laesst den Spieler nicht
	 *  aus dem Spielfeld laufen
	 *  durch new AllowBombTimer(1) wird der AllowBombTimer gestartet, er setzt alowBomb auf flase, und nach einer gewissen
	 *  Zeit wieder auf true, damit der Spieler nur Bomben in einem
	 *  gewissen Zeitabstand legen kann
	 */
	@Override
	public void run(){
		if ( DEBUG ) {
			System.out.println("Start Player 1 KeyListener");
		}
		Thread t = currentThread(); // weil nicht ganz billig
		while(true) {
			try {
				t.sleep(delay);
			} catch (InterruptedException e1) {
				break;
			}
			if (isKeyPressed(KeyEvent.VK_SPACE) || isVirtualKeyPressed(KeyEvent.VK_SPACE)) {
				say(5);
				Player[] players = BombermanMain.getPlayers();
				LogicField field = players[0].getField();
				if (field != null && players[0] != null) {
					if(alowBomb) {
						field.setBomb(field, BOMB_RADIUS, BOMB_EXPLO_TIME, players[0]); 
						new AllowBombTimer(1);
					}
						keysDown.remove(KeyEvent.VK_SPACE);
					if (DEBUG)
						System.out.println("Bomb placed by Player 1 . . .");
				}
			}
			
			if (isKeyPressed(KeyEvent.VK_A) || isVirtualKeyPressed(KeyEvent.VK_A)) {
				say(3);
				if(move(-1,0) && DEBUG)
					System.out.println("Player goes left. . .");
				else
					if(DEBUG)
						System.out.println("You cannot go here, dumbass!");
			}
			
			if (isKeyPressed(KeyEvent.VK_D ) || isVirtualKeyPressed(KeyEvent.VK_D)) {
				say(1);
				if(move(1,0) && DEBUG)
					System.out.println("Player goes right. . .");
				else
					if(DEBUG)
						System.out.println("You cannot go here, dumbass!");
			}
			
			if (isKeyPressed(KeyEvent.VK_S) || isVirtualKeyPressed(KeyEvent.VK_S)) {
				say(2);
				if(move(0,1) && DEBUG)
					System.out.println("Player goes down. . .");
				else
					if(DEBUG)
						System.out.println("You cannot go here, dumbass!");
			}
		
			if (isKeyPressed(KeyEvent.VK_W ) || isVirtualKeyPressed(KeyEvent.VK_W)) {
				say(4);
				if(move(0,-1) && DEBUG)
					System.out.println("Player goes up. . .");
				else
					if(DEBUG)
						System.out.println("You cannot go here, dumbass!");
			}
			
			/*//SO KÖNNTE MAN DIAGONAL GEHEN (BEISPIEL)... NICHT FÜRS EGTL SPIEL VORGESEHEN
			if (isKeyPressed(KeyEvent.VK_L)) {
				if(move(1,1) && DEBUG)
					System.out.println("Player goes diaDings. . .");
				else
					if(DEBUG)
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
			int xPos = BombermanMain.getPlayers()[0].getField().getPositionX();
			int yPos = BombermanMain.getPlayers()[0].getField().getPositionY();
			BombermanServer.submit(i);
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
		if(DEBUG)
			System.out.println("call move("+i+","+j+")");
		Player[] players = BombermanMain.getPlayers();
		LogicField field = players[0].getField();
		if (field != null && players[0] != null) {
			int x = field.getPositionX();
			int y = field.getPositionY();
			if ((i < 0 && field.getPositionX() > 0 ) 
					|| (i > 0 && field.getPositionX() < (BombermanMain.getPlaygroundWidth() - 1))
						|| (j < 0 && field.getPositionY() > 0)
							|| (j > 0 && field.getPositionY() < (BombermanMain.getPlaygroundHeight()-1))) {
				if(DEBUG)
					System.out.println("next field exist");
				LogicField field2 = BombermanMain.getFields()[x+i][y+j];
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
		PlayerOneKeyListenerServer.delay = delay;
	}

	public static void setPosition(int xPosition, int yPosition) {
		Player player = BombermanMain.getPlayers()[0];
		player.getField().setPlayer(null);
		BombermanMain.getFields()[xPosition][yPosition].setPlayer(player);
	}

	public static void fastSpeed() {
		delay = 50;
	}

	public static void normalSpeed() {
		delay = 120;
	}

	public static boolean isAlowBomb() {
		return alowBomb;
	}

	public static void setAlowBomb(boolean alow) {
		alowBomb = alow;
	}

	public static int getBombDelay() {
		return bombDelay;
	}

	public static void setBombDelay(int bombDelay) {
		PlayerOneKeyListenerServer.bombDelay = bombDelay;
	}
	
	public static void moreBombs() {
		bombDelay = 400;
	}
	
	public static void normalBombs() {
		bombDelay = 1000;
	}
	
}
