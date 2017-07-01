/**
 * 
 */
package ps.engine;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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
		fail("Not yet implemented"); // TODO
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
