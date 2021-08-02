package com.example.websocketchatting

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class ChatRoom(
    var roomId: String,

    var name: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
) {
    companion object {
        fun create(name: String): ChatRoom {
            return ChatRoom(UUID.randomUUID().toString(), name)
        }
    }
}