package pl.gomoku.game.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.gomoku.game.model.exception.UuidNotValidException;
import pl.gomoku.game.model.request.CreateGameRequest;
import pl.gomoku.game.model.response.CreateGameResponse;
import pl.gomoku.game.model.response.GameResponse;
import pl.gomoku.game.service.GameService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateGameResponse> createGomokuGame(@RequestBody CreateGameRequest createGameRequest) {
        return ResponseEntity.ok(gameService.createGame(createGameRequest));
    }

    @GetMapping("/{uuidIdentifier}")
    public ResponseEntity<GameResponse> getGomokuGame(@PathVariable("uuidIdentifier") String uuidIdentifier) {
        UUID gameIdentifier;
        try {
            gameIdentifier = UUID.fromString(uuidIdentifier);
        } catch (IllegalArgumentException ex) {
            throw new UuidNotValidException(ex);
        }
        return ResponseEntity.ok(gameService.getGomokuGame(gameIdentifier));

    }

}
