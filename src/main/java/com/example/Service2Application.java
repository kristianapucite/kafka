package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class Service2Application {

	public static void main(String[] args) {
		try {
			SpringApplication.run(Service2Application.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	@Bean
//	CommandLineRunner commandLineRunner(KafkaTemplate<String, String> template)  {
//		return args -> {
//			template.send("testTopic", "this is working");
//		};
//	}

}
