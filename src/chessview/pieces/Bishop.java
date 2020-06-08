package chessview.pieces;

import chessview.*;

/**
 * <h1>Class Bishop</h1>
 * This class represents the piece Bishop in chess board
 */

public class Bishop extends PieceImpl implements Piece {
	/**
	 * Construct Bishop
	 * @param isWhite - check color of piece
	 */
	public Bishop(boolean isWhite) {
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
				&& board.clearDiaganolExcept(oldPosition, newPosition, p, t);
	}
	/**
	 * This method represents bishop piece as a character "B" or "b" depends on color of piece
	 * @return "B" for white bishop, "b" for black one
	 */
	public String toString() {
		if(isWhite) {
			return "B";
		} else {
			return "b";
		}
	}
}
