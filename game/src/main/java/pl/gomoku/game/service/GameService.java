package pl.gomoku.game.service;

import pl.gomoku.game.model.request.CreateGameRequest;
import pl.gomoku.game.model.response.CreateGameResponse;

public interface GameService {

    CreateGameResponse createGame(CreateGameRequest createGameRequest);
}
