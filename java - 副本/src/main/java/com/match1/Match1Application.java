package com.match1;

import com.match1.server.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Match1Application {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(Match1Application.class, args);
		new Server().start();
	}

}
