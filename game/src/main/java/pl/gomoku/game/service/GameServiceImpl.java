package pl.gomoku.game.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.gomoku.game.model.GomokuGame;
import pl.gomoku.game.model.request.CreateGameRequest;
import pl.gomoku.game.model.response.CreateGameResponse;

import java.util.Map;
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
}
