package ru.itis.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.dto.*;
import ru.itis.models.Message;
import ru.itis.models.Room;
import ru.itis.models.User;
import ru.itis.repositories.MessageRepository;
import ru.itis.repositories.RoomRepository;
import ru.itis.repositories.UserRepository;
import ru.itis.services.RoomService;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<RoomDto> getRooms() {
        return roomRepository.findAll().stream()
                .map(RoomDto::from)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void createRoom(CreateRoomDto createRoomDto) {
        Room room = Room.builder()
                .originalName(createRoomDto.getName())
                .generatedName(UUID.randomUUID().toString())
                .build();
        roomRepository.save(room);
    }

    @Override
    public RoomWithMessagesHistoryDto getRoomByGeneratedName(String roomName) {
        Room room = roomRepository.findByGeneratedName(roomName).orElseThrow(IllegalStateException::new);
        List<MessageDto> messages = messageRepository.findMessageByRoom(room).stream()
                .map(MessageDto::from)
                .collect(Collectors.toList());

        return RoomWithMessagesHistoryDto.builder()
                .name(room.getOriginalName())
                .generatedName(room.getGeneratedName())
                .messages(messages)
                .build();
    }

    @Override
    @Transactional
    public MessageDto sendMessageToRoom(String roomGeneratedName, RoomMessageDto roomMessageDto) {
        Room room = roomRepository.findByGeneratedName(roomGeneratedName).orElseThrow(IllegalStateException::new);
        User user = userRepository.findUserByCookie(roomMessageDto.getSender()).orElseThrow(IllegalStateException::new);

        Message message = Message.builder()
                .text(roomMessageDto.getText())
                .sender(user)
                .room(room)
                .timeSent(new Date())
                .build();
        messageRepository.save(message);
        return MessageDto.from(message);
    }
}
