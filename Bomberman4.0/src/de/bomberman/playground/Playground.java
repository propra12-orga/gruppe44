package de.bomberman.playground;

import de.bomberman.field.LogicField;
import de.bomberman.items.Wall;
import de.bomberman.main.BombermanMain;
import de.bomberman.player.Player;

/** aktuell kein aktiver inhalt des Projektes
 * @author Gruppe 44 
 *
 */
public class Playground {
	
	/*private static int cntPlayer;
	private static int size;
	private static LogicField[][] fields;
	private static Player[] players;
	
	public Playground (int cntPlayer, int size){
		this.cntPlayer = cntPlayer;
		this.size = size;
		this.fields = new LogicField[size][size];
		this.players = new Player[cntPlayer];
		
		for (int i=0; i < cntPlayer; i++){
			this.players[i] = new Player(i+1);
		}
	
		for (int i=0; i < size; i++) {
			for (int j=0; j < size; j++) {
				this.fields[i][j] = new LogicField(i,j);
				
				if ((i+1) % 2 == 0 && (j+1) % 2 == 0){
					this.fields[i][j].setWall(new Wall());
				}
			}
		}
		switch (cntPlayer) { //neu
		case 4:
			if(BombermanMain.DEBUG)
				System.out.println("Spieler 4 initializing...");
			fields[size-1][size-1].setPlayer(players[3]);
		case 3:
			if(BombermanMain.DEBUG)
				System.out.println("Spieler 3 initializing...");
			fields[0][size-1].setPlayer(players[2]);
		case 2:
			if(BombermanMain.DEBUG)
				System.out.println("Spieler 2 initializing...");
			fields[size-1][0].setPlayer(players[1]);
		case 1:
			if(BombermanMain.DEBUG)
				System.out.println("Spieler 1 initializing...");
			fields[0][0].setPlayer(players[0]);
		}

	}

	public static int getCntPlayer() {
		return cntPlayer;
	}

	public static int getSize() {
		return size;
	}

	public static LogicField[][] getFields() {
		return fields;
	}

	public static  Player[] getPlayers() {
		return players;
	}
	
	
	
	
*/
}
