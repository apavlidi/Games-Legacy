package Airplane;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Renderrer extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Space.space.repaint(g);
	}
}
