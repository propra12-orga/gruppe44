import java.awt.Color;


public class Spielfeld {
	public int type; 		//0=leeres Feld; 1=WallFix; 2=WallLvl; 3=Gegner; 4=Ausgang, wobei 4 nur gesetzt werden kann wenn es sich um eine WallFix handelt
	
	public Spielfeld() {
		StdDraw.setXscale(0.0,512.0);
		StdDraw.setYscale(0.0, 512.0);
		StdDraw.setPenColor(Color.white);
		StdDraw.filledSquare(255, 255, 255);
	}
}
