package com.test.kafkaconsumer.demoConsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.test.kafkaconsumer.demoConsumer.KafkaData;

//This is consumer application

@SpringBootApplication
public class DemoConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoConsumerApplication.class, args);
	}
	
	
	@Bean
	public KafkaData getKafkaData() {
		return new KafkaData();
	}

}
