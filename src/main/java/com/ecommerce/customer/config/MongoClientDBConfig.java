package com.ecommerce.customer.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

    @Configuration
    @EnableMongoRepositories(basePackages = "YOUR.PACKAGE.WITH.repository")
    class MongoClientDBConfig extends AbstractMongoClientConfiguration {

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

        @Override
        public String getDatabaseName() {
            return dbName;
        }

        @Override
        public MongoClient mongoClient() {
            ConnectionString connectionString = new ConnectionString(getConnectionString());
            MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                    .applyConnectionString(connectionString)
                    .build();
            return MongoClients.create(mongoClientSettings);
        }

        private String getConnectionString() {
            if (activeProfile.contains("local")) {
                return String.format("mongodb://%s:%s/%s", dbUri, dbPort, dbName);
            }
            return String.format("mongodb://%s:%s@%s:%s/%s?ssl=true&replicaSet=rs0&readpreference=secondaryPreferred&retrywrites=false",
                    dbUser, dbPassword, dbUri, dbPort, dbName);
        }

}
