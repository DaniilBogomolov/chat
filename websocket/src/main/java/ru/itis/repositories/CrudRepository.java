package ru.itis.repositories;

import java.util.List;
import java.util.Optional;


public interface CrudRepository<E, ID> {
    void save(E entity);
    Optional<E> find(ID id);
    List<E> findAll();
    void update(E entity);
    void delete(ID id);
}
