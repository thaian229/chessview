package chessview.pieces;

import chessview.*;

public class Pawn extends PieceImpl implements Piece {
	public Pawn(boolean isWhite) {
		super(isWhite);
	}
	
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
	
	public String toString() {
		if(isWhite) {
			return "P";
		} else {
			return "p";
		}
	}
}
