<<<<<<< HEAD
import java.awt.Color;


public class Bomb extends Objekte implements Runnable {

	/**
	 *  radius of bomb explosion
	 */
	final static int RADIUS = 1; 
	
	/**
	 *  countdown , time that must pass for bomb exploding
	 */
	final static int COUNTDOWN = 10; 
	
	public double vx,vy;
	public int positionX, positionY;
	
	public Bomb(int positionX, int positionY, double width) {
		super.grafik = Color.BLUE;
		this.positionX = positionX;
		this.positionY = positionY;
		this.width = width;
		
	}
	public void draw(){
		StdDraw.filledCircle(positionX*30, positionY*30, this.RADIUS*10);
	}
	@Override
	public void run() {
		int time = 1000 * COUNTDOWN;
		try {
			StdDraw.setPenColor(this.grafik);
			this.draw(this.positionX, this.positionY);
			System.out.println("Bombe gesetzt");
			Thread.currentThread();
			Thread.sleep(time);
			System.out.println("EXPLOSION!!!!!!!!!!");
			this.grafik = new Color(255,160,00);
			for(int i = -1; i <= 1 ; i++)
				for(int k = -1; k <= 1; k++)
					this.draw(this.positionX+i, this.positionY+k);
			Thread.sleep(200);
			this.grafik = StdDraw.YELLOW;
			for(int i = -1; i <= 1 ; i++)
				for(int k = -1; k <= 1; k++)
					this.draw(this.positionX+i, this.positionY+k);
			Thread.sleep(200);
			this.grafik = StdDraw.GRAY;
			for(int i = -1; i <= 1 ; i++)
				for(int k = -1; k <= 1; k++)
			this.draw(this.positionX+i, this.positionY+k);
			this.grafik = StdDraw.BLUE;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	

}
=======
/**
 * @author Giulia Kirstein (2019594)
 *
 */
public class Bomb {

	private int positionX, positionY;
	private int radius;
	private int time;
	private int player;
	
	public Bomb(int positionX, int positionY, int radius, int time, int player) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.radius = radius;
		this.time = time;
		this.player = player;
	}
	
	public int getPositionX() {
		return this.positionX; 
	}
	
	public int getPositionY() {
		return this.positionY; 
	}
	
	public int getRadius() {
		return this.radius;
	}
	
	public int getTime() {
		return this.time;
	}
	
	public int getPlayer() {
		return this.player;
	}
	
}
	
>>>>>>> Bombe die Zweite
