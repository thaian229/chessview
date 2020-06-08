package chessview;

import chessview.moves.Move;

/**
 * <h1>Class Round</h1>
 * A round consists of a move by white, and will normally also have a move by
 * black. The latter may not happen in the case that White wins the game.
 * 
 * @author djp
 * 
 */
public class Round {
	private Move white;
	private Move black;
	
	/**
	 * Create a round from a white move, and an optional black move.
	 * 
	 * @param white - whites move; cannot be null;
	 * @param black - blacks move; may be null.
	 */
	public Round(Move white, Move black) {
		if(white == null) {
			throw new IllegalArgumentException("A round must always consist of a move by white");
		}
		this.white = white;
		this.black = black;
	}

	/**
	 * Get a white move
	 * @return - white move
	 */
	public Move white() {
		return white;
	}

	/**
	 * Get a black move
	 * @return - black move
	 */
	public Move black() {
		return black;
	}

	/**
	 * This method represents a round with white move and black move respectively as String
	 * @return - a String which represents a round
	 */
	public String toString() {
		String r = white.toString();
		if(black != null) {
			r += " " + black.toString();
		}
		return r;
	}
}
