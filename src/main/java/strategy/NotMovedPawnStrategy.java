package strategy;

import publisher.MoveStrategy;
import publisher.Publisher;

public class NotMovedPawnStrategy implements MoveStrategy {
	private final Publisher publisher;

	public NotMovedPawnStrategy(Publisher publisher) {
		this.publisher = publisher;
		publisher.add(this);
	}
}
