package chessview;

/**
 * <h1>Final Class Position</h1>
 *  This class represents position on the board
 *  @author djp
 */
public final class Position {
	private int row; // must be between 1 and 8
	private int col; // must be between 1 and 8

	/**
	 * Construct position
	 * @param row - row
	 * @param col - column
	 */
	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public int row() { 
		return row; 
	}
	
	public int column() { 
		return col; 
	}

	/**
	 * Check 2 position, they are the same if the rows are equal, and the columns are equal
	 * @param o - object o
	 * @return true if equals, otherwise false
	 */
	public boolean equals(Object o) {
		if(o instanceof Position) {
			Position p = (Position) o;
			return row == p.row && col == p.col;
		}
		return false;
	}

	/**
	 * This method determines a hashcode
	 * @return row XOR col
	 */
	public int hashCode() {
		return row ^ col;
	}

	/**
	 * This method represents position as chess Notation
	 * @return a string with column and row (position)
	 */
	public String toString() {		
		return ((char)('a'+(col-1))) + Integer.toString(row);		
	}
}
