package de.bomberman.gui.menugui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import de.bomberman.main.BombermanMain;
import de.bomberman.playground.Playground;

public class PropertiesDialog extends JDialog {
	public static final int COLUMNS = 2;
	public static final int ROWS = 3;
	JTextArea sizeArea;
	JTextArea plcntArea;
	
	public PropertiesDialog(){
		setTitle("Properties");
		setLayout(new GridLayout(ROWS,COLUMNS));
		sizeArea = new JTextArea();
		sizeArea.setText(Integer.toString(Playground.getSize()));
		plcntArea = new JTextArea();
		plcntArea.setText(Integer.toString(Playground.getCntPlayer()));
		JButton submit = new JButton("submit");
		submit.addActionListener(getSubmitListener());
		add( new JLabel("Playground-size:"));
		add(sizeArea);
		add( new JLabel("Player:"));
		add(plcntArea);
		add(submit);
		setVisible(true);
	}

	private ActionListener getSubmitListener() {
		
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TO-DO eigende Exception basteln ;-)
				try{
					int tempSize = Integer.parseInt(sizeArea.getText());
					if (tempSize > 40 || tempSize < 0 )
						throw new NumberFormatException();
					
					int tempPlayerCnt = Integer.parseInt(plcntArea.getText());
					if ( tempPlayerCnt > 4 || tempPlayerCnt < 0)
						throw new NumberFormatException();
					
					// anzahl spieler 
					
					JDialog gui =(JDialog) BombermanMain.getGUI();
					gui.setVisible(false);
					gui.dispose();
					setVisible(false);
					dispose();
					BombermanMain.setSize(tempSize);
					BombermanMain.setCntPlayer(tempPlayerCnt);
					BombermanMain.init();
					
				} catch (NumberFormatException e) {
					System.out.println("Please insert an correct integer number between 0 and 40");
					if (BombermanMain.DEBUG)
						System.out.println(e.toString());
				}


			}
		};
	}
}
