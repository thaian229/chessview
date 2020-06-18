package chessview.moves;

import chessview.*;
import chessview.pieces.*;

/**
 * <h1>Class Castling</h1>
 *
 * This class implements castling move
 */
public class Castling implements MultiPieceMove {
    private boolean isWhite;
    private boolean kingSide;

    /**
     * Construct castling
     * @param isWhite -  check color of piece
     * @param kingSide - castling side (king or queen side)
     */
    public Castling(boolean isWhite, boolean kingSide) {
        this.isWhite = isWhite;
        this.kingSide = kingSide;
    }

    public boolean isWhite() {
        return isWhite;
    }

    /**
     * Update the board to reflect the board after the castling move is played.
     * @param board - board game
     */
    public void apply(Board board) {
        int row = isWhite ? 1 : 8;
        int rcol = kingSide ? 8 : 1;
        int rncol = kingSide ? 6 : 4;
        int kncol = kingSide ? 7 : 3;

        Position kpos = new Position(row, 5);
        Position knpos = new Position(row, kncol);
        Position rpos = new Position(row, rcol);
        Position rnpos = new Position(row, rncol);

        board.move(kpos, knpos);
        board.move(rpos, rnpos);
    }

    /**
     * Check whether this move is valid or not.
     * @param board - game board
     * @return true if valid, otherwise false
     */
    public boolean isValid(Board board) {
        int row = isWhite ? 1 : 8;
        int col = kingSide ? 8 : 1;
        Position kpos = new Position(row, 5);
        Position rpos = new Position(row, col);
        Piece king = board.pieceAt(kpos);
        Piece rook = board.pieceAt(rpos);
        if (king instanceof King && rook instanceof Rook)
            return king.equals(new King(isWhite))
                    && rook.equals(new Rook(isWhite))
                    && board.clearRowExcept(kpos, rpos, king, rook)
                    && ((King) king).isCastleable()
                    && ((Rook) rook).isCastleable()
                    && board.safeRowExcept(kpos, rpos, isWhite, king, rook);
        else return false;
    }

    /**
     * This method returns move in long algebraic notation
     * @return "0-0" for castling in king side, "0-0-0" in queen side
     */
    public String toString() {
        if (kingSide) {
            return "O-O";
        } else {
            return "O-O-O";
        }
    }
}
