package chessview;

import chessview.moves.Move;
import chessview.pieces.*;

/**
 * <h1>Class Board</h1>
 * This class is used to create a chess board
 * @author djp
 */
public class Board {
	private Piece[][] pieces; // this is the underlying data structure for a board.
	
	/**
	 * Construct an initial board.
	 */
	public Board() {
		pieces = new Piece[9][9];
		
		for(int i=1;i<=8;++i) {
			pieces[2][i] = new Pawn(true);
			pieces[7][i] = new Pawn(false);
		}

		// rooks
		pieces[1][1] = new Rook(true);
		pieces[1][8] = new Rook(true);
		pieces[8][1] = new Rook(false);
		pieces[8][8] = new Rook(false);

		// knights
		pieces[1][2] = new Knight(true);
		pieces[1][7] = new Knight(true);
		pieces[8][2] = new Knight(false);
		pieces[8][7] = new Knight(false);

		// bishops
		pieces[1][3] = new Bishop(true);
		pieces[1][6] = new Bishop(true);
		pieces[8][3] = new Bishop(false);
		pieces[8][6] = new Bishop(false);

		// king + queen
		pieces[1][4] = new Queen(true);
		pieces[1][5] = new King(true);
		pieces[8][4] = new Queen(false);
		pieces[8][5] = new King(false);
	}

	/**
	 * Construct a board which is identical to another board.
	 * 
	 * @param board - chess board
	 */
	public Board(Board board) {
		this.pieces = new Piece[9][9];
		for(int row=1;row<=8;++row) {
			for(int col=1;col<=8;++col) {
				this.pieces[row][col] = board.pieces[row][col];
			}	
		}
	}

	/**
	 * Apply a given move to this board, check validity of the move
	 *
	 * @param move - a chess move
	 *
	 * @return  true is successful, otherwise false
	 */
	public boolean apply(Move move) {		
		boolean isWhite = move.isWhite();

		if(move.isValid(this)) {						
			move.apply(this);
			boolean whiteNowInCheck = isInCheck(true);
			boolean blackNowInCheck = isInCheck(false);
			
			if(isWhite && whiteNowInCheck) {							
				// white must move out of check				
				return false;
			} else if(!isWhite && blackNowInCheck) {				
				// black must move out of check				
				return false;
			}
			
			return true;
		} else {			
			return false;			
		}
	}

	/**
	 * Move a piece from one position to another.
	 *
	 * @param oldPosition - position of this piece before move.
	 * @param newPosition - position of this piece after move.
	 */
	public void move(Position oldPosition, Position newPosition) {		
		Piece p = pieces[oldPosition.row()][oldPosition.column()];		
		pieces[newPosition.row()][newPosition.column()] = p;
		pieces[oldPosition.row()][oldPosition.column()] = null;
	}

	/**
	 * Set a piece at input position
	 * @param pos - position of a piece
	 * @param piece - chess piece
	 */
	public void setPieceAt(Position pos, Piece piece) {
		pieces[pos.row()][pos.column()] = piece;
	}

	/**
	 * Get a piece by position on board
	 * @param pos - position of a piece
	 * @return a piece at input position
	 */
	public Piece pieceAt(Position pos) {
		return pieces[pos.row()][pos.column()];
	}

	/**
	 * Visualize the board's state in text
	 *
	 * @return state of chess board.
	 */
	public String toString() {
		String r = "";
		for(int row=8;row!=0;row--) {
			r += row + "|";
			for(int col=1;col<=8;col++) {				
				Piece p = pieces[row][col];
				if(p != null) {					
					r += p + "|";
				} else {
					r += "_|";
				}				
			}
			r += "\n";
		}
		return r + "  a b c d e f g h";
	}

	/**
	 * This method determines whether or not one side is in check.
	 * 
	 * @param isWhite
	 *            --- true means check whether white is in check; otherwise,
	 *            check black.
	 * @return true if one side is in check, otherwise false
	 */
	public boolean isInCheck(boolean isWhite) {
		King king = null; // opposition king
		Position kingPos = null;

		// First, find my king
		outer:
		for (int row = 1; row <= 8; ++row) {
			for (int col = 1; col <= 8; ++col) {
				Position pos = new Position(row, col);
				Piece p = pieceAt(pos);
				if (p instanceof King && p.isWhite() == isWhite) {
					// found him.
					king = (King) p;
					kingPos = pos;
					// The following will break out of the entire loop, not
					// just the innermost loop. This isn't exactly great
					// style, but it is pretty convenient here.
					break outer;
				}
			}
		}

		// Second, check opposition pieces to see whether they can take
		// my king or not.  If one can, we're in check!
		for (int row = 1; row <= 8; ++row) {
			for (int col = 1; col <= 8; ++col) {
				Position pos = new Position(row, col);
				Piece p = pieceAt(pos);
				// If this is an opposition piece, and it can take my king,
				// then we're definitely in check.
				if (p != null && p.isWhite() != isWhite
						&& p.isValidMove(pos, kingPos, king, this)) {
					// p can take opposition king, so we're in check.						
					return true;
				}
			}
		}
		
		// couldn't find any piece in check.
		return false;
	}
	
