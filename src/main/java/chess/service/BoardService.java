package chess.service;

import java.sql.SQLException;

import chess.ChessGame;
import chess.dao.BoardDao;
import chess.dao.PlayerDao;
import chess.dao.RoomDao;
import chess.domain.Board;
import chess.domain.Turn;
import chess.domain.position.Position;
import chess.domain.state.BoardFactory;
import chess.domain.state.Playing;
import chess.dto.GameDto;

public class BoardService {
	private final BoardDao boardDao = new BoardDao();
	private final RoomDao roomDao = new RoomDao();
	private final PlayerDao playerDao = new PlayerDao();

	public Board create(int roomId) throws SQLException, ClassNotFoundException {
		Board board = boardDao.create(roomId, BoardFactory.create());
		return board;
	}

	public GameDto load(int roomId) throws SQLException, ClassNotFoundException {
		Board board = boardDao.findByRoomId(roomId);
		int turnPlayerId = roomDao.findTurnPlayerId(roomId);
		Turn turn = playerDao.findTurn(turnPlayerId);
		ChessGame game = new ChessGame(new Playing(board, turn));
		return new GameDto(board.getBoard(), turn, game.status());
	}

	public GameDto move(int roomId, Position source, Position target) throws SQLException, ClassNotFoundException {
		Board board = boardDao.findByRoomId(roomId);
		int turnPlayerId = roomDao.findTurnPlayerId(roomId);
		Turn turn = playerDao.findTurn(turnPlayerId);
		ChessGame game = new ChessGame(new Playing(board, turn));
		game.move(source, target);
		boardDao.updateBoard(roomId, source.getName(), target.getName());
		return new GameDto(game.board().getBoard(), turn, game.status());
	}
}
