package pl.gomoku.game.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class CreateGameResponse {

    private UUID gameIdentifier;
    private String whitePlayer;
    private String blackPlayer;
}
