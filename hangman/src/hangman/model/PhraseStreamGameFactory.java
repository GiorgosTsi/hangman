package hangman.model;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import hangman.Main;

/**
 * Factory class for games.
 * 
 *  This class reads phrases from a file and returns them in a random order
 * 
 * @author vsam
 *
 */
public class PhraseStreamGameFactory extends GameFactory {

	private ArrayList<Phrase> phrases = new ArrayList<Phrase>();
	
	private int nextPhrase;

	/**
	 * Construct a list of phrases read from the given stream. The stream must
	 * return a text file, with one phrase per line.
	 * 
	 * @param stream  the stream to read phrases from
	 */
	public PhraseStreamGameFactory(InputStream stream) {
		
		super();
		
		phrases = new ArrayList<Phrase>();
		
		/*
		 * Read all phrases from stream, one phrase per line
		 */
		Scanner scn = new Scanner(stream);
		while(scn.hasNext())
			phrases.add( new Phrase(scn.nextLine()) );

		/*
		 * Permute elements of phrases array
		 */
		Random rng = new Random();
		for(int i=phrases.size(); i>0; i--) {
			int j = rng.nextInt(i);
			assert 0<= j && j < i;
			if(i-1 != j) {
				// swap elements in positions i-1 and j
				Phrase tmp = phrases.get(i-1);
				phrases.set(i-1, phrases.get(j));
				phrases.set(j, tmp);
			}
		}
		
		// initialize the next phrase pointer
		nextPhrase = 0;
	}
	
	@Override
	protected Game createGameObject() {
		// create the object
		Game newGame = new Game(phrases.get(nextPhrase), maxBadMoves);
		
		// increment counter
		nextPhrase ++;
		
		// rewind counter if we run out of games
		if(nextPhrase >= phrases.size())
			nextPhrase = 0;
		
		return newGame;
	}
	
	
}
