/**
 * 
 */
package ps.engine;

import static org.junit.Assert.*;

import java.io.File;
import java.util.logging.Logger;

import org.junit.Test;

import ps.engine.util.ResourceLoader;

/**
 * 
 *
 */
public class PrebuildBoardTest {

	private static final Logger LOGGER = Logger.getLogger(PrebuildBoardTest.class.getName());

	/**
	 * Test method for {@link ps.engine.PrebuildBoard#getFile()}.
	 */
	@Test
	public final void testGetFile() {
		for (PrebuildBoard savedBoard : PrebuildBoard.values()) {
			File boardFile = ResourceLoader.getSavedFile(savedBoard.getFile());
			LOGGER.info(boardFile.getAbsolutePath());
			assertTrue(boardFile.exists());
		}

	}

}
