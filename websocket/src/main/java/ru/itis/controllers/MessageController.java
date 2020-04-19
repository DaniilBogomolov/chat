package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import ru.itis.dto.MessageDto;
import ru.itis.dto.RoomMessageDto;
import ru.itis.models.Message;
import ru.itis.services.RoomService;

@Controller
public class MessageController {

    @Autowired
    private RoomService roomService;

    @MessageMapping("/room/{roomName}")
    @SendTo("/topic/room/{roomName}")
    public MessageDto sendMessage(@DestinationVariable String roomName,
                                  RoomMessageDto roomMessageDto) {
        return roomService.sendMessageToRoom(roomName, roomMessageDto);
    }
}
