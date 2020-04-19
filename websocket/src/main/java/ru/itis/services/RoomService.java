package ru.itis.services;

import ru.itis.dto.*;
import ru.itis.models.Room;

import java.util.List;

public interface RoomService {

    List<RoomDto> getRooms();

    void createRoom(CreateRoomDto createRoomDto);

    RoomWithMessagesHistoryDto getRoomByGeneratedName(String room);

    MessageDto sendMessageToRoom(String roomGeneratedName, RoomMessageDto roomMessageDto);
}
