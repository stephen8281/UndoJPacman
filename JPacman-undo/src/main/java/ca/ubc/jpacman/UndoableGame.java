package ca.ubc.jpacman;

import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Game;
import org.jpacman.framework.model.Ghost;

public class UndoableGame extends Game {

	public void undo() {

	}

	/**
	 * @right now it just calls the superclass's movePlayer method
	 */
	@Override
	public void movePlayer(Direction dir) {
		super.movePlayer(dir);
	}

	/**
	 * @right now it just calls the superclass's moveGhost method
	 */
	@Override
	public void moveGhost(Ghost theGhost, Direction dir) {
		super.moveGhost(theGhost, dir);
	}

}
