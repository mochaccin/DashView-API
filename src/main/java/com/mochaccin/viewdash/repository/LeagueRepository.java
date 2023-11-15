package com.mochaccin.viewdash.repository;

import com.mochaccin.viewdash.model.League;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing League objects in MongoDB.
 */
@Repository
public interface LeagueRepository extends MongoRepository<League, ObjectId> {

    /**
     * Finds a League by its name.
     *
     * @param name The name of the League to be searched for.
     * @return An Optional containing the League if found, or an empty Optional if not found.
     */
    Optional<League> findLeagueByName(String name);

    /**
     * Checks if a League with the given name exists.
     *
     * @param name The name of the League to check for existence.
     * @return true if a League with the given name exists, false otherwise.
     */
    boolean existsByName(String name);
}
