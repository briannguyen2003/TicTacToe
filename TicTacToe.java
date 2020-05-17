package TicTacToe;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
public class TicTacToe {
	private static char[] gameBoard = {' ',' ',' ', ' ', ' ', ' ',' ',' ',' '};
	private static ArrayList<Integer> playedPositions = new ArrayList<Integer>();
	private static boolean gameOn = true;
	private static ArrayList<Integer> playerFeed = new ArrayList<Integer>();
	private static ArrayList<Integer> cpuFeed = new ArrayList<Integer>();
	
	public static void main(String[] args) {
		tutorial();
		placePiece(firstMove());
	}
	public static void printGameBoard() {
		System.out.println(gameBoard[0] + " | " + gameBoard[1] + " | " + gameBoard[2]);
		System.out.println("--+---+--");
		System.out.println(gameBoard[3] + " | " + gameBoard[4] + " | " + gameBoard[5]);
		System.out.println("--+---+--");
		System.out.println(gameBoard[6] + " | " + gameBoard[7] + " | " + gameBoard[8]);
		System.out.println("\n");
	}
	public static void placePiece(boolean firstIsPlayer) {
		if(firstIsPlayer) {
			System.out.println("You: X");
			System.out.println("CPU: O");
			while(true) {
				printGameBoard();
				int playerPos = playerInput();
				playerFeed.add(playerPos);
				gameBoard[playerPos - 1] = 'X';
				testWinCondition(playerFeed, true);
				if(playedPositions.size() == 9 && gameOn == true) {
					tie();
					break;
				}
				int cpuPos = cpuInput();
				cpuFeed.add(cpuPos);
				gameBoard[cpuPos - 1] = 'O';
				testWinCondition(cpuFeed, false);
			}
		}
		else {
			System.out.println("You: O");
			System.out.println("CPU: X");
			while(true) {
				int cpuPos = cpuInput();
				cpuFeed.add(cpuPos);
				gameBoard[cpuPos - 1] = 'X';
				testWinCondition(cpuFeed, false);
				if(playedPositions.size() == 9 && gameOn == true) {
					tie();
					break;
				}
				printGameBoard();
				int playerPos = playerInput();
				playerFeed.add(playerPos);
				gameBoard[playerPos - 1] = 'O';
				testWinCondition(playerFeed, true);
			}
		}
	}
	public static int playerInput(){
		boolean cont = true;
		String positionString = "";
		int position = -1;
		while(cont) {
			Scanner input = new Scanner(System.in);
			System.out.print("Enter a position 1-9: ");
			positionString = input.next();
			try {
				position = Integer.parseInt(positionString);
				if(position < 1 || position > 9) {
					System.out.println("Not a valid position, try again.");
					printGameBoard();
					cont = true;
				}
				else if(playedPositions.contains(position)) {
					System.out.println("Space is taken, try again.");
					printGameBoard();
					cont = true;
				}
				else cont = false;
				
			} catch(Exception ex) {
				System.out.println("Not a valid position, try again.");
				printGameBoard();
				cont = true;
			}
		}
		playedPositions.add(position);
		System.out.print("\n");
			return position;
	}
	public static int cpuInput() {
		boolean cont = true;
		Random rand = new Random();
		int position = -1;
		while(cont) {
			position = rand.nextInt(9) + 1;
			if(playedPositions.contains(position)) {
				cont = true;
			}
			else cont = false;
		}
		playedPositions.add(position);
		return position;
	}
	public static void testWinCondition(ArrayList<Integer> feed, boolean isPlayer) {
		if(feed.contains(1) && feed.contains(2) && feed.contains(3)) {
			win(isPlayer);
		}
		else if(feed.contains(4) && feed.contains(5) && feed.contains(6)) {
			win(isPlayer);
		}
		else if(feed.contains(7) && feed.contains(8) && feed.contains(9)) {
			win(isPlayer);
		}
		else if(feed.contains(1) && feed.contains(4) && feed.contains(7)) {
			win(isPlayer);
		}
		else if(feed.contains(2) && feed.contains(5) && feed.contains(8)) {
			win(isPlayer);
		}
		else if(feed.contains(3) && feed.contains(6) && feed.contains(9)) {
			win(isPlayer);
		}
		else if(feed.contains(1) && feed.contains(5) && feed.contains(9)) {
			win(isPlayer);
		}
		else if(feed.contains(3) && feed.contains(5) && feed.contains(7)) {
			win(isPlayer);
		}
	}
	public static void win(boolean isPlayer) {
		gameOn = false;
		printGameBoard();
		if(isPlayer) {
			System.out.println("You win!");
			System.out.print("\n");
		}
		else {
			System.out.println("You lose :(");
			System.out.print("\n");
		}
		playAgain();
	}
	public static void tie() {
		gameOn = false;
		System.out.println("Its a tie!");
		playAgain();
	}
	public static boolean firstMove() {
		boolean yesOrNo = true;
		boolean cont = true;
		while(cont) {
			Scanner sc = new Scanner(System.in);
			System.out.print("Do you want to go first? (Y/N) ");
			char yesOrNoString = sc.next().charAt(0);
			if(yesOrNoString == 'Y' || yesOrNoString == 'y') {
				yesOrNo = true;
				cont = false;
			}
			else if(yesOrNoString == 'N' || yesOrNoString == 'n') {
				yesOrNo = false;
				cont = false;
			}
			else {
				System.out.println("Not a valid option. Try again.");
				cont = true;
			}
		}
		return yesOrNo;
	}
	public static void playAgain() {
		boolean yesOrNo = true;
		boolean cont = true;
		while(cont) {
			Scanner sc = new Scanner(System.in);
			System.out.print("Do you want to play again? (Y/N) ");
			char yesOrNoString = sc.next().charAt(0);
			if(yesOrNoString == 'Y' || yesOrNoString == 'y') {
				yesOrNo = true;
				cont = false;
			}
			else if(yesOrNoString == 'N' || yesOrNoString == 'n') {
				yesOrNo = false;
				cont = false;
			}
			else {
				System.out.println("Not a valid option. Try again.");
				cont = true;
			}
		}
		System.out.print("\n");
		if (yesOrNo) {
			resetGame();
			placePiece(firstMove());
		}
		else {
			System.out.println("Thanks for playing!");
			System.exit(0);
		}
	}
	public static void resetGame() {
		for(int i = 0; i < 9; i++) {
			gameBoard[i] = ' ';
		}
		gameOn = true;
		playedPositions.clear();
		playerFeed.clear();
		cpuFeed.clear();
	}
	public static void tutorial() {
		System.out.println("The game board is labeled with positions from 1-9.");
		System.out.println("This is what the board looks like: ");
		System.out.println("1" + " | " + "2" + " | " + "3");
		System.out.println("--+---+--");
		System.out.println("4" + " | " + "5" + " | " + "6");
		System.out.println("--+---+--");
		System.out.println("7" + " | " + "8" + " | " + "9");
		System.out.print("\n");
	}
}
