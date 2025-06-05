package com.example.springbootkafka.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootkafka.kafka.JsonKafkaProducer;
import com.example.springbootkafka.payload.User;

@RestController
@RequestMapping("/api/v1/kafka")
public class JsomMessageController {
	     
	     private JsonKafkaProducer jsonKafkaProducer;

	     
		public JsomMessageController(JsonKafkaProducer jsonKafkaProducer) {
			this.jsonKafkaProducer = jsonKafkaProducer;
		}
		
		
		
		@PostMapping("/publish")
		public ResponseEntity<String> publish(@RequestBody User user){
			
			jsonKafkaProducer.sendMessage(user);
			return ResponseEntity.ok("Json message sent to Kafka topic");
		}
		
	     
	     
	

}
