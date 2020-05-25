package chessview.moves;

import chessview.*;
import chessview.pieces.*;

/**
 * This represents an "en passant move" --- http://en.wikipedia.org/wiki/En_passant.
 * 
 * @author djp
 * 
 */
public class EnPassant extends SinglePieceTake {
	public EnPassant(SinglePieceMove move) {
		super(move.piece(),new Pawn(!move.piece().isWhite()),move.oldPosition(),move.newPosition());		
	}
	
	public boolean isValid(Board board) {		
		return true;
	}
	
	public void apply(Board board) {
		super.apply(board);
		int dir = piece.isWhite() ? -1 : 1;
		board.move(oldPosition, new Position(newPosition.row() + dir,
				newPosition.column()));
	}
	
	public String toString() {
		return super.toString() + "ep";
	}
}
