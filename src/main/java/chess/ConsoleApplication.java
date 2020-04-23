package chess;

import chess.controller.ConsoleController;
import chess.domain.state.Ready;

public class ConsoleApplication {
	public static void main(String[] args) {
		ConsoleController controller = new ConsoleController(new ChessGame(new Ready()));
		controller.run();
	}
}
