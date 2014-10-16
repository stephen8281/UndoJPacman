package ca.ubc.jpacman;

import org.jpacman.framework.ui.MainUI;

public class UndoablePacman extends MainUI {

	private static final long serialVersionUID = 1L;

	public UndoablePacman() {
		super();
		withFactory(new UndoableGameFactory());
	}

	/**
	 * Causes the game to roll back to the state before the last player move
	 */
	public void undo() {
	}
}
