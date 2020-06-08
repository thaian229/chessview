package chessview.moves;

import chessview.Board;
import chessview.Position;
import chessview.pieces.*;

/**
 * <h1>Class SinglePieceMove</h1>
 * This class represents a single piece move
 */

public class SinglePieceMove implements MultiPieceMove {
	protected Piece piece;
	protected Position oldPosition;
	protected Position newPosition;

	/**
	 * Construct a single piece move
	 * @param piece - chess piece
	 * @param oldPosition - position of this piece before move.
	 * @param newPosition - position of this piece after move.
	 */
	public SinglePieceMove(Piece piece, Position oldPosition, Position newPosition) {
		this.piece = piece;
		this.oldPosition = oldPosition;
		this.newPosition = newPosition;
	}

	/**
	 * Get piece
	 * @return piece
 	 */
	public Piece piece() {
		return piece;
	}
	/**
	 * Is this move for white or black?
	 */
	public boolean isWhite() {
		return piece.isWhite();
	}
	/**
	 * Get old position
	 * @return old position
	 */
	public Position oldPosition() {
		return oldPosition;
	}
	
	public Position newPosition() {
		return newPosition;
	}
	/**
	 * Check whether this move is valid or not.
	 * @param board - game board
	 * @return true if valid, otherwise false
	 */
	public boolean isValid(Board board) {
		return piece.isValidMove(oldPosition, newPosition, null, board);
	}
	/**
	 * Update the board to reflect the board after the move is played.
	 *
	 * @param b - game board
	 */
	public void apply(Board b) {
		b.move(oldPosition,newPosition);
	}
	/**
	 * This method represents single piece move as a string
	 * @return string describes single piece move
	 */
	public String toString() {
		return pieceChar(piece) + oldPosition + "-" + newPosition; 
	}

	/**
	 * Assign symbol to each type of piece
	 * @param p - piece
	 * @return Character for each type of piece
	 */
	protected static String pieceChar(Piece p) {
		if(p instanceof Pawn) {
			return "";
		} else if(p instanceof Knight) {
			return "N";
		} else if(p instanceof Bishop) {
			return "B";
		} else if(p instanceof Rook) {
			return "R";
		} else if(p instanceof Queen) {
			return "Q";
		} else {
			return "K";
		}
	}
}
