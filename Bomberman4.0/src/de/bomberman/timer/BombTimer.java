package de.bomberman.timer;

import de.bomberman.items.Bomb;
import de.bomberman.main.BombermanMain;

/** Regelt das Explodieren, verzögert um die zeit der Bombe das Explodieren
 * @author Giulia Kirstein (2019594)
 *
 */
public class BombTimer extends Thread {
	private static final boolean DEBUG = false;
	private Bomb bomb;
	boolean exploded = false;
	
	public BombTimer(Bomb bomb){
		super("BombTimer - Player "+bomb.getPlayer().getID()); //neu
		this.bomb = bomb;
		this.start();
	}

	public int getPosX() {
		return bomb.getField().getPositionX();
	}

	public int getPosY() {
		return bomb.getField().getPositionY();
	}


	@Override
	public void run() {
		Thread.currentThread();
		try {
			Thread.sleep(1000*this.bomb.getTime()); //TIME sekunden waren 
			if(!exploded) {
				if( DEBUG )
					System.out.println("bomb exploding!");
				bomb.getField().setBomb(null);
				try{
					bomb.exploding();
				} catch (NullPointerException e) {
					
				}
				this.exploded = true;
			}
			
			//Business.explodingHorizontel(bomb.getField().getPositionX(), bomb.getField().getPositionY(), this.bomb.getPlayer());
			//Business.explodingVertical(bomb.getField().getPositionX(), bomb.getField().getPositionY(), this.bomb.getPlayer());
			//Business.removeExploding(bomb.getField().getPositionX(), bomb.getField().getPositionY());
		} catch (InterruptedException e) {
			if(!exploded) {
				if( DEBUG )
					System.out.println("Bomb exploding because of Interrupt Exception");
				bomb.getField().setBomb(null);
				bomb.exploding();
				this.exploded = true;
			}
		}
		
		
	}


	
}