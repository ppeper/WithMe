package com.bonobono.backend.global.config;

<<<<<<< HEAD
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.context.request.RequestContextListener;
//import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;

@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "com.bonobono.backend.chatting.repository")
@EnableMongoRepositories(basePackages = "com.bonobono.backend.chatting.mongo")
@Configuration
public class JpaConfig {


=======
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JpaConfig {
//    @SpringBootApplication과 @EnableJpaAuditing을 분리하기 위해 만든 클래스
>>>>>>> origin/develop
}
