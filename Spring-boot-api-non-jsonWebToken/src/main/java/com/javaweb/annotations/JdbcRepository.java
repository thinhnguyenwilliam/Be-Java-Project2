package com.javaweb.annotations;

import java.util.List;
import java.util.Optional;

public interface JdbcRepository<T> 
{

    // Save an entity to the database
    void save(T entity);

    // Find an entity by its ID
    Optional<T> findById(Integer id);

    // Return all entities of type T
    List<T> findAll();

    // Update an entity
    void update(T entity);

    // Delete an entity by its ID
    void deleteById(Integer id);
}
