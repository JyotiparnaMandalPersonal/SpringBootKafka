package com.test.kafkaconsumer.demoConsumer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;
import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.test.kafkaconsumer.demoConsumer.KafkaDao;
import com.test.kafkaconsumer.demoConsumer.KafkaDataService;

//import com.example.demoKafka.datastore.KafkaData;
//import com.example.demoKafka.datastore.KafkaDataService;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.Consumer;

@Service
public class Consumer1 {
	
	private final Logger logger = LoggerFactory.getLogger(Consumer1.class);

	
	
	@Autowired(required=true)
	public KafkaDataService kafkaDataService;
	
	@Autowired(required=true)
	public KafkaData kafkaData;
	

	
	
    @KafkaListener(topics = "users1", groupId = "group_id" , containerFactory = "kafkaListenerContainerFactory")
   // public void consume(String message) throws IOException {
    public void consume(ConsumerRecord<?,?> record) throws IOException {

       //logger.info(String.format("#### -> Consumed message -> %s", message));
    	logger.info(String.format("#### -> Consumed message -> %s", record.value()));
       
    	//throw new RuntimeException("test");
       kafkaData.setId((int)record.offset());
       kafkaData.setData(String.valueOf(record.value()));
       kafkaDataService.addData(kafkaData);
      
    }
       

    

}
