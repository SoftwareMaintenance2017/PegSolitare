/**
 * 
 */
package ps.engine;

/**
 * 
 *
 */
public enum PrebuildBoard {
	
	LATIN_CROSS("latinCross.board"),
	GAME_OVER("gameOver.board"),
	PYRAMID("Pyramid.board");

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
