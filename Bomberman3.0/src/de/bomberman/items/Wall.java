package de.bomberman.items;

/**
 * @author Rena Moehlmann (2042105)
 * Initalisieren einer Wand, die kaputt gehen kann. (Inkluive Position etc)
 */
public class Wall {

	private boolean breakable;
	private int positionX, positionY; //nicht zwingend notwendig?
	private int power; //Anzahl der Male, die eine Wand von einer Bombe getroffen werden muss, um kaputt zu gehen

	
	public Wall(boolean breakable) {
		this.breakable = breakable;
		//beliebige Position der Wand
		randomStrange();
	}
	
	public Wall(boolean breakable, int positionX, int positionY) {
		this.breakable = breakable;
		this.positionX = positionX;
		this.positionY = positionY;
		//bestimmte Position der Wand
		randomStrange();
	}
	
	
	public Wall(int positionX, int positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.breakable = false;
		//Konstruktor für feste Wand
	}
	

	public Wall() {
		this.breakable = false;
	}
	/**
	 * Anzahl der Male, die eine Wand von einer Bombe getroffen werden muss, um kaputt zu gehen, wird bestimmt (zufaellig)
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
		//gibt Wert der Wand zurück
	}
	/**
	 * wird die Wand getroffen, wird ihr ein "Power" abgezogen
	 */
	public void subPower() {
		power--;
	}
	
	public int getPower() {
		return this.power;
	}

	public int getPositionX() {
		return this.positionX;
	}

	public int getPositionY() {
		return this.positionY;
	}
	
}
