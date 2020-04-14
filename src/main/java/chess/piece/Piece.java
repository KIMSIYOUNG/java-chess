package chess.piece;

import java.util.Map;

import chess.Position;
import chess.publisher.MoveStrategy;
import chess.publisher.Observer;
import chess.publisher.Publisher;

public class Piece implements Observer {
	private final PieceType pieceType;
	private final MoveStrategy moveStrategy;
	private Map<Position, Piece> board;

	public Piece(Publisher publisher, PieceType pieceType, MoveStrategy moveStrategy) {
		this.pieceType = pieceType;
		this.moveStrategy = moveStrategy;
		publisher.add(this);
	}

	@Override
	public void update(Map<Position, Piece> board) {
		this.board = board;
	}

	@Override
	public int getSize() {
		return 0;
	}
}
