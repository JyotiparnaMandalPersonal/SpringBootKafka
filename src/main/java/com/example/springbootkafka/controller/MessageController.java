package com.example.springbootkafka.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootkafka.kafka.KafkaProducer;

@RestController
@RequestMapping("/api/vi/kafka")
public class MessageController {
	
	
	private KafkaProducer kafkaProducer;

	public MessageController(KafkaProducer kafkaProducer) {
		this.kafkaProducer = kafkaProducer;
	}
	
	//   Query Parameter
	//   http://localhost:8099/api/vi/kafka/publish?message=hello world
	@GetMapping("/publish")
	public ResponseEntity<String> publish(@RequestParam("message") String message){
		
		kafkaProducer.sendMessage(message);
		return ResponseEntity.ok("Message sent to the topic");
	}
	
	

}
