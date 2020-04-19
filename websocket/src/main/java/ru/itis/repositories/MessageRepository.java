package ru.itis.repositories;

import ru.itis.models.Message;
import ru.itis.models.Room;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {

    List<Message> findMessageByRoom(Room room);
}
