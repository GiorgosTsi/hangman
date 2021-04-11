package hangman.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import hangman.model.Phrase;


class PhraseTests {


	
	@Test
	void testConstructor() {
				
		assertEquals(new Phrase("Superman").getPrintablePhrase(), "SUPERMAN");
		assertEquals(new Phrase(" SuperMan  ").getPrintablePhrase(), "SUPERMAN");
		assertEquals(new Phrase("Superman ").getPrintablePhrase(), "SUPERMAN");
		assertEquals(new Phrase("SuPerman").getPrintablePhrase(), "SUPERMAN");

		assertEquals(new Phrase(" Bridget  O'Hara ").getPrintablePhrase(), "BRIDGET O'HARA");
		
		assertThrows(IllegalArgumentException.class, () -> {
			new Phrase("");
		});

		assertThrows(IllegalArgumentException.class, () -> {
			new Phrase("!! ");
		});
	}
	
	@Test
	void testGuessable() {
		assertEquals(new Phrase("Bridget O' Hara ").countGuessable(), 12);
		assertEquals(new Phrase(" Bridget  O'Hara ").countGuessable(), 12);
		assertEquals(new Phrase("zavarakatranemia").countGuessable(), 16);
		assertEquals(new Phrase("  a  a  a    ").countGuessable(), 3);
	}
	
	@Test
	void testGuesses() {
		assertEquals(new Phrase("Bridget O' Hara ").countGuesses(), 10);
		assertEquals(new Phrase(" Bridget  O'Hara ").countGuesses(), 10);
		assertEquals(new Phrase("zavarakatranemia").countGuesses(), 10);
		assertEquals(new Phrase("  a  a  a    ").countGuesses(), 1);		
	}
	
	@Test
	void testAppearances() {
		
		assertEquals(new Phrase("Bridget O'Hara").appearances('a'), 2);
		assertEquals(new Phrase("Bridget O'Hara").appearances('A'), 2);
		assertEquals(new Phrase("Bridget O'Hara").appearances('\''), -1);
		assertEquals(new Phrase("Bridget O'Hara").appearances(' '), -1);

		assertEquals(new Phrase("Bridget O'Hara").appearances('b'), 1);
		assertEquals(new Phrase("Bridget O'Hara").appearances('B'), 1);
		assertEquals(new Phrase("Bridget O'Hara").appearances('r'), 2);
		assertEquals(new Phrase("Bridget O'Hara").appearances('R'), 2);
		
		assertEquals(new Phrase("Bridget O'Hara").appearances('Z'), 0);
		assertEquals(new Phrase("Bridget O'Hara").appearances('z'), 0);
		
	}
	
}
