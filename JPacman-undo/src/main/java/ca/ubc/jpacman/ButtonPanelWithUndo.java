package ca.ubc.jpacman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JButton;

import org.jpacman.framework.ui.ButtonPanel;

public class ButtonPanelWithUndo extends ButtonPanel {
	private static final long serialVersionUID = 5078677478811886965L;
	private JButton undoButton;

	private UndoablePacman undoableInteractor;

	/**
	 * Create and add the basic buttons plus the undo button
	 */
	@Override
	public void initialize() {
		super.initialize();
		undoButton = new JButton("Undo");
		initializeUndoButton();

		addButton(undoButton);
	}

	/**
	 * Create the undo button
	 */
	protected void initializeUndoButton() {
		undoButton.setEnabled(false);
		undoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				undo();
			}
		});
		undoButton.setName("jpacman.undo");
	}

	/**
	 * Provide the UndoablePacman to call for undo
	 * 
	 * @param up
	 *            The UndoablePacman instance that include this ButtonPanel
	 * @return Itself for fluency
	 */
	public ButtonPanel withUndoablePacman(UndoablePacman up) {
		this.undoableInteractor = up;
		return this;
	}

	/**
	 * Enable/disable the buttons based on the game state
	 */
	@Override
	public void update(Observable o, Object arg) {
		super.update(o, arg);
		updateUndoButton();
	}

	/**
	 * Enable/disable the undo button based on the game state
	 */
	public void updateUndoButton() {
		if (undoButton != null)
			undoButton.setEnabled(undoableInteractor.undoAvailable());
	}

	/**
	 * Pause the game and undo the last action.
	 */
	public void undo() {
		super.pause();
		undoableInteractor.undo();
		updateUndoButton();
		// ensure the full window has the focus.
	}

}
