package ca.ubc.jpacman;

import java.util.ArrayList;
import java.util.List;

import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Ghost;
import org.jpacman.framework.model.Player;
import org.jpacman.framework.model.Tile;

/**
 * The undo stack frame contains all state information needed to return the game to the state before
 * the last player action.
 * 
 * @author Jason Jang
 */

public class UndoStackFrame {
	public Tile player; // The tile the player was on
	public Direction dir; // The player's direction before the move
	public List<Tile> ghosts = new ArrayList<Tile>(); // The tiles each ghost is on
	public boolean ateFood; // Whether the last player action result in a food being eaten.

	public UndoStackFrame(Player player, List<Ghost> ghosts, boolean ateFood) {
		this.player = player.getTile();
		this.dir = player.getDirection();
		for (Ghost g : ghosts) {
			this.ghosts.add(g.getTile());
		}
		this.ateFood = ateFood;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("P[" + player.getX() + "," + player.getY() + "|" + dir + "] ");
		for (Tile g : ghosts)
			sb.append("G[" + g.getX() + "," + g.getY() + "] ");
		return sb.toString();
	}
}
