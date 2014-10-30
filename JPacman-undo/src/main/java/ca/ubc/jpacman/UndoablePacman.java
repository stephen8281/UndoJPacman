package ca.ubc.jpacman;

import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.ui.ButtonPanel;
import org.jpacman.framework.ui.MainUI;
import org.jpacman.framework.ui.PacmanInteraction;

public class UndoablePacman extends MainUI {

	private static final long serialVersionUID = 1L;
	private ButtonPanelWithUndo buttonPanelWithUndo;

	public UndoablePacman() {
		super();
		withFactory(new UndoableGameFactory());
	}

	protected ButtonPanel createButtonPanel(PacmanInteraction pi) {
		assert (pi != null);
		if (buttonPanelWithUndo == null) {
			buttonPanelWithUndo = new ButtonPanelWithUndo();
		}
		withButtonPanel(buttonPanelWithUndo);
		return buttonPanelWithUndo.withUndoablePacman(this).withParent(this).withInteractor(pi);
	}

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

	public boolean undoAvailable() {
		return ((UndoableGame) getGame()).undoAvailable();
	}
}
