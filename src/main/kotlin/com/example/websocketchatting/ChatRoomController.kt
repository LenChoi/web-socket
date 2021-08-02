package com.example.websocketchatting

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/chat")
class ChatRoomController(
    val chatRoomRepository: ChatRoomRepository
) {
    @GetMapping("/room")
    fun rooms(model: Model): String {
        return "/chat/room"
    }

    @GetMapping("/rooms")
    @ResponseBody
    fun room(): MutableList<ChatRoom> {
        println("여기옴")
        return chatRoomRepository.findAll()
    }

    @PostMapping("/room")
    @ResponseBody
    fun createRoom(name: String): ChatRoom {
        var chatRoom = ChatRoom.create(name)

        return chatRoomRepository.save(chatRoom)
    }

    @GetMapping("/room/enter/{roomId}")
    fun roomDetail(model: Model, @PathVariable roomId: String): String {
        model.addAttribute("room", roomId)
        return "/chat/roomdetail"
    }

    @GetMapping("/room/{roomId}")
    @ResponseBody
    fun roomInfo(@PathVariable roomId:String): ChatRoom? {
        return chatRoomRepository.findByRoomId(roomId)
    }
}