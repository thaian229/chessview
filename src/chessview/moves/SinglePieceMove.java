package chessview.moves;

import java.util.*;

import chessview.*;
import chessview.pieces.*;

public class SinglePieceMove implements MultiPieceMove {
    protected Piece piece;
    protected Position oldPosition;
    protected Position newPosition;

    public SinglePieceMove(Piece piece, Position oldPosition, Position newPosition) {
        this.piece = piece;
        this.oldPosition = oldPosition;
        this.newPosition = newPosition;
    }

    public Piece piece() {
        return piece;
    }

    public boolean isWhite() {
        return piece.isWhite();
    }

    public Position oldPosition() {
        return oldPosition;
    }

    public Position newPosition() {
        return newPosition;
    }

    public boolean isValid(Board board) {
        Piece temp = board.pieceAt(oldPosition);
        if (temp != null)
            return temp.isValidMove(oldPosition, newPosition, null, board);
        return false;
    }

    public void apply(Board b) {
        if (b.pieceAt(oldPosition) instanceof Pawn) {
            ((Pawn) b.pieceAt(oldPosition)).setEnPassant(oldPosition, newPosition, b);
        }
        b.move(oldPosition, newPosition);
//
    }

    public String toString() {
        return pieceChar(piece) + oldPosition + "-" + newPosition;
    }

    protected static String pieceChar(Piece p) {
        if (p instanceof Pawn) {
            return "";
        } else if (p instanceof Knight) {
            return "N";
        } else if (p instanceof Bishop) {
            return "B";
        } else if (p instanceof Rook) {
            return "R";
        } else if (p instanceof Queen) {
            return "Q";
        } else {
            return "K";
        }
    }
}
