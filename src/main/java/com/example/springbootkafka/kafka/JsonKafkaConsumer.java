package com.example.springbootkafka.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.springbootkafka.payload.User;

@Service
public class JsonKafkaConsumer {
	
	private static final Logger logger  = LoggerFactory.getLogger(JsonKafkaConsumer.class);
	
	
	   @KafkaListener(topics = "javaguides" , groupId = "myGroup")
	   public void consume(User user) {
		   
		   logger.info(String.format("Json message received %s", user.toString()));
		   
	   }

}
