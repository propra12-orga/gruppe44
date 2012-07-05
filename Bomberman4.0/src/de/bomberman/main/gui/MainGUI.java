package de.bomberman.main.gui;

import java.awt.HeadlessException;

import javax.swing.JFrame;

/** Frame (Container fuer die GUI)
 * @author Gruppe 44 
 *
 */
public class MainGUI extends JFrame {

	public MainGUI(String title) throws HeadlessException {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	
}
