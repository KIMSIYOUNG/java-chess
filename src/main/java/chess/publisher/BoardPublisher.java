package chess.publisher;

import chess.Position;
import chess.piece.Piece;
import chess.piece.PieceType;
import chess.strategy.NotMovedPawnStrategy;

import java.util.*;

public class BoardPublisher implements Publisher {
    private Map<Position, Piece> board;
    private final List<Observer> observers;

    public BoardPublisher() {
        this.observers = new ArrayList<>();
        this.board = new HashMap<>();
        init();
    }

    private void init() {
        board.put(Position.of("a2"), new Piece(this, PieceType.PAWN, new NotMovedPawnStrategy()));
        notifyToAll();
    }

    @Override
    public void add(Observer piece) {
        observers.add(piece);
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
        if (!observers.contains(observer)) {
            throw new IllegalArgumentException("sex");
        }
        observer.update(board);
    }

    public List<Observer> getObservers() {
        return Collections.unmodifiableList(observers);
    }

    public void move(final Position source, final Position target) {
        Piece sourcePiece = board.get(source);
        if (Objects.isNull(sourcePiece)) {
            throw new IllegalArgumentException("sex");
        }
        board.put(target, sourcePiece);
        board.remove(source);
    }

    public boolean isTherePiece(Position source) {
        return Objects.nonNull(board.get(source));
    }
}
