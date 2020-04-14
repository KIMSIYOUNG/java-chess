package publisher;

import chess.piece.Piece;
import chess.position.Position;

import java.util.*;

public class Board implements Publisher {
    private final Map<Position, Piece> board;
    private final List<Observer> observers;

    public Board(Map<Position, Piece> board) {
        this.board = board;
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
