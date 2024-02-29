package com.hunger.saviour.portal;

import com.hunger.saviour.portal.services.CustomMongoRepositoryImpl;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.UuidRepresentation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableDiscoveryClient
@EnableMongoRepositories(basePackages = "com.hunger.saviour.portal", repositoryBaseClass = CustomMongoRepositoryImpl.class)
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

//	@Bean
//	public MongoClient mongo() throws Exception {
//		ConnectionString connectionString = new ConnectionString("mongodb+srv://iharikrishna180:hari@hungersaviour.efr4kgm.mongodb.net/?retryWrites=true&w=majority");
//
//		MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
//				.uuidRepresentation(UuidRepresentation.STANDARD)
//				.applyConnectionString(connectionString).build();
//		return MongoClients.create(mongoClientSettings);
//	}
}
