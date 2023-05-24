package com.example.ClientTest;

import com.example.client.model.Order;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@Configuration
public class ClientTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientTestApplication.class, args);
	}

	@Bean
	public Map<Long, Order> getOrderMap() {
		return new HashMap<>();
	}

}
