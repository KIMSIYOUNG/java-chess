package chess;

import chess.piece.Piece;
import chess.piece.PieceType;
import chess.publisher.BoardPublisher;
import chess.publisher.Observer;
import chess.publisher.Publisher;
import chess.strategy.NotMovedPawnStrategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class PublisherTest {
	private Map<Position, Piece> rawBoard;
	private BoardPublisher board;
	private Publisher publisher;

	@BeforeEach
	void setUp() {
		rawBoard = new HashMap<>();
		board = new BoardPublisher();
		publisher = new BoardPublisher();
	}

	@Test
	void create() {
		assertThat(board).isInstanceOf(Publisher.class);
	}

	@Test
	void add() {
		NotMovedPawnStrategy pawn = new NotMovedPawnStrategy(board);
		board.add(pawn);
		assertThat(board.getObservers().contains(pawn)).isTrue();
	}

	@Test
	void update() {
		Publisher publisher = new BoardPublisher();
		Observer pawn = new NotMovedPawnStrategy(publisher);
		publisher.notifyToAll();
		assertThat(pawn.getSize()).isEqualTo(1);
	}
}
