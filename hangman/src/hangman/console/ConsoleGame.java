package hangman.console;

import java.io.InterruptedIOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;

import hangman.model.Game;
import hangman.model.GameFactory;
import hangman.model.Phrase;

/**
 * Play an instance of hangman on the console
 * 
 * @author vsam
 *
 */
public class ConsoleGame {
	
	// Game factory
	GameFactory gameFactory;
	
	// Current game
	Game game;
	
	/*
	 * The following two are used for input and output
	 */
	Scanner in = new Scanner(System.in);
	PrintStream out = System.out;
	
	
	/**
	 * Create a new game interaction
	 */
	
	public ConsoleGame(GameFactory gameFactory) {

		// Initialize game factory
		this.gameFactory = gameFactory;
		gameFactory.setMaxBadMoves(7);
		
		// initialize the first game
		game = gameFactory.create();
	}

	/*
	 * The following two constants render the hangman in text, see printHangman to understand how.
	 */
		
	static private final String[] hangmanRows = {
		"---------  ",	// 0
		"|       |  ",	// 1
		"|       O  ",	// 2
		"|       |  ",	// 3
		"|      \\|  ",	// 4
		"|      \\|/ ",	// 5
		"|       |  ",	// 6
		"|      /   ",  // 7
		"|      / \\ ", // 8
		"|           "  // 9				
	};
	
	static private final int[][] levelRaster = {
			{0, 1, 9, 9, 9, 9, 9 },
			{0, 1, 2, 9, 9, 9, 9 },
			{0, 1, 2, 3, 9, 9, 9 },
			{0, 1, 2, 4, 9, 9, 9 },
			{0, 1, 2, 5, 9, 9, 9 },
			{0, 1, 2, 5, 6, 9, 9 },
			{0, 1, 2, 5, 6, 7, 9 },
			{0, 1, 2, 5, 6, 8, 9 }
	};
	
	/*
	 * Display the little hang,man graphic
	 */
	void printHangman() {
		for(int row : levelRaster[game.badMoves()])
			out.println(hangmanRows[row]);
	}
	
	/*
	 * Game score, this is printed on top
	 */
	void printScore() {
		out.format("Hangman: Next move: %2d     Bad moves so far: %2d of %2d%n%n", 
				game.allMoves(), game.badMoves(), game.maximumBadMoves());		
	}

	/*
	 * Display the phrase, with _  in place of unguessed letters
	 */
	void printSecretPhrase() {
		out.print("Guess the next letter:  [");
		 
		Phrase phrase = game.getPhrase();
		
		for(int i=0; i < phrase.getLength(); i++) {
			char c = phrase.getChar(i);
			
			if(i>0)
				out.print(" ");
			
			if(Phrase.guessable(c) && ! game.getGoodGuesses().contains(c))
				out.print('_');
			else 
				out.print(c);
		}
		
		out.println("]");
	}
	
	/*
	 * Remind the user of bad guesses
	 */
	void printBadGuesses() {
	
		if(! game.getBadGuesses().isEmpty()) {
			out.print("You have guessed badly to your doom, these letters:");
			for(char c: game.getBadGuesses().getData()) {
				out.print(" "+c);
			}
			out.println();
		}
		
	}

	/*
	 * Display the view of the game state
	 */
	void showView() {
		printScore();
		printHangman();
		printSecretPhrase();		
		printBadGuesses();
	}
	
	/*
	 * Read a line from the given scanner and get a guess, or a quit message
	 */
	char readGuess() throws InterruptedIOException {
		do {
			out.print("Enter your guess => ");
			String line = in.nextLine();
			
			if(line.length()==0)
				continue;
			
			if(line.length() == 1) {
				return line.charAt(0);
			}
			
			if(line.equals("quit")) {
				out.println(" ** ** ** ** Chicken ** ** ** ** ");
				throw new InterruptedIOException();
			}
			
			out.println("    I do not understand this, enter a letter for your guess (or type 'quit' if you are chicken)");
			
		} while(true);
	}
	
	
	/**
	 * Execute the game
	 */
	void runGame() {
		
		do {
			
			/* Display the goodies */
			showView();
			
			/* Make a move */
			Integer outcome = null;
			try {
				
				char guess;
				do {
					guess = readGuess();
					outcome = game.makeMove(guess);
				} while(outcome == null);
					
			} catch(InterruptedIOException e) {
				
			}
				
			/* Player quit */
			if(outcome==null)
				return;
		
			/* Encourage the player */
			if(outcome == 0) 
				out.println("He he he, you failed.");
			else
				out.println("....  ok, you were lucky this time");	
			
			
		} while(! game.gameOver());

		/* Final friendly message */
		if(game.playerWon()) 
			out.println("   --->>  YOU WILL LIVE TO SEE ANOTHER DAY!  <<---- ");			
		else
			out.println("   --->>  YOU ARE DEAD BY HANGING!  <<---- ");
		
	}

	
	
	/**
	 * Main loop
	 */
	public void run() {
		boolean quit = false;
		do {
			runGame();
			out.println("============================================================");
			
			// A short read loop to confirm trying again (or not)
			while(true) {
				out.print("Do you want to try again ? (Y/N)  => ");
				String line = in.nextLine();
				
				line = line.toUpperCase();
				if(line.startsWith("Y")) {
					game = gameFactory.create();
					break;
				}
				if(line.startsWith("N")) {
					quit = true;
					break;
				}
			}
		} while(! quit);
	}
	
}
