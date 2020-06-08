package chessview.pieces;

import chessview.*;
/**
 * <h1>Class Rook</h1>
 * This class represents the piece rook in the chess board
 */

public class Rook extends PieceImpl implements Piece {
	public Rook(boolean isWhite) {
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
		Piece p = board.pieceAt(oldPosition);
		Piece t = board.pieceAt(newPosition);
		
		return this.equals(p)
				&& (t == isTaken || (isTaken != null && isTaken.equals(t)))
				&& (board.clearColumnExcept(oldPosition, newPosition, p, t) || board
						.clearRowExcept(oldPosition, newPosition, p, t));
	}
	/**
	 * This method represents rook piece as a character "R" or "r" depends on color of piece
	 * @return "R" for white rook, "r" for black one
	 */
	public String toString() {
		if(isWhite) {
			return "R";
		} else {
			return "r";
		}
	}
}
