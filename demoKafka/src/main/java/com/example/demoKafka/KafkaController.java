package com.example.demoKafka;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

	private final Producer1 producer1;
  

    @Autowired
    KafkaController(Producer1 producer) {
        this.producer1 = producer;
     
    }


    
    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
  // public void sendMessageToKafkaTopic(@RequestParam("message") int message) {
        producer1.sendMessage(message);
    	
        
    }
    
    
    
	
    

}