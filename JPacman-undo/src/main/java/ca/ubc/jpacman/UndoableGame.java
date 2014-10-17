package ca.ubc.jpacman;

import java.util.Stack;

import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Game;
import org.jpacman.framework.model.IBoardInspector.SpriteType;
import org.jpacman.framework.model.Sprite;
import org.jpacman.framework.model.Tile;

public class UndoableGame extends Game {

	private Stack<UndoStackFrame> undoStack = new Stack<UndoStackFrame>();

	public void movePlayer(Direction dir) {
		Tile target = getBoard().tileAtDirection(getPlayer().getTile(), dir);
		Sprite currentOccupier = target.topSprite();
		if ((currentOccupier == null || currentOccupier.getSpriteType() != SpriteType.WALL)
		        && getPlayer().isAlive()) {
			undoStack.push(new UndoStackFrame(getPlayer(), getGhosts(), currentOccupier != null
			        && currentOccupier.getSpriteType() == SpriteType.FOOD));
			super.movePlayer(dir);
		}
	}

	/**
	 * Rolls back the game to the previous state
	 */
	public void undo() {
		if (undoStack.empty())
			return;

		UndoStackFrame sf = undoStack.pop();

		getPlayer().deoccupy();
		getPlayer().occupy(sf.player);

		System.out.println(sf);
	}
}
