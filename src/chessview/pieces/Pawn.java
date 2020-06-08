package chessview.pieces;

import chessview.*;

/**
 * <h1>Class Pawn</h1>
 * This class represents the piece Pawn in chess board
 */
public class Pawn extends PieceImpl implements Piece {

    private boolean isEnPassantable = false;

    /**
     * Construct Pawn
     * @param isWhite - check color of piece
     */
    public Pawn(boolean isWhite) {
        super(isWhite);
    }

    public boolean isEnPassantable() {
        return isEnPassantable;
    }

    public boolean isValidMove(Position oldPosition, Position newPosition,
                               Piece isTaken, Board board) {
//        System.out.println("Color\n" + isWhite() + "\nstatus\n" + isEnPassantable());
//        System.out.println("oldRow: "+ oldPosition.row() + " oldCol: " + oldPosition.column() + " " + isEnPassantable + "\n");


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
                this.isEnPassantable = false;
                return true;
            }

        } else if ((oldRow + dir) == newRow && oldCol == newCol) {
            if (this.equals(p) && t == null) {
                this.isEnPassantable = false;
                return true;
            }

        } else if ((oldRow + dir + dir) == newRow && oldCol == newCol) {
            if (((dir == 1 && oldRow == 2) || (dir == -1 && oldRow == 7))
                    && board.pieceAt(new Position(oldRow + dir, oldCol)) == null
                    && t == null && this.equals(p)) {
//			    System.out.println("\n" + isEnPassantable + "\n");
                this.isEnPassantable = true;
                return true;
            }
        }
        return false;
    }

//    public void setEnPassant(Position oldPosition, Position newPosition,
//                             Board board) {
//        System.out.println("Color\n" + isWhite() + "\nstatus\n" + isEnPassantable());
//        System.out.println("oldRow: " + oldPosition.row() + " oldCol: " + oldPosition.column() + " " + "newRow: " + newPosition.row() + " newCol: " + newPosition.column() + " " + isEnPassantable + "\n");


//        int dir = isWhite ? 1 : -1;
//        int oldRow = oldPosition.row();
//        int oldCol = oldPosition.column();
//        int newRow = newPosition.row();
//        int newCol = newPosition.column();
//
//        Piece p = board.pieceAt(oldPosition);
//        Piece t = board.pieceAt(newPosition);
//
//        // this logic is more complex than for other pieces, since there is a
//        // difference between a take and non-take move for pawns.
//        if ((oldRow + dir) == newRow && oldCol == newCol) {
//            if (this.equals(p) && t == null) {
//                this.isEnPassantable = false;
//            }
//        } else if ((oldRow + dir + dir) == newRow && oldCol == newCol) {
//            if (((dir == 1 && oldRow == 2) || (dir == -1 && oldRow == 7))
//                    && board.pieceAt(new Position(oldRow + dir, oldCol)) == null
//                    && t == null && this.equals(p)) {
//                this.isEnPassantable = true;
//            }
//        }
//    }

    /**
     * This method represents pawn piece as a character "P" or "p" depends on color of piece
     * @return "P" for white pawn, "p" for black one
     */
    public String toString() {
        if (isWhite) {
            return "P";
        } else {
            return "p";
        }
    }
}
