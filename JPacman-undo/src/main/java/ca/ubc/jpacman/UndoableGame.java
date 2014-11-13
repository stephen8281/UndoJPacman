package ca.ubc.jpacman;

import java.util.Stack;

import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Food;
import org.jpacman.framework.model.Game;
import org.jpacman.framework.model.IBoardInspector.SpriteType;
import org.jpacman.framework.model.Sprite;
import org.jpacman.framework.model.Tile;

public class UndoableGame extends Game {

	private Stack<UndoStackFrame> undoStack = new Stack<UndoStackFrame>();

	/**
	 * A proxy to record game state when the player moves.
	 */
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
		UndoStackFrame actionStackFrame = undoStack.pop();

		if (actionStackFrame.ateFoodLastMove) {
			Tile newTile = getPlayer().getTile();
			Food eatenFood = new Food();
			eatenFood.occupy(newTile);
			getPointManager().consumePointsOnBoard(getPlayer(), -1 * eatenFood.getPoints());

		}

		for (int i = 0; i < getGhosts().size(); i++) {
			getGhosts().get(i).deoccupy();
			getGhosts().get(i).occupy(actionStackFrame.previousGhostTiles.get(i));

		}
		getPlayer().deoccupy();
		getPlayer().occupy(actionStackFrame.previousPlayerTile);
		getPlayer().setDirection(actionStackFrame.previousPlayerDirection);
		getPlayer().resurrect();

		notifyViewers();

		System.out.println(actionStackFrame);
	}

	/**
	 * Checks if undo is currently possible
	 * 
	 * @return Whether undo is currently possible
	 */
	public boolean undoAvailable() {
		return !undoStack.empty();
	}
}
