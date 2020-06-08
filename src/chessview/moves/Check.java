package chessview.moves;

import chessview.Board;

/**
 * <h1>Class Check</h1>
 * This represents a "check move". Note that, a check move can only be made up
 * from an underlying simple move; that is, we can't check a check move.
 * 
 * @author djp
 * 
 */
public class Check implements Move {
	private MultiPieceMove move;

	/**
	 * Construct check move
	 * @param move - a MultiPieceMove
	 */
	public Check(MultiPieceMove move) {
		this.move = move;		
	}

	/**
	 * Get move
	 * @return move
	 */
	public MultiPieceMove move() {
		return move;
	}

	/**
	 * Is this move for white or black?
	 * @return true if white, otherwise false
	 */
	public boolean isWhite() {
		return move.isWhite();
	}

	/**
	 * Check whether this move is valid or not.
	 * @param board - game board
	 * @return true if valid, otherwise false
	 */
	public boolean isValid(Board board) {		
		if (move.isValid(board)) {
			// Ok, underlying move is valid; now, we need to check that it
			// actually corresponds to putting the opposition's king into check.

			// First, copy board and apply underlying move.
			board = new Board(board);
			board.apply(move);
						
			// Second, check opposition is now in check.
			return board.isInCheck(!isWhite());
		}
		return false;
	}

	/**
	 * Update the board to reflect the board after the check move is played.
	 * @param board - board game
	 */
	public void apply(Board board) {
		move.apply(board);
	}

	/**
	 * This method represents check move as a string
	 * @return string describes a check move
	 */
	public String toString() {
		return move.toString() + "+";
	}
}
