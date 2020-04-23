package chess;

import chess.controller.SparkController;

public class WebUIChessApplication {
	public static void main(String[] args) {
		new SparkController().route();
	}
}
