package com.example.demoKafka;

import java.util.Properties;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import org.json.simple.JSONObject;

@Service
public class Producer1 {

	
	  private static final Logger logger = LoggerFactory.getLogger(Producer1.class);
	  
	   
  
	  
	  
	  private static final String TOPIC = "users1";
	  
	  @Autowired private KafkaTemplate<String, String> kafkaTemplate;
	  
	  public void sendMessage(String message) {   
		  
	  logger.info(String.format("#### -> Producing message -> %s", message));
	  this.kafkaTemplate.send(TOPIC, message);	  
	  
	  }
	 
	
	
	
	

	/*
	 * public void sendMessage(String message) { String topicName = "myTopic";
	 * Properties props = new Properties(); props.setProperty("bootstrap.servers",
	 * "localhost:9092"); props.setProperty("acks", "all");
	 * props.setProperty("retries", "0"); props.setProperty("batch.size", "16384");
	 * props.setProperty("linger.ms", "1"); props.setProperty("buffer.memory",
	 * "33554432"); props.setProperty("key.serializer",
	 * "org.apache.kafka.common.serialization.StringSerializer");
	 * props.setProperty("value.serializer",
	 * "org.apache.kafka.common.serialization.StringSerializer");
	 * 
	 * Producer<String, String> producer = new KafkaProducer<String, String>(props);
	 * 
	 * for(int i = 0; i < 10; i++) producer.send(new ProducerRecord<String,
	 * String>(topicName, Integer.toString(i), Integer.toString(i) ));
	 * 
	 * System.out.println("Message sent successfully"); producer.close();
	 * 
	 * }
	 */

}
