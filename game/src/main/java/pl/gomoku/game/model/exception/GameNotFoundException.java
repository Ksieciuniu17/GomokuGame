package pl.gomoku.game.model.exception;

import lombok.Getter;

@Getter
public class GameNotFoundException extends RuntimeException {

    public GameNotFoundException(String message) {
        super(message);
    }
}
