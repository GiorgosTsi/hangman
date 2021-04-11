package hangman.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hangman.model.Game;
import hangman.model.GameFactory;
import hangman.model.Phrase;


/**
 * The controller of the application
 * 
 * This class extends KeyListener and is the handler of all keystrokes 
 * in the application
 * 
 * @author vsam
 *
 */
public class UIGame implements KeyListener {

	// game factory
	GameFactory gameFactory;
	
	// main frame (window)
	MainFrame mainFrame;
	
	// The current game
	Game game;
		
	public UIGame(GameFactory gameFactory) {
		// Initialize game factory
		this.gameFactory = gameFactory;
		gameFactory.setMaxBadMoves(7);

		// start up with a new game
		this.game = gameFactory.create();
		
		// Create the main window
		mainFrame = new MainFrame(this);		
	}
	
	// Replace game with a new game
	public void startGame() {
		game = gameFactory.create();
		mainFrame.newGameButton.setEnabled(false);
		mainFrame.repaint();
	}
	
	// quit the application
	public void quit() {
		mainFrame.dispose();
		mainFrame = null;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		game.makeMove(e.getKeyChar());
		if(game.gameOver()) {
			mainFrame.newGameButton.setEnabled(true);
		}
		mainFrame.repaint();
	}
	
	@Override
	public void keyReleased(KeyEvent e) {				
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
	}	
	
}
