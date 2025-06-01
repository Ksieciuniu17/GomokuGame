package pl.gomoku.game.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class GameResponse {

    private String whitePlayer;
    private String blackPlayer;
}
