package chessview.pieces;

import chessview.*;

public class King extends PieceImpl implements Piece {

	public boolean isCastleable = true;
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
	
	public String toString() {
		if(isWhite) {
			return "K";
		} else {
			return "k";
		}
	}
}
