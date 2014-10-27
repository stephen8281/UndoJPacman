package ca.ubc.jpacman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.jpacman.framework.ui.ButtonPanel;
import org.jpacman.framework.ui.PacmanInteraction.MatchState;

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
    	//undoButton.requestFocusInWindow();
	}
	
	
	public void undo() {
		super.pause();
		undoableInteractor.undo();
		undoButton.setEnabled(true);
		// ensure the full window has the focus.   	
	}
	
	
	
}
