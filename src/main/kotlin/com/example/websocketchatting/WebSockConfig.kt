package com.example.websocketchatting

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.*

@Configuration
@EnableWebSocket
class WebSockConfig: WebSocketMessageBrokerConfigurer{
    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        /** 메시지 구독 요청 **/
        registry.enableSimpleBroker("/sub")

        /** 메시지 발행 요청 **/
        registry.setApplicationDestinationPrefixes("/pub")
    }

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        /**
         * stomp websocket의 연결 endpoint
         * 개발서버의 접속 주소는 ws://localhost:8080/ws-stomp
         */
        registry.addEndpoint("/ws-stomp").setAllowedOriginPatterns("*").withSockJS()
    }
}