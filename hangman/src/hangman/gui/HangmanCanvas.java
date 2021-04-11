package hangman.gui;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import hangman.model.Game;

/**
 * Draws the hangman sketch
 * @author vsam
 *
 */
class HangmanCanvas extends JPanel {

	/**
	 * The model
	 */
	private final UIGame uiGame;

	/**
	 * @param uiGame
	 */
	HangmanCanvas(UIGame uiGame) {
		this.uiGame = uiGame;
		setLayout(null);
	}

	@Override
	public void paint(Graphics g) {
		Game game = this.uiGame.game;		
		int level = game.badMoves();
		
		/* Change the background as needed */ 
		if(game.gameOver()) {
			if(game.playerWon())
				setBackground(Color.GREEN);
			else
				setBackground(Color.RED);
		} else
			setBackground(Color.CYAN);
		
		// Clears the panel area
		super.paint(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		// set stroke to a thick line
		BasicStroke bs = new BasicStroke(10, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
		
		g2.setColor(Color.BLACK);
		
		// Gallows
		g2.setStroke(bs);
		int[] x = { 20, 20,  120, 120 };
		int[] y = { 288, 20, 20, 40 };
		g2.drawPolyline(x,y,4);

		// head
		if(level>=1) 
			g2.drawOval(100, 40, 40, 40);
		
		if(level>=2)
			g2.drawLine(120, 80, 120, 120);
						
		if(level>=3)
			g2.drawLine(120, 110, 90, 80);

		if(level>=4)
			g2.drawLine(120, 110, 150, 80);

		if(level>=5)
			g2.drawLine(120, 120, 120, 150);

		if(level>=6)
			g2.drawLine(120, 150, 90, 190);

		if(level>=7)
			g2.drawLine(120, 150, 150, 190);
		
	}
}