package ru.itis.repositories;

import ru.itis.models.Room;

import java.util.Optional;

public interface RoomRepository extends CrudRepository<Room, Long> {

    Optional<Room> findByGeneratedName(String generatedName);


}
