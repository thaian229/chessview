package chessview.pieces;

/**
 * <h1>Abstract class PieceImpl</h1>
 */
public abstract class PieceImpl {
	protected boolean isWhite;

	/**
	 * Construct color of a piece
	 * @param isWhite - check color of piece
	 */
	public PieceImpl(boolean isWhite) {		
		this.isWhite = isWhite;
	}
	/**
	 * Determine whether this piece is white or black.
	 * @return true if white, otherwise false
	 */
	public boolean isWhite() {
		return isWhite;
	}
	/**
	 * Check 2 piece, they are the same if their class and color are the same
	 * @param o - object o
	 * @return true if equals, otherwise false
	 */
	public boolean equals(Object o) {
		if (o instanceof PieceImpl) {
			PieceImpl p = (PieceImpl) o;
			return o.getClass() == getClass() && isWhite == p.isWhite;
		}
		return false;
	}		
}
