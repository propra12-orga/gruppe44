package de.bomberman.network;

import de.bomberman.player.PlayerOneKeyListener;
import de.bomberman.player.PlayerTwoKeyListener;


/**momentan kein aktiver Inhalt des Projects
 * @author Gruppe 44 
 *
 */
public class Protocol {
	
	private int player;
	private int actionCode;
	private int xPosition;
	private int yPosition;
	private int key;
	private boolean status;
	
	
	// constructor
	public Protocol(String protocolString){
		
		
		
		boolean[] status = new boolean[4];
		String[] temp;
		temp = protocolString.split("\\D{1,}");
		/*System.out.println(protocolString);
		for(int i = 0 ; i < temp.length ; i++)
			System.out.println((i+1)+": "+temp[i]);*/
		
		
		try{
			for (int i = 0 ; i < temp.length ; i++) {
				if (temp[i].startsWith("1") && !status [0]) {
					player = Integer.parseInt(temp[i].substring(1));
					status[0] = true;
				} else if (temp[i].startsWith("2") && !status [1]) {
					actionCode = Integer.parseInt(temp[i].substring(1));
					status[1] = true;
				} else if (temp[i].startsWith("3") && !status [2]) {
					xPosition = Integer.parseInt(temp[i].substring(1));
					status[2] = true;
				} else if (temp[i].startsWith("4") && !status [3]) {
					yPosition = Integer.parseInt(temp[i].substring(1));
					status[3] = true;
				} else {
					System.out.println("Protocol Error");
				}
			}
		} catch (NumberFormatException e1 ) {
			System.out.println("Protocol Error");
		}
		
		if( status[0] && status[1] && status[2] && status[3]) {
			/*System.out.println("Player "+player+" aktionCode: "+actionCode+"\n" +
				"on position: "+xPosition+"/"+yPosition);*/
			this.status = true;
		} else {
			System.out.println("Protocol Error");
		}
	}
	
	public static String getProtocolString(int player, int actionCode, int xPos, int yPos, int key) {
		if(actionCode == 6)
			return "y1"+player+"x2"+actionCode+"x3"+key+"x4"+00+"y";
		else
			return "y1"+player+"x2"+actionCode+"x3"+xPos+"x4"+yPos+"y";
		
	}

	public int getPlayer() {
		return player;
	}

	public int getActionCode() {
		return actionCode;
	}

	public int getxPosition() {
		return xPosition;
	}

	public int getyPosition() {
		return yPosition;
	}

	public boolean isStatus() {
		return status;
	}
	
	public int getKey() {
		return key;
	}

	public static void submit(Protocol protocol) {
		int player = protocol.getPlayer();
		
		if( player == 1 ) {
			PlayerOneKeyListener.setPosition(protocol.getxPosition(), protocol.getyPosition());
			PlayerOneKeyListener.virtualKeyPressed(protocol.getActionCode());
		}
		if( player == 2 ) {
			PlayerTwoKeyListener.setPosition(protocol.getxPosition(), protocol.getyPosition());
			PlayerTwoKeyListener.virtualKeyPressed(protocol.getActionCode());
		}
	}

	public int getControl() {
		return player*4+actionCode*3+xPosition*2+yPosition;
	}
}
