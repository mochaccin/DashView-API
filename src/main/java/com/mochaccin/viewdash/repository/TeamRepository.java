package com.mochaccin.viewdash.repository;

import com.mochaccin.viewdash.model.Team;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing Team objects in MongoDB.
 */
@Repository
public interface TeamRepository extends MongoRepository<Team, ObjectId> {

    /**
     * Find a Team object by its name.
     *
     * @param name The name of the team to search for.
     * @return An Optional containing the Team object if found, or an empty Optional if not found.
     */
    Optional<Team> findTeamByName(String name);

    /**
     * Check if a Team with the given name exists.
     *
     * @param name The name of the team to check for existence.
     * @return true if a Team with the given name exists, false otherwise.
     */
    boolean existsByName(String name);
}