package chessview.pieces;

import chessview.*;

/**
 * <h1>Class Queen</h1>
 * This class represents the piece Queen in the chess board
 */
public class Queen extends PieceImpl implements Piece {
	/**
	 * Construct Queen
	 * @param isWhite - check color of piece
	 */
	public Queen(boolean isWhite) {
		super(isWhite);
	}

	public boolean isValidMove(Position oldPosition, Position newPosition,
			Piece isTaken, Board board) {
		Piece p = board.pieceAt(oldPosition);
		Piece t = board.pieceAt(newPosition);
		return this.equals(p)
				&& (t == isTaken || (isTaken != null && isTaken.equals(t)))
				&& (board.clearDiaganolExcept(oldPosition, newPosition, p, t)
						|| board.clearColumnExcept(oldPosition, newPosition, p,
								t) || board.clearRowExcept(oldPosition,
						newPosition, p, t));
	}

	/**
	 * This method represents queen piece as a character "Q" or "q" depends on color of piece
	 * @return "Q" for white queen, "q" for black one
	 */
	public String toString() {
		if(isWhite) {
			return "Q";
		} else {
			return "q";
		}
	}
}
