package com.ast.furnishly.repositories;

import java.util.List;
import java.util.Optional;

/**
 * Generic repository interface for managing domain objects.
 *
 * @param <T> The type of the domain object.
 * @param <K> The type of the identifier (ID) for the domain object.
 */
public interface Repository<T, K> {

    /**
     * Saves the given domain object.
     *
     * @param t The domain object to save.
     * @return The saved domain object.
     */
    T save(T t);

    /**
     * Updates the given domain object.
     *
     * @param t The domain object to update.
     */
    void update(T t);

    /**
     * Deletes a domain object by its ID.
     *
     * @param id The ID of the domain object to delete.
     * @return `true` if the deletion was successful, otherwise `false`.
     */
    boolean deleteById(K id);

    /**
     * Finds a domain object by its ID.
     *
     * @param id The ID of the domain object to find.
     * @return An optional containing the found domain object, or an empty optional if not found.
     */
    Optional<T> findById(K id);

    /**
     * Retrieves all domain objects.
     *
     * @return A list of all domain objects.
     */
    List<T> findAll();

    /**
     * Checks if a domain object with the given ID exists.
     *
     * @param id The ID of the domain object to check.
     * @return `true` if the domain object exists, otherwise `false`.
     */
    boolean existsById(K id);
}
