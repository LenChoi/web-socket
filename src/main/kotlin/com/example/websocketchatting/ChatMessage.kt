package com.example.websocketchatting

import java.awt.TrayIcon

/**
 * 채팅 메시지를 주고 받기 위한 DTO
 * 상황에 따라 채팅방 입장, 채팅방에 메시지 보내기 두가지 상황이 있음
 */
class ChatMessage(
    /** 메시지 타입: 입장, 채팅**/
    var type: MessageType,

    var roomId: String,
    var sender: String,
    var message: String
)