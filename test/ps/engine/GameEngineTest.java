/**
 * 
 */
package ps.engine;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

import ps.engine.model.Position;

/**
 * 
 *
 */
public class GameEngineTest {

	private static Logger LOGGER = Logger.getLogger(GameEngineTest.class.getName());

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
		Position removedPegPosition = new Position(4, 3);
		Position finalPosition = new Position(5, 3);
		game.movePeg(initialPosition, finalPosition);

		assertTrue(game.getBoard().getHole(initialPosition).isEnabled());
		assertTrue(game.getBoard().getHole(removedPegPosition).isEnabled());
		assertTrue(game.getBoard().getHole(finalPosition).isEnabled());

		assertFalse(game.getBoard().getHole(initialPosition).hasPeg());
		assertFalse(game.getBoard().getHole(removedPegPosition).hasPeg());
		assertTrue(game.getBoard().getHole(finalPosition).hasPeg());

	}

	/**
	 * Test method for
	 * {@link ps.engine.GameEngine#movePeg(ps.engine.model.Position, ps.engine.model.Position)}.
	 */
	@Test
	public final void testMovePegPyramid() {
		game.newGame(PrebuildBoard.PYRAMID);

		Position initialPosition = new Position(3, 2);
		Position firtRemovedPeg = new Position(2, 2);
		Position middlePosition = new Position(1, 2);
		Position secondRemovedPeg = new Position(1, 3);
		Position finalPosition = new Position(1, 4);

		game.movePeg(initialPosition, middlePosition);
		game.movePeg(middlePosition, finalPosition);

		LOGGER.info("\n" + game.getBoard().toString());

		assertTrue(game.getBoard().getHole(initialPosition).isEnabled());
		assertTrue(game.getBoard().getHole(firtRemovedPeg).isEnabled());
		assertTrue(game.getBoard().getHole(finalPosition).isEnabled());

		assertFalse(game.getBoard().getHole(initialPosition).hasPeg());
		assertFalse(game.getBoard().getHole(firtRemovedPeg).hasPeg());
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
