package publisher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board implements Publisher {
	private final List<Observer> observers;

	public Board() {
		this.observers = new ArrayList<>();
	}

	@Override
	public void add(Observer moveStrategy) {
		observers.add(moveStrategy);
	}

	@Override
	public void delete(Observer observer) {

	}

	@Override
	public void notify(Observer observer) {

	}

	public List<Observer> getObservers() {
		return Collections.unmodifiableList(observers);
	}
}
