import java.awt.*;
import java.awt.event.KeyEvent;


public class bman {
	public static double Screen_x,Screen_y,Width_x,Width_y;
	public static int Segments = 15;
	public static int Width_Segments;
	
	public static void main(String[] args) {
		Screen_x = 512;
		Screen_y = 512;
		Width_x = Screen_x / 2;
		Width_y = Screen_y / 2;
		Width_Segments = (int)Screen_x / Segments;
		//Width_x = 256;
		//Width_y = 256;
		//Width_Segments = 34;
		
		//Bildschirmaufl�sung auslesen und die Gr��e des StdDraw anpassen!
		//StdDraw.setCanvasSize(512, 512);
		StdDraw.setXscale(0.0,Screen_x);
		StdDraw.setYscale(0.0, Screen_y);
		StdDraw.setPenColor(Color.GRAY);
		StdDraw.filledSquare(Width_x, Width_y, Width_x);
		
		
		Objekte WFix = new WallFix();
		WFix.width = Width_Segments;
		
		Objekte Ava = new Avatar();
		
		//Spielfeld[][] game1 = new Spielfeld[14][14];
		for(int i = 1; i<Segments-1;i+=2) {
			for (int j = 1; j <Segments-1;j+=2) {
				//game1[i][j].type = 1;
				//WFix.px = j * Width_Segments;
				//WFix.py = i * Width_Segments;
				WFix.draw(j,i);
			}
		}
		
		Ava.width = Width_Segments;
		Ava.vx = Width_Segments;
		Ava.px = 0;
		Ava.py = 0;
		Ava.draw(0,0);
		//Bom.draw(14, 14);
		
		while(true) {
			//Avatar Grafik setzen und zeichnen
			Ava.grafik =Color.red;
			Ava.draw(Ava.px,Ava.py);
			
			//20 millisekunden Warten...
			StdDraw.show(50);
			
			//Um Avatar Grafik zu l�schen Farbe auf Hintergrumndfarbe setzen und zeichnen
			Ava.grafik = Color.gray;
			Ava.draw(Ava.px,Ava.py);
			
			if (StdDraw.isKeyPressed(KeyEvent.VK_SPACE)) {
				Thread bombThread = new Thread(new Bomb(Ava.px, Ava.py, Ava.width));
				bombThread.start();
			}
			
			//Die Rechts-Links Bewegungen k�nnen nur bei den positionen durchgef�hrt werden, wenn die aktuelle y-Position geteilt durch die anzahl der Segmente eine ungerade Zahl ergibt
			if ((Ava.py % 2)==0) {
				// Abfrage, ob die Rechte-Pfeiltaste gedr�ckt wird
				//und Avatar bewegen
				if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
					if (Ava.px < Segments-1) {
						Ava.px++;
						System.out.println("x: "+Ava.px + " y: "+Ava.py);
					}
				}
				
				//Abfrage, ob die Linke-Pfeiltaste gedr�ckt wird
				//und Avatar bewegen
				else if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {
					if (Ava.px > 0) {
						Ava.px --;
						System.out.println("x: "+Ava.px + " y: "+Ava.py);
					}
				}
			}
			if((Ava.px % 2) == 0) {
				//Abfrage, ob die Unten-Pfeiltaste gedr�ckt wird
				//und Avatar bewegen
				if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) {
					if (Ava.py > 0) {
						Ava.py --;
						System.out.println("x: "+Ava.px + " y: "+Ava.py);
					}

				}
				
				//Abfrage, ob die Oben-Pfeiltaste gedr�ckt wird
				//und Avatar bewegen
				else if(StdDraw.isKeyPressed(KeyEvent.VK_UP)) {
					if (Ava.py < Segments-1) {
						Ava.py ++;
						System.out.println("x: "+Ava.px + " y: "+Ava.py);
					}
				}
			}
			
			
			
			
		}
	
		
		/*Objekte b1 = new Objekte();
		b1.width = 0.5;
		b1.px = 0.5;
		b1.py = 0.5;
		b1.draw();*/
		
	}
}