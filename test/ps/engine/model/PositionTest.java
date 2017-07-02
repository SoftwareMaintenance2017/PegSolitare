/**
 * 
 */
package ps.engine.model;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

/**
 * @author juan
 *
 */
public class PositionTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link ps.engine.model.Position#hashCode()}.
	 */
	@Test
	public final void testHashCode() {
		Position position = new Position(121, 0);
		assertNotNull(position.hashCode());

		Position other = new Position(121, 0);
		assertEquals(position.hashCode(), other.hashCode());
	}

	/**
	 * Test method for {@link ps.engine.model.Position#Position(int, int)}.
	 */
	@Test
	public final void testPosition() {
		Position position = new Position(0, 0);
		assertNotNull(position);
	}

	/**
	 * Test method for {@link ps.engine.model.Position#getColumn()}.
	 */
	@Test
	public final void testGetColumn() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link ps.engine.model.Position#getRow()}.
	 */
	@Test
	public final void testGetRow() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link ps.engine.model.Position#toString()}.
	 */
	@Test
	public final void testToString() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link ps.engine.model.Position#equals(java.lang.Object)}.
	 */
	@Test
	public final void testEqualsObject() {
		Position position = new Position(121, 0);
		assertEquals(position, position);
		assertFalse(position.equals(null));
		assertFalse(position.equals(new Vector<>()));

		Position same = new Position(position.getRow(), position.getColumn());
		assertEquals(position, same);

		Position other = new Position(position.getColumn(), position.getRow());
		assertFalse(position.equals(other));
	}

}
