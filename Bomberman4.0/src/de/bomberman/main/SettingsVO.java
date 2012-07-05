package de.bomberman.main;

	/**
	 * 
	 * @author Gruppe 44 
	 *
	 */
public class SettingsVO {

	private int playerCnt;
	private int playgroundWidth;
	private int playgroundHeight;
	
	private boolean sound;
	private boolean grafik;
	
	private int[] exitField = new int[2];
	private boolean exit;
	
	

	/** Beinhaltet die Einstellungen des Spiel / des nächsten Spiels
	 * @param playerCnt
	 * @param playgroundWidth
	 * @param playgroundHeight
	 */
	public SettingsVO(int playerCnt, int playgroundWidth, int playgroundHeight) {
		super();
		this.playerCnt = playerCnt;
		this.playgroundWidth = playgroundWidth;
		this.playgroundHeight = playgroundHeight;
	}

	public int getPlayerCnt() {
		return playerCnt;
	}

	public void setPlayerCnt(int playerCnt) {
		this.playerCnt = playerCnt;
	}

	public int getPlaygroundWidth() {
		return playgroundWidth;
	}

	public void setPlaygroundWidth(int playgroundWidth) {
		this.playgroundWidth = playgroundWidth;
	}

	public int getPlaygroundHeight() {
		return playgroundHeight;
	}

	public void setPlaygroundHeight(int playgroundHeight) {
		this.playgroundHeight = playgroundHeight;
	}

	public boolean isSound() {
		return sound;
	}

	public void setSound(boolean sound) {
		this.sound = sound;
	}

	public boolean isGrafik() {
		return grafik;
	}

	public void setGrafik(boolean grafik) {
		this.grafik = grafik;
	}

	public void setExitField(int exitFieldX, int exitFieldY) {
		exitField[0] = exitFieldX;
		exitField[1] = exitFieldY;
		exit = true;
	}

	public int[] getExitField() {
		return exitField;
	}

	public void setExitField(int[] exitField) {
		this.exitField = exitField;
	}

	public boolean isExit() {
		return exit;
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}
	
	
}
