package hangman.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents the logical state of a game
 * 
 * The state is represented as two sets of characters. One set contains the good guesses and
 * the other contains the bad guesses.
 * 
 * The game is over when the number of bad guesses exceeds `maxBadGuesses`.
 * 
 */
public class Game {

	private Phrase phrase;

	private CharList goodGuesses;
		
	private CharList badGuesses;

	private final int maxBadMoves;

	
	/**
	 * Initialize a new game
	 * 
	 * @param phrase		the phrase to guess
	 * @param maxBadMoves	bad moves that get you hanged
	 */
	public Game(Phrase phrase, int maxBadMoves) {
		this.phrase = phrase;
		this.maxBadMoves = maxBadMoves;
		
		goodGuesses = new CharList();
		badGuesses = new CharList();
	}

	
	/**
	 * Init a game from a string, this may throw IllegalArgumentException
	 */
	public Game(String phrase, int maxbad) {
		this(new Phrase(phrase), maxbad);
	}
	
	/**
	 * The phrase to guess
	 * @return
	 */
	public Phrase getPhrase() {
		return phrase;
	}

	/**
	 * Characters guessed correctly
	 * @return
	 */
	public CharList getGoodGuesses() {
		return goodGuesses;
	}

	/**
	 * Character guesses that did not appear in the text
	 * @return
	 */
	public CharList getBadGuesses() {
		return badGuesses;
	}

	/**
	 * Number of moves made so far
	 * @return
	 */
	public int allMoves() {
		return badMoves() + goodMoves();
	}
	
	/**
	 * Number of good moves made so far
	 * @return
	 */
	public int goodMoves() {
		return goodGuesses.size();
	}
	
	/**
	 * Number of bad moves made so far
	 * @return
	 */
	public int badMoves() {
		return badGuesses.size();
	}
	
	public int maximumBadMoves() {
		return maxBadMoves;
	}
	
	/**
	 * Return true if the player has won this game
	 * 
	 * A value of false means that either the player lost, or that
	 * the game is still playing
	 */
	public boolean playerWon() {
		return goodGuesses.size()==phrase.countGuesses();
	}
	
	/**
	 * Return true if the game is over
	 * @return
	 */
	public boolean gameOver() {
		return  playerWon() || badMoves() >= maxBadMoves;
	}
	
	/**
	 * Return true if the letter was guessed during the game
	 * @param l
	 * @return 
	 */
	public boolean alreadyGuessed(char l) {
		char c = Phrase.normalize(l);
		return badGuesses.contains(c) || goodGuesses.contains(c);
	}
	
	/**
	 * Make a new move in the game
	 * 
	 * The move is legal if the game is still playing, and the guessed letter has not been
	 * guessed before. In this case, an Integer is returned corresponding to the number
	 * of appearances.  A bad guess appears 0 times, whereas a good guess appears some non-zero
	 * number of times.
	 * 
	 * @param guess	the next guess
	 * @return		null if the move is illegal, else the number of appearances of guess in phrase
	 */
	public Integer makeMove(char guess) {
		guess = Phrase.normalize(guess);

		/* 
		 * First check for illegal move!
		 */
		if(! Phrase.guessable(guess))
			return null;

		if(alreadyGuessed(guess))
			return null;
		
		if(gameOver())
			return null;
		
		/*
		 * Ok, it is legal, just record the outcome
		 */
		int appearances = phrase.appearances(guess);
		
		if(appearances > 0)
			goodGuesses.add(guess);
		else {
			badGuesses.add(guess);
		}
		
		return appearances;
	}
	
	
}
