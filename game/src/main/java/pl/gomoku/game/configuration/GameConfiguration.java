package pl.gomoku.game.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.gomoku.game.model.GomokuGame;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class GameConfiguration {

    @Bean
    public Map<UUID, GomokuGame> gomokuGamesMap () {
        return new ConcurrentHashMap<>();
    }
}
