package hangman.model;

import java.util.Arrays;

/**
 * A sequence of characters, where each character appears only once.
 * 
 * That is, a character is only added to the sequence if it does not appear already.
 * 
 * @author vsam
 */
public class CharList {

	/* The array of characters */
	private char[] data;
	
	/* The data size */
	private int dataSize;
	
	
	/**
	 * Construct a new empty list
	 */
	public CharList( ) {
		data = new char[4];
		dataSize = 0;
	}
	
	/**
	 * Check if key belongs to the sequence
	 * @param key
	 * @return
	 */
	public boolean contains(char key) {
		for(int i=0; i<data.length; i++)
			if(data[i] == key)
				return true;
		return false;
	}
	
	/**
	 * Add a key to the sequence if it does not exist
	 * @param key
	 */
	public void add(char key) {
		if(! contains(key)) {
			
			/* If the data array is full, we need to increase it */
			if(dataSize == data.length) {
				/* increase data size by doubling its length */
				char[] newData = new char[data.length * 2];
				for(int i=0; i<data.length; i++)
					newData[i] = data[i];
				data = newData;
			}
			
			data[dataSize ++] = key;
		}
	}
	
	/**
	 * The size of the sequence
	 * @return
	 */
	public int size() {
		return dataSize;
	}
	
	/**
	 * Check if the sequence is empty
	 * @return
	 */
	public boolean isEmpty() {
		return dataSize == 0;
	}
	
	/**
	 * Returns an array containing all the characters in the sequence 
	 * @return
	 */
	public char[] getData() {
		/* Note how we use the Java library! No need to implement this again... */
		return Arrays.copyOf(data, dataSize);
	}
}
