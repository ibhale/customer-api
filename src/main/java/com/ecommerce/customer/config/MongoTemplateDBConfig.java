package com.ecommerce.customer.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "YOUR.PACKAGE.WITH.repository")
class MongoTemplateDBConfig {

    @Value("local")
    private String activeProfile;

    @Value("${mongodb.host:localhost}")
    private String dbUri;
    @Value("${mongodb.port:27017}")
    private int dbPort;
    @Value("${mongodb.database.name:ecommerce1}")
    private String dbName;
    @Value("${mongodb.username:}")
    private String dbUser;
    @Value("${mongodb.password:}")
    private String dbPassword;

    @Bean
    public MongoTemplate mongoTemplate() {

        //MongoClient is the actual pool used by mongo. Create it using client factory then, autoclosing of threads are handled on its own
        MongoDatabaseFactory factory = new SimpleMongoClientDatabaseFactory(getConnectionString());
        MongoTemplate template = new MongoTemplate(factory);

        return template;
    }

    private String getConnectionString() {
        if (activeProfile.contains("local")) {
            return String.format("mongodb://%s:%s/%s", dbUri, dbPort, dbName);
        }
        return String.format("mongodb://%s:%s@%s:%s/%s?ssl=true&replicaSet=rs0&readpreference=secondaryPreferred&retrywrites=false",
                dbUser, dbPassword, dbUri, dbPort, dbName);
    }

}
