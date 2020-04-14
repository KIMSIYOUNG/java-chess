package chess.publisher;

import java.util.HashMap;
import java.util.Map;

import chess.Position;
import chess.piece.Piece;
import chess.piece.PieceType;
import chess.strategy.NotMovedPawnStrategy;

public class BoardFactory {
	public static Map<Position, Piece> create(BoardPublisher publisher) {
		Map<Position, Piece> board = new HashMap<>();
		board.put(Position.of("a2"), new Piece(PieceType.PAWN, new NotMovedPawnStrategy(publisher)));
		return board;
	}
}
