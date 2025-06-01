package pl.gomoku.game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GomokuGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(GomokuGameApplication.class, args);
	}

}
