package hangman;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import hangman.console.ConsoleGame;
import hangman.gui.UIGame;
import hangman.model.GameFactory;
import hangman.model.Phrase;
import hangman.model.PhraseStreamGameFactory;

public class Main {

	public static void main(String[] args) {

		InputStream phraseStream = Main.class.getResourceAsStream("/phrases.txt");
		if(phraseStream==null) {
			System.err.println("Resource /phrases.txt not found");
			System.exit(1);
		}
		
		GameFactory gameFactory = new PhraseStreamGameFactory(phraseStream);
		
		//new ConsoleGame(gameFactory).run();
		new UIGame(gameFactory);
	}

}
