package com.example.mongodb.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClientFactory;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.mongodb.autoconfigure.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MongoConfig {

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    /*
    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(mongoUri);
    }
    */

    @Bean
    @Primary
    MongoProperties mongoProperties() {
        MongoProperties properties = new MongoProperties();
        properties.setUri(mongoUri);
        return properties;
    }
}
