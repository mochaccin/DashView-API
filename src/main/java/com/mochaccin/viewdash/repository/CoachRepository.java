package com.mochaccin.viewdash.repository;

import com.mochaccin.viewdash.model.Coach;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Coach entities.
 * This interface extends MongoRepository, providing CRUD operations and additional query methods for Coach entities.
 */
@Repository
public interface CoachRepository extends MongoRepository<Coach, ObjectId> {

    /**
     * Checks if a Coach with the given name exists.
     *
     * @param name The name of the Coach to check for existence.
     * @return true if a Coach with the given name exists, false otherwise.
     */
    boolean existsByName(String name);
}
