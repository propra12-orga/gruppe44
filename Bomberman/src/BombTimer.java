
/**
 * @author Giulia Kirstein (2019594)
 *
 */
public class BombTimer extends Thread {
	private Bomb bomb;
	
	
	public BombTimer(Bomb bomb){
		this.bomb = bomb;
		this.start();
	}

	public int getPosX() {
		return bomb.getPositionX();
	}

	public int getPosY() {
		return bomb.getPositionY();
	}


	@Override
	public void run() {
		Thread.currentThread();
		try {
			Thread.sleep(1000*this.bomb.getTime()); //TIME sekunden waren 
			System.out.println("BOMBE EXPLODIERT!");
			
			Business.explodingHorizontel(this.bomb.getPositionX(), this.bomb.getPositionY(), this.bomb.getPlayer());
			Business.explodingVertical(this.bomb.getPositionX(), this.bomb.getPositionY(), this.bomb.getPlayer());
			Business.removeExploding(this.bomb.getPositionX(), this.bomb.getPositionY());
		} catch (InterruptedException e) {
			System.out.println("Thread.sleep() fehlgeschlagen!");
		}
		
		
	}


	
}