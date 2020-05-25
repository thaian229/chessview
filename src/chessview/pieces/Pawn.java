package chessview.pieces;

import chessview.*;

public class Pawn extends PieceImpl implements Piece {

    public Pawn(boolean isWhite) {
        super(isWhite);
    }

    public boolean isValidMove(Position oldPosition, Position newPosition,
                               Piece isTaken, Board board) {
        System.out.println("Color\n" + isWhite() + "\nstatus\n" + isEnPassantable());

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
            if (this.equals(p) && isTaken.equals(t)
                    && (oldCol == (newCol + 1) || oldCol == (newCol - 1))
                    && (oldRow + dir) == newRow) {
                super.setEnPassantable(false);
//                System.out.println("disabled\n" + isEnPassantable());
                return true;
            }

        } else if ((oldRow + dir) == newRow && oldCol == newCol) {
            if (this.equals(p) && t == null) {
                super.setEnPassantable(false);
//                System.out.println("disabled\n" + isEnPassantable());
                return true;
            }

        } else if ((oldRow + dir + dir) == newRow && oldCol == newCol) {
            if (((dir == 1 && oldRow == 2) || (dir == -1 && oldRow == 7))
                    && board.pieceAt(new Position(oldRow + dir, oldCol)) == null
                    && t == null && this.equals(p)) {
                super.setEnPassantable(true);
//                System.out.println("enabled\n" + isEnPassantable());
                return true;
            }
        }
        return false;
    }

    public String toString() {
        if (isWhite) {
            return "P";
        } else {
            return "p";
        }
    }
}
