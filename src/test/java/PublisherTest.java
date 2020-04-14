import org.junit.jupiter.api.Test;
import publisher.Board;
import publisher.Publisher;

import static org.assertj.core.api.Assertions.assertThat;

public class PublisherTest {
    @Test
    void create() {
        assertThat(new Board()).isInstanceOf(Publisher.class);
    }
}
