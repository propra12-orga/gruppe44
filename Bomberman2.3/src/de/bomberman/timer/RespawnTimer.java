package de.bomberman.timer;

import de.bomberman.main.BombermanMain;
import de.bomberman.main.Player;
import de.bomberman.playground.Playground;

public class RespawnTimer extends Thread {
	private int time;
	private Player player;

	
	public RespawnTimer(int time, Player player) {
		super("Respawn Timer - Player "+player.getID()); //neu Name des Respawn Timer
		this.time = time;
		this.player = player;
	}
	
	public void run() {
		Thread.currentThread();
		try {
			Thread.sleep(time*1000);
			if (BombermanMain.INFO) {
				System.out.println("Respawn");
			}
			
			if (player.getID() == 1) {
				this.player.setField(Playground.getFields()[0][0]);
				Playground.getFields()[0][0].setPlayer(player);
			}
			if (player.getID() == 2) {
				this.player.setField(Playground.getFields()[(Playground.getSize()-1)][0]);
				Playground.getFields()[(Playground.getSize()-1)][0].setPlayer(player);
			}
			if (player.getID() == 3) {
				this.player.setField(Playground.getFields()[0][(Playground.getSize()-1)]);
				Playground.getFields()[0][(Playground.getSize()-1)].setPlayer(player);
			}
			if (player.getID() == 4) {
				this.player.setField(Playground.getFields()[(Playground.getSize()-1)][(Playground.getSize()-1)]);
				Playground.getFields()[(Playground.getSize()-1)][(Playground.getSize()-1)].setPlayer(player);
			}
			player.setDead(false);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}