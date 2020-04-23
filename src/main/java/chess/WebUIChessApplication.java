package chess;

import chess.controller.BoardController;

public class WebUIChessApplication {
	public static void main(String[] args) {
		BoardController controller = new BoardController();
		controller.route();
	}
}
