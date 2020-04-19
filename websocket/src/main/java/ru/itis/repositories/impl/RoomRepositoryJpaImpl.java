package ru.itis.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.itis.models.Room;
import ru.itis.repositories.RoomRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class RoomRepositoryJpaImpl implements RoomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    //language=HQL
    public static final String HQL_FIND_ROOM_BY_GENERATED_NAME = "select room from Room room where room.generatedName = :name";

    @Override
    public Optional<Room> findByGeneratedName(String generatedName) {
        try {
            Room room = entityManager.createQuery(HQL_FIND_ROOM_BY_GENERATED_NAME, Room.class)
                    .setParameter("name", generatedName)
                    .getSingleResult();
            return Optional.ofNullable(room);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(Room entity) {
        entityManager.persist(entity);
    }

    @Override
    public Optional<Room> find(Long id) {
        try {
            Room room = entityManager.find(Room.class, id);
            return Optional.ofNullable(room);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Room> findAll() {
        return entityManager.createQuery("from Room", Room.class).getResultList();
    }

    @Override
    public void update(Room entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(find(id));
    }
}
