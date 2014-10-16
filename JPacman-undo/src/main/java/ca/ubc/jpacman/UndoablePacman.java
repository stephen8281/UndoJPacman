package ca.ubc.jpacman;

import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.ui.MainUI;

public class UndoablePacman extends MainUI {

	private static final long serialVersionUID = 1L;

	public UndoablePacman() {
		super();
		withFactory(new UndoableGameFactory());
	}

	/**
	 * Main starting point of the JPacman game.
	 * 
	 * @param args
	 *            Ignored
	 * @throws FactoryException
	 *             If reading game map fails.
	 */
	public static void main(String[] args) throws FactoryException {
		new UndoablePacman().main();
	}

	/**
	 * Causes the game to roll back to the state before the last player move
	 */
	public void undo() {
	}
}
