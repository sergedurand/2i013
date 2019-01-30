package terrain;

public class TerrainException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TerrainException(String message) {
		super("Problème terrain : "+message);
	}
}
