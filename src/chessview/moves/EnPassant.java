package chessview.moves;

import chessview.*;
import chessview.pieces.*;

/**
 * <h1>Class EnPassant</h1>
 * This represents an "en_passant move" --- http://en.wikipedia.org/wiki/En_passant.
 * 
 * @author djp
 * 
 */
public class EnPassant extends SinglePieceTake {
	/**
	 * Construct EnPassant move
	 * @param move - a move
	 */
	public EnPassant(SinglePieceMove move) {
		super(move.piece(),new Pawn(!move.piece().isWhite()),move.oldPosition(),move.newPosition());		
	}

	/**
	 * Check whether this move is valid or not.
	 * @param board - game board
	 * @return true if valid, otherwise false
	 */
	public boolean isValid(Board board) {		
		return true;
	}
	/**
	 * Update the board to reflect the board after the EnPassant move is played.
	 *
	 * @param board - game board
	 */
	public void apply(Board board) {
		super.apply(board);
		int dir = piece.isWhite() ? -1 : 1;
		board.move(oldPosition, new Position(newPosition.row() + dir,
				newPosition.column()));
	}
	/**
	 * This method represents EnPassant move as a string
	 * @return string describes an EnPassant move
	 */
	public String toString() {
		return super.toString() + "ep";
	}
}
