package ru.itis.repositories.impl;

import org.springframework.stereotype.Repository;
import ru.itis.models.Message;
import ru.itis.models.Room;
import ru.itis.repositories.MessageRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class MessageRepositoryJpaImpl implements MessageRepository {

    @PersistenceContext
    private EntityManager entityManager;

    //language=HQL
    public static final String HQL_FIND_MESSAGES_BY_ROOM = "select message from Message message join Room room on message.room = room where room.id = :id order by message.timeSent";

    @Override
    public List<Message> findMessageByRoom(Room room) {
        return entityManager.createQuery(HQL_FIND_MESSAGES_BY_ROOM, Message.class)
                .setParameter("id", room.getId())
                .getResultList();
    }

    @Override
    public void save(Message entity) {
        entityManager.persist(entity);
    }

    @Override
    public Optional<Message> find(Long id) {
        try {
            Message message = entityManager.find(Message.class, id);
            return Optional.ofNullable(message);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Message> findAll() {
        return entityManager.createQuery("from Message", Message.class)
                .getResultList();
    }

    @Override
    public void update(Message entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(find(id));
    }
}
