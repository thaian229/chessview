package chessview.moves;

import chessview.*;
import chessview.pieces.Piece;

/**
 * <h1>Class SinglePieceTake</h1>
 * This class represents single piece take
 */
public class SinglePieceTake extends SinglePieceMove {
	protected Piece isTaken;

	/**
	 * Construct SinglePieceTake
	 * @param piece - piece
	 * @param isTaken - check isTaken or not
	 * @param oldPosition - position of this piece before move.
	 * @param newPosition - position of this piece after move.
	 */
	public SinglePieceTake(Piece piece, Piece isTaken, Position oldPosition, Position newPosition) {
		super(piece,oldPosition,newPosition);
		this.isTaken = isTaken;
	}

	/**
	 * Check whether this move is valid or not.
	 *
	 * @param board - game board
	 */
	public boolean isValid(Board board) {
		Piece temp = board.pieceAt(oldPosition);
		if(temp!=null)
		return temp.isValidMove(oldPosition, newPosition, isTaken, board);
		return false;
	}

	/**
	 * This method represents single piece take as a string
	 * @return string describes single piece take
	 */
	public String toString() {
		return pieceChar(piece) + oldPosition + "x" + pieceChar(isTaken) + newPosition; 
	}	
}
