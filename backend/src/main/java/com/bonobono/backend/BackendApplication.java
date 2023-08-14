package com.bonobono.backend;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@OpenAPIDefinition(servers = {@Server(url="https://i9d105.p.ssafy.io", description = "default server url")})
// aws가 아닌 로컬 환경에서 스프링부트 실행시 발생하는 에러 해결
@SpringBootApplication(exclude = {
		io.awspring.cloud.autoconfigure.context.ContextInstanceDataAutoConfiguration.class,
		io.awspring.cloud.autoconfigure.context.ContextStackAutoConfiguration.class,
		io.awspring.cloud.autoconfigure.context.ContextRegionProviderAutoConfiguration.class
})
public class BackendApplication {

	@PostConstruct
	void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
	}

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
