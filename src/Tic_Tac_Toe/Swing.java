package Tic_Tac_Toe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.Timer;
import javax.swing.*;

public class Swing extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Timer time;

	static int turn = 0;

	static int player1 = 0;
	static int player2 = 0;
	static int kinisi = 0;
	ImageIcon iconO = new ImageIcon("o.png");
	ImageIcon iconX = new ImageIcon("x.png");
	ImageIcon currentTurnIcon;
	JButton label1;
	JButton label2;
	JButton label3;
	JButton label4;
	JButton label5;
	JButton label6;
	JButton label7;
	JButton label8;
	JButton label9;

	public Swing() {
		setTitle("first game");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(400, 200);
		setLayout(new GridLayout(3, 3));

		getContentPane().setBackground(Color.LIGHT_GRAY);

		JMenu optionsMenu = new JMenu("Options");
		JMenuItem newGameOption = new JMenuItem("New Game");
		newGameOption.addActionListener(new newGameListener());
		optionsMenu.add(newGameOption);

		JMenuItem showScorOption = new JMenuItem("Show scor");
		showScorOption.addActionListener(new showScorListener());
		optionsMenu.add(showScorOption);

		JMenu colorMenu = new JMenu("Change Colors");
		JMenuItem colorRed = new JMenuItem("Red");
		colorRed.addActionListener(new changeColors());
		JMenuItem colorYellow = new JMenuItem("Yellow");
		colorYellow.addActionListener(new changeColors());
		JMenuItem colorCyan = new JMenuItem("Cyan");
		colorCyan.addActionListener(new changeColors());
		colorMenu.add(colorRed);
		colorMenu.add(colorYellow);
		colorMenu.add(colorCyan);
		optionsMenu.add(colorMenu);

		JMenu helpMenu = new JMenu("Help");
		JMenuItem rulesOption = new JMenuItem("Rules");
		helpMenu.add(rulesOption);
		rulesOption.addActionListener(new helpOption());

		JMenuBar bar = new JMenuBar();
		bar.add(optionsMenu);
		bar.add(helpMenu);
		setJMenuBar(bar);
		label1 = new JButton("1");
		add(label1);
		label1.addActionListener(new moveListener());

		label2 = new JButton("2");
		add(label2);
		label2.addActionListener(new moveListener());

		label3 = new JButton("3");
		add(label3);
		label3.addActionListener(new moveListener());

		label4 = new JButton("4");
		add(label4);
		label4.addActionListener(new moveListener());

		label5 = new JButton("5");
		add(label5);
		label5.addActionListener(new moveListener());

		label6 = new JButton("6");
		add(label6);
		label6.addActionListener(new moveListener());

		label7 = new JButton("7");
		add(label7);
		label7.addActionListener(new moveListener());

		label8 = new JButton("8");
		add(label8);
		label8.addActionListener(new moveListener());

		label9 = new JButton("9");
		add(label9);
		label9.addActionListener(new moveListener());

		addWindowListener(new CheckOnExit());

		time = new Timer(30, this);
		time.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (winPin()) {
			JOptionPane.showMessageDialog(null,
					"There is a winner\n" + "The scor is :\n" + "player1: " + player1 + "\nplayer2: " + player2);
			clear();
			kinisi = 0;
			turn = 0;
		} else if (kinisi == 9) {
			JOptionPane.showMessageDialog(null,
					"There was no winner\nThe scor remains at: \nplayer1: " + player1 + "\nplayer2: " + player2);
			kinisi = 0;
			clear();
		}
		if (turn % 2 != 0)
			try {
				Thread.sleep(800);
				botMove();
			} catch (InterruptedException e1) {
				System.out.println(e1);
			}

	}

	public void botMove() {
		getTurn();
		char randomMove;
		do {
			if (checkIfBotWins() != '0' && checkPin(checkIfBotWins()))
				randomMove = checkIfBotWins();
			else if (checkForBlock() != '0' && checkPin(checkForBlock()))
				randomMove = checkForBlock();
			else
				randomMove=doGoodMove();
				//randomMove = (char) ((int) (Math.random() * 10) + '0');
		} while (!checkPin(randomMove));
		editPin(randomMove);
		kinisi++;
	}

	public char doGoodMove() {
		boolean found;
		int move=0;
		if (label1.getIcon()==iconO)
		{
			if (label2.getIcon()==iconX || label3.getIcon()==iconX){
				if (label5.getIcon()==iconX || label9.getIcon()==iconX){
					if (label4.getIcon()==iconX || label7.getIcon()==iconX){
						found=false;
					}else
						do{
							move=(int)(Math.random()*10);
							}while (move!=4 && move!=7);
				}else 
						do{
						move=(int)(Math.random()*10);
						}while (move!=5 && move!=9);
			}
			else
				do{
					move=(int)(Math.random()*10);
					}while (move!=2 && move!=3);
		}else
		move=(int) (Math.random() * 10);
		System.out.println(move);		return (char) ( move + '0');
	}

	public char checkIfBotWins() {
		if ((label2.getIcon() == iconO && label3.getIcon() == iconO)
				|| (label5.getIcon() == iconO && label9.getIcon() == iconO)
				|| (label4.getIcon() == iconO && label7.getIcon() == iconO))
			return '1';
		if ((label1.getIcon() == iconO && label3.getIcon() == iconO)
				|| (label5.getIcon() == iconO && label8.getIcon() == iconO))
			return '2';
		if ((label1.getIcon() == iconO && label2.getIcon() == iconO)
				|| (label7.getIcon() == iconO && label5.getIcon() == iconO)
				|| (label6.getIcon() == iconO && label9.getIcon() == iconO))

			return '3';
		if ((label1.getIcon() == iconO && label7.getIcon() == iconO)
				|| (label5.getIcon() == iconO && label6.getIcon() == iconO))
			return '4';
		if ((label1.getIcon() == iconO && label9.getIcon() == iconO)
				|| (label4.getIcon() == iconO && label6.getIcon() == iconO)
				|| (label3.getIcon() == iconO && label7.getIcon() == iconO)
				|| (label2.getIcon() == iconO && label8.getIcon() == iconO))
			return '5';
		if ((label3.getIcon() == iconO && label9.getIcon() == iconO)
				|| (label5.getIcon() == iconO && label4.getIcon() == iconO))
			return '6';
		if ((label1.getIcon() == iconO && label4.getIcon() == iconO)
				|| (label5.getIcon() == iconO && label3.getIcon() == iconO)
				|| (label8.getIcon() == iconO && label9.getIcon() == iconO))
			return '7';
		if ((label3.getIcon() == iconO && label5.getIcon() == iconO)
				|| (label7.getIcon() == iconO && label9.getIcon() == iconO))
			return '8';
		if ((label1.getIcon() == iconO && label5.getIcon() == iconO)
				|| (label3.getIcon() == iconO && label6.getIcon() == iconO)
				|| (label7.getIcon() == iconO && label8.getIcon() == iconO))
			return '9';
		return '0';
	}

	public char checkForBlock() {
		if (((label2.getIcon() == iconX && label3.getIcon() == iconX)
				|| (label5.getIcon() == iconX && label9.getIcon() == iconX)
				|| (label4.getIcon() == iconX && label7.getIcon() == iconX)) && checkPin('1'))
			return '1';
		if (((label1.getIcon() == iconX && label3.getIcon() == iconX)
				|| (label5.getIcon() == iconX && label8.getIcon() == iconX)) && checkPin('2'))
			return '2';
		if (((label1.getIcon() == iconX && label2.getIcon() == iconX)
				|| (label7.getIcon() == iconX && label5.getIcon() == iconX)
				|| (label6.getIcon() == iconX && label9.getIcon() == iconX)) && checkPin('3'))
			return '3';
		if (((label1.getIcon() == iconX && label7.getIcon() == iconX)
				|| (label5.getIcon() == iconX && label6.getIcon() == iconX)) && checkPin('4'))
			return '4';
		if (((label1.getIcon() == iconX && label9.getIcon() == iconX)
				|| (label4.getIcon() == iconX && label6.getIcon() == iconX)
				|| (label3.getIcon() == iconX && label7.getIcon() == iconX)
				|| (label2.getIcon() == iconX && label8.getIcon() == iconX)) && checkPin('5'))
			return '5';
		if (((label3.getIcon() == iconX && label9.getIcon() == iconX)
				|| (label5.getIcon() == iconX && label4.getIcon() == iconX)) && checkPin('6'))
			return '6';
		if (((label1.getIcon() == iconX && label4.getIcon() == iconX)
				|| (label5.getIcon() == iconX && label3.getIcon() == iconX)
				|| (label8.getIcon() == iconX && label9.getIcon() == iconX)) && checkPin('7'))
			return '7';
		if (((label2.getIcon() == iconX && label5.getIcon() == iconX)
				|| (label7.getIcon() == iconX && label9.getIcon() == iconX)) && checkPin('8'))
			return '8';
		if (((label1.getIcon() == iconX && label5.getIcon() == iconX)
				|| (label3.getIcon() == iconX && label6.getIcon() == iconX)
				|| (label7.getIcon() == iconX && label8.getIcon() == iconX)) && checkPin('9'))
			return '9';
		return '0';
	}

	private class helpOption implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String actionCommand = e.getActionCommand();
			System.out.println(actionCommand);
			if (actionCommand.equals("Rules"))
				JOptionPane.showMessageDialog(null,
						"The goal of Tic Tac Toe is to be the first player to get three in a row on a 3x3 grid.\nPlayers alternate placing Xs and Os on the board until either \n(a) one player has three in a row, horizontally, vertically or diagonally; or \n(b) all nine squares are filled.If a player is able to draw three Xs or three Os in a row, \nthat player wins.If all nine squares are filled and neither player has three in a row, the game is a draw",
						"Rules", JOptionPane.WARNING_MESSAGE);

			else
				System.out.println("Unexpected error on help.");
		}

	}

	private class changeColors implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String color = e.getActionCommand();
			if (color.equals("Red")) {
				label1.setBackground(Color.RED);
				label2.setBackground(Color.RED);
				label3.setBackground(Color.RED);
				label4.setBackground(Color.RED);
				label5.setBackground(Color.RED);
				label6.setBackground(Color.RED);
				label7.setBackground(Color.RED);
				label8.setBackground(Color.RED);
				label9.setBackground(Color.RED);
			} else if (color.equals("Cyan")) {
				label1.setBackground(Color.CYAN);
				label2.setBackground(Color.CYAN);
				label3.setBackground(Color.CYAN);
				label4.setBackground(Color.CYAN);
				label5.setBackground(Color.CYAN);
				label6.setBackground(Color.CYAN);
				label7.setBackground(Color.CYAN);
				label8.setBackground(Color.CYAN);
				label9.setBackground(Color.CYAN);

			}

			else if (color.equals("Yellow")) {
				label1.setBackground(Color.YELLOW);
				label2.setBackground(Color.YELLOW);
				label3.setBackground(Color.YELLOW);
				label4.setBackground(Color.YELLOW);
				label5.setBackground(Color.YELLOW);
				label6.setBackground(Color.YELLOW);
				label7.setBackground(Color.YELLOW);
				label8.setBackground(Color.YELLOW);
				label9.setBackground(Color.YELLOW);
			} else
				System.out.println("Unexpected error on change colors.");
		}
	}

	private class CheckOnExit implements WindowListener {
		@Override
		public void windowClosed(WindowEvent arg0) {
		}

		@Override
		public void windowClosing(WindowEvent arg0) {
			ConfirmWindow checkers = new ConfirmWindow();
			checkers.setVisible(true);
		}

		@Override
		public void windowDeactivated(WindowEvent arg0) {
		}

		@Override
		public void windowDeiconified(WindowEvent arg0) {
		}

		@Override
		public void windowIconified(WindowEvent arg0) {
		}

		@Override
		public void windowOpened(WindowEvent arg0) {
		}

		@Override
		public void windowActivated(WindowEvent e) {
		}

	}

	private class ConfirmWindow extends JFrame implements ActionListener {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ConfirmWindow() {
			setSize(200, 150);
			setLayout(new BorderLayout());
			getContentPane().setBackground(Color.YELLOW);
			JLabel confirmLabel = new JLabel("Are you sure you want to exit?");
			add(confirmLabel, BorderLayout.CENTER);

			JPanel buttonPanel = new JPanel();
			buttonPanel.setLayout(new FlowLayout());
			buttonPanel.setBackground(Color.ORANGE);

			JButton exitButton = new JButton("Yes");
			exitButton.addActionListener(this);
			buttonPanel.add(exitButton);

			JButton cancelButton = new JButton("No");
			cancelButton.addActionListener(this);
			buttonPanel.add(cancelButton);

			add(buttonPanel, BorderLayout.SOUTH);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String actionCommand = e.getActionCommand();
			if (actionCommand.equals("Yes"))
				System.exit(0);
			else if (actionCommand.equals("No"))
				dispose();
			else
				System.out.println("Unexpected Error in Confirm Window.");
		}

	}

	public static void main(String args[]) {
		Swing game = new Swing();
		game.setVisible(true);

	}

	private class newGameListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			clear();
			kinisi = 0;
			player1 = 0;
			player2 = 0;
			turn = 0;
		}
	}

	private class showScorListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "The scor is :\n" + "player1: " + player1 + "\nplayer2: " + player2);
		}
	}

	private class moveListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (!e.getActionCommand().isEmpty()) {
				char thesi = e.getActionCommand().charAt(0);
				if (turn % 2 == 0) {
					getTurn();
					kinisi++;
					editPin(thesi);
				}
			}
		}
	}

	public void clear() {
		label1.setText("1");
		label1.setIcon(null);
		label2.setText("2");
		label2.setIcon(null);
		label3.setText("3");
		label3.setIcon(null);
		label4.setText("4");
		label4.setIcon(null);
		label5.setText("5");
		label5.setIcon(null);
		label6.setText("6");
		label6.setIcon(null);
		label7.setText("7");
		label7.setIcon(null);
		label8.setText("8");
		label8.setIcon(null);
		label9.setText("9");
		label9.setIcon(null);
	}

	boolean winPin() {
		boolean win = false;
		if (((label1.getIcon() == iconX) && (label2.getIcon() == iconX) && (label3.getIcon() == iconX))
				|| ((label5.getIcon() == iconX) && (label4.getIcon() == iconX) && (label6.getIcon() == iconX))
				|| ((label7.getIcon() == iconX) && (label8.getIcon() == iconX) && (label9.getIcon() == iconX))
				|| ((label1.getIcon() == iconX) && (label4.getIcon() == iconX) && (label7.getIcon() == iconX))
				|| ((label2.getIcon() == iconX) && (label5.getIcon() == iconX) && (label8.getIcon() == iconX))
				|| ((label3.getIcon() == iconX) && (label6.getIcon() == iconX) && (label9.getIcon() == iconX))
				|| ((label1.getIcon() == iconX) && (label5.getIcon() == iconX) && (label9.getIcon() == iconX))
				|| ((label3.getIcon() == iconX) && (label5.getIcon() == iconX) && (label7.getIcon() == iconX))) {
			player1++;
			win = true;
		}

		else if (((label1.getIcon() == iconO) && (label2.getIcon() == iconO) && (label3.getIcon() == iconO))
				|| ((label5.getIcon() == iconO) && (label4.getIcon() == iconO) && (label6.getIcon() == iconO))
				|| ((label7.getIcon() == iconO) && (label8.getIcon() == iconO) && (label9.getIcon() == iconO))
				|| ((label1.getIcon() == iconO) && (label4.getIcon() == iconO) && (label7.getIcon() == iconO))
				|| ((label2.getIcon() == iconO) && (label5.getIcon() == iconO) && (label8.getIcon() == iconO))
				|| ((label3.getIcon() == iconO) && (label6.getIcon() == iconO) && (label9.getIcon() == iconO))
				|| ((label1.getIcon() == iconO) && (label5.getIcon() == iconO) && (label9.getIcon() == iconO))
				|| ((label3.getIcon() == iconO) && (label5.getIcon() == iconO) && (label7.getIcon() == iconO))) {
			player2++;
			win = true;

		}
		return win;
	}

	public void getTurn() {
		turn++;
		if (turn % 2 == 0)
			currentTurnIcon = iconO;
		else
			currentTurnIcon = iconX;

	}

	void editPin(char thesi) {
		switch (thesi) {
		case '1':
			label1.setIcon(currentTurnIcon);
			label1.setText("");
			break;
		case '2':
			label2.setIcon(currentTurnIcon);
			label2.setText("");
			break;
		case '3':
			label3.setIcon(currentTurnIcon);
			label3.setText("");
			break;
		case '4':
			label4.setIcon(currentTurnIcon);
			label4.setText("");
			break;
		case '5':
			label5.setIcon(currentTurnIcon);
			label5.setText("");
			break;
		case '6':
			label6.setIcon(currentTurnIcon);
			label6.setText("");
			break;
		case '7':
			label7.setIcon(currentTurnIcon);
			label7.setText("");
			break;
		case '8':
			label8.setIcon(currentTurnIcon);
			label8.setText("");
			break;
		case '9':
			label9.setIcon(currentTurnIcon);
			label9.setText("");
			break;
		}
	}

	boolean checkPin(char thesi) {
		boolean ok = false;
		switch (thesi) {
		case '1':
			if (label1.getText().equals("1"))
				ok = true;
			break;
		case '2':
			if (label2.getText().equals("2"))
				ok = true;
			break;
		case '3':
			if (label3.getText().equals("3"))
				ok = true;
			break;
		case '4':
			if (label4.getText().equals("4"))
				ok = true;
			break;
		case '5':
			if (label5.getText().equals("5"))
				ok = true;
			break;
		case '6':
			if (label6.getText().equals("6"))
				ok = true;
			break;
		case '7':
			if (label7.getText().equals("7"))
				ok = true;
			break;
		case '8':
			if (label8.getText().equals("8"))
				ok = true;
			break;
		case '9':
			if (label9.getText().equals("9"))
				ok = true;
			break;
		}
		return ok;
	}

}
