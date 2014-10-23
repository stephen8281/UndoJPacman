package ca.ubc.jpacman;

import org.jpacman.framework.factory.DefaultGameFactory;
import org.jpacman.framework.model.Game;

public class UndoableGameFactory extends DefaultGameFactory {

	private transient Game UndoGame;

	@Override
	public Game makeGame() {
		UndoGame = new UndoableGame();
		return UndoGame;
	}

	/**
	 * @return The game created by this factory.
	 */
	@Override
	protected Game getGame() {
		assert UndoGame != null;
		return UndoGame;
	}

}
