package com.test.kafkaconsumer.demoConsumer;
// https://www.confluent.io/blog/spring-kafka-can-your-kafka-consumers-handle-a-poison-pill/
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.LoggingErrorHandler;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer2;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;

@EnableKafka
@Configuration
public class KafkaConfiguration {
	
	@Autowired
    private KafkaOperations<String, Integer> kafkaOperations;
	
	@Bean
	public ConsumerFactory<String,String> consumerFactory() {
		System.out.println("inside ConsumerFactory");
		Map<String, Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG ,"localhost:9092");
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
		config.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, JsonDeserializer.class);
		config.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName());
		//config.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class);
		//config.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, StringDeserializer.class);
		

		
					
		return new DefaultKafkaConsumerFactory<>(config);
	}
	
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(){
		System.out.println("inside ConcurrentKafkaListenerContainerFactory");
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<String,String>();
		factory.setConsumerFactory(consumerFactory());		
		factory.setErrorHandler(errorHandler(new DeadLetterPublishingRecoverer(kafkaOperations)));
		
		return factory;
	}
	
	 	
	
	 @Bean
	  public SeekToCurrentErrorHandler errorHandler(DeadLetterPublishingRecoverer deadLetterPublishingRecoverer) {
		// System.out.println("inside SeekToCurrentErrorHandler");
	    return new SeekToCurrentErrorHandler(deadLetterPublishingRecoverer);
	  }
	 
	 @Bean
	  public DeadLetterPublishingRecoverer publisher(KafkaOperations bytesTemplate) {
		// System.out.println("inside DeadLetterPublishingRecoverer");
	    return new DeadLetterPublishingRecoverer(bytesTemplate);
	  }
	 
	 
	 @Bean
	  public LoggingErrorHandler errorHandler() {
	    return new LoggingErrorHandler();
	  }
	  
	  //Not working
	/*@Bean
	public ConcurrentKafkaListenerContainerFactory<Object, Object> kafkaListenerContainerFactory(ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
																								ConsumerFactory<Object, Object> kafkaConsumerFactory,
																								KafkaTemplate<Object, Object> template)
	{
		ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<Object, Object>();
		  configurer.configure(factory, kafkaConsumerFactory);
		 factory.setErrorHandler(new SeekToCurrentErrorHandler(new DeadLetterPublishingRecoverer(template), 3));
		// factory.setErrorHandler(new SeekToCurrentErrorHandler());
		  return factory;
	}*/
	
	/*
	 * the ErrorHandlingDeserializer ensures that the poison pill is handled and logged. 
	 * The consumer offset moves forward so that the consumer can continue consuming the next record.
	 * https://www.confluent.io/blog/spring-kafka-can-your-kafka-consumers-handle-a-poison-pill/
	 * https://docs.spring.io/spring-kafka/docs/2.2.0.RELEASE/reference/html/_reference.html#error-handling-deserializer
	 */
	
	/* @Bean
	  public LoggingErrorHandler errorHandler() {
	    return new LoggingErrorHandler();
	  }
	*/
	
	

}
