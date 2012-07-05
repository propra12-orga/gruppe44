package de.bomberman.ki;

import java.net.MalformedURLException;

import de.bomberman.field.LogicField;
import de.bomberman.main.BombermanMain;
import de.bomberman.player.Player;
import de.bomberman.sound.SoundBib;

/** Klasse für die Kuenstliche Inteligenz
 * @author Katja Baran
 *
 */
public class KI extends Player implements Runnable{
	private static final boolean DEBUG = false;
	private LogicField field;
	private int lifePoints; // zukunftsmusik
	private int speed; // zukunftsmusik
	private Thread t;
	private static int delay = 200;
	
	/** Konstruktor fuer eine KI
	 * @param field
	 * @param lifePoints
	 * @param speed
	 */
	public KI(LogicField field, int lifePoints, int speed) {
		field.setPlayer(this);
		this.field = field;
		this.lifePoints = lifePoints;
		this.speed = speed;
		t = new Thread(this);
		t.start();
	}
	
	
	/** Die Logik (Regelt das Verhalten) der KI (eigener Thread)
	 * @see java.lang.Runnable#run()
	 **/
	public void run() {
		boolean run = true;
		while(run) {
			try {
			if ( DEBUG )
				System.out.println("KI spam!");
			
			t.sleep(delay );
			int tempSmell = field.getSmell();
			int xPos = field.getPositionX();
			int yPos = field.getPositionY();
			int heigth = BombermanMain.getPlaygroundHeight();
			int width = BombermanMain.getPlaygroundWidth();
			LogicField[][] fields = BombermanMain.getFields();
			boolean noSmellFound = true;
			for( int i = -1; i < 2; i++){
				for(int k = -1; k < 2; k++) {
					if (( xPos + i ) >= 0 && ( xPos + i ) < width && ( yPos + k ) >= 0 && ( yPos + k ) < heigth ) {
						int fieldSmell = fields[xPos + i][yPos + k].getSmell();
						if ( DEBUG ) {
							System.out.println("Field: "+xPos+"/"+yPos+" SField: "+i+"/"+k);
							System.out.println("Fieldsmell: "+fieldSmell+" tempSmell: "+tempSmell);
						}
						if( fieldSmell > tempSmell ) {
							noSmellFound = false;
							if( ( xPos - i ) >= 0 && ( xPos - i ) < width && fieldSmell < fields[xPos - i][yPos + k].getSmell())
								move( ( xPos - i ), ( yPos + k ));
							else if(( yPos - k ) >= 0 && ( yPos - k ) < heigth && fieldSmell < fields[xPos + i][yPos - k].getSmell())
								move( ( xPos + i ), ( yPos - k ));
							else 
								move( ( xPos + i ), ( yPos + k ));
						}
					}
				}
			}
			
			System.out.println("noSmellFound: "+noSmellFound);
			if( noSmellFound ) {
				int rnd = (int)(10*Math.random());
				System.out.println(rnd);
				switch ( rnd ) {
				case 1:
					move( xPos + 1, yPos);
					break;
				case 2:
					move( xPos + 1, yPos);
					break;
				case 3:
					move( xPos , yPos +1);
					break;
				case 4:
					move( xPos - 1, yPos);
					break;
				case 5:
					move( xPos - 1, yPos);
					break;
				case 6:
					move( xPos , yPos -1);
					break;
				case 7:
					move( xPos , yPos -1);
					break;
				case 8:
					move( xPos , yPos + 1);
					break;
					
				}
			}
			} catch (InterruptedException e) {
				//e.printStackTrace();
				run = false;
			}
		}
		System.out.println("Enemy KI stops");
		t.stop();
		field.setPlayer(null);
	}

	/** Bewegt die Ki auf das Feld i, j
	 * @param i
	 * @param j
	 */
	private void move(int i, int j) {
		int width = BombermanMain.getPlaygroundWidth();
		int height = BombermanMain.getPlaygroundHeight();
		if( i < width  && j < height  && i >= 0 && j >= 0) {
			LogicField[][] fields = BombermanMain.getFields();
			if ( fields[i][j].getPlayer() != null ) {
				BombermanMain.stopGame(false);
			} else if( fields[i][j].getWall() == null && !fields[i][j].isExitField() && fields[i][j].getBomb() == null) {
				field.setPlayer(null);
				fields[i][j].setPlayer(this);
				field = fields[i][j];
			}
		}
	}

	public void stopListener() {
		System.out.println("interrupt");
		t.interrupt();
	}

	public static void normalSpeed() {
		delay = 200;
	}

	public static void slowerSpeed() {
		delay = 350;
	}
	
	/** Setzt die Ki auf ihre Ausgangs Position
	 * @see de.bomberman.player.Player#reset()
	 **/
	@Override
	public void reset() {
		int height = BombermanMain.getPlaygroundHeight();
		int width = BombermanMain.getPlaygroundWidth();
		LogicField[][] fields = BombermanMain.getFields();
		LogicField tempField = fields[width - 1][height - 1];
		
		this.field.setPlayer(null);
		this.field = tempField;
		tempField.setPlayer(this);
	}
}
