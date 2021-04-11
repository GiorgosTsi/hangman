package hangman.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import hangman.model.CharList;

class CharListTests {

	@Test
	void testAdd() {
		
		CharList l = new CharList();
		
		l.add('a');
		l.add('b');
		
		assertFalse(l.isEmpty());

		assertEquals(l.size(), 2);
		assertTrue(l.contains('a'));
		assertTrue(l.contains('b'));

		assertFalse(l.contains('c'));
	}

	@Test
	void testEmpty() {
		CharList l = new CharList();
		assertTrue(l.isEmpty());
		
		l.add('a');
		assertFalse(l.isEmpty());
	}
	
	
	@Test
	void testEnlarge() {
		CharList l = new CharList();
		int i=0;
		for(char c='a'; c<='z'; c++) {
			l.add(c);
			i++;
			
			assertEquals(l.size(), i);
			assertEquals(l.getData().length, i);
		}
	}
	
	@Test
	void testNoDuplicates() {
		CharList l = new CharList();
		l.add('a');
		l.add('b');
		l.add('c');
		
		assertEquals(l.size(), 3);
		l.add('a');
		assertEquals(l.size(), 3);
		l.add('b');
		assertEquals(l.size(), 3);
		l.add('c');
		assertEquals(l.size(), 3);

		l.add('d');
		assertEquals(l.size(), 4);

	}
	
}
