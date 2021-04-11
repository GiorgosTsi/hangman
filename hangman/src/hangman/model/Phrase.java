package hangman.model;

/**
 * A phrase is a non-empty sequence of chars, where letters are to be guessed by the algorithm. 
 * 
 * A phrase can contain other characters, for example "Object-oriented programming". The '-'
 * and the space are ''non-guessable'', so the player should see them at the start of a game.
 * 
 * Guessable characters are ''normalized'', which basically means, only upper-case characters
 * are used.
 * 
 * @author vsam
 *
 */
public class Phrase {

	/**
	 * Array of normalized chars
	 */
	private char[] phrase;
	
	/**
	 * Construct the phrase after normalization
	 * @param pw
	 */
	public Phrase(String pw) {
		
		/* Break up along whitespaces */
		String[] swords = pw.trim().split("\\s+");
		if(swords.length == 0)
			throw new IllegalArgumentException("There are no words in "+pw);		
	
		/* Concatenate words after converting to upper case, with one space between words,
		 * and converted to upper case
		 */
		StringBuilder phraseBuilder = new StringBuilder();
		for(int i=0; i< swords.length; i++) {
			
			/* An empty word is not allowed! */
			if(swords[i].length()==0)
				throw new IllegalArgumentException("There is an empty word in "+pw);		
				
			if(i > 0)
				phraseBuilder.append(" ");
			phraseBuilder.append(swords[i].toUpperCase());
		}
		
		/* Set the printable phrase */
		String printablePhrase = phraseBuilder.toString();
		
		/* Turn into array of chars */
		phrase = printablePhrase.toCharArray();
		
		/* Final check: we must have guessable characters */
		if(countGuessable() == 0) 
			throw new IllegalArgumentException("There are no guessable characters in "+pw);
	}
	
	/**
	 * Returns the number of number of guessable characters in the phrase  
	 * @return 
	 */
	public int countGuessable() {
		int count = 0;
		
		for(int wno=0; wno < phrase.length; wno++) {
			if(guessable(phrase[wno]))
				count ++;
		}
		
		return count;
	}
	
	/**
	 * Returns the number of distinct guesses in the phrase
	 * 
	 * @param c
	 * @return
	 */
	public int countGuesses() {
		int count = 0;
		
		/* Check every character */
		for(int wno=0; wno< phrase.length; wno++) {
			/* These do not count! */
			if(!guessable(phrase[wno]))
				continue;
			
			/* Did we see it before? */
			boolean found = false;
			for(int i=0; i<wno; i++) {
				if(phrase[i] == phrase[wno]) {
					found = true;
					break;
				}
			}
			
			if(! found)
				count++;
		}
		
		return count;
	}
	
	/**
	 * The phrase as a string
	 * @return
	 */
	public String getPrintablePhrase() {
		return new String(phrase);
	}

	/**
	 * Get the char at a certain position
	 * @param pos
	 * @return
	 */
	public char getChar(int pos) {
		return phrase[pos];
	}
	
	/**
	 * Length of the phrase
	 * @return
	 */
	public int getLength() {
		return phrase.length;
	}

	/**
	 * Return the number of occurrences of a guessable character in the word.
	 * 
	 *  If c is non-guessable, return -1;
	 * 
	 * @param c
	 * @return
	 */
	public int appearances(char c) {
		c = normalize(c);
		if(! guessable(c))
			return -1;
		
		int count = 0;
		for(int i=0; i<phrase.length; i++)
			if(c==phrase[i])
				count ++;
		
		return count;
	}
	
	/**
	 * Returns true if c is a guessable character and appears in the phrase 
	 * 
	 * @param c
	 * @return
	 */
	public boolean contains(char c) {
		return appearances(c)>0;
	}

	
	/**
	 * Turn a character into a normalized form. 
	 * 
	 * Currently, this just raises all letters to upper case and leaves all other
	 * characters alone.
	 * 
	 * @param c
	 * @return
	 */
	static public char normalize(char c) {
		return  (Character.isLetter(c)) ?  Character.toUpperCase(c) : c;
	}
	
	/**
	 * This is the definition if 'guessable' characters. These are characters in a phrase that the players
	 * should guess.
	 * 
	 * Currently, this concept is identical to letters.
	 * 
	 * @param c
	 * @return
	 */
	static public boolean guessable(char c) {
		return Character.isLetter(c);
	}
}
