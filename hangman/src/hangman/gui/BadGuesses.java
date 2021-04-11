package hangman.gui;

import java.awt.Canvas;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * Draws a dequence of crossed-out characters
 * @author vsam
 *
 */
class BadGuesses extends JPanel {
	
	/**
	 * 
	 */
	private final UIGame uiGame;

	/**
	 * @param uiGame
	 */
	BadGuesses(UIGame uiGame) {
		this.uiGame = uiGame;
	}

	
	public void paint(Graphics g) {		
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
		
		char[] bad = this.uiGame.game.getBadGuesses().getData();
		
		for(int i=0; i<bad.length; i++) {
			g.drawChars(bad, i, 1, i*24, 24);
			g.drawLine(i*24, 24, i*24+20, 0);
		}

		
	}
}