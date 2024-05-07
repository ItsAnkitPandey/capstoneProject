package com.cabbooking.tripbookingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TripbookingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TripbookingServiceApplication.class, args);
	}

}
