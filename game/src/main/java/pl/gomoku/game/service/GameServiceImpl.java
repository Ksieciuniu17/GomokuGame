package pl.gomoku.game.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.gomoku.game.feign.GameStatisticFeignClient;
import pl.gomoku.game.model.domain.GomokuGame;
import pl.gomoku.game.model.exception.GameNotFoundException;
import pl.gomoku.game.model.request.CreateGameRequest;
import pl.gomoku.game.model.request.GameRequest;
import pl.gomoku.game.model.response.CreateGameResponse;
import pl.gomoku.game.model.response.GameResponse;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameServiceImpl implements GameService {

    private final Map<UUID, GomokuGame> gomokuGamesMap;
    private final GameStatisticFeignClient gameStatisticFeignClient;

    @Override
    public CreateGameResponse createGame(CreateGameRequest createGameRequest) {
        UUID gameIdentifier = UUID.randomUUID();
        gomokuGamesMap.putIfAbsent(
                gameIdentifier,
                new GomokuGame(
                        createGameRequest.getWhitePlayerNickname(),
                        createGameRequest.getBlackPlayerNickname(),
                        createGameRequest.getBoardSize()));
        gameStatisticFeignClient.createGame(GameRequest.builder()
                        .gameStartDate(OffsetDateTime.now())
                        .blackPlayer(createGameRequest.getBlackPlayerNickname())
                        .whitePlayer(createGameRequest.getWhitePlayerNickname())
                        .gameIdentifier(gameIdentifier)
                .build());
        log.info("New gomoku game created for identifier: %s".formatted(gameIdentifier));
        return CreateGameResponse.builder()
                .gameIdentifier(gameIdentifier)
                .whitePlayer(createGameRequest.getWhitePlayerNickname())
                .blackPlayer(createGameRequest.getBlackPlayerNickname())
                .build();
    }

    @Override
    public GameResponse getGomokuGame(UUID uuidIdentifier) {
        if (uuidIdentifier == null) {
            throw new GameNotFoundException("Provided game identifier cannot be null!");
        }
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
