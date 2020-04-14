package chess;

public class Position {
	private final int x;
	private final int y;

	private Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public static Position of(String input) {
		String upper = input.toUpperCase();
		return new Position(upper.charAt(0) - 64, Integer.parseInt(upper.substring(1)));
	}

	@Override
	public String toString() {
		return "x : " + x + "y : " + y;
	}
}
