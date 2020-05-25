package tests.part2;

import org.junit.Test;
import junit.framework.TestCase;
import static tests.TestHelpers.*;

public class InvalidKnightTests extends TestCase {
	
	public @Test void testInvalidKnightMoves() {
		String[] tests = { 
			"Nb1-b3",
			"Nb1-c4",
			"Nb1-d2",
			"Nb1-c3 d7-d5\nNc3-d5",
			"Nb1-c3 e7-e6\nNc3-a3"
		};
		checkInvalidTests(tests);
	}
	
	public @Test void testInvalidKnightTakes() {
		String[] tests = {
			"Nb1xd2",
			"Nb1xb7",
			"Nb1-c3 e7-e6\nNc3xe6",
			"e2-e4 Nb8-c6\ne4-e5 Nc6xd4",
			"d2-d4 Nb8-c6\nNg1-f3 Nc6-e5\nNf3xBe5"			
		};
		
		checkInvalidTests(tests);
	}
}
