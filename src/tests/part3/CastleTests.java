package tests.part3;

import org.junit.Test;
import junit.framework.TestCase;
import static tests.TestHelpers.*;

public class CastleTests extends TestCase {
	
	public @Test void testValidCastling() {
		String[][] tests = { 
				// Test 1
				{"e2-e4 e7-e6\nNg1-f3 d7-d6\nBf1-c4 f7-f6\nO-O",
				 "8|r|n|b|q|k|b|n|r|\n"+
				 "7|p|p|p|_|_|_|p|p|\n"+
				 "6|_|_|_|p|p|p|_|_|\n"+
				 "5|_|_|_|_|_|_|_|_|\n"+
				 "4|_|_|B|_|P|_|_|_|\n"+
				 "3|_|_|_|_|_|N|_|_|\n"+
				 "2|P|P|P|P|_|P|P|P|\n"+
				 "1|R|N|B|Q|_|R|K|_|\n"+
				 "  a b c d e f g h"
				},
				// Test 2
				{"e2-e3 e7-e6\nd2-d3 Ng8-f6\nc2-c3 Bf8-c5\na2-a3 O-O",
				 "8|r|n|b|q|_|r|k|_|\n"+
				 "7|p|p|p|p|_|p|p|p|\n"+
				 "6|_|_|_|_|p|n|_|_|\n"+
				 "5|_|_|b|_|_|_|_|_|\n"+
				 "4|_|_|_|_|_|_|_|_|\n"+
				 "3|P|_|P|P|P|_|_|_|\n"+
				 "2|_|P|_|_|_|P|P|P|\n"+
				 "1|R|N|B|Q|K|B|N|R|\n"+
				 "  a b c d e f g h"
				},
				// Test 3
				{"d2-d4 a7-a6\nQd1-d3 b7-b6\nBc1-e3 c7-c6\nNb1-a3 d7-d6\nO-O-O",
				 "8|r|n|b|q|k|b|n|r|\n"+
				 "7|_|_|_|_|p|p|p|p|\n"+
				 "6|p|p|p|p|_|_|_|_|\n"+
				 "5|_|_|_|_|_|_|_|_|\n"+
				 "4|_|_|_|P|_|_|_|_|\n"+
				 "3|N|_|_|Q|B|_|_|_|\n"+
				 "2|P|P|P|_|P|P|P|P|\n"+
				 "1|_|_|K|R|_|B|N|R|\n"+
				 "  a b c d e f g h"
				},
				// Test 4
				{"a2-a3 d7-d5\nb2-b3 Qd8-d6\nc2-c3 Bc8-e6\nd2-d3 Nb8-a6\ne2-e3 O-O-O",
				 "8|_|_|k|r|_|b|n|r|\n"+
				 "7|p|p|p|_|p|p|p|p|\n"+
				 "6|n|_|_|q|b|_|_|_|\n"+
				 "5|_|_|_|p|_|_|_|_|\n"+
				 "4|_|_|_|_|_|_|_|_|\n"+
				 "3|P|P|P|P|P|_|_|_|\n"+
				 "2|_|_|_|_|_|P|P|P|\n"+
				 "1|R|N|B|Q|K|B|N|R|\n"+
				 "  a b c d e f g h"
				}
		
		};
		checkValidTests(tests);
	}
	
	public @Test void testInvalidCastling() {
		String[] tests = {
			"e2-e4 e7-e6\nNg1-f3 d7-d6\nO-O",
			"e2-e3 e7-e6\nd2-d3 Ng8-f6\nc2-c3 O-O",
			"d2-d4 a7-a6\nQd1-d3 b7-b6\nNb1-a3 d7-d6\nO-O-O",
			"a2-a3 d7-d5\nb2-b3 Qd8-d6\nc2-c3 Bc8-e6\ne2-e3 O-O-O",
			"e2-e4 e7-e6\nNg1-f3 d7-d6\nBf1-c4 f7-f6\nKe1-f1 g7-g6\nKf1-e1 h7-h6\nO-O", // tricksy
			"d2-d4 a7-a6\nQd1-d3 b7-b6\nBc1-e3 c7-c6\nNb1-a3 d7-d6\nKe1-d1 g7-g6\nKd1-e1 h7-h6\nO-O-O",
			"Nb1-a3 a7-a6\nd2-d3 d7-d6\nBc1-h6 e7-e6\nQd1-d2 f7-f5\ne2-e4 Qd8-g5\nb2-b3 Qg5-g4\nO-O-O",
			"Nb1-a3 a7-a6\nd2-d3 d7-d6\nBc1-h6 e7-e6\nQd1-d2 f7-f5\ne2-e4 Qd8-g5\nf2-f3 Qg5-h4+\nO-O-O"
		};
		
		checkInvalidTests(tests);
	}
}
