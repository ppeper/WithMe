package com.bonobono.backend.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;

@EnableJpaAuditing
//@EnableJpaRepositories(basePackages = "com.bonobono.backend.chatting.repository")
@EnableMongoRepositories(basePackages = "com.bonobono.backend.chatting.mongo")
@Configuration
public class JpaConfig {

}
