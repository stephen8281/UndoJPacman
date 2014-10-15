package org.jpacman.test.framework.accept;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
		Tile prevTile = getPlayer().getTile();
		getEngine().up();
		// when
		((UndoablePacman) getUI()).undo();
		// then
		assertEquals(prevTile, getPlayer().getTile());
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
}
