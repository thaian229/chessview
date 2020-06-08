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
