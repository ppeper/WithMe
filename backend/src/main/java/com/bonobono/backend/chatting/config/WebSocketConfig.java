package com.bonobono.backend.chatting.config;

import com.bonobono.backend.chatting.handler.SocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;



@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer{

    @Autowired
    SocketHandler socketHandler;

    // websocket핸드셰이크 수행
    // 요청은 핸들러로 라우트 되고
    // beforeHandshake메소드에서 헤더 중 필요한 값을 가져와 true값 반환하면 Upgrade 헤더와 함께 101 Switching Protocols 상태 코드를 포함한 응답 반환
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(socketHandler, "/ws/chat/{roomNumber}")
                .addInterceptors(new HttpSessionHandshakeInterceptor(), new CustomHandshakeInterceptor())
                .setAllowedOrigins("https://i9d105.p.ssafy.io:8081");
    }

    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxBinaryMessageBufferSize(500000);
        container.setMaxTextMessageBufferSize(500000);
        return container;
    }


}