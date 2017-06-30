/**
 * 
 */
package ps.engine;

/**
 * 
 *
 */
public enum PrebuildBoard {
	
	LATIN_CROSS("latinCross.board");

	private String file;

	private PrebuildBoard(String file) {
		this.file = file;
	}

	/**
	 * @return the file
	 */
	public String getFile() {
		return file;
	}

}
