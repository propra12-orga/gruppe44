package de.bomberman.items;

import de.bomberman.field.FieldGUI;
import de.bomberman.field.LogicField;
import de.bomberman.main.BombermanMain;

/** Wall Objekt
 * @author Rena Moehlmann (2042105)
 *
 */
public class Wall {

	/**
	 * Damit lassen sich Konsolen Ausgaben die für die 
	 * Entwicklung Hilfreich sind EIN und AUS schalten.
	 */
	private static final boolean DEBUG = true;
	
	private boolean breakable;
	private LogicField field;
	private int power; //Anzahl der Male, die eine Wand von einer Bombe getroffen werden muss, um kaputt zu gehen

	
	/**Konstruktor für ein Wall Objekt
	 * @param breakable
	 * @param field
	 */
	public Wall(boolean breakable, LogicField field) {
		this.breakable = breakable;
		this.field = field;
		//beliebige Position der Wand
		randomStrange();
	}


	public Wall() {
		this.breakable = false;
	}

	/**
	 *  Würfelt die Haltbarkeit der Wände
	 *  3 = Haltbar , 1 = Achtung einsturz Gefahr
	 */
	private void randomStrange() {
		int randomNumber = (int) (Math.random()*10);
		if (randomNumber < 4)
			this.power = 1;
		if (randomNumber > 3 && randomNumber < 7)
			this.power = 2;
		if (randomNumber > 6 && randomNumber <= 10)
			this.power = 3;
		//bestimmt Anzahl der Male, die eine Wand von einer Bombe getroffen werden muss, um kaputt zu gehen
	}
	
	public boolean isBreakable() {
		return this.breakable;
		//gibt Wert der Wand zurÃ¼ck
	}

	/**
	 * reduziert die Haltbarkeit der Wand, bzw zerstört sie bei
	 * power == 0;
	 */
	public void subPower() {
		power--;
		FieldGUI fgui = BombermanMain.getGuiFields()[field.getPositionX()][field.getPositionY()];
		fgui.repaint();
		if ( DEBUG ) {
			System.out.println("Subtract Power, Power now: "+power);
		}
		if (power <= 0) {
			if ( DEBUG ) {
				System.out.println("Unset Wall");
				field.setWall(null);
			}
		}
	}
	
	public int getPower() {
		return this.power;
	}

	public LogicField getField() {
		return field;
	}
}
