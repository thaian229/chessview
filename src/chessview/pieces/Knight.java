package chessview.pieces;

import chessview.*;
/**
 * <h1>Class Knight</h1>
 * This class represents the piece Knight in chess board
 */

public class Knight extends PieceImpl implements Piece {
	/**
	 * Construct Knight
	 * @param isWhite - check color of piece
	 */
	public Knight(boolean isWhite) {
		super(isWhite);
	}
	/**
	 * Check whether or not a given move on a given board is valid. For takes,
	 * the piece being taken must be supplied.
	 *
	 * @param oldPosition - position of this piece before move.
	 * @param newPosition - position of this piece after move.
	 * @param isTaken - piece being taken, or null if no piece taken.
	 * @param board - board on which the validity of this move is being checked.
	 * @return true if valid, otherwise false
	 */
	public boolean isValidMove(Position oldPosition, Position newPosition,
			Piece isTaken, Board board) {
		int diffCol = Math.max(oldPosition.column(), newPosition.column())
				- Math.min(oldPosition.column(), newPosition.column());
		int diffRow = Math.max(oldPosition.row(), newPosition.row())
				- Math.min(oldPosition.row(), newPosition.row());
		Piece p = board.pieceAt(oldPosition);
		Piece t = board.pieceAt(newPosition);
		return this.equals(p)
				&& (t == isTaken || (isTaken != null && isTaken.equals(t)))
				&& ((diffCol == 2 && diffRow == 1) || (diffCol == 1 && diffRow == 2));
	}
	/**
	 * This method represents knight piece as a character "N" or "n" depends on color of piece
	 * @return "N" for white knight, "n" for black one
	 */
	public String toString() {
		if(isWhite) {
			return "N";
		} else {
			return "n";
		}
	}
}
