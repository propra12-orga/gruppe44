import java.awt.Color;


public class Objekte {
	public Color grafik = Color.WHITE;
	public int px, py;
	public double vx, vy;
	public double width;
	
	public Objekte() {
		px = py = 0;
		vx = 0;
		vy = 0;
	}
	
	public void move() {
		//px = px + vx;
		//py = py + vy;
	}
	
	public void draw(int x, int y) {
		StdDraw.setPenColor(grafik);	
		StdDraw.filledSquare((x*width)+(width/2),(y*width)+(width/2),width/2);	
	}
	
	public void delete() {
	}
}
