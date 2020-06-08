package chessview;

import chessview.viewer.BoardFrame;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * <h1>Class Main</h1>
 * This is main class of the project
 */
public class Main {
	/**
	 * This is main method
	 */
	public static void main(String[] args) {
		String gameMatchPath = "";
		try {
			if(args.length > 0) {
				gameMatchPath = args[0];
			} else {
				gameMatchPath = "src/default-game.txt";
			}
			ChessGame game = new ChessGame(new FileReader(gameMatchPath));
			new BoardFrame(game);
			textView(game);
		} catch(IOException e) {			
			System.err.println("Error loading file: " + gameMatchPath);
			System.err.println(e.getMessage());			
		}
	}

	/**
	 * This method print out the board by text
	 * @param game - board game
	 */
	
	public static void textView(ChessGame game) {
		try {			
			List<Board> boards = game.boards();
			List<Round> rounds = game.rounds();
			
			System.out.println(boards.get(0));
			
			for(int i=0;i!=rounds.size();++i) {
				System.out.println("\n==================");	
				Round r = rounds.get(i);
				System.out.println("WHITE PLAYS: " + r.white());				
				if(((2*i)+1) >= boards.size()) {
					throw new RuntimeException("Invalid move!");
				}
				Board wb = boards.get((2*i)+1);																			
				System.out.println(wb);
				if(r.black() != null) {
					System.out.println("\nBLACK PLAYS: " + r.black());
					if(((2*i)+2) >= boards.size()) {
						throw new RuntimeException("Invalid move!");
					}
					Board bb = boards.get((2*i)+2);
					System.out.println(bb);
				}				
			}			
			
		} catch(Exception e) {			
			e.printStackTrace(); // useful for debugging
		}
	}
}
