package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import chess.domain.Team;
import chess.domain.Turn;

public class PlayerDao {
	public Turn findTurn(int playerId) throws SQLException, ClassNotFoundException {
		String query = "select * from player where player_id = (?)";
		try (Connection con = ConnectionLoader.load();
			 PreparedStatement pstmt = con.prepareStatement(query)) {
			pstmt.setInt(1, playerId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					String team = rs.getString("team");
					return new Turn(Team.of(team));
				}
				throw new IllegalArgumentException("Turn이 잘못되었습니다.");
			}
		}
	}

	public int create(String name, String password, String team) throws SQLException, ClassNotFoundException {
		String query = "insert into player(name, password, team) value (?, ?, ?)";
		try (Connection con = ConnectionLoader.load(); PreparedStatement pstmt = con.prepareStatement(query,
			PreparedStatement.RETURN_GENERATED_KEYS)) {
			pstmt.setString(1, name);
			pstmt.setString(2, password);
			pstmt.setString(3, team);
			pstmt.executeUpdate();
			return getId(pstmt);
		}
	}

	private int getId(PreparedStatement pstmt) throws SQLException {
		try (ResultSet rs = pstmt.getGeneratedKeys()) {
			if (rs.next()) {
				return rs.getInt(1);
			}
			throw new IllegalArgumentException();
		}
	}
}
