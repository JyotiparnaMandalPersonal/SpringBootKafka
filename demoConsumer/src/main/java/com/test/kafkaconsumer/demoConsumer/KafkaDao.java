package com.test.kafkaconsumer.demoConsumer;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


	
public interface KafkaDao extends CrudRepository<KafkaData, Integer> {

		//List<KafkaData> findById(Integer id);
		
	}


