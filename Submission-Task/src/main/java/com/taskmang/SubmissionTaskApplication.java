package com.taskmang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;



@EnableFeignClients
@SpringBootApplication
public class SubmissionTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubmissionTaskApplication.class, args);
	}

}
