package com.test.kafkaconsumer.demoConsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import com.test.kafkaconsumer.demoConsumer.KafkaDao;

@Service
public class KafkaDataService {
	
	
	@Autowired(required=true)
	public KafkaDao kafkaDao;
	
	
	public void addData(KafkaData KafkaData)  
	{    
		kafkaDao.save(KafkaData);    
	}    
	

}
