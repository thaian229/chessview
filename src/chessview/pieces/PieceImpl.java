package chessview.pieces;

import java.util.Arrays;

import chessview.*;


public abstract class PieceImpl {
    protected boolean isWhite;
    private boolean EnPassantable = false;

    public PieceImpl(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setEnPassantable(boolean enPassantable) {
        EnPassantable = enPassantable;
    }

    public boolean isEnPassantable() {
        return EnPassantable;
    }

    public boolean equals(Object o) {
        if (o instanceof PieceImpl) {
            PieceImpl p = (PieceImpl) o;
            return o.getClass() == getClass() && isWhite == p.isWhite;
        }
        return false;
    }
}
