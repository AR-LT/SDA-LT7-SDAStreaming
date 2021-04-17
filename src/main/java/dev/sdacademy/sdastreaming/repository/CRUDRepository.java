package dev.sdacademy.sdastreaming.repository;

import dev.sdacademy.sdastreaming.entity.Entity;

import java.util.List;

public interface CRUDRepository<T extends Entity> {

    void create(T author);  // CREATE

    void update(T author);  // READ

    List<T> findAll();      // UPDATE

    void delete(int id);    // DELETE

    default void save(T entity) {
        if (entity.getId() != null) {
            update(entity);
        } else {
            create(entity);
        }
    }
}
