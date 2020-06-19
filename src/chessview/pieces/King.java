package chessview.pieces;

import chessview.*;

/**
 * <h1>Class King</h1>
 * This class represents the piece King in chess board
 */
public class King extends PieceImpl implements Piece {
	private boolean isCastleable = true;

	/**
	 * Construct King
	 * @param isWhite - check color of piece
	 */
	public King(boolean isWhite) {
		super(isWhite);
	}

	public boolean isCastleable() {
		return isCastleable;
	}

	public boolean isValidMove(Position oldPosition, Position newPosition,
							   Piece isTaken, Board board) {
		int diffCol = Math.max(oldPosition.column(), newPosition.column())
				- Math.min(oldPosition.column(), newPosition.column());
		int diffRow = Math.max(oldPosition.row(), newPosition.row())
				- Math.min(oldPosition.row(), newPosition.row());
		Piece p = board.pieceAt(oldPosition);
		Piece t = board.pieceAt(newPosition);
		if(this.equals(p)
				&& (t == isTaken || (isTaken != null && isTaken.equals(t)))
				&& (diffCol == 1 || diffRow == 1) && diffCol <= 1
				&& diffRow <= 1) {
			this.isCastleable = false;
			return true;
		}
		return false;
	}

	/**
	 * This method represents king piece as a character "K" or "k" depends on color of piece
	 * @return "K" for white king, "k" for black one
	 */
	public String toString() {
		if(isWhite) {
			return "K";
		} else {
			return "k";
		}
	}
}
