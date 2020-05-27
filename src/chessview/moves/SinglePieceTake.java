package chessview.moves;

import chessview.*;
import chessview.pieces.Piece;

public class SinglePieceTake extends SinglePieceMove {
	protected Piece isTaken;
	
	public SinglePieceTake(Piece piece, Piece isTaken, Position oldPosition, Position newPosition) {
		super(piece,oldPosition,newPosition);
		this.isTaken = isTaken;
	}
	
	public boolean isValid(Board board) {
		Piece temp = board.pieceAt(oldPosition);
		if(temp!=null)
		return temp.isValidMove(oldPosition, newPosition, isTaken, board);
		return false;
	}
	
	public String toString() {
		return pieceChar(piece) + oldPosition + "x" + pieceChar(isTaken) + newPosition; 
	}	
}
