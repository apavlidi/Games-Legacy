package FlappyBird;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

import javax.swing.JFrame;

public class FlappyBird implements ActionListener, MouseListener {

	public static FlappyBird flappyBird;

	public final int WIDTH = 800, HEIGHT = 800;

	public Renderer renderer;

	// to pouli einai ena tetragono (Rectangle
	public Rectangle bird;

	// oi solines einai mia lista tipou tetragonou (Rectangle)
	public ArrayList<Rectangle> columns;

	public Random rnd;

	// ticks einai i stigmi,to second
	// motion einai i kinisi
	public int ticks, yMotion;

	public boolean gameOver, started;

	public int score;

	public FlappyBird() {

		JFrame jframe = new JFrame();

		Timer timer = new Timer(20, this);

		renderer = new Renderer();

		rnd = new Random();

		jframe.add(renderer);
		jframe.setSize(WIDTH, HEIGHT);
		jframe.setResizable(true);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setVisible(true);
		jframe.setTitle("Flappy Bird");
		jframe.addMouseListener(this);
			
		//protoi 2 parametroi pou 8a topo8eti8i to pouli kai 2 alles parametrou ine oi diastasis tou
		bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2, 20, 20);

		columns = new ArrayList<Rectangle>();

		addColumn(true);
		addColumn(true);
		addColumn(true);
		addColumn(true);
		timer.start();
	}

	public static void main(String args[]) {
		flappyBird = new FlappyBird();

	}

	public void addColumn(boolean start) {
		int space = 300;
		int width = 100;
		int height = 50 + rnd.nextInt(300); // to mikrotero pou bori na ine o
											// kato solinas einai 50 kai to
											// megalitero ine 300
		if (start) {
			// protoi 4 solines
			// o kato solinas
			columns.add(new Rectangle(WIDTH + width + columns.size() * 300, HEIGHT - height - 120, width, height));
			// o pano solinas
			columns.add(new Rectangle(WIDTH + width + (columns.size() - 1) * 300, 0, width, HEIGHT - height - space));
			
			
		} else {
			// o kato solinas
			columns.add(new Rectangle(columns.get(columns.size() -1).x + 600, HEIGHT - height - 120, width, height));
			// o pano solinas
			columns.add(new Rectangle(columns.get(columns.size() - 1).x, 0, width, HEIGHT - height - space));

		}
	}

	public void paintColumn(Graphics g, Rectangle column) {
		g.setColor(Color.green.darker());
		g.fillRect(column.x, column.y, column.width, column.height);
	}

	public void jump() {
		if (gameOver) {
			bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2, 20, 20);
			columns.clear();
			columns = new ArrayList<Rectangle>();
		
			yMotion = 0;
			score = 0;

			addColumn(true);
			addColumn(true);
			addColumn(true);
			addColumn(true);
			
			gameOver = false;
		}
		if (!started) { // an dn exi 3ekinisi to game 3ekinaei
			started = true;
		} else if (!gameOver) { // an exi 3ekinisi kai to game dn ine over
			if (yMotion > 0) {
				yMotion = 0;
			}
			yMotion -= 10;
		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		ticks++; // etsi exoume mia metavliti gia ton xrono pou pernaei

		int speed = 10; // i taxitita pou 8eloume na pernane oi solines
		if (started) {
			for (int i = 0; i < columns.size(); i++) {
				// automata perni kai tous 4 solines pano kato kai tous mioni
				// tis sintetagmenes kata tin taxitita
				Rectangle column = columns.get(i);

				column.x -= speed;
			}
			// ana 2 sec anaveazo taxitita kai i taxitita periorizete sto 15
			if (ticks % 2 == 0 && yMotion < 15) {
				yMotion += 2;
			}

			for (int i = 0; i < columns.size(); i++) {
				Rectangle column = columns.get(i);
				// otan oi kolones exoun figi apo tin o8oni sta aristera kai tis
				// exo perasi diladi einai mikroteres apo to 0 oi sintetagmenes
				// tous
				if (column.x + column.width < 0) {
					// diegra4e tis kolones
					columns.remove(column);
					if (column.y == 0) {
						//vazo false gia na paei sto else kai na dimiourgisi neous solines alla konta to ena me ton allon
						addColumn(false);
					}
				}

			}
			
			//to y giati ine pros ta pano
			bird.y += yMotion; // pros8eto tin taxitita pou ine 15 sto bird.y

			for (Rectangle column : columns) {
				// prepi na to meletiso
				if (column.y == 0 && bird.x + bird.width / 2 > column.x + column.width / 2 - 10
						&& bird.x + bird.width / 2 < column.x + column.width / 2 + 10) {
					score++;
				}
				if (column.intersects(bird)) {
					gameOver = true;
					// gia na kounaei i kolona to pouli
					bird.x = column.x - bird.width;
				}
			}

			if (bird.y > HEIGHT - 120 || bird.y < 0) {
				// otan perasei pano apo tin o8oni i pesi kato telioni to game
				gameOver = true;
			}
			if (bird.y + yMotion >= HEIGHT - 120) { // gia na pefti sosta
				bird.y = HEIGHT - 120 - bird.height;
			}

		}
		
		//ka8e xroniki stigmi kane repaint
		renderer.repaint();
	}

	public void repaint(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect(0, 0, WIDTH, HEIGHT); // gemise apo tin arxi mexri to telos

		g.setColor(Color.red);
		// gemise apo tin gonia tou x tou pouliou
		g.fillRect(bird.x, bird.y, bird.width, bird.height);

		g.setColor(Color.orange);
		g.fillRect(0, HEIGHT - 120, WIDTH, 150);

		g.setColor(Color.green);
		g.fillRect(0, HEIGHT - 120, WIDTH, 20);

		for (Rectangle column : columns) {
			paintColumn(g, column);
		}
		g.setColor(Color.white);
		g.setFont(new Font("Arial", 1, 100));

		if (!started) {
			g.drawString("Click to start!", 75, HEIGHT / 2 - 50);
		}
		if (gameOver) {
			g.drawString("Game Over!", 100, HEIGHT / 2 - 50);
		}

		if (!gameOver && started) {
			g.drawString(String.valueOf(score), WIDTH / 2 - 25, 100);
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		jump();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

}
