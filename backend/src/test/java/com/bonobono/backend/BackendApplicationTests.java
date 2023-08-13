package com.bonobono.backend;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;


@OpenAPIDefinition(servers = {@Server(url = "/", description = "Default Server URL")})

@SpringBootTest
@WebAppConfiguration
class BackendApplicationTests {

	@Test
	void contextLoads() {
	}

}