	/**
	 * The following method checks whether the given diaganol is completely
	 * clear, except for a given set of pieces. Observe that this doesn't
	 * guarantee a given diaganol move is valid, since this method does not
	 * ensure anything about the relative positions of the given pieces.
	 * 
	 * @param startPosition - start of diaganol
	 * @param endPosition - end of diaganol
	 * @param exceptions - the list of pieces allowed on the diaganol
	 * @return true is clear, otherwise false
	 */
	public boolean clearDiaganolExcept(Position startPosition,
			Position endPosition, Piece... exceptions) {			
		int startCol = startPosition.column();
		int endCol = endPosition.column();
		int startRow = startPosition.row();
		int endRow = endPosition.row();		
		int diffCol = Math.max(startCol,endCol) - Math.min(startCol,endCol);
		int diffRow = Math.max(startRow,endRow) - Math.min(startRow,endRow);		
		
		if(diffCol != diffRow || diffCol == 0) {			
			return false;
		}
		
		int row = startRow;
		int col = startCol;
		while(row != endRow && col != endCol) {			
			Piece p = pieces[row][col];			
			if(p != null && !contains(p,exceptions)) {				
				return false;
			}
			col = col <= endCol ? col + 1 : col - 1;
			row = row <= endRow ? row + 1 : row - 1;
		}				
		
		return true;
	}

	/**
	 * The following method checks whether the given column is completely
	 * clear, except for a given set of pieces. Observe that this doesn't
	 * guarantee a given column move is valid, since this method does not
	 * ensure anything about the relative positions of the given pieces.
	 * 
	 * @param startPosition - start of column
	 * @param endPosition - end of column
	 * @param exceptions - the list of pieces allowed on the column
	 * @return true if clear, otherwise false
	 */
	public boolean clearColumnExcept(Position startPosition,
			Position endPosition, Piece... exceptions) {			
		int minCol = Math.min(startPosition.column(), endPosition.column());
		int maxCol = Math.max(startPosition.column(), endPosition.column());
		int minRow = Math.min(startPosition.row(), endPosition.row());
		int maxRow = Math.max(startPosition.row(), endPosition.row());		
		int diffCol = maxCol - minCol;
		int diffRow = maxRow - minRow;
		
		if(diffCol != 0 || diffRow == 0) {
			return false;
		}
		
		int row = minRow;		
		while(row <= maxRow) {	
			Piece p = pieces[row][minCol];			
			if(p != null && !contains(p,exceptions)) {			
				return false;
			}					
			row++; 
		}				
		
		return true;
	}
	
	/**
	 * The following method checks whether the given row is completely
	 * clear, except for a given set of pieces. Observe that this doesn't
	 * guarantee a given row move is valid, since this method does not
	 * ensure anything about the relative positions of the given pieces.
	 * 
	 * @param startPosition - start of row
	 * @param endPosition - end of row
	 * @param exceptions - the list of pieces allowed on the row
	 * @return true if clear, otherwise false
	 */
	public boolean clearRowExcept(Position startPosition,
			Position endPosition, Piece... exceptions) {			
		int minCol = Math.min(startPosition.column(), endPosition.column());
		int maxCol = Math.max(startPosition.column(), endPosition.column());
		int minRow = Math.min(startPosition.row(), endPosition.row());
		int maxRow = Math.max(startPosition.row(), endPosition.row());		
		int diffCol = maxCol - minCol;
		int diffRow = maxRow - minRow;
		
		if(diffRow != 0 || diffCol == 0) {
			return false;
		}
		
		int col = minCol;		
		while(col <= maxCol) {			
			Piece p = pieces[minRow][col];			
			if(p != null && !contains(p,exceptions)) {			
				return false;
			}					
			col++; 
		}
		
		return true;
	}

	public boolean safeRowExcept(Position startPosition,
								  Position endPosition, boolean isWhite, Piece... exceptions) {
		int minCol = Math.min(startPosition.column(), endPosition.column());
		int maxCol = Math.max(startPosition.column(), endPosition.column());
		int minRow = Math.min(startPosition.row(), endPosition.row());
		int maxRow = Math.max(startPosition.row(), endPosition.row());
		int diffCol = maxCol - minCol;
		int diffRow = maxRow - minRow;

		if(diffRow != 0 || diffCol == 0) {
			return false;
		}

		for (int r = 1; r <= 8; ++r) {
			for (int c = 1; c <= 8; ++c) {
				Position pos = new Position(r, c);
				Piece p = pieceAt(pos);
				if (p != null && p.isWhite() != isWhite
						&& p.isValidMove(pos, startPosition, p, this)) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * Check if a piece is in board
	 *
	 * this method is used to help 3 above methods
	 *
	 * @param p1 - a chess piece
	 * @param pieces - exception list
	 * @return true if piece is in list, otherwise false
	 */
	private static boolean contains(Piece p1, Piece... pieces) {		
		for(Piece p2 : pieces) {						
			if(p1 == p2) {									
				return true;
			}
		}									
		
		return false;
	}	
}
