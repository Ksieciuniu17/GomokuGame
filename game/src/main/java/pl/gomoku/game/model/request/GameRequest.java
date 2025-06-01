package pl.gomoku.game.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Setter
@Getter
@Builder
public class GameRequest {

    private UUID gameIdentifier;
    private OffsetDateTime gameStartDate;
    private String whitePlayer;
    private String blackPlayer;

    public GameRequest(UUID gameIdentifier, OffsetDateTime gameStartDate, String whitePlayer, String blackPlayer) {
        this.gameIdentifier = gameIdentifier;
        this.gameStartDate = gameStartDate;
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
    }

    public GameRequest() {
    }
}
