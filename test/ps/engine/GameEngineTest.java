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

import ps.engine.model.Board;
import ps.engine.model.Hole;
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

		Hole initialPegHole = game.getBoard().getHole(initialPosition);
		assertTrue(initialPegHole.isEnabled());
		assertFalse(initialPegHole.hasPeg());

		Hole removedPegHole = game.getBoard().getHole(removedPegPosition);
		assertTrue(removedPegHole.isEnabled());
		assertFalse(removedPegHole.hasPeg());

		Hole finalPegHole = game.getBoard().getHole(finalPosition);
		assertTrue(finalPegHole.isEnabled());
		assertTrue(finalPegHole.hasPeg());

	}

	/**
	 * Test method for
	 * {@link ps.engine.GameEngine#movePeg(ps.engine.model.Position, ps.engine.model.Position)}.
	 */
	@Test
	public final void testMovePegPyramid() {
		game.newGame(PrebuildBoard.PYRAMID);

		Position initialPosition = new Position(3, 2);
		Position firtRemovedPegPosition = new Position(2, 2);
		Position middlePosition = new Position(1, 2);
		Position secondRemovedPegPosition = new Position(1, 3);
		Position finalPosition = new Position(1, 4);

		game.movePeg(initialPosition, middlePosition);
		game.movePeg(middlePosition, finalPosition);

		LOGGER.info("\n" + game.getBoard().toString());

		Hole initialPegHole = game.getBoard().getHole(initialPosition);
		assertTrue(initialPegHole.isEnabled());
		assertFalse(initialPegHole.hasPeg());

		Hole firstRemovedPegHole = game.getBoard().getHole(firtRemovedPegPosition);
		assertTrue(firstRemovedPegHole.isEnabled());
		assertFalse(firstRemovedPegHole.hasPeg());

		Hole middlePegHole = game.getBoard().getHole(middlePosition);
		assertTrue(middlePegHole.isEnabled());
		assertFalse(middlePegHole.hasPeg());

		Hole secondRemovedPegHole = game.getBoard().getHole(secondRemovedPegPosition);
		assertTrue(secondRemovedPegHole.isEnabled());
		assertFalse(secondRemovedPegHole.hasPeg());

		Hole finalPegHole = game.getBoard().getHole(finalPosition);
		assertTrue(finalPegHole.isEnabled());
		assertTrue(finalPegHole.hasPeg());

	}

	/**
	 * Test method for {@link ps.engine.GameEngine#getBoard()}.
	 */
	@Test
	public final void testGetBoard() {
		Board board = game.getBoard();
		assertNotNull(board);
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
		game.newGame(PrebuildBoard.PYRAMID);

		Position initialPosition = new Position(3, 2);
		Position firtRemovedPegPosition = new Position(2, 2);
		Position middlePosition = new Position(1, 2);
		Position secondRemovedPegPosition = new Position(1, 3);
		Position finalPosition = new Position(1, 4);

		game.movePeg(initialPosition, middlePosition);
		game.movePeg(middlePosition, finalPosition);

		LOGGER.info("\n" + game.getBoard().toString());

		game.undoMove();

		Hole initialPegHole = game.getBoard().getHole(initialPosition);
		assertTrue(initialPegHole.hasPeg());

		Hole firstRemovedPegHole = game.getBoard().getHole(firtRemovedPegPosition);
		assertTrue(firstRemovedPegHole.hasPeg());

		Hole middlePegHole = game.getBoard().getHole(middlePosition);
		assertFalse(middlePegHole.hasPeg());

		Hole secondRemovedPegHole = game.getBoard().getHole(secondRemovedPegPosition);
		assertTrue(secondRemovedPegHole.hasPeg());

		Hole finalPegHole = game.getBoard().getHole(finalPosition);
		assertFalse(finalPegHole.hasPeg());

		LOGGER.info("\n" + game.getBoard().toString());

	}

	/**
	 * Test method for {@link ps.engine.GameEngine#moveHistory()}.
	 */
	@Test
	public final void testMoveHistory() {
		fail("Not yet implemented"); // TODO
	}

}
