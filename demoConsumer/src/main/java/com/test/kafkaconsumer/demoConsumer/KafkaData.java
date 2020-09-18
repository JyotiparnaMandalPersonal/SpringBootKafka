package com.test.kafkaconsumer.demoConsumer;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name ="KafkaData")
public class KafkaData {
	@Id  
    @Column(name = "id",unique=true, nullable = false, insertable = false, updatable = false )		 
    private Integer id;
 
	@Column(name = "data")
    private String data; 	
        
    
    public KafkaData() {
		
	}        

	public KafkaData(Integer id, String data) {
		
		this.id = id;
		this.data = data;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}




}
