package pl.gomoku.game.model.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateGameRequest {

    private String whitePlayerNickname;
    private String blackPlayerNickname;
    @NotNull
    @Min(10)
    @Max(1000)
    private Integer boardSize;
}
