/**
 * 
 */
package ps.engine;

/**
 * 
 *
 */
public enum PrebuildBoard {
	
	LATIN_CROSS("board/latinCross.board"),
	GAME_OVER("board/gameOver.board"),
	PYRAMID("board/Pyramid.board");

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
