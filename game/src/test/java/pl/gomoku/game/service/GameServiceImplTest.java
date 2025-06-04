package pl.gomoku.game.service;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.gomoku.game.model.domain.GameBoard;
import pl.gomoku.game.model.domain.GomokuGame;
import pl.gomoku.game.model.exception.GameNotFoundException;
import pl.gomoku.game.model.exception.UuidNotValidException;
import pl.gomoku.game.model.response.GameResponse;

import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameServiceImplTest {

    @Mock
    private Map<UUID, GomokuGame> gomokuGameMap;

    @InjectMocks
    private GameServiceImpl gameService;

    @Test
    void createGame() {
        //TODO tests
    }

    @Test
    void whenUuuidIdentifierExistsThenReturnGameData() {
        UUID validUuid = UUID.randomUUID();
        GameResponse response = GameResponse.builder()
                .whitePlayer("Stefan")
                .blackPlayer("Mariola")
                .build();
        when(gomokuGameMap.get(validUuid))
                .thenReturn(new GomokuGame("Stefan", "Mariol", new GameBoard(15)));

        GameResponse gameResponse = gameService.getGomokuGame(validUuid);

        verify(gomokuGameMap).get(any(UUID.class));
        verify(gomokuGameMap).get(validUuid);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(gameResponse).isNotNull();
        softAssertions.assertThat(gameResponse.getWhitePlayer())
                .isNotNull()
                .isEqualTo(response.getWhitePlayer());
        softAssertions.assertThat(gameResponse.getBlackPlayer())
                .isNotNull()
                .isEqualTo(response.getBlackPlayer());
        softAssertions.assertAll();
    }
    @Test
    void whenInvalidGameIdentifierNullThrowGameNotFoundException() {

        UUID invalidUuid = null;
        assertThrows(UuidNotValidException.class, () -> {
            gameService.getGomokuGame(invalidUuid);
        });
    }
    @Test
    void whenUUidIndentifierNotExistsInGameMapThenThrowNotFoundException() {
        UUID validUuid = UUID.randomUUID();
        when(gomokuGameMap.get(validUuid)).thenThrow(new GameNotFoundException("Wrong UUID"));
        assertThrows(GameNotFoundException.class, () -> {
            gameService.getGomokuGame(validUuid);});
    }
}