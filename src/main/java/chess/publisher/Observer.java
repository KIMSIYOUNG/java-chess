package chess.publisher;

import chess.piece.Piece;
import chess.Position;

import java.util.Map;

public interface Observer {
	void update(Map<Position, Piece> board);

	int getSize();
}
