package pl.gomoku.game.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.gomoku.game.model.request.GameRequest;

@FeignClient(value = "gameStatisticClient", url = "http://localhost:8081/")
public interface GameStatisticFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "/game",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    void createGame(@RequestBody GameRequest gameRequest);
}
