package Airplane;

import java.awt.Rectangle;

public class Boss {
	public Rectangle rect;
	public int life = 0;

	Boss() {

		}

	Boss(Rectangle rect, int life) {
			this.rect = rect;
			this.life = life;
		}
}
