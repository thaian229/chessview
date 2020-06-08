package chessview.moves;

import chessview.Board;

/**
 * <h1>Class NonCheck</h1>
 * This class represents a noncheck move
 */

public class NonCheck implements Move {
	private MultiPieceMove move;

	/**
	 * Construct NonCheck move
	 * @param move - a move
	 */
	public NonCheck(MultiPieceMove move) {
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
	 */
	public boolean isWhite() {
		return move.isWhite();
	}
	/**
	 * Check whether this move is valid or not.
	 *
	 * @param board - game board
	 */
	public boolean isValid(Board board) {		
		if (move.isValid(board)) {
			// Ok, underlying move is valid; now, we need to check that it
			// actually corresponds to putting the opposition's king into check.

			// First, copy board and apply underlying move.			
			board = new Board(board);
			board.apply(move);			
			
			// Second, check opposition is not in check.
			return board != null && !board.isInCheck(!isWhite());			
		}
		return false;
	}
	/**
	 * Update the board to reflect the board after the move is played.
	 *
	 * @param board - game board
	 */
	public void apply(Board board) {
		move.apply(board);
	}
	/**
	 * This method represents noncheck move as a string
	 * @return string describes a noncheck move
	 */
	public String toString() {
		return move.toString();
	}
}
