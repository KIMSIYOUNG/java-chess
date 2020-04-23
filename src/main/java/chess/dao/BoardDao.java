package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import chess.domain.Board;
import chess.dto.BoardDto;

public class BoardDao {

	public Board create(int roomId, Board board) throws SQLException, ClassNotFoundException {
		List<BoardDto> mappers = BoardMapper.createBoardDto(board);
		String query = "insert into board(room_id, piece_name, piece_team, piece_position) values (?,?,?,?)";
		try (Connection con = ConnectionLoader.load(); PreparedStatement pstmt = con.prepareStatement(query)) {
			for (BoardDto mapper : mappers) {
				pstmt.setInt(1, roomId);
				pstmt.setString(2, mapper.pieceName());
				pstmt.setString(3, mapper.pieceTeam());
				pstmt.setString(4, mapper.piecePosition());
				pstmt.addBatch();
			}
			pstmt.executeBatch();
		}
		return board;
	}

	public Board findByRoomId(int roomId) throws SQLException, ClassNotFoundException {
		String query = "select * from board where room_id = (?)";
		try (Connection con = ConnectionLoader.load(); PreparedStatement pstmt = con.prepareStatement(query)) {
			pstmt.setInt(1, roomId);
			try (ResultSet rs = pstmt.executeQuery()) {
				List<BoardDto> mappers = new ArrayList<>();
				while (rs.next()) {
					mappers.add(new BoardDto(rs.getString(2), rs.getString(3), rs.getString(4)));
				}
				return BoardMapper.createBoard(mappers);
			}
		}
	}

	public void updateBoard(int roomId, String from, String to) throws SQLException, ClassNotFoundException {
		String query = "update board set piece_position = ? where room_id =? and piece_position = ?";
		try (Connection con = ConnectionLoader.load(); PreparedStatement pstmt = con.prepareStatement(query)) {
			pstmt.setString(1, to);
			pstmt.setInt(2, roomId);
			pstmt.setString(3, from);
			pstmt.executeUpdate();
		}
	}
}
