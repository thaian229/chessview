package chessview.pieces;

import chessview.*;

/**
 * <h1>Class Rook</h1>
 * This class represents the piece rook in the chess board
 */
public class Rook extends PieceImpl implements Piece {
	private boolean isCastleable = true;

	public Rook(boolean isWhite) {
		super(isWhite);
	}

	public boolean isCastleable() {
		return isCastleable;
	}

	public boolean isValidMove(Position oldPosition, Position newPosition,
			Piece isTaken, Board board) {
		Piece p = board.pieceAt(oldPosition);
		Piece t = board.pieceAt(newPosition);

		if (this.equals(p)
				&& (t == isTaken || (isTaken != null && isTaken.equals(t)))
				&& (board.clearColumnExcept(oldPosition, newPosition, p, t) || board
				.clearRowExcept(oldPosition, newPosition, p, t))) {
			this.isCastleable = false;
			return true;
		}
		return false;
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
