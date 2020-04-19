package ru.itis.repositories.impl;

import org.springframework.stereotype.Repository;
import ru.itis.models.User;
import ru.itis.repositories.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryJpaImpl implements UserRepository {


    @PersistenceContext
    private EntityManager entityManager;

    //language=HQL
    public static final String HQL_FIND_ALL = "from User user";

    //language=HQL
    public static final String HQL_FIND_USER_BY_NAME = "from User user where user.name = :name";

    //language=HQL
    public static final String HQL_FIND_USER_BY_COOKIE = "from User user where user.token = :cookie";

    @Override
    public void save(User entity) {
        entityManager.persist(entity);
    }

    @Override
    public Optional<User> find(Long id) {
        try {
            User user = entityManager.find(User.class, id);
            return Optional.ofNullable(user);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery(HQL_FIND_ALL, User.class).getResultList();
    }

    @Override
    public void update(User entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(find(id));
    }

    @Override
    public Optional<User> findUserByName(String name) {
        try {
            User user = entityManager.createQuery(HQL_FIND_USER_BY_NAME, User.class)
                    .setParameter("name", name)
                    .getSingleResult();
            return Optional.ofNullable(user);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findUserByCookie(String cookie) {
        try {
            User user = entityManager.createQuery(HQL_FIND_USER_BY_COOKIE, User.class)
                    .setParameter("cookie", cookie)
                    .getSingleResult();
            return Optional.ofNullable(user);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
