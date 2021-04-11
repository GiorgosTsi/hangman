package hangman.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class QuitActionListener implements ActionListener {

	UIGame uiGame;
	
	QuitActionListener(UIGame uiGame) {
		this.uiGame = uiGame;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		uiGame.quit();
	}

}

