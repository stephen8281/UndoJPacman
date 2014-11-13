package ca.ubc.jpacman;

import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.ui.ButtonPanel;
import org.jpacman.framework.ui.MainUI;
import org.jpacman.framework.ui.PacmanInteraction;

public class UndoablePacman extends MainUI {

	private static final long serialVersionUID = 1L;
	private ButtonPanelWithUndo buttonPanelWithUndo;

	/**
	 * Creates a new UI with undo functionality for the default board
	 */
	public UndoablePacman() {
		super();
		withFactory(new UndoableGameFactory());
	}

	/**
	 * Create a panel containing the start/stop/undo buttons.
	 * 
	 * @param pi
	 *            Interactor capable of performing requested actions.
	 * @return The new panel with buttons.
	 */
	@Override
	protected ButtonPanel createButtonPanel(PacmanInteraction pi) {
		assert (pi != null);
		if (buttonPanelWithUndo == null) {
			buttonPanelWithUndo = new ButtonPanelWithUndo();
		}
		withButtonPanel(buttonPanelWithUndo);
		return buttonPanelWithUndo.withUndoablePacman(this).withParent(this).withInteractor(pi);
	}

	/**
	 * Top level method creating the game, and starting up the interactions. Attach new buttonPanel
	 * to allow it to observe game and update undo button accordingly
	 * 
	 * @throws FactoryException
	 *             If creating the game fails.
	 */
	public void main() throws FactoryException {
		super.main();
		((UndoableGame) getGame()).attach(buttonPanelWithUndo);
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
		((UndoableGame) getGame()).undo();
	}

	/**
	 * Checks whether undo is possible
	 * 
	 * @return Whether undo is possible
	 */
	public boolean undoAvailable() {
		return ((UndoableGame) getGame()).undoAvailable();
	}
}
