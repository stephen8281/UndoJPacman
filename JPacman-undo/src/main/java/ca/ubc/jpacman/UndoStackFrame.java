package ca.ubc.jpacman;

import java.util.ArrayList;
import java.util.List;

import org.jpacman.framework.model.Ghost;
import org.jpacman.framework.model.Player;
import org.jpacman.framework.model.Tile;

public class UndoStackFrame {
	public Tile player;
	public List<Tile> ghosts = new ArrayList<Tile>();
	public boolean ateFood;

	public UndoStackFrame(Player player, List<Ghost> ghosts, boolean ateFood) {
		this.player = player.getTile();
		for (Ghost g : ghosts) {
			this.ghosts.add(g.getTile());
		}
		this.ateFood = ateFood;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("P[" + player.getX() + "," + player.getY() + "]");
		return sb.toString();
	}
}
