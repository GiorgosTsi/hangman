package hangman.model;

/**
 * Abstract base class for game factories
 * 
 * @author vsam
 *
 */
public abstract class GameFactory {

	private int gamesCreated;

	protected int maxBadMoves;
	
	
	/**
	 * Can only be used from subclasses.
	 */
	protected GameFactory() {
		gamesCreated = 0;
		maxBadMoves = 7;
	}
	
	
	/**
	 * This should be overriden by concrete subclasses to return new
	 * games.
	 * 
	 * @return a new game object, or null if no more games can be created
	 */
	abstract protected Game createGameObject();
	
	
	public int getMaxBadMoves() {
		return maxBadMoves;
	}


	public void setMaxBadMoves(int maxBadMoves) {
		this.maxBadMoves = maxBadMoves;
	}


	/**
	 * This is the method that should be called to obtain new games
	 * 
	 * @return a new game object
	 */
	public Game create() {
		Game newGame = createGameObject();
		if(newGame != null)
			gamesCreated++;
		return newGame;
	}

	
	/**
	 * Get number of games created
	 * @return
	 */
	public int getGamesCreated() {
		return gamesCreated;
	}
	
}
