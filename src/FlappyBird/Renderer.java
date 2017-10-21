package FlappyBird;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Renderer extends JPanel {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) { //auti i me8odos ine apo to JPanel kai trexi o super tis Render pou ine to JPanel
		super.paintComponent(g);
		FlappyBird.flappyBird.repaint(g);
	
	}

}
