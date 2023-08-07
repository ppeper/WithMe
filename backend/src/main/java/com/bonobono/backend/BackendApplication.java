package com.bonobono.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// aws가 아닌 로컬 환경에서 스프링부트 실행시 발생하는 에러 해결
@SpringBootApplication(exclude = {
		io.awspring.cloud.autoconfigure.context.ContextInstanceDataAutoConfiguration.class,
		io.awspring.cloud.autoconfigure.context.ContextStackAutoConfiguration.class,
		io.awspring.cloud.autoconfigure.context.ContextRegionProviderAutoConfiguration.class
})
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
