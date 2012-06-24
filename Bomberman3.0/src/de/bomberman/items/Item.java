package de.bomberman.items;

import de.bomberman.main.Player;
import de.bomberman.playground.LogicField;

public class Item {
	protected Player player;
	protected LogicField field;
	
	public Item(Player player,LogicField field){
		this.player = player;
		this.field = field;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public LogicField getField() {
		return field;
	}

	public void setField(LogicField field) {
		this.field = field;
	}
	
	
}
