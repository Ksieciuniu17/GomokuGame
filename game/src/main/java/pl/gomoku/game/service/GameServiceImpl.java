package pl.gomoku.game.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.gomoku.game.model.GomokuGame;
import pl.gomoku.game.model.exception.GameNotFoundException;
import pl.gomoku.game.model.request.CreateGameRequest;
import pl.gomoku.game.model.response.CreateGameResponse;
import pl.gomoku.game.model.response.GameResponse;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameServiceImpl implements GameService {

    private final Map<UUID, GomokuGame> gomokuGamesMap;

    @Override
    public CreateGameResponse createGame(CreateGameRequest createGameRequest) {
        UUID gameIdentifier = UUID.randomUUID();
        gomokuGamesMap.putIfAbsent(
                gameIdentifier,
                new GomokuGame(
                        createGameRequest.getWhitePlayerNickname(),
                        createGameRequest.getBlackPlayerNickname(),
                        createGameRequest.getBoardSize()));
        log.info("New gomoku game created for identifier: %s".formatted(gameIdentifier));
        return CreateGameResponse.builder()
                .gameIdentifier(gameIdentifier)
                .whitePlayer(createGameRequest.getWhitePlayerNickname())
                .blackPlayer(createGameRequest.getBlackPlayerNickname())
                .build();
    }

    @Override
    public GameResponse getGomokuGame(UUID uuidIdentifier) {
        return Optional.ofNullable(gomokuGamesMap.get(uuidIdentifier))
                .map(game -> GameResponse.builder()
                        .whitePlayer(game.getWhitePlayer())
                        .blackPlayer(game.getBlackPlayer())
                        .build())
                .orElseThrow(() -> new GameNotFoundException(
                        "Game with identifier not found for: %s"
                                .formatted(uuidIdentifier)));
    }
}
