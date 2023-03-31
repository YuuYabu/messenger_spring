package com.example.messenger_spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@Configuration
@EnableWebSocketMessageBroker // Stompを使用するための宣言
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{
  @Override
  public void configureMessageBroker(MessageBrokerRegistry registry) {
    // pub/sub メッセージングを実現するのに、メッセージを登録するリクエストのprefixを /sub とする
    registry.enableSimpleBroker("/sub");
    // pub/sub メッセージングを実現するのに、メッセージを発行するリクエストのprefixを /pub とする
    registry.setApplicationDestinationPrefixes("/pub");
  }

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    // Stomp WebSocket の接続endpoint
    registry.addEndpoint("/ws-stomp").withSockJS();
  }
}
