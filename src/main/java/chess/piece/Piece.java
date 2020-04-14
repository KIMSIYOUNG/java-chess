package chess.piece;

import chess.publisher.MoveStrategy;

public class Piece {
	private final PieceType pieceType;
	private final MoveStrategy moveStrategy;

	public Piece(PieceType pieceType, MoveStrategy moveStrategy) {
		this.pieceType = pieceType;
		this.moveStrategy = moveStrategy;
	}
}
