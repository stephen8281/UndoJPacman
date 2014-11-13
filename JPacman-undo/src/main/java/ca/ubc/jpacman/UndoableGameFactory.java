package ca.ubc.jpacman;

import org.jpacman.framework.factory.DefaultGameFactory;
import org.jpacman.framework.model.Game;

public class UndoableGameFactory extends DefaultGameFactory {

	private transient Game theGame;

	/**
	 * Creates and returns a new UndoableGame
	 */
	@Override
	public Game makeGame() {
		theGame = new UndoableGame();
		return theGame;
	}

	/**
	 * @return the game previously created by this factory
	 */
	@Override
	public Game getGame() {
		return theGame;
	}
}
