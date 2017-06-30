package ps.engine.util;

import java.io.File;

public class ResourceLoader {

	private ResourceLoader() {
	}
	/**
	 * @return the file
	 */
	public static File getSavedFile(String file) {
		return new File(ResourceLoader.class.getClassLoader().getResource(file).getFile());
	}

}
