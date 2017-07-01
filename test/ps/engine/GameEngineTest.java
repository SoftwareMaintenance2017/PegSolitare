/**
 * 
 */
package ps.engine;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import ps.engine.model.Position;

/**
 * 
 *
 */
public class GameEngineTest {

	private GameEngine game;

	@Before
	public void initialize() {
		game = new GameEngine();
	}

	/**
	 * Test method for {@link ps.engine.GameEngine#GameEngine()}.
	 */
	@Test
	public final void testGameEngine() {
		GameEngine game = new GameEngine();
		assertNotNull(game);
		assertNotNull(game.getBoard());
	}

	/**
	 * Test method for {@link ps.engine.GameEngine#newGame()}.
	 */
	@Test
	public final void testNewGame() {
		game.newGame();
		assertNotNull(game.getBoard());
	}

	/**
	 * Test method for {@link ps.engine.GameEngine#movePeg(ps.engine.model.Position, ps.engine.model.Position)}.
	 */
	@Test
	public final void testMovePeg() {
		game.newGame(PrebuildBoard.LATIN_CROSS);

		Position initialPosition = new Position(3, 3);
		Position middlePosition = new Position(4, 3);
		Position finalPosition = new Position(5, 3);
		game.movePeg(initialPosition, finalPosition);

		assertTrue(game.getBoard().getHole(initialPosition).isEnabled());
		assertTrue(game.getBoard().getHole(middlePosition).isEnabled());
		assertTrue(game.getBoard().getHole(finalPosition).isEnabled());

		assertFalse(game.getBoard().getHole(initialPosition).hasPeg());
		assertFalse(game.getBoard().getHole(middlePosition).hasPeg());
		assertTrue(game.getBoard().getHole(finalPosition).hasPeg());

	}

	/**
	 * Test method for {@link ps.engine.GameEngine#getBoard()}.
	 */
	@Test
	public final void testGetBoard() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link ps.engine.GameEngine#isOver()}.
	 */
	@Test
	public final void testIsOver() {
		assertFalse(game.isOver());

		game.newGame(PrebuildBoard.GAME_OVER);
		assertTrue(game.isOver());
	}

	/**
	 * Test method for {@link ps.engine.GameEngine#undoMove()}.
	 */
	@Test
	public final void testUndoMove() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link ps.engine.GameEngine#moveHistory()}.
	 */
	@Test
	public final void testMoveHistory() {
		fail("Not yet implemented"); // TODO
	}

}
