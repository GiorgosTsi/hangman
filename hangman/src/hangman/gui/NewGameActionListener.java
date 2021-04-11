package hangman.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class NewGameActionListener implements ActionListener {

	UIGame uiGame;
	
	NewGameActionListener(UIGame uiGame) {
		this.uiGame = uiGame;
	}	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		uiGame.startGame();
	}

}
