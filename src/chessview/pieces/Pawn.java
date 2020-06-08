package chessview.pieces;

import chessview.*;
/**
 * <h1>Class Pawn</h1>
 * This class represents the piece Pawn in chess board
 */

public class Pawn extends PieceImpl implements Piece {
	/**
	 * Construct Pawn
	 * @param isWhite - check color of piece
	 */
	public Pawn(boolean isWhite) {
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
		int dir = isWhite ? 1 : -1;
		int oldRow = oldPosition.row();
		int oldCol = oldPosition.column();
		int newRow = newPosition.row();
		int newCol = newPosition.column();

		Piece p = board.pieceAt(oldPosition);
		Piece t = board.pieceAt(newPosition);

		// this logic is more complex than for other pieces, since there is a
		// difference between a take and non-take move for pawns.
		
		if (isTaken != null) {			
			return this.equals(p) && isTaken.equals(t)
					&& (oldCol == (newCol + 1) || oldCol == (newCol - 1))
					&& (oldRow + dir) == newRow;
		} else if ((oldRow + dir) == newRow && oldCol == newCol) {
			return this.equals(p) && t == null;
		} else if ((oldRow + dir + dir) == newRow && oldCol == newCol) {
			return ((dir == 1 && oldRow == 2) || (dir == -1 && oldRow == 7))
					&& board.pieceAt(new Position(oldRow + dir, oldCol)) == null
					&& t == null && this.equals(p);
		}
		return false;
	}
	/**
	 * This method represents pawn piece as a character "P" or "p" depends on color of piece
	 * @return "P" for white pawn, "p" for black one
	 */
	public String toString() {
		if(isWhite) {
			return "P";
		} else {
			return "p";
		}
	}
}
