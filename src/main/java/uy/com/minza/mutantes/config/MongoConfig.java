package uy.com.minza.mutantes.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "uy.com.minza.mutantes.repository.mongo")
public class MongoConfig {

}
