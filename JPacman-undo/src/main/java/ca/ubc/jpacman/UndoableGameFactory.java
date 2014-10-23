package ca.ubc.jpacman;

import org.jpacman.framework.factory.DefaultGameFactory;
import org.jpacman.framework.model.Game;

public class UndoableGameFactory extends DefaultGameFactory {

	private transient Game theGame;

	@Override
	public Game makeGame() {
		theGame = new UndoableGame();
		return theGame;
	}

	@Override
	public Game getGame() {
		return theGame;
	}
}
