package chess.publisher;

import chess.Position;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BoardPublisherTest {
    @ParameterizedTest
    @CsvSource(value = {"a2,a3"})
    void move(String source, String target) {
        BoardPublisher board = new BoardPublisher();
        board.move(Position.of(source), Position.of(target));
        assertThat(board.isTherePiece(Position.of(target))).isTrue();
    }
}