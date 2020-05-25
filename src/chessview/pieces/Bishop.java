package chessview.pieces;

import chessview.*;

public class Bishop extends PieceImpl implements Piece {
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
	
	public String toString() {
		if(isWhite) {
			return "B";
		} else {
			return "b";
		}
	}
}
