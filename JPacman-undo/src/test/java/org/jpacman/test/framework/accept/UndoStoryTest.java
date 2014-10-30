package org.jpacman.test.framework.accept;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Ghost;
import org.jpacman.framework.model.IBoardInspector.SpriteType;
import org.jpacman.framework.model.Tile;
import org.jpacman.framework.ui.MainUI;
import org.junit.Test;

import ca.ubc.jpacman.UndoablePacman;

public class UndoStoryTest extends MovePlayerStoryTest {
	public MainUI makeUI() {
		return (MainUI) new UndoablePacman();
	}

	@Test
	public void test_undo_1_move() {
		// given
		getEngine().start();
		Direction prevDir = getPlayer().getDirection();
		Tile prevTile = getPlayer().getTile();
		getEngine().up();
		// when
		((UndoablePacman) getUI()).undo();
		// then
		assertEquals(prevTile, getPlayer().getTile());
		assertEquals(prevDir, getPlayer().getDirection());
	}

	@Test
	public void test_undo_2_food() {
		// given
		getEngine().start();
		Tile prevTile = getPlayer().getTile();
		getEngine().left();
		Tile foodTile = getPlayer().getTile();
		// when
		((UndoablePacman) getUI()).undo();
		// then
		assertEquals(0, getPlayer().getPoints());
		assertEquals(prevTile, getPlayer().getTile());
		assertEquals(SpriteType.FOOD, foodTile.topSprite().getSpriteType());
	}

	@Test
	public void test_undo_3_died() {
		// given
		getEngine().start();
		Tile prevTile = getPlayer().getTile();
		getEngine().right();
		Tile ghostTile = getPlayer().getTile();
		// when
		((UndoablePacman) getUI()).undo();
		// then
		assertEquals(0, getPlayer().getPoints());
		assertTrue(getPlayer().isAlive());
		assertEquals(prevTile, getPlayer().getTile());
		assertEquals(SpriteType.GHOST, ghostTile.topSprite().getSpriteType());
	}

	@Test
	public void test_undo_4_won() {
		// given
		getEngine().start();
		getEngine().left(); // eat first food
		getEngine().right(); // go back
		getEngine().up(); // move next to final food
		Tile prevTile = getPlayer().getTile();
		getEngine().right(); // eat final food
		Tile foodTile = getPlayer().getTile();
		// when
		((UndoablePacman) getUI()).undo();
		// then
		assertEquals(prevTile, getPlayer().getTile());
		// game.won() calls allEaten() so we don't test it.
		assertFalse(getUI().getGame().getPointManager().allEaten());
		assertEquals(SpriteType.FOOD, foodTile.topSprite().getSpriteType());
	}

	@Test
	public void test_undo_5_wall() {
		// given
		getEngine().start();
		Tile prevTile = getPlayer().getTile();
		getEngine().up();
		getEngine().left(); // move into wall
		// when
		((UndoablePacman) getUI()).undo();
		// then
		assertEquals(prevTile, getPlayer().getTile());
	}

	@Test
	public void test_undo_6_tunnel() {
		// given
		getEngine().start();
		getEngine().up();
		getEngine().right();
		Tile prevTile = getPlayer().getTile();
		getEngine().up(); // through tunnel
		// when
		((UndoablePacman) getUI()).undo();
		// then
		assertEquals(prevTile, getPlayer().getTile());
	}

	@Test
	public void test_undo_7_ghosts() {
		// given
		getEngine().start();
		Ghost g = theGhost();
		Tile prevTile = g.getTile();
		getEngine().up();
		getUI().getGame().moveGhost(g, Direction.DOWN);
		Tile currTile = g.getTile();
		assertFalse(prevTile == currTile);
		// when
		((UndoablePacman) getUI()).undo();
		// then
		assertEquals(prevTile, g.getTile());
	}
}
