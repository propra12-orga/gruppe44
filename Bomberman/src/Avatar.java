import java.awt.Color;


public class Avatar extends Objekte {
	public double vx,vy;
	public int px, py;
	
	public Avatar() {
		//vx=width;
		//vy=0;
		super.grafik = Color.RED;
		
	}	
	public void move() {
		//px = px+vx;
		//py = py+vy;
	}
	public void delete() {
		StdDraw.setPenColor(Color.GRAY);	
		StdDraw.filledSquare((px*width)+(width/2),(py*width)+(width/2),width/2);	
	}
	
	public void draw(int x, int y) {
		StdDraw.setPenColor(grafik);	
		StdDraw.filledSquare((x*width)+(width/2),(y*width)+(width/2),width/2);
	}

}
