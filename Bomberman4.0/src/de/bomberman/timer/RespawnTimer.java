package de.bomberman.timer;

import de.bomberman.main.BombermanMain;
import de.bomberman.player.Player;
import de.bomberman.playground.Playground;

/** Lässt den Spieler , nach einer gewissen Zeit, auf seiner Startposition wieder erwachen nachdem 
 * er von eine Bombe getroffen wurde.
 * @author Nuni
 *
 */
public class RespawnTimer extends Thread {
	private int time;
	private Player player;
	private final boolean DEBUG = true;

	
	public RespawnTimer(int time, Player player) {
		super("Respawn Timer - Player "+player.getID()); //neu Name des Respawn Timer
		this.time = time;
		this.player = player;
		if( DEBUG )
			System.out.println("initalizise RespawnTimer for Player "+player.getID());
	}
	
	public void run() {
		Thread.currentThread();
		try {
			Thread.sleep(time*1000);
			if ( DEBUG ) {
				System.out.println("Respawn");
			}
			int width = BombermanMain.getPlaygroundWidth();
			int height = BombermanMain.getPlaygroundHeight();
			
			switch (player.getID()) {
				case 1:
					this.player.setField(BombermanMain.getFields()[0][0]);
					BombermanMain.getFields()[0][0].setPlayer(player);
				break;
				case 2:
					this.player.setField(BombermanMain.getFields()[width - 1][0]);
					BombermanMain.getFields()[(width - 1)][0].setPlayer(player);
				break;
				case 3:
					this.player.setField(BombermanMain.getFields()[0][(height - 1)]);
					BombermanMain.getFields()[0][height - 1].setPlayer(player);
				break;
				case 4:
					this.player.setField(BombermanMain.getFields()[width - 1][height - 1]);
					BombermanMain.getFields()[width - 1][height - 1].setPlayer(player);
				break;
			}
			player.setDead(false);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}