package chessview.viewer;

import chessview.Board;
import chessview.Round;
import chessview.moves.Move;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * <h1>Class RoundCanvas</h1>
 * This class construct graphic for a rectangle to show total rounds of a game
 */
public class RoundCanvas extends Canvas {
	private ArrayList<Round> rounds;
	private ArrayList<Board> boards;
	private int index = 0;
	private static String[] preferredFonts = {"Arial","Times New Roman"};
	private Font font;

	/**
	 * Construct position, size of rectangle contains all rounds and size of characters in usage
	 * @param rounds - list of rounds
	 * @param boards - list of boards
	 */
	public RoundCanvas(List<Round> rounds, List<Board> boards) {
		this.rounds = new ArrayList<Round>(rounds);
		this.boards = new ArrayList<Board>(boards);
		setBounds(0, 0, 200, 400);
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();		
		HashSet<String> availableNames = new HashSet();
		
		for(String name : env.getAvailableFontFamilyNames()) {
			availableNames.add(name);			
		}
		
		for(String pf : preferredFonts) {
			if(availableNames.contains(pf)) {
				font = new Font(pf,Font.PLAIN,20);
				break;
			}
		}
	}
	/**
	 * Draw a white rectangle and set color for characters in usage
	 * @param g - object of graphic class
	 */
	public void paint(Graphics g) {		
		g.setColor(Color.WHITE);
		g.fillRect(0,0,getWidth(),getHeight());		
		g.setFont(font);
		FontMetrics metrics = g.getFontMetrics();
		int height = metrics.getHeight();
		
		int ypos = metrics.getAscent();		
		
		for(int i=0;i!=rounds.size();++i) {
			if((i*2)+1 >= boards.size() || boards.get(i*2) == null) {
				g.setColor(Color.RED);
			} else if((i*2) < index) {
				g.setColor(Color.BLACK);
			} else {
				g.setColor(Color.GREEN);
			}
			Move white = rounds.get(i).white();			
			char[] chars = white.toString().toCharArray();			
			g.drawChars(chars,0,chars.length,10,ypos);
			ypos += height;
		}
		
		int midx = (getWidth()-20) / 2;
		ypos = metrics.getAscent();
		
		for(int i=0;i!=rounds.size();++i) {
			if(((i*2)+2) >= boards.size()) {
				g.setColor(Color.RED);
			} else if(((i*2)+1) < index) {
				g.setColor(Color.BLACK);
			} else {
				g.setColor(Color.GREEN);
			}
			Move black = rounds.get(i).black();			
			if(black != null) {
				char[] chars = black.toString().toCharArray();			
				g.drawChars(chars,0,chars.length,midx,ypos);
				ypos += height;
			}
		}								
	}

	/**
	 * repaint round after forward move
	 * @param amount - amount of move
	 */
	public void fwd(int amount) {
		index = Math.min(boards.size()-1,index + amount);
		repaint();
	}

	/**
	 * repaint round after backward move
	 * @param amount - amount of move
	 */
	public void bwd(int amount) {
		index = Math.max(0,index - amount);
		repaint();
	}
}
