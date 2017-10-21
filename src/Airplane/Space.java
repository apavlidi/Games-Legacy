package Airplane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Space implements ActionListener, KeyListener, MouseListener {

	public static int WINDOW_WIDTH = 800;
	public static int WINDOW_HEIGHT = 600;
	public static int PLANE_WIDTH = 40, PLANE_HEIGHT = 40;
	public static int ENEMY_WIDTH = 40, ENEMY_HEIGHT = 40;
	public static int BULLET_WIDTH = 20, BULLET_HEIGHT = 7;
	public static int FONT_SIZE = 32;
	public static String JFRAME_TITLE = "8bit Game";
	public static int START_TEXT_X = 300;
	public static int START_TEXT_Y = 300;
	public static int WIN_TEXT_X = 200;
	public static int WIN_TEXT_Y = 300;
	public static int GAME_OVER_X = 300;
	public static int GAME_OVER_Y = 300;
	public static int SCORE_X = 50;
	public static int SCORE_Y = 50;
	public static int HIGHSCORE_X = 500;
	public static int HIGHSCORE_Y = 50;
	public static int BORDER_TOP = 100;
	public static int BORDER_DOWN = 530;
	public static int DEFAULT_X_MOTION_ENEMY_WEAK = 4;
	public static int DEFAULT_X_MOTION_ENEMY_STRONG = 1;
	public static int DEFAULT_X_MOTION_BULLET = 7;
	public static int BOSS_LIFE = 20;
	public Spaceship spaceship;
	public static int X_MOTION_BOSS = 1;
	public ArrayList<Rectangle> enemiesWeak;
	public ArrayList<enemyStrong> enemiesStrong;
	public ArrayList<Rectangle> bulletss;
	public Rectangle bullet1;
	public Rectangle bullet2;
	public static Space space;
	public Boss boss;
	public Rectangle life;

	public BufferedImage backgroundImage;
	public BufferedImage spaceShipImage;
	public BufferedImage enemyWeakImage;
	public BufferedImage enemyStrongImage;
	public BufferedImage bossImage;
	public BufferedImage bulletImage;
	public BufferedImage lifeImage;
	public BufferedImage bulletBossImage;

	public Timer time;
	public Font fontObject;
	public Renderrer renderrer;

	public boolean start = false;
	public boolean gameOver = false;
	public boolean win = false;
	public boolean shootFirstBullet = false;
	public boolean shootSecondBullet = false;
	public int score = 0;
	public int highscore = 0;
	public int ticks = 0;
	public int xMotionEnemyWeak = DEFAULT_X_MOTION_ENEMY_WEAK;
	public int xMotionEnemyStrong = DEFAULT_X_MOTION_ENEMY_STRONG;

	public int xMotionBullet = DEFAULT_X_MOTION_BULLET;
	public boolean levelup = true;
	public int levelupScore;
	public int counterMove = 0;
	public boolean bossReady = false;

	public boolean movedown = true;
	public boolean moveup = false;
	JFrame jframeChoice;
	JFrame jframeGame;

	public Space() {
		jframeGame = new JFrame();
		jframeGame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		jframeGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframeGame.setResizable(true);
		jframeGame.setTitle(JFRAME_TITLE);
		jframeGame.getContentPane().setBackground(Color.BLACK);
		jframeGame.setVisible(false);
		jframeGame.addKeyListener(this);
		jframeGame.addMouseListener(this);
		renderrer = new Renderrer();
		jframeGame.add(renderrer);

		spaceship = new Spaceship(new Rectangle(0, 300, PLANE_WIDTH, PLANE_HEIGHT), 3);
		enemiesWeak = new ArrayList<Rectangle>();
		enemiesStrong = new ArrayList<enemyStrong>();
		bullet1 = new Rectangle();
		bullet2 = new Rectangle();
		boss = new Boss(new Rectangle(1000, 150, 150, 300), BOSS_LIFE);
		enemiesWeak.add(new Rectangle(1000, (int) (Math.random() * 400 + 100), ENEMY_WIDTH, ENEMY_HEIGHT));
		enemiesWeak.add(new Rectangle(1300, (int) (Math.random() * 400 + 100), ENEMY_WIDTH, ENEMY_HEIGHT));
		enemiesWeak.add(new Rectangle(1600, (int) (Math.random() * 400 + 100), ENEMY_WIDTH, ENEMY_HEIGHT));
		enemiesWeak.add(new Rectangle(1900, (int) (Math.random() * 400 + 100), ENEMY_WIDTH, ENEMY_HEIGHT));
		life = null;
		time = new Timer(20, this);
		time.start();
		fontObject = new Font("Arial", Font.BOLD, FONT_SIZE);
		try {
			backgroundImage = ImageIO.read(new File("spaceBackground.png"));
			enemyWeakImage = ImageIO.read(new File("weakEnemy.png"));
			enemyStrongImage = ImageIO.read(new File("strongEnemy.png"));
			bulletImage = ImageIO.read(new File("bullet.png"));
			bossImage = ImageIO.read(new File("boss.png"));
			bulletBossImage = ImageIO.read(new File("bulletBoss.png"));
			lifeImage = ImageIO.read(new File("heart.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		renderrer.repaint();
		
		
		jframeChoice = new JFrame();
		jframeChoice.setSize(400, 200);
		jframeChoice.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframeChoice.setResizable(true);
		jframeChoice.setTitle(JFRAME_TITLE);
		jframeChoice.setLayout(new BorderLayout());
		jframeChoice.getContentPane().setBackground(Color.BLACK);
		jframeChoice.setVisible(true);
		
		JPanel labelPanel=new JPanel();
		labelPanel.setLayout(new FlowLayout());
		JLabel label1=new JLabel();
		label1.setIcon(spaceship1ImageIcon);
		JLabel label2=new JLabel(spaceship2ImageIcon);
		JLabel label3=new JLabel(spaceship3ImageIcon);
		labelPanel.add(label1);
		labelPanel.add(label2);
		labelPanel.add(label3);
		jframeChoice.add(labelPanel, BorderLayout.CENTER);
		
		JPanel butPanel = new JPanel();
		butPanel.setLayout(new FlowLayout());
		JButton chooseFirst = new JButton("Choose first");
		chooseFirst.addActionListener(new ConfirmListener());
		JButton chooseSecond = new JButton("Choose second");
		chooseSecond.addActionListener(new ConfirmListener());
		JButton chooseThird = new JButton("Choose third");
		chooseThird.addActionListener(new ConfirmListener());
		butPanel.add(chooseFirst);
		butPanel.add(chooseSecond);
		butPanel.add(chooseThird);
		jframeChoice.add(butPanel, BorderLayout.SOUTH);

	}
	ImageIcon spaceship1ImageIcon=new ImageIcon("spaceship1.png");
	ImageIcon spaceship2ImageIcon=new ImageIcon("spaceship2.png");
	ImageIcon spaceship3ImageIcon=new ImageIcon("spaceship3.png");
	
	public class ConfirmListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (e.getActionCommand().equals("Choose first"))
					spaceShipImage = ImageIO.read(new File("spaceship1.png"));
				else if (e.getActionCommand().equals("Choose second"))
					spaceShipImage = ImageIO.read(new File("spaceship2.png"));
				else if (e.getActionCommand().equals("Choose third"))
					spaceShipImage = ImageIO.read(new File("spaceship3.png"));

			} catch (IOException k){
				
			}
			
			jframeChoice.setVisible(false);
			jframeGame.setVisible(true);
			start = true;
		}
	}

	public void repaint(Graphics g) {
		g.setFont(fontObject);
		g.drawImage(backgroundImage, 0, 0, null);
		g.setColor(Color.RED);
		g.drawImage(spaceShipImage, spaceship.rect.x, spaceship.rect.y, null);

		if (spaceship.life == 3) {
			g.drawImage(lifeImage, 250, 30, null);
			g.drawImage(lifeImage, 290, 30, null);
			g.drawImage(lifeImage, 330, 30, null);
		} else if (spaceship.life == 2) {
			g.drawImage(lifeImage, 250, 30, null);
			g.drawImage(lifeImage, 290, 30, null);
		} else if (spaceship.life == 1) {
			g.drawImage(lifeImage, 250, 30, null);
		}

		if (!gameOver) {
			if (shootBoss)
				paintBossBullet(g, bossBullet);

			if (shootFirstBullet)
				paintBullet(g, bullet1);

			if (shootSecondBullet)
				paintBullet(g, bullet2);
		}
		if (life != null)
			paintHeart(g, life);

		for (Rectangle enemyWeak : enemiesWeak)
			paintEnemyWeak(g, enemyWeak);

		for (enemyStrong enemyStrong : enemiesStrong)
			paintEnemyStrong(g, enemyStrong);

		if (bossReady)
			paintBoss(g, boss);

		if (win) {
			g.setColor(Color.RED);
			g.drawString("YOU WIN,CONGRATULATIONS", WIN_TEXT_X, WIN_TEXT_Y);
		}

		if (!start) {
			g.setColor(Color.WHITE);
			g.drawString("Click to start", START_TEXT_X, START_TEXT_Y);
		} else {
			g.setColor(Color.RED);
			g.drawString("Score: " + score, SCORE_X, SCORE_Y);
			g.drawString("Highscore: " + highscore, HIGHSCORE_X, HIGHSCORE_Y);
		}

		if (gameOver) {
			g.setColor(Color.RED);
			g.drawString("Game Over", GAME_OVER_X, GAME_OVER_Y);
		}
	}

	public void paintBossBullet(Graphics g, Rectangle bossBullet) {
		g.drawImage(bulletBossImage, bossBullet.x, bossBullet.y, null);
	}

	public void paintHeart(Graphics g, Rectangle life) {
		g.drawImage(lifeImage, life.x, life.y, null);
	}

	public void paintBoss(Graphics g, Boss boss) {
		g.drawImage(bossImage, boss.rect.x, boss.rect.y, null);
	}

	public void paintEnemyWeak(Graphics g, Rectangle enemyWeak) {
		g.drawImage(enemyWeakImage, enemyWeak.x, enemyWeak.y, null);
	}

	public void paintEnemyStrong(Graphics g, enemyStrong enemyStrong) {
		g.drawImage(enemyStrongImage, enemyStrong.rect.x, enemyStrong.rect.y, null);
	}

	public void paintBullet(Graphics g, Rectangle bullet) {
		g.drawImage(bulletImage, bullet.x, bullet.y, null);
	}

	public void addEnemyWeak() {
		int heightSpawnEnemy;
		do {
			heightSpawnEnemy = (int) (Math.random() * 500);
		} while (heightSpawnEnemy < BORDER_TOP || heightSpawnEnemy > BORDER_DOWN);
		enemiesWeak.add(new Rectangle(enemiesWeak.get(enemiesWeak.size() - 1).x + 300, heightSpawnEnemy, ENEMY_WIDTH,
				ENEMY_HEIGHT));
	}

	public void addLife() {
		int heightSpawnLife;
		do {
			heightSpawnLife = (int) (Math.random() * 600);
		} while (heightSpawnLife < BORDER_TOP || heightSpawnLife > BORDER_DOWN);
		life = new Rectangle(1000, heightSpawnLife, 30, 30);
	}

	public void addEnemyStrong() {
		int heightSpawnEnemy;
		do {
			heightSpawnEnemy = (int) (Math.random() * 600);
		} while (heightSpawnEnemy < BORDER_TOP || heightSpawnEnemy > BORDER_DOWN);
		enemiesStrong.add(new enemyStrong(new Rectangle(enemiesStrong.get(enemiesStrong.size() - 1).rect.x + 600,
				heightSpawnEnemy, ENEMY_WIDTH, ENEMY_HEIGHT), 2));
	}

	public static void main(String[] args) {
		space = new Space();
	}

	public boolean shootBoss = false;
	public Rectangle bossBullet = new Rectangle();
	public int xMotionBossBullet = 9;
	public int level = 1;

	public void initializeEnemiesStrong() {
		if (enemiesStrong.size() < 3) {
			enemiesStrong.add(new enemyStrong(
					new Rectangle(1000, (int) (Math.random() * 400 + 100), ENEMY_WIDTH, ENEMY_HEIGHT), 2));
			enemiesStrong.add(new enemyStrong(
					new Rectangle(1600, (int) (Math.random() * 400 + 100), ENEMY_WIDTH, ENEMY_HEIGHT), 2));
			enemiesStrong.add(new enemyStrong(
					new Rectangle(2200, (int) (Math.random() * 400 + 100), ENEMY_WIDTH, ENEMY_HEIGHT), 2));
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ticks++;
		// anevazi epipedo ka8e 10 enemies
		if (levelup)
			if (score % 10 == 0 && score != 0) {
				levelupScore = score;
				xMotionEnemyWeak++;
				level++;
				xMotionEnemyStrong++;
				levelup = false;
			}
		if (levelupScore != score)
			levelup = true;
		// oso pezis kai dn exis xasi
		if (start) {
			if (!gameOver) {
				if (bossReady) {
					enemiesWeak = new ArrayList<Rectangle>();
					enemiesStrong = new ArrayList<enemyStrong>();
					// erxete stin o8oni alla se sigekrimeno orio sta ristera
					if (boss.rect.x > 600)
						boss.rect.x -= 10;
					// meni sta oria pano-kato
					if (boss.rect.y < BORDER_DOWN - 200 && boss.rect.y > BORDER_TOP) {
						if (movedown && boss.rect.y < BORDER_DOWN - 250) {
							boss.rect.y += 1;
							if (boss.rect.y == BORDER_DOWN - 250) {
								movedown = false;
								moveup = true;
							}
						}
						if (moveup && boss.rect.y > BORDER_TOP) {
							boss.rect.y -= 1;
							if (boss.rect.y < 110) {
								movedown = true;
								moveup = false;
							}
						}
					}
					// molis ftasi sta aristera sosta tote pirovolaei
					if (boss.rect.x == 600) {
						if (ticks % 100 == 0 && !shootBoss) {
							bossBullet = new Rectangle(boss.rect.x, boss.rect.y + 70, BULLET_WIDTH, BULLET_HEIGHT);
							shootBoss = true;
						}
						if (bossBullet.x < 0) {
							bossBullet = new Rectangle();
							shootBoss = false;
						}
						if (shootBoss)
							bossBullet.x -= xMotionBossBullet;
					}
					// an i sfera tou boss mas xtipisi
					if (bossBullet.intersects(spaceship.rect)) {
						bossBullet = new Rectangle();
						spaceship.life--;
					}
					// an kapia sfaira xtipisi to boss
					if (boss != null && boss.rect.intersects(bullet1)) {
						bullet1 = new Rectangle();
						shootFirstBullet = false;
						boss.life--;
						if (boss.life == 0) {
							boss = null;
							bossReady = false;
						}
					}
					if (boss != null && boss.rect.intersects(bullet2)) {
						bullet2 = new Rectangle();
						shootSecondBullet = false;
						boss.life--;
						if (boss.life == 0) {
							boss = null;
							bossReady = false;
						}
					}
					if (boss == null) {
						win = true;
						boss = new Boss();
						time.stop();
					}
				}

				// xronos spawn tis kardias
				if (ticks % 3000 == 0 && spaceship.life < 3)
					addLife();
				if (life != null) {
					life.x -= 7;
					if (life.intersects(spaceship.rect)) {
						spaceship.life++;
						life = null;
					}
				}

				if (level == 2) {
					initializeEnemiesStrong();
				}
				if (level == 5)
					bossReady = true;

				// oi exr8oi proxorane,an o exr8os akoubisi to plane tote
				// stamatei o xronos kai xaneis
				// to score paei sto 0
				for (int i = 0; i < enemiesWeak.size(); i++) {
					enemiesWeak.get(i).x -= xMotionEnemyWeak;
					if (enemiesWeak.get(i).intersects(spaceship.rect)) {
						enemiesWeak.remove(i);
						addEnemyWeak();
						spaceship.life--;
						System.out.println("life is decreased");
					}
				}

				for (int i = 0; i < enemiesStrong.size(); i++) {
					enemiesStrong.get(i).rect.x -= xMotionEnemyStrong;
					if (enemiesStrong.get(i).rect.intersects(spaceship.rect)) {
						enemiesStrong.remove(i);
						addEnemyStrong();
						spaceship.life--;
						System.out.println("life is decreased");
					}
				}

				// an oi exr8oi perasoun aristera tis o8onis diagrafonte kai
				// dimiourgounte
				// neoi
				for (int i = 0; i < enemiesWeak.size(); i++)
					if (enemiesWeak.get(i).x < 0) {
						enemiesWeak.remove(i);
						addEnemyWeak();
					}

				for (int i = 0; i < enemiesStrong.size(); i++)
					if (enemiesStrong.get(i).rect.x < 0) {
						enemiesStrong.remove(i);
						addEnemyStrong();
					}

				// elegxoi an oi sfaires petixenoun tous ex8rous kai anevazi to
				// score
				// an petixi exr8o diagrafete o ex8ros kai prosti8ete
				// kenourios,episis diagrafete i antistixi sfaira
				for (int i = 0; i < enemiesWeak.size(); i++) {
					if (enemiesWeak.get(i).intersects(bullet1)) {
						bullet1 = new Rectangle();
						enemiesWeak.remove(i);
						addEnemyWeak();
						shootFirstBullet = false;
						score++;
					}
					if (enemiesWeak.get(i).intersects(bullet2)) {
						bullet2 = new Rectangle();
						enemiesWeak.remove(i);
						addEnemyWeak();
						shootSecondBullet = false;
						score++;
					}
					if (score > highscore)
						highscore = score;
				}

				// an kapia sfaira xtipisi strongEnemy
				for (int i = 0; i < enemiesStrong.size(); i++) {
					if (enemiesStrong.get(i).rect.intersects(bullet1)) {
						bullet1 = new Rectangle();
						enemiesStrong.get(i).life--;
						enemiesStrong.get(i).rect.setLocation(enemiesStrong.get(i).rect.x += 30,
								enemiesStrong.get(i).rect.y);
						if (enemiesStrong.get(i).life == 0) {
							enemiesStrong.remove(i);
							addEnemyStrong();
							score++;
						}
						shootFirstBullet = false;
					}
					if (enemiesStrong.get(i).rect.intersects(bullet2)) {
						bullet2 = new Rectangle();
						enemiesStrong.get(i).life--;
						enemiesStrong.get(i).rect.setLocation(enemiesStrong.get(i).rect.x += 30,
								enemiesStrong.get(i).rect.y);
						if (enemiesStrong.get(i).life == 0) {
							enemiesStrong.remove(i);
							addEnemyStrong();
							score++;
						}
						shootSecondBullet = false;
					}
					if (score > highscore)
						highscore = score;
				}

				// an oi sfaires perasoun de3ia tis o8onis tote diagrafonte kai
				// dimourgounte kenourgies kai boris na 3anapirovolisis
				if (bullet1.x > WINDOW_WIDTH) {
					bullet1 = new Rectangle();
					shootFirstBullet = false;
				}
				if (bullet2.x > WINDOW_WIDTH) {
					bullet2 = new Rectangle();
					shootSecondBullet = false;
				}
				// kinounte oi sferes me tin antistixi taxitita
				if (shootFirstBullet)
					bullet1.x += xMotionBullet;

				if (shootSecondBullet)
					bullet2.x += xMotionBullet;

				// oso exi pati8i to kato kai exi simpliro8i i kinisi kata 50
				// tote stamata
				if (goingDown && counterMove == 50) {
					counterMove = 0;
					goingDown = false;
				}
				// oso exi pati8i to pano kai exi simpliro8i i kinisi kata 50
				// tote stamata
				if (goingUp && counterMove == 50) {
					counterMove = 0;
					goingUp = false;
				}
				// oso exi pati8i to kato koubi kai dn exi katevi kata 50
				if (goingDown && counterMove < 50) {
					spaceship.rect.y -= 5;
					counterMove += 5;
				}
				// oso exi pati8i to pano koubi kai dn exi anevi kata 50
				if (goingUp && counterMove < 50) {
					spaceship.rect.y += 5;
					counterMove += 5;
				}

			} else
				time.stop();
			if (spaceship.life == 0)
				gameOver = true;
			// else
			// gameOver = true;
		}
		renderrer.repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {

	}

	public boolean goingDown;
	public boolean goingUp;

	@Override
	public void keyReleased(KeyEvent e) {
		if (start) {
			if (e.getKeyCode() == KeyEvent.VK_UP)
				if (spaceship.rect.y > 100) {
					goingDown = true;
				}
			if (e.getKeyCode() == KeyEvent.VK_DOWN)
				if (spaceship.rect.y < 500) {
					goingUp = true;
				}
			if (e.getKeyCode() == KeyEvent.VK_SPACE)
				if (!shootFirstBullet) {
					bullet1 = new Rectangle(spaceship.rect.x + 50, spaceship.rect.y + 23, BULLET_WIDTH, BULLET_HEIGHT);
					shootFirstBullet = true;
				} else if (!shootSecondBullet && bullet1.x > 400) {
					bullet2 = new Rectangle(spaceship.rect.x + 50, spaceship.rect.y + 23, BULLET_WIDTH, BULLET_HEIGHT);
					shootSecondBullet = true;
				}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (!start)
			start = true;
		if (gameOver || win) {
			win = false;
			level = 1;
			bossReady = false;
			score = 0;
			xMotionEnemyWeak = DEFAULT_X_MOTION_ENEMY_WEAK;
			xMotionEnemyStrong = DEFAULT_X_MOTION_ENEMY_STRONG;
			gameOver = false;
			time.start();
			spaceship = new Spaceship(new Rectangle(0, 300, 40, 40), 3);
			enemiesWeak = new ArrayList<Rectangle>();
			enemiesStrong = new ArrayList<enemyStrong>();
			boss = new Boss(new Rectangle(1000, 150, 150, 300), BOSS_LIFE);
			bossBullet = new Rectangle();
			shootBoss = false;
			shootFirstBullet = false;
			shootSecondBullet = false;
			bullet1 = new Rectangle();
			bullet2 = new Rectangle();
			enemiesWeak.add(new Rectangle(1000, (int) (Math.random() * 500), 60, 30));
			enemiesWeak.add(new Rectangle(1300, (int) (Math.random() * 500), 60, 30));
			enemiesWeak.add(new Rectangle(1600, (int) (Math.random() * 500), 60, 30));
			enemiesWeak.add(new Rectangle(1900, (int) (Math.random() * 500), 60, 30));

		}
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
