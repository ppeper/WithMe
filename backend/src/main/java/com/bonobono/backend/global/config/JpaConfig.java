package com.bonobono.backend.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
//import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;

@EnableJpaAuditing
//@EnableReactiveMongoAuditing
@Configuration
public class JpaConfig {
}
