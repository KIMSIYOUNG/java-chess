import org.junit.jupiter.api.Test;
import publisher.Board;
import publisher.Publisher;
import strategy.NotMovedPawnStrategy;

import static org.assertj.core.api.Assertions.assertThat;

public class PublisherTest {
    @Test
    void create() {
        assertThat(new Board()).isInstanceOf(Publisher.class);
    }

    @Test
    void add() {
        NotMovedPawnStrategy pawn = new NotMovedPawnStrategy();
        Board board = new Board();
        board.add(pawn);
        assertThat(board.getObservers().contains(pawn)).isTrue();
    }
}
