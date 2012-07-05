package de.bomberman.main;

/** Beinhaltet die Statistik eine Spiels
 * @author Gruppe 44 
 *
 */
public class ScoreVO {
	int[] kills = new int[4];
	int[] deads = new int[4];
	int[] score = new int[4];
	String[] name = new String[4];
	
	
	public int[] getKills() {
		return kills;
	}
	public void setKills(int ID, int kills) {
		this.kills[ID] = kills;
	}
	public int[] getDeads() {
		return deads;
	}
	public void setDeads(int ID, int deads) {
		this.deads[ID] = deads;
	}
	public int[] getScore() {
		return score;
	}
	public void setScore(int ID, int score) {
		this.score[ID] = score;
	}
	public String[] getName() {
		return name;
	}
	public void setName(String[] name) {
		this.name = name;
	}
	public void addScore(int ID) {
		score[ID]++;
	}

	
	
	
	
}
