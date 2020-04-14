package chess.strategy;

import chess.piece.Piece;
import chess.Position;
import chess.publisher.MoveStrategy;
import chess.publisher.Publisher;

import java.util.Map;

public class NotMovedPawnStrategy implements MoveStrategy {
	private Map<Position, Piece> board;
	private final Publisher publisher;

	public NotMovedPawnStrategy(Publisher publisher) {
		this.publisher = publisher;
		publisher.add(this);
	}

	@Override
	public void update(Map<Position, Piece> board) {
		this.board = board;
	}

	@Override
	public int getSize() {
		return board.values().size();
	}
}
