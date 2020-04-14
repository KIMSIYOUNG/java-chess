package chess.publisher;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BoardFactoryTest {
	@Test
	void create() {
		BoardPublisher boardPublisher = new BoardPublisher();
		boardPublisher.setBoard();

		assertThat(boardPublisher.getObservers()).hasSize(1);
	}
}