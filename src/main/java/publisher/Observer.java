package publisher;

import chess.piece.Piece;
import chess.position.Position;

import java.util.Map;

public interface Observer {
	void update(Map<Position, Piece> board);

	int getSize();
}
