package ps.engine.util;

import java.io.File;
import java.io.InputStream;

public class ResourceLoader {

	private ResourceLoader() {
	}
	/**
	 * @return the file
	 */
	public static File getSavedFile(String file) {
		return new File(ResourceLoader.class.getClassLoader().getResource(file).getPath());
	}

	/**
	 * @return the file
	 */
	public static InputStream getAsInputSteamFile(String file) {
		return ResourceLoader.class.getClassLoader().getResourceAsStream(file);
	}

}
