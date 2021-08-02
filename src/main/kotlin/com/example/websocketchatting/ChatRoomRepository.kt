package com.example.websocketchatting

import org.springframework.data.jpa.repository.JpaRepository

interface ChatRoomRepository : JpaRepository<ChatRoom, Long> {
    fun findByRoomId(roomId: String): ChatRoom?
}