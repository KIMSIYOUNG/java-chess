package chess.publisher;

import chess.piece.Piece;
import chess.Position;

import java.util.*;

public class BoardPublisher implements Publisher {
    private final Map<Position, Piece> board;
    private final List<Observer> observers;

    public BoardPublisher() {
        this.board = new HashMap<>();
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
    public void notifyToAll() {
        for (Observer observer : observers) {
            observer.update(board);
        }
    }

    @Override
    public void notifySpecificObserver(Observer observer) {
    	if(!observers.contains(observer)){
    		throw new IllegalArgumentException("sex");
		}
    	observer.update(board);
    }

    public List<Observer> getObservers() {
        return Collections.unmodifiableList(observers);
    }
}
