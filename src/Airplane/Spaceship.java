package Airplane;

import java.awt.Rectangle;

public class Spaceship {
	public Rectangle rect;
	public int life = 0;
	Spaceship() {

	}
	Spaceship(Rectangle rect, int life) {
		this.rect = rect;
		this.life = life;
	}
}
