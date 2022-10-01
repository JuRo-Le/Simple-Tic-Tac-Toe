/**
 * 
 */
import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;
/**
 * @author Le
 *
 */
class TicTacToe {

	static final String line1 = "---------";
    static final String line2 = "| ";
    static final String line3 = " |";
	static final String space = " ";
    static final String X = "X";
    static final String O = "O";
    static final String S = "_";

    static int x = 0, o = 0, s = 0;
    static int[] input = new int[2];
    static boolean check = true;
    static int number = 0;
    static boolean stop = false;
    
    private static void drawRubik(String[] xo) {
		x = 0; 
		o = 0; 
		s = 0;
        System.out.println(line1);
		for (int i = 0; i < xo.length; i++) {
            // layout
			if (i == 0 || i == 3 || i == 6) {
				System.out.print(line2 + xo[i]);
			} else if (i == 2 || i == 5 || i == 8) {
				System.out.println(space + xo[i] + line3);
			} else {
				System.out.print(space + xo[i]);
			}
            // counts x o _
            if (xo[i].equals(X)) {
                x++;
            } else if (xo[i].equals(O)) {
                o++;
            } else {
                s++;
            }
		}
        System.out.println(line1);
    }

    private static void checkWiner(String[] xo) {
        int win = 0; 
		String winner = S;
        // 1 line winner
		// XXX______
		if (checkLine(xo, 0, 1, 2)) {
			win++;
			winner = xo[0];
		}
		// ___XXX___
		if (win < 2 && checkLine(xo, 3, 4, 5)) {
			win++;
			winner = xo[3];
		}
		// ______XXX
		if (win < 2 && checkLine(xo, 6, 7, 8)) {
			win++;
			winner = xo[6];
		}
		// X__X__X__
		if (win < 2 && checkLine(xo, 0, 3, 6)) {
			win++;
			winner = xo[0];
		}
		// _X__X__X_
		if (win < 2 && checkLine(xo, 1, 4, 7)) {
			win++;
			winner = xo[1];
		}
		// __X__X__X
		if (win < 2 && checkLine(xo, 2, 5, 8)) {
			win++;
			winner = xo[2];
		}
		// X___X___X
		if (win < 2 && checkLine(xo, 0, 4, 8)) {
			win++;
			winner = xo[0];
		}
		// __X_X_X__
		if (win < 2 && checkLine(xo, 2, 4, 6)) {
			win++;
			winner = xo[2];
		}
        // 2 lines winner
        // XXX__X__X
        if (checkLine(xo, 0, 1, 2) && checkLine(xo, 2, 5, 8)) {
            win--;
            winner = xo[0];
        }
        // XXXX__X__
        if (checkLine(xo, 0, 1, 2) && checkLine(xo, 0, 3, 6)) {
            win--;
            winner = xo[0];
        }
        // __X__XXXX
        if (checkLine(xo, 6, 7, 8) && checkLine(xo, 2, 5, 8)) {
            win--;
            winner = xo[6];
        }

		// impossible check
		//if (Math.abs(x - o) > 1 || win > 1) {
		//	stop = true;
		//	System.out.println("Impossible");
		//}
		// win check
		if (win == 1) {
			stop = true;
			System.out.println(winner + " wins");
		}
		// draw check
		if (!stop) {
			if (s == 0) {
				stop = true;
				System.out.println("Draw");
			}
		}
		// running check
		//if (!stop) {
		//	System.out.println("Game not finished");
		//}
    }

    private static void checkDigit(Scanner scanner) {
        int i = 0;
        while (i < 2) {
            if (scanner.hasNextInt()) {
                input[i] = scanner.nextInt();
                i++;
                check = true;
            } else {
                System.out.println("You should enter numbers!");
                i = 0;
                check = false;
            }
        }
    }

    private static void checkNumber() {
        if (input[0] <= 0 || input[0] > 3 || input[1] <= 0 || input[1] > 3) {
            check = false;
            System.out.println("Coordinates should be from 1 to 3!");
        } else {
            check = true;
        }
    }

    private static void checkExist(String[] xo) {
		number = 0;
        if (input[0] == 1) {
            number = input[1] - 1;
        } else {
            number = 3 * (input[0] - 1) + input[1] - 1;
        }

        if (!xo[number].equals(S)) {
            check = false;
            System.out.println("This cell is occupied! Choose another one!");
        } else {
            check = true;
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
		String[] xo = "_________".split("");
        drawRubik(xo);
        String player = S;
        int round = 0;
        while (!stop) {
            do {
                checkDigit(scanner);
                if (check) {
                    checkNumber();
                }
                if (check) {
                    checkExist(xo);
                }
            } while (!check);
            if (round % 2 == 0) {
                player = X;
            } else {
                player = O;
            }
            xo[number] = player;
            drawRubik(xo);
            checkWiner(xo);
            round++;
        }
	}
	
	private static boolean checkLine(String[] xo, int i, int j, int k) {
		return !xo[i].equals(S) && xo[i].equals(xo[j]) && xo[i].equals(xo[k]);
	}
	
}
