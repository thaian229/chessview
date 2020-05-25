package chessview.moves;

import chessview.*;
import chessview.pieces.*;

/**
 * This represents an "en passant move" --- http://en.wikipedia.org/wiki/En_passant.
 *
 * @author djp
 */
public class EnPassant extends SinglePieceTake {

    public EnPassant(SinglePieceMove move) {
        super(move.piece(), new Pawn(!move.piece().isWhite()), move.oldPosition(), move.newPosition());
    }

    @Override
    public boolean isValid(Board board) {
        int dir = isWhite() ? 1 : -1;
        int oldRow = oldPosition.row();
        int oldCol = oldPosition.column();
        int newRow = newPosition.row();
        int newCol = newPosition.column();

        Position isTakenPos = new Position(oldRow, newCol);

        Piece s = board.pieceAt(oldPosition);

        Piece t = board.pieceAt(isTakenPos);

        Piece d = board.pieceAt(newPosition);

        if (piece instanceof Pawn && t instanceof Pawn && t.isWhite() != piece.isWhite() && d == null) {
            if ((oldRow + dir) == newRow && (oldCol + 1) == newCol || (oldCol - 1) == newCol
//                    && ((oldRow == 4) && (!piece.isWhite())) || ((oldRow == 5) && (piece.isWhite()))
//                    && (s.isEnPassantable())
            ) {
                return true;
            }
        }
        return false;
    }

    public void apply(Board board) {
        super.apply(board);
        int oldRow = oldPosition.row();
        int newCol = newPosition.column();
        board.setPieceAt(new Position(oldRow, newCol), null);
    }

    public String toString() {
        return super.toString() + "ep";
    }
}
