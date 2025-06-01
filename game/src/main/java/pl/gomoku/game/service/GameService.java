package pl.gomoku.game.service;

import pl.gomoku.game.model.request.CreateGameRequest;
import pl.gomoku.game.model.response.CreateGameResponse;
import pl.gomoku.game.model.response.GameResponse;

import java.util.UUID;

public interface GameService {

    CreateGameResponse createGame(CreateGameRequest createGameRequest);

    GameResponse getGomokuGame(UUID uuidIdentifier);
}
