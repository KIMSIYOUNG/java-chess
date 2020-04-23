package chess.controller;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.dao.PlayerDao;
import chess.dao.RoomDao;
import chess.domain.position.Position;
import chess.service.BoardService;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class BoardController {
	private final BoardService boardService = new BoardService();
	private final Gson gson = new GsonBuilder().create();

	public void route() {
		Spark.staticFileLocation("/templates");
		get("/", this::index);
		get("/init", this::init);
		get("/new_game/:id", this::createNewGame);
		get("/board/:id", this::load);
		post("/move/:id", this::move);
		post("/users", this::users);
	}

	private String index(Request request, Response response) {
		try {
			Map<String, Object> model = new HashMap<>();
			return render(model, "index.html");
		} catch (Exception e) {
			response.status(500);
			return gson.toJson(e.getMessage());
		}
	}

	private String init(Request request, Response response) {
		try {
			return render(new HashMap<>(), "userNames.html");
		} catch (Exception e) {
			response.status(500);
			return gson.toJson(e.getMessage());
		}
	}

	private String createNewGame(Request request, Response response) {
		try {
			HashMap<String, Object> model = new HashMap<>();
			model.put("id", request.params(":id"));
			boardService.create(Integer.parseInt(request.params(":id")));
			return render(model, "playingGame.html");
		} catch (Exception e) {
			response.status(500);
			return gson.toJson(e.getMessage());
		}
	}

	private String load(Request request, Response response) {
		try {
			int id = Integer.parseInt(request.params(":id"));
			return gson.toJson(boardService.load(id));
		} catch (Exception e) {
			response.status(500);
			return gson.toJson(e.getMessage());
		}
	}

	private String move(Request request, Response response) {
		try {
			int id = Integer.parseInt(request.params(":id"));
			Map<String, Integer> map = gson.fromJson(request.body(), new TypeToken<Map<String, Integer>>() {
			}.getType());
			Position source = Position.of(map.get("sourceX"), map.get("sourceY"));
			Position target = Position.of(map.get("targetX"), map.get("targetY"));

			return gson.toJson(boardService.move(id, source, target));
		} catch (Exception e) {
			response.status(500);
			return gson.toJson(e.getMessage());
		}
	}

	// TODO: 2020/04/23
	private String users(Request request, Response response) {
		try {
			PlayerDao playerDao = new PlayerDao();
			Map<String, String> req = gson.fromJson(request.body(), new TypeToken<Map<String, String>>() {
			}.getType());
			int playerId = playerDao.create(req.get("name"), req.get("password"));
			RoomDao roomDao = new RoomDao();
			int roomId = roomDao.create(playerId);
			HashMap<String, Object> model = new HashMap<>();
			model.put("id", roomId);
			return gson.toJson(model);
		} catch (Exception e) {
			response.status(500);
			return gson.toJson(e.getMessage());
		}
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}

