package Airplane;

import java.awt.Rectangle;

public class enemyStrong {
	public Rectangle rect;
	public int life = 0;

	enemyStrong() {

	}

	enemyStrong(Rectangle rect, int life) {
		this.rect = rect;
		this.life = life;
	}
}
