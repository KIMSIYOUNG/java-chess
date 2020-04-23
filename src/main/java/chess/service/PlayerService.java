package chess.service;

import chess.dao.PlayerDao;
import chess.domain.Team;

import java.sql.SQLException;
import java.util.Map;

public class PlayerService {
    private final PlayerDao playerDao = new PlayerDao();

    public int create(String playerName, String playerPassword, String team) throws SQLException, ClassNotFoundException {
        int id = playerDao.create(playerName, playerPassword, team);
        return id;
    }
}
