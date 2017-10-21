package Tic_Tac_Toe;
public class turn {
	static int turn1 = 1;
	static int player1 = 0;
	static int player2 = 0;
	static char mark;

	static void fillPin(char pin[][]) {
		Integer i, j;
		pin[0][0] = '1';
		pin[0][1] = '2';
		pin[0][2] = '3';
		pin[1][0] = '4';
		pin[1][1] = '5';
		pin[1][2] = '6';
		pin[2][0] = '7';
		pin[2][1] = '8';
		pin[2][2] = '9';
		/*
		 * for (i = 0; i <= pin.length - 1; i++) for (j = 0; j <= pin[i].length
		 * - 1; j++) pin[i][j] = j.toString(i+1+j);
		 */
	}

	static void displayPin(char pin[][]) {
		for (int i = 0; i <= pin.length - 1; i++) {
			for (int j = 0; j <= pin[i].length - 1; j++)
				System.out.print(pin[i][j]);
			System.out.println();
		}
	}

	static void getTurn() {
		turn1++;
		if (turn.turn1 % 2 == 0)
			mark = 'X';
		else
			mark = 'O';
	}

	static void win(char pin[][]) {
		System.out.println("Εχουμε νικιτή");
		System.out.println("Το σκορ έιναι:");
		System.out.println("Player 1: " + player1);
		System.out.println("Player 2: " + player2);
		turn.fillPin(pin);
	}

	static boolean checkPin(char pin[][], char thesi) {
		boolean ok = false;
		switch (thesi) {
		case '1':
			if (pin[0][0] == '1')
				ok = true;
			break;
		case '2':
			if (pin[0][1] == '2')
				ok = true;
			break;
		case '3':
			if (pin[0][2] == '3')
				ok = true;
			break;
		case '4':
			if (pin[1][0] == '4')
				ok = true;
			break;
		case '5':
			if (pin[1][1] == '5')
				ok = true;
			break;
		case '6':
			if (pin[1][2] == '6')
				ok = true;
			break;
		case '7':
			if (pin[2][0] == '7')
				ok = true;
			break;
		case '8':
			if (pin[2][1] == '8')
				ok = true;
		case '9':
			if (pin[2][2] == '9')
				ok = true;
			break;
		}
		return ok;
	}

	static void editPin(char pin[][], char thesi) {
		switch (thesi) {
		case '1':
			pin[0][0] = mark;
			break;
		case '2':
			pin[0][1] = mark;
			break;
		case '3':
			pin[0][2] = mark;
			break;
		case '4':
			pin[1][0] = mark;
			break;
		case '5':
			pin[1][1] = mark;
			break;
		case '6':
			pin[1][2] = mark;
			break;
		case '7':
			pin[2][0] = mark;
			break;
		case '8':
			pin[2][1] = mark;
			break;
		case '9':
			pin[2][2] = mark;
			break;

		}
	}

	static boolean winPin(char pin[][]) {
		boolean win = false;
		if (((pin[0][0] == 'X') && (pin[0][1] == 'X') && (pin[0][2] == 'X'))
				|| ((pin[1][0] == 'X') && (pin[1][1] == 'X') && (pin[1][2] == 'X'))
				|| ((pin[2][0] == 'X') && (pin[2][1] == 'X') && (pin[2][2] == 'X'))
				|| ((pin[0][0] == 'X') && (pin[1][0] == 'X') && (pin[2][0] == 'X'))
				|| ((pin[0][1] == 'X') && (pin[1][1] == 'X') && (pin[2][1] == 'X'))
				|| ((pin[0][2] == 'X') && (pin[1][2] == 'X') && (pin[2][2] == 'X'))
				|| ((pin[0][0] == 'X') && (pin[1][1] == 'X') && (pin[2][2] == 'X'))
				|| ((pin[0][2] == 'X') && (pin[1][1] == 'X') && (pin[2][0] == 'X'))) {
			player1++;
			win = true;
		} else if (((pin[0][0] == 'O') && (pin[0][1] == 'O') && (pin[0][2] == 'O'))
				|| ((pin[1][0] == 'O') && (pin[1][1] == 'O') && (pin[1][2] == 'O'))
				|| ((pin[2][0] == 'O') && (pin[2][1] == 'O') && (pin[2][2] == 'O'))
				|| ((pin[0][0] == 'O') && (pin[1][0] == 'O') && (pin[2][0] == 'O'))
				|| ((pin[0][1] == 'O') && (pin[1][1] == 'O') && (pin[2][1] == 'O'))
				|| ((pin[0][2] == 'O') && (pin[1][2] == 'O') && (pin[2][2] == 'O'))
				|| ((pin[0][0] == 'O') && (pin[1][1] == 'O') && (pin[2][2] == 'O'))
				|| ((pin[0][2] == 'O') && (pin[1][1] == 'O') && (pin[2][0] == 'O'))) {
			player2++;
			win = true;
		}
		return win;
	}
}
