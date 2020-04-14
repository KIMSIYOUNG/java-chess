package chess;

import org.junit.jupiter.api.Test;

class PositionTest {
	@Test
	void ofTest() {
		Position a1 = Position.of("a1");
		System.out.println(a1.toString());
	}
}