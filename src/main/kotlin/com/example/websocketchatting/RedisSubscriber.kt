package com.example.websocketchatting

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.stereotype.Service

@Service
class RedisSubscriber(
    val objectMapper: ObjectMapper,
    val redisTemplate: RedisTemplate<String, Any>,
    val messagingTemplate: SimpMessageSendingOperations
) : MessageListener {


    /**
     * Redis에서 메시지가 발행(publish)되면 대기하고 있던 onMessage가 해당 메시지를 받아 처리한다.
     */
    override fun onMessage(message: Message, pattern: ByteArray?) {
        try {
            // redis에서 발행된 데이터를 받아 deserialize
            // ChatMessage 객채로 맵핑
            val roomMessage = objectMapper.readValue(
                redisTemplate.stringSerializer.deserialize(message.body),
                ChatMessage::class.java
            )
            // Websocket 구독자에게 채팅 메시지 Send
            messagingTemplate.convertAndSend("/sub/chat/room/" + roomMessage.roomId, roomMessage)
        } catch (e: Exception) {
//            log.error(e.message)
        }
    }
}
