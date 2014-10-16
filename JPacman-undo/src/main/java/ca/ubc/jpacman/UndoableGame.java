package ca.ubc.jpacman;

import java.util.Stack;

import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Game;
import org.jpacman.framework.model.Player;
import org.jpacman.framework.model.Sprite;

public class UndoableGame extends Game {

	private Stack<UndoStackFrame> undoStack = new Stack<UndoStackFrame>();

	public void movePlayer(Direction dir) {
		undoStack.push(new UndoStackFrame(getPlayer(), getGhosts(), false));
		super.movePlayer(dir);
		System.out.println("mov");
	}

	private void eatFood(Player player, Sprite currentSprite) {
		System.out.println("eat");
	}

	/**
	 * Rolls back the game to the previous state
	 */
	public void undo() {
		if (undoStack.empty())
			return;
	}
}
