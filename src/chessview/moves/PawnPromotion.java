package chessview.moves;

import chessview.*;
import chessview.pieces.*;

/**
 * <h1>Class PawnPromotion</h1>
 *
 * This class represents Pawn promotion
 */
public class PawnPromotion implements MultiPieceMove {
	private Piece promotion;
	private SinglePieceMove move;

	/**
	 * Construct PawnPromotion
	 * @param move - a SinglePieceMove
	 * @param promotion - piece promotion
	 */
	public PawnPromotion(SinglePieceMove move, Piece promotion) {
		this.promotion = promotion;
		this.move = move;
	}
	
	public boolean isWhite() {
		return move.isWhite();
	}

	/**
	 * Check whether this move is valid or not.
	 * @param board - game board
	 */
	public boolean isValid(Board board) {				
		int row = isWhite() ? 8 : 1;		
		return move.isValid(board)
				&& move.piece() instanceof Pawn
				&& move.newPosition.row() == row;
	}

	/**
	 * Update the board to reflect the board after the move is played.
	 *
	 * @param board - game board
	 */
	public void apply(Board board) {
		move.apply(board);
		board.setPieceAt(move.newPosition(), promotion);
	}

	/**
	 * This method represents Pawn promotion move as a string
	 * @return string describes a pawn promotion move
	 */
	public String toString() {
		return super.toString() + "=" + SinglePieceMove.pieceChar(promotion);
	}
}
