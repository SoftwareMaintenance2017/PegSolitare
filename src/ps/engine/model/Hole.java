package ps.engine.model;

public class Hole {
	private boolean enabled;
	private boolean hasPeg;
	
	/**
	 * @param enabled
	 * @param hasPeg
	 */
	public Hole(boolean enabled, boolean hasPeg) {
		super();
		this.enabled = enabled;
		this.hasPeg = hasPeg;
	}

	public boolean isEnabled() {
		return enabled;
	}
	
	/**
	 * @param enabled
	 *            the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean hasPeg() {
		return hasPeg;
	}
	
	public void setHasPeg(boolean hasPeg) {
		this.hasPeg = hasPeg;
	}
}
