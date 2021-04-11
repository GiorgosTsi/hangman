package hangman.gui;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * The main window
 * @author vsam
 *
 */
class MainFrame extends JFrame {

	/**
	 *  Font for labels 
	 */
	static final Font labelFont = new Font(Font.SANS_SERIF, Font.PLAIN, 24);
	
	/**
	 * Size of main window
	 */
	static final Dimension winSize = new Dimension(1200, 600);
	
	HangmanCanvas hangmanCanvas;
	GuessPattern guessPattern;
	BadGuesses badGuesses;
	UIGame uiGame;
	
	JButton newGameButton;
	
	MainFrame(UIGame uiGame) {
	
		this.uiGame = uiGame;
		
		/* Quit button */
		JButton quitButton = new JButton("Quit");//creating instance of JButton  
		quitButton.setBounds(20,20,130, 40);//x axis, y axis, width, height
		quitButton.addActionListener(new QuitActionListener(uiGame));
		
		/* New game button */
		newGameButton = new JButton("New game");
		newGameButton.setBounds(180,20,130,40);
		newGameButton.setEnabled(false);
		newGameButton.addActionListener(new NewGameActionListener(uiGame));
				
		/* Hangman canvas */
		hangmanCanvas = new HangmanCanvas(uiGame);
		hangmanCanvas.setBounds(100,100,200, 400);	//x axis, y axis, width, height  
		
		/* Guess pattern */
		guessPattern = new GuessPattern(uiGame);
		guessPattern.setBounds(320,300,880,64);

		/* Bad guesses */
		JLabel badGuessesLabel = new JLabel("Bad guesses");
		badGuessesLabel.setFont(labelFont);
		badGuessesLabel.setBounds(320, 430, 200, 64);
		badGuesses = new BadGuesses(uiGame);
		badGuesses.setBounds(520, 450, 400, 64);

		/* uiGame is the listener for receiving keytype callbacks */
		addKeyListener(uiGame);
		setFocusable(true);
		
		/* Add child components to the window */ 
		add(guessPattern);
		add(quitButton);
		add(newGameButton);
		add(hangmanCanvas);
		add(badGuessesLabel);
		add(badGuesses);
		          
		/* Final configuration */
		setSize(winSize);
		setLayout(null);	//using no layout managers  
		setVisible(true);	//making the frame visible  		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	
}
