package chessview.moves;

import chessview.*;
import chessview.pieces.*;

/**
 * <h1>Class EnPassant</h1>
 *
 * This represents an "en_passant move" --- http://en.wikipedia.org/wiki/En_passant.
 */
public class EnPassant extends SinglePieceTake {

    /**
     * Construct EnPassant move
     * @param move - a move
     */
    public EnPassant(SinglePieceMove move) {
        super(move.piece(), new Pawn(!move.piece().isWhite()), move.oldPosition(), move.newPosition());
    }

    /**
     * Check whether this move is valid or not.
     * @param board - game board
     * @return true if valid, otherwise false
     */
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

//        System.out.println(piece + " " + t + " " + ((Pawn) t).isEnPassantable() + "\n");


        if (piece instanceof Pawn && t instanceof Pawn && t.isWhite() != piece.isWhite() && d == null) {
            if ((oldRow + dir) == newRow && ((oldCol + 1) == newCol || (oldCol - 1) == newCol) && ((Pawn) t).isEnPassantable()
            ) {
                return true;
            }
        }
        return false;
    }

    /**
     * Update the board to reflect the board after the EnPassant move is played.
     *
     * @param board - game board
     */
    public void apply(Board board) {
        super.apply(board);
        int oldRow = oldPosition.row();
        int newCol = newPosition.column();
        board.setPieceAt(new Position(oldRow, newCol), null);
    }

    /**
     * This method represents EnPassant move as a string
     * @return string describes an EnPassant move
     */
    public String toString() {
        return super.toString() + "ep";
    }
}
