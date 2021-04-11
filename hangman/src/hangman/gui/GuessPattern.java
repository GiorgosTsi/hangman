package hangman.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import hangman.model.Phrase;

/**
 * The pattern of hidden and guessed letters of the phrase
 * @author vsam
 *
 */
class GuessPattern extends JPanel {
	
	/**
	 * 
	 */
	private final UIGame uiGame;

	/**
	 * @param uiGame
	 */
	GuessPattern(UIGame uiGame) {
		this.uiGame = uiGame;
	}

	@Override
	public void paint(Graphics g) {
		Phrase phrase = this.uiGame.game.getPhrase();
					
		char[] drawnChar = new char[phrase.getLength()];
		for(int i=0; i<phrase.getLength(); i++) {
			char c = phrase.getChar(i);
			if(Phrase.guessable(c) && ! this.uiGame.game.getGoodGuesses().contains(c))
				drawnChar[i] = '_';
			else
				drawnChar[i] = c;
		}
		
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
		
		for(int i=0; i<phrase.getLength(); i++) {
			if(drawnChar[i]!='_')
				g.drawChars(drawnChar, i, 1, i*24, 24);
		}

		g.setColor(Color.DARK_GRAY);
		for(int i=0; i<phrase.getLength(); i++) {
			if(drawnChar[i]=='_')
				g.drawRect(i*24, 0, 18, 24);
		}

		
	}	
}