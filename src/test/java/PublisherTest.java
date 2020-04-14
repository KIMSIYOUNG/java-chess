import chess.piece.Piece;
import chess.position.Position;
import org.junit.jupiter.api.Test;
import publisher.Board;
import publisher.Observer;
import publisher.Publisher;
import strategy.NotMovedPawnStrategy;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class PublisherTest {
    private Map<Position, Piece> board = new HashMap<>();

    @Test
    void create() {
        assertThat(new Board(board)).isInstanceOf(Publisher.class);
    }

    @Test
    void add() {
        Board board = new Board(this.board);
        NotMovedPawnStrategy pawn = new NotMovedPawnStrategy(board);
        board.add(pawn);
        assertThat(board.getObservers().contains(pawn)).isTrue();
    }

    @Test
    void update() {
        board.put(new Position(), new Piece());
        Publisher publisher = new Board(board);
        Observer pawn = new NotMovedPawnStrategy(publisher);
        publisher.notifyToAll();
        assertThat(pawn.getSize()).isEqualTo(1);
    }
}
