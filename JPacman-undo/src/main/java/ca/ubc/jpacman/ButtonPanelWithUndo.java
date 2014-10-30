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

	@Override
	public void initialize() {
		super.initialize();
		undoButton = new JButton("Undo");
		initializeUndoButton();

		addButton(undoButton);
	}

	protected void initializeUndoButton() {
		undoButton.setEnabled(false);
		undoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				undo();
			}
		});
		undoButton.setName("jpacman.undo");
		// undoButton.requestFocusInWindow();
	}

	public ButtonPanel withUndoablePacman(UndoablePacman up) {
		this.undoableInteractor = up;
		return this;
	}

	@Override
	public void update(Observable o, Object arg) {
		super.update(o, arg);
		updateUndoButton();
	}

	public void updateUndoButton() {
		if (undoButton != null)
			undoButton.setEnabled(undoableInteractor.undoAvailable());
	}

	public void undo() {
		super.pause();
		undoableInteractor.undo();
		updateUndoButton();
		// ensure the full window has the focus.
	}

}
