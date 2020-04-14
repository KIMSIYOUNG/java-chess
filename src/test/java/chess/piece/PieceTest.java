package chess.piece;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import chess.publisher.BoardPublisher;
import chess.strategy.NotMovedPawnStrategy;

class PieceTest {
	@Test
	void create() {
		BoardPublisher publisher = new BoardPublisher();
		Piece pawn = new Piece(publisher, PieceType.PAWN, new NotMovedPawnStrategy());
		assertThat(publisher.getObservers()).contains(pawn);
	}
}