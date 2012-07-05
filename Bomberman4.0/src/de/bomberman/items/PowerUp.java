package de.bomberman.items;

import de.bomberman.field.LogicField;
import de.bomberman.player.Player;

/** PowerUp Objekt
 * @author Gruppe 44 
 *
 */
public class PowerUp extends Item {
	private int type;
	
	/** Konstruktor fuer ein PowerUp 
	 * Wuerfelt ein PowerUp Objekt aus
	 * @param field
	 */
	public PowerUp(LogicField field) {
		super(null, field);
		double rnd = Math.random();
		if( rnd >= 0 && rnd < 0.4 ) {
			type = 1; //schneller laufen
		} else if ( rnd >= 0.4 && rnd < 0.8 ) {
			type = 2; // schneller bomben legen
		} else {
			type = 3; //ki langsamer
		}
	}
	public int getType() {
		return type;
	}
	
	
}
