package com.em.tag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TagApplication {

	public static void main(String[] args) {
		SpringApplication.run(TagApplication.class, args);
	}

}
