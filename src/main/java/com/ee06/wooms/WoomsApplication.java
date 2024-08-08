package com.ee06.wooms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class WoomsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WoomsApplication.class, args);
	}

}
