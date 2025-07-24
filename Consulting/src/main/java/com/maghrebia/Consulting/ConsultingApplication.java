package com.maghrebia.Consulting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages ="com.maghrebia.Consulting.feign")
public class ConsultingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsultingApplication.class, args);
	}

}
